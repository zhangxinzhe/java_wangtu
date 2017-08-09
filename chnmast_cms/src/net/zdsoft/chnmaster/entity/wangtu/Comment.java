/*
 * @(#)Reward.java    Created on 2017年7月8日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu;

import java.util.Date;
import java.util.List;

import net.zdsoft.common.entity.BaseEntity;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月8日 下午12:53:45 $
 */
public class Comment extends BaseEntity {
    private static final long serialVersionUID = 426089512558069682L;

    private long userId;
    private long reviewerId;
    private String content;
    private float serviceQuality;
    private String serviceQualityContent;
    private float serviceAttitude;
    private String serviceAttitudeContent;
    private Date commentTime;
    private String replyContent;
    private Date replyTime;
    private int isSatisfy;/* 0: 不满意，1满意 */
    private String remark;
    private long rewardId;
    private boolean isAnonymous;

    // 附加字段
    private String userName;
    private String avatarFile;
    private List<CommentPicture> commentPictures;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getServiceAttitude() {
        return serviceAttitude;
    }

    public void setServiceAttitude(float serviceAttitude) {
        this.serviceAttitude = serviceAttitude;
    }

    public String getServiceAttitudeContent() {
        return serviceAttitudeContent;
    }

    public void setServiceAttitudeContent(String serviceAttitudeContent) {
        this.serviceAttitudeContent = serviceAttitudeContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public int getIsSatisfy() {
        return isSatisfy;
    }

    public void setIsSatisfy(int isSatisfy) {
        this.isSatisfy = isSatisfy;
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

    public String getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(String avatarFile) {
        this.avatarFile = avatarFile;
    }

    public List<CommentPicture> getCommentPictures() {
        return commentPictures;
    }

    public void setCommentPictures(List<CommentPicture> commentPictures) {
        this.commentPictures = commentPictures;
    }

    public float getServiceQuality() {
        return serviceQuality;
    }

    public void setServiceQuality(float serviceQuality) {
        this.serviceQuality = serviceQuality;
    }

    public String getServiceQualityContent() {
        return serviceQualityContent;
    }

    public void setServiceQualityContent(String serviceQualityContent) {
        this.serviceQualityContent = serviceQualityContent;
    }

	public long getRewardId() {
		return rewardId;
	}

	public void setRewardId(long rewardId) {
		this.rewardId = rewardId;
	}

	public boolean getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}
    
    

}
