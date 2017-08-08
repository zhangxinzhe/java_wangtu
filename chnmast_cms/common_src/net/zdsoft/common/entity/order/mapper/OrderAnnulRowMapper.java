/*
 * @(#)OrderAnnulRowMapper.java    Created on 2016年3月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.order.OrderAnnul;
import net.zdsoft.common.enums.AnnulType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年3月25日 下午2:12:58 $
 */
@SuppressWarnings("rawtypes")
public class OrderAnnulRowMapper implements RowMapper {
    private static OrderAnnulRowMapper rowMapper = new OrderAnnulRowMapper();

    public static OrderAnnulRowMapper instance() {
        return rowMapper;
    }

    @Override
    public OrderAnnul mapRow(ResultSet rs, int i) throws SQLException {
        OrderAnnul entity = new OrderAnnul();
        entity.setId(rs.getLong("ID"));
        entity.setOrderId(rs.getLong("ORDER_ID"));
        entity.setAnnulType(AnnulType.getAnnulType(rs.getInt("ANNUL_TYPE")));
        entity.setCouponCode(rs.getString("COUPON_CODE"));
        entity.setAnnulAmount(rs.getFloat("ANNUL_AMOUNT"));
        entity.setOpeUserId(rs.getLong("OPE_USER_ID"));
        entity.setOpeRealName(rs.getString("OPE_REAL_NAME"));
        entity.setOpeTime(rs.getTimestamp("OPE_TIME"));
        entity.setRemark(rs.getString("REMARK"));
        return entity;
    }

}
