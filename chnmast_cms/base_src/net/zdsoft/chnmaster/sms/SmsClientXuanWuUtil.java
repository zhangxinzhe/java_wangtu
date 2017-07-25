/*
 * @(#)SmsSendUtil.java    Created on 2014-1-17
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.sms;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;

import net.zdsoft.chnmaster.config.Config;
import net.zdsoft.chnmaster.service.SmsSendDetailService;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.enums.ReceiveStatusEnum;
import net.zdsoft.common.exoperate.ApplicationContextHolder;
import net.zdsoft.common.utils.WarningMailUtil;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2014-1-17 下午01:51:29 $
 */
public class SmsClientXuanWuUtil {

    /**
     * 发短信线程池的大小
     */
    private static final int POOL_SIZE = 20;

    private final ExecutorService sendExecutService = Executors.newFixedThreadPool(POOL_SIZE);

    private static SmsClientXuanWuUtil instance = new SmsClientXuanWuUtil();

    private static String smsWolunUrl = "http://121.40.60.163:8081/message/services/sendmsg";

    @Resource
    public SmsSendDetailService smsSendDetailService;

    public static SmsClientXuanWuUtil getInstance() {
        return instance;
    }

    /**
     * 发送短信,手机号码和短信内容的个数必须一致
     *
     * @param phones
     *            手机号码
     * @param batchID
     *            批次唯一序号
     * @param contents
     *            短信内容
     * @param type
     *            0表示自定义短信，1表示固定格式短信
     */
    public void post(String[] sequences, String[] phones, String[] contents, int type) {
        sendExecutService.submit(new SmsSendExecutor(sequences, phones, contents, type));
    }

    /**
     * 发送短信
     *
     * @param phone
     *            手机号码
     * @param content
     *            短信内容
     * @param type
     *            0表示自定义短信，1表示固定格式短信
     *
     */
    public void post(String sequence, String phones, String contents, int type) {
        post(new String[] { sequence }, new String[] { phones }, new String[] { contents }, type);
    }

    class SmsSendExecutor implements Runnable {
        private final String[] sequences;
        private final String[] phones;
        private final String[] contents;
        private final int contentType;

        public SmsSendExecutor(String[] sequences, String[] phones, String[] contents, int contentType) {
            this.sequences = sequences;
            this.phones = phones;
            this.contents = contents;
            this.contentType = contentType;
        }

        @Override
        public void run() {
            String username = null;
            String password = null;
            // 判断是否是模板格式短信内容
            String gateway = "1";
            if (this.contentType == 1) {
                gateway = Config.getParam(BaseConstants.PHONE_PUBLIC_CONTENT_GATEWAY);
                if ("1".equals(gateway)) {
                    username = Config.getParam(BaseConstants.PHONE_WOLUN_USERNAME);
                    password = Config.getParam(BaseConstants.PHONE_WOLUN_PASSWORD);
                }
                else {
                    username = Config.getParam(BaseConstants.PHONE_PUBLIC_CONTENT_USERNAME);
                    password = Config.getParam(BaseConstants.PHONE_PUBLIC_CONTENT_PASSWORD);
                }

            }
            else {
                username = Config.getParam(BaseConstants.PHONE_DEFINED_CONTENT_USERNAME);
                password = Config.getParam(BaseConstants.PHONE_DEFINED_CONTENT_PASSWORD);
            }
            if ("1".equals(gateway)) {
                sendWoLunSms(username, password);
            }
            else {
                sendXuanWuSms(username, password);
            }
        }

