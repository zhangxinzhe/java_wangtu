/*
 * @(#)AlipayLogType.java    Created on 2014-6-12
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.alipay.enums;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2014-6-12 下午6:35:46 $
 */
public enum AlipayLogType {
    BUY {

        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return BUY_NAME_VALUE;
        }

    },
    CHECK {

        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return CHECK_NAME_VALUE;
        }

    },
    CLOSE {

        @Override
        public int getValue() {
            return 3;
        }

        @Override
        public String getNameValue() {
            return CLOSE_NAME_VALUE;
        }
    },
    WAP_AUTH {

        @Override
        public int getValue() {
            return 4;
        }

        @Override
        public String getNameValue() {
            return WAP_AUTH_NAME_VALUE;
        }
    },
    WAP_BUY {

        @Override
        public int getValue() {
            return 5;
        }

        @Override
        public String getNameValue() {
            return WAP_BUY_NAME_VALUE;
        }
    },

    /** 微信下单日志 **/
    WECHATPAY_UNIDIED {
        @Override
        public int getValue() {
            return 6;
        }

        @Override
        public String getNameValue() {
            return WECHATPAY_NAME_VALUE;
        }
    };

    private static final String BUY_NAME_VALUE = "支付日志";

    private static final String CHECK_NAME_VALUE = "订单查询日志";

    private static final String CLOSE_NAME_VALUE = "订单关闭失败日志";
    private static final String WAP_AUTH_NAME_VALUE = "手机端支付宝请求授权日志";

    private static final String WAP_BUY_NAME_VALUE = "手机端支付日志";

    private static final String WECHATPAY_NAME_VALUE = "微信下单日志";

    public abstract int getValue();

    public abstract String getNameValue();
}
