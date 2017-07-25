/*
 * @(#)CheckSmsSendStatusServiceImpl.java    Created on 2015年5月14日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.sms.impl;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.MTReport;

import net.zdsoft.chnmaster.dao.SmsSendBatchDao;
import net.zdsoft.chnmaster.dao.SmsSendDetailDao;
import net.zdsoft.chnmaster.service.SmsSendBatchService;
import net.zdsoft.chnmaster.service.SmsSendDetailService;
import net.zdsoft.chnmaster.service.sms.CheckSmsSendStatusService;
import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.entity.sms.SmsSendBatch;
import net.zdsoft.common.entity.sms.SmsSendDetail;
import net.zdsoft.common.enums.ReceiveStatusEnum;
import net.zdsoft.common.utils.Util;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015年5月14日 下午3:29:43 $
 */
@Service("checkSmsSendStatusService")
public class CheckSmsSendStatusServiceImpl implements CheckSmsSendStatusService {
    protected Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private SmsSendDetailDao smsSendDetailDao;
    @Resource
    private SmsSendDetailService smsSendDetailService;
    @Resource
    private SmsSendBatchDao smsSendBatchDao;
    @Resource
    private SmsSendBatchService smsSendBatchService;

    @Override
    public void checkStatus(int from) {
        if (!Util.validateTask(from)) {
            return;
        }
        // 检查短信检查是否开启
        if (!"1".equals(NetstudyConfig.getParam(BaseConstants.PHONE_XUANWU_SEND_REPORT))) {
            return;
        }
        log.debug("短信检查开始");
        String publicUsername = NetstudyConfig.getParam(BaseConstants.PHONE_PUBLIC_CONTENT_USERNAME);
        String publicPassword = NetstudyConfig.getParam(BaseConstants.PHONE_PUBLIC_CONTENT_PASSWORD);

        String definedUsername = NetstudyConfig.getParam(BaseConstants.PHONE_DEFINED_CONTENT_USERNAME);
        String definedPassword = NetstudyConfig.getParam(BaseConstants.PHONE_DEFINED_CONTENT_PASSWORD);
        String accountNames[] = { publicUsername, definedUsername };
        List<SmsSendBatch> batchs = smsSendBatchDao.getBatchs(accountNames,
                Integer.parseInt(NetstudyConfig.getParam(BaseConstants.PHONE_XUANWU_CHECK_TIMES)), 100);
        for (SmsSendBatch smsSendBatch : batchs) {
            String username = smsSendBatch.getAccountName();
            String password = null;
            if (username.equals(publicUsername)) {
                password = publicPassword;
            }
            else {
                password = definedPassword;
            }
            getReportByBatchId(smsSendBatch, username, password);
        }
        // 检查是否还有需要检查的信息
        if (batchs.size() == 100) {
            checkStatus(from);
        }
        log.debug("短信检查结束");
    }

