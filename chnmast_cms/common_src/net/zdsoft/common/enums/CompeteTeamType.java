/*
 * @(#)CompeteTeamType.java    Created on 2016年5月6日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年5月6日 下午4:40:08 $
 */
public enum CompeteTeamType {
    PUBLIC(1, "公开合唱组"),

    PROFESSIONAL(2, "专业合唱组"),

    JUVENILE(3, "少年组"),

    YOUTH(4, "成年组"),

    CHILD_HC(5, "少儿合唱组"),

    AMUL_HC(6, "成人混声合唱组"),

    AMAN_HC(7, "成人男声合唱组"),

    AWOM_HC(8, "成人女声合唱组"),

    OMUL_HC(9, "老年混声合唱组"),

    OMAN_HC(10, "老年男声合唱组"),

    OWOM_HC(11, "老年女声合唱组"),

    DUET_HC(12, "重唱、小合唱组");

    private int value;
    private String nameValue;

    CompeteTeamType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static CompeteTeamType get(int value) {
        for (CompeteTeamType type : CompeteTeamType.values()) {
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
