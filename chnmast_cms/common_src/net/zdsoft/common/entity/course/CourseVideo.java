/*
 * @(#)CourseVideo.java    Created on 2016年5月24日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.course;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;

/**
 * 点播视频视频
 *
 * @author xiongwq
 * @version $Revision: 1.0 $, $Date: 2016年5月24日 下午2:09:55 $
 */
public class CourseVideo extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 课程id
     */
    private long courseId;
    /**
     * 课次id
     */
    private long courseTimeId;
    /**
     * 视频时长
     */
    private float videoDuration;
    /**
     * 视频路径
     */
    private String videoFile;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件类型
     */
    private String fileExt;
    /**
     * 视频文件大小（单位：字节）
     */
    private long fileSize;
    /**
     * 预计更新时间
     */
    private Date predictTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 点播次数
     */
    private long playNum;
    /**
     * 视频上传方式（0视频上传，1ftp维护）
     */
    private int uploadType;
    /**
     * 视频转换状态（0：无需转换，1：转换中，2：转换成功，3：转换失败）
     */
    private long convertStatus;
    /**
     * 视频转换任务GUID
     */
    private String convertId;
    /**
     * 转换开始时间
     */
    private Date convertRunTime;
    /**
     * 转换结束时间
     */
    private Date convertEndTime;
    /**
     * 转换结果信息
     */
    private String convertMsg;
    /**
     * 说明
     */
    private String remark;

    /************************ get、set ***************************/
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

    public float getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(float videoDuration) {
        this.videoDuration = videoDuration;
    }

    public String getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(String videoFile) {
        this.videoFile = videoFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getPredictTime() {
        return predictTime;
    }

    public void setPredictTime(Date predictTime) {
        this.predictTime = predictTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public long getPlayNum() {
        return playNum;
    }

    public void setPlayNum(long playNum) {
        this.playNum = playNum;
    }

    public long getConvertStatus() {
        return convertStatus;
    }

    public void setConvertStatus(long convertStatus) {
        this.convertStatus = convertStatus;
    }

    public String getConvertId() {
        return convertId;
    }

    public void setConvertId(String convertId) {
        this.convertId = convertId;
    }

    public Date getConvertRunTime() {
        return convertRunTime;
    }

    public void setConvertRunTime(Date convertRunTime) {
        this.convertRunTime = convertRunTime;
    }

    public Date getConvertEndTime() {
        return convertEndTime;
    }

    public void setConvertEndTime(Date convertEndTime) {
        this.convertEndTime = convertEndTime;
    }

    public String getConvertMsg() {
        return convertMsg;
    }

    public void setConvertMsg(String convertMsg) {
        this.convertMsg = convertMsg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUploadType() {
        return uploadType;
    }

    public void setUploadType(int uploadType) {
        this.uploadType = uploadType;
    }

}
