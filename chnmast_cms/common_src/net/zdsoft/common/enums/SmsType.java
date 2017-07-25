/*
 * @(#)ChargeType.java    Created on 2011-2-15
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: AccountType.java 12683 2011-02-18 08:06:04Z xiangll $
 */
package net.zdsoft.common.enums;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2013-11-19 下午04:13:40 $
 */
public enum SmsType {
    /** 找回密码 **/
    GETPWD(1, "找回密码"), /** 后台通知 **/
    INFORM(2, "后台通知"),
    /** 前台验证码获取 **/
    /** 前台用户注册 **/
    REGISTER(3, "前台注册"), /** 其他 **/
    /** 活动报名 **/
    COMPETEATTEND(4, "活动报名"), USERBIND(5, "账号绑定"), /** 微课对接 **/
    OTHER(0, "其他");

    private int value;
    private String nameValue;

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

    private SmsType(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    /**
     * 根据值得到是否类型
     *
     * @param value
     * @return 如果没有得到对应的是否类型，返回null
     */
    public static SmsType get(int value) {
        for (SmsType type : SmsType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }
}
