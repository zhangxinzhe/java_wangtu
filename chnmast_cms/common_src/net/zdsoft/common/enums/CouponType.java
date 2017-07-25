/*
 * @(#)CouponType.java    Created on 2016年8月8日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 优惠类型（0折扣优惠；1金额优惠）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年8月8日 下午5:55:50 $
 */
public enum CouponType {

    /**
     * 0折扣优惠
     */
    COUPON_DISCOUNT(0, "折扣优惠"),

    /**
     * 1金额优惠
     */
    COUPON_AMOUNT(1, "金额优惠");

    private int value;
    private String nameValue;

    CouponType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static CouponType get(int value) {
        for (CouponType s : CouponType.values()) {
            if (s.getValue() == value) {
                return s;
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
