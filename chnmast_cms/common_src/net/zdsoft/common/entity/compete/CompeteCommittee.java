/*
 * @(#)CompeteCommittee.java    Created on 2016年4月9日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.compete;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.SexType;
import net.zdsoft.common.enums.TitleType;

/**
 * T_COMPETE_COMMITTEE
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年4月9日 下午1:19:38 $
 */
public class CompeteCommittee extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    private long competeId;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 性别
     */
    private SexType sex;
    /**
     * 但文名称
     */
    private String unitName;
    /**
     * 头衔(9执行主席、1顾问、2主席、3副主席、4委员、5办公室主任、6办公室副主任、7办公室执行主任、8办公室执行副主任)
     */
    private TitleType title;
    /**
     * 个人职务
     */
    private String jobTitle;
    /**
     * 个人介绍
     */
    private String introduction;
    /**
     * 找路径
     */
    private String photoFile;
    /**
     * 排序好
     */
    private int orderNo;
    /**
     * 备注
     */
    private String remark;

    public long getCompeteId() {
        return competeId;
    }

    public void setCompeteId(long competeId) {
        this.competeId = competeId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public TitleType getTitle() {
        return title;
    }

    public void setTitle(TitleType title) {
        this.title = title;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(String photoFile) {
        this.photoFile = photoFile;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
