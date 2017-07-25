/*
 * @(#)UnionType.java    Created on 2016年5月16日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * ${user}$
 */
package net.zdsoft.common.enums;

/**
 * 联合会员类型<br>
 * 1个人教育类，2个人装备类，3团体教育类，4团体教育类
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年5月16日 下午5:00:45 $
 */
public enum UnionMemberType {
    /**
     * 个人教育
     */
    PERSONAL_EDUCATION(1, "个人教育类"),

    /**
     * 个人装备
     */
    PERSONAL_EQUIPMENT(2, "个人装备类"),

    /**
     * 团体教育
     */
    TEAM_EDUCATION(3, "团体教育类"),

    /**
     * 团体装备
     */
    TEAM_EQUIPMENT(4, "团体装备类");

    private int value;
    private String nameValue;

    UnionMemberType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static UnionMemberType get(int value) {
        for (UnionMemberType type : UnionMemberType.values()) {
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
