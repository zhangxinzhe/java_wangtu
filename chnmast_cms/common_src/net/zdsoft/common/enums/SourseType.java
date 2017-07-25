/*
 * @(#)SourseType.java    Created on 2016年5月23日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 来源类型
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年5月23日 上午10:31:41 $
 */
public enum SourseType {

    /**
     * 0 后台创建
     */
    BACK_ADD {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getNameValue() {
            return BACK_ADD_NAME;
        }

        @Override
        public String getNameValue2() {
            return BACK_ADD_NAME;
        }

        @Override
        public String getNameValue3() {
            return BACK_ADD_NAME;
        }
    },

    /**
     * 1 前台申请/申请发布/申请加盟
     */
    FRONT_ADD {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return FRONT_ADD_NAME;
        }

        @Override
        public String getNameValue2() {
            return FRONT_ADD_NAME2;
        }

        @Override
        public String getNameValue3() {
            return FRONT_ADD_NAME3;
        }
    };

    private static final String BACK_ADD_NAME = "后台创建";

    private static final String FRONT_ADD_NAME = "前台申请";
    private static final String FRONT_ADD_NAME2 = "申请发布";
    private static final String FRONT_ADD_NAME3 = "申请加盟";

    /**
     * 根据值得到类型
     *
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static SourseType get(int value) {
        for (SourseType type : SourseType.values()) {
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

}
