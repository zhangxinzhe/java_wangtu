/*
 * @(#)OrderMapper.java    Created on 2017年7月25日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.entity.wangtu.Order;
import net.zdsoft.chnmaster.enums.wangtu.OrderType;
import net.zdsoft.common.enums.OrderStatus;
import net.zdsoft.common.enums.PayType;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月25日 下午1:55:30 $
 */
public class OrderMapper implements RowMapper<Order> {

    private static OrderMapper mapper = new OrderMapper();

    public static OrderMapper instance() {
        return mapper;
    }

    @Override
    public Order mapRow(ResultSet rs, int arg1) throws SQLException {
        Order entity = new Order();
        entity.setId(rs.getLong("ID"));
        entity.setTradeNo(rs.getString("TRADE_NO"));
        entity.setRelationId(rs.getLong("RELATION_ID"));
        entity.setUserId(rs.getLong("USER_ID"));
        entity.setPayAmount(rs.getDouble("PAY_AMOUNT"));
        entity.setCreationTime(rs.getTimestamp("CREATION_TIME"));
        entity.setPaymentTime(rs.getTimestamp("PAYMENT_TIME"));
        entity.setStatus(OrderStatus.getOrderStatus(rs.getInt("ORDER_STATUS")));
        entity.setPayType(PayType.getPayType(rs.getInt("PAY_TYPE")));
        entity.setOrderType(OrderType.getOrderType(rs.getInt("ORDER_TYPE")));
        entity.setRemark(rs.getString("REMARK"));

        return entity;
    }

    static class FoundsOrderMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet arg0, int arg1) throws SQLException {
            // TODO Auto-generated method stub
            return null;
        }

    }

}
