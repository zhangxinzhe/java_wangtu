/*
 * @(#)AgencyRowMapper.java    Created on 2015年12月8日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.agency.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.agency.AgencyJoin;
import net.zdsoft.common.enums.AgencyJoinStatus;
import net.zdsoft.common.enums.AgencyType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年12月8日 下午5:04:04 $
 */
@SuppressWarnings("rawtypes")
public class AgencyJoinRowMapper implements RowMapper {
    private static AgencyJoinRowMapper rowMapper = new AgencyJoinRowMapper();

    public static AgencyJoinRowMapper instance() {
        return rowMapper;
    }

    @Override
    public AgencyJoin mapRow(ResultSet rs, int arg1) throws SQLException {
        AgencyJoin entity = new AgencyJoin();
        entity.setId(rs.getLong("ID"));
        entity.setAgencyName(rs.getString("AGENCY_NAME"));
        entity.setRegionCode(rs.getString("REGIONCODE"));
        entity.setContactMan(rs.getString("CONTACT_MAN"));
        entity.setContactPhone(rs.getString("CONTACT_PHONE"));
        entity.setEmail(rs.getString("EMAIL"));
        entity.setAddress(rs.getString("ADDRESS"));
        entity.setIntroduction(rs.getString("INTRODUCTION"));
        entity.setAgencyType(AgencyType.get(rs.getInt("AGENCY_TYPE")));
        entity.setAgencyStatus(AgencyJoinStatus.get(rs.getInt("AGENCY_STATUS")));
        entity.setCreateTime(rs.getTimestamp("CREATETIME"));
        entity.setRemark(rs.getString("REMARK"));
        return entity;
    }

}
