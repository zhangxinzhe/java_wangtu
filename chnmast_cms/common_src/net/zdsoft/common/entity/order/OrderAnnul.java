/*
 * @(#)OrderAnnul.java    Created on 2016年3月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.AnnulType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年3月25日 下午1:56:48 $
 */
public class OrderAnnul extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID=T_ORDER.ID
     */
    private long orderId;
    /**
     * 优惠类型（0无，10整课购买，11后台优惠，12优惠券）
     */
    private AnnulType annulType;
    /**
     * 优惠券码
     */
    private String couponCode;

    /**
     * 优惠减免金额
     */
    private float annulAmount;
    /**
     * 操作人ID（系统优惠时添0）
     */
    private long opeUserId;
    /**
     * 操作人姓名
     */
    private String opeRealName;
    /**
     * 记录产生时间
     */
    private Date opeTime;
    /**
     * 交易详细
     */
    private String remark;

    /**************************** 扩展属性 *************************/

    /**************************** get、set方法 *************************/
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
     * @return Returns the annulType.
     */
    public AnnulType getAnnulType() {
        return annulType;
    }

    /**
     * @param annulType
     *            The annulType to set.
     */
    public void setAnnulType(AnnulType annulType) {
        this.annulType = annulType;
    }

    /**
     * @return Returns the annulAmount.
     */
    public float getAnnulAmount() {
        return annulAmount;
    }

    /**
     * @param annulAmount
     *            The annulAmount to set.
     */
    public void setAnnulAmount(float annulAmount) {
        this.annulAmount = annulAmount;
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
     * @return Returns the opeRealName.
     */
    public String getOpeRealName() {
        return opeRealName;
    }

    /**
     * @param opeRealName
     *            The opeRealName to set.
     */
    public void setOpeRealName(String opeRealName) {
        this.opeRealName = opeRealName;
    }

    /**
     * @return Returns the opeTime.
     */
    public Date getOpeTime() {
        return opeTime;
    }

    /**
     * @param opeTime
     *            The opeTime to set.
     */
    public void setOpeTime(Date opeTime) {
        this.opeTime = opeTime;
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

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

}
