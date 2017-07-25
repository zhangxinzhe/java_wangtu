/*
 * @(#)AccountRecordDaoImpl.java    Created on 2016年12月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.account.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.account.AccountRecordDao;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.dao.queryCondition.QueryConditionBuilder;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.account.AccountRecord;
import net.zdsoft.common.entity.account.mapper.AccountRecordRowMapper;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年12月22日 下午1:53:48 $
 */
@SuppressWarnings("unchecked")
@Repository("accountRecordDao")
public class AccountRecordDaoImpl extends BaseDaoImpl implements AccountRecordDao {

    private static final String add_account_record = "account.add_account_record";
    private static final String list_user_account_record_by_condition = "account.list_user_account_record_by_condition";
    private static final String get_sum_total_amount_by_condition = "account.get_sum_total_amount_by_condition";

    @Override
    public int addAccountRecord(AccountRecord record) {
        return executeUpdate(getSql(add_account_record),
                new Object[] { record.getUserId(), record.getRelationId(), new Date(), record.getAuditUserId(),
                        record.getAuditRealName(), record.getChangeType().getValue(), record.getChangeFunds(),
                        record.getRemainFunds(), record.getDetailType().getValue(), record.getRecordType().getValue(),
                        record.getRemark() });
    }

    @Override
    public List<AccountRecord> getAccountRecordListByUserId(long userId, PageDto page) {
        String sql = "SELECT * FROM T_ACCOUNT_RECORD WHERE USERID = ? ORDER BY RECORD_DATE DESC";
        if (page == null) {
            return find(sql, new Object[] { userId }, AccountRecordRowMapper.instance());
        }
        return findForPage(sql, new Object[] { userId }, AccountRecordRowMapper.instance(), page);
    }

    @Override
    public List<AccountRecord> getAccountRecordByConditions(List<QueryCondition> conditions, PageDto page) {
        String sql = getSql(list_user_account_record_by_condition);
        QueryConditionBuilder builder = new QueryConditionBuilder();
        builder.addConditions(conditions);
        String builderStr = builder.buildCondition();
        if (StringUtils.isNotEmpty(builderStr)) {
            sql += " AND " + builderStr;
        }
        sql += " ORDER BY AR.RECORD_DATE DESC";// 时间倒序
        if (page == null) {
            return find(sql, builder.buildParameters(), AccountRecordRowMapper.accountRecordUserRowMapper);
        }
        return findForPage(sql, builder.buildParameters(), AccountRecordRowMapper.accountRecordUserRowMapper, page);
    }

    @Override
    public float getTotalAmount(List<QueryCondition> conditions) {
        String sql = getSql(get_sum_total_amount_by_condition);
        QueryConditionBuilder builder = new QueryConditionBuilder();
        builder.addConditions(conditions);
        String builderStr = builder.buildCondition();
        if (StringUtils.isNotEmpty(builderStr)) {
            sql += " AND " + builderStr;
        }

        Float sum = (Float) findForObject(sql, builder.buildParameters(), new RowMapper<Float>() {
            @Override
            public Float mapRow(ResultSet rs, int arg1) throws SQLException {
                return rs.getFloat("SUM_CHANGE_FUNDS");
            }
        });
        if (sum != null) {
            return sum;
        }
        else {
            return 0;
        }
    }

}
