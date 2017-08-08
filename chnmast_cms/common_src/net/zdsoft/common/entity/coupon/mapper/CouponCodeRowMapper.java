/*
 * @(#)CouponCodeRowMapper.java    Created on 2016年8月9日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.coupon.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.coupon.CouponCode;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年8月9日 下午5:02:33 $
 */
@SuppressWarnings("rawtypes")
public class CouponCodeRowMapper implements RowMapper {

    private static CouponCodeRowMapper rowMapper = new CouponCodeRowMapper();

    public static CouponCodeRowMapper instance() {
        return rowMapper;
    }

    @Override
    public CouponCode mapRow(ResultSet rs, int arg1) throws SQLException {
        CouponCode entity = new CouponCode();
        entity.setId(rs.getLong("ID"));
        entity.setCouponId(rs.getLong("COUPON_ID"));
        entity.setCouponCode(rs.getString("COUPON_CODE"));
        entity.setIsCancel(YesNoType.get(rs.getInt("IS_CANCEL")));
        return entity;
    }

}
