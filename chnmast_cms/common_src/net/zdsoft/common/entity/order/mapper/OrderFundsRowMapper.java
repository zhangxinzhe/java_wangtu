/*
 * @(#)OrderFundsRowMapper.java    Created on 2016年2月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.order.OrderFunds;
import net.zdsoft.common.enums.ChargeType;
import net.zdsoft.common.enums.CourseContentType;
import net.zdsoft.common.enums.RecordDetailType;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2016年2月22日 下午5:44:45 $
 */
@SuppressWarnings("rawtypes")
public class OrderFundsRowMapper implements RowMapper {

    private static OrderFundsRowMapper rowMapper = new OrderFundsRowMapper();
    public static OrderFundsAndOrderInfo orderFundsAndOrderInfoRowMapper = new OrderFundsAndOrderInfo();

    public static OrderFundsRowMapper instance() {
        return rowMapper;
    }

    @Override
    public OrderFunds mapRow(ResultSet rs, int i) throws SQLException {
        OrderFunds of = new OrderFunds();
        of.setId(rs.getLong("ID"));
        of.setUserId(rs.getLong("USERID"));
        of.setRelationId(rs.getString("RELATION_ID"));
        of.setWareType(CourseContentType.get(rs.getInt("WARE_TYPE")));
        of.setRecordDate(rs.getTimestamp("RECORD_DATE"));
        of.setOpeUserId(rs.getInt("OPE_USER_ID"));
        of.setOpeRealName(rs.getString("OPE_REAL_NAME"));
        of.setChangeType(ChargeType.get(rs.getInt("CHANGE_TYPE")));
        of.setChangeFunds(rs.getFloat("CHANGE_FUNDS"));
        of.setRemainFunds(rs.getFloat("REMAIN_FUNDS"));
        of.setDetailType(RecordDetailType.get(rs.getInt("DETAIL_TYPE")));
        of.setRemark(rs.getString("REMARK"));
        return of;
    }

    static class OrderFundsAndOrderInfo implements RowMapper {
        @Override
        public OrderFunds mapRow(ResultSet rs, int i) throws SQLException {
            OrderFunds of = rowMapper.mapRow(rs, i);

            of.setOrderId(rs.getLong("ORDERID"));
            of.setTradeNo(rs.getString("TRADE_NO"));
            of.setPayAmount(rs.getFloat("PAY_AMOUNT"));
            of.setOrderCreateTime(rs.getTimestamp("CREATION_TIME"));
            return of;
        }

    }
}
