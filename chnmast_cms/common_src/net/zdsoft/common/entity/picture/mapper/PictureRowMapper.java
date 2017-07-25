/*
 * @(#)PictureRowMapper.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.picture.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.picture.Picture;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.keel.jdbc.MapRowMapper;
import net.zdsoft.keel.jdbc.MultiRowMapper;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 下午12:59:44 $
 */
@SuppressWarnings("rawtypes")
public class PictureRowMapper implements RowMapper {
    private static PictureRowMapper rowMapper = new PictureRowMapper();
    public static PictureMultiRowMapper multiRowMapper = new PictureMultiRowMapper();
    public static PictureMapRowMapper mapRowMapper = new PictureMapRowMapper();

    public static PictureRowMapper instance() {
        return rowMapper;
    }

    static class PictureMultiRowMapper implements MultiRowMapper<Picture> {
        @Override
        public Picture mapRow(ResultSet rs, int i) throws SQLException {
            return rowMapper.mapRow(rs, i);
        }

    }

    static class PictureMapRowMapper implements MapRowMapper<Long, Picture> {

        @Override
        public Long mapRowKey(ResultSet rs, int i) throws SQLException {
            return rs.getLong("OBJECT_ID");
        }

        @Override
        public Picture mapRowValue(ResultSet rs, int i) throws SQLException {
            return rowMapper.mapRow(rs, i);
        }

    }

    @Override
    public Picture mapRow(ResultSet rs, int arg1) throws SQLException {
        Picture pic = new Picture();
        pic.setId(rs.getLong("ID"));
        pic.setObjectId(rs.getLong("OBJECT_ID"));
        pic.setObjectType(rs.getInt("OBJECT_TYPE"));
        pic.setPictureName(rs.getString("PICTURE_NAME"));
        pic.setPictureFile(rs.getString("PICTURE_FILE"));
        pic.setVideoFile(rs.getString("VIDEO_FILE"));
        pic.setFileType(rs.getInt("FILE_TYPE"));
        pic.setFileSize(rs.getLong("FILE_SIZE"));
        pic.setFileExt(rs.getString("FILE_EXT"));
        pic.setIsShow(YesNoType.get(rs.getInt("IS_SHOW")));
        pic.setShowOrder(rs.getInt("SHOW_ORDER"));
        pic.setCreateTime(rs.getTimestamp("CREATETIME"));
        return pic;
    }

}
