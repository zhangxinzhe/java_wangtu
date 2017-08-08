/*
 * @(#)IndexStudentRowMapper.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.index.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.index.IndexStudent;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 下午12:55:30 $
 */
@SuppressWarnings("rawtypes")
public class IndexStudentRowMapper implements RowMapper {
    private static IndexStudentRowMapper rowMapper = new IndexStudentRowMapper();

    public static IndexStudentRowMapper instance() {
        return rowMapper;
    }

    @Override
    public IndexStudent mapRow(ResultSet rs, int arg1) throws SQLException {
        IndexStudent entity = new IndexStudent();
        entity.setId(rs.getLong("ID"));
        entity.setStuName(rs.getString("STU_NAME"));
        entity.setPhotoFile(rs.getString("PHOTO_FILE"));
        entity.setUnitName(rs.getString("UNIT_NAME"));
        entity.setIntroduction(rs.getString("INTRODUCTION"));
        entity.setIsShow(YesNoType.get(rs.getInt("IS_SHOW")));
        entity.setOrderNo(rs.getInt("ORDER_NO"));
        entity.setCreateTime(rs.getTimestamp("CREATETIME"));
        return entity;
    }

}
