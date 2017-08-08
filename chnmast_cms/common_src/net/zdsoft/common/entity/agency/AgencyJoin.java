/*
 * @(#)AgencyJoin.java    Created on 2015年12月8日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.agency;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.AgencyJoinStatus;
import net.zdsoft.common.enums.AgencyType;

/**
 * 申请加盟的基地（高校、机构或琴行）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年12月8日 下午4:55:59 $
 */
public class AgencyJoin extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 基地名称
     */
    private String agencyName;
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
     * 基地类型（1高校，2培训机构，3琴行）
     */
    private AgencyType agencyType;
    /**
     * 申请加盟状态（0：申请中，1：已加盟）
     */
    private AgencyJoinStatus agencyStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;

    /**************************** 辅助字段 *************************/
    private String regionName;// 所在地区名字

    /**************************** get、set方法 *************************/
    /**
     * @return Returns the agencyName.
     */
    public String getAgencyName() {
        return agencyName;
    }

    /**
     * @param agencyName
     *            The agencyName to set.
     */
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    /**
     * @return Returns the regionCode.
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * @param regionCode
     *            The regionCode to set.
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    /**
     * @return Returns the contactMan.
     */
    public String getContactMan() {
        return contactMan;
    }

    /**
     * @param contactMan
     *            The contactMan to set.
     */
    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    /**
     * @return Returns the contactPhone.
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * @param contactPhone
     *            The contactPhone to set.
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Returns the address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
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
     * @return Returns the agencyType.
     */
    public AgencyType getAgencyType() {
        return agencyType;
    }

    /**
     * @param agencyType
     *            The agencyType to set.
     */
    public void setAgencyType(AgencyType agencyType) {
        this.agencyType = agencyType;
    }

    /**
     * @return Returns the agencyStatus.
     */
    public AgencyJoinStatus getAgencyStatus() {
        return agencyStatus;
    }

    /**
     * @param agencyStatus
     *            The agencyStatus to set.
     */
    public void setAgencyStatus(AgencyJoinStatus agencyStatus) {
        this.agencyStatus = agencyStatus;
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
     * @return Returns the regionName.
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * @param regionName The regionName to set.
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

}
