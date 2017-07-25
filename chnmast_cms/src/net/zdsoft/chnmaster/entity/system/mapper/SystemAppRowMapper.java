/* 
 * @(#)SystemAppRowMapper.java    Created on 2014-6-27
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.system.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.entity.system.SystemApp;

@SuppressWarnings("rawtypes")
public class SystemAppRowMapper implements RowMapper {
    private static final SystemAppRowMapper mapper = new SystemAppRowMapper();

    public static SystemAppRowMapper instance() {
        return mapper;
    }

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        SystemApp dto = new SystemApp();
        dto.setId(rs.getLong("ID"));
        dto.setAppName(rs.getString("APP_NAME"));
        dto.setAppDes(rs.getString("APP_DES"));
        dto.setIndexUrl(rs.getString("INDEX_URL"));
        dto.setIsUsing(rs.getInt("IS_USING"));
        return dto;
    }
}
