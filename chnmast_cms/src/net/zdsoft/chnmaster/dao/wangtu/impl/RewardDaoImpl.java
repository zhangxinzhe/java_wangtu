/*
 * @(#)RewardDaoImpl.java    Created on 2017年7月11日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.wangtu.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.wangtu.RewardDao;
import net.zdsoft.chnmaster.entity.wangtu.Reward;
import net.zdsoft.chnmaster.entity.wangtu.mapper.RewardMapper;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.dao.queryCondition.QueryConditionBuilder;
import net.zdsoft.common.entity.PageDto;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月11日 上午11:45:16 $
 */
@SuppressWarnings("unchecked")
@Repository("rewardDao")
public class RewardDaoImpl extends BaseDaoImpl implements RewardDao {

    @Override
    public List<Reward> getRewardsByCondition(List<QueryCondition> condistions, PageDto page) {
        String sql = "SELECT R.*,U.USERNAME,U.REALNAME,C.CATANAME FROM T_REWARD R,T_USER U,T_CATALOG C WHERE U.ID=R.USER_ID AND C.ID=R.CATALOG_ID ";
        QueryConditionBuilder builder = new QueryConditionBuilder();
        builder.addConditions(condistions);
        String paramStr = builder.buildCondition();
        if (StringUtils.isNotBlank(paramStr)) {
            sql += " and " + paramStr;
        }
        sql += " order by create_time desc";
        if (page == null) {
            return this.find(sql, builder.buildParameters(), RewardMapper.instance());
        }
        return this.findForPage(sql, builder.buildParameters(), RewardMapper.instance(), page);
    }

    @Override
    public int addReward(Reward reward) {
        String sql = "INSERT INTO T_REWARD (ID,USER_ID,CATALOG_ID,TITLE,DESCRIPTION,LOCATION,PRICE,unfinish_price,REMARK,create_time)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        return this.executeUpdate(sql,
                new Object[] { reward.getId(), reward.getUserId(), reward.getCatalogId(), reward.getTitle(),
                        reward.getDescription(), reward.getPrice(), reward.getUnfinishPrice(), reward.getRemark(),
                        new Date() });
    }

    @Override
    public int deleteReward(long id) {
        String sql = "DELETE * FROM T_REWARD WHERE ID=? AND REWARD_STATUS<3 ";
        return this.executeUpdate(sql, new Object[] { id });
    }

    @Override
    public int editReward(Reward reward) {
        return 0;
    }

    @Override
    public Reward getRewardById(long rewardId) {
        String sql = "SELECT R.*,U.USERNAME,U.REALNAME,C.CATANAME FROM T_REWARD R,T_USER U,T_CATALOG C WHERE U.ID=R.USER_ID AND C.ID=R.CATALOG_ID AND R.ID=?";
        return (Reward) this.findForObject(sql, new Object[] { rewardId }, RewardMapper.instance());
    }

    @Override
    public long generatePrimaryKey() {
        return this.generatePrimaryKey("T_REWARD");
    }

    @Override
    public List<Reward> getMyRewardBidding(long userId, PageDto page) {
        String sql = "SELECT T.* FROM T_REWARD T,T_REWARD_BIDDING B WHERE B.REWARD_ID=T.ID AND B.USER_ID=? ";
        if (null == page) {
            return this.find(sql, new Object[] { userId }, RewardMapper.instance());
        }
        return this.findForPage(sql, new Object[] { userId }, RewardMapper.instance(), page);
    }

}