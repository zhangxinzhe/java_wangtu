/*
 * @(#)RewardBiddingMapper.java    Created on 2017年7月17日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.entity.wangtu.Order;
import net.zdsoft.chnmaster.entity.wangtu.RewardBidding;
import net.zdsoft.chnmaster.enums.wangtu.BiddingStatus;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月17日 下午4:03:45 $
 */
public class RewardBiddingMapper implements RowMapper<RewardBidding> {

    private static RewardBiddingMapper rowMapper = new RewardBiddingMapper();
    public static RewardBiddingAndUserMapper rewardBiddingAndUserMapper = new RewardBiddingAndUserMapper();

    public static RewardBiddingMapper instance() {
        return rowMapper;
    }
    
    static class RewardBiddingAndUserMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int arg1) throws SQLException {
        	RewardBidding entity = rowMapper.mapRow(rs, arg1);
        	entity.setUserName(rs.getString("username"));
        	entity.setAvatarFile(rs.getString("AVATAR_FILE"));
            return null;
        }

    }

    @Override
    public RewardBidding mapRow(ResultSet rs, int arg1) throws SQLException {
        RewardBidding entity = new RewardBidding();
        entity.setId(rs.getLong("id"));
        entity.setRewardId(rs.getLong("reward_id"));
        entity.setUserId(rs.getLong("user_id"));
        entity.setPrice(rs.getDouble("price"));
        entity.setUnfinishPrice(rs.getDouble("unfinish_price"));
        entity.setStatus(BiddingStatus.getStatus(rs.getInt("state")));
        entity.setCreateTime(rs.getTimestamp("create_date"));
        return entity;
    }

}
