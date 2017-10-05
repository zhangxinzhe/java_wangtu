/*
 * @(#)RewardBiddingDao.java    Created on 2017年7月17日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.wangtu;

import java.util.List;

import net.zdsoft.chnmaster.entity.wangtu.RewardBidding;
import net.zdsoft.chnmaster.enums.wangtu.BiddingStatus;

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
     * 删除竞价
     * @param biddingId
     * @return
     */
    public int deleteRewardBidding(long biddingId);

    /**
     * 判断用户是否参与竞价
     *
     * @param rewardId
     * @param userId
     * @return
     */
    public int isApplyRewardByRewardIdAndUserId(long rewardId, long userId);

    public RewardBidding getRewardBiddingByByRewardIdAndUserId(long rewardId, long userId, BiddingStatus state);

    /**
     * 获取选中的竞价
     *
     * @param rewardId
     * @return
     */
    public RewardBidding getSelectedRewardBidding(long rewardId);

    /**
     * 修改竞价状态
     *
     * @param biddingId
     * @return
     */
    public int updateStatusById(long biddingId, BiddingStatus state);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public RewardBidding getRewardBiddingById(long id);

    /**
     * 获取被选中的竞价
     *
     * @param rewardId
     * @return
     */
    public RewardBidding getChooseBiddingByRewardId(long rewardId);

    /**
     *
     *
     * @param rewardId
     * @return
     */
    public RewardBidding getBiddingById(long biddingId);
}
