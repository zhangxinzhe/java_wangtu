/*
 * @(#)PicObjType.java    Created on 2015年12月4日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 图片视频来源类型
 *
 * @author wangly
 * @version $Revision: 1.0 $, $Date: 2015年12月4日 上午11:51:42 $
 */
public enum PicObjType {

    AGENCY {
        @Override
        public int getValue() {
            return AGENCY_VALUE;
        }

        @Override
        public String getNameValue() {
            return AGENCY_NAME;
        }
    },
    MASTER {

        @Override
        public int getValue() {
            return MASTER_VALUE;
        }

        @Override
        public String getNameValue() {
            return MASTER_NAME;
        }

    },
    STUDENT {
        @Override
        public int getValue() {
            return STUDENT_VALUE;
        }

        @Override
        public String getNameValue() {
            return STUDENT_NAME;
        }
    };
    /**
     * 得到类型的整数值
     *
     * @return
     */
    public abstract int getValue();

    public abstract String getNameValue();

    private static final int AGENCY_VALUE = 1;
    private static final String AGENCY_NAME = "培训基地";

    private static final int MASTER_VALUE = 2;
    private static final String MASTER_NAME = "名师大家";

    private static final int STUDENT_VALUE = 3;
    private static final String STUDENT_NAME = "优秀学员";

}
