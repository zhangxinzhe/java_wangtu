/*
 * @(#)CouponCourseRowMapper.java    Created on 2016年7月14日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.coupon.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.coupon.CouponCourse;
import net.zdsoft.common.enums.CourseContentType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年7月14日 下午1:57:17 $
 */
@SuppressWarnings("rawtypes")
public class CouponCourseRowMapper implements RowMapper {

    private static CouponCourseRowMapper rowMapper = new CouponCourseRowMapper();
    public static CouponBindCourseInfo couponBindCourseInfo = new CouponBindCourseInfo();

    public static CouponCourseRowMapper instance() {
        return rowMapper;
    }

    @Override
    public CouponCourse mapRow(ResultSet rs, int i) throws SQLException {
        CouponCourse c = new CouponCourse();
        c.setId(rs.getLong("ID"));
        c.setCouponId(rs.getLong("COUPON_ID"));
        c.setCourseId(rs.getLong("COURSE_ID"));
        return c;
    }

    static class CouponBindCourseInfo implements RowMapper<CouponCourse> {
        @Override
        public CouponCourse mapRow(ResultSet rs, int i) throws SQLException {
            CouponCourse c = rowMapper.mapRow(rs, i);
            c.setCourseName(rs.getString("C.COURSE_NAME"));
            c.setContentType(CourseContentType.get(rs.getInt("C.CONTENT_TYPE")));
            return c;
        }
    }

}
