/*
 * @(#)AgencyType.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 名师类型（1专家老师/大家，2专业老师/名师，3音乐导师）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午11:06:27 $
 */
public enum TeachType {

    /**
     * 专家老师/大家
     */
    SPECIALIST {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return SPECIALIST_NAME;
        }
    },

    /**
     * 专业老师/名师
     */
    SPECIALTY {
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return SPECIALTY_NAME;
        }
    },

    /**
     * 音乐导师【1.4.0.0】
     */
    MUSICTUTOR {
        @Override
        public int getValue() {
            return 3;
        }

        @Override
        public String getNameValue() {
            return MUSICTUTOR_NAME;
        }
    };

    private static final String SPECIALIST_NAME = "专家老师/大家";
    private static final String SPECIALTY_NAME = "专业老师/名师";
    private static final String MUSICTUTOR_NAME = "音乐导师";

    /**
     * 根据值得到类型
     *
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static TeachType get(int value) {
        for (TeachType type : TeachType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    public abstract int getValue();

    public abstract String getNameValue();
}
