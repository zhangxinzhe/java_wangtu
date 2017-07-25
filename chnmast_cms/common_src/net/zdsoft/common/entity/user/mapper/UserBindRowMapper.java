/*
 * @(#)UserBindRowMapper.java    Created on 2016年7月14日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.user.UserBind;
import net.zdsoft.common.enums.PlatType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年7月14日 下午2:13:21 $
 */
@SuppressWarnings("rawtypes")
public class UserBindRowMapper implements RowMapper {

    private static UserBindRowMapper rowMapper = new UserBindRowMapper();

    public static UserBindRowMapper instance() {
        return rowMapper;
    }

    @Override
    public UserBind mapRow(ResultSet rs, int i) throws SQLException {
        UserBind b = new UserBind();
        b.setId(rs.getLong("ID"));
        b.setBindId(rs.getString("BIND_ID"));
        b.setNickName(rs.getString("NICK_NAME"));
        b.setUserId(rs.getLong("USER_ID"));
        b.setNewUserId(rs.getString("NEW_USERID"));
        b.setPlatType(PlatType.get(rs.getInt("PLAT_TYPE")));
        b.setBindTime(rs.getTimestamp("BIND_TIME"));
        return b;
    }

}
