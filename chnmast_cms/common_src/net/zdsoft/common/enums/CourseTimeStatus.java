/*
 * @(#)CourseTimeStatus.java    Created on 2016年4月19日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 课次进行状态
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年4月19日 上午10:33:40 $
 */
public enum CourseTimeStatus {
    UNSTART(0, "未开始"), DOING(1, "进行中"), FINISH(9, "结束");

    private int value;
    private String nameValue;

    CourseTimeStatus(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public CourseTimeStatus get(int value) {
        for (CourseTimeStatus status : CourseTimeStatus.values()) {
            if (status.getValue() == value) {
                return status;
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
