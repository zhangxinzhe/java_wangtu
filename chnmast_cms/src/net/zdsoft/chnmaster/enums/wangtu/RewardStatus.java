/*
 * @(#)RewardStatus.java    Created on 2017年7月11日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.enums.wangtu;

/**
 * 悬赏状态
 *
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月11日 下午2:27:09 $
 */

public enum RewardStatus {

    CREATE(1, "创建"), PUBLISH(2, "发布"), DOING(3, "已接单"), FINISH(4, "完成");

    private int value;
    private String nameValue;

    RewardStatus(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static RewardStatus getStatus(int value) {
        for (RewardStatus status : RewardStatus.values()) {
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
