/*
 * @(#)RewardBiddingDaoImpl.java    Created on 2017年7月17日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.wangtu.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.wangtu.RewardBiddingDao;
import net.zdsoft.chnmaster.entity.wangtu.RewardBidding;
import net.zdsoft.chnmaster.entity.wangtu.mapper.RewardBiddingMapper;
import net.zdsoft.common.dao.BaseDaoImpl;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月17日 下午5:04:43 $
 */
@SuppressWarnings("unchecked")
@Repository("rewardBiddingDao")
public class RewardBiddingDaoImpl extends BaseDaoImpl implements RewardBiddingDao {

    @Override
    public List<RewardBidding> getRewardBiddingByRewardId(long rewardId) {
        String sql = "SELECT * FROM T_REWARD_BIDDING R ,T_USER U WHERE U.ID = R.USER_ID AND R.REWARD_ID=? ORDER BY R.CREATE_DATE ";
        return this.find(sql, new Object[] { rewardId }, RewardBiddingMapper.instance());

    }

    @Override
    public int addRewardBidding(RewardBidding bidding) {
        String sql = "INSERT INTO T_REWARD (REWARD_ID,USER_ID,PRICE,UNFINISH_PRICE,STATE,CREATE_DATE)VALUES(?,?,?,?,0,?)";
        return this.executeUpdate(sql, new Object[] { bidding.getRewardId(), bidding.getUserId(), bidding.getPrice(),
                bidding.getUnfinishPrice(), new Date() });
    }

    @Override
    public int isApplyRewardByRewardIdAndUserId(long rewardId, long userId) {
        String sql = "SELECT COUNT(1) FROM T_REWARD_BIDDING WHERE REWARD_ID=? AND USER_ID=? ";
        return this.findForInt(sql, new Object[] { rewardId, userId });
    }

    @Override
    public RewardBidding getRewardBiddingByByRewardIdAndUserId(long rewardId, long userId) {
        String sql = "SELECT * FROM T_REWARD_BIDDING WHERE REWARD_ID=? AND USER_ID=? ";
        return (RewardBidding) this.findForObject(sql, new Object[] { rewardId, userId },
                RewardBiddingMapper.instance());
    }

}
