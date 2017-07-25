/* 
 * @(#)CmsUserType.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.enums;

/**
 * 1.超级管理员 2.平台普通管理员
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-30 下午6:03:52 $
 */
public enum CmsUserType {

    SUPER_ADMIN {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return SUPER_ADMIN_NAME;
        }

    },
    GENERAL_ADMIN {
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return GENERAL_ADMIN_NAME;
        }
    };
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
    public static CmsUserType get(int value) {
        for (CmsUserType type : CmsUserType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }

    private static final String SUPER_ADMIN_NAME = "超级管理员";
    private static final String GENERAL_ADMIN_NAME = "平台普通管理员";
}
