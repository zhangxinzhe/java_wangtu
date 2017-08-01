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
	UNPAY(0, "未支付"), PAY(1, "已支付"), SUCCESS(2, "竞价成功"),  FINISH(3, "任务完成"),  FAIL(4, "竞价失败"),  USER_CANCEL(5, "用户撤销"),  PUBLISHER_CANCEL(6, "发布撤销");

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
