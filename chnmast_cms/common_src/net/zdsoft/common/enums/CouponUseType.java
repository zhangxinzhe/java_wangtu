/*
 * @(#)UseType.java    Created on 2016年4月19日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * ${user}$
 */
package net.zdsoft.common.enums;

/**
 * 使用类型<br>
 * 使用次数类型:0代表多次，1代表一次
 *
 * @author zhuyd
 *
 */
public enum CouponUseType {

    /**
     * 使用多次
     */
    USE_MANY_TIMES {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getNameValue() {
            return USE_MANY_TIMES_NAME;
        }
    },
    /**
     * 使用一次
     */
    USE_ONLY_ONE_TIME {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return USE_ONLY_ONE_TIME_NAME;
        }
    };

    /**
     * 得到类型的整数值
     */
    public abstract int getValue();

    /**
     * 得到类型名称
     */
    public abstract String getNameValue();

    /**
     * 根据值得到类型
     *
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static CouponUseType get(int value) {
        for (CouponUseType type : CouponUseType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    private static final String USE_MANY_TIMES_NAME = "多次";
    private static final String USE_ONLY_ONE_TIME_NAME = "一次";

}
