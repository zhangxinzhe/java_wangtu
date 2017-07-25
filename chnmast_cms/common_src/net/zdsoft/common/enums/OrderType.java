/*
 * @(#)ReportType.java    Created on 2015年12月22日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 报名方式（0前台报名，1后台报名）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月22日 上午10:30:55 $
 */
public enum OrderType {
    FRONT_REPORT(0, "前台报名"), CMS_REPORT(1, "后台报名");

    private int value;
    private String nameValue;

    OrderType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    /**
     * 根据value获取
     *
     * @return
     */
    public static OrderType getOrderType(int value) {
        for (OrderType r : OrderType.values()) {
            if (r.getValue() == value) {
                return r;
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
