/*
 * @(#)UserInfoApiService.java    Created on 2016年3月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.wechatpay.api;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.common.utils.HttpUtil;

public class UserInfoApiService {
    private static UserInfoApiService api = new UserInfoApiService();
    // 订单查询接口
    private static final String url = "https://api.weixin.qq.com/sns/userinfo?";

    public static UserInfoApiService getInstance() {
        return api;
    }

    /**
     * 获取用户信息（需要用户授权,scope=snsapi_userinfo时调用）
     *
     * @param params
     * @return {'openId':'':'sex':"1/2/0(未知)",'nickname':'昵称','headimgurl':'头像路径'...}
     */
    public JSONObject getUserInfo(Map<String, String> params) throws Exception {
        StringBuffer sendUrl = new StringBuffer();
        sendUrl.append(url);
        sendUrl.append("access_token=" + params.get("access_token"));
        sendUrl.append("&openid=" + params.get("openid"));
        sendUrl.append("&lang=zh_CN");
        JSONObject object = HttpUtil.getJson(sendUrl.toString());
        // 判断错误信息
        if (StringUtils.isNotBlank(object.getString("errcode"))) {
            object.put("error_code", "return_error");
            object.put("error", object.getString("errmsg"));
        }
        return object;
    }

}
