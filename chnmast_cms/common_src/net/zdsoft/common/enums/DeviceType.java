/*
 * @(#)DeviceType.java    Created on 2015年12月23日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 设备分类（0PC，1手机）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月23日 下午2:52:16 $
 */
public enum DeviceType {
    PC(0, "PC"), MOBILE(1, "手机");

    private int value;
    private String nameValue;

    DeviceType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    /**
     * 根据value获取对象
     *
     * @return
     */
    public static DeviceType getDeviceType(int value) {
        for (DeviceType d : DeviceType.values()) {
            if (d.getValue() == value) {
                return d;
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
