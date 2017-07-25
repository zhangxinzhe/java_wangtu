/*
 * @(#)Alipay.java    Created on 2014-6-12
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.alipay.enums;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2014-6-12 下午4:15:04 $
 */
public enum AlipayService {
    /**
     * 支付宝即时到账交易接口
     */
    CREATE_DIRECT_PAY_BY_USER {
        @Override
        public String getValue() {
            return CREATE_DIRECT_PAY_BY_USER_VALUE;
        }

        @Override
        public String getNameValue() {
            return CREATE_DIRECT_PAY_BY_USER_NAME_VALUE;
        }

    },
    /**
     * 支付宝单笔交易查询接口
     */
    SINGLE_TRADE_QUERY {

        @Override
        public String getValue() {
            return SINGLE_TRADE_QUERY_VALUE;
        }

        @Override
        public String getNameValue() {
            return SINGLE_TRADE_QUERY_NAME_VALUE;
        }

    },
    /**
     * 支付宝交易关闭接口
     */
    CLOSE_TRADE {

        @Override
        public String getValue() {
            return CLOSE_TRADE_VALUE;
        }

        @Override
        public String getNameValue() {
            return CLOSE_TRADE_NAME_VALUE;
        }

    },
    /**
     * 支付宝移动端支付
     */
    MOBILE_SECURITYPAY_PAY {

        @Override
        public String getValue() {
            return MOBILE_SECURITYPAY_PAY_VALUE;
        }

        @Override
        public String getNameValue() {
            return MOBILE_SECURITYPAY_PAY_NAME_VALUE;
        }

    },
    /**
     * 手机网页支付授权接口
     */
    ALIPAY_WAP_TRADE_CREATE_DIRECT {

        @Override
        public String getValue() {
            return ALIPAY_WAP_TRADE_CREATE_DIRECT_VALUE;
        }

        @Override
        public String getNameValue() {
            return ALIPAY_WAP_TRADE_CREATE_DIRECT_NAME_VALUE;
        }

    },
    /**
     * 手机网页即时到账交易接口
     */
    ALIPAY_WAP_AUTH_AUTHANDEXECUTE {

        @Override
        public String getValue() {
            return ALIPAY_WAP_AUTH_AUTHANDEXECUTE_VALUE;
        }

        @Override
        public String getNameValue() {
            return ALIPAY_WAP_AUTH_AUTHANDEXECUTE_NAME_VALUE;
        }

    };

    private static final String CREATE_DIRECT_PAY_BY_USER_VALUE = "create_direct_pay_by_user";
    private static final String CREATE_DIRECT_PAY_BY_USER_NAME_VALUE = "支付宝即时到账交易接口";

    private static final String SINGLE_TRADE_QUERY_VALUE = "single_trade_query";
    private static final String SINGLE_TRADE_QUERY_NAME_VALUE = "支付宝单笔交易查询接口";

    private static final String CLOSE_TRADE_VALUE = "close_trade";
    private static final String CLOSE_TRADE_NAME_VALUE = "支付宝交易关闭接口";

    private static final String MOBILE_SECURITYPAY_PAY_VALUE = "mobile.securitypay.pay";
    private static final String MOBILE_SECURITYPAY_PAY_NAME_VALUE = "支付宝移动端支付";

    private static final String ALIPAY_WAP_TRADE_CREATE_DIRECT_VALUE = "alipay.wap.trade.create.direct";
    private static final String ALIPAY_WAP_TRADE_CREATE_DIRECT_NAME_VALUE = "手机网页即时到账授权接口";

    private static final String ALIPAY_WAP_AUTH_AUTHANDEXECUTE_VALUE = "alipay.wap.auth.authAndExecute";
    private static final String ALIPAY_WAP_AUTH_AUTHANDEXECUTE_NAME_VALUE = "手机网页即时到账交易接口";

    public abstract String getValue();

    public abstract String getNameValue();

    /**
     * 判断是否pc服务
     *
     * @param alipayService
     * @return
     */
    public static boolean isPcService(AlipayService alipayService) {
        if (alipayService == AlipayService.CLOSE_TRADE || alipayService == AlipayService.CREATE_DIRECT_PAY_BY_USER
                || alipayService == AlipayService.SINGLE_TRADE_QUERY) {
            return true;
        }
        return false;
    }
}
