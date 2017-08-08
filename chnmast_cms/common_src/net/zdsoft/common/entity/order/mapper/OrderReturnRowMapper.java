/*
 * @(#)OrderReturnRowMapper.java    Created on 2016年2月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.order.OrderReturn;
import net.zdsoft.common.enums.OrderReturnReasonType;
import net.zdsoft.common.enums.OrderReturnStatusType;
import net.zdsoft.common.enums.ReturnStatus;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年2月22日 下午4:10:09 $
 */
@SuppressWarnings("rawtypes")
public class OrderReturnRowMapper implements RowMapper {

    private static OrderReturnRowMapper rowMapper = new OrderReturnRowMapper();

    public static OrderReturnRowMapper instance() {
        return rowMapper;
    }

    @Override
    public OrderReturn mapRow(ResultSet rs, int i) throws SQLException {
        OrderReturn or = new OrderReturn();
        or.setId(rs.getLong("ID"));
        or.setOrderId(rs.getLong("ORDER_ID"));
        or.setDetailId(rs.getLong("DETAIL_ID"));
        or.setUserId(rs.getLong("USERID"));
        or.setRealName(rs.getString("REALNAME"));
        or.setCreateTime(rs.getTimestamp("CREATE_TIME"));
        or.setReason(OrderReturnReasonType.get(rs.getInt("REASON")));
        or.setReasonDes(rs.getString("REASON_DES"));
        or.setStatus(ReturnStatus.getStatus(rs.getInt("STATUS")));
        or.setStatusType(OrderReturnStatusType.get(rs.getInt("STATUS_TYPE")));
        or.setCourseUserId(rs.getLong("COURSE_USERID"));
        or.setCourseRealName(rs.getString("COURSE_REALNAME"));
        or.setCourseTime(rs.getTimestamp("COURSE_TIME"));
        or.setCourseRemark(rs.getString("COURSE_REMARK"));
        or.setMoneyAmount(rs.getFloat("MONEY_AMOUNT"));
        or.setMoneyUserId(rs.getLong("MONEY_USERID"));
        or.setMoneyRealName(rs.getString("MONEY_REALNAME"));
        or.setMoneyTime(rs.getTimestamp("MONEY_TIME"));
        or.setMoneyRemark(rs.getString("MONEY_REMARK"));
        return or;
    }

}
