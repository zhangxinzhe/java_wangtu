/*
 * @(#)InfoType.java    Created on 2015年12月1日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 信息类型（1公告、2新闻、3热点追踪、4视频回顾）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月1日 上午11:03:22 $
 */
public enum InfoType {

    /**
     * 公告
     */
    NOTICE(1, "公告"),

    /**
     * 新闻
     */
    INFORMATION(2, "新闻"),

    /**
     * 热点追踪
     */
    HOTSPOT(3, "热点追踪"),

    /**
     * 视频回顾
     */
    VIDEO_REVIEW(4, "视频回顾");

    private int value;
    private String nameValue;

    InfoType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static InfoType getValue(int value) {
        for (InfoType type : InfoType.values()) {
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
