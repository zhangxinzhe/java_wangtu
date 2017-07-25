/*
 * @(#)ReturnStatus.java    Created on 2015年12月23日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 课程状态（=T_ORDER_RETURN.STATUS，0正常，10退课处理中，20退课成功，30退课失败，40退费处理中，50退费成功，60退费失败）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月23日 下午3:31:14 $
 */
public enum ReturnStatus {
    NORMAL(0, "正常"), 
    RETURNING(10, "退课处理中"), 
    RETURN_SUCCESS(20, "退课成功"), 
    RETURN_FAILED(30, "退课失败"), 
    MONEY_RETURNING(40, "退费处理中"), 
    MONEY_SUCCESS(50, "退费成功"), 
    MONEY_FAILED(60, "退费失败");

    private int value;
    private String nameValue;

    ReturnStatus(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    /**
     * 根据value获取对象
     *
     * @return
     */
    public static ReturnStatus getStatus(int value) {
        for (ReturnStatus s : ReturnStatus.values()) {
            if (s.getValue() == value) {
                return s;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getNameValue() {
        return nameValue;
    }

    public void setNameValue(String nameValue) {
        this.nameValue = nameValue;
    }

}
