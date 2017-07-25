/*
 * @(#)SmsSendBatchServiceImpl.java    Created on 2015年5月15日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.SmsSendBatchDao;
import net.zdsoft.chnmaster.service.SmsSendBatchService;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015年5月15日 上午11:17:42 $
 */
@Service("smsSendBatchService")
public class SmsSendBatchServiceImpl implements SmsSendBatchService {
    @Resource
    private SmsSendBatchDao smsSendBatchDao;

    @Override
    public int updateStatus(long id, int status) {
        return smsSendBatchDao.updateStatus(id, status);
    }

    @Override
    public int updateCheckDate(long id, Date checkDate) {
        return smsSendBatchDao.updateCheckDate(id, checkDate);
    }
}
