/*
 * @(#)CouponCodeRuleRowMapper.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.coupon.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.coupon.CouponCodeRule;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午2:40:55 $
 */
@SuppressWarnings("rawtypes")
public class CouponCodeRuleRowMapper implements RowMapper {

    private static CouponCodeRuleRowMapper rowMapper = new CouponCodeRuleRowMapper();

    public static CouponCodeRuleRowMapper instance() {
        return rowMapper;
    }

    @Override
    public CouponCodeRule mapRow(ResultSet rs, int arg1) throws SQLException {
        CouponCodeRule entity = new CouponCodeRule();
        entity.setId(rs.getInt("ID"));
        entity.setBatchCode(rs.getString("BATCH_CODE"));
        entity.setCode1(rs.getInt("CODE1"));
        entity.setCode2(rs.getInt("CODE2"));
        entity.setStatus(rs.getInt("STATUS"));
        return entity;
    }

}
