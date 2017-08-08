/*
 * @(#)OrderDetailRowMapper.java    Created on 2015年12月23日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.order.OrderDetail;
import net.zdsoft.common.enums.CourseContentType;
import net.zdsoft.common.enums.ReturnStatus;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月23日 下午4:11:02 $
 * @param
 */
@SuppressWarnings("rawtypes")
public class OrderDetailRowMapper implements RowMapper {

    private static OrderDetailRowMapper rowMapper = new OrderDetailRowMapper();

    public static OrderDetailRowMapper instance() {
        return rowMapper;
    }

    @Override
    public OrderDetail mapRow(ResultSet rs, int i) throws SQLException {
        OrderDetail od = new OrderDetail();
        od.setId(rs.getLong("ID"));
        od.setOrderId(rs.getLong("ORDER_ID"));
        od.setWareId(rs.getLong("WARE_ID"));
        od.setWareTimeId(rs.getLong("WARE_TIMEID"));
        od.setWarePriceId(rs.getLong("WARE_PRICEID"));
        od.setWareType(CourseContentType.get(rs.getInt("WARE_TYPE")));
        od.setWareName(rs.getString("WARE_NAME"));
        od.setWareNum(rs.getInt("WARE_NUM"));
        od.setPayablePer(rs.getFloat("PAYABLE_PER"));
        od.setActualPer(rs.getFloat("ACTUAL_PER"));
        od.setPayableAmount(rs.getFloat("PAYABLE_AMOUNT"));
        od.setActualAmount(rs.getFloat("ACTUAL_AMOUNT"));
        od.setHasGood(YesNoType.get(rs.getInt("HAS_GOOD")));
        od.setReturStatus(ReturnStatus.getStatus(rs.getInt("STATUS")));
        od.setRemark(rs.getString("REMARK"));
        return od;
    }

}
