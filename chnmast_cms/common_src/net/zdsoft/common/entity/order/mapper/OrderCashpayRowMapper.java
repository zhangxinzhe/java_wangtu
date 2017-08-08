/*
 * @(#)OrderCashpayRowMapper.java    Created on 2016年2月29日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.order.OrderCashpay;

/**
 * @author wangly
 * @version $Revision: 1.0 $, $Date: 2016年2月29日 下午6:00:12 $
 */
@SuppressWarnings("rawtypes")
public class OrderCashpayRowMapper implements RowMapper {

    private static OrderCashpayRowMapper rowMapper = new OrderCashpayRowMapper();

    public static OrderCashpayRowMapper instance() {
        return rowMapper;
    }

    @Override
    public OrderCashpay mapRow(ResultSet rs, int i) throws SQLException {
        OrderCashpay order = new OrderCashpay();
        order.setId(rs.getLong("ID"));
        order.setOrderId(rs.getLong("ORDER_ID"));
        order.setOpeUserId(rs.getLong("OPE_USERID"));
        order.setOpeRealname(rs.getString("OPE_REALNAME"));
        order.setCreateTime(rs.getTimestamp("CREATE_TIME"));
        order.setCashRealname(rs.getString("CASH_REALNAME"));
        order.setCashTime(rs.getTimestamp("CASH_TIME"));
        order.setCashAmount(rs.getFloat("CASH_AMOUNT"));
        order.setChargeType(rs.getString("CHARGE_TYPE"));
        order.setRemark(rs.getString("REMARK"));
        return order;
    }
}
