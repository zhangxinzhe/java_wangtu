/*
 * @(#)OrderReportRowMapper.java    Created on 2015年12月22日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.order.OrderReport;
import net.zdsoft.common.enums.CourseContentType;
import net.zdsoft.common.enums.CourseStudyType;
import net.zdsoft.common.enums.OrderReportReturnStatus;
import net.zdsoft.common.enums.OrderType;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月22日 上午10:42:36 $
 */
@SuppressWarnings("rawtypes")
public class OrderReportRowMapper implements RowMapper {

    private static OrderReportRowMapper rowMapper = new OrderReportRowMapper();

    public static OrderReportStatRowMapper statRowMapper = new OrderReportStatRowMapper();

    public static OrderReportRowMapper instance() {
        return rowMapper;
    }

    @Override
    public OrderReport mapRow(ResultSet rs, int index) throws SQLException {
        OrderReport or = new OrderReport();
        or.setId(rs.getLong("ID"));
        or.setOrderId(rs.getLong("ORDER_ID"));
        or.setDetailId(rs.getLong("DETAIL_ID"));
        or.setUserId(rs.getLong("USER_ID"));
        or.setRealName(rs.getString("REALNAME"));
        or.setCourseId(rs.getLong("COURSEID"));
        or.setCourseTimeId(rs.getLong("COURSE_TIMEID"));
        or.setCoursePriceId(rs.getLong("COURSE_PRICEID"));
        or.setStudyType(CourseStudyType.get(rs.getInt("STUDY_TYPE")));
        or.setCourseContentType(CourseContentType.get(rs.getInt("COURSE_CONTENT_TYPE")));
        or.setReportTime(rs.getTimestamp("REPORTTIME"));
        or.setReportType(OrderType.getOrderType(rs.getInt("REPORTTYPE")));
        or.setPayableAmount(rs.getFloat("PAYABLE_AMOUNT"));
        or.setActualAmount(rs.getFloat("ACTUAL_AMOUNT"));
        or.setIsReturnCourse(OrderReportReturnStatus.get(rs.getInt("IS_RETURN_COURSE")));
        or.setIsReturnMoney(OrderReportReturnStatus.get(rs.getInt("IS_RETURN_MONEY")));
        return or;
    }

    static class OrderReportStatRowMapper implements RowMapper {

        @Override
        public OrderReport mapRow(ResultSet rs, int index) throws SQLException {
            OrderReport or = rowMapper.mapRow(rs, index);
            or.setUserName(rs.getString("USERNAME"));
            or.setCourseName(rs.getString("COURSE_NAME"));
            or.setCourseSeq(rs.getInt("SEQ"));
            or.setCourseBeginTime(rs.getTimestamp("BEGINTIME"));
            or.setCourseEndTime(rs.getTimestamp("ENDTIME"));
            return or;
        }

    }
}
