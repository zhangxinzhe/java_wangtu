/*
 * @(#)SmsSendBatchDaoImpl.java    Created on 2015年5月15日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.SmsSendBatchDao;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.entity.sms.SmsSendBatch;
import net.zdsoft.common.entity.sms.mapper.SmsSendBatchRowMapper;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015年5月15日 上午9:24:55 $
 */
@SuppressWarnings("unchecked")
@Repository("smsSendBatchDao")
public class SmsSendBatchDaoImpl extends BaseDaoImpl implements SmsSendBatchDao {
    private static final String save_sms_send_batch = "sms.save_sms_send_batch";
    private static final String update_sms_send_batch_status = "sms.update_sms_send_batch_status";
    private static final String update_sms_send_batch_check_date = "sms.update_sms_send_batch_check_date";

    @Override
    public int save(SmsSendBatch batch) {
        return executeUpdate(getSql(save_sms_send_batch), new Object[] { batch.getBatchId(), batch.getAccountName(),
                batch.getCreateDate(), batch.getCheckDate() });
    }

    @Override
    public int updateStatus(long id, int status) {
        return executeUpdate(getSql(update_sms_send_batch_status), new Object[] { status, id });
    }

    @Override
    public int updateCheckDate(long id, Date checkDate) {
        return executeUpdate(getSql(update_sms_send_batch_check_date), new Object[] { checkDate, id });
    }

    @Override
    public List<SmsSendBatch> getBatchs(String[] accountNames, int checkTimes, int num) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM T_SMS_SEND_BATCH WHERE STATUS = 0 AND ACCOUNT_NAME IN(");
        List<Object> argsList = new ArrayList<Object>();
        for (int i = 0; i < accountNames.length; i++) {
            if (i > 0) {
                sql.append(",");
            }
            sql.append("?");
            argsList.add(accountNames[i]);
        }
        sql.append(") AND CHECK_DATE <= ? AND CHECK_TIMES < ? LIMIT ?");
        argsList.add(Calendar.getInstance().getTime());
        argsList.add(checkTimes);
        argsList.add(num);
        return find(sql.toString(), argsList.toArray(), SmsSendBatchRowMapper.rowMapper);
    }

    @Override
    public String getSendAccount(String batchId) {
        String sql = "SELECT ACCOUNT_NAME FROM T_SMS_SEND_BATCH WHERE BATCH_ID = ?";
        return (String) findForObject(sql, new Object[] { batchId }, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int arg1) throws SQLException {
                return rs.getString("ACCOUNT_NAME");
            }

        });
    }
}
