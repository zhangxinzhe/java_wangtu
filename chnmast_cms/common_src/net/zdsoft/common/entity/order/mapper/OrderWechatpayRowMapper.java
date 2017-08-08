/*
 * @(#)OrderWechatpayRowMapper.java    Created on 2016年3月30日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.alipay.enums.AlipayFormState;
import net.zdsoft.common.alipay.enums.AlipayFormType;
import net.zdsoft.common.entity.order.OrderWechatpay;
import net.zdsoft.common.wechatpay.enums.WechatpayTradeType;

public class OrderWechatpayRowMapper implements RowMapper<OrderWechatpay> {
    public static OrderWechatpayRowMapper rowMapper = new OrderWechatpayRowMapper();

    @Override
    public OrderWechatpay mapRow(ResultSet rs, int i) throws SQLException {
        OrderWechatpay wechatpay = new OrderWechatpay();
        wechatpay.setId(rs.getLong("id"));
        wechatpay.setOrderId(rs.getLong("order_id"));
        wechatpay.setTradeNo(rs.getString("trade_no"));
        wechatpay.setOpenId(rs.getString("open_id"));
        wechatpay.setTransactionId(rs.getString("transaction_id"));
        wechatpay.setPrepayId(rs.getString("prepay_id"));
        wechatpay.setTradeType(WechatpayTradeType.get(rs.getString("trade_type")));
        wechatpay.setCodeUrl(rs.getString("code_url"));
        wechatpay.setFormState(AlipayFormState.get(rs.getInt("form_state")));
        wechatpay.setFormAmount(rs.getFloat("form_amount"));
        wechatpay.setUserId(rs.getLong("userid"));
        wechatpay.setCreationTime(rs.getTimestamp("creation_time"));
        wechatpay.setModifyTime(rs.getTimestamp("modify_time"));
        wechatpay.setFormType(AlipayFormType.get(rs.getInt("form_type")));
        wechatpay.setIsDeal(rs.getInt("ISDEAL"));
        wechatpay.setOperatorId(rs.getLong("operator_id"));
        wechatpay.setOperatorName(rs.getString("operator_name"));
        return wechatpay;
    }

}
