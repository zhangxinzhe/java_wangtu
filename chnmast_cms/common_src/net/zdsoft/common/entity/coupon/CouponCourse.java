/*
 * @(#)CouponCourse.java    Created on 2016年7月14日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.coupon;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.CourseContentType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年7月14日 下午1:54:10 $
 */
public class CouponCourse extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 优惠券id
     */
    private long couponId;

    /**
     * 课程id
     */
    private long courseId;

    /**************************** 扩展属性 *************************/
    private String courseName;// 课程名称
    private CourseContentType contentType;// 课程类型

    /**************************** get set *************************/

    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseContentType getContentType() {
        return contentType;
    }

    public void setContentType(CourseContentType contentType) {
        this.contentType = contentType;
    }
}
