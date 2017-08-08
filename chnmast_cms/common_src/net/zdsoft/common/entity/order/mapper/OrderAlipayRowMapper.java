/*
 * @(#)OrderAlipayRowMapper.java    Created on 2016年2月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.alipay.enums.AlipayFormState;
import net.zdsoft.common.alipay.enums.AlipayFormType;
import net.zdsoft.common.entity.order.OrderAlipay;

/**
 * @author xiongwq
 * @version $Revision: 1.0 $, $Date: 2016年2月22日 下午5:06:13 $
 */
@SuppressWarnings("rawtypes")
public class OrderAlipayRowMapper implements RowMapper {
    private static OrderAlipayRowMapper rowMapper = new OrderAlipayRowMapper();

    public static OrderAlipayRowMapper instance() {
        return rowMapper;
    }

    @Override
    public OrderAlipay mapRow(ResultSet rs, int i) throws SQLException {
        OrderAlipay oa = new OrderAlipay();
        oa.setId(rs.getLong("ID"));
        oa.setOrderId(rs.getLong("ORDER_ID"));
        oa.setTradeNo(rs.getString("TRADE_NO"));
        oa.setAlipayId(rs.getString("ALIPAY_ID"));
        oa.setNotifyId(rs.getString("NOTIFY_ID"));
        oa.setFormState(AlipayFormState.get(rs.getInt("FORM_STATE")));
        oa.setFormAmount(rs.getFloat("FORM_AMOUNT"));
        oa.setUserId(rs.getLong("USERID"));
        oa.setCreationTime(rs.getTimestamp("CREATION_TIME"));
        oa.setModifyTime(rs.getTimestamp("MODIFY_TIME"));
        oa.setFormType(AlipayFormType.get(rs.getInt("FORM_TYPE")));
        oa.setIsDeal(rs.getInt("ISDEAL"));
        oa.setOperatorId(rs.getLong("OPERATOR_ID"));
        oa.setOperatorName(rs.getString("OPERATOR_NAME"));
        return oa;
    }

}
