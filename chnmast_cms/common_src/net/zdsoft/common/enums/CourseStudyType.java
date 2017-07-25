/*
 * @(#)CourseStudyType.java    Created on 2015年12月21日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 上课形式（0在线直播，1现场听课/现场听音乐会，2点播）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年12月21日 上午10:57:47 $
 */
public enum CourseStudyType {
    /**
     * 在线直播
     */
    ONLINE {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getNameValue() {
            return ONLINE_NAME;
        }

        @Override
        public String getNameValue2() {
            return ONLINE_NAME;
        }
    },

    /**
     * 现场听课/现场听音乐会
     */
    LOCALE {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return LOCALE_COURSE_NAME;
        }

        @Override
        public String getNameValue2() {
            return LOCALE_CONCERT_NAME;
        }

    },

    /**
     * 点播
     */
    VOD {
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return VOD_NAME;
        }

        @Override
        public String getNameValue2() {
            return VOD_NAME;
        }
    };

    private static final String ONLINE_NAME = "直播";
    private static final String VOD_NAME = "点播";
    private static final String LOCALE_COURSE_NAME = "现场听课";
    private static final String LOCALE_CONCERT_NAME = "现场观看";

    /**
     * 得到类型的整数值
     *
     * @return
     */
    public abstract int getValue();

    public abstract String getNameValue();

    public abstract String getNameValue2();

    /**
     * 根据值得到是否类型
     *
     * @param value
     * @return 如果没有得到对应的是否类型，返回null
     */
    public static CourseStudyType get(int value) {
        for (CourseStudyType type : CourseStudyType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

}
