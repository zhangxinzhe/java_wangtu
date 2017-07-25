/*
 * @(#)CodeUseState.java    Created on 2016年3月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 验证码使用状态（0：未使用，1：已使用）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年3月25日 下午2:09:50 $
 */
public enum CodeUseState {
    UNUSE(0, "未使用"), USED(1, "已使用");
    private int value;
    private String nameValue;

    CodeUseState(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static CodeUseState getCodeUseState(int value) {
        for (CodeUseState state : CodeUseState.values()) {
            if (state.getValue() == value) {
                return state;
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
