/*
 * @(#)CheckSmsSendStatusService.java    Created on 2015年5月14日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.sms;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015年5月14日 下午3:29:15 $
 */
public interface CheckSmsSendStatusService {
    /**
     * 短信信息检查
     * 
     * @param from
     *            0 - spring 1-distributed task service
     */
    public void checkStatus(int from);

    /**
     * 检查单条短信发送的情况
     *
     * @param detailId
     * @return
     */
    public String checkMsgStatus(long detailId);
}
