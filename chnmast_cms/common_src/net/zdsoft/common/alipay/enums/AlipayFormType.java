/* 
 * @(#)AlipayFormType.java    Created on 2013-9-6
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.alipay.enums;

/**
 * @author Administrator @
 * @version $Revision: 1.0 $, $Date: 2013-9-6 下午7:41:08 $
 */
public enum AlipayFormType {
    SHOPPING {
        @Override
        public int getValue() {
            return SHOPPING_VALUE;
        }

        @Override
        public String getNameValue() {
            return SHOPPING_NAME_VALUE;
        }

    },
    CHARGE {

        @Override
        public int getValue() {
            return CHARGE_VALUE;
        }

        @Override
        public String getNameValue() {
            return CHARGE_NAME_VALUE;
        }

    };

    public abstract int getValue();

    public abstract String getNameValue();

    public static AlipayFormType get(int value) {
        for (AlipayFormType type : AlipayFormType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    private static final int SHOPPING_VALUE = 1;
    private static final String SHOPPING_NAME_VALUE = "购物";

    private static final int CHARGE_VALUE = 0;
    private static final String CHARGE_NAME_VALUE = "充值";

}
