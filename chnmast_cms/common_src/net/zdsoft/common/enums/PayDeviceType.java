/*
 * @(#)PayDevice.java    Created on 2016年2月24日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

import net.zdsoft.common.utils.OSUtil;

/**
 * 支付设备
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年2月24日 下午3:21:05 $
 */
public enum PayDeviceType {
    PC(0, "PC"), ANDROID(1, "android"), IOS(2, "iphone"), OTHER(9, "其他");

    private int value;
    private String nameValue;

    PayDeviceType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static PayDeviceType get(int value) {
        for (PayDeviceType p : PayDeviceType.values()) {
            if (p.getValue() == value) {
                return p;
            }
        }
        return null;
    }

    /**
     * 通过操作系统获取支付设备信息
     * 
     * @return
     */
    public static PayDeviceType getByOs() {
        String os = OSUtil.getClientOs();
        if ("android".equals(os)) {
            return PayDeviceType.ANDROID;
        }
        else if ("iphone".equals(os)) {
            return PayDeviceType.IOS;
        }
        else if ("mac".equals(os) || "pc".equals(os)) {
            return PayDeviceType.PC;
        }
        else {
            return PayDeviceType.OTHER;
        }
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
