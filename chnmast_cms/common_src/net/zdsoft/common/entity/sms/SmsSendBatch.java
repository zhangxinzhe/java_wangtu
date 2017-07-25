/*
 * @(#)SmsSendBatch.java    Created on 2015年5月14日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.sms;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015年5月14日 下午7:49:39 $
 */
public class SmsSendBatch extends BaseEntity {
    private static final long serialVersionUID = 7628656533722712912L;
    private String batchId; // 群发短信id
    private String accountName; // 发送账号
    private Date createDate; // 发送时间
    private int checkTimes; // 检查次数
    private Date checkDate; // 下次检查时间
    private int status; // 状态0待检查，1检查完成

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getCheckTimes() {
        return checkTimes;
    }

    public void setCheckTimes(int checkTimes) {
        this.checkTimes = checkTimes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
}
