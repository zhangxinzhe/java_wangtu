/* 
 * @(#)CmsRoleType.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.enums;


/**
 * 后台角色类型 1平台超级管理员角色，2平台自定义角色
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-26 上午11:07:18 $
 */
public enum CmsRoleType {
    SUPER_ROLE {

        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return SUPER_ROLE_NAME;
        }

    },
    CUSTOM_ROLE {

        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return CUSTOM_ROLE_NAME;
        }

    };

    private static final String SUPER_ROLE_NAME = "平台超级管理员";
    private static final String CUSTOM_ROLE_NAME = "平台自定义角色";

    public abstract int getValue();

    public abstract String getNameValue();

    /**
     * 根据值得到是否类型
     * 
     * @param value
     * @return 如果没有得到对应的是否类型，返回null
     */
    public static CmsRoleType get(int value) {
        for (CmsRoleType type : CmsRoleType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }
}
