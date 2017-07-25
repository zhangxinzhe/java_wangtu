/*
 * @(#)HifiOrderService.java    Created on 2016年11月24日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.hifi.service;

import java.util.Map;

/**
 * Hifi对接-订单处理
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年11月24日 下午7:58:34 $
 */
public interface HifiOrderApiService {

    /**
     * 创建订单
     *
     * @param apikey
     * @param productId
     *            套餐id
     *
     * @return "result"：订单创建结果，成功时为true,失败时为false<br>
     *         "orderId"：成功时为订单id，失败时为空
     */
    public Map<String, String> createHifiOrder(String apikey, String productId);

    /**
     * 支付订单
     *
     * @param orderId
     *            hifi订单id
     * @param tradeStateValue
     *            音乐网平台支付结果，固定值（支付成功："1"；支付失败："0"）
     *
     * @return "result"：支付结果，0交易成功，1交易失败，-1表示签名错误，-2表示支付状态传值错误，-3表示调用支付接口失败<br>
     *         "msg"：支付结果说明
     */
    public Map<String, String> payHifiOrder(String orderId, int tradeStateValue);

}
