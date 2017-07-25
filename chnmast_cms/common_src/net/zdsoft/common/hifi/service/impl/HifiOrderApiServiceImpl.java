/*
 * @(#)HifiOrderServiceImpl.java    Created on 2016年11月24日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.hifi.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.common.hifi.service.HifiOrderApiService;
import net.zdsoft.common.hifi.util.HifiApiUtil;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年11月24日 下午8:03:56 $
 */
@Service("hifiOrderApiService")
public class HifiOrderApiServiceImpl extends HifiServiceImpl implements HifiOrderApiService {

    @Override
    public Map<String, String> createHifiOrder(String apikey, String productId) {
        Map<String, String> hifiOrderMap = new HashMap<>();
        try {
            JSONObject obj = HifiApiUtil.createHifiOrder(apikey, productId);
            if (isGetData(obj)) {
                String result = obj.getString("result");
                String orderId = obj.getString("orderId");
                hifiOrderMap.put("result", result);
                hifiOrderMap.put("orderId", orderId);
            }
        }
        catch (Exception e) {
            log.error("【调用HiFi接口失败-service-创建hifi订单】" + e.getMessage());
            return null;
        }
        return hifiOrderMap;
    }

    @Override
    public Map<String, String> payHifiOrder(String orderId, int tradeStateValue) {
        Map<String, String> hifiOrderMap = new HashMap<>();
        try {
            String msg = "";
            int result = HifiApiUtil.payHifiOrder(orderId, tradeStateValue);
            if (result == 0) {
                msg = "订单交易成功";
            }
            else if (result == 1) {
                msg = "订单交易失败";
            }
            else if (result == -1) {
                msg = "签名错误";
            }
            else if (result == -2) {
                msg = "支付状态传值错误";
            }
            else if (result == -3) {
                msg = "调用支付接口失败";
            }
            hifiOrderMap.put("result", result + "");
            hifiOrderMap.put("msg", msg);
        }
        catch (Exception e) {
            log.error("【调用HiFi接口失败-service-支付hifi订单】" + e.getMessage());
            return null;
        }
        return hifiOrderMap;
    }

}
