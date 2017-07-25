/*
 * @(#)SmsSendService.java    Created on 2013-11-21
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service;

import net.zdsoft.common.entity.sms.SmsSendDetail;
import net.zdsoft.common.enums.ReceiveStatusEnum;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2013-11-21 下午04:26:47 $
 */
public interface SmsSendDetailService {

    /**
     * 发送短信详情,并返回主键
     *
     * @param smsSendDetail
     * @return
     */
    public long addSmsSendDetail(SmsSendDetail smsSendDetail);

    /**
     * 更新状态
     *
     * @param sequence
     * @param status
     * @return
     */
    public int updateSmsSendDetailStatusBySequence(String sequence, int gatewayType, ReceiveStatusEnum status,
            String otherReason);

    /**
     * 更新状态
     *
     * @param sequences
     * @param batchId
     * @param status
     * @param otherReason
     * @return
     */
    public int updateSmsSendDetailStatusBySequences(String[] sequences, String batchId, int gatewayType,
            ReceiveStatusEnum status, String otherReason);

    /**
     * 更新短信群发信息
     *
     * @param sequences
     * @param batchId
     * @param accountName
     * @return
     */
    public int updateBatchIdBySequences(String[] sequences, String batchId, String accountName);

    /**
     * 通过组信息更新短信状态
     *
     * @param batchId
     * @param gatewayType
     * @param status
     * @param otherReason
     * @param mobile
     * @return
     */
    public int updateSmsSendDetailStatusByBatchId(String batchId, int gatewayType, ReceiveStatusEnum status,
            String otherReason, String mobile);
}
