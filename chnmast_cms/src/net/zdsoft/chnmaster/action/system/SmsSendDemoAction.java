/*
 * @(#)SmsSendDemoAction.java    Created on 2015年12月18日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.system;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.util.GUID;

import net.zdsoft.chnmaster.action.common.CmsBaseAction;
import net.zdsoft.chnmaster.service.SmsSendService;
import net.zdsoft.chnmaster.sms.SmsClientXuanWuUtil;
import net.zdsoft.common.entity.sms.SmsSend;
import net.zdsoft.common.entity.sms.SmsSendDetail;
import net.zdsoft.common.enums.ReceiveStatusEnum;
import net.zdsoft.common.enums.SendStatusEnum;
import net.zdsoft.common.enums.SmsType;
import net.zdsoft.keel.util.RandomUtils;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月18日 下午5:08:29 $
 */
@Scope("prototype")
@Controller
public class SmsSendDemoAction extends CmsBaseAction {

    private static final long serialVersionUID = 1L;
    private String phone = "18616916227";

    @Resource
    private SmsSendService smsSendService;

    public String smsSendDemo() {

        SmsSend send = new SmsSend();
        send.setSendUserid(0L);
        send.setSendRealname("系统");
        send.setContent("注册验证码短信，验证码为：" + RandomUtils.getRandomNum(6));
        send.setSmsType(SmsType.REGISTER);
        send.setSendDate(new Date());
        send.setSendStatus(SendStatusEnum.SUCCESS);

        SmsSendDetail smsSendDetail = new SmsSendDetail();
        smsSendDetail.setReceiveUserid(0l);
        smsSendDetail.setReceiveUsername("注册用户");
        smsSendDetail.setReceiveRealname("注册用户");
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
        return this.print("发送完成");
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
