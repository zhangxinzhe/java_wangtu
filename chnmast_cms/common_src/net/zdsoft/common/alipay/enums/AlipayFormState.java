/* 
 * @(#)ChargeType.java    Created on 2011-2-15
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: AlipayOrderType.java 16492 2011-06-01 07:50:56Z wangzb $
 */
package net.zdsoft.common.alipay.enums;


/**
 * @author xiangll
 * @version $Revision: 16492 $, $Date: 2011-06-01 15:50:56 +0800 (星期三, 01 六月 2011) $
 */
public enum AlipayFormState {

    WAIT {
        @Override
        public int getValue() {
            return WAIT_VALUE;
        }

        @Override
        public String getNameValue() {
            return WAIT_STATUS;
        }
    },
    FAILURE {
        @Override
        public int getValue() {
            return FAILURE_VALUE;
        }

        @Override
        public String getNameValue() {
            return FAILURE_STATUS;
        }
    },
    SUCCESS {
        @Override
        public int getValue() {
            return SUCCESS_VALUE;
        }

        @Override
        public String getNameValue() {
            return SUCCESS_STATUS;
        }
    },
    CLOSE {
        @Override
        public int getValue() {
            return CLOSE_VALUE;
        }

        @Override
        public String getNameValue() {
            return CLOSE_STATUS;
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
    public static AlipayFormState get(int value) {
        for (AlipayFormState type : AlipayFormState.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }

    private static final int WAIT_VALUE = 0;
    private static final int FAILURE_VALUE = 1;
    private static final int SUCCESS_VALUE = 2;
    private static final int CLOSE_VALUE = 3;

    private static final String WAIT_STATUS = "待处理";
    private static final String FAILURE_STATUS = "失败";
    private static final String SUCCESS_STATUS = "成功";
    private static final String CLOSE_STATUS = "关闭";

}
