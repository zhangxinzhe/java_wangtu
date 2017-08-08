/*
 * @(#)CommentPictureMapper.java    Created on 2017年8月7日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.entity.wangtu.CommentPicture;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年8月7日 上午10:03:30 $
 */
public class CommentPictureMapper implements RowMapper<CommentPicture> {

    private static CommentPictureMapper mapper = new CommentPictureMapper();

    public static CommentPictureMapper instance() {
        return mapper;
    }

    @Override
    public CommentPicture mapRow(ResultSet rs, int arg1) throws SQLException {
        CommentPicture entity = new CommentPicture();
        entity.setId(rs.getLong("id"));
        entity.setCommentId(rs.getLong("comment_id"));
        entity.setPicturePath(rs.getString("picture_path"));
        return entity;
    }

}
