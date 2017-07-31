/*
 * @(#)AccountDao.java    Created on 2016年12月21日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.account;

import java.util.List;

import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.account.Account;

/**
 * t_account
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年12月21日 下午2:09:17 $
 */
public interface AccountDao {

    /**
     * 根据id获取账户余额
     *
     * @param id
     * @return
     */
    public Account getAccountById(long id);

    /**
     * 新增账户表
     *
     * @param account
     * @return
     */
    public int addAccount(Account account);

    /**
     * 修改余额
     *
     * @param accountId
     * @param founds
     * @return
     */
    public int updateFundsByAccountId(long accountId, double founds);

    public int updateAccount(Account account);

    /**
     * 余额列表
     *
     * @param cons
     * @param page
     * @return
     */
    public List<Account> listAccount(List<QueryCondition> cons, PageDto page);
}
