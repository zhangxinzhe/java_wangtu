/*
 * @(#)OrderVerifyCodeRowMapper.java    Created on 2016年3月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.order.OrderVerifyCode;
import net.zdsoft.common.enums.CodeUseState;
import net.zdsoft.common.enums.CourseContentType;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年3月25日 下午2:22:33 $
 */
public class OrderVerifyCodeRowMapper implements RowMapper<OrderVerifyCode> {

    private static OrderVerifyCodeRowMapper rowMapper = new OrderVerifyCodeRowMapper();
    public static DetailOrderVerifyCode detailRowMapper = new DetailOrderVerifyCode();

    public static OrderVerifyCodeRowMapper instance() {
        return rowMapper;
    }

    @Override
    public OrderVerifyCode mapRow(ResultSet rs, int index) throws SQLException {
        OrderVerifyCode code = new OrderVerifyCode();
        code.setId(rs.getLong("ID"));
        code.setOrderId(rs.getLong("ORDER_ID"));
        code.setOrderReportId(rs.getLong("ORDER_REPORT_ID"));
        code.setCourseId(rs.getLong("COURSE_ID"));
        code.setCourseTimeId(rs.getLong("COURSE_TIMEID"));
        code.setContentType(CourseContentType.get(rs.getInt("COURSE_CONTENT_TYPE")));
        code.setVerifyCode(rs.getLong("VERIFY_CODE"));
        code.setUseState(CodeUseState.getCodeUseState(rs.getInt("IS_USED")));
        code.setUseDate(rs.getTimestamp("USED_DATE"));
        code.setRemark(rs.getString("REMARK"));
        return code;
    }

    static class DetailOrderVerifyCode implements RowMapper<OrderVerifyCode> {

        @Override
        public OrderVerifyCode mapRow(ResultSet rs, int index) throws SQLException {
            OrderVerifyCode code = rowMapper.mapRow(rs, index);
            code.setCourseName(rs.getString("COURSE_NAME"));
            code.setStartTime(rs.getTimestamp("BEGINTIME"));
            code.setEndTime(rs.getTimestamp("ENDTIME"));
            code.setSeq(rs.getInt("SEQ"));
            code.setTheme(rs.getString("TITLE"));
            code.setPhone(rs.getString("PHONE"));
            code.setRealName(rs.getString("REALNAME"));
            code.setOrderNo(rs.getString("TRADE_NO"));
            return code;
        }

    }
}
