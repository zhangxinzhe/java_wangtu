/*
 * @(#)CourseType.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 状态（0：未发布，1已发布，2：已下架）
 *
 * @author hong
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午11:14:04 $
 */
public enum CourseStatusEunm {

    /**
     * 未发布
     */
    NOT_PUBLISH {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getNameValue() {
            return NOT_PUBLISH_NAME;
        }
    },

    /**
     * 已发布
     */
    PUBLISHED {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return PUBLISHED_NAME;
        }

    },

    /**
     * 已下架
     */
    CANCEL {
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return CANCEL_NAME;
        }

    };

    private static final String NOT_PUBLISH_NAME = "未发布";
    private static final String PUBLISHED_NAME = "已发布";
    private static final String CANCEL_NAME = "已下架";

    /**
     * 根据值得到类型
     *
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static CourseStatusEunm get(int value) {
        for (CourseStatusEunm type : CourseStatusEunm.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    public abstract int getValue();

    public abstract String getNameValue();

}
