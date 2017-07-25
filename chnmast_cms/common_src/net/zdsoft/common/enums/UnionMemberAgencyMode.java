/*
 * @(#)Agency.java    Created on 2016年5月16日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * ${user}$
 */
package net.zdsoft.common.enums;

/**
 * 对应T_USER_UNION_MEMBER表中的AGENCY_MODE <br>
 * 注:NONE(0, "空"), PRODUCTION(1, "生产"), SALE(2, "销售")
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年5月16日 下午5:20:14 $
 */
public enum UnionMemberAgencyMode {

    NONE(0, "空"), PRODUCTION(1, "生产"), SALE(2, "销售");
    private int value;
    private String nameValue;

    UnionMemberAgencyMode(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static UnionMemberAgencyMode get(int value) {
        for (UnionMemberAgencyMode type : UnionMemberAgencyMode.values()) {
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
