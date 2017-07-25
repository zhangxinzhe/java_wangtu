/*
 * @(#)CompeteType.java    Created on 2016年5月6日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 活动类型（10神州唱响；20黄龙奖合唱比赛；21黄龙奖乐队比赛···）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年5月6日 下午2:35:26 $
 */
public enum CompeteType {

    /**
     * 神州唱响
     */
    SZCX(10, "神州唱响"),

    /**
     * 黄龙奖合唱比赛
     */
    HLJ_HC(20, "黄龙奖-合唱比赛"),

    /**
     * 黄龙奖乐队比赛
     */
    HLJ_YD(21, "黄龙奖-乐队比赛");

    private int value;
    private String nameValue;

    CompeteType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static CompeteType get(int value) {
        for (CompeteType type : CompeteType.values()) {
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
