/*
 * @(#)RewardDao.java    Created on 2017年7月11日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.wangtu;

import java.util.List;

import net.zdsoft.chnmaster.entity.wangtu.Reward;
import net.zdsoft.chnmaster.enums.wangtu.RewardStatus;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月11日 上午11:42:56 $
 */
public interface RewardDao {
    /**
     * 获取主键
     *
     * @return
     */
    public long generatePrimaryKey();

    /**
     * 根据条件查询
     *
     * @param condistions
     * @param page
     * @return
     */
    public List<Reward> getRewardsByCondition(List<QueryCondition> condistions, PageDto page);

    public int addReward(Reward reward);
    
    public int updateReward(Reward reward);

    public int deleteReward(long id);

    public int editReward(Reward reward);

    public Reward getRewardById(long rewardId);

    /**
     * 我竞价的悬赏
     *
     * @param userId
     * @param page
     * @return
     */
    public List<Reward> getMyRewardBidding(long userId, PageDto page);

    /**
     * 修改状态
     */
    public int updateRewardStatus(long id, RewardStatus status);
}
