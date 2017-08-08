/*
 * @(#)orderAlipayLog.java    Created on 2016年2月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;

/**
 * 支付宝支付日志
 *
 * @author xiongwq
 * @version $Revision: 1.0 $, $Date: 2016年2月22日 下午5:19:32 $
 */
public class OrderAlipayLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * T_ORDER_ALIPAY.TRADE_NO
     */
    private String tradeNo;
    /**
     * 日志内容
     */
    private String logContent;
    /**
     * 创建时间
     */
    private Date creationTime;
    /**
     * 日志类型（ 1表示支付，2表示查询）
     */
    private int logType;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public int getLogType() {
        return logType;
    }

    public void setLogType(int logType) {
        this.logType = logType;
    }

}
