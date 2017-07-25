/*
 * @(#)CourseType.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 状态枚举 <br>
 * （0：正常，1：已取消）<br>
 * （0：正常，1：已下架）<br>
 * （0：正常，1：注销）
 *
 * @author hong
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午11:14:04 $
 */
public enum StatusEunm {

    /**
     * 正常
     */
    NORMAL {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getNameValue() {
            return NORMAL_NAME;
        }

        @Override
        public String getNameValue2() {
            return NORMAL_NAME2;
        }

        @Override
        public String getNameValue3() {
            return NORMAL_NAME3;
        }

        @Override
        public boolean getBooleanValue() {
            return false;
        }
    },

    /**
     * 已取消
     */
    CANCEL {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return CANCEL_NAME;
        }

        @Override
        public String getNameValue2() {
            return CANCEL_NAME2;
        }

        @Override
        public String getNameValue3() {
            return CANCEL_NAME3;
        }

        @Override
        public boolean getBooleanValue() {
            return true;
        }
    };

    private static final String NORMAL_NAME = "正常";
    private static final String CANCEL_NAME = "已取消";

    private static final String NORMAL_NAME2 = "正常";
    private static final String CANCEL_NAME2 = "已下架";

    private static final String NORMAL_NAME3 = "正常";
    private static final String CANCEL_NAME3 = "注销";

    /**
     * 根据值得到类型
     *
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static StatusEunm get(int value) {
        for (StatusEunm type : StatusEunm.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    public abstract int getValue();

    public abstract String getNameValue();

    public abstract String getNameValue2();

    public abstract String getNameValue3();

    public abstract boolean getBooleanValue();
}
