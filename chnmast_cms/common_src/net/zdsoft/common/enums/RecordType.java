/*
 * @(#)RecordType.java    Created on 2016年12月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 交易记录类型（0充值，1退费，10购买，11提现）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年12月22日 下午5:15:03 $
 */
public enum RecordType {

    /** 充值 **/
    CHARGE(0, "充值"),

    /** 退费 **/
    RETURN(1, "退费"),

    /** 购买 **/
    BUY(10, "购买"),

    /** 提现 **/
    WITHDRAW(11, "提现");

    private int value;
    private String nameValue;

    RecordType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    /**
     * 根据value获取对象
     */
    public static RecordType getRecordType(int value) {
        for (RecordType t : RecordType.values()) {
            if (t.getValue() == value) {
                return t;
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
