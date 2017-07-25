/*
 * @(#)RewardBiddingServiceImpl.java    Created on 2017年7月17日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.wangtu.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.wangtu.RewardBiddingDao;
import net.zdsoft.chnmaster.entity.wangtu.RewardBidding;
import net.zdsoft.chnmaster.service.wangtu.RewardBiddingService;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月17日 下午5:03:18 $
 */
@Service("rewardBiddingService")
public class RewardBiddingServiceImpl implements RewardBiddingService {

    private RewardBiddingDao rewardBiddingDao;

    @Override
    public List<RewardBidding> getRewardBiddingByRewardId(long rewardId) {
        return rewardBiddingDao.getRewardBiddingByRewardId(rewardId);
    }

    @Override
    public int addRewardBidding(RewardBidding bidding) {
        return rewardBiddingDao.addRewardBidding(bidding);
    }

    @Override
    public int isApplyRewardByRewardIdAndUserId(long rewardId, long userId) {
        return rewardBiddingDao.isApplyRewardByRewardIdAndUserId(rewardId, userId);
    }

}
