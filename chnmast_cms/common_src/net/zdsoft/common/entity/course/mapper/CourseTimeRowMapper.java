/*
 * @(#)CourseTimeRowMapper.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.course.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.course.CourseTime;
import net.zdsoft.common.enums.CourseContentType;
import net.zdsoft.common.enums.CourseStudyType;
import net.zdsoft.keel.jdbc.MapRowMapper;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午11:01:06 $
 */
@SuppressWarnings("rawtypes")
public class CourseTimeRowMapper implements RowMapper {
    private static CourseTimeRowMapper rowMapper = new CourseTimeRowMapper();
    public static CourseAndTimeRowMapper courseAndTimeRowMapper = new CourseAndTimeRowMapper();
    public static StuCourseAndTimeRowMapper stuCourseAndTimeRowMapper = new StuCourseAndTimeRowMapper();
    public static CourseTimeMapRowMapper mapRowMapper = new CourseTimeMapRowMapper();
    public static BegintimeRowMapper begintimeRowMapper = new BegintimeRowMapper();
    public static TimeRowMapper timeRowMapper = new TimeRowMapper();

    public static CourseTimeRowMapper instance() {
        return rowMapper;
    }

    @Override
    public CourseTime mapRow(ResultSet rs, int arg1) throws SQLException {
        CourseTime entity = new CourseTime();
        entity.setId(rs.getLong("ID"));
        entity.setCourseId(rs.getLong("COURSEID"));
        entity.setBeginTime(rs.getTimestamp("BEGINTIME"));
        entity.setEndTime(rs.getTimestamp("ENDTIME"));
        entity.setSeq(rs.getInt("SEQ"));
        entity.setTitle(rs.getString("TITLE"));
        return entity;
    }

    public static class CourseAndTimeRowMapper implements RowMapper {
        @Override
        public CourseTime mapRow(ResultSet rs, int i) throws SQLException {
            CourseTime entity = new CourseTime();
            entity.setId(rs.getLong("T.ID"));
            entity.setCourseId(rs.getLong("T.COURSEID"));
            entity.setBeginTime(rs.getTimestamp("T.BEGINTIME"));
            entity.setEndTime(rs.getTimestamp("T.ENDTIME"));
            entity.setSeq(rs.getInt("T.SEQ"));
            entity.setTitle(rs.getString("T.TITLE"));

            entity.setCourseName(rs.getString("C.COURSE_NAME"));
            entity.setTeaId(rs.getLong("C.TEA_ID"));
            entity.setAssId(rs.getLong("C.ASS_ID"));
            entity.setTeaRealName(rs.getString("C.TEA_REALNAME"));
            entity.setAssRealName(rs.getString("C.ASS_REALNAME"));
            entity.setPictureFile(rs.getString("C.PICTURE_FILE"));
            entity.setWxbId(rs.getLong("C.WXBID"));
            entity.setContentType(CourseContentType.get(rs.getInt("C.CONTENT_TYPE")));
            entity.setPlace(rs.getString("C.PLACE"));

            entity.setStudyType(CourseStudyType.get(rs.getInt("P.STUDY_TYPE")));
            return entity;
        }
    }

    static class StuCourseAndTimeRowMapper implements RowMapper {
        @Override
        public CourseTime mapRow(ResultSet rs, int i) throws SQLException {
            CourseTime entity = new CourseTime();
            entity.setId(rs.getLong("T.ID"));
            entity.setCourseId(rs.getLong("T.COURSEID"));
            entity.setBeginTime(rs.getTimestamp("T.BEGINTIME"));
            entity.setEndTime(rs.getTimestamp("T.ENDTIME"));
            entity.setSeq(rs.getInt("T.SEQ"));
            entity.setTitle(rs.getString("T.TITLE"));

            entity.setCourseName(rs.getString("C.COURSE_NAME"));
            entity.setTeaId(rs.getLong("C.TEA_ID"));
            entity.setAssId(rs.getLong("C.ASS_ID"));
            entity.setTeaRealName(rs.getString("C.TEA_REALNAME"));
            entity.setAssRealName(rs.getString("C.ASS_REALNAME"));
            entity.setPictureFile(rs.getString("C.PICTURE_FILE"));
            entity.setWxbId(rs.getLong("C.WXBID"));
            entity.setContentType(CourseContentType.get(rs.getInt("C.CONTENT_TYPE")));
            entity.setPlace(rs.getString("PLACE"));

            entity.setStudyType(CourseStudyType.get(rs.getInt("ORT.STUDY_TYPE")));
            return entity;
        }
    }

    static class CourseTimeMapRowMapper implements MapRowMapper<Long, CourseTime> {
        @Override
        public Long mapRowKey(ResultSet rs, int i) throws SQLException {
            return rs.getLong("ID");
        }

        @Override
        public CourseTime mapRowValue(ResultSet rs, int i) throws SQLException {
            return rowMapper.mapRow(rs, i);
        }
    }

    static class BegintimeRowMapper implements RowMapper<Date> {
        @Override
        public Date mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getDate("BEGINTIME");
        }
    }

    public static class TimeRowMapper implements RowMapper {
        @Override
        public CourseTime mapRow(ResultSet rs, int i) throws SQLException {
            CourseTime entity = new CourseTime();
            entity.setId(rs.getLong("T.ID"));
            entity.setCourseId(rs.getLong("T.COURSEID"));
            entity.setBeginTime(rs.getTimestamp("T.BEGINTIME"));
            entity.setEndTime(rs.getTimestamp("T.ENDTIME"));
            entity.setSeq(rs.getInt("T.SEQ"));
            entity.setTitle(rs.getString("T.TITLE"));

            entity.setCourseName(rs.getString("C.COURSE_NAME"));
            entity.setTeaId(rs.getLong("C.TEA_ID"));
            entity.setAssId(rs.getLong("C.ASS_ID"));
            entity.setTeaRealName(rs.getString("C.TEA_REALNAME"));
            entity.setAssRealName(rs.getString("C.ASS_REALNAME"));
            entity.setWxbId(rs.getLong("C.WXBID"));
            entity.setContentType(CourseContentType.get(rs.getInt("C.CONTENT_TYPE")));
            entity.setPlace(rs.getString("PLACE"));
            return entity;
        }
    }

}
