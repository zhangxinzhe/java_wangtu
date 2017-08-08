/*
 * @(#)CourseCommentRowMapper.java    Created on 2016年6月27日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.course.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.course.CourseComment;
import net.zdsoft.common.enums.AuditStatusType;
import net.zdsoft.common.enums.CommentType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年6月27日 下午1:56:23 $
 */
@SuppressWarnings("rawtypes")
public class CourseCommentRowMapper implements RowMapper {

    public static CourseCommentRowMapper rowMapper = new CourseCommentRowMapper();

    public static VideoCommentRowMapper videoRowMapper = new VideoCommentRowMapper();

    public static CourseCommentRowMapper instance() {
        return rowMapper;
    }

    @Override
    public CourseComment mapRow(ResultSet rs, int arg1) throws SQLException {
        CourseComment entity = new CourseComment();
        entity.setId(rs.getLong("ID"));
        entity.setCourseId(rs.getLong("COURSEID"));
        entity.setTeaId(rs.getLong("TEA_ID"));
        entity.setObsId(rs.getLong("OBS_ID"));
        entity.setObsName(rs.getString("OBS_NAME"));
        entity.setIp(rs.getString("IP"));
        entity.setScore(rs.getInt("SCORE"));
        entity.setContent(rs.getString("CONTENT"));
        entity.setCommentTime(rs.getTimestamp("COMMENTTIME"));
        entity.setAuditStatus(AuditStatusType.get(rs.getInt("AUDIT_STATUS")));
        entity.setAuditDate(rs.getTimestamp("AUDIT_DATE"));
        entity.setAuditRealName(rs.getString("AUDIT_REALNAME"));
        entity.setCommentType(CommentType.getValue(rs.getInt("COMMENT_TYPE")));
        return entity;
    }

    public static class VideoCommentRowMapper implements RowMapper {

        @Override
        public CourseComment mapRow(ResultSet rs, int arg1) throws SQLException {
            CourseComment entity = new CourseComment();
            entity.setId(rs.getLong("ID"));
            entity.setCourseId(rs.getLong("COURSEID"));
            entity.setTeaId(rs.getLong("TEA_ID"));
            entity.setObsId(rs.getLong("OBS_ID"));
            entity.setObsName(rs.getString("OBS_NAME"));
            entity.setIp(rs.getString("IP"));
            entity.setScore(rs.getInt("SCORE"));
            entity.setContent(rs.getString("CONTENT"));
            entity.setCommentTime(rs.getTimestamp("COMMENTTIME"));
            entity.setAuditStatus(AuditStatusType.get(rs.getInt("AUDIT_STATUS")));
            entity.setAuditDate(rs.getTimestamp("AUDIT_DATE"));
            entity.setAuditRealName(rs.getString("AUDIT_REALNAME"));
            entity.setCommentType(CommentType.getValue(rs.getInt("COMMENT_TYPE")));
            entity.setCourseName(rs.getString("title"));
            return entity;
        }

    }

}
