/*
 * @(#)OrderStatus.java    Created on 2015年12月23日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 订单状态（0待付款，1交易成功，2交易失败）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月23日 下午2:14:08 $
 */
public enum OrderStatus {
    UNPAY(0, "待付款"), SUCCESS(1, "交易成功"), FAILED(2, "交易失败");

    private int value;
    private String nameValue;

    OrderStatus(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    /**
     * 根据value值回去对象
     *
     * @return
     */
    public static OrderStatus getOrderStatus(int value) {
        for (OrderStatus s : OrderStatus.values()) {
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

    /**
     * 通过状态类型获取订单状态
     */
    public static OrderStatus getOrderStatusBYStatusType(OrderStatusType statusType) {
        OrderStatus status = null;
        if (statusType == null) {
            status = null;
        }
        else if (statusType == OrderStatusType.USER_SUCCESS || statusType == OrderStatusType.CMS_SUCCESS
                || statusType == OrderStatusType.SYSTEM_SUCCESS) {
            status = OrderStatus.SUCCESS;
        }
        else {
            status = OrderStatus.FAILED;
        }
        return status;

    }

}
