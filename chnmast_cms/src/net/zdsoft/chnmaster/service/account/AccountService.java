/*
 * @(#)AccountService.java    Created on 2016年12月21日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.account;

import net.zdsoft.common.entity.account.Account;

/**
 * 用户个人余额账户
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年12月21日 下午2:05:38 $
 */
public interface AccountService {

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
    public int updateFundsByAccountId(long accountId, float founds);

    /**
     *
     * @param account
     * @return
     */
    public int updateAccount(Account account);

}
