/* 
 * @(#)SystemLogDao.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.system;

import java.util.List;

import net.zdsoft.chnmaster.entity.system.SystemLog;
import net.zdsoft.common.dao.BaseDao;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;

/**
 * 系统日志【t_system_log】
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-26 下午3:55:21 $
 */
public interface SystemLogDao extends BaseDao {
    /**
     * 新增系统日志
     * 
     * @param systemLog
     */
    public void addSystemLog(SystemLog systemLog);

    /**
     * 获取系统日志
     * 
     * @param conditions
     * @param page
     * @return
     */
    public List<SystemLog> getSystemLog(List<QueryCondition> conditions, PageDto page);

    /**
     * 获取操作的数据的数目
     * 
     * @param userId
     * @return
     */
    public long getUserOperatorNum(long userId);

}
