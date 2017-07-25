/*
 * @(#)OrderReportReturnStatus.java    Created on 2016年2月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 是否已退课（0未退课，1退课处理中，2已退课）<br/>
 * 是否已退费（0未退费，1退费处理中，2已退费）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年2月22日 下午8:02:23 $
 */
public enum OrderReportReturnStatus {

    COURSE_NO(0, "未退课"),
    COURSE_ING(1, "退课处理中"),
    COURSE_RETURNED(2, "已退课"),

    MONEY_NO(0, "未退费"),
    MONEY_ING(1, "退课处理中"),
    MONEY_RETURNED(2, "已退课"),;

    private int value;
    private String nameValue;

    OrderReportReturnStatus(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    /**
     * 根据value获取对象
     *
     * @return
     */
    public static OrderReportReturnStatus get(int value) {
        for (OrderReportReturnStatus type : OrderReportReturnStatus.values()) {
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
