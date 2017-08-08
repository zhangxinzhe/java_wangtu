/*
 * @(#)CommentMapper.java    Created on 2017年8月7日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.entity.wangtu.Comment;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年8月7日 上午9:52:46 $
 */
public class CommentMapper implements RowMapper<Comment> {

    private static CommentMapper mapper = new CommentMapper();

    public static CommentMapper instane() {
        return mapper;
    }

    @Override
    public Comment mapRow(ResultSet rs, int arg1) throws SQLException {
        Comment entity = new Comment();
        entity.setId(rs.getLong("id"));
        entity.setUserId(rs.getLong("user_id"));
        entity.setReviewerId(rs.getLong("reviewer_id"));
        entity.setContent(rs.getString("content"));
        entity.setServiceQuality(rs.getFloat("service_quility"));
        entity.setServiceQualityContent(rs.getString("service_quility_content"));
        entity.setServiceAttitude(rs.getFloat("service_attitude"));
        entity.setServiceAttitudeContent(rs.getString("service_attitude_content"));
        entity.setCommentTime(rs.getTimestamp("comment_time"));
        entity.setReplyTime(rs.getTimestamp("reply_time"));
        entity.setReplyContent(rs.getString("reply_content"));
        entity.setIsSatisfy(rs.getInt("is_satisfy"));
        entity.setRemark(rs.getString("remark"));
        entity.setUserName(rs.getString("username"));
        entity.setAvatarFile(rs.getString("AVATAR_FILE"));
        return entity;
    }

}
