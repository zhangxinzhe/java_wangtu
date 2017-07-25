/*
 * @(#)CompeteApplyType.java    Created on 2016年4月11日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 报名形式（0前台报名，1后台报名）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年4月11日 上午11:25:04 $
 */
public enum CompeteApplyType {
    FORMER(0, "前台报名"), CMS(1, "后台报名");

    private int value;
    private String nameValue;

    CompeteApplyType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static CompeteApplyType get(int value) {
        for (CompeteApplyType type : CompeteApplyType.values()) {
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
