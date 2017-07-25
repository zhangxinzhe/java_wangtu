/*
 * @(#)SmsSendBatchDao.java    Created on 2015年5月15日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao;

import java.util.Date;
import java.util.List;

import net.zdsoft.common.entity.sms.SmsSendBatch;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015年5月15日 上午9:24:38 $
 */
public interface SmsSendBatchDao {
    /**
     * 保存
     *
     * @param batch
     * @return
     */
    public int save(SmsSendBatch batch);

    /**
     * 更新处理状态
     *
     * @param id
     * @param status
     * @return
     */
    public int updateStatus(long id, int status);

    /**
     * 更新处理时间
     *
     * @param id
     * @param checkDate
     * @return
     */
    public int updateCheckDate(long id, Date checkDate);

    /**
     * 获取前num条待检查的信息
     *
     * @param accountNames
     *            发送账号信息
     * @param checkTimes
     *            小于检查的改次数
     * @param num
     * @return
     */
    public List<SmsSendBatch> getBatchs(String[] accountNames, int checkTimes, int num);

    /**
     * 获取发送的账号信息
     *
     * @param batchId
     * @return
     */
    public String getSendAccount(String batchId);
}
