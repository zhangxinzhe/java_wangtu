/*
 * @(#)CompeteCommitteeRowMapper.java    Created on 2016年4月9日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.compete.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.compete.CompeteCommittee;
import net.zdsoft.common.enums.SexType;
import net.zdsoft.common.enums.TitleType;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年4月9日 下午1:46:17 $
 */
@SuppressWarnings("rawtypes")
public class CompeteCommitteeRowMapper implements RowMapper {

    private static CompeteCommitteeRowMapper rowMapper = new CompeteCommitteeRowMapper();

    public static CompeteCommitteeRowMapper instance() {
        return rowMapper;
    }

    @Override
    public CompeteCommittee mapRow(ResultSet rs, int i) throws SQLException {
        CompeteCommittee entity = new CompeteCommittee();
        entity.setId(rs.getLong("ID"));
        entity.setCompeteId(rs.getLong("COMPETE_ID"));
        entity.setRealName(rs.getString("REALNAME"));
        entity.setSex(SexType.get(rs.getInt("SEX")));
        entity.setUnitName(rs.getString("UNIT_NAME"));
        entity.setTitle(TitleType.get(rs.getInt("TITLE")));
        entity.setJobTitle(rs.getString("JOB_TITLE"));
        entity.setIntroduction(rs.getString("INTRODUCTION"));
        entity.setPhotoFile(rs.getString("PHOTO_FILE"));
        entity.setOrderNo(rs.getInt("ORDER_NO"));
        entity.setRemark(rs.getString("REMARK"));
        return entity;
    }

}
