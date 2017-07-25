/*
 * @(#)OrderCheckStatus.java    Created on 2015年12月23日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 付款确认状态（ 0未查询 1查询中 2检查成功,支付宝已付款 3检查成功,支付宝未付款 4检查成功,支付宝无订单 5检查失败）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月23日 下午2:37:59 $
 */
public enum OrderCheckStatus {

    UNCHECK(0, "未查询"),

    CHECKING(1, "查询中"),

    CHECKED_ALIPAY_PAYED(2, "检查成功,支付宝已付款"),

    CHECKED_ALIPAY_UNPAY(3, "检查成功,支付宝未付款"),

    CHECKED_NO_ALIPAY(4, "检查成功,支付宝无订单"),

    CHECK_FAILED(5, "检查失败");

    private int value;
    private String nameValue;

    OrderCheckStatus(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    /**
     * 根据value获取对象
     *
     * @return
     */
    public static OrderCheckStatus getStatus(int value) {
        for (OrderCheckStatus s : OrderCheckStatus.values()) {
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
