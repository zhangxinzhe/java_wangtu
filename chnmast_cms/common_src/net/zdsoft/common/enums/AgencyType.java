/*
 * @(#)AgencyType.java    Created on 2015年10月30日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 基地类型（1高校，2培训机构，3琴行）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 上午11:06:27 $
 */
public enum AgencyType {

    /**
     * 高校
     */
    GAOXIAO {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return GAOXIAO_NAME;
        }
    },

    /**
     * 培训机构
     */
    PEIXUNJG {
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return PEIXUNJG_NAME;
        }
    },

    /**
     * 琴行
     */
    QINGHANG {
        @Override
        public int getValue() {
            return 3;
        }

        @Override
        public String getNameValue() {
            return QINGHANG_NAME;
        }
    };

    private static final String GAOXIAO_NAME = "高校";
    private static final String PEIXUNJG_NAME = "培训机构";
    private static final String QINGHANG_NAME = "琴行";

    /**
     * 根据值得到类型
     *
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static AgencyType get(int value) {
        for (AgencyType type : AgencyType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    public abstract int getValue();

    public abstract String getNameValue();
}
