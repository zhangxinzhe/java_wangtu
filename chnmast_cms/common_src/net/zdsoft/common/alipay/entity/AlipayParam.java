/*
 * @(#)StudentChargeApplyDto.java    Created on 2011-5-26
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: AlipayParamDto.java 38102 2013-05-14 09:58:29Z heyz $
 */
package net.zdsoft.common.alipay.entity;

import java.io.Serializable;
import java.util.Date;

import net.zdsoft.common.alipay.enums.AlipayService;
import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;

/**
 * @author wangzb
 * @version $Revision: 38102 $, $Date: 2013-05-14 17:58:29 +0800 (星期二, 14 五月 2013) $
 */
public class AlipayParam implements Serializable {

    private static final long serialVersionUID = 7673556940958580386L;
    /* 固定值 */
    private final String payGateway = "https://mapi.alipay.com/gateway.do";// 支付网关
    private AlipayService service;// 接口名称
    private final String signType = "MD5";// 签名方式
    private final String inputCharset = "utf-8";// 编码
    private final String paymentType = "1";// 支付方式(人民币)
    private String defaultBank = "CMB";// 招行
    private final String creditCardPay = "Y";// 信用卡支付
    private final String creditCardDefaultDisplay = "Y";// 默认显示信用卡
    private String payMethod = "directPay";// bankPay(网银);creditCard(信用卡)directPay(余额等其他方式);
    private String notifyUrl;// 通知接收URL
    private String returnUrl;// 支付完成后跳转返回的网址URL
    /* 配置参数 */
    private String partner;// 支付宝合作伙伴id (账户内提取)
    private String key;// 密钥
    private String sellerEmail;// 卖家支付宝帐号
    private String rsaPrivateKey; // 公司支付宝RSA私钥
    private String rsaPublicKey; // 支付宝RSA公钥
    private String subject;// 商品名称
    /* 动态值 */
    private String outTradeNo;// 外部交易号(唯一)
    private String totalFee;// 商品总价
    private String sign;// 签名(加密串)
    private String aliapyUrl;// 支付地址(完成路径)
    private Date dealEndTime;
    private boolean isValidateOrder; // 是否验证订单
    private String itBPay; // 需额外开通使用

    public String getPayGateway() {
        return payGateway;
    }

    public AlipayService getService() {
        if (service == null) {
            service = AlipayService.CREATE_DIRECT_PAY_BY_USER;
        }
        return service;
    }

    public void setService(AlipayService service) {
        this.service = service;
    }

    public String getSignType() {
        return signType;
    }

    public String getInputCharset() {
        return inputCharset;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getDefaultBank() {
        return defaultBank;
    }

    public void setDefaultBank(String defaultBank) {
        this.defaultBank = defaultBank;
    }

    public String getCreditCardPay() {
        return creditCardPay;
    }

    public String getCreditCardDefaultDisplay() {
        return creditCardDefaultDisplay;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public String getSign() {
        return sign;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAliapyUrl() {
        return aliapyUrl;
    }

    public void setAliapyUrl(String aliapyUrl) {
        this.aliapyUrl = aliapyUrl;
    }

    public Date getDealEndTime() {
        return dealEndTime;
    }

    public void setDealEndTime(Date dealEndTime) {
        this.dealEndTime = dealEndTime;
    }

    public boolean isValidateOrder() {
        return isValidateOrder;
    }

    public void setValidateOrder(boolean isValidateOrder) {
        this.isValidateOrder = isValidateOrder;
    }

    public String getItBPay() {
        return itBPay;
    }

    public void setItBPay(String itBPay) {
        this.itBPay = itBPay;
    }

}
