/*
 * @(#)HifiMember.java    Created on 2016年11月25日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.hifi.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * hifi会员信息
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年11月25日 上午11:36:13 $
 */
public class HifiMember implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户是否开通VIP套餐，是为Y，否为N
     */
    private boolean inService;
    /**
     * 用户VIP套餐开始日期，未开通为空
     */
    private Date startDate;
    /**
     * 用户VIP套餐到期日期，未开通为空
     */
    private Date endDate;
    /**
     * 服务器当前日期
     */
    private Date curdate;
    /**
     * 用户当月总下载数，未开通为0
     */
    private int total;
    /**
     * 用户当月剩余下载数，未开通为0
     */
    private int leftCount;
    /**
     * 用户尚未使用的每月下载数
     */
    private String additonnal;

    // 接口中暂未使用参数:level，newMonthlySet

    /**************************** get、set方法 *************************/
    /**
     * @return Returns the inService.
     */
    public boolean getInService() {
        return inService;
    }

    /**
     * @param inService
     *            The inService to set.
     */
    public void setInService(boolean inService) {
        this.inService = inService;
    }

    /**
     * @return Returns the startDate.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            The startDate to set.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return Returns the endDate.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            The endDate to set.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return Returns the curdate.
     */
    public Date getCurdate() {
        return curdate;
    }

    /**
     * @param curdate
     *            The curdate to set.
     */
    public void setCurdate(Date curdate) {
        this.curdate = curdate;
    }

    /**
     * @return Returns the total.
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total
     *            The total to set.
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return Returns the leftCount.
     */
    public int getLeftCount() {
        return leftCount;
    }

    /**
     * @param leftCount
     *            The leftCount to set.
     */
    public void setLeftCount(int leftCount) {
        this.leftCount = leftCount;
    }

    /**
     * @return Returns the additonnal.
     */
    public String getAdditonnal() {
        return additonnal;
    }

    /**
     * @param additonnal
     *            The additonnal to set.
     */
    public void setAdditonnal(String additonnal) {
        this.additonnal = additonnal;
    }

}
