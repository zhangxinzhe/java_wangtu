/* 
 * @(#)ChargeType.java    Created on 2011-2-15
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: AccountType.java 12683 2011-02-18 08:06:04Z xiangll $
 */
package net.zdsoft.common.enums;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2013-11-19 下午04:20:28 $
 */
public enum SendStatusEnum {
    /** 待发送 **/
    WAIT(0, "待发送"),
    /** 已发送 **/
    SUCCESS(1, "已发送");

    private int value;
    private String name;

    private SendStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public String getNameValue() {
        return this.name;
    }

    /**
     * 根据值得到是否类型
     * 
     * @param value
     * @return 如果没有得到对应的是否类型，返回null
     */
    public static SendStatusEnum get(int value) {
        for (SendStatusEnum type : SendStatusEnum.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }
}
