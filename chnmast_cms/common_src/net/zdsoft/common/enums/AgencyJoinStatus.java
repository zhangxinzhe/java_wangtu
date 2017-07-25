/*
 * @(#)AgencyType.java    Created on 2015年12月8日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 申请加盟状态（0：申请中，1：已加盟）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年12月8日 下午5:01:37 $
 */
public enum AgencyJoinStatus {

    /**
     * 申请中
     */
    APPLYING {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getNameValue() {
            return APPLYING_NAME;
        }
    },

    /**
     * 已加盟
     */
    JOINED {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return JOINED_NAME;
        }
    };

    private static final String APPLYING_NAME = "申请中";
    private static final String JOINED_NAME = "已加盟";

    /**
     * 根据值得到类型
     *
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static AgencyJoinStatus get(int value) {
        for (AgencyJoinStatus type : AgencyJoinStatus.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    public abstract int getValue();

    public abstract String getNameValue();
}
