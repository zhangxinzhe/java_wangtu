/* 
 * @(#)SystemLogServiceImpl.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.system.SystemLogDao;
import net.zdsoft.chnmaster.entity.system.SystemLog;
import net.zdsoft.chnmaster.service.common.BaseServiceImpl;
import net.zdsoft.chnmaster.service.system.SystemLogService;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;

@Service("systemLogService")
public class SystemLogServiceImpl extends BaseServiceImpl implements SystemLogService {
    @Resource
    private SystemLogDao systemLogDao;

    @Override
    public void addSystemLog(SystemLog systemLog) {
        systemLogDao.addSystemLog(systemLog);
    }

    @Override
    public List<SystemLog> getSystemLogs(List<QueryCondition> conditions, PageDto page) {
        return systemLogDao.getSystemLog(conditions, page);
    }

    @Override
    // 已调用
    public long getUserOperatorNum(long userId) {
        return systemLogDao.getUserOperatorNum(userId);
    }

}
