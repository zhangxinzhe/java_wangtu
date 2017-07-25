/*
 * @(#)OrderStatusType.java    Created on 2015年12月23日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 *
 * 状态类型（0待付款，11交易成功-用户完成，12交易成功-后台完成，13交易成功-系统完成，21交易失败-后台取消，22交易失败-用户取消，23交易失败-超时未付款）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月23日 下午2:19:53 $
 */
public enum OrderStatusType {
    EMPTY(0, "待付款"),

    /** 交易成功-系统完成 **/
    USER_SUCCESS(11, "交易成功-用户完成"),

    /** 交易成功-后台完成 **/
    CMS_SUCCESS(12, "交易成功-后台完成"),

    /** 交易成功-系统完成 **/
    SYSTEM_SUCCESS(13, "交易成功-系统完成"),

    /** 交易失败-后台取消 **/
    CMS_CANCEL(21, "交易失败-后台取消"),

    /** 交易失败-用户取消 **/
    USER_CANCEL(22, "交易失败-用户取消"),

    /** 交易失败-超时未付款 **/
    TIMEOUT(23, "交易失败-超时未付款");

    private int value;
    private String nameValue;

    OrderStatusType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    /**
     * 根据value获取对象
     *
     * @return
     */
    public static OrderStatusType getOrderStatusType(int value) {
        for (OrderStatusType type : OrderStatusType.values()) {
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
