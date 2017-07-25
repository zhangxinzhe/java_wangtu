/*
 * @(#)CourseContentType.java    Created on 2015年11月3日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 商品类型（1基地课程，2音乐会，3.活动专题，4点播视频，5HIFI会员，6IOS乐币）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年11月3日 下午2:16:48 $
 */
public enum CourseContentType {
    /**
     * 基地课程
     */
    COURSE {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return COURSE_NAME;
        }
    },

    /**
     * 音乐会
     */
    CONCERT {
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return CONCERT_NAME;
        }
    },

    /**
     * 主题活动
     */
    COMPETE {
        @Override
        public int getValue() {
            return 3;
        }

        @Override
        public String getNameValue() {
            return COMPETE_NAME;
        }
    },

    /**
     * 点播视频
     */
    VOD {
        @Override
        public int getValue() {
            return 4;
        }

        @Override
        public String getNameValue() {
            return VOD_NAME;
        }
    },

    /**
     * HIFI会员
     */
    HIFI {
        @Override
        public int getValue() {
            return 5;
        }

        @Override
        public String getNameValue() {
            return HIFI_NAME;
        }
    },

    /**
     * IOS乐币
     */
    IOS_RECHARGE {
        @Override
        public int getValue() {
            return 6;
        }

        @Override
        public String getNameValue() {
            return IOS_RECHARGE_NAME;
        }

    };

    private static final String COURSE_NAME = "基地课程";
    private static final String CONCERT_NAME = "音乐会";
    private static final String COMPETE_NAME = "主题活动";
    private static final String VOD_NAME = "点播视频";
    private static final String HIFI_NAME = "HIFI会员";
    private static final String IOS_RECHARGE_NAME = "IOS乐币";

    /**
     * 根据值得到类型
     *
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static CourseContentType get(int value) {
        for (CourseContentType type : CourseContentType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    public abstract int getValue();

    public abstract String getNameValue();
}
