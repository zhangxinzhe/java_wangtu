/*
 * @(#)AccountRecordServiceImpl.java    Created on 2016年12月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.account.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.account.AccountRecordDao;
import net.zdsoft.chnmaster.service.account.AccountRecordService;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.account.AccountRecord;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2016年12月22日 下午1:52:32 $
 */
@Service("accountRecordService")
public class AccountRecordServiceImpl implements AccountRecordService {

    @Resource
    private AccountRecordDao accountRecordDao;

    @Override
    public int addAccountRecord(AccountRecord record) {
        return accountRecordDao.addAccountRecord(record);
    }

    @Override
    public List<AccountRecord> getAccountRecordListByUserId(long userId, PageDto page) {
        return accountRecordDao.getAccountRecordListByUserId(userId, page);
    }

    @Override
    public List<AccountRecord> getAccountRecordByConditions(List<QueryCondition> conditions, PageDto page) {
        return accountRecordDao.getAccountRecordByConditions(conditions, page);
    }

    @Override
    public float getTotalAmount(List<QueryCondition> conditions) {
        return accountRecordDao.getTotalAmount(conditions);
    }

}
