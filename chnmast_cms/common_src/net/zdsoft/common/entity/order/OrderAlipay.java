/*
 * @(#)OrderAlipay.java    Created on 2016年2月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order;

import java.util.Date;

import net.zdsoft.common.alipay.enums.AlipayFormState;
import net.zdsoft.common.alipay.enums.AlipayFormType;
import net.zdsoft.common.entity.BaseEntity;

/**
 * 支付宝在线支付
 *
 * @author xiongwq
 * @version $Revision: 1.0 $, $Date: 2016年2月22日 下午4:39:13 $
 */
public class OrderAlipay extends BaseEntity {
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
     * 支付宝ID（用于验证是否数据篡改）
     */
    private String alipayId;
    /**
     * 支付宝通知ID（用于验证是否数据篡改）
     */
    private String notifyId;
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
     * @return Returns the tradeNo.
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * @param tradeNo
     *            The tradeNo to set.
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * @return Returns the alipayId.
     */
    public String getAlipayId() {
        return alipayId;
    }

    /**
     * @param alipayId
     *            The alipayId to set.
     */
    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId;
    }

    /**
     * @return Returns the notifyId.
     */
    public String getNotifyId() {
        return notifyId;
    }

    /**
     * @param notifyId
     *            The notifyId to set.
     */
    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    /**
     * @return Returns the formState.
     */
    public AlipayFormState getFormState() {
        return formState;
    }

    /**
     * @param formState
     *            The formState to set.
     */
    public void setFormState(AlipayFormState formState) {
        this.formState = formState;
    }

    /**
     * @return Returns the formAmount.
     */
    public float getFormAmount() {
        return formAmount;
    }

    /**
     * @param formAmount
     *            The formAmount to set.
     */
    public void setFormAmount(float formAmount) {
        this.formAmount = formAmount;
    }

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
     * @return Returns the creationTime.
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime
     *            The creationTime to set.
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * @return Returns the modifyTime.
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     *            The modifyTime to set.
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * @return Returns the formType.
     */
    public AlipayFormType getFormType() {
        return formType;
    }

    /**
     * @param formType
     *            The formType to set.
     */
    public void setFormType(AlipayFormType formType) {
        this.formType = formType;
    }

    /**
     * @return Returns the isDeal.
     */
    public int getIsDeal() {
        return isDeal;
    }

    /**
     * @param isDeal
     *            The isDeal to set.
     */
    public void setIsDeal(int isDeal) {
        this.isDeal = isDeal;
    }

    /**
     * @return Returns the operatorId.
     */
    public long getOperatorId() {
        return operatorId;
    }

    /**
     * @param operatorId
     *            The operatorId to set.
     */
    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     *
     * @return Returns the operatorName
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * @param operatorName
     *            The operatorName to set.
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

}
