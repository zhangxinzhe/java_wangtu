/*
 * @(#)CouponCode.java    Created on 2016年8月9日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.coupon;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.YesNoType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年8月9日 下午4:59:21 $
 */
public class CouponCode extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 优惠券id
     */
    private long couponId;

    /**
     * 推广码
     */
    private String couponCode;

    /**
     * 状态是否注销（0否，1是）
     */
    private YesNoType isCancel;

    /**************************** 扩展属性 *************************/

    /**************************** get set *************************/
    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    /**
     * @return Returns the isCancel.
     */
    public YesNoType getIsCancel() {
        return isCancel;
    }

    /**
     * @param isCancel The isCancel to set.
     */
    public void setIsCancel(YesNoType isCancel) {
        this.isCancel = isCancel;
    }
}
