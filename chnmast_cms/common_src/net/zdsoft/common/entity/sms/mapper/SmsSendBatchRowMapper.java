/*
 * @(#)SmsSendBatchRowMapper.java    Created on 2015年5月15日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.sms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.sms.SmsSendBatch;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015年5月15日 上午9:48:42 $
 */
public class SmsSendBatchRowMapper implements RowMapper<SmsSendBatch> {
    public static SmsSendBatchRowMapper rowMapper = new SmsSendBatchRowMapper();

    @Override
    public SmsSendBatch mapRow(ResultSet rs, int i) throws SQLException {
        SmsSendBatch batch = new SmsSendBatch();
        batch.setId(rs.getLong("id"));
        batch.setAccountName(rs.getString("ACCOUNT_NAME"));
        batch.setBatchId(rs.getString("batch_Id"));
        batch.setCheckDate(rs.getTimestamp("CHECK_DATE"));
        batch.setCheckTimes(rs.getInt("check_times"));
        batch.setCreateDate(rs.getTimestamp("create_date"));
        batch.setStatus(rs.getInt("status"));
        return batch;
    }
}
