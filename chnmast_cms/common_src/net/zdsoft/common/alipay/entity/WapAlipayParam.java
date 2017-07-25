/*
 * @(#)StudentChargeApplyDto.java    Created on 2011-5-26
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: AlipayParamDto.java 38102 2013-05-14 09:58:29Z heyz $
 */
package net.zdsoft.common.alipay.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import net.zdsoft.common.alipay.enums.AlipayService;
import net.zdsoft.common.alipay.util.SignUtil;
import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;

/**
 * 手机端支付参数
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2015-5-22 下午7:48:26 $
 */
public class WapAlipayParam implements Serializable {
    private static final long serialVersionUID = 1L;
    /* 固定值 */
    private static String payGateway = "http://wappaygw.alipay.com/service/rest.htm";// 支付网关
    private AlipayService service;// 接口名称
    private String partner;// 支付宝合作伙伴id (账户内提取)
    private String key;// 密钥
    private String rsaPrivateKey;// 私钥
    private String rsaPublicKey;// 公钥
    private String sellerEmail;// 卖家支付宝账号
    private String inputCharset = "utf-8";// 编码
    // 签名方式，选择项：0001(RSA)、MD5
    public static String signType = "MD5";// 无线的产品中，签名方式为rsa时，sign_type需赋值为0001而不是RSA
    public static String format = "xml";// 返回格式
    public static String v = "2.0";// 返回格式
    private String reqData;
    private String sign;

    public String getPayGateway() {
        return payGateway;
    }

    public void setPayGateway(String paygateway) {
        payGateway = paygateway;
    }

    public AlipayService getService() {
        return service;
    }

    public void setService(AlipayService service) {
        this.service = service;
    }

    public String getInputCharset() {
        return inputCharset;
    }

    public void setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        WapAlipayParam.signType = signType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        WapAlipayParam.format = format;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        WapAlipayParam.v = v;
    }

    public String getReqData() {
        return reqData;
    }

    public void setReqData(String reqData) {
        this.reqData = reqData;
    }

    public String getPartner() {
        if (partner == null) {
            partner = NetstudyConfig.getParam(BaseConstants.ALIPAY_PARTNER);
        }
        return partner;
    }

    public String getKey() {
        if (key == null) {
            key = NetstudyConfig.getParam(BaseConstants.ALIPAY_KEY);
        }
        return key;
    }

    public String getSellerEmail() {
        if (sellerEmail == null) {
            sellerEmail = NetstudyConfig.getParam(BaseConstants.ALIPAY_SELLER_EMAIL);
        }
        return sellerEmail;
    }

    public String getRsaPrivateKey() {
        if (rsaPrivateKey == null) {
            rsaPrivateKey = NetstudyConfig.getParam(BaseConstants.ALIPAY_RSA_PRIVATE_KEY);
        }
        return rsaPrivateKey;
    }

    public String getRsaPublicKey() {
        if (rsaPublicKey == null) {
            rsaPublicKey = NetstudyConfig.getParam(BaseConstants.ALIPAY_RSA_PUBLIC_KEY);
        }
        return rsaPublicKey;
    }

    public String getSign() {
        if (sign == null) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("service", service.getValue());
            params.put("partner", partner);
            params.put("_input_charset", inputCharset);
            params.put("sec_id", signType);
            params.put("format", format);
            params.put("v", v);
            params.put("req_data", reqData);
            sign = SignUtil.signMd5(params, key, inputCharset);
        }

        return sign;
    }

}