    /**
     * 获取未读取的状态报告 适用于整个账号查询
     *
     * @param contentType
     *            0表示自定义短信 1表示固定格式短信
     * @return
     */
    @Deprecated
    public void getReport(int contentType) {
        // 验证是否需要获取短信发送报告
        if (!"1".equals(NetstudyConfig.getParam(BaseConstants.PHONE_XUANWU_SEND_REPORT))) {
            return;
        }

        String username = null;
        String password = null;
        if (contentType == 1) {
            username = NetstudyConfig.getParam(BaseConstants.PHONE_PUBLIC_CONTENT_USERNAME);
            password = NetstudyConfig.getParam(BaseConstants.PHONE_PUBLIC_CONTENT_PASSWORD);
        }
        else {
            username = NetstudyConfig.getParam(BaseConstants.PHONE_DEFINED_CONTENT_USERNAME);
            password = NetstudyConfig.getParam(BaseConstants.PHONE_DEFINED_CONTENT_PASSWORD);
        }
        Account ac = new Account(username, password);// 设置帐号密码
        PostMsg pm = new PostMsg(); // 新建一个PostMsg对象
        // pm.getCmHost().setHost("211.147.239.62",9080);//您设置的下行端口MOS
        // pm.getWsHost().setHost("211.147.239.62",9070);
        pm.getCmHost().setHost(NetstudyConfig.getParam(BaseConstants.PHONE_CM_HOST),
                Integer.parseInt(NetstudyConfig.getParam(BaseConstants.PHONE_CM_PORT)));// 您设置的下行端口
        // 400
        pm.getWsHost().setHost(NetstudyConfig.getParam(BaseConstants.PHONE_WS_HOST),
                Integer.parseInt(NetstudyConfig.getParam(BaseConstants.PHONE_WS_PORT)));

        try {
            MTReport[] mp = pm.getReports(ac, 100);
            // System.out.println("获取短信结果条数：" + (mp == null ? 0 : mp.length));
            if (ArrayUtils.isNotEmpty(mp)) {
                for (int i = 0; i < mp.length; i++) {
                    ReceiveStatusEnum status = null;
                    String otherReason = null;
                    if (mp[i].getState() == 0) {
                        status = ReceiveStatusEnum.SUCCESS;
                    }
                    else {
                        status = ReceiveStatusEnum.FAILURE;
                        if (mp[i].getState() == 1) {
                            otherReason = "发送失败，原因：已过期";
                        }
                        else if (mp[i].getState() == 2) {
                            otherReason = "发送失败，原因：无法发送";
                        }
                        else if (mp[i].getState() == 3) {
                            otherReason = "发送失败，原因：被拒绝";
                        }
                        else if (mp[i].getState() == 4) {
                            otherReason = "发送失败，原因：未知";
                        }
                        else if (mp[i].getState() == 5) {
                            otherReason = "发送失败，原因：已删除";
                        }
                    }
                    smsSendDetailService.updateSmsSendDetailStatusBySequence(mp[i].getCustomMsgID(), 1, status,
                            otherReason);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据batchId获取状态报告
     *
     * @param batchId
     * @return
     */
    public String getReportByBatchId(SmsSendBatch batch, String username, String password) {
        UUID batchID = UUID.fromString(batch.getBatchId());
        Account ac = new Account(username, password);// 设置帐号密码
        PostMsg pm = new PostMsg(); // 新建一个PostMsg对象
        // pm.getCmHost().setHost("211.147.239.62",9080);//您设置的下行端口MOS
        // pm.getWsHost().setHost("211.147.239.62",9070);
        pm.getCmHost().setHost(NetstudyConfig.getParam(BaseConstants.PHONE_CM_HOST),
                Integer.parseInt(NetstudyConfig.getParam(BaseConstants.PHONE_CM_PORT)));// 您设置的下行端口
        // 400
        pm.getWsHost().setHost(NetstudyConfig.getParam(BaseConstants.PHONE_WS_HOST),
                Integer.parseInt(NetstudyConfig.getParam(BaseConstants.PHONE_WS_PORT)));

        String result = "";
        try {
            MTReport[] foundMtReports = pm.findReports(ac, 1, batchID, null, 0); // 查询到运营商的状态报告
            boolean needCheck = true;
            if (ArrayUtils.isNotEmpty(foundMtReports)) {
                needCheck = false;
                for (MTReport report : foundMtReports) {
                    String sequence = report.getCustomMsgID();
                    int state = report.getState();
                    log.debug("短信检查：sequence-{};state-{}", sequence, state);
                    if (StringUtils.isBlank(sequence)) {
                        continue;
                    }
                    int index = sequence.indexOf("#");
                    if (index > 0) {
                        sequence = sequence.substring(0, index);
                    }
                    ReceiveStatusEnum status = null;
                    String otherReason = "";
                    if (state == 0) {
                        status = ReceiveStatusEnum.SUCCESS;
                    }
                    else {
                        needCheck = true;
                        status = ReceiveStatusEnum.FAILURE;
                        if (state == 1) {
                            otherReason = "发送失败，原因：已过期";
                        }
                        else if (state == 2) {
                            otherReason = "发送失败，原因：无法发送";
                        }
                        else if (state == 3) {
                            otherReason = "发送失败，原因：被拒绝";
                        }
                        else if (state == 4) {
                            otherReason = "发送失败，原因：未知";
                        }
                        else if (state == 5) {
                            otherReason = "发送失败，原因：已删除";
                        }
                        otherReason = "state=" + state + "(" + otherReason + ")#" + report.getOriginResult() == null
                                ? "" : report.getOriginResult();
                    }
                    smsSendDetailService.updateSmsSendDetailStatusBySequence(sequence, 1, status, otherReason);
                }

            }
            // 设置下次检查状态
            if (needCheck) {
                Calendar checkDate = Calendar.getInstance();
                checkDate.setTime(batch.getCheckDate());
                checkDate.add(Calendar.MINUTE, (batch.getCheckTimes() + 1) * 10);
                smsSendBatchService.updateCheckDate(batch.getId(), checkDate.getTime());
            }
            else {
                smsSendBatchService.updateStatus(batch.getId(), 1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }
        return result;
    }

    @Override
    public String checkMsgStatus(long detailId) {
        SmsSendDetail detail = smsSendDetailDao.getDetail(detailId);
        // 信息不存在
        if (detail == null) {
            return null;
        }
        // 检查是否是新版数据
        if (StringUtils.isBlank(detail.getBatchId()) || StringUtils.isBlank(detail.getSequence())) {
            return "OLD_SMS";
        }
        // 检查发送网关是否成功
        if (detail.getReceiveStatus() == ReceiveStatusEnum.GATEWAY_FAILURE) {
            return "GATEWAY_FAILURE";
        }
        // 检查发送是否成功
        if (detail.getReceiveStatus() == ReceiveStatusEnum.SUCCESS) {
            return "SUCCESS";
        }
        // 获取发送的账号信息
        String accountName = smsSendBatchDao.getSendAccount(detail.getBatchId());
        if (StringUtils.isBlank(accountName)) {
            return "OLD_ACCOUNT";
        }
        // 检查账号信息
        String accountPwd = null;
        if (accountName.equals(NetstudyConfig.getParam(BaseConstants.PHONE_PUBLIC_CONTENT_USERNAME))) {
            accountPwd = NetstudyConfig.getParam(BaseConstants.PHONE_PUBLIC_CONTENT_PASSWORD);
        }
        else if (accountName.equals(NetstudyConfig.getParam(BaseConstants.PHONE_DEFINED_CONTENT_USERNAME))) {
            accountPwd = NetstudyConfig.getParam(BaseConstants.PHONE_DEFINED_CONTENT_PASSWORD);
        }
        else {
            return "OLD_ACCOUNT";
        }
        // 查询到运营商的状态报告
        UUID batchID = UUID.fromString(detail.getBatchId());
        // 设置帐号密码
        Account ac = new Account(accountName, accountPwd);
        // 新建一个PostMsg对象
        PostMsg pm = new PostMsg();
        try {
            MTReport[] foundMtReports = pm.findReports(ac, 1, batchID, detail.getMobile(), 0);
            if (ArrayUtils.isNotEmpty(foundMtReports)) {
                for (MTReport report : foundMtReports) {
                    String sequence = report.getCustomMsgID();
                    int state = report.getState();
                    if (StringUtils.isBlank(sequence)) {
                        continue;
                    }
                    int index = sequence.indexOf("#");
                    if (index > 0) {
                        sequence = sequence.substring(0, index);
                    }
                    // 判断是否是当前需要获取的短信信息
                    if (!detail.getSequence().equals(sequence)) {
                        continue;
                    }
                    ReceiveStatusEnum status = null;
                    String otherReason = null;
                    if (state == 0) {
                        status = ReceiveStatusEnum.SUCCESS;
                    }
                    else {
                        status = ReceiveStatusEnum.FAILURE;
                        if (state == 1) {
                            otherReason = "发送失败，原因：已过期";
                        }
                        else if (state == 2) {
                            otherReason = "发送失败，原因：无法发送";
                        }
                        else if (state == 3) {
                            otherReason = "发送失败，原因：被拒绝";
                        }
                        else if (state == 4) {
                            otherReason = "发送失败，原因：未知";
                        }
                        else if (state == 5) {
                            otherReason = "发送失败，原因：已删除";
                        }
                        otherReason += "#" + report.getOriginResult();
                    }
                    smsSendDetailService.updateSmsSendDetailStatusBySequence(sequence, 1, status, otherReason);
                    return otherReason;
                }
            }
            return "CHECK_NOT_FIND";
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            return "CHECK_ERROR";
        }
    }

    public static void main(String[] args) {
        String batchIdStr = "a87beed6-f6ad-43e7-9bc6-7e73d078d891";
        UUID batchID = UUID.fromString(batchIdStr);
        Account ac = new Account("cadmin3@wpwl", "netstudy");// 设置帐号密码
        PostMsg pm = new PostMsg(); // 新建一个PostMsg对象
        // pm.getCmHost().setHost("211.147.239.62",9080);//您设置的下行端口MOS
        // pm.getWsHost().setHost("211.147.239.62",9070);
        pm.getCmHost().setHost(NetstudyConfig.getParam(BaseConstants.PHONE_CM_HOST),
                Integer.parseInt(NetstudyConfig.getParam(BaseConstants.PHONE_CM_PORT)));// 您设置的下行端口
        // 400
        pm.getWsHost().setHost(NetstudyConfig.getParam(BaseConstants.PHONE_WS_HOST),
                Integer.parseInt(NetstudyConfig.getParam(BaseConstants.PHONE_WS_PORT)));

        String result = "";
        try {
            MTReport[] foundMtReports = pm.findReports(ac, 1, batchID, "13009774002", 0); // 查询到运营商的状态报告
            if (ArrayUtils.isNotEmpty(foundMtReports)) {
                for (MTReport report : foundMtReports) {
                    String sequence = report.getCustomMsgID();
                    int state = report.getState();
                    if (StringUtils.isBlank(sequence)) {
                        continue;
                    }
                    int index = sequence.indexOf("#");
                    if (index > 0) {
                        sequence = sequence.substring(0, index);
                    }
                    ReceiveStatusEnum status = null;
                    String otherReason = null;
                    if (state == 0) {
                        status = ReceiveStatusEnum.SUCCESS;
                    }
                    else {
                        status = ReceiveStatusEnum.FAILURE;
                        if (state == 1) {
                            otherReason = "发送失败，原因：已过期";
                        }
                        else if (state == 2) {
                            otherReason = "发送失败，原因：无法发送";
                        }
                        else if (state == 3) {
                            otherReason = "发送失败，原因：被拒绝";
                        }
                        else if (state == 4) {
                            otherReason = "发送失败，原因：未知";
                        }
                        else if (state == 5) {
                            otherReason = "发送失败，原因：已删除";
                        }
                        otherReason += "#" + report.getOriginResult();
                    }

                    System.out.println(status + " " + otherReason);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }
}
