/*
 * @(#)CourseButType.java    Created on 2016年2月29日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 购买类型，0整课购买，1选课次购买
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年2月29日 下午3:19:55 $
 */
public enum CourseBuyType {
    /** 整课购买 **/
    ALLBUY(0, "整课购买"),

    /** 选课次购买 **/
    PARTBUY(1, "选课次购买");

    private int value;
    private String nameValue;

    CourseBuyType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static CourseBuyType getValue(int value) {
        for (CourseBuyType t : CourseBuyType.values()) {
            if (t.getValue() == value) {
                return t;
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
