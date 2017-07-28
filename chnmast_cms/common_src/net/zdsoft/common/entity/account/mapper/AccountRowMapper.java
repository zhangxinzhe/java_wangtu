/*
 * @(#)AccountRowMapper.java    Created on 2016年12月21日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.account.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.account.Account;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年12月21日 上午11:32:46 $
 */
@SuppressWarnings("rawtypes")
public class AccountRowMapper implements RowMapper {

    private static AccountRowMapper rowMapper = new AccountRowMapper();

    public static UserAccountRowMapper userAccountRowMapper = new UserAccountRowMapper();

    public static AccountRowMapper instance() {
        return rowMapper;
    }

    @Override
    public Account mapRow(ResultSet rs, int arg1) throws SQLException {
        Account entity = new Account();
        entity.setId(rs.getLong("ID"));
        entity.setFunds(rs.getFloat("FUNDS"));
        entity.setModifyTime(rs.getTimestamp("MODIFY_TIME"));
        entity.setFundsLocked(rs.getFloat("FUNDS_LOCKED"));
        entity.setStatus(rs.getInt("STATUS"));
        entity.setAlipayAccount(rs.getString("ALIPAY_ACCOUNT"));
        entity.setBankName(rs.getString("BANK_NAME"));
        entity.setBankUserName(rs.getString("BANK_ACCOUNT"));
        entity.setBankAccount(rs.getString("BANK_USER_NAME"));
        return entity;
    }

    static class UserAccountRowMapper implements RowMapper {
        @Override
        public Account mapRow(ResultSet rs, int arg1) throws SQLException {
            Account entity = rowMapper.mapRow(rs, arg1);

            entity.setUserName(rs.getString("USERNAME"));
            entity.setRealName(rs.getString("REALNAME"));
            entity.setPhone(rs.getString("PHONE"));
            entity.setEmail(rs.getString("EMAIL"));
            return entity;
        }
    }

}
