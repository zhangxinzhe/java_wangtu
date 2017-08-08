/*
 * @(#)Coupon.java    Created on 2016年4月19日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * ${user}$
 */
package net.zdsoft.common.entity.coupon.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.coupon.Coupon;
import net.zdsoft.common.enums.CouponCreateType;
import net.zdsoft.common.enums.CouponType;
import net.zdsoft.common.enums.CouponUseType;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年4月19日 上午10:43:57 $
 */
@SuppressWarnings("rawtypes")
public class CouponRowMapper implements RowMapper {

    private static CouponRowMapper rowMapper = new CouponRowMapper();
    public static CouponCodeRowMapper couponCodeRowMapper = new CouponCodeRowMapper();

    public static CouponRowMapper instance() {
        return rowMapper;
    }

    @Override
    public Coupon mapRow(ResultSet rs, int i) throws SQLException {
        Coupon entity = new Coupon();
        entity.setId(rs.getInt("ID"));
        entity.setName(rs.getString("NAME"));
        entity.setCreateType(CouponCreateType.get(rs.getInt("CREATE_TYPE")));
        entity.setCreateNum(rs.getInt("CREATE_NUM"));
        entity.setBatchCode(rs.getString("BATCH_CODE"));
        entity.setCouponType(CouponType.get(rs.getInt("COUPON_TYPE")));
        entity.setDiscount(rs.getFloat("DISCOUNT"));
        entity.setAmount(rs.getFloat("AMOUNT"));
        entity.setCouponUseType(CouponUseType.get(rs.getInt("USE_TYPE")));
        entity.setBeginTime(rs.getTimestamp("BEGIN_TIME"));
        entity.setEndTime(rs.getTimestamp("END_TIME"));
        entity.setOwnerName(rs.getString("OWNER_NAME"));
        entity.setCreateTime(rs.getTimestamp("CREATE_TIME"));
        entity.setRemark(rs.getString("REMARK"));
        return entity;
    }

    static class CouponCodeRowMapper implements RowMapper<Coupon> {
        @Override
        public Coupon mapRow(ResultSet rs, int i) throws SQLException {
            Coupon c = rowMapper.mapRow(rs, i);
            c.setCouponCodeId(rs.getLong("COUPON_CODE_ID"));
            c.setCouponCode(rs.getString("COUPON_CODE"));
            c.setIsCancel(YesNoType.get(rs.getInt("IS_CANCEL")));
            return c;
        }
    }

}
