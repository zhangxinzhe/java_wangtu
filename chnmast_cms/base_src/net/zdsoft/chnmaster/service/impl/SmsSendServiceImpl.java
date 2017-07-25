/*
 * @(#)SmsSendServiceImpl.java    Created on 2013-11-21
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.SmsSendDao;
import net.zdsoft.chnmaster.dao.SmsSendDetailDao;
import net.zdsoft.chnmaster.service.SmsSendService;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.sms.SmsSend;
import net.zdsoft.common.entity.sms.SmsSendDetail;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2013-11-21 下午04:27:27 $
 */
@Service("smsSendService")
public class SmsSendServiceImpl implements SmsSendService {
    @Resource
    private SmsSendDao smsSendDao;
    @Resource
    private SmsSendDetailDao smsSendDetailDao;

    @Override
    public Long addSmsSend(SmsSend smsSend) {
        return smsSendDao.addSmsSend(smsSend);
    }

    @Override
    public void addSmsSend(SmsSend send, SmsSendDetail sendDetail) {
        long sendId = smsSendDao.addSmsSend(send);
        sendDetail.setSendId(sendId);
        smsSendDetailDao.addSmsSendDetail(sendDetail);
    }

    @Override
    public void addSmsSend(SmsSend send, List<SmsSendDetail> sendDetails) {
        long sendId = smsSendDao.addSmsSend(send);
        smsSendDetailDao.addSmsSendDetail(sendId, sendDetails);
    }

    @Override
    public List<SmsSend> getSmsSends(List<QueryCondition> queryConditions, PageDto page) {
        return smsSendDao.getSmsSends(queryConditions, page);
    }
}
