/* 
 * @(#)DogNoExType.java    Created on 2014-3-25
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 加密狗加密码是否永不过期状态枚举
 * 
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2014-3-25 下午1:40:18 $
 */
public enum DogNoExpireType {

    /**
     * 加密狗密码永不过期
     */
    YES {

        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return YES_NAME;
        }

    },
    /**
     * 加密狗密码不是永不过期
     */
    NO {

        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getNameValue() {
            return NO_NAME;
        }

    };

    /**
     * 得到类型的整数值
     * 
     * @return
     */
    public abstract int getValue();

    public abstract String getNameValue();

    /**
     * 根据值得到类型
     * 
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static DogNoExpireType get(int value) {
        for (DogNoExpireType type : DogNoExpireType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    private static final String YES_NAME = "是";
    private static final String NO_NAME = "否";
}
