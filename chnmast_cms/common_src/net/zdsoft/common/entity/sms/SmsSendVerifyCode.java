/*
 * @(#)SmsSendVerifyCode.java    Created on 2015年5月18日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.sms;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015年5月18日 上午10:08:39 $
 */
public class SmsSendVerifyCode extends BaseEntity {
    private long userId; // 发送者ID
    private String mainPhone; // 发送的手机号码
    private String verifyCode; // 验证码
    private Date sendTime; // 最后发送时间
    private Date verifyTime; // 验证码失效时间

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

}
