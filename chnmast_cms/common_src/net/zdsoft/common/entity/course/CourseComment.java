/*
 * @(#)CourseComment.java    Created on 2016年6月27日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.course;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.AuditStatusType;
import net.zdsoft.common.enums.CommentType;
import net.zdsoft.common.enums.CourseContentType;

/**
 * 课程评论表
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年6月27日 下午1:41:21 $
 */
public class CourseComment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 评论类型（1课程评论，2视频回顾评论）
     */
    private CommentType commentType;
    /**
     * 评论内容id（与评论类型对应，courseId，infoId）
     */
    private Long courseId;

    /**
     * 主讲ID
     */
    private Long teaId;

    /**
     * 评论人ID
     */
    private Long obsId;

    /**
     * 评论人姓名
     */
    private String obsName;

    /**
     * 评论人IP
     */
    private String ip;

    /**
     * 评分
     */
    private int score;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date commentTime;

    /**
     * 审核状态（2审核通过，3审核不通过）
     */
    private AuditStatusType auditStatus;

    /**
     * 审核日期
     */
    private Date auditDate;

    /**
     * 审核人姓名
     */
    private String auditRealName;

    /**************************** 辅助字段 *************************/
    private String avatarFile;// 头像
    private String speakerName;// 主讲人姓名
    private String courseName;// 课程姓名
    private CourseContentType contentType;// 内容分类（1基地课程，2音乐会，4点播视频）
    private String obsUserName;// 评论人用户名
    private String showObsName;// 前台显示名字（经过安全显示处理）

    /**************************** get、set方法 *************************/
    /**
     * @return Returns the courseId.
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * @param courseId
     *            The courseId to set.
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * @return Returns the teaId.
     */
    public Long getTeaId() {
        return teaId;
    }

    /**
     * @param teaId
     *            The teaId to set.
     */
    public void setTeaId(Long teaId) {
        this.teaId = teaId;
    }

    /**
     * @return Returns the obsId.
     */
    public Long getObsId() {
        return obsId;
    }

    /**
     * @param obsId
     *            The obsId to set.
     */
    public void setObsId(Long obsId) {
        this.obsId = obsId;
    }

    /**
     * @return Returns the obsName.
     */
    public String getObsName() {
        return obsName;
    }

    /**
     * @param obsName
     *            The obsName to set.
     */
    public void setObsName(String obsName) {
        this.obsName = obsName;
    }

    /**
     * @return Returns the ip.
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     *            The ip to set.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return Returns the content.
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            The content to set.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return Returns the commentTime.
     */
    public Date getCommentTime() {
        return commentTime;
    }

    /**
     * @param commentTime
     *            The commentTime to set.
     */
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    /**
     * @return Returns the auditStatus.
     */
    public AuditStatusType getAuditStatus() {
        return auditStatus;
    }

    /**
     * @param auditStatus
     *            The auditStatus to set.
     */
    public void setAuditStatus(AuditStatusType auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @return Returns the auditDate.
     */
    public Date getAuditDate() {
        return auditDate;
    }

    /**
     * @param auditDate
     *            The auditDate to set.
     */
    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    /**
     * @return Returns the auditRealName.
     */
    public String getAuditRealName() {
        return auditRealName;
    }

    /**
     * @param auditRealName
     *            The auditRealName to set.
     */
    public void setAuditRealName(String auditRealName) {
        this.auditRealName = auditRealName;
    }

    /**
     * @return Returns the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score
     *            The score to set.
     */
    public void setScore(int score) {
        this.score = score;
    }

    public String getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(String avatarFile) {
        this.avatarFile = avatarFile;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return Returns the showObsName.
     */
    public String getShowObsName() {
        return showObsName;
    }

    /**
     * @param showObsName
     *            The showObsName to set.
     */
    public void setShowObsName(String showObsName) {
        this.showObsName = showObsName;
    }

    /**
     * @return Returns the contentType.
     */
    public CourseContentType getContentType() {
        return contentType;
    }

    /**
     * @param contentType
     *            The contentType to set.
     */
    public void setContentType(CourseContentType contentType) {
        this.contentType = contentType;
    }

    /**
     * @return Returns the commentType.
     */
    public CommentType getCommentType() {
        return commentType;
    }

    /**
     * @param commentType
     *            The commentType to set.
     */
    public void setCommentType(CommentType commentType) {
        this.commentType = commentType;
    }

    public String getObsUserName() {
        return obsUserName;
    }

    public void setObsUserName(String obsUserName) {
        this.obsUserName = obsUserName;
    }

}
