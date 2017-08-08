/*
 * @(#)IndexStudent.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.index;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午10:16:34 $
 */
public class IndexStudent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 学员姓名
     */
    private String stuName;
    /**
     * 照片文件路径
     */
    private String photoFile;
    /**
     * 所属单位
     */
    private String unitName;
    /**
     * 介绍
     */
    private String introduction;
    /**
     * 是否显示
     */
    private YesNoType isShow;
    /**
     * 排序号
     */
    private int orderNo;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * @return Returns the stuName.
     */
    public String getStuName() {
        return stuName;
    }

    /**
     * @param stuName
     *            The stuName to set.
     */
    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    /**
     * @return Returns the photoFile.
     */
    public String getPhotoFile() {
        return photoFile;
    }

    /**
     * @param photoFile
     *            The photoFile to set.
     */
    public void setPhotoFile(String photoFile) {
        this.photoFile = photoFile;
    }

    /**
     * @return Returns the unitName.
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * @param unitName
     *            The unitName to set.
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * @return Returns the introduction.
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * @param introduction
     *            The introduction to set.
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
     * @return Returns the orderNo.
     */
    public int getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo
     *            The orderNo to set.
     */
    public void setOrderNo(int orderNo) {
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

}
