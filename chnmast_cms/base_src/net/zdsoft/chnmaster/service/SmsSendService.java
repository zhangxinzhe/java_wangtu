/*
 * @(#)SmsSendService.java    Created on 2013-11-21
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service;

import java.util.List;

import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.sms.SmsSend;
import net.zdsoft.common.entity.sms.SmsSendDetail;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2013-11-21 下午04:26:47 $
 */
public interface SmsSendService {
    /**
     * 保存短信息信息
     *
     * @param send
     * @param sendDetails
     */
    public void addSmsSend(SmsSend send, List<SmsSendDetail> sendDetails);

    /**
     * 保存短信息信息
     *
     * @param send
     * @param sendDetails
     */
    public void addSmsSend(SmsSend send, SmsSendDetail sendDetail);

    /**
     * 发送短信
     *
     * @param userIds
     * @param smsContent
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
}
