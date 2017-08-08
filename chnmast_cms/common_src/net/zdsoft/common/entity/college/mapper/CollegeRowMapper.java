/*
 * @(#)CollegeRowMapper.java    Created on 2016年11月30日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.college.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.college.College;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年11月30日 下午2:17:52 $
 */
@SuppressWarnings("rawtypes")
public class CollegeRowMapper implements RowMapper {

    private static CollegeRowMapper rowMapper = new CollegeRowMapper();

    public static CollegeRowMapper instance() {
        return rowMapper;
    }

    @Override
    public College mapRow(ResultSet rs, int index) throws SQLException {
        College entity = new College();
        entity.setId(rs.getInt("ID"));
        entity.setName(rs.getString("NAME"));
        entity.setPhone(rs.getString("PHONE"));
        entity.setBannerFile(rs.getString("BANNER_FILE"));
        entity.setLogoFile(rs.getString("LOGO_FILE"));
        entity.setIntroduction(rs.getString("INTRODUCTION"));
        entity.setFeature(rs.getString("FEATURE"));
        entity.setIsRecommend(YesNoType.get(rs.getInt("ISRECOMMEND")));
        entity.setDisplayNo(rs.getInt("DISPLAY_ORDER"));
        entity.setCreateTime(rs.getTimestamp("CREATETIME"));
        return entity;
    }

}
