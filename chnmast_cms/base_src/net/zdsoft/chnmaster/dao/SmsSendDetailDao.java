/*
 * @(#)SmsSendDetailDao.java    Created on 2013-11-21
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao;

import java.util.List;

import net.zdsoft.common.dao.BaseDao;
import net.zdsoft.common.entity.sms.SmsSendDetail;
import net.zdsoft.common.enums.ReceiveStatusEnum;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2013-11-21 下午03:53:13 $
 */
public interface SmsSendDetailDao extends BaseDao {
    /**
     * 保存
     *
     * @param sendId
     * @param sendDetails
     * @return
     */
    public int[] addSmsSendDetail(final long sendId, final List<SmsSendDetail> sendDetails);

    /**
     * 添加短信发送明细
     *
     * @param smsSend
     * @return
     */
    public int addSmsSendDetail(SmsSendDetail smsSendDetail);

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
     * @return
     */
    public int updateBatchIdBySequences(String[] sequences, int gatewayType, String batchId);

    /**
     * 获取短信详细情况
     *
     * @param id
     * @return
     */
    public SmsSendDetail getDetail(long id);

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
