/* 
 * @(#)SystemRoleRowMapper.java    Created on 2014-6-27
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.system.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.entity.system.SystemRole;
import net.zdsoft.chnmaster.enums.CmsRoleType;

@SuppressWarnings("rawtypes")
public class SystemRoleRowMapper implements RowMapper {
    private static SystemRoleRowMapper rowMapper = new SystemRoleRowMapper();

    public static SystemRoleRowMapper instance() {
        return rowMapper;
    }

    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        SystemRole systemRole = new SystemRole();
        systemRole.setId(rs.getLong("ID"));
        systemRole.setName(rs.getString("NAME"));
        systemRole.setDescription(rs.getString("DESCRIPTION"));
        systemRole.setCreateTime(rs.getDate("CREATETIME"));
        systemRole.setRoleType(CmsRoleType.get(rs.getInt("ROLETYPE")));
        return systemRole;
    }

}
