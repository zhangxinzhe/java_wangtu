/*
 * @(#)Agency.java    Created on 2015年10月27日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.agency;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.AgencyType;
import net.zdsoft.common.enums.StatusEunm;
import net.zdsoft.common.enums.YesNoType;

/**
 * 培训基地（高校或琴行）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月27日 下午2:57:29 $
 */
public class Agency extends BaseEntity {

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
     * 是否注销（0正常，1已注销）
     */
    private StatusEunm isCancel;
    /**
     * 基地来源（0后台创建，1申请加盟）
     */
    private int agencySource;
    /**
     * 排行（显示顺序）
     */
    private int ranking;
    /**
     * 是否推荐（首页推荐）
     */
    private YesNoType isRecommend = YesNoType.NO;
    /**
     * 推荐日期
     */
    private Date recommendTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;

    private long userId;
    private String username; // 用户名
    private String password; // 密码
    private String regionName;// 所在地区名字
    private String pictureFile;// 图片路径

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
     * @return Returns the isCancel.
     */
    public StatusEunm getIsCancel() {
        return isCancel;
    }

    /**
     * @param isCancel
     *            The isCancel to set.
     */
    public void setIsCancel(StatusEunm isCancel) {
        this.isCancel = isCancel;
    }

    /**
     * @return Returns the ranking.
     */
    public int getRanking() {
        return ranking;
    }

    /**
     * @param ranking
     *            The ranking to set.
     */
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    /**
     * @return Returns the isRecommend.
     */
    public YesNoType getIsRecommend() {
        return isRecommend;
    }

    /**
     * @param isRecommend
     *            The isRecommend to set.
     */
    public void setIsRecommend(YesNoType isRecommend) {
        this.isRecommend = isRecommend;
    }

    /**
     * @return Returns the recommendTime.
     */
    public Date getRecommendTime() {
        return recommendTime;
    }

    /**
     * @param recommendTime
     *            The recommendTime to set.
     */
    public void setRecommendTime(Date recommendTime) {
        this.recommendTime = recommendTime;
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
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Returns the regionName.
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * @param regionName
     *            The regionName to set.
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * @return Returns the userId.
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            The userId to set.
     */
    public void setUserId(long userId) {
        this.userId = userId;
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
     * @return Returns the agencySource.
     */
    public int getAgencySource() {
        return agencySource;
    }

    /**
     * @param agencySource
     *            The agencySource to set.
     */
    public void setAgencySource(int agencySource) {
        this.agencySource = agencySource;
    }

}
