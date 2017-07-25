/*
 * @(#)CatalogMapper.java    Created on 2017年7月5日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.entity.wangtu.Catalog;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月5日 上午11:33:12 $
 */
public class CatalogMapper implements RowMapper<Catalog> {

    private static CatalogMapper rowMapper = new CatalogMapper();

    public static CatalogMapper instance() {
        return rowMapper;
    }

    @Override
    public Catalog mapRow(ResultSet rs, int arg1) throws SQLException {
        Catalog entity = new Catalog();
        entity.setId(rs.getLong("id"));
        entity.setCname(rs.getString("CATANAME"));
        entity.setIsDelete(YesNoType.get(rs.getInt("is_delete")));
        return entity;
    }

}
