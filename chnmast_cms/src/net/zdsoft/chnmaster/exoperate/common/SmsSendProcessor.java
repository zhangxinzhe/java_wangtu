/*
 * @(#)SmsSendProcessor.java    Created on 2015年12月25日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.exoperate.common;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.opensymphony.util.GUID;

import net.zdsoft.chnmaster.service.SmsSendService;
import net.zdsoft.chnmaster.sms.SmsClientXuanWuUtil;
import net.zdsoft.common.entity.sms.SmsSend;
import net.zdsoft.common.entity.sms.SmsSendDetail;
import net.zdsoft.common.enums.ReceiveStatusEnum;
import net.zdsoft.common.enums.SendStatusEnum;
import net.zdsoft.common.enums.SmsType;
import net.zdsoft.common.exoperate.ExoperateProcessor;

/**
 * 短信发送相关
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月25日 上午9:49:08 $
 */
@Component("smsSendProcessor")
public class SmsSendProcessor implements ExoperateProcessor {

    @Resource
    private SmsSendService smsSendService;

    @Override
    public void process(String operateType, Map values) {
        String content = (String) values.get("content");// 内容
        String phone = (String) values.get("phone");// 手机号
        int smsType = (int) values.get("smsType");// 短信类型
        long receiveId = (long) values.get("receiveId");// 接收人id
        String userName = (String) values.get("userName");// 接收人账号
        String realName = (String) values.get("realName");// 接收人姓名

        SmsSend send = new SmsSend();
        send.setSendUserid(0L);
        send.setSendRealname("系统");
        send.setContent(content);
        send.setSmsType(SmsType.get(smsType));
        send.setSendDate(new Date());
        send.setSendStatus(SendStatusEnum.SUCCESS);

        SmsSendDetail smsSendDetail = new SmsSendDetail();
        smsSendDetail.setReceiveUserid(receiveId);
        smsSendDetail.setReceiveUsername(userName);
        smsSendDetail.setReceiveRealname(realName);
        smsSendDetail.setMobile(phone);
        if (StringUtils.isBlank(smsSendDetail.getMobile())) {
            smsSendDetail.setReceiveStatus(ReceiveStatusEnum.FAILURE);
            smsSendDetail.setStatusDesc(ReceiveStatusEnum.FAILURE.getNameValue());
        }
        else {
            smsSendDetail.setReceiveStatus(ReceiveStatusEnum.GATEWAY);
            smsSendDetail.setStatusDesc(ReceiveStatusEnum.GATEWAY.getNameValue());
            smsSendDetail.setSequence(GUID.generateGUID());
        }

        smsSendService.addSmsSend(send, smsSendDetail);
        SmsClientXuanWuUtil.getInstance().post(new String[] { smsSendDetail.getSequence() }, new String[] { phone },
                new String[] { send.getContent() }, 1);

    }

}
