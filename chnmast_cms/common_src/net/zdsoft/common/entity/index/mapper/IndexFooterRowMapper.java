/*
 * @(#)IndexFooterRowMapper.java    Created on 2016年11月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.index.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.index.IndexFooter;
import net.zdsoft.common.enums.ContentType;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author mengzs
 * @version $Revision: 1.0 $, $Date: 2016年11月25日 上午11:44:26 $
 */
public class IndexFooterRowMapper implements RowMapper {

    public static IndexFooterRowMapper indexFooterRowMapper = new IndexFooterRowMapper();

    public static IndexFooterRowMapper instance() {
        return indexFooterRowMapper;
    }

    @Override
    public IndexFooter mapRow(ResultSet rs, int num) throws SQLException {
        IndexFooter footer = new IndexFooter();
        footer.setId(rs.getLong("ID"));
        footer.setTitle(rs.getString("TITLE"));
        footer.setContentType(ContentType.get(rs.getInt("CONTENT_TYPE")));
        footer.setLinkUrl(rs.getString("LINK_URL"));
        footer.setLocalFile(rs.getString("LOCAL_FILE"));
        footer.setIsShow(YesNoType.get(rs.getInt("IS_SHOW")));
        footer.setDisplayOrder(rs.getInt("DISPLAY_ORDER"));
        return footer;
    }

}
