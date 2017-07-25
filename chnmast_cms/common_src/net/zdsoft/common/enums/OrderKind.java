/*
 * @(#)OrderKind.java    Created on 2016年4月11日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 订单类型（0课程/音乐会，3活动，4视频点播，5HIFI会员，6IOS乐币）（1，2保留，拆分课程/音乐会）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年4月11日 上午11:01:14 $
 */
public enum OrderKind {

    /**
     * 课程/音乐会
     */
    COURSE(0, "课程/音乐会"),

    /**
     * 主题活动
     */
    COMPETE(3, "主题活动"),

    /**
     * 点播视频
     */
    VOD(4, "点播视频"),

    /**
     * hifi会员
     */
    HIFI(5, "HIFI会员"),

    /**
     * IOS重置
     */
    IOS_RECHARGE(6, "IOS乐币");

    private int value;
    private String nameValue;

    OrderKind(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static OrderKind get(int value) {
        for (OrderKind kind : OrderKind.values()) {
            if (kind.getValue() == value) {
                return kind;
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
