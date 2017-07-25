/*
 * @(#)TradeError.java    Created on 2016年3月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.wechatpay.enums;

/**
 * 微信交易错误代码
 *
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2016年3月25日 下午2:55:08 $
 */
public enum WechatpayTradeError {
    /** 商户无此接口权限 **/
    NOAUTH("NOAUTH", "商户未开通此接口权限"),

    /** 用户帐号余额不足 **/
    NOTENOUGH("NOTENOUGH", "用户帐号余额不足"),

    /** 商户订单已支付 **/
    ORDERPAID("ORDERPAID", "商户订单已支付，无需重复操作"),

    /** 订单已关闭 **/
    ORDERCLOSED("ORDERCLOSED", "当前订单已关闭，无法支付"),

    /** 系统超时 **/
    SYSTEMERROR("SYSTEMERROR", "系统超时"),

    /** 参数中缺少APPID **/
    APPID_NOT_EXIST("APPID_NOT_EXIST", "参数中缺少APPID"),

    /** 参数中缺少MCHID **/
    MCHID_NOT_EXIST("MCHID_NOT_EXIST", "参数中缺少MCHID"),

    /** appid和mch_id不匹配 **/
    APPID_MCHID_NOT_MATCH("APPID_MCHID_NOT_MATCH", "appid和mch_id不匹配"),

    /** 缺少必要的请求参数 **/
    LACK_PARAMS("LACK_PARAMS", "缺少必要的请求参数"),

    /** 商户订单号重复 **/
    OUT_TRADE_NO_USED("OUT_TRADE_NO_USED", "商户订单号重复"),

    /** 签名错误 **/
    SIGNERROR("SIGNERROR", "签名错误"),

    /** XML格式错误 **/
    XML_FORMAT_ERROR("XML_FORMAT_ERROR", "XML格式错误"),

    /** 未使用post传递参数 **/
    REQUIRE_POST_METHOD("REQUIRE_POST_METHOD", "未使用post传递参数"),

    /** post数据为空 **/
    POST_DATA_EMPTY("POST_DATA_EMPTY", "post数据为空"),

    /** 编码格式错误 **/
    NOT_UTF8("NOT_UTF8", "编码格式错误");

    private String value;
    private String nameValue;

    private WechatpayTradeError(String value, String nameValue) {
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
}
