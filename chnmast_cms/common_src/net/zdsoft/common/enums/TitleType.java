/*
 * @(#)TitleType.java    Created on 2016年4月9日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 主委会头衔（9执行主席、1顾问、2主席、3副主席、4委员、5办公室主任、6办公室副主任、7办公室执行主任、8办公室执行副主任）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年4月9日 下午1:24:35 $
 */
public enum TitleType {

    /**
     * 执行主席
     */
    EXE_CHAIR(9, "执行主席"),

    /**
     * 顾问
     */
    ADVISER(1, "顾问"),

    /**
     * 主席
     */
    CHAIRMAN(2, "主席"),

    /**
     * 副主席
     */
    VICE_PRESIDENT(3, "副主席"),

    /**
     * 委员
     */
    MEMBER(4, "委员"),

    /**
     * 办公室主任
     */
    DIRECTOR(5, "办公室主任"),

    /**
     * 办公室副主任
     */
    D_DIRECTOR(6, "办公室副主任"),

    /**
     * 办公室执行主任
     */
    ZX_DIRECTOR(7, "办公室执行主任"),

    /**
     * 办公室执行副主任
     */
    ZX_D_DIRECTOR(8, "办公室执行副主任");

    private int value;
    private String nameValue;

    TitleType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static TitleType get(int value) {
        for (TitleType type : TitleType.values()) {
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
