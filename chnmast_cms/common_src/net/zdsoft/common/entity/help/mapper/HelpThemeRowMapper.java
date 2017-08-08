/*
 * @(#)HelpThemeRowMapper.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.help.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.help.HelpTheme;
import net.zdsoft.common.enums.ContentType;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午11:54:04 $
 */
@SuppressWarnings("rawtypes")
public class HelpThemeRowMapper implements RowMapper {
    private static HelpThemeRowMapper rowMapper = new HelpThemeRowMapper();

    public static HelpThemeRowMapper instance() {
        return rowMapper;
    }

    @Override
    public HelpTheme mapRow(ResultSet rs, int arg1) throws SQLException {
        HelpTheme entity = new HelpTheme();
        entity.setId(rs.getLong("ID"));
        entity.setCataLogId(rs.getLong("CATALOG_ID"));
        entity.setThemeName(rs.getString("THEMENAME"));
        entity.setDisplayOrder(rs.getInt("DISPLAYORDER"));
        entity.setLinkType(ContentType.get(rs.getInt("LINKTYPE")));
        entity.setLinkUrl(rs.getString("LINKURL"));
        entity.setContentFile(rs.getString("CONTENTFILE"));
        entity.setIsShow(YesNoType.get(rs.getInt("IS_SHOW")));
        return entity;
    }

}
