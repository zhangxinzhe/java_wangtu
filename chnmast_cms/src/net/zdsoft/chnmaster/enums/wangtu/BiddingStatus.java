/*
 * @(#)BiddingStatus.java    Created on 2017年7月17日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.enums.wangtu;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月17日 下午3:57:09 $
 */
public enum BiddingStatus {
    UNPAY(0, "未支付"), PAY(1, "已支付"), APPLY(0, "竞价中"), SUCCESS(1, "竞价成功"), FAIL(2, "竞价失败");

    private int value;
    private String nameValue;

    BiddingStatus(int value, String nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    public static BiddingStatus getStatus(int value) {
        for (BiddingStatus status : BiddingStatus.values()) {
            if (status.getValue() == value) {
                return status;
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
