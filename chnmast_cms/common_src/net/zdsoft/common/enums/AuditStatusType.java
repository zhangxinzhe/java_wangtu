/*
 * @(#)UnionAuditStatus.java    Created on 2016年5月16日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * ${user}$
 */
package net.zdsoft.common.enums;

/**
 * 0未提交申请，1审核中，2审核通过，3审核不通过
 */
public enum AuditStatusType {

    /**
     * 0 未提交申请
     */
    UNSUBMIT(0, "未提交申请"),

    /**
     * 1 审核中
     */
    AUDITING(1, "审核中"),

    /**
     * 2 审核通过
     */
    AUDITPASS(2, "审核通过"),

    /**
     * 3 审核不通过
     */
    AUDITNOPASS(3, "审核不通过");

    private int value;
    private String nameValue;

    AuditStatusType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static AuditStatusType get(int value) {
        for (AuditStatusType type : AuditStatusType.values()) {
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
