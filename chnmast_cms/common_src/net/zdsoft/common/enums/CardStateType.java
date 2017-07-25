/* 
 * @(#)CardGenarateStatesType.java    Created on 2011-2-14
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: CardGenarateStatesType.java 12292 2011-02-15 01:34:02Z xiangll $
 */
package net.zdsoft.common.enums;

/**
 * @author xiangll
 * @version $Revision: 12292 $, $Date: 2011-02-15 09:34:02 +0800 (星期二, 2011-02-15) $
 */
public enum CardStateType {

    /**
     * 未充值
     */
    NORMAL {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getNameValue() {
            return CARDGENERATE_STATE_NORMAL_NAME;
        }
    },
    /**
     * 已充值
     */
    USED {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return CARDGENERATE_STATE_USED_NAME;
        }
    },
    /**
     * 已注销
     */
    CANCEL {
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return CARDGENERATE_STATE_CANCEL_NAME;
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
     * 根据值得到类型
     * 
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static CardStateType get(int value) {
        for (CardStateType type : CardStateType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }

    public static final String CARDGENERATE_STATE_NORMAL_NAME = "未充值";
    public static final String CARDGENERATE_STATE_USED_NAME = "已充值";
    public static final String CARDGENERATE_STATE_CANCEL_NAME = "注销";
}
