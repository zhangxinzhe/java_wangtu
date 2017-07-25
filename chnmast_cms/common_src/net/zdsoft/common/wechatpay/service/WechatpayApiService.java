/*
 * @(#)WeiXinApiService.java    Created on 2016年3月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.wechatpay.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface WechatpayApiService {
    /**
     * 查询用户信息
     *
     * @param code
     *            微信授权码
     * @return
     */
    public JSONObject queryUserInfo(String code);

    /**
     * 查询用户openId
     *
     * @param code
     *            微信授权码
     * @return
     */
    public JSONObject queryForOpenId(String code);

    /**
     * 关闭订单接口
     *
     * @param outTradeNo
     * @return
     */
    public JSONObject closeOrder(String outTradeNo, boolean isApp);

    /**
     * 查询订单接口
     *
     * @param outTradeNo
     * @return
     */
    public JSONObject queryOrder(String outTradeNo, boolean isApp);

    /**
     * 统一下单接口
     *
     * @param params
     * @return
     */
    public JSONObject unifiedOrder(Map<String, Object> params);
}
