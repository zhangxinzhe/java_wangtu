/*
 * @(#)OrderWechatpay.java    Created on 2016年3月28日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order;

import java.util.Date;

import net.zdsoft.common.alipay.enums.AlipayFormState;
import net.zdsoft.common.alipay.enums.AlipayFormType;
import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.wechatpay.enums.WechatpayTradeType;

public class OrderWechatpay extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID=T_ORDER.ID
     */
    private long orderId;
    /**
     * 交易号（32位uuid）
     */
    private String tradeNo;
    /**
     * 微信用户序列号
     */
    private String openId;
    /**
     * 微信交易号
     */
    private String transactionId;
    /**
     * 订单序列号ID（2小时有效）
     */
    private String prepayId;
    /**
     * 支付方式
     */
    private WechatpayTradeType tradeType;
    /**
     * 二维码支付链接
     */
    private String codeUrl;
    /**
     * 订单状态（0.待处理，1失败，2成功）
     */
    private AlipayFormState formState;
    /**
     * 订单金额
     */
    private float formAmount;
    /**
     * 用户id
     */
    private long userId;
    /**
     * 交易创建时间
     */
    private Date creationTime;
    /**
     * 交易修改时间
     */
    private Date modifyTime;
    /**
     * 订单类型（0充值，1购物）
     */
    private AlipayFormType formType;
    /**
     * 支付宝回调状态，避免回调并发（1正在处理，0未在处理）
     */
    private int isDeal;
    /**
     * 操作人id
     */
    private long operatorId;
    /**
     * 操作人姓名
     */
    private String operatorName;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public AlipayFormState getFormState() {
        return formState;
    }

    public void setFormState(AlipayFormState formState) {
        this.formState = formState;
    }

    public float getFormAmount() {
        return formAmount;
    }

    public void setFormAmount(float formAmount) {
        this.formAmount = formAmount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public AlipayFormType getFormType() {
        return formType;
    }

    public void setFormType(AlipayFormType formType) {
        this.formType = formType;
    }

    public int getIsDeal() {
        return isDeal;
    }

    public void setIsDeal(int isDeal) {
        this.isDeal = isDeal;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public WechatpayTradeType getTradeType() {
        return tradeType;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public void setTradeType(WechatpayTradeType tradeType) {
        this.tradeType = tradeType;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    /**
     * @return Returns the openId.
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId
     *            The openId to set.
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * @return Returns the transactionId.
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId
     *            The transactionId to set.
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
