/*
 * @(#)RewardMapper.java    Created on 2017年7月8日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.entity.wangtu.Reward;
import net.zdsoft.chnmaster.enums.wangtu.RewardStatus;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月8日 下午1:12:57 $
 * @param <T>
 */
public class RewardMapper implements RowMapper<Reward> {
    private static RewardMapper rowMapper = new RewardMapper();

    public static RewardMapper instance() {
        return rowMapper;
    }

    @Override
    public Reward mapRow(ResultSet rs, int arg1) throws SQLException {
        Reward entity = new Reward();
        entity.setId(rs.getLong("ID"));
        entity.setUserId(rs.getLong("user_id"));
        entity.setCatalogId(rs.getLong("catalog_id"));
        entity.setTitle(rs.getString("title"));
        entity.setDescription(rs.getString("description"));
        entity.setLocation(rs.getString("location"));
        entity.setPrice(rs.getDouble("price"));
        entity.setUnfinishPrice(rs.getDouble("unfinish_price"));
        entity.setRemark(rs.getString("remark"));
        entity.setStatus(RewardStatus.getStatus(rs.getInt("reward_status")));
        entity.setCreateTime(rs.getTimestamp("create_time"));
        entity.setUserName(rs.getString("username"));
        entity.setRealName(rs.getString("REALNAME"));
        entity.setCataName(rs.getString("CataName"));
        entity.setDeadline(rs.getTimestamp("deadline"));
        return entity;
    }

}
