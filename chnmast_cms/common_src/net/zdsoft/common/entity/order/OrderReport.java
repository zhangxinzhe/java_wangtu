/*
 * @(#)OrderReport.java    Created on 2015年12月22日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.CourseContentType;
import net.zdsoft.common.enums.CourseStudyType;
import net.zdsoft.common.enums.OrderReportReturnStatus;
import net.zdsoft.common.enums.OrderType;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月22日 上午10:22:36 $
 */
public class OrderReport extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private long orderId;

    /**
     * detail_id
     */
    private long detailId;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 课程id
     */
    private long courseId;

    /**
     * 场次ID（订单是基地课程时，为空；是音乐会时，为场次ID）
     */
    private long courseTimeId;
    /**
     * 价格类型ID（=T_COURSE_PRICE.ID）
     */
    private long coursePriceId;

    /**
     * 上课形式（0在线直播，1现场听课/现场听音乐会，2点播）
     */
    private CourseStudyType studyType;

    /**
     * 课程分类（1基地课程，2音乐会）
     */
    private CourseContentType courseContentType;

    /**
     * 报名时间
     */
    private Date reportTime;

    /**
     * 报名方式（0前台报名，1后台报名）
     */
    private OrderType reportType;

    /**
     * 应付金额（课程价格
     */
    private float payableAmount;

    /**
     * 实付金额
     */
    private float actualAmount;

    /**
     * 是否已退课（0未退课，1退课处理中，2已退课，根据T_ORDER_RETURN.STATUS字段值取值）
     */
    private OrderReportReturnStatus isReturnCourse;

    /**
     * 是否已退费（0未退费，1退费处理中，2已退费，根据T_ORDER_RETURN.STATUS字段值取值）
     */
    private OrderReportReturnStatus isReturnMoney;

    ////////////////////////// 赋值字段//////////////////////////////

    private String courseName;// 课程名称
    private String userName;// 用户名
    private int courseSeq;// 课次序号
    private Date courseBeginTime;// 课次开始时间
    private Date courseEndTime;// 课次结束时间

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getCourseTimeId() {
        return courseTimeId;
    }

    public void setCourseTimeId(long courseTimeId) {
        this.courseTimeId = courseTimeId;
    }

    public CourseContentType getCourseContentType() {
        return courseContentType;
    }

    public void setCourseContentType(CourseContentType courseContentType) {
        this.courseContentType = courseContentType;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public OrderType getReportType() {
        return reportType;
    }

    public void setReportType(OrderType reportType) {
        this.reportType = reportType;
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

    public OrderReportReturnStatus getIsReturnCourse() {
        return isReturnCourse;
    }

    public void setIsReturnCourse(OrderReportReturnStatus isReturnCourse) {
        this.isReturnCourse = isReturnCourse;
    }

    public OrderReportReturnStatus getIsReturnMoney() {
        return isReturnMoney;
    }

    public void setIsReturnMoney(OrderReportReturnStatus isReturnMoney) {
        this.isReturnMoney = isReturnMoney;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public CourseStudyType getStudyType() {
        return studyType;
    }

    public void setStudyType(CourseStudyType studyType) {
        this.studyType = studyType;
    }

    public long getCoursePriceId() {
        return coursePriceId;
    }

    public void setCoursePriceId(long coursePriceId) {
        this.coursePriceId = coursePriceId;
    }

    /**
     * @return Returns the courseSeq.
     */
    public int getCourseSeq() {
        return courseSeq;
    }

    /**
     * @param courseSeq
     *            The courseSeq to set.
     */
    public void setCourseSeq(int courseSeq) {
        this.courseSeq = courseSeq;
    }

    /**
     * @return Returns the courseBeginTime.
     */
    public Date getCourseBeginTime() {
        return courseBeginTime;
    }

    /**
     * @param courseBeginTime
     *            The courseBeginTime to set.
     */
    public void setCourseBeginTime(Date courseBeginTime) {
        this.courseBeginTime = courseBeginTime;
    }

    /**
     * @return Returns the courseEndTime.
     */
    public Date getCourseEndTime() {
        return courseEndTime;
    }

    /**
     * @param courseEndTime
     *            The courseEndTime to set.
     */
    public void setCourseEndTime(Date courseEndTime) {
        this.courseEndTime = courseEndTime;
    }

}
