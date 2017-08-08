/*
 * @(#)OrderRowMapper.java    Created on 2015年12月23日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.order.Order;
import net.zdsoft.common.enums.CourseBuyType;
import net.zdsoft.common.enums.DeviceType;
import net.zdsoft.common.enums.HifiCheckStatus;
import net.zdsoft.common.enums.OrderCheckStatus;
import net.zdsoft.common.enums.OrderKind;
import net.zdsoft.common.enums.OrderStatus;
import net.zdsoft.common.enums.OrderStatusType;
import net.zdsoft.common.enums.OrderType;
import net.zdsoft.common.enums.PayType;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月23日 下午3:44:17 $
 */
@SuppressWarnings("rawtypes")
public class OrderRowMapper implements RowMapper {

    private static OrderRowMapper rowMapper = new OrderRowMapper();
    public static OrderDealQueryMapper orderDealQueryMapper = new OrderDealQueryMapper();

    public static OrderRowMapper instance() {
        return rowMapper;
    }

    static class OrderDealQueryMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            Order or = rowMapper.mapRow(rs, i);
            or.setRealname(rs.getString("REALNAME"));
            or.setUsername(rs.getString("USERNAME"));
            return or;
        }

    }

    @Override
    public Order mapRow(ResultSet rs, int i) throws SQLException {
        Order or = new Order();
        or.setId(rs.getLong("ID"));
        or.setTradeNo(rs.getString("TRADE_NO"));
        or.setUserId(rs.getLong("USER_ID"));
        or.setTotalAmount(rs.getFloat("TOTAL_AMOUNT"));
        or.setAnnulAmount(rs.getFloat("ANNUL_AMOUNT"));
        or.setPayAmount(rs.getFloat("PAY_AMOUNT"));
        or.setCreationTime(rs.getTimestamp("CREATION_TIME"));
        or.setPaymentTime(rs.getTimestamp("PAYMENT_TIME"));
        or.setCloseTime(rs.getTimestamp("CLOSE_TIME"));
        or.setOperatorId(rs.getLong("OPERATOR_ID"));
        or.setOperatorName(rs.getString("OPERATOR_NAME"));
        or.setOrderStatus(OrderStatus.getOrderStatus(rs.getInt("ORDER_STATUS")));
        or.setStatusType(OrderStatusType.getOrderStatusType(rs.getInt("STATUS_TYPE")));
        or.setPayType(PayType.getPayType(rs.getInt("PAY_TYPE")));
        or.setOrderType(OrderType.getOrderType(rs.getInt("ORDER_TYPE")));
        or.setCheckStatus(OrderCheckStatus.getStatus(rs.getInt("CHECK_STATUS")));
        or.setCheckResult(rs.getString("CHECK_RESULT"));
        or.setCheckNum(rs.getInt("CHECK_NUM"));
        or.setCreateDevice(DeviceType.getDeviceType(rs.getInt("CREATE_DEVICE")));
        or.setModifyDevice(DeviceType.getDeviceType(rs.getInt("MODIFY_DEVICE")));
        or.setBuyType(CourseBuyType.getValue(rs.getInt("BUY_TYPE")));
        or.setOrderKind(OrderKind.get(rs.getInt("ORDER_KIND")));
        or.setRemark(rs.getString("REMARK"));
        or.setHifiOrder(rs.getString("HIFI_ORDER"));
        or.setHifiStatus(HifiCheckStatus.get(rs.getInt("HIFI_CHECK_STATUS")));
        return or;
    }

}
