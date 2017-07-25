/*
 * @(#)ApiUtils.java    Created on 2016年3月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.wechatpay.api;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.util.GUID;

import net.zdsoft.common.utils.HttpUtil;
import net.zdsoft.common.wechatpay.constant.WechatpayConstant;
import net.zdsoft.common.wechatpay.enums.WechatpayTradeType;
import net.zdsoft.common.wechatpay.utils.SignUtils;
import net.zdsoft.common.wechatpay.utils.XmlUtils;

public class UnifiedApiService {
    private static UnifiedApiService api = new UnifiedApiService();
    // 统一下单接口地址
    private static final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static UnifiedApiService getInstance() {
        return api;
    }

    /**
     * 统一下单接口
     *
     * @return <xml> <return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg>
     *         <appid><![CDATA[wx2421b1c4370ec43b]]></appid> <mch_id><![CDATA[10000100]]></mch_id>
     *         <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>
     *         <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign> <result_code><![CDATA[SUCCESS]]></result_code>
     *         <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>
     *         <trade_type><![CDATA[JSAPI]]></trade_type> </xml>
     * @throws Exception
     */
    public JSONObject unifiedOrder(Map<String, Object> params) throws Exception {
        // 随机字符串
        String nonceStr = GUID.generateGUID();
        params.put("nonce_str", nonceStr);
        // 创建签名信息
        WechatpayTradeType tradeType = (WechatpayTradeType) params.get("trade_type");
        boolean isApp = tradeType == WechatpayTradeType.APP;
        String sign = SignUtils.getSign(params, isApp);
        JSONObject json = new JSONObject();
        json.putAll(params);
        // 签名
        json.put("sign", sign);
        // 创建xml参数信息
        String param = XmlUtils.createXmlStr(json);
        // 获取
        String result = HttpUtil.postForm(url, param);
        Document document = DocumentHelper.parseText(result);
        JSONObject parent = new JSONObject();
        XmlUtils.parseXml2Json(document.getRootElement(), parent);
        JSONObject reObj = parent.getJSONObject("xml") == null ? new JSONObject() : parent.getJSONObject("xml");
        if (reObj.isEmpty()) {
            reObj.put("error_code", "return_error");
            reObj.put("error", "返回信息有误");
            return reObj;
        }
        // 校验商户信息合法性
        if (!params.get("appid").equals(reObj.getString("appid"))
                || !params.get("mch_id").equals(reObj.getString("mch_id"))) {
            reObj.put("error_code", "return_error");
            reObj.put("error", "返回商户信息有误");
            return reObj;
        }

        // 校验数据是否合法
        reObj.put("trade_type", params.get("trade_type"));
        String paySign = SignUtils.getSign(reObj, isApp);
        if (!paySign.equals(reObj.getString("sign"))) {
            reObj.put("error_code", "return_error");
            reObj.put("error", "返回数据签名有误");
            return reObj;
        }

        // 业务结果
        if (!"SUCCESS".equals(reObj.getString("return_code"))) {
            reObj.put("error_code", "return_error");
            reObj.put("error", reObj.getString("return_msg"));
        }
        else if (!"SUCCESS".equals(reObj.getString("result_code"))) {
            reObj.put("error_code", "return_error");
            reObj.put("error", reObj.getString("err_code"));
        }
        return reObj;
    }

    public static void main(String[] args) {
        try {
            // String appKey = "7eb20d1e8f354e3aa8da7cb497e77113";
            String body = "音乐大师 场次";
            int totalFee = 1;
            String spbill_create_ip = "192.168.60.113";
            String notifyUrl = "http://m.chnmaster.dev/order/weixinBack.htm";
            String openId = "oTTnwv822mp5b1QpR5q9VsKM1HJI";
            Map<String, Object> map = new HashMap<String, Object>();
            // 公众账号ID
            map.put("appid", WechatpayConstant.appId);
            // 商户号
            map.put("mch_id", WechatpayConstant.mchId);
            map.put("body", body);
            // 商户订单号
            String outTradeNo = GUID.generateGUID();
            System.out.println("out_trade_no:" + outTradeNo);
            map.put("out_trade_no", outTradeNo);
            // 总金额
            map.put("total_fee", Integer.toString(totalFee));
            // 终端IP
            // String IP = URLUtil.getIpAddr(ServletActionContext.getRequest());
            map.put("spbill_create_ip", spbill_create_ip);
            // 通知地址
            map.put("notify_url", notifyUrl);
            // 交易类型
            WechatpayTradeType tradeType = WechatpayTradeType.NATIVE;
            map.put("trade_type", tradeType);
            // 用户标识 trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
            if ("JSAPI".equals(tradeType.getValue())) {
                map.put("openid", openId);
            }

            JSONObject object = UnifiedApiService.getInstance().unifiedOrder(map);
            System.out.println(object.toJSONString());
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
