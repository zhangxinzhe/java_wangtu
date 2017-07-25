/*
 * @(#)PayType.java    Created on 2015年12月23日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 付款方式（0未确定，1支付宝，2余额，3微信，4线下支付，5免费，6后台支付，7苹果支付，8乐币支付）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月23日 下午2:25:30 $
 */
public enum PayType {
    /** 未确定 **/
    UNPAY(0, "未确定"),

    /** 支付宝 **/
    ALIPAY(1, "支付宝"),

    /** 余额 **/
    REMAIN(2, "余额"),

    /** 微信支付 **/
    WECHATPAY(3, "微信支付"),

    /** 线下支付 **/
    OFFLINE(4, "线下支付"),

    /** 免费 **/
    FREE(5, "免费"),

    /** 后台支付 **/
    BACK(6, "后台支付"),

    /** 苹果支付 **/
    APPLEPAY(7, "苹果支付"),

    /** 乐币支付 **/
    YUEBIPAY(8, "乐币支付");

    private int value;
    private String nameValue;

    PayType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    /**
     * 根据value获取对象
     *
     * @return
     */
    public static PayType getPayType(int value) {
        for (PayType t : PayType.values()) {
            if (t.getValue() == value) {
                return t;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getNameValue() {
        return nameValue;
    }

    public void setNameValue(String nameValue) {
        this.nameValue = nameValue;
    }

}
