/*
 * @(#)OrderReturnReasonType.java    Created on 2016年2月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 退课原因编码（0其他原因，1报错课，2电脑、网络问题上不了课，3课程不满意）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年2月22日 下午5:00:11 $
 */
public enum OrderReturnReasonType {

    /**
     * 报错课
     */
    MISTAKE(1, "报错课"), 
    /**
     * 电脑、网络问题上不了课
     */
    DEVICEPROBLEM(2, "电脑、网络问题上不了课"), 
    /**
     * 课程不满意
     */
    SCHOOL(3, "课程不满意"), 
    /**
     * 其他原因
     */
    OTHER(0, "其他原因");

    private int value;
    private String name;

    private OrderReturnReasonType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public String getNameValue() {
        return this.name;
    }

    /**
     * 根据值得到是否类型
     *
     * @param value
     * @return 如果没有得到对应的是否类型，返回null
     */
    public static OrderReturnReasonType get(int value) {
        for (OrderReturnReasonType type : OrderReturnReasonType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

}
