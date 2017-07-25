/*
 * @(#)QueryApiUtils.java    Created on 2016年3月25日
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

public class QueryApiService {
    private static QueryApiService api = new QueryApiService();
    // 订单查询接口
    private static final String url = "https://api.mch.weixin.qq.com/pay/orderquery";

    public static QueryApiService getInstance() {
        return api;
    }

    /**
     * 订单查询
     *
     * @param params
     * @return <xml> <return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg>
     *         <appid><![CDATA[wx2421b1c4370ec43b]]></appid> <mch_id><![CDATA[10000100]]></mch_id>
     *         <device_info><![CDATA[1000]]></device_info> <nonce_str><![CDATA[TN55wO9Pba5yENl8]]></nonce_str>
     *         <sign><![CDATA[BDF0099C15FF7BC6B1585FBB110AB635]]></sign> <result_code><![CDATA[SUCCESS]]></result_code>
     *         <openid><![CDATA[oUpF8uN95-Ptaags6E_roPHg7AG0]]></openid> <is_subscribe><![CDATA[Y]]></is_subscribe>
     *         <trade_type><![CDATA[MICROPAY]]></trade_type> <bank_type><![CDATA[CCB_DEBIT]]></bank_type>
     *         <total_fee>1</total_fee> <fee_type><![CDATA[CNY]]></fee_type>
     *         <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>
     *         <out_trade_no><![CDATA[1415757673]]></out_trade_no> <attach><![CDATA[订单额外描述]]></attach>
     *         <time_end><![CDATA[20141111170043]]></time_end> <trade_state><![CDATA[SUCCESS]]></trade_state> </xml>
     * @throws Exception
     */
    public JSONObject queryOrder(Map<String, Object> params) throws Exception {
        // 随机字符串
        String nonceStr = GUID.generateGUID();
        params.put("nonce_str", nonceStr);
        WechatpayTradeType tradeType = (WechatpayTradeType) params.get("trade_type");
        boolean isApp = tradeType == WechatpayTradeType.APP;
        params.remove("trade_type");
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
            Map<String, Object> map = new HashMap<String, Object>();
            // 公众账号ID
            map.put("appid", WechatpayConstant.appId);
            // 商户号
            map.put("mch_id", WechatpayConstant.mchId);
            // 商户订单号
            String outTradeNo = "3E0GI6UWG6ZKFT0YSVCFEEY63WZ40722";
            map.put("out_trade_no", outTradeNo);
            JSONObject object = QueryApiService.getInstance().queryOrder(map);
            System.out.println(object.toJSONString());
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