        public void sendWoLunSms(String username, String password) {
            try {
                Service service = new Service();
                Call call = (Call) service.createCall();
                call.setTargetEndpointAddress(new URL(smsWolunUrl));
                call.setOperationName("sendmsg");
                // 沃伦短信子帐号
                String extNo = Config.getParam(BaseConstants.PHONE_WOLUN_EXTNO);
                for (int i = 0; i < phones.length; i++) {
                    if (StringUtils.isBlank(this.phones[i]) || StringUtils.isBlank(this.phones[i].trim())) {
                        ReceiveStatusEnum status = ReceiveStatusEnum.GATEWAY_FAILURE; // 发送到网关失败
                        // 添加错误原因
                        String result = "result:手机号码为空";
                        smsSendDetailService.updateSmsSendDetailStatusBySequence(this.sequences[i], 2, status, result);
                        continue;
                    }
                    String res = (String) call
                            .invoke(new Object[] { username, password, this.contents[i], this.phones[i], extNo });
                    String[] results = res.split(";");
                    String smsid = null;
                    for (String item : results) {
                        if (item.indexOf("smsid") > -1) {
                            String[] items = item.split(":");
                            smsid = items[1];
                        }
                    }

                    if (smsSendDetailService == null) {
                        smsSendDetailService = (SmsSendDetailService) ApplicationContextHolder.getApplicationContext()
                                .getBean("smsSendDetailService");
                    }
                    if (StringUtils.isBlank(smsid)) {
                        ReceiveStatusEnum status = ReceiveStatusEnum.GATEWAY_FAILURE; // 发送到网关失败
                        // 添加错误原因
                        String result = "result:" + res;
                        smsSendDetailService.updateSmsSendDetailStatusBySequence(this.sequences[i], 2, status, result);
                        WarningMailUtil.instance().send("沃伦短信发送错误提醒-seeqences:" + this.sequences[i], result);
                    }
                    else {
                        smsSendDetailService.updateSmsSendDetailStatusBySequences(new String[] { this.sequences[i] },
                                smsid, 2, ReceiveStatusEnum.GATEWAY, res);
                    }
                }
            }
            catch (Exception e) {
                ReceiveStatusEnum status = ReceiveStatusEnum.GATEWAY_FAILURE; // 发送到网关失败
                // 添加错误原因
                String result = "result:发送异常;message=" + (e.getMessage() == null ? "" : e.getMessage());
                smsSendDetailService.updateSmsSendDetailStatusBySequences(this.sequences, null, 2, status, result);
                WarningMailUtil.instance().send("沃伦短信错误异常提醒-" + this.sequences.toString(), e.getMessage());
            }
        }

