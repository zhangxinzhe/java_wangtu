/*
 * @(#)AccountRecordRowMapper.java    Created on 2016年12月21日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.account.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.account.AccountRecord;
import net.zdsoft.common.enums.ChargeType;
import net.zdsoft.common.enums.RecordDetailType;
import net.zdsoft.common.enums.RecordType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年12月21日 上午11:39:42 $
 */
public class AccountRecordRowMapper implements RowMapper<AccountRecord> {

    private static AccountRecordRowMapper row = new AccountRecordRowMapper();

    public static AccountRecordUserRowMapper accountRecordUserRowMapper = new AccountRecordUserRowMapper();

    public static AccountRecordRowMapper instance() {
        return row;
    }

    @Override
    public AccountRecord mapRow(ResultSet rs, int arg1) throws SQLException {
        AccountRecord ar = new AccountRecord();
        ar.setId(rs.getLong("ID"));
        ar.setUserId(rs.getLong("USERID"));
        ar.setRelationId(rs.getString("RELATION_ID"));
        ar.setRecordDate(rs.getTimestamp("RECORD_DATE"));
        ar.setAuditUserId(rs.getLong("AUDIT_USER_ID"));
        ar.setAuditRealName(rs.getString("AUDIT_REAL_NAME"));
        ar.setChangeType(ChargeType.get(rs.getInt("CHANGE_TYPE")));
        ar.setChangeFunds(rs.getFloat("CHANGE_FUNDS"));
        ar.setRemainFunds(rs.getFloat("REMAIN_FUNDS"));
        ar.setDetailType(RecordDetailType.get(rs.getInt("DETAIL_TYPE")));
        ar.setRecordType(RecordType.getRecordType(rs.getInt("RECORD_TYPE")));
        ar.setRemark(rs.getString("REMARK"));
        return ar;
    }

    @SuppressWarnings("rawtypes")
    static class AccountRecordUserRowMapper implements RowMapper {
        @Override
        public AccountRecord mapRow(ResultSet rs, int arg1) throws SQLException {
            AccountRecord ar = row.mapRow(rs, arg1);

            ar.setUserName(rs.getString("USERNAME"));
            ar.setRealName(rs.getString("REALNAME"));
            ar.setPhone(rs.getString("PHONE"));
            ar.setEmail(rs.getString("EMAIL"));

            ar.setTradeNo(rs.getString("TRADE_NO"));
            return ar;
        }
    }

}
