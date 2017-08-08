/*
 * @(#)OrderAlipayLogRowMapper.java    Created on 2016年2月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.order.OrderAlipayLog;

/**
 * @author xiongwq
 * @version $Revision: 1.0 $, $Date: 2016年2月22日 下午5:23:56 $
 */
@SuppressWarnings("rawtypes")
public class OrderAlipayLogRowMapper implements RowMapper {
    private static OrderAlipayLogRowMapper rowMapper = new OrderAlipayLogRowMapper();

    public static OrderAlipayLogRowMapper instance() {
        return rowMapper;
    }

    @Override
    public OrderAlipayLog mapRow(ResultSet rs, int i) throws SQLException {
        OrderAlipayLog oal = new OrderAlipayLog();
        oal.setId(rs.getLong("ID"));
        oal.setTradeNo(rs.getString("TRADE_NO"));
        oal.setLogContent(rs.getString("LOG_CONTENT"));
        oal.setCreationTime(rs.getDate("CREATION_TIME"));
        oal.setLogType(rs.getInt("LOG_TYPE"));
        return oal;
    }

}
