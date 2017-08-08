/*
 * @(#)Coupon.java    Created on 2016年4月19日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * ${user}$
 */
package net.zdsoft.common.entity.coupon;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.CouponCreateType;
import net.zdsoft.common.enums.CouponType;
import net.zdsoft.common.enums.CouponUseType;
import net.zdsoft.common.enums.YesNoType;

/**
 * 优惠券/推广码
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年4月19日 上午10:16:29 $
 */
public class Coupon extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 推广码名称
     */
    private String name;

    /**
     * 生成类型（0单张生成；1批量生成）
     */
    private CouponCreateType createType;

    /**
     * 批量生成数量
     */
    private int createNum;

    /**
     * 批次编号
     */
    private String batchCode;

    /**
     * 优惠类型（0折扣优惠；1金额优惠）
     */
    private CouponType couponType;

    /**
     * 折扣比例
     */
    private float discount;

    /**
     * 优惠金额
     */
    private float amount;

    /**
     * 使用类型（0多次使用，1只能使用一次）
     */
    private CouponUseType couponUseType;

    /**
     * 使用开始时间（精确到分钟）
     */
    private Date beginTime;

    /**
     * 使用截至时间（精确到分钟）
     */
    private Date endTime;

    /**
     * 推广人名字
     */
    private String ownerName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

    /**************************** 扩展属性 *************************/
    /**
     * 推广码ID
     */
    private long couponCodeId;
    /**
     * 推广码
     */
    private String couponCode;
    /**
     * 状态是否注销（0否，1是）
     */
    private YesNoType isCancel;

    private String tempStr;// 临时字符串（前台显示）
    private String courseIdsStr;// 关联的课程ids（id1,id2,id3..）

    // 导出功能临时字段
    private String timeStr;
    private String courseStr;
    private String discountStr;
    private String amountStr;

    /**************************** get set *************************/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public CouponUseType getCouponUseType() {
        return couponUseType;
    }

    public void setCouponUseType(CouponUseType couponUseType) {
        this.couponUseType = couponUseType;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTempStr() {
        return tempStr;
    }

    public void setTempStr(String tempStr) {
        this.tempStr = tempStr;
    }

    public String getCourseIdsStr() {
        return courseIdsStr;
    }

    public void setCourseIdsStr(String courseIdsStr) {
        this.courseIdsStr = courseIdsStr;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getCourseStr() {
        return courseStr;
    }

    public void setCourseStr(String courseStr) {
        this.courseStr = courseStr;
    }

    public CouponCreateType getCreateType() {
        return createType;
    }

    public void setCreateType(CouponCreateType createType) {
        this.createType = createType;
    }

    public int getCreateNum() {
        return createNum;
    }

    public void setCreateNum(int createNum) {
        this.createNum = createNum;
    }

    public CouponType getCouponType() {
        return couponType;
    }

    public void setCouponType(CouponType couponType) {
        this.couponType = couponType;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * @return Returns the couponCodeId.
     */
    public long getCouponCodeId() {
        return couponCodeId;
    }

    /**
     * @param couponCodeId
     *            The couponCodeId to set.
     */
    public void setCouponCodeId(long couponCodeId) {
        this.couponCodeId = couponCodeId;
    }

    /**
     * @return Returns the discountStr.
     */
    public String getDiscountStr() {
        return discountStr;
    }

    /**
     * @param discountStr
     *            The discountStr to set.
     */
    public void setDiscountStr(String discountStr) {
        this.discountStr = discountStr;
    }

    /**
     * @return Returns the amountStr.
     */
    public String getAmountStr() {
        return amountStr;
    }

    /**
     * @param amountStr
     *            The amountStr to set.
     */
    public void setAmountStr(String amountStr) {
        this.amountStr = amountStr;
    }

    /**
     * @return Returns the isCancel.
     */
    public YesNoType getIsCancel() {
        return isCancel;
    }

    /**
     * @param isCancel The isCancel to set.
     */
    public void setIsCancel(YesNoType isCancel) {
        this.isCancel = isCancel;
    }

}
