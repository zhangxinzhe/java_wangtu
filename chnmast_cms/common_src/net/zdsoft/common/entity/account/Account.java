/*
 * @(#)Account.java    Created on 2016年12月21日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.account;

import java.math.BigDecimal;
import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;

/**
 * 个人账户余额表
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年12月21日 上午11:16:00 $
 */
public class Account extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 账户余额
     */
    private double funds;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 冻结金额
     */
    private float fundsLocked;

    /**
     * 0正常用户，1正在进行退费操作中
     */
    private int status;

    /**************************** 扩展属性 *************************/
    private String userName;// 用户名
    private String realName;// 姓名
    private String email;// 邮箱
    private String phone;// 电话

    private String alipayAccount;
    private String bankName;
    private String bankUserName;
    private String bankAccount;

    /**
     * @return Returns the funds.
     */
    public double getFunds() {
        BigDecimal bg = new BigDecimal(funds);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        return f1;
    }

    /**
     * @param funds
     *            The funds to set.
     */
    public void setFunds(double funds) {
        this.funds = funds;
    }

    /**
     * @return Returns the modifyTime.
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     *            The modifyTime to set.
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * @return Returns the fundsLocked.
     */
    public float getFundsLocked() {
        return fundsLocked;
    }

    /**
     * @param fundsLocked
     *            The fundsLocked to set.
     */
    public void setFundsLocked(float fundsLocked) {
        this.fundsLocked = fundsLocked;
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

    /**
     * @return Returns the userName.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            The userName to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return Returns the realName.
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     *            The realName to set.
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Returns the phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            The phone to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

}
