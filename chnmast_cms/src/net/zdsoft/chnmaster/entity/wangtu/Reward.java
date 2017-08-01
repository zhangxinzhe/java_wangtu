/*
 * @(#)Reward.java    Created on 2017年7月8日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu;

import java.util.Date;
import java.util.List;

import net.zdsoft.chnmaster.enums.wangtu.BiddingStatus;
import net.zdsoft.chnmaster.enums.wangtu.RewardStatus;
import net.zdsoft.common.entity.BaseEntity;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月8日 下午12:53:45 $
 */
public class Reward extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private long userId;
    private long catalogId;
    private String title;
    private String description;
    private String location;
    private double price;
    private double unfinishPrice;
    private String remark;
    private RewardStatus status;// 1:创建，2：发布，3：已接单，4：完成
    private Date deadline;

    private Date createTime;

    // 附加字段
    private String userName;
    private String realName;
    private String cataName;
    private String picturePath;
    private List<RewardBidding> biddingList;
    private List<RewardPicture> pictures;
    private int biddingNum;
    private BiddingStatus biddingStatus;
    private String phone;

    /**
     * 竞价详情
     */
    private RewardBidding biddingDetail;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(long catalogId) {
        this.catalogId = catalogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getUnfinishPrice() {
        return unfinishPrice;
    }

    public void setUnfinishPrice(double unfinishPrice) {
        this.unfinishPrice = unfinishPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public RewardStatus getStatus() {
        return status;
    }

    public void setStatus(RewardStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCataName() {
        return cataName;
    }

    public void setCataName(String cataName) {
        this.cataName = cataName;
    }

    public List<RewardBidding> getBiddingList() {
        return biddingList;
    }

    public void setBiddingList(List<RewardBidding> biddingList) {
        this.biddingList = biddingList;
    }

    public List<RewardPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<RewardPicture> pictures) {
        this.pictures = pictures;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getBiddingNum() {
        return biddingNum;
    }

    public void setBiddingNum(int biddingNum) {
        this.biddingNum = biddingNum;
    }

    public BiddingStatus getBiddingStatus() {
        return biddingStatus;
    }

    public void setBiddingStatus(BiddingStatus biddingStatus) {
        this.biddingStatus = biddingStatus;
    }

    public RewardBidding getBiddingDetail() {
        return biddingDetail;
    }

    public void setBiddingDetail(RewardBidding biddingDetail) {
        this.biddingDetail = biddingDetail;
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    
    

}
