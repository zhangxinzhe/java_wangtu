/*
 * @(#)OrderCashpay.java    Created on 2016年2月29日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;

/**
 * @author wangly
 * @version $Revision: 1.0 $, $Date: 2016年2月29日 下午5:42:38 $
 */
public class OrderCashpay extends BaseEntity {

    /**
     * 订单ID=T_ORDER.ID
     */
    private long orderId;
    /**
     * 操作人ID
     */
    private long opeUserId;
    /**
     * 操作人姓名
     */
    private String opeRealname;
    /**
     * 操作时间
     */
    private Date createTime;
    /**
     * 收款人姓名
     */
    private String cashRealname;
    /**
     * 收款时间
     */
    private Date cashTime;
    /**
     * 收款金额（=T_ORDER.PAY_AMOUNT）
     */
    private float cashAmount;
    /**
     * 收款方式(现金/银行转账等，文字描述)
     */
    private String chargeType;
    /**
     * 备注
     */
    private String remark;

    /**
     * @return Returns the orderId.
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     *            The orderId to set.
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return Returns the opeUserId.
     */
    public long getOpeUserId() {
        return opeUserId;
    }

    /**
     * @param opeUserId
     *            The opeUserId to set.
     */
    public void setOpeUserId(long opeUserId) {
        this.opeUserId = opeUserId;
    }

    /**
     * @return Returns the opeRealname.
     */
    public String getOpeRealname() {
        return opeRealname;
    }

    /**
     * @param opeRealname
     *            The opeRealname to set.
     */
    public void setOpeRealname(String opeRealname) {
        this.opeRealname = opeRealname;
    }

    /**
     * @return Returns the createTime.
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            The createTime to set.
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return Returns the cashRealname.
     */
    public String getCashRealname() {
        return cashRealname;
    }

    /**
     * @param cashRealname
     *            The cashRealname to set.
     */
    public void setCashRealname(String cashRealname) {
        this.cashRealname = cashRealname;
    }

    /**
     * @return Returns the cashTime.
     */
    public Date getCashTime() {
        return cashTime;
    }

    /**
     * @param cashTime
     *            The cashTime to set.
     */
    public void setCashTime(Date cashTime) {
        this.cashTime = cashTime;
    }

    /**
     * @return Returns the cashAmount.
     */
    public float getCashAmount() {
        return cashAmount;
    }

    /**
     * @param cashAmount
     *            The cashAmount to set.
     */
    public void setCashAmount(float cashAmount) {
        this.cashAmount = cashAmount;
    }

    /**
     * @return Returns the chargeType.
     */
    public String getChargeType() {
        return chargeType;
    }

    /**
     * @param chargeType
     *            The chargeType to set.
     */
    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
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
}
