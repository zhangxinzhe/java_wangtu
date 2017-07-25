/*
 * @(#)UserGroupTypeRowMapper.java    Created on 2016年6月13日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.user.UserGroupType;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.keel.jdbc.MapRowMapper;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年6月13日 上午11:27:20 $
 */
@SuppressWarnings("rawtypes")
public class UserGroupTypeRowMapper implements RowMapper {
    private static UserGroupTypeRowMapper rowMapper = new UserGroupTypeRowMapper();
    public static UserGroupTypeMap userGroupTypeMap = new UserGroupTypeMap();

    public static UserGroupTypeRowMapper instance() {
        return rowMapper;
    }

    @Override
    public UserGroupType mapRow(ResultSet rs, int arg1) throws SQLException {
        UserGroupType entity = new UserGroupType();
        entity.setId(rs.getLong("ID"));
        entity.setTitle(rs.getString("TITLE"));
        entity.setIsCanDel(YesNoType.get(rs.getInt("IS_CAN_DEL")));
        return entity;
    }

    static class UserGroupTypeMap implements MapRowMapper<Long, UserGroupType> {
        @Override
        public Long mapRowKey(ResultSet rs, int i) throws SQLException {
            return rs.getLong("id");
        }

        @Override
        public UserGroupType mapRowValue(ResultSet rs, int i) throws SQLException {
            return rowMapper.mapRow(rs, i);
        }
    }
}
