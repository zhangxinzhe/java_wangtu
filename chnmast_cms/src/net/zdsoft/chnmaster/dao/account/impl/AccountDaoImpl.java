/*
 * @(#)AccountDaoImpl.java    Created on 2016年12月21日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.account.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.account.AccountDao;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.dao.queryCondition.QueryConditionBuilder;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.account.Account;
import net.zdsoft.common.entity.account.mapper.AccountRowMapper;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年12月21日 下午2:11:07 $
 */
@SuppressWarnings("unchecked")
@Repository("accountDao")
public class AccountDaoImpl extends BaseDaoImpl implements AccountDao {

    private static final String get_account_by_id = "account.get_account_by_id";
    private static final String add_account = "account.add_account";

    @Override
    public Account getAccountById(long id) {
        return (Account) findForObject(getSql(get_account_by_id), new Object[] { id }, AccountRowMapper.instance());
    }

    @Override
    public int addAccount(Account account) {
        return executeUpdate(getSql(add_account),
                new Object[] { account.getId(), account.getFunds(), new Date(), account.getFundsLocked(), 0 });
    }

    @Override
    public int updateFundsByAccountId(long accountId, double founds) {
        String sql = "UPDATE T_ACCOUNT SET FUNDS = ?,MODIFY_TIME=?  WHERE ID=? ";
        return executeUpdate(sql, new Object[] { founds, new Date(), accountId });
    }

    @Override
    public int updateAccount(Account account) {
        String sql = "UPDATE T_ACCOUNT SET FUNDS=? ,MODIFY_TIME=?,ALIPAY_ACCOUNT=?,BANK_NAME=?,BANK_ACCOUNT=?,BANK_USER_NAME=? WHERE ID=?";
        return this.executeUpdate(sql, new Object[] { account.getFunds(), new Date(), account.getAlipayAccount(),
                account.getBankName(), account.getBankAccount(), account.getBankUserName(), account.getId() });
    }

    @Override
    public List<Account> listAccount(List<QueryCondition> cons, PageDto page) {
        String sql = "SELECT * FROM T_USER U,T_ACCOUNT A WHERE A.ID=U.ID AND U.IS_CANCEL=0 ";
        QueryConditionBuilder builder = new QueryConditionBuilder();
        builder.addConditions(cons);
        String paramStr = builder.buildCondition();
        if (StringUtils.isNotBlank(paramStr)) {
            sql += " AND " + paramStr;
        }
        if (null == page) {
            find(sql, builder.buildParameters(), AccountRowMapper.userAccountRowMapper);
        }
        return findForPage(sql, builder.buildParameters(), AccountRowMapper.userAccountRowMapper, page);
    }

}
