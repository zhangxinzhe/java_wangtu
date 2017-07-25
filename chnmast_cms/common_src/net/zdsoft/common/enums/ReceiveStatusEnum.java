/* 
 * @(#)ChargeType.java    Created on 2011-2-15
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: AccountType.java 12683 2011-02-18 08:06:04Z xiangll $
 */
package net.zdsoft.common.enums;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2013-11-19 下午04:30:37 $
 */
public enum ReceiveStatusEnum {
    /**
     * 发送网关
     */
    GATEWAY {
        @Override
        public int getValue() {
            return GATEWAY_VALUE;
        }

        @Override
        public String getNameValue() {
            return GATEWAY_NAME;
        }
    },
    /**
     * 发送成功
     */
    SUCCESS {
        @Override
        public int getValue() {
            return SUCCESS_VALUE;
        }

        @Override
        public String getNameValue() {
            return SUCCESS_NAME;
        }
    },
    /**
     * 网关失败
     */
    GATEWAY_FAILURE {
        @Override
        public int getValue() {
            return GATEWAY_FAILURE_VALUE;
        }

        @Override
        public String getNameValue() {
            return GATEWAY_FAILURE_NAME;
        }
    },
    /**
     * 发送失败
     */
    FAILURE {
        @Override
        public int getValue() {
            return FAILURE_VALUE;
        }

        @Override
        public String getNameValue() {
            return FAILURE_NAME;
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
    public static ReceiveStatusEnum get(int value) {
        for (ReceiveStatusEnum type : ReceiveStatusEnum.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }

    private static final int GATEWAY_VALUE = 0;
    private static final String GATEWAY_NAME = "发送到短信网关";

    private static final int SUCCESS_VALUE = 1;
    private static final String SUCCESS_NAME = "发送成功";

    private static final int GATEWAY_FAILURE_VALUE = 2;
    private static final String GATEWAY_FAILURE_NAME = "发送到网关失败";

    private static final int FAILURE_VALUE = 3;
    private static final String FAILURE_NAME = "发送短信失败";
}
