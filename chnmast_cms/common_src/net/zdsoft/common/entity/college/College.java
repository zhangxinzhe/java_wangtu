/*
 * @(#)College.java    Created on 2016年11月30日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.college;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hanqr t_college
 * @version $Revision: 1.0 $, $Date: 2016年11月30日 下午2:09:19 $
 */
public class College extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 高校名称
     */
    private String name;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 行政区码
     */
    private String regionCode;
    /**
     * banner图（upload/college/{年}/{月}/{日}/{id}_banner.{扩展名}?t=timestamp）
     */
    private String bannerFile;
    /**
     * logo图（upload/college/{年}/{月}/{日}/{id}_logo.{扩展名}?t=timestamp）
     */
    private String logoFile;
    /**
     * 高校简介
     */
    private String introduction;
    /**
     * 高校优势
     */
    private String feature;
    /**
     * 是否推荐
     */
    private YesNoType isRecommend;
    /**
     * 显示次序
     */
    private int displayNo;
    /**
     * 创建时间
     */
    private Date createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getBannerFile() {
        return bannerFile;
    }

    public void setBannerFile(String bannerFile) {
        this.bannerFile = bannerFile;
    }

    public String getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(String logoFile) {
        this.logoFile = logoFile;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public YesNoType getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(YesNoType isRecommend) {
        this.isRecommend = isRecommend;
    }

    public int getDisplayNo() {
        return displayNo;
    }

    public void setDisplayNo(int displayNo) {
        this.displayNo = displayNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
