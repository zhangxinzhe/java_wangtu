/* 
 * @(#)RecordDetailType.java    Created on 2013-9-5
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2013-9-5 上午10:24:05 $
 */
public enum RecordDetailType {
    REDUCE {

        @Override
        public int getValue() {
            return REDUCE_VALUE;
        }

        @Override
        public String getNameValue() {
            return REDUCE_NAME_VALUE;
        }

    },
    ADD {

        @Override
        public int getValue() {
            return ADD_VALUE;
        }

        @Override
        public String getNameValue() {
            return ADD_NAME_VALUE;
        }

    };

    public abstract int getValue();

    public abstract String getNameValue();

    public static RecordDetailType get(int value) {
        for (RecordDetailType type : RecordDetailType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }

    private static final int ADD_VALUE = 0;
    private static final String ADD_NAME_VALUE = "入账";

    private static final int REDUCE_VALUE = 1;
    private static final String REDUCE_NAME_VALUE = "出账";

}
