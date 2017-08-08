/*
 * @(#)OrderFunds.java    Created on 2016年2月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.ChargeType;
import net.zdsoft.common.enums.CourseContentType;
import net.zdsoft.common.enums.RecordDetailType;

/**
 * 总收入金额变动流水记录
 *
 * @author xiongwq
 * @version $Revision: 1.0 $, $Date: 2016年2月22日 下午5:30:16 $
 */
public class OrderFunds extends BaseEntity {
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
     * 商品类型（1基地课程，2音乐会，3.活动专题，4视频点播，5HIFI会员，6IOS乐币）
     */
    private CourseContentType wareType;
    /**
     * 记录产生时间
     */
    private Date recordDate;
    /**
     * 操作人ID
     */
    private long opeUserId;
    /**
     * 操作人姓名
     */
    private String opeRealName;
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
     * 交易详细
     */
    private String remark;

    /**************************** 扩展属性 *************************/
    private String userName;// 用户名
    private String realName;// 姓名
    private String email;// 邮箱

    private long orderId;// 订单id
    private String tradeNo;// 订单号
    private String wareName;// 商品名称
    private float payAmount;// 实付金额
    private Date orderCreateTime;// 下单时间

    /**************************** get、set方法 *************************/

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public long getOpeUserId() {
        return opeUserId;
    }

    public void setOpeUserId(long opeUserId) {
        this.opeUserId = opeUserId;
    }

    public String getOpeRealName() {
        return opeRealName;
    }

    public void setOpeRealName(String opeRealName) {
        this.opeRealName = opeRealName;
    }

    public ChargeType getChangeType() {
        return changeType;
    }

    public void setChangeType(ChargeType changeType) {
        this.changeType = changeType;
    }

    public float getChangeFunds() {
        return changeFunds;
    }

    public void setChangeFunds(float changeFunds) {
        this.changeFunds = changeFunds;
    }

    public float getRemainFunds() {
        return remainFunds;
    }

    public void setRemainFunds(float remainFunds) {
        this.remainFunds = remainFunds;
    }

    public RecordDetailType getDetailType() {
        return detailType;
    }

    public void setDetailType(RecordDetailType detailType) {
        this.detailType = detailType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public CourseContentType getWareType() {
        return wareType;
    }

    public void setWareType(CourseContentType wareType) {
        this.wareType = wareType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public float getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(float payAmount) {
        this.payAmount = payAmount;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

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

}
