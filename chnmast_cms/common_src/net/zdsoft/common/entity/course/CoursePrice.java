/*
 * @(#)CoursePrice.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.course;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.CoursePriceType;
import net.zdsoft.common.enums.CourseStudyType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午9:54:31 $
 */
public class CoursePrice extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 课程或音乐会ID
     */
    private Long courseId;
    /**
     * 场次ID（=T_COURSE_TIME.ID，基地课程时为0，表示所有课次的总价格；音乐会时为场次ID）
     */
    private long courseTimeId;
    /**
     * 原价
     */
    private float oldPrice;
    /**
     * 现价
     */
    private float nowPrice;
    /**
     * 会员价
     */
    private float nowPriceVip;
    /**
     * 显示序号
     */
    private int seq;
    /**
     * 数量
     */
    private int num;
    /**
     * 以购买数
     */
    private int painNum;
    /**
     * 剩余数量
     */
    private int remainNum;
    /**
     * 上课形式（0在线直播，1现场听课/现场听音乐会，2点播）
     */
    private CourseStudyType studyType;
    /**
     * 价格类型（0正式，1预售）
     */
    private CoursePriceType priceType;

    /**
     * 价格说明（如现场/直播）
     */
    private String remark;

    /**************************** 扩展属性 *************************/
    /**
     * 是否维护了价格
     */
    private Boolean isSetPrice;

    /**
     * 实际票数（默认1）
     */
    private int acNum = 1;

    /**
     * 是否被购买
     */
    private boolean isPayed;

    /**
     * 场次开始时间
     */
    private Date beginTime;
    /**
     * 场次主题
     */
    private String title;

    /**************************** get、set方法 *************************/
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public float getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(float oldPrice) {
        this.oldPrice = oldPrice;
    }

    public float getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(float nowPrice) {
        this.nowPrice = nowPrice;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(int remainNum) {
        this.remainNum = remainNum;
    }

    public CourseStudyType getStudyType() {
        return studyType;
    }

    public void setStudyType(CourseStudyType studyType) {
        this.studyType = studyType;
    }

    public Boolean getIsSetPrice() {
        return isSetPrice;
    }

    public void setIsSetPrice(Boolean isSetPrice) {
        this.isSetPrice = isSetPrice;
    }

    public CoursePriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(CoursePriceType priceType) {
        this.priceType = priceType;
    }

    public long getCourseTimeId() {
        return courseTimeId;
    }

    public void setCourseTimeId(long courseTimeId) {
        this.courseTimeId = courseTimeId;
    }

    /**
     * @return Returns the nowPriceVip.
     */
    public float getNowPriceVip() {
        return nowPriceVip;
    }

    /**
     * @param nowPriceVip
     *            The nowPriceVip to set.
     */
    public void setNowPriceVip(float nowPriceVip) {
        this.nowPriceVip = nowPriceVip;
    }

    /**
     * @return Returns the painNum.
     */
    public int getPainNum() {
        return painNum;
    }

    /**
     * @param painNum
     *            The painNum to set.
     */
    public void setPainNum(int painNum) {
        this.painNum = painNum;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean isPayed) {
        this.isPayed = isPayed;
    }

    public int getAcNum() {
        return acNum;
    }

    public void setAcNum(int acNum) {
        this.acNum = acNum;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
