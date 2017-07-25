/*
 * @(#)CompeteStage.java    Created on 2016年4月9日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.enums;

/**
 * 赛程（1初赛，2复赛，3半决赛，4决赛）
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年4月9日 下午1:06:04 $
 */
public enum CompeteStage {
    FIRST(1, "初赛"), SECOND(2, "复赛"), THIRD(3, "半决赛"), FOURTH(4, "决赛");

    private int value;
    private String nameValue;

    CompeteStage(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static CompeteStage get(int value) {
        for (CompeteStage stage : CompeteStage.values()) {
            if (stage.getValue() == value) {
                return stage;
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
