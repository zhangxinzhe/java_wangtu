/*
 * @(#)AgencyRowMapper.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.agency.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.agency.Agency;
import net.zdsoft.common.enums.AgencyType;
import net.zdsoft.common.enums.StatusEunm;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.common.utils.Util;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午10:37:28 $
 */
@SuppressWarnings("rawtypes")
public class AgencyRowMapper implements RowMapper {
    private static AgencyRowMapper rowMapper = new AgencyRowMapper();
    public static AgencyBaseRowMapper baseMapper = new AgencyBaseRowMapper();

    public static AgencyRowMapper instance() {
        return rowMapper;
    }

    static class AgencyBaseRowMapper implements RowMapper {
        @Override
        public Agency mapRow(ResultSet rs, int i) throws SQLException {
            Agency entity = rowMapper.mapRow(rs, i);
            entity.setUsername(rs.getString("USERNAME"));
            entity.setPassword(Util.decodePassword(rs.getString("PASSWORD")));
            return entity;
        }
    }

    @Override
    public Agency mapRow(ResultSet rs, int arg1) throws SQLException {
        Agency entity = new Agency();
        entity.setId(rs.getLong("ID"));
        entity.setAgencyName(rs.getString("AGENCY_NAME"));
        entity.setRegionCode(rs.getString("REGIONCODE"));
        entity.setContactMan(rs.getString("CONTACT_MAN"));
        entity.setContactPhone(rs.getString("CONTACT_PHONE"));
        entity.setEmail(rs.getString("EMAIL"));
        entity.setAddress(rs.getString("ADDRESS"));
        entity.setIntroduction(rs.getString("INTRODUCTION"));
        entity.setAgencyType(AgencyType.get(rs.getInt("AGENCY_TYPE")));
        entity.setIsCancel(StatusEunm.get(rs.getInt("IS_CANCEL")));
        entity.setAgencySource(rs.getInt("AGENCY_SOURCE"));
        entity.setRanking(rs.getInt("RANKING"));
        entity.setIsRecommend(YesNoType.get(rs.getInt("ISRECOMMEND")));
        entity.setRecommendTime(rs.getTimestamp("RECOMMENDTIME"));
        entity.setCreateTime(rs.getTimestamp("CREATETIME"));
        entity.setRemark(rs.getString("REMARK"));
        return entity;
    }

}
