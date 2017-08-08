/*
 * @(#)CompeteVideo.java    Created on 2016年8月16日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.compete;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.CompeteType;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年8月16日 下午2:37:11 $
 */
public class CompeteVideo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;
    /**
     * 活动id
     */
    private long competeId;
    /**
     * 活动类型
     */
    private CompeteType competeType;
    /**
     * 视频预览图
     */
    private String picFile;
    /**
     * 视频路径（upload/video/{年}/{月}/{日}/{COMPETEID}/{timestamp}.扩展名）
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
     * 视频时长（分钟）
     */
    private float videoDuration;
    /**
     * 视频文件大小（单位：字节）
     */
    private long fileSize;
    /**
     * 是否显示
     */
    private YesNoType isShow;
    /**
     * 显示序号
     */
    private Integer orderNo;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the competeType.
     */
    public CompeteType getCompeteType() {
        return competeType;
    }

    /**
     * @param competeType
     *            The competeType to set.
     */
    public void setCompeteType(CompeteType competeType) {
        this.competeType = competeType;
    }

    /**
     * @return Returns the videoFile.
     */
    public String getVideoFile() {
        return videoFile;
    }

    /**
     * @param videoFile
     *            The videoFile to set.
     */
    public void setVideoFile(String videoFile) {
        this.videoFile = videoFile;
    }

    /**
     * @return Returns the fileName.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     *            The fileName to set.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return Returns the fileExt.
     */
    public String getFileExt() {
        return fileExt;
    }

    /**
     * @param fileExt
     *            The fileExt to set.
     */
    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    /**
     * @return Returns the videoDuration.
     */
    public float getVideoDuration() {
        return videoDuration;
    }

    /**
     * @param videoDuration
     *            The videoDuration to set.
     */
    public void setVideoDuration(float videoDuration) {
        this.videoDuration = videoDuration;
    }

    /**
     * @return Returns the fileSize.
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize
     *            The fileSize to set.
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return Returns the isShow.
     */
    public YesNoType getIsShow() {
        return isShow;
    }

    /**
     * @param isShow
     *            The isShow to set.
     */
    public void setIsShow(YesNoType isShow) {
        this.isShow = isShow;
    }

    /**
     * @return Returns the remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            The remark to set.
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return Returns the competeId.
     */
    public long getCompeteId() {
        return competeId;
    }

    /**
     * @param competeId
     *            The competeId to set.
     */
    public void setCompeteId(long competeId) {
        this.competeId = competeId;
    }

    /**
     * @return Returns the picFile.
     */
    public String getPicFile() {
        return picFile;
    }

    /**
     * @param picFile
     *            The picFile to set.
     */
    public void setPicFile(String picFile) {
        this.picFile = picFile;
    }

    /**
     * @return Returns the orderNo.
     */
    public Integer getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo
     *            The orderNo to set.
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return Returns the createTime.
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            The createTime to set.
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取播放时长
     */
    public String getVideoDurationStr() {
        String str = "";
        if (videoDuration == 0) {
            str = "-";
            return str;
        }
        int allSeconds = (int) videoDuration;
        int hour = allSeconds / 3600;
        if (hour > 0) {
            str += hour + "小时";
        }
        int munites = (allSeconds - hour * 3600) / 60;
        if (munites > 0) {
            str += munites + "分";
        }
        int second = allSeconds % 60;
        if (second > 0) {
            str += second + "秒";
        }
        return str;
    }

}
