/*
 * @(#)WerXinApiService.java    Created on 2016年3月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.wechatpay.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.utils.URLUtil;
import net.zdsoft.common.wechatpay.api.AccessTokenApiService;
import net.zdsoft.common.wechatpay.api.CloseApiService;
import net.zdsoft.common.wechatpay.api.QueryApiService;
import net.zdsoft.common.wechatpay.api.UnifiedApiService;
import net.zdsoft.common.wechatpay.api.UserInfoApiService;
import net.zdsoft.common.wechatpay.enums.WechatpayTradeType;
import net.zdsoft.common.wechatpay.service.WechatpayApiService;

@Service("wechatpayApiService")
public class WechatpayApiServiceImpl implements WechatpayApiService {

    @Override
    public JSONObject queryUserInfo(String code) {
        JSONObject object = new JSONObject();
        if (StringUtils.isBlank(code)) {
            object.put("error_code", "verify_error");
            object.put("error", "授权码信息有误，请重新输入");
            return object;
        }
        Map<String, String> params = new HashMap<>();
        // 微信授权码
        params.put("code", code);
        // 公众账号ID
        params.put("appid", NetstudyConfig.getParam(BaseConstants.WECHATPAY_APP_ID));
        // 安全码
        params.put("secret", NetstudyConfig.getParam(BaseConstants.WECHATPAY_APP_SECRET));
        try {
            object = AccessTokenApiService.getInstance().getAccessTokenAndOpenId(params);
            // 判断错误信息
            if (object.getString("errcode") != null) {
                object.put("error_code", "return_error");
                object.put("error", object.getString("errmsg"));
                return object;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            object.put("error_code", "connect_error");
            object.put("error", "连接微信失败");
            return object;
        }

        // 获取openid 和accesstoken
        String openId = object.getString("openid");
        String accessToken = object.getString("access_token");
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(openId)) {
            object.put("error_code", "connect_error");
            object.put("error", "连接微信失败");
            return object;
        }
        params.put("openid", openId);
        params.put("access_token", accessToken);
        try {
            object = UserInfoApiService.getInstance().getUserInfo(params);
            // 判断错误信息
            if (object.getString("errcode") != null) {
                object.put("error_code", "return_error");
                object.put("error", object.getString("errmsg"));
                return object;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            object.put("error_code", "connect_error");
            object.put("error", "连接微信失败");
        }
        return object;
    }

    @Override
    public JSONObject queryForOpenId(String code) {
        JSONObject object = new JSONObject();
        if (StringUtils.isBlank(code)) {
            object.put("error_code", "verify_error");
            object.put("error", "授权码信息有误，请重新输入");
            return object;
        }
        Map<String, String> params = new HashMap<>();
        // 微信授权码
        params.put("code", code);
        // 公众账号ID
        params.put("appid", NetstudyConfig.getParam(BaseConstants.WECHATPAY_APP_ID));
        // 安全码
        params.put("secret", NetstudyConfig.getParam(BaseConstants.WECHATPAY_APP_SECRET));
        try {
            object = AccessTokenApiService.getInstance().getAccessTokenAndOpenId(params);
            // 判断错误信息
            if (object.getString("errcode") != null) {
                object.put("error_code", "return_error");
                object.put("error", object.getString("errmsg"));
                return object;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            object.put("error_code", "connect_error");
            object.put("error", "连接微信失败");
            return object;
        }
        return object;
    }

    @Override
    public JSONObject closeOrder(String outTradeNo, boolean isApp) {
        Map<String, Object> params = new HashMap<>();
        params.put("trade_type", isApp ? WechatpayTradeType.APP : WechatpayTradeType.JSAPI);
        if (isApp) {
            // 公众账号ID
            params.put("appid", NetstudyConfig.getParam(BaseConstants.WECHATPAY_APP_APP_ID));
            // 商户号
            params.put("mch_id", NetstudyConfig.getParam(BaseConstants.WECHATPAY_APP_MCH_ID));
            params.put("trade_type", WechatpayTradeType.APP);
        }
        else {
            // 公众账号ID
            params.put("appid", NetstudyConfig.getParam(BaseConstants.WECHATPAY_APP_ID));
            // 商户号
            params.put("mch_id", NetstudyConfig.getParam(BaseConstants.WECHATPAY_MCH_ID));
            params.put("trade_type", WechatpayTradeType.JSAPI);
        }

        // 商户订单号
        params.put("out_trade_no", outTradeNo);
        JSONObject object = null;
        try {
            object = CloseApiService.getInstance().closeOrder(params);
            // 添加错误信息
            if (!"SUCCESS".equals(object.getString("return_code"))) {
                object.put("error_code", "return_error");
                object.put("error", object.getString("return_msg"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            object = new JSONObject();
            object.put("error_code", "connect_error");
            object.put("error", "连接微信失败");
        }
        return object;
    }

    @Override
    public JSONObject queryOrder(String outTradeNo, boolean isApp) {
        Map<String, Object> params = new HashMap<>();
        if (isApp) {
            // 公众账号ID
            params.put("appid", NetstudyConfig.getParam(BaseConstants.WECHATPAY_APP_APP_ID));
            // 商户号
            params.put("mch_id", NetstudyConfig.getParam(BaseConstants.WECHATPAY_APP_MCH_ID));
            params.put("trade_type", WechatpayTradeType.APP);
        }
        else {
            // 公众账号ID
            params.put("appid", NetstudyConfig.getParam(BaseConstants.WECHATPAY_APP_ID));
            // 商户号
            params.put("mch_id", NetstudyConfig.getParam(BaseConstants.WECHATPAY_MCH_ID));
            params.put("trade_type", WechatpayTradeType.JSAPI);
        }

        // 商户订单号
        params.put("out_trade_no", outTradeNo);
        JSONObject object = null;
        try {
            object = QueryApiService.getInstance().queryOrder(params);
        }
        catch (Exception e) {
            e.printStackTrace();
            object = new JSONObject();
            object.put("error_code", "config_error");
            object.put("error", "连接微信失败");
        }
        return object;
    }

    @Override
    public JSONObject unifiedOrder(Map<String, Object> params) {
        JSONObject object = new JSONObject();
        if (MapUtils.isEmpty(params)) {
            object.put("error_code", "verify_error");
            object.put("error", "订单信息有误，请重新输入");
            return object;
        }
        // 检查订单相关信息
        String outTradeNo = (String) params.get("out_trade_no");
        if (StringUtils.isBlank(outTradeNo)) {
            object.put("error_code", "verify_error");
            object.put("error", "订单号信息有误，请重新输入");
            return object;
        }
        // 总金额
        float totalFee = (float) params.get("total_fee");
        if (totalFee <= 0) {
            object.put("error_code", "verify_error");
            object.put("error", "订单金额有误，请重新输入");
            return object;
        }
        params.put("total_fee", (int) (totalFee * 100));
        // 通知地址
        String notifyUrl = (String) params.get("notify_url");
        if (StringUtils.isBlank(notifyUrl)) {
            object.put("error_code", "verify_error");
            object.put("error", "订单回调地址有误，请重新输入");
            return object;
        }
        // 交易类型
        WechatpayTradeType tradeType = (WechatpayTradeType) params.get("trade_type");
        if (tradeType == null) {
            object.put("error_code", "verify_error");
            object.put("error", "交易类型有误，请重新输入");
            return object;
        }
        // 用户标识 trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
        if ("JSAPI".equals(tradeType.getValue())) {
            String openId = (String) params.get("openid");
            if (StringUtils.isBlank(openId)) {
                object.put("error_code", "verify_error");
                object.put("error", "用户标识信息有误，请重新输入");
                return object;
            }

        }
        // 终端IP
        String IP = URLUtil.getIpAddr(ServletActionContext.getRequest());
        object.put("spbill_create_ip", IP);
        if (WechatpayTradeType.APP == tradeType) {
            // 公众账号ID
            params.put("appid", NetstudyConfig.getParam(BaseConstants.WECHATPAY_APP_APP_ID));
            // 商户号
            params.put("mch_id", NetstudyConfig.getParam(BaseConstants.WECHATPAY_APP_MCH_ID));
        }
        else {
            // 公众账号ID
            params.put("appid", NetstudyConfig.getParam(BaseConstants.WECHATPAY_APP_ID));
            // 商户号
            params.put("mch_id", NetstudyConfig.getParam(BaseConstants.WECHATPAY_MCH_ID));
        }
        try {
            object = UnifiedApiService.getInstance().unifiedOrder(params);
        }
        catch (Exception e) {
            e.printStackTrace();
            object = new JSONObject();
            object.put("error_code", "connect_error");
            object.put("error", "连接微信失败");
        }
        return object;
    }
}
