/*
 * @(#)CourseProgressEunm.java    Created on 2015年11月24日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 进度状态（0项目待定，1预售/预定，2售票中，3演出开始，4结束）
 *
 * @author hongxu
 * @version $Revision: 1.0 $, $Date: 2015年11月24日 下午7:36:52 $
 */
public enum CourseProgressEunm {

    /**
     * 0项目待定
     */
    WAIT {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getNameValue() {
            return WAIT_NAME;
        }
    },

    /**
     * 1预售/预定
     */
    BOOKING {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return BOOKING_NAME;
        }
    },

    /**
     * 2售票中
     */
    SALING {
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return SALING_NAME;
        }
    },

    /**
     * 3演出开始
     */
    STARTED {
        @Override
        public int getValue() {
            return 3;
        }

        @Override
        public String getNameValue() {
            return STARTED_NAME;
        }
    },

    /**
     * 4结束
     */
    ENDED {
        @Override
        public int getValue() {
            return 4;
        }

        @Override
        public String getNameValue() {
            return ENDED_NAME;
        }
    };

    private static final String WAIT_NAME = "项目待定";
    private static final String BOOKING_NAME = "预售/预定";
    private static final String SALING_NAME = "售票中";
    private static final String STARTED_NAME = "演出开始";
    private static final String ENDED_NAME = "已结束";

    /**
     * 根据值得到类型
     *
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static CourseProgressEunm get(int value) {
        for (CourseProgressEunm type : CourseProgressEunm.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    public abstract int getValue();

    public abstract String getNameValue();
}
