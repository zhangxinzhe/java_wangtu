/*
 * @(#)Compete.java    Created on 2016年4月9日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.compete;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.CompeteType;
import net.zdsoft.common.enums.YesNoType;

/**
 * T_COMPETE
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年4月9日 下午12:51:48 $
 */
public class Compete extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 活动名称
     */
    private String competeName;
    /**
     * 活动类型
     */
    private CompeteType competeType;
    /**
     * 期号
     */
    private String competeBatch;
    /**
     * 活动章程文件路径
     */
    private String ruleFile;
    /**
     * 报名表原文件名
     */
    private String formName;
    /**
     * 报名表下载路径
     */
    private String formFile;
    /**
     * 报名费
     */
    private float competeFee;
    /**
     * 开始时间
     */
    private Date beginTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 关联课程id
     */
    private long courseId;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否进行中
     */
    private YesNoType isDoing;
    /**
     * 备注
     */
    private String remark;

    /**
     * 报名开始时间
     */
    private Date attendBeginTime;

    /**
     * 报名结束时间
     */
    private Date attendEndTime;

    /**
     * 投票开始时间
     */
    private Date voteBeginTime;

    /**
     * 投票结束时间
     */
    private Date voteEndTime;

    /*************************** 辅助字段 ******************************/
    /**
     * 是否有报名记录
     */
    private boolean hasCompeteFee;

    private String courseName;// 课程名称

    /*************************** get set ******************************/

    public boolean isHasCompeteFee() {
        return hasCompeteFee;
    }

    public Date getAttendBeginTime() {
        return attendBeginTime;
    }

    public void setAttendBeginTime(Date attendBeginTime) {
        this.attendBeginTime = attendBeginTime;
    }

    public Date getAttendEndTime() {
        return attendEndTime;
    }

    public void setAttendEndTime(Date attendEndTime) {
        this.attendEndTime = attendEndTime;
    }

    public void setHasCompeteFee(boolean hasCompeteFee) {
        this.hasCompeteFee = hasCompeteFee;
    }

    public String getCompeteName() {
        return competeName;
    }

    public void setCompeteName(String competeName) {
        this.competeName = competeName;
    }

    public String getRuleFile() {
        return ruleFile;
    }

    public void setRuleFile(String ruleFile) {
        this.ruleFile = ruleFile;
    }

    public String getFormFile() {
        return formFile;
    }

    public void setFormFile(String formFile) {
        this.formFile = formFile;
    }

    public float getCompeteFee() {
        return competeFee;
    }

    public void setCompeteFee(float competeFee) {
        this.competeFee = competeFee;
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

    public YesNoType getIsDoing() {
        return isDoing;
    }

    public void setIsDoing(YesNoType isDoing) {
        this.isDoing = isDoing;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public Date getVoteBeginTime() {
        return voteBeginTime;
    }

    public void setVoteBeginTime(Date voteBeginTime) {
        this.voteBeginTime = voteBeginTime;
    }

    public Date getVoteEndTime() {
        return voteEndTime;
    }

    public void setVoteEndTime(Date voteEndTime) {
        this.voteEndTime = voteEndTime;
    }

    public CompeteType getCompeteType() {
        return competeType;
    }

    public void setCompeteType(CompeteType competeType) {
        this.competeType = competeType;
    }

    public String getCompeteBatch() {
        return competeBatch;
    }

    public void setCompeteBatch(String competeBatch) {
        this.competeBatch = competeBatch;
    }

    /**
     * @return Returns the courseId.
     */
    public long getCourseId() {
        return courseId;
    }

    /**
     * @param courseId
     *            The courseId to set.
     */
    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    /**
     * @return Returns the courseName.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName
     *            The courseName to set.
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
