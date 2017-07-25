/*
 * @(#)ChargeType.java    Created on 2011-2-15
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: ChargeType.java 40439 2013-07-15 06:46:56Z muyl $
 */
package net.zdsoft.common.enums;

/**
 * 账户操作类型
 */
public enum ChargeType {

    LOCALE_ADD(10, "线下支付"),

    LOCALE_REDUCE(11, "线下退费"),

    ONLINE_PAYMENT(20, "网上支付"),

    CANCEL_LOCKED_FUNDS(21, "网上退费"),

    ACCOUNT_ADD(30, "余额充值"),

    ACCOUNT_BUY(31, "余额支付");

    private int value;
    private String nameValue;

    ChargeType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    /**
     * 根据value获取对象
     *
     * @return
     */
    public static ChargeType get(int value) {
        for (ChargeType type : ChargeType.values()) {
            if (type.getValue() == value) {
                return type;
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
