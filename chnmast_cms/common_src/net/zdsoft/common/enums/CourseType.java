/*
 * @(#)CourseType.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 课程类型（11大师班，12普及班，21演唱会，22毕业生音乐会）<br>
 * 内容分类（1：基地课程，2：音乐会）
 *
 * @author hong
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午11:14:04 $
 */
public enum CourseType {

    /**
     * 大师班
     */
    MASTER {
        @Override
        public int getValue() {
            return 11;
        }

        @Override
        public String getNameValue() {
            return MASTER_NAME;
        }
    },

    /**
     * 普及版
     */
    ORDINARY {
        @Override
        public int getValue() {
            return 12;
        }

        @Override
        public String getNameValue() {
            return ORDINARY_NAME;
        }
    },

    /**
     * 演唱会
     */
    CONCERT {
        @Override
        public int getValue() {
            return 21;
        }

        @Override
        public String getNameValue() {
            return CONCERT_NAME;
        }
    },

    /**
     * 毕业生音乐会
     */
    GRADUATESPARTY {
        @Override
        public int getValue() {
            return 22;
        }

        @Override
        public String getNameValue() {
            return GRADUATESPARTY_NAME;
        }
    },
    /**
     * 其他
     */
    OTHER {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getNameValue() {
            return OTHER_NAME;
        }
    };
    private static final String MASTER_NAME = "大师班";
    private static final String ORDINARY_NAME = "普及班";
    private static final String CONCERT_NAME = "演唱会";
    private static final String GRADUATESPARTY_NAME = "毕业生音乐会";
    private static final String OTHER_NAME = "其他";

    /**
     * 根据值得到类型
     *
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static CourseType get(int value) {
        for (CourseType type : CourseType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    public abstract int getValue();

    public abstract String getNameValue();
}
