/*
 * @(#)HifiCheckStatus.java    Created on 2016年11月24日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 购买hifi会员，对接过程状态检查（0未确定，11下单成功，12下单失败，21付款成功，22付款失败，31更新会员成功，32更新会员失败）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年11月24日 下午7:06:57 $
 */
public enum HifiCheckStatus {
    UNPAY(0, "未确定"), ORDER_SUCCESS(11, "下单成功"), ORDER_FAIL(12, "下单失败"), PAY_SUCCESS(21, "付款成功"), PAY_FAIL(22,
            "付款失败"), UPDATE_SUCCESS(31, "更新会员成功"), UPDATE_FAIL(32, "更新汇演失败");

    private int value;
    private String nameValue;

    HifiCheckStatus(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static HifiCheckStatus get(int value) {
        for (HifiCheckStatus t : HifiCheckStatus.values()) {
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
