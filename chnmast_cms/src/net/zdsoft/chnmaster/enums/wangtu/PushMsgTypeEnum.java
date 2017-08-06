/*
 * @(#)ChargeType.java    Created on 2011-2-15
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: AccountType.java 12683 2011-02-18 08:06:04Z xiangll $
 */
package net.zdsoft.chnmaster.enums.wangtu;

/**
 * 推送消息类型
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2015-1-16 上午11:43:08 $
 */
public enum PushMsgTypeEnum {
    /**
     * 竞价通知
     */
    BIDDING(10, "竞价通知"),

    /**
     * 竞价成功
     */
    BIDDING_SUCCESS(20, "竞价成功");

    private String name;
    private int value;

    // 构造方法
    private PushMsgTypeEnum(int value, String name) {
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

    public static PushMsgTypeEnum get(int value) {
        for (PushMsgTypeEnum type : PushMsgTypeEnum.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }
}
