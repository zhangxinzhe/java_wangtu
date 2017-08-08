/*
 * @(#)OrderVerifyCode.java    Created on 2016年3月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.CodeUseState;
import net.zdsoft.common.enums.CourseContentType;

/**
 * T_ORDER_VERIFYCODE
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年3月25日 下午2:01:57 $
 */
public class OrderVerifyCode extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 订单号
     */
    private long orderId;
    /**
     * 订单报表id
     */
    private long orderReportId;

    /**
     * 课程id
     */
    private long courseId;

    /**
     * 课次id
     */
    private long courseTimeId;

    /**
     * 课程分类（1基地课程，2音乐会）
     */
    private CourseContentType contentType;

    /**
     * 验证码
     */
    private long verifyCode;

    /**
     * 使用状态（0：未使用，1：已使用）
     */
    private CodeUseState useState;

    /**
     * 验证日期
     */
    private Date useDate;

    /**
     * 备注
     */
    private String remark;

    /************************** 拓展查询字段 ***************************/

    private String courseName;// 课程名称
    private Date startTime;// 课次开始时间
    private Date endTime;// 课次结束时间
    private int seq;// 课次序号
    private String theme;// 课次主题
    private String phone;// 购买人手机号
    private String realName;// 购买人姓名
    private String orderNo;// 订单号

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getOrderReportId() {
        return orderReportId;
    }

    public void setOrderReportId(long orderReportId) {
        this.orderReportId = orderReportId;
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

    public CourseContentType getContentType() {
        return contentType;
    }

    public void setContentType(CourseContentType contentType) {
        this.contentType = contentType;
    }

    public long getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(long verifyCode) {
        this.verifyCode = verifyCode;
    }

    public CodeUseState getUseState() {
        return useState;
    }

    public void setUseState(CodeUseState useState) {
        this.useState = useState;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

}
