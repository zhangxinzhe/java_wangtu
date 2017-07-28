/*
 * @(#)OrderType.java    Created on 2017年7月25日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.enums.wangtu;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月25日 上午11:10:50 $
 */
public enum OrderType {

    BIDDING_ORDER(0, "竞价支付"), FOUNDS_BACK(1, "余额提现");

    private int value;
    private String valueName;

    OrderType(int value, String valuename) {
        this.value = value;
        this.valueName = valuename;
    }

    public static OrderType getOrderType(int value) {
        for (OrderType type : OrderType.values()) {
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

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

}
