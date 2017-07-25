/*
 * @(#)ChargeType.java    Created on 2011-2-15
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: ChargeType.java 40439 2013-07-15 06:46:56Z muyl $
 */
package net.zdsoft.common.enums;

/**
 * 内容类型（0无，1外部连接，2编辑内容，3视频，4音频）（共用）<br>
 * <br>
 * 数据表：应字段，使用的枚举：<br>
 * 首页（t_index_adv）contentType：（0、1、2、3、4）<br>
 * 公告新闻（t_index_info）contentType：（1、2）<br>
 * 帮助主题（t_help_theme）linkType：（1、2）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午11:26:52 $
 */
public enum ContentType {

    /**
     * 无
     */
    NONE {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getNameValue() {
            return NONE_NAME;
        }
    },

    /**
     * 外部链接
     */
    LINK {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return LINK_NAME;
        }
    },

    /**
     * 编辑内容
     */
    CONTENT {
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return CONTENT_NAME;
        }
    },

    /**
     * 视频
     */
    VIDEO {
        @Override
        public int getValue() {
            return 3;
        }

        @Override
        public String getNameValue() {
            return VIDEO_NAME;
        }
    },

    /**
     * 音频
     */
    AUDIO {
        @Override
        public int getValue() {
            return 4;
        }

        @Override
        public String getNameValue() {
            return AUDIO_NAME;
        }
    };

    private static final String NONE_NAME = "无";
    private static final String LINK_NAME = "外部链接";
    private static final String CONTENT_NAME = "编辑内容";
    private static final String VIDEO_NAME = "视频";
    private static final String AUDIO_NAME = "音频";

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
    public static ContentType get(int value) {
        for (ContentType type : ContentType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

}
