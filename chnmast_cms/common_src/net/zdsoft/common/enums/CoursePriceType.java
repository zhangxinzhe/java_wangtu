/*
 * @(#)CoursePriceType.java    Created on 2015年12月21日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 价格类型（0正式，1预售）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年12月21日 上午10:57:47 $
 */
public enum CoursePriceType {
    /**
     * 正式价格
     */
    FORMAL {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getNameValue() {
            return FORMAL_NAME;
        }
    },

    /**
     * 预售价格
     */
    PRESELL {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return PRESELL_NAME;
        }

    };

    private static final String FORMAL_NAME = "正式价格";
    private static final String PRESELL_NAME = "预售价格";

    /**
     * 得到类型的整数值
     *
     * @return
     */
    public abstract int getValue();

    public abstract String getNameValue();

    /**
     * 根据值得到是否类型
     *
     * @param value
     * @return 如果没有得到对应的是否类型，返回null
     */
    public static CoursePriceType get(int value) {
        for (CoursePriceType type : CoursePriceType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

}
