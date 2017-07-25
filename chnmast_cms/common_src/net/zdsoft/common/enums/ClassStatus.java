/* 
 * @(#)ChargeType.java    Created on 2011-2-15
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: AccountType.java 12683 2011-02-18 08:06:04Z xiangll $
 */
package net.zdsoft.common.enums;

/**
 * 
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2015-1-16 上午11:43:08 $
 */
public enum ClassStatus {
    /**
     * 未开始
     */
    before(1, "未开始"),
    /**
     * 倒计时，即将进入
     */
    wait(2, "即将进入"),
    /**
     * 进入课堂
     */
    in(3, "进入课程"),
    /**
     * 已结束
     */
    after(4, "已结束");

    private String name;
    private int value;

    // 构造方法
    private ClassStatus(int value, String name) {
        this.name = name;
        this.value = value;
    }

    public String getNameValue() {
        return name;
    }

    public void setNameValue(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
