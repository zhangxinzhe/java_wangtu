/* 
 * @(#)SmsSendBatchService.java    Created on 2015年5月15日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service;

import java.util.Date;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015年5月15日 上午11:16:14 $
 */
public interface SmsSendBatchService {
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
}
