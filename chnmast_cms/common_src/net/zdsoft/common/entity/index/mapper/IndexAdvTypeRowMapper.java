/*
 * @(#)IndexAdvTypeRowMapper.java    Created on 2015年12月28日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.index.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.index.IndexAdvType;
import net.zdsoft.common.enums.FieldType;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月28日 下午2:29:55 $
 */
@SuppressWarnings("rawtypes")
public class IndexAdvTypeRowMapper implements RowMapper {

    private static IndexAdvTypeRowMapper rowMapper = new IndexAdvTypeRowMapper();

    public static IndexAdvTypeRowMapper instance() {
        return rowMapper;
    }

    @Override
    public IndexAdvType mapRow(ResultSet rs, int i) throws SQLException {
        IndexAdvType t = new IndexAdvType();
        t.setId(rs.getLong("ID"));
        t.setTitle(rs.getString("TITLE"));
        t.setFieldType(FieldType.get(rs.getInt("FIELD_TYPE")));
        return t;
    }

}
