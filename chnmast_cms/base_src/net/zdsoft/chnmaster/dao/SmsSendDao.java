/*
 * @(#)SmsSendDao.java    Created on 2013-11-21
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao;

import java.util.List;

import net.zdsoft.common.dao.BaseDao;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.sms.SmsSend;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2013-11-21 下午03:34:36 $
 */
public interface SmsSendDao extends BaseDao {

    /**
     * 添加短信发送 并返回主键
     *
     * @param smsSend
     * @return
     */
    public Long addSmsSend(SmsSend smsSend);

    /**
     * 短信查询
     *
     * @param queryConditions
     * @param page
     * @return
     */
    public List<SmsSend> getSmsSends(List<QueryCondition> queryConditions, PageDto page);

    /**
     * 获取接收者短信条数
     *
     * @param queryConditions
     * @return
     */
    public int countSmsSend(List<QueryCondition> queryConditions);
}
