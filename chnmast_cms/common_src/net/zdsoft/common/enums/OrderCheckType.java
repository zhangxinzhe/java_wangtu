/*
 * @(#)OrderCheckService.java    Created on 2016年4月6日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

public enum OrderCheckType {
    /** 订单查询 **/
    QUERY_ORDER(1, "订单查询"),

    /** 订单支付 **/
    UNIFIED_ORDER(2, "订单支付"),

    /** 订单关闭 **/
    CLOSE_ORDER(3, "订单关闭"),

    /** 订单检查任务 **/
    TASK_ORDER(4, "订单检查任务");

    private int value;
    private String nameValue;

    OrderCheckType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    /**
     * 根据value获取对象
     *
     * @return
     */
    public static OrderCheckType getStatus(int value) {
        for (OrderCheckType s : OrderCheckType.values()) {
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
