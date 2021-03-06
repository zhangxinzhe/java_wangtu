/*
 * @(#)RewardBiddingServiceImpl.java    Created on 2017年7月17日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.wangtu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.wangtu.RewardBiddingDao;
import net.zdsoft.chnmaster.dao.wangtu.RewardDao;
import net.zdsoft.chnmaster.entity.wangtu.Order;
import net.zdsoft.chnmaster.entity.wangtu.RewardBidding;
import net.zdsoft.chnmaster.enums.wangtu.BiddingStatus;
import net.zdsoft.chnmaster.enums.wangtu.RewardStatus;
import net.zdsoft.chnmaster.service.account.AccountService;
import net.zdsoft.chnmaster.service.wangtu.OrderService;
import net.zdsoft.chnmaster.service.wangtu.RewardBiddingService;
import net.zdsoft.common.entity.account.Account;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月17日 下午5:03:18 $
 */
@Service("rewardBiddingService")
public class RewardBiddingServiceImpl implements RewardBiddingService {

    @Resource
    private RewardBiddingDao rewardBiddingDao;
    @Resource
    private RewardDao rewardDao;
    @Resource
    private OrderService orderService;
    @Resource
    private AccountService accountService;

    @Override
    public List<RewardBidding> getRewardBiddingByRewardId(long rewardId) {
        return rewardBiddingDao.getRewardBiddingByRewardId(rewardId);
    }

    @Override
    public int addRewardBidding(RewardBidding bidding) {
        return rewardBiddingDao.addRewardBidding(bidding);
    }
    
    @Override
    public int deleteRewardBidding(long biddingId){
    	return rewardBiddingDao.deleteRewardBidding(biddingId);
    }

    @Override
    public int isApplyRewardByRewardIdAndUserId(long rewardId, long userId) {
        return rewardBiddingDao.isApplyRewardByRewardIdAndUserId(rewardId, userId);
    }

    @Override
    public RewardBidding getRewardBiddingByByRewardIdAndUserId(long rewardId, long userId, BiddingStatus state) {
        return rewardBiddingDao.getRewardBiddingByByRewardIdAndUserId(rewardId, userId, state);
    }

    @Override
    public String updateCancelBiddingReward(long biddingId, BiddingStatus states) {
        RewardBidding bidding = rewardBiddingDao.getRewardBiddingById(biddingId);
        if (null == bidding) {
            return "竞价信息不存在！";
        }

        // 修改竞价状态
        int i = rewardBiddingDao.updateStatusById(biddingId, states);
        if (i <= 0) {
            return "撤销失败，请重试！";
        }
        // 查询竞价支付订单
        Order order = orderService.getFinishOrderByUserIdAndRewardId(bidding.getUserId(), bidding.getId());
        if (order != null) {
            // 支付金额退回
            Account a = accountService.getAccountById(bidding.getUserId());
            // a.setFunds(a.getFunds() + order.getPayAmount());
            accountService.updateFundsByAccountId(a.getId(), a.getFunds() + order.getPayAmount());
        }
        return "success";
    }

    @Override
    public int updateStatusById(long biddingId, BiddingStatus state) {

        return rewardBiddingDao.updateStatusById(biddingId, state);

    }

    @Override
    public int updateStatusToChoosed(long biddingId) {
        RewardBidding bidding = rewardBiddingDao.getRewardBiddingById(biddingId);
        if (bidding == null) {
            return 0;
        }
        rewardBiddingDao.updateStatusById(biddingId, BiddingStatus.SUCCESS);
        return rewardDao.updateRewardStatus(bidding.getRewardId(), RewardStatus.DOING);
    }

    @Override
    public RewardBidding getChooseBiddingByRewardId(long rewardId) {

        return rewardBiddingDao.getChooseBiddingByRewardId(rewardId);
    }

    @Override
    public RewardBidding getBiddingById(long biddingId) {
        return rewardBiddingDao.getBiddingById(biddingId);
    }

}
