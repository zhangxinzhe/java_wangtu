/*
 * @(#)AuditStatus.java    Created on 2016年4月11日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 审核状态（0：未审核，1审核通过，2：审核失败）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年4月11日 上午11:48:52 $
 */
public enum AuditStatus {
    UNAUDIT(0, "未审核"), AUDITPASS(1, "审核通过"), AUDITFAIL(2, "审核失败");

    private int value;
    private String nameValue;

    AuditStatus(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static AuditStatus get(int value) {
        for (AuditStatus s : AuditStatus.values()) {
            if (s.getValue() == value) {
                return s;
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
