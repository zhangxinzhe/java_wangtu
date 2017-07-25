/* 
 * @(#)SystemLogService.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.system;

import java.util.List;

import net.zdsoft.chnmaster.entity.system.SystemLog;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;

/**
 * 系统日志服务接口
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-26 下午4:02:36 $
 */
public interface SystemLogService {

    /**
     * 新增系统日志
     * 
     * @param systemLog
     */
    public void addSystemLog(SystemLog systemLog);

    /**
     * 搜索系统日志
     * 
     * @param page
     * @param conditions
     * @return
     */
    public List<SystemLog> getSystemLogs(List<QueryCondition> conditions, PageDto page);

    /**
     * 获取该用户操作的数据数目
     * 
     * @param userId
     * @return
     */
    public long getUserOperatorNum(long userId);

}
