/*
 * @(#)CoursePriceRowMapper.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.course.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.course.CoursePrice;
import net.zdsoft.common.enums.CoursePriceType;
import net.zdsoft.common.enums.CourseStudyType;
import net.zdsoft.keel.jdbc.MapRowMapper;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午10:57:40 $
 */
@SuppressWarnings("rawtypes")
public class CoursePriceRowMapper implements RowMapper {
    private static CoursePriceRowMapper rowMapper = new CoursePriceRowMapper();
    public static CoursePriceMapRowMapper mapRowMapper = new CoursePriceMapRowMapper();
    public static CoursePriceWithTimeRowMapper timeRowMapper = new CoursePriceWithTimeRowMapper();

    public static CoursePriceRowMapper instance() {
        return rowMapper;
    }

    static class CoursePriceMapRowMapper implements MapRowMapper<Long, CoursePrice> {
        @Override
        public Long mapRowKey(ResultSet rs, int i) throws SQLException {
            return rs.getLong("ID");
        }

        @Override
        public CoursePrice mapRowValue(ResultSet rs, int i) throws SQLException {
            return rowMapper.mapRow(rs, i);
        }
    }

    static class CoursePriceWithTimeRowMapper implements RowMapper {

        @Override
        public CoursePrice mapRow(ResultSet rs, int arg1) throws SQLException {
            CoursePrice entity = new CoursePrice();
            entity.setId(rs.getLong("ID"));
            entity.setCourseId(rs.getLong("COURSEID"));
            entity.setCourseTimeId(rs.getLong("COURSE_TIMEID"));
            entity.setOldPrice(rs.getFloat("OLDPRICE"));
            entity.setNowPrice(rs.getFloat("NOWPRICE"));
            entity.setNowPriceVip(rs.getFloat("NOWPRICE_VIP"));
            entity.setSeq(rs.getInt("SEQ"));
            entity.setNum(rs.getInt("NUM"));
            entity.setPainNum(rs.getInt("PAIN_NUM"));
            entity.setRemainNum(rs.getInt("REMAIN_NUM"));
            entity.setStudyType(CourseStudyType.get(rs.getInt("STUDY_TYPE")));
            entity.setPriceType(CoursePriceType.get(rs.getInt("PRICE_TYPE")));
            entity.setRemark(rs.getString("REMARK"));
            entity.setBeginTime(rs.getTimestamp("BEGINTIME"));
            entity.setTitle(rs.getString("TITLE"));
            return entity;
        }

    }

    @Override
    public CoursePrice mapRow(ResultSet rs, int arg1) throws SQLException {
        CoursePrice entity = new CoursePrice();
        entity.setId(rs.getLong("ID"));
        entity.setCourseId(rs.getLong("COURSEID"));
        entity.setCourseTimeId(rs.getLong("COURSE_TIMEID"));
        entity.setOldPrice(rs.getFloat("OLDPRICE"));
        entity.setNowPrice(rs.getFloat("NOWPRICE"));
        entity.setNowPriceVip(rs.getFloat("NOWPRICE_VIP"));
        entity.setSeq(rs.getInt("SEQ"));
        entity.setNum(rs.getInt("NUM"));
        entity.setPainNum(rs.getInt("PAIN_NUM"));
        entity.setRemainNum(rs.getInt("REMAIN_NUM"));
        entity.setStudyType(CourseStudyType.get(rs.getInt("STUDY_TYPE")));
        entity.setPriceType(CoursePriceType.get(rs.getInt("PRICE_TYPE")));
        entity.setRemark(rs.getString("REMARK"));
        return entity;
    }

}
