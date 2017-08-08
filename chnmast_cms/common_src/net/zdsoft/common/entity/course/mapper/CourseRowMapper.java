/*
 * @(#)CourseRowMapper.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.course.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.course.Course;
import net.zdsoft.common.enums.AuditStatusType;
import net.zdsoft.common.enums.CourseContentType;
import net.zdsoft.common.enums.CourseProgressEunm;
import net.zdsoft.common.enums.CourseStatusEunm;
import net.zdsoft.common.enums.CourseType;
import net.zdsoft.common.enums.SourseType;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.keel.jdbc.MapRowMapper;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午10:47:30 $
 */
@SuppressWarnings("rawtypes")
public class CourseRowMapper implements RowMapper {
    private static CourseRowMapper rowMapper = new CourseRowMapper();
    public static CourseMapRowMapper mapRowMapper = new CourseMapRowMapper();

    public static CourseRowMapper instance() {
        return rowMapper;
    }

    @Override
    public Course mapRow(ResultSet rs, int arg1) throws SQLException {
        Course course = new Course();
        course.setId(rs.getLong("ID"));
        course.setCourseName(rs.getString("COURSE_NAME"));
        course.setIntroduction(rs.getString("INTRODUCTION"));
        course.setTeaId(rs.getLong("TEA_ID"));
        course.setTeaRealName(rs.getString("TEA_REALNAME"));
        course.setAssId(rs.getLong("ASS_ID"));
        course.setAssRealName(rs.getString("ASS_REALNAME"));
        course.setStudentId(rs.getLong("STUDENT_ID"));
        course.setCollegeId(rs.getLong("COLLEGE_ID"));
        course.setPictureFile(rs.getString("PICTURE_FILE"));
        course.setBookingTime(rs.getTimestamp("BOOKINGTIME"));
        course.setSaleTime(rs.getTimestamp("SALETIME"));
        course.setBeginTime(rs.getTimestamp("BEGINTIME"));
        course.setEndTime(rs.getTimestamp("ENDTIME"));
        course.setCourseOldcost(rs.getFloat("COURSE_OLDCOST"));
        course.setCourseNowcost(rs.getFloat("COURSE_NOWCOST"));
        course.setCourseNowcostVip(rs.getFloat("COURSE_NOWCOST_VIP"));
        course.setIsUseCoupon(YesNoType.get(rs.getInt("IS_USE_COUPON")));
        course.setIsBuyAll(YesNoType.get(rs.getInt("IS_BUY_ALL")));
        course.setPlace(rs.getString("PLACE"));
        course.setContentType(CourseContentType.get(rs.getInt("CONTENT_TYPE")));
        course.setCourseType(CourseType.get(rs.getInt("COURSE_TYPE")));
        course.setWxbId(rs.getLong("WXBID"));
        course.setStatus(CourseStatusEunm.get(rs.getInt("STATUS")));
        course.setProgress(CourseProgressEunm.get(rs.getInt("PROGRESS")));
        course.setIsRecommend(YesNoType.get(rs.getInt("ISRECOMMEND")));
        course.setRecommendTime(rs.getTimestamp("RECOMMENDTIME"));
        course.setRecommendSeq(rs.getInt("RECOMMENDSEQ"));
        course.setCreateTime(rs.getTimestamp("CREATETIME"));
        course.setModifyTime(rs.getTimestamp("MODIFYTIME"));
        course.setRemark(rs.getString("REMARK"));
        course.setCourseSource(SourseType.get(rs.getInt("COURSE_SOURCE")));
        course.setApplyDate(rs.getTimestamp("APPLY_DATE"));
        course.setApplyInfo(rs.getString("APPLY_INFO"));
        course.setAuditStatus(AuditStatusType.get(rs.getInt("AUDIT_STATUS")));
        course.setAuditDate(rs.getTimestamp("AUDIT_DATE"));
        course.setAuditRealName(rs.getString("AUDIT_REALNAME"));
        course.setAuditMsg(rs.getString("AUDIT_MSG"));
        return course;
    }

    static class CourseMapRowMapper implements MapRowMapper<Long, Course> {
        @Override
        public Long mapRowKey(ResultSet rs, int arg1) throws SQLException {
            return rs.getLong("ID");
        }

        @Override
        public Course mapRowValue(ResultSet rs, int arg1) throws SQLException {
            return rowMapper.mapRow(rs, arg1);
        }
    }

}
