/*
 * @(#)SmsSendServiceImpl.java    Created on 2013-11-21
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.impl;

import java.util.Calendar;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.config.Config;
import net.zdsoft.chnmaster.dao.SmsSendBatchDao;
import net.zdsoft.chnmaster.dao.SmsSendDetailDao;
import net.zdsoft.chnmaster.service.SmsSendDetailService;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.entity.sms.SmsSendBatch;
import net.zdsoft.common.entity.sms.SmsSendDetail;
import net.zdsoft.common.enums.ReceiveStatusEnum;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2013-11-21 下午04:27:27 $
 */
@Service("smsSendDetailService")
public class SmsSendDetailServiceImpl implements SmsSendDetailService {

    @Resource
    private SmsSendDetailDao smsSendDetailDao;
    @Resource
    private SmsSendBatchDao smsSendBatchDao;

    @Override
    public long addSmsSendDetail(SmsSendDetail smsSendDetail) {
        return smsSendDetailDao.addSmsSendDetail(smsSendDetail);
    }

    @Override
    public int updateSmsSendDetailStatusBySequence(String sequence, int gatewayType, ReceiveStatusEnum status,
            String otherReason) {
        if (StringUtils.isBlank(otherReason)) {
            otherReason = status.getNameValue();
        }
        return smsSendDetailDao.updateSmsSendDetailStatusBySequence(sequence, gatewayType, status, otherReason);
    }

    @Override
    public int updateSmsSendDetailStatusBySequences(String[] sequences, String batchId, int gatewayType,
            ReceiveStatusEnum status, String otherReason) {
        if (StringUtils.isBlank(otherReason)) {
            otherReason = status.getNameValue();
        }
        return smsSendDetailDao.updateSmsSendDetailStatusBySequences(sequences, batchId, gatewayType, status,
                otherReason);
    }

    @Override
    public int updateBatchIdBySequences(String[] sequences, String batchId, String accountName) {
        SmsSendBatch batch = new SmsSendBatch();
        batch.setBatchId(batchId);
        batch.setAccountName(accountName);
        Calendar now = Calendar.getInstance();
        batch.setCreateDate(now.getTime());
        now.add(Calendar.MINUTE, Integer.parseInt(Config.getParam(BaseConstants.PHONE_XUANWU_CHECK_MINIUTE)));
        batch.setCheckDate(now.getTime());
        smsSendBatchDao.save(batch);
        return smsSendDetailDao.updateBatchIdBySequences(sequences, 1, batchId);
    }

    @Override
    public int updateSmsSendDetailStatusByBatchId(String batchId, int gatewayType, ReceiveStatusEnum status,
            String otherReason, String mobile) {
        return smsSendDetailDao.updateSmsSendDetailStatusByBatchId(batchId, gatewayType, status, otherReason, mobile);
    }
}
