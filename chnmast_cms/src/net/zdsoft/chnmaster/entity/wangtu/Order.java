/*
 * @(#)Order.java    Created on 2017年7月25日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu;

import java.util.Date;

import net.zdsoft.chnmaster.enums.wangtu.OrderType;
import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.OrderStatus;
import net.zdsoft.common.enums.PayType;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月25日 上午11:08:49 $
 */
public class Order extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private String tradeNo;
    private long relationId;
    private long userId;
    private double payAmount;
    private Date creationTime;
    private Date paymentTime;
    private OrderStatus status;
    private PayType payType;
    private OrderType orderType;
    private String remark;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public long getRelationId() {
        return relationId;
    }

    public void setRelationId(long relationId) {
        this.relationId = relationId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
