/*
 * @(#)RewardPictureMapper.java    Created on 2017年7月18日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.entity.wangtu.RewardPicture;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月18日 下午2:01:47 $
 */
public class RewardPictureMapper implements RowMapper<RewardPicture> {

    private static RewardPictureMapper mapper = new RewardPictureMapper();

    public static RewardPictureMapper instance() {
        return mapper;
    }

    @Override
    public RewardPicture mapRow(ResultSet rs, int arg1) throws SQLException {
        RewardPicture entity = new RewardPicture();
        entity.setId(rs.getLong("id"));
        entity.setRewardId(rs.getLong("reward_id"));
        entity.setFilePath(rs.getString("picPath"));
        return entity;
    }

}
