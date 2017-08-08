/*
 * @(#)IndexInfoRowMapper.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.index.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.index.IndexInfo;
import net.zdsoft.common.enums.ContentType;
import net.zdsoft.common.enums.InfoType;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 下午12:49:13 $
 */
@SuppressWarnings("rawtypes")
public class IndexInfoRowMapper implements RowMapper {
    private static IndexInfoRowMapper rowMapper = new IndexInfoRowMapper();

    public static IndexInfoRowMapper instance() {
        return rowMapper;
    }

    @Override
    public IndexInfo mapRow(ResultSet rs, int arg1) throws SQLException {
        IndexInfo entity = new IndexInfo();
        entity.setId(rs.getLong("ID"));
        entity.setTitle(rs.getString("TITLE"));
        entity.setInfoType(InfoType.getValue(rs.getInt("INFO_TYPE")));
        entity.setInfoShort(rs.getString("INFO_SHORT"));
        entity.setContentType(ContentType.get(rs.getInt("CONTENT_TYPE")));
        entity.setLinkUrl(rs.getString("LINK_URL"));
        entity.setLocalFile(rs.getString("LOCAL_FILE"));
        entity.setIsShow(YesNoType.get(rs.getInt("IS_SHOW")));
        entity.setIsLight(YesNoType.get(rs.getInt("IS_LIGHT")));
        entity.setNewDays(rs.getInt("NEW_DAYS"));
        entity.setOrderNo(rs.getInt("ORDER_NO"));
        entity.setComeFrom(rs.getString("COME_FROM"));
        entity.setComeUrl(rs.getString("COME_URL"));
        entity.setInfoDate(rs.getTimestamp("INFO_DATE"));
        entity.setEditor(rs.getString("EDITOR"));
        entity.setCreateDate(rs.getTimestamp("CREATE_DATE"));
        entity.setIsUrgent(YesNoType.get(rs.getInt("IS_URGENT")));
        String sPic = rs.getString("SHARE_PIC_FILE");
        entity.setSharePicFile(sPic);
        return entity;
    }
}
