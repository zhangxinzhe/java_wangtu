/*
 * @(#)CompeteVideoRowMapper.java    Created on 2016年8月16日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.compete.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.compete.CompeteVideo;
import net.zdsoft.common.enums.CompeteType;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年8月16日 下午3:21:02 $
 */
@SuppressWarnings("rawtypes")
public class CompeteVideoRowMapper implements RowMapper {

    private static CompeteVideoRowMapper rowMapper = new CompeteVideoRowMapper();

    public static CompeteVideoRowMapper instance() {
        return rowMapper;
    }

    @Override
    public CompeteVideo mapRow(ResultSet rs, int i) throws SQLException {
        CompeteVideo entity = new CompeteVideo();
        entity.setId(rs.getLong("ID"));
        entity.setName(rs.getString("NAME"));
        entity.setCompeteId(rs.getLong("COMPETEID"));
        entity.setCompeteType(CompeteType.get(rs.getInt("COMPETE_TYPE")));
        entity.setPicFile(rs.getString("PIC_FILE"));
        entity.setVideoFile(rs.getString("VIDEO_FILE"));
        entity.setFileName(rs.getString("FILE_NAME"));
        entity.setFileExt(rs.getString("FILE_EXT"));
        entity.setFileSize(rs.getLong("FILE_SIZE"));
        entity.setVideoDuration(rs.getFloat("VIDEO_DURATION"));
        entity.setIsShow(YesNoType.get(rs.getInt("IS_SHOW")));
        entity.setOrderNo(rs.getObject("ORDER_NO") == null ? null : rs.getInt("ORDER_NO"));
        entity.setCreateTime(rs.getTimestamp("CREATETIME"));
        entity.setRemark(rs.getString("REMARK"));
        return entity;
    }

}
