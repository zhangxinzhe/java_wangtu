/* 
 * @(#)SystemLogDaoImpl.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.system.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.system.SystemLogDao;
import net.zdsoft.chnmaster.entity.system.SystemLog;
import net.zdsoft.chnmaster.entity.system.mapper.SystemLogRowMapper;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.dao.queryCondition.QueryConditionBuilder;
import net.zdsoft.common.entity.PageDto;

@SuppressWarnings("unchecked")
@Repository("systemLogDao")
public class SystemLogDaoImpl extends BaseDaoImpl implements SystemLogDao {
    private static final String new_systemlog = "system.new_systemlog";
    private static final String list_systemlog = "system.list_systemlog";
    private static final String get_user_operator_num = "system.get_user_operator_num";

    @Override
    public void addSystemLog(SystemLog systemLog) {
        systemLog.setOperateTime(new Date());

        executeUpdate(getSql(new_systemlog), new Object[] { systemLog.getOperate(), systemLog.getOperateTime(),
                systemLog.getUserId(), systemLog.getOperateIp() });
    }

    @Override
    public List<SystemLog> getSystemLog(List<QueryCondition> conditions, PageDto page) {
        QueryConditionBuilder builder = new QueryConditionBuilder();
        builder.addConditions(conditions);

        StringBuilder sql = new StringBuilder(getSql(list_systemlog));
        sql.append(" WHERE A.Userid=B.ID ");
        String condition = builder.buildCondition();
        if (!StringUtils.isBlank(condition)) {
            sql.append(" AND ");
            sql.append(builder.buildCondition());
        }
        sql.append(" ORDER BY A.OPERATETIME DESC");

        return findForPage(sql.toString(), builder.buildParameters(), SystemLogRowMapper.instance(), page);
    }

    @Override
    // 已调用
    public long getUserOperatorNum(long userId) {
        return findForLong(getSql(get_user_operator_num), new Object[] { userId });
    }
}
