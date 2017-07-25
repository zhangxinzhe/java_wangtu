/*
 * @(#)PaltType.java    Created on 2016年7月14日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2016年7月14日 下午2:05:31 $
 */
public enum PlatType {

    /**
     * 微课平台
     */
    WEIKE(0, "微课平台"),

    /**
     * 微信平台
     */
    WEIXIN(1, "微信平台"),

    /**
     * QQ
     */
    QQ_PART(2, "QQ"),

    /**
     * hifi音乐
     */
    HIFI(3, "hifi音乐"),

    /**
     * 第三方平台
     */
    OTHER_PART(9, "其他平台");

    private int value;
    private String name;

    private PlatType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public String getNameValue() {
        return this.name;
    }

    public static PlatType get(int value) {
        for (PlatType type : PlatType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }

}
