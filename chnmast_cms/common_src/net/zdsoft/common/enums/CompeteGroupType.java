/*
 * @(#)GroupType.java    Created on 2016年4月11日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 组别（1教师组民族唱法、2教师组美声唱法 、3学生组民族唱法 、4学生组美声唱法 、5学生组流行唱法）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年4月11日 上午11:29:34 $
 */
public enum CompeteGroupType {

    /**
     * 教师组民族唱法
     */
    TEA_FOLK_STYLE(1, "教师组民族唱法"),

    /**
     * 教师组美声唱法
     */
    TEA_BEL_CANTO(2, "教师组美声唱法"),

    /**
     * 学生组民族唱法
     */
    STU_FOLK_STYLE(3, "学生组民族唱法"),

    /**
     * 学生组美声唱法
     */
    STU_BEL_CANTO(4, "学生组美声唱法"),

    /**
     * 学生组流行唱法
     */
    STU_POPULAR(5, "学生组流行唱法");

    private int value;
    private String nameValue;

    CompeteGroupType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static CompeteGroupType get(int value) {
        for (CompeteGroupType t : CompeteGroupType.values()) {
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
