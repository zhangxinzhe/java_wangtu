/*
 * @(#)Picture.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.picture;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.YesNoType;

/**
 * 照片或视频
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午10:20:10 $
 */
public class Picture extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 来源ID
     */
    private long objectId;
    /**
     * 类型（1培训基地，2名师大家，3优秀学员，4活动图集）
     */
    private int objectType;
    /**
     * 名称
     */
    private String pictureName;
    /**
     * 图片路径
     */
    private String pictureFile;
    /**
     * 视频路径
     */
    private String videoFile;
    /**
     * 文件类型（1图片，2视频）
     */
    private int fileType;
    /**
     * 文件大小（字节）
     */
    private long fileSize;
    /**
     * 文件类型（扩展名）
     */
    private String fileExt;
    /**
     * 是否显示（0不显示，1显示）
     */
    private YesNoType isShow;
    /**
     * 显示排序
     */
    private int showOrder;
    /**
     * 创建时时间
     */
    private Date createTime;

    /********************************* 辅助字段 ************************************/
    private boolean isCurrent;// 是否选中

    /**
     * @return Returns the objectId.
     */
    public long getObjectId() {
        return objectId;
    }

    /**
     * @param objectId
     *            The objectId to set.
     */
    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    /**
     * @return Returns the objectType.
     */
    public int getObjectType() {
        return objectType;
    }

    /**
     * @param objectType
     *            The objectType to set.
     */
    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    /**
     * @return Returns the pictureName.
     */
    public String getPictureName() {
        return pictureName;
    }

    /**
     * @param pictureName
     *            The pictureName to set.
     */
    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    /**
     * @return Returns the pictureFile.
     */
    public String getPictureFile() {
        return pictureFile;
    }

    /**
     * @param pictureFile
     *            The pictureFile to set.
     */
    public void setPictureFile(String pictureFile) {
        this.pictureFile = pictureFile;
    }

    /**
     * @return Returns the fileType.
     */
    public int getFileType() {
        return fileType;
    }

    /**
     * @param fileType
     *            The fileType to set.
     */
    public void setFileType(int fileType) {
        this.fileType = fileType;
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
     * @return Returns the showOrder.
     */
    public int getShowOrder() {
        return showOrder;
    }

    /**
     * @param showOrder
     *            The showOrder to set.
     */
    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
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
     * @return Returns the isCurrent.
     */
    public boolean getIsCurrent() {
        return isCurrent;
    }

    /**
     * @param isCurrent
     *            The isCurrent to set.
     */
    public void setCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

}
