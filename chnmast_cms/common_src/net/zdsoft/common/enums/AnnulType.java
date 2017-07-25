/*
 * @(#)AnnulType.java    Created on 2015年12月23日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 优惠类型（0无，10整课购买，11后台优惠，12优惠券）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月23日 下午3:02:35 $
 */
public enum AnnulType {

    NO_ANNUL(0, "无减免"),

    COURSE_ANNUL(10, "整课购买"),

    SYSTEM_ANNUL(11, "后台优惠"),

    COUPON(12, "优惠券");

    private int value;
    private String nameValue;

    AnnulType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    /**
     * 根据value获取对象
     *
     * @return
     */
    public static AnnulType getAnnulType(int value) {
        for (AnnulType type : AnnulType.values()) {
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
