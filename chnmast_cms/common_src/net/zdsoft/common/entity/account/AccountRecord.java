/*
 * @(#)AccountRecord.java    Created on 2016年12月21日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.account;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.ChargeType;
import net.zdsoft.common.enums.RecordDetailType;
import net.zdsoft.common.enums.RecordType;

/**
 * 个人账户余额流水记录表
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年12月21日 上午11:24:40 $
 */
public class AccountRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private long userId;
    /**
     * 关联记录ID（T_ORDER.id，T_ORDER_RETURN.id）
     */
    private String relationId;
    /**
     * 记录产生时间
     */
    private Date recordDate;
    /**
     * 审核人ID
     */
    private long auditUserId;
    /**
     * 审核人姓名
     */
    private String auditRealName;
    /**
     * 操作类型（参看CHARGETYPE.JAVA枚举类）
     */
    private ChargeType changeType;
    /**
     * 变动金额
     */
    private float changeFunds;
    /**
     * 变动后总金额
     */
    private float remainFunds;
    /**
     * 交易类型（0增加、入账，1减少、出账）
     */
    private RecordDetailType detailType;
    /**
     * 交易记录类型
     */
    private RecordType recordType;
    /**
     * 交易详细
     */
    private String remark;

    /**************************** 扩展属性 *************************/
    private String userName;// 用户名
    private String realName;// 姓名
    private String email;// 邮箱
    private String phone;// 电话

    private String tradeNo;// 订单号

    /**
     * @return Returns the userId.
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            The userId to set.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @return Returns the relationId.
     */
    public String getRelationId() {
        return relationId;
    }

    /**
     * @param relationId
     *            The relationId to set.
     */
    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    /**
     * @return Returns the recordDate.
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * @param recordDate
     *            The recordDate to set.
     */
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    /**
     * @return Returns the auditUserId.
     */
    public long getAuditUserId() {
        return auditUserId;
    }

    /**
     * @param auditUserId
     *            The auditUserId to set.
     */
    public void setAuditUserId(long auditUserId) {
        this.auditUserId = auditUserId;
    }

    /**
     * @return Returns the auditRealName.
     */
    public String getAuditRealName() {
        return auditRealName;
    }

    /**
     * @param auditRealName
     *            The auditRealName to set.
     */
    public void setAuditRealName(String auditRealName) {
        this.auditRealName = auditRealName;
    }

    /**
     * @return Returns the changeType.
     */
    public ChargeType getChangeType() {
        return changeType;
    }

    /**
     * @param changeType
     *            The changeType to set.
     */
    public void setChangeType(ChargeType changeType) {
        this.changeType = changeType;
    }

    /**
     * @return Returns the changeFunds.
     */
    public float getChangeFunds() {
        return changeFunds;
    }

    /**
     * @param changeFunds
     *            The changeFunds to set.
     */
    public void setChangeFunds(float changeFunds) {
        this.changeFunds = changeFunds;
    }

    /**
     * @return Returns the remainFunds.
     */
    public float getRemainFunds() {
        return remainFunds;
    }

    /**
     * @param remainFunds
     *            The remainFunds to set.
     */
    public void setRemainFunds(float remainFunds) {
        this.remainFunds = remainFunds;
    }

    /**
     * @return Returns the detailType.
     */
    public RecordDetailType getDetailType() {
        return detailType;
    }

    /**
     * @param detailType
     *            The detailType to set.
     */
    public void setDetailType(RecordDetailType detailType) {
        this.detailType = detailType;
    }

    /**
     * @return Returns the remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            The remark to set.
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return Returns the recordType.
     */
    public RecordType getRecordType() {
        return recordType;
    }

    /**
     * @param recordType
     *            The recordType to set.
     */
    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    /**
     * @return Returns the userName.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            The userName to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return Returns the realName.
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     *            The realName to set.
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Returns the phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            The phone to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return Returns the tradeNo.
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * @param tradeNo The tradeNo to set.
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

}
