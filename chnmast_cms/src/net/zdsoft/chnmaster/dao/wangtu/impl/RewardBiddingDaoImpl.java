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
import net.zdsoft.chnmaster.enums.wangtu.BiddingStatus;
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
        String sql = "SELECT * FROM T_REWARD_BIDDING R ,T_USER U WHERE U.ID = R.USER_ID AND R.REWARD_ID=? AND STATE = ? ORDER BY R.CREATE_DATE ";
        return this.find(sql, new Object[] { rewardId, BiddingStatus.PAY.getValue() },
                RewardBiddingMapper.rewardBiddingAndUserMapper);

    }

    @Override
    public int addRewardBidding(RewardBidding bidding) {
        String sql = "INSERT INTO T_REWARD_BIDDING (REWARD_ID,USER_ID,PRICE,UNFINISH_PRICE,STATE,CREATE_DATE)VALUES(?,?,?,?,0,?)";
        return this.executeUpdate(sql, new Object[] { bidding.getRewardId(), bidding.getUserId(), bidding.getPrice(),
                bidding.getUnfinishPrice(), new Date() });
    }
    
    @Override
    public int deleteRewardBidding(long biddingId){
    	String sql = "DELETE FROM T_REWARD_BIDDING WHERE ID = ?";
    	return this.executeUpdate(sql, new Object[] { biddingId });
    }

    @Override
    public int isApplyRewardByRewardIdAndUserId(long rewardId, long userId) {
        String sql = "SELECT COUNT(1) FROM T_REWARD_BIDDING WHERE REWARD_ID=? AND USER_ID=? AND STATE < 4";
        return this.findForInt(sql, new Object[] { rewardId, userId });
    }

    @Override
    public RewardBidding getRewardBiddingByByRewardIdAndUserId(long rewardId, long userId, BiddingStatus state) {
        String sql = "SELECT * FROM T_REWARD_BIDDING WHERE REWARD_ID=? AND USER_ID=? and state=? ";
        return (RewardBidding) this.findForObject(sql, new Object[] { rewardId, userId, state.getValue() },
                RewardBiddingMapper.instance());
    }

    @Override
    public RewardBidding getSelectedRewardBidding(long rewardId) {
        String sql = "SELECT * FROM T_REWARD_BIDDING WHERE REWARD_ID=? AND STATE in(2,3,6) ";
        return (RewardBidding) this.findForObject(sql, new Object[] { rewardId }, RewardBiddingMapper.instance());
    }

    @Override
    public int updateStatusById(long biddingId, BiddingStatus state) {
        String sql = "UPDATE T_REWARD_BIDDING SET STATE=? WHERE ID=?";
        return this.executeUpdate(sql, new Object[] { state.getValue(), biddingId });
    }

    @Override
    public RewardBidding getRewardBiddingById(long id) {
        String sql = "SELECT * FROM T_REWARD_BIDDING WHERE ID=? AND STATE<4";
        return (RewardBidding) findForObject(sql, new Object[] { id }, RewardBiddingMapper.instance());
    }

    @Override
    public RewardBidding getChooseBiddingByRewardId(long rewardId) {
        String sql = "SELECT * FROM T_REWARD_BIDDING WHERE REWARD_ID=? AND (STATE=2 or state =3) ";
        return (RewardBidding) findForObject(sql, new Object[] { rewardId }, RewardBiddingMapper.instance());
    }

    @Override
    public RewardBidding getBiddingById(long biddingId) {
        String sql = "SELECT * FROM T_REWARD_BIDDING WHERE ID=? ";
        return (RewardBidding) this.findForObject(sql, new Object[] { biddingId }, RewardBiddingMapper.instance());
    }

}
