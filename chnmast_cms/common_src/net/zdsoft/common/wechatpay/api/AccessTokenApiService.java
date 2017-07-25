/*
 * @(#)AccessTokenApiService.java    Created on 2016年3月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.wechatpay.api;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.common.utils.HttpUtil;

public class AccessTokenApiService {
    private static AccessTokenApiService api = new AccessTokenApiService();
    // 获取accessToken和openid
    private static final String access_token_openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?";
    // 获取accessToken 2小时内有效，防止多次获取
    private static final String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    public static AccessTokenApiService getInstance() {
        return api;
    }

    /**
     * 获取微信用户AccessToken和OpenId(无需用户授权,scope=snsapi_base时调用)
     *
     * @param params
     * @return {'accessToken':'','openId':''}
     * @throws Exception
     */
    public JSONObject getAccessTokenAndOpenId(Map<String, String> params) throws Exception {
        StringBuffer sendUrl = new StringBuffer();
        sendUrl.append(access_token_openid_url);
        sendUrl.append("appid=" + params.get("appid"));
        sendUrl.append("&secret=" + params.get("secret"));
        sendUrl.append("&code=" + params.get("code"));
        sendUrl.append("&grant_type=authorization_code");
        JSONObject object = HttpUtil.getJson(sendUrl.toString());
        // 判断错误信息
        if (StringUtils.isNotBlank(object.getString("errcode"))) {
            object.put("error_code", "return_error");
            object.put("error", object.getString("errmsg"));
        }
        return object;
    }

    /**
     * 获取微信公众号accessToken（共用）
     *
     * @param params
     * @return {}
     * @throws Exception
     */
    public JSONObject getAccessToken(Map<String, String> params) throws Exception {
        StringBuffer sendUrl = new StringBuffer();
        sendUrl.append(access_token_url);
        sendUrl.append("&appid=" + params.get("appid"));
        sendUrl.append("&secret=" + params.get("secret"));
        JSONObject object = HttpUtil.getJson(sendUrl.toString());
        // 判断错误信息
        if (StringUtils.isNotBlank(object.getString("errcode"))) {
            object.put("error_code", "return_error");
            object.put("error", object.getString("errmsg"));
        }
        return object;
    }
}
