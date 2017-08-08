/*
 * @(#)School.java    Created on 2016年4月5日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.school;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.StatusEunm;

/**
 * 学校
 *
 * @author xiongwq
 * @version $Revision: 1.0 $, $Date: 2016年4月5日 下午4:06:06 $
 */
public class School extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 学校简拼
     */
    private String shortspell;
    /**
     * 所在地区
     */
    private String regionCode;
    /**
     * 联系人
     */
    private String contactMan;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * email
     */
    private String email;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 介绍
     */
    private String introduction;
    /**
     * 是否注销（0正常，1已注销）
     */
    private StatusEunm isCancel;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;

    private String regionName;// 所在地区名字

    /**************************** get、set方法 *************************/
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public StatusEunm getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(StatusEunm isCancel) {
        this.isCancel = isCancel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getShortspell() {
        return shortspell;
    }

    public void setShortspell(String shortspell) {
        this.shortspell = shortspell;
    }

}
