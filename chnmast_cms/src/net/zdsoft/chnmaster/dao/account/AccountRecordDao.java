/*
 * @(#)AccountRecordService.java    Created on 2016年12月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.account;

import java.util.List;

import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.account.AccountRecord;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年12月22日 下午1:50:44 $
 */
public interface AccountRecordDao {

    /**
     * 新增
     *
     * @param record
     * @return
     */
    public int addAccountRecord(AccountRecord record);

    /**
     * 根据个人用户查询交易记录
     *
     * @param userId
     * @param page
     * @return
     */
    public List<AccountRecord> getAccountRecordListByUserId(long userId, PageDto page);

    /**
     * 根据条件查询ios收支明细
     *
     * @param conditions
     * @param page
     * @return
     */
    public List<AccountRecord> getAccountRecordByConditions(List<QueryCondition> conditions, PageDto page);

    /**
     * 根据条件查询ios收支总金额
     *
     * @param conditions
     * @return
     */
    public float getTotalAmount(List<QueryCondition> conditions);

}