        /**
         * 玄武短信发送
         *
         * @param username
         * @param password
         */
        public void sendXuanWuSms(String username, String password) {
            Account ac = new Account(username, password);// 设置帐号密码
            PostMsg pm = new PostMsg(); // 新建一个PostMsg对象
            // pm.getCmHost().setHost("211.147.239.62",9080);//您设置的下行端口MOS
            // pm.getWsHost().setHost("211.147.239.62",9070);
            pm.getCmHost().setHost(Config.getParam(BaseConstants.PHONE_CM_HOST),
                    Integer.parseInt(Config.getParam(BaseConstants.PHONE_CM_PORT)));// 您设置的下行端口
            // 400
            pm.getWsHost().setHost(Config.getParam(BaseConstants.PHONE_WS_HOST),
                    Integer.parseInt(Config.getParam(BaseConstants.PHONE_WS_PORT)));

            MTPack pack = new MTPack();
            pack.setBatchName("音乐网短信");
            pack.setBizType(0);
            pack.setMsgType(MTPack.MsgType.SMS);
            pack.setSendType(MTPack.SendType.GROUP);
            pack.setDistinctFlag(false);
            // pack.setCustomNum("");//添加扩展码
            List<MessageData> msgs = new ArrayList<MessageData>();
            List<String> itemSequences = new ArrayList<String>();
            for (int i = 0; i < phones.length; i++) {
                if (StringUtils.isBlank(this.phones[i]) || StringUtils.isBlank(this.phones[i].trim())) {
                    ReceiveStatusEnum status = ReceiveStatusEnum.GATEWAY_FAILURE; // 发送到网关失败
                    // 添加错误原因
                    String result = "result:手机号码为空";
                    smsSendDetailService.updateSmsSendDetailStatusBySequence(this.sequences[i], 1, status, result);
                    continue;
                }
                msgs.add(new MessageData(this.phones[i], this.contents[i], this.sequences[i]));
                itemSequences.add(this.sequences[i]);
                if (msgs.size() == 100) {
                    UUID batchID = UUID.randomUUID();
                    try {
                        pack.setBatchID(batchID);
                        pack.setMsgs(msgs);
                        GsmsResponse resp = pm.post(ac, pack);

                        // System.out.println(resp);
                        // System.out.println("您的UUID为："+resp.getUuid());
                        //
                        // System.out.println("系统返回值为："+resp.getResult());//返回系统返回值 枚举类型
                        // System.out.println(msgs);
                        //
                        // System.out.println(resp.getResult() + "/发送到网关状态报告，0成功，其它值为不成功（详见二次开发接口-2.0（JAVA）开发手册的说明）");
                        if (smsSendDetailService == null) {
                            smsSendDetailService = (SmsSendDetailService) ApplicationContextHolder
                                    .getApplicationContext().getBean("smsSendDetailService");
                        }
                        if (resp.getResult() != 0) {
                            ReceiveStatusEnum status = ReceiveStatusEnum.GATEWAY_FAILURE; // 发送到网关失败
                            // 添加错误原因
                            String result = "result:" + resp.getResult() + ";message="
                                    + (resp.getMessage() == null ? "" : resp.getMessage());
                            smsSendDetailService.updateSmsSendDetailStatusBySequences(
                                    itemSequences.toArray(new String[0]), batchID.toString(), 1, status, result);
                            WarningMailUtil.instance().send("短信发送错误提醒-" + batchID, result);
                        }
                        else {
                            smsSendDetailService.updateBatchIdBySequences(itemSequences.toArray(new String[0]),
                                    batchID.toString(), username);
                        }
                    }
                    catch (Exception e) {
                        ReceiveStatusEnum status = ReceiveStatusEnum.GATEWAY_FAILURE; // 发送到网关失败
                        // 添加错误原因
                        String result = "result:发送异常;message=" + (e.getMessage() == null ? "" : e.getMessage());
                        smsSendDetailService.updateSmsSendDetailStatusBySequences(itemSequences.toArray(new String[0]),
                                batchID.toString(), 1, status, result);
                        WarningMailUtil.instance().send("玄武短信错误异常提醒-" + batchID, e.getMessage());
                    }
                    msgs = new ArrayList<MessageData>();
                    itemSequences = new ArrayList<String>();
                }
            }
            if (CollectionUtils.isEmpty(msgs)) {
                return;
            }
            UUID batchID = UUID.randomUUID();
            try {
                pack.setBatchID(batchID);
                pack.setMsgs(msgs);
                GsmsResponse resp = pm.post(ac, pack);

                // System.out.println(resp);
                // System.out.println("您的UUID为："+resp.getUuid());
                //
                // System.out.println("系统返回值为："+resp.getResult());//返回系统返回值 枚举类型
                // System.out.println(msgs);
                //
                // System.out.println(resp.getResult() + "/发送到网关状态报告，0成功，其它值为不成功（详见二次开发接口-2.0（JAVA）开发手册的说明）");
                if (smsSendDetailService == null) {
                    smsSendDetailService = (SmsSendDetailService) ApplicationContextHolder.getApplicationContext()
                            .getBean("smsSendDetailService");
                }
                if (resp.getResult() != 0) {
                    ReceiveStatusEnum status = ReceiveStatusEnum.GATEWAY_FAILURE; // 发送到网关失败
                    // 添加错误原因
                    String result = "result:" + resp.getResult() + ";message="
                            + (resp.getMessage() == null ? "" : resp.getMessage());
                    smsSendDetailService.updateSmsSendDetailStatusBySequences(itemSequences.toArray(new String[0]),
                            batchID.toString(), 1, status, result);
                    WarningMailUtil.instance().send("玄武短信发送错误提醒-" + batchID, result);
                }
                else {
                    smsSendDetailService.updateBatchIdBySequences(itemSequences.toArray(new String[0]),
                            batchID.toString(), username);
                }
            }
            catch (Exception e) {
                ReceiveStatusEnum status = ReceiveStatusEnum.GATEWAY_FAILURE; // 发送到网关失败
                // 添加错误原因
                String result = "result:发送异常;message=" + (e.getMessage() == null ? "" : e.getMessage());
                smsSendDetailService.updateSmsSendDetailStatusBySequences(itemSequences.toArray(new String[0]),
                        batchID.toString(), 1, status, result);
                WarningMailUtil.instance().send("玄武短信错误异常提醒-" + batchID, e.getMessage());
            }
        }
    }
}
