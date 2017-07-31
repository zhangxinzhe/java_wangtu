/*
 * @(#)AccountServiceImpl.java    Created on 2017年7月24日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.account.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.account.AccountDao;
import net.zdsoft.chnmaster.service.account.AccountService;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.account.Account;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月24日 上午10:58:41 $
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public Account getAccountById(long id) {
        return accountDao.getAccountById(id);
    }

    @Override
    public int addAccount(Account account) {
        return accountDao.addAccount(account);
    }

    @Override
    public int updateFundsByAccountId(long accountId, double founds) {
        return accountDao.updateFundsByAccountId(accountId, founds);
    }

    @Override
    public int updateAccount(Account account) {
        return accountDao.updateAccount(account);
    }

    @Override
    public List<Account> listAccount(List<QueryCondition> cons, PageDto page) {
        return accountDao.listAccount(cons, page);
    }

}
