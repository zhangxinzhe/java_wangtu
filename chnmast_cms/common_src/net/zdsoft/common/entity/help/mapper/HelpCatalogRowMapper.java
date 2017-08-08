/*
 * @(#)HelpCatalogRowMapper.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.help.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.help.HelpCatalog;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午11:50:55 $
 */
@SuppressWarnings("rawtypes")
public class HelpCatalogRowMapper implements RowMapper {
    private static HelpCatalogRowMapper rowMapper = new HelpCatalogRowMapper();

    public static HelpCatalogRowMapper instance() {
        return rowMapper;
    }

    @Override
    public HelpCatalog mapRow(ResultSet rs, int arg1) throws SQLException {
        HelpCatalog entity = new HelpCatalog();
        entity.setId(rs.getLong("ID"));
        entity.setCatalogName(rs.getString("CATALOGNAME"));
        entity.setParentId(rs.getLong("PARENTID"));
        entity.setDisplayOrder(rs.getInt("DISPLAYORDER"));
        entity.setIsShow(YesNoType.get(rs.getInt("IS_SHOW")));
        return entity;
    }

}
