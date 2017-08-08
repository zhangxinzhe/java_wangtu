/*
 * @(#)OrderReturn.java    Created on 2016年2月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.OrderReturnReasonType;
import net.zdsoft.common.enums.OrderReturnStatusType;
import net.zdsoft.common.enums.ReturnStatus;

/**
 * 退课退费表
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年2月22日 下午2:17:38 $
 */
public class OrderReturn extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID=T_ORDER.ID
     */
    private long orderId;

    /**
     * 订单ID=T_ORDER_DETAIL.ID
     */
    private long detailId;
    /**
     * 退课申请人ID
     */
    private long userId;
    /**
     * 退课申请人姓名
     */
    private String realName;
    /**
     * 退课发起时间
     */
    private Date createTime;
    /**
     * 退课原因编码（0其他原因，1报错课，2电脑、网络问题上不了课，3课程不满意）
     */
    private OrderReturnReasonType reason;
    /**
     * 退课其他原因（REASON中选0时，此字段输入其他原因）
     */
    private String reasonDes;
    /**
     * 退课状态（10退课处理中，20退课成功，30退课失败，40退费处理中，50退费成功，60退费失败）
     */
    private ReturnStatus status;
    /**
     * 状态类型（0无，21退课成功-无需审核，22退课成功-管理员审核，51退费成功-系统处理，52退费成功-人工打款）
     */
    private OrderReturnStatusType statusType;
    /**
     * 退课处理人ID
     */
    private long courseUserId;
    /**
     * 退课处理人姓名
     */
    private String courseRealName;
    /**
     * 退课处理时间
     */
    private Date courseTime;
    /**
     * 退课处理备注
     */
    private String courseRemark;
    /**
     * 退款金额（=T_ORDER_DETAIL.ACTUAL_AMOUNT）
     */
    private float moneyAmount;
    /**
     * 退款处理人ID
     */
    private long moneyUserId;
    /**
     * 退款处理人姓名
     */
    private String moneyRealName;
    /**
     * 退款处理时间
     */
    private Date moneyTime;
    /**
     * 退款处理备注
     */
    private String moneyRemark;

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
     * @return Returns the detailId.
     */
    public long getDetailId() {
        return detailId;
    }

    /**
     * @param detailId
     *            The detailId to set.
     */
    public void setDetailId(long detailId) {
        this.detailId = detailId;
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
     * @return Returns the reason.
     */
    public OrderReturnReasonType getReason() {
        return reason;
    }

    /**
     * @param reason
     *            The reason to set.
     */
    public void setReason(OrderReturnReasonType reason) {
        this.reason = reason;
    }

    /**
     * @return Returns the reasonDes.
     */
    public String getReasonDes() {
        return reasonDes;
    }

    /**
     * @param reasonDes
     *            The reasonDes to set.
     */
    public void setReasonDes(String reasonDes) {
        this.reasonDes = reasonDes;
    }

    /**
     * @return Returns the status.
     */
    public ReturnStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(ReturnStatus status) {
        this.status = status;
    }

    /**
     * @return Returns the statusType.
     */
    public OrderReturnStatusType getStatusType() {
        return statusType;
    }

    /**
     * @param statusType
     *            The statusType to set.
     */
    public void setStatusType(OrderReturnStatusType statusType) {
        this.statusType = statusType;
    }

    /**
     * @return Returns the courseUserId.
     */
    public long getCourseUserId() {
        return courseUserId;
    }

    /**
     * @param courseUserId
     *            The courseUserId to set.
     */
    public void setCourseUserId(long courseUserId) {
        this.courseUserId = courseUserId;
    }

    /**
     * @return Returns the courseRealName.
     */
    public String getCourseRealName() {
        return courseRealName;
    }

    /**
     * @param courseRealName
     *            The courseRealName to set.
     */
    public void setCourseRealName(String courseRealName) {
        this.courseRealName = courseRealName;
    }

    /**
     * @return Returns the courseTime.
     */
    public Date getCourseTime() {
        return courseTime;
    }

    /**
     * @param courseTime
     *            The courseTime to set.
     */
    public void setCourseTime(Date courseTime) {
        this.courseTime = courseTime;
    }

    /**
     * @return Returns the courseRemark.
     */
    public String getCourseRemark() {
        return courseRemark;
    }

    /**
     * @param courseRemark
     *            The courseRemark to set.
     */
    public void setCourseRemark(String courseRemark) {
        this.courseRemark = courseRemark;
    }

    /**
     * @return Returns the moneyAmount.
     */
    public float getMoneyAmount() {
        return moneyAmount;
    }

    /**
     * @param moneyAmount
     *            The moneyAmount to set.
     */
    public void setMoneyAmount(float moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    /**
     * @return Returns the moneyUserId.
     */
    public long getMoneyUserId() {
        return moneyUserId;
    }

    /**
     * @param moneyUserId
     *            The moneyUserId to set.
     */
    public void setMoneyUserId(long moneyUserId) {
        this.moneyUserId = moneyUserId;
    }

    /**
     * @return Returns the moneyRealName.
     */
    public String getMoneyRealName() {
        return moneyRealName;
    }

    /**
     * @param moneyRealName
     *            The moneyRealName to set.
     */
    public void setMoneyRealName(String moneyRealName) {
        this.moneyRealName = moneyRealName;
    }

    /**
     * @return Returns the moneyTime.
     */
    public Date getMoneyTime() {
        return moneyTime;
    }

    /**
     * @param moneyTime
     *            The moneyTime to set.
     */
    public void setMoneyTime(Date moneyTime) {
        this.moneyTime = moneyTime;
    }

    /**
     * @return Returns the moneyRemark.
     */
    public String getMoneyRemark() {
        return moneyRemark;
    }

    /**
     * @param moneyRemark
     *            The moneyRemark to set.
     */
    public void setMoneyRemark(String moneyRemark) {
        this.moneyRemark = moneyRemark;
    }

}
