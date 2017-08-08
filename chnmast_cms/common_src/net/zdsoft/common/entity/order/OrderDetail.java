/*
 * @(#)OrderDetail.java    Created on 2015年12月23日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.CourseContentType;
import net.zdsoft.common.enums.CourseStudyType;
import net.zdsoft.common.enums.ReturnStatus;
import net.zdsoft.common.enums.YesNoType;

/**
 * t_order_detail
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月23日 下午3:17:17 $
 */
public class OrderDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID=T_ORDER.ID
     */
    private long orderId;

    /**
     * 课程ID（=T_COURSE.ID）
     */
    private long wareId;

    /**
     * 场次ID（=T_COURSE_TIME.ID，基地课程时为0；音乐会时为场次ID）
     */
    private long wareTimeId;

    /**
     * 价格类型ID（=T_COURSE_PRICE.ID）
     */
    private long warePriceId;

    /**
     * 商品类型（1基地课程，2音乐会，3.活动专题，4点播视频，5HIFI会员，6乐币充值）
     */
    private CourseContentType wareType;

    /**
     * 商品名称（=COURSE_NAME）
     */
    private String wareName;
    /**
     * 购买数量
     */
    private int wareNum;
    /**
     * 应付单价
     */
    private float payablePer;
    /**
     * 实付单价
     */
    private float actualPer;
    /**
     * 应付金额（商品价格）
     */
    private float payableAmount;

    /**
     * 实付金额
     */
    private float actualAmount;

    /**
     * 是否赞过 （0未赞过， 1赞过）
     */
    private YesNoType hasGood;

    /**
     * 课程状态（=T_ORDER_RETURN.STATUS，0正常，10退课处理中，20退课成功，30退课失败，40退费处理中，50退费成功，60退费失败）
     */
    private ReturnStatus returStatus;

    /**
     * 备注
     */
    private String remark;

    /**************************** 扩展属性 *************************/
    private CourseStudyType studyType;// 上课形式（0在线直播，1现场听课/现场听音乐会，2点播）

    private String coursePriceRemark;// 课程价格备注信息

    /**************************** get、set方法 *************************/
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getWareId() {
        return wareId;
    }

    public void setWareId(long wareId) {
        this.wareId = wareId;
    }

    public long getWareTimeId() {
        return wareTimeId;
    }

    public void setWareTimeId(long wareTimeId) {
        this.wareTimeId = wareTimeId;
    }

    public CourseContentType getWareType() {
        return wareType;
    }

    public void setWareType(CourseContentType wareType) {
        this.wareType = wareType;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public float getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(float payableAmount) {
        this.payableAmount = payableAmount;
    }

    public float getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(float actualAmount) {
        this.actualAmount = actualAmount;
    }

    public YesNoType getHasGood() {
        return hasGood;
    }

    public void setHasGood(YesNoType hasGood) {
        this.hasGood = hasGood;
    }

    public ReturnStatus getReturStatus() {
        return returStatus;
    }

    public void setReturStatus(ReturnStatus returStatus) {
        this.returStatus = returStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getWarePriceId() {
        return warePriceId;
    }

    public void setWarePriceId(long warePriceId) {
        this.warePriceId = warePriceId;
    }

    public CourseStudyType getStudyType() {
        return studyType;
    }

    public void setStudyType(CourseStudyType studyType) {
        this.studyType = studyType;
    }

    public int getWareNum() {
        return wareNum;
    }

    public void setWareNum(int wareNum) {
        this.wareNum = wareNum;
    }

    public float getPayablePer() {
        return payablePer;
    }

    public void setPayablePer(float payablePer) {
        this.payablePer = payablePer;
    }

    public float getActualPer() {
        return actualPer;
    }

    public void setActualPer(float actualPer) {
        this.actualPer = actualPer;
    }

    /**
     * @return Returns the coursePriceRemark.
     */
    public String getCoursePriceRemark() {
        return coursePriceRemark;
    }

    /**
     * @param coursePriceRemark
     *            The coursePriceRemark to set.
     */
    public void setCoursePriceRemark(String coursePriceRemark) {
        this.coursePriceRemark = coursePriceRemark;
    }

}
