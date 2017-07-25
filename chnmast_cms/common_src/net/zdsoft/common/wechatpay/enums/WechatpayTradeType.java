/*
 * @(#)TradeType.java    Created on 2016年3月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.wechatpay.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 微信交易类型 <br>
 * JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付*@author Administrator*@version $Revision:1.0 $,$Date:2016 年3月25日 下午2:32:00 $
 */
public enum WechatpayTradeType {
    /** 公众号支付 **/
    JSAPI("JSAPI", "公众号支付"),

    /** 原生扫码支付 **/
    NATIVE("NATIVE", "原生扫码支付"),

    /** app支付 **/
    APP("APP", "app支付");

    private String value;
    private String nameValue;

    private WechatpayTradeType(String value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNameValue() {
        return nameValue;
    }

    public void setNameValue(String nameValue) {
        this.nameValue = nameValue;
    }

    public static WechatpayTradeType get(String value) {
        WechatpayTradeType tradeType = null;
        if (StringUtils.isBlank(value)) {
            return tradeType;
        }
        for (WechatpayTradeType type : WechatpayTradeType.values()) {
            if (type.getValue().equals(value)) {
                tradeType = type;
                break;
            }
        }
        return tradeType;
    }
}
