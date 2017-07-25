/* 
 * @(#)SystemLogRowMapper.java    Created on 2014-6-27
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.system.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.entity.system.SystemLog;

@SuppressWarnings("rawtypes")
public class SystemLogRowMapper implements RowMapper {
    private static final SystemLogRowMapper mapper = new SystemLogRowMapper();

    public static SystemLogRowMapper instance() {
        return mapper;
    }

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        SystemLog systemLog = new SystemLog();
        systemLog.setId(rs.getLong("ID"));
        systemLog.setUserId(rs.getLong("USERID"));
        systemLog.setOperate(rs.getString("OPERATE"));
        systemLog.setOperateTime(rs.getTimestamp("OPERATETIME"));
        systemLog.setOperateIp(rs.getString("OPERATEIP"));
        systemLog.setName(rs.getString("USERNAME"));
        systemLog.setRealName(rs.getString("REALNAME"));
        return systemLog;
    }

}
