/*
 * @(#)SystemModuleRowMapper.java    Created on 2014-6-27
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.system.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.config.Config;
import net.zdsoft.chnmaster.entity.system.SystemModule;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.enums.YesNoType;

@SuppressWarnings("rawtypes")
public class SystemModuleRowMapper implements RowMapper {
    private static final SystemModuleRowMapper mapper = new SystemModuleRowMapper();

    public static SystemModuleRowMapper instance() {
        return mapper;
    }

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        SystemModule dto = new SystemModule();
        dto.setId(rs.getLong("ID"));
        dto.setAppId(rs.getLong("APP_ID"));
        dto.setName(rs.getString("NAME"));
        dto.setUrl(rs.getString("URL"));
        dto.setFullUrl(Config.getParam(BaseConstants.DOMAIN_CMS) + rs.getString("URL"));
        dto.setDescription(rs.getString("DESCRIPTION"));
        dto.setIsShow(YesNoType.get(rs.getInt("ISSHOW")));
        dto.setParentId(rs.getLong("PARENTID"));
        dto.setIsCommon(YesNoType.get(rs.getInt("ISCOMMON")));
        dto.setIsOperate(YesNoType.get(rs.getInt("ISOPERATE")));
        dto.setSeq(rs.getLong("SEQ"));
        return dto;
    }
}
