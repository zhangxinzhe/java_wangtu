/* 
 * @(#)Column.java    Created on 2015-5-11
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 栏目配置（1.LOGO;2.页脚内容;3.友情链接;4.客服电话;5.二维码;）
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2015-5-11 下午4:23:29 $
 */
public enum ColumnElement {
    /**
     * LOGO
     */
    LOGO {

        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return LOGO_NAME;
        }

    },
    /**
     * 页脚内容
     */
    FOOTER {

        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return FOOTER_NAME;
        }

    },
    /**
     * 友情链接
     */
    LINK {

        @Override
        public int getValue() {
            return 3;
        }

        @Override
        public String getNameValue() {
            return LINK_NAME;
        }

    },
    /**
     * 客服电话
     */
    SERVICE_TEL {

        @Override
        public int getValue() {
            return 4;
        }

        @Override
        public String getNameValue() {
            return SERVICE_TEL_NAME;
        }

    },
    /**
     * 二维码
     */
    QUICK_RESPONSE {

        @Override
        public int getValue() {
            return 5;
        }

        @Override
        public String getNameValue() {
            return QUICK_RESPONSE_NAME;
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
    public static ColumnElement get(int value) {
        for (ColumnElement type : ColumnElement.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    public static String getNameValue(int value) {
        for (ColumnElement type : ColumnElement.values()) {
            if (type.getValue() == value) {
                return type.getNameValue();
            }
        }
        return null;
    }

    private static final String LOGO_NAME = "LOGO";
    private static final String FOOTER_NAME = "页脚内容";
    private static final String LINK_NAME = "友情链接";
    private static final String SERVICE_TEL_NAME = "客服电话";
    private static final String QUICK_RESPONSE_NAME = "二维码";
}
