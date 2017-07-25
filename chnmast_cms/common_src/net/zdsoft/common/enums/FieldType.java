/*
 * @(#)FieldType.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 栏目类型（0轮播图片，1精彩视频，2伴奏音频，3活动专题-轮播图片）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午11:37:06 $
 */
public enum FieldType {

    /**
     * 轮播图片
     */
    PINTURE {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getNameValue() {
            return PINTURE_NAME;
        }
    },

    /**
     * 精彩视频
     */
    VIDEO {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return VIDEO_NAME;
        }
    },

    /**
     * 伴奏音频
     */
    AUDIO {
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return AUDIO_NAME;
        }

    },

    /**
     * 活动专题-轮播图片
     */
    COMPETE_PINTURE {
        @Override
        public int getValue() {
            return 3;
        }

        @Override
        public String getNameValue() {
            return COMPETE_PINTURE_NAME;
        }
    };

    private static final String PINTURE_NAME = "轮播图片";
    private static final String VIDEO_NAME = "精彩视频";
    private static final String AUDIO_NAME = "伴奏音频";
    private static final String COMPETE_PINTURE_NAME = "活动专题-轮播图片";

    public abstract int getValue();

    public abstract String getNameValue();

    /**
     * 根据值得到是否类型
     *
     * @param value
     * @return 如果没有得到对应的是否类型，返回null
     */
    public static FieldType get(int value) {
        for (FieldType type : FieldType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
