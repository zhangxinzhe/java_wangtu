/*
 * @(#)CouponCreateType.java    Created on 2016年8月8日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 生成类型（0单张生成；1批量生成）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年8月8日 下午5:58:54 $
 */
public enum CouponCreateType {

    /**
     * 0单张生成
     */
    CREATE_SINGLE(0, "单张生成"),

    /**
     * 1批量生成
     */
    CREATE_BATCH(1, "批量生成");

    private int value;
    private String nameValue;

    CouponCreateType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static CouponCreateType get(int value) {
        for (CouponCreateType s : CouponCreateType.values()) {
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
