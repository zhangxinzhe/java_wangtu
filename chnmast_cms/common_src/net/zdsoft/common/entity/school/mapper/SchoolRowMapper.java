/*
 * @(#)SchoolRowMapper.java    Created on 2016年4月5日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.school.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.school.School;
import net.zdsoft.common.enums.StatusEunm;

/**
 * @author xiongwq
 * @version $Revision: 1.0 $, $Date: 2016年4月5日 下午4:12:35 $
 */
@SuppressWarnings("rawtypes")
public class SchoolRowMapper implements RowMapper {
    private static SchoolRowMapper rowMapper = new SchoolRowMapper();

    public static SchoolRowMapper instance() {
        return rowMapper;
    }

    @Override
    public School mapRow(ResultSet rs, int arg1) throws SQLException {
        School entity = new School();
        entity.setId(rs.getLong("ID"));
        entity.setSchoolName(rs.getString("SCHOOL_NAME"));
        entity.setShortspell(rs.getString("SHORTSPELL"));
        entity.setRegionCode(rs.getString("REGIONCODE"));
        entity.setContactMan(rs.getString("CONTACT_MAN"));
        entity.setContactPhone(rs.getString("CONTACT_PHONE"));
        entity.setEmail(rs.getString("EMAIL"));
        entity.setAddress(rs.getString("ADDRESS"));
        entity.setIntroduction(rs.getString("INTRODUCTION"));
        entity.setIsCancel(StatusEunm.get(rs.getInt("IS_CANCEL")));
        entity.setCreateTime(rs.getTimestamp("CREATETIME"));
        return entity;
    }

}
