/*
 * @(#)OrderReturnStatusType.java    Created on 2016年2月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 状态类型（0无，21退课成功-无需审核，22退课成功-管理员审核，51退费成功-系统处理，52退费成功-人工打款）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年2月22日 下午5:17:43 $
 */
public enum OrderReturnStatusType {

    EMPTY(0, "无"), 
    COURSE_NO_CHECK(21, "退课成功-无需审核"), 
    COURSE_CHECKED(22, "退课成功-管理员审核"), 
    MONEY_SYS(51, "退费成功-系统处理"), 
    MONEY_HAND(52, "退费成功-人工打款");

    private int value;
    private String nameValue;

    OrderReturnStatusType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    /**
     * 根据value获取对象
     *
     * @return
     */
    public static OrderReturnStatusType get(int value) {
        for (OrderReturnStatusType type : OrderReturnStatusType.values()) {
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
