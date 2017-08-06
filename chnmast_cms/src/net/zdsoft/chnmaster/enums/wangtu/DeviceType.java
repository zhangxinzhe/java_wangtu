/*
 * @(#)ChargeType.java    Created on 2011-2-15
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: AccountType.java 12683 2011-02-18 08:06:04Z xiangll $
 */
package net.zdsoft.chnmaster.enums.wangtu;

/**
 * 设备类型
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2015-1-16 上午11:43:08 $
 */
public enum DeviceType {
    /**
     * 安卓
     */
    ANDROID(0, "安卓"),

    /**
     * 苹果
     */
    IOS(1, "苹果");

    private String name;
    private int value;

    // 构造方法
    private DeviceType(int value, String name) {
        this.name = name;
        this.value = value;
    }

    public static DeviceType get(int value) {
        for (DeviceType type : DeviceType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }

    public String getNameValue() {
        return name;
    }

    public void setNameValue(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
