/*
 * @(#)CouponCodeRule.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.coupon;

import net.zdsoft.common.entity.BaseEntity;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午2:34:16 $
 */
public class CouponCodeRule extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 批次编号（1-4位）
     */
    private String batchCode;

    /**
     * 优惠码5-8位编号（规则号）
     */
    private int code1;

    /**
     * 优惠码9-12位（向下减1-10以内的随机数）
     */
    private int code2;

    /**
     * 优惠码规则状态，0表示可用，1表示无效
     */
    private int status;

    /**
     * @return Returns the batchCode.
     */
    public String getBatchCode() {
        return batchCode;
    }

    /**
     * @param batchCode
     *            The batchCode to set.
     */
    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    /**
     * @return Returns the code1.
     */
    public int getCode1() {
        return code1;
    }

    /**
     * @param code1
     *            The code1 to set.
     */
    public void setCode1(int code1) {
        this.code1 = code1;
    }

    /**
     * @return Returns the code2.
     */
    public int getCode2() {
        return code2;
    }

    /**
     * @param code2
     *            The code2 to set.
     */
    public void setCode2(int code2) {
        this.code2 = code2;
    }

    /**
     * @return Returns the status.
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(int status) {
        this.status = status;
    }

}
