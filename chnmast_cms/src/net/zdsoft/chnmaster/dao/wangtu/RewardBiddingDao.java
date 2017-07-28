/*
 * @(#)RewardBiddingDao.java    Created on 2017年7月17日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.wangtu;

import java.util.List;

import net.zdsoft.chnmaster.entity.wangtu.RewardBidding;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月17日 下午5:04:00 $
 */
public interface RewardBiddingDao {
    /**
     * 悬赏竞价列表
     *
     * @param rewardId
     * @return
     */
    public List<RewardBidding> getRewardBiddingByRewardId(long rewardId);

    /**
     * 新增竞价
     *
     * @param bidding
     * @return
     */
    public int addRewardBidding(RewardBidding bidding);

    /**
     * 判断用户是否参与竞价
     *
     * @param rewardId
     * @param userId
     * @return
     */
    public int isApplyRewardByRewardIdAndUserId(long rewardId, long userId);
}