/*
 * @(#)RewardBidding.java    Created on 2017年7月17日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu;

import java.util.Date;

import net.zdsoft.chnmaster.enums.wangtu.BiddingStatus;
import net.zdsoft.common.entity.BaseEntity;

/**
 * 悬赏竞价表
 *
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月17日 下午2:41:51 $
 */
public class RewardBidding extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private long rewardId;
    private long userId;
    private double price;
    private double unfinishPrice;
    private BiddingStatus status;
    private Date createTime;

    public long getRewardId() {
        return rewardId;
    }

    public void setRewardId(long rewardId) {
        this.rewardId = rewardId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public BiddingStatus getStatus() {
        return status;
    }

    public void setStatus(BiddingStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
