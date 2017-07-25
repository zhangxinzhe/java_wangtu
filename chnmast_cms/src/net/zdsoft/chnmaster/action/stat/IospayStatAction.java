/*
 * @(#)IospayStatAction.java    Created on 2016年12月23日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.stat;

import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsPageAction;
import net.zdsoft.chnmaster.service.account.AccountRecordService;
import net.zdsoft.chnmaster.service.account.AccountService;
import net.zdsoft.common.dao.queryCondition.EqualCondition;
import net.zdsoft.common.dao.queryCondition.GreaterEqualCondition;
import net.zdsoft.common.dao.queryCondition.LessEqualCondition;
import net.zdsoft.common.dao.queryCondition.LikeCondition;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.enums.RecordType;
import net.zdsoft.keel.util.DateUtils;

/**
 * ios收支统计
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年12月23日 下午2:17:12 $
 */
@Scope("prototype")
@Controller
public class IospayStatAction extends CmsPageAction {
    private static final long serialVersionUID = 1L;

    @Resource
    private AccountService accountService;
    @Resource
    private AccountRecordService accountRecordService;

    private Date qpBeginTime;
    private Date qpEndTime;
    private long userId;
    private String userName;
    private String realName;
    private String tradeNo;

    private float totalAmount;// 充值总金额
    private float actualAmount;// 实收总金额

    /**
     * 充值记录
     */
    public String iospayStat() {
        if (qpBeginTime == null) {
            qpBeginTime = org.apache.commons.lang3.time.DateUtils.setDays(new Date(), 1);
        }
        if (qpEndTime == null) {
            qpEndTime = new Date();
        }
        list = accountRecordService.getAccountRecordByConditions(buildConditions(true), getPage());
        totalAmount = accountRecordService.getTotalAmount(buildConditions(true));
        DecimalFormat df = new DecimalFormat("#.00");
        actualAmount = Float.parseFloat(df.format(totalAmount * 0.70f));
        return SUCCESS;
    }

    /**
     * 购买记录
     */
    public String iospayStatDetail() {
        if (qpBeginTime == null) {
            qpBeginTime = org.apache.commons.lang3.time.DateUtils.setDays(new Date(), 1);
        }
        if (qpEndTime == null) {
            qpEndTime = new Date();
        }
        list = accountRecordService.getAccountRecordByConditions(buildConditions(false), getPage());
        return SUCCESS;
    }

    /**
     * 收支统计-个人用户
     */
    public String iospayStatAccount() {
        // list = accountService.getUserAccountsByConditions(buildAccountConditions(), getPage());
        return SUCCESS;
    }

    /**
     * 收支统计-个人用户-交易明细
     */
    public String iospayStatAccountInfo() {
        if (userId > 0) {
            getPage().setRowNum(8);
            list = accountRecordService.getAccountRecordListByUserId(userId, getPage());
        }
        return SUCCESS;
    }

    /**
     * 收支统计-组装条件
     *
     * @param isFunds
     *            充值记录（true）；购买记录（false）
     * @return
     */
    private List<QueryCondition> buildConditions(boolean isFunds) {
        List<QueryCondition> conditions = new ArrayList<QueryCondition>();
        if (isFunds) {// 充值记录
            conditions.add(new EqualCondition("AR.RECORD_TYPE", RecordType.CHARGE.getValue(), Types.INTEGER));
        }
        else {// 购买记录
            conditions.add(new EqualCondition("AR.RECORD_TYPE", RecordType.BUY.getValue(), Types.INTEGER));
        }

        if (StringUtils.isNotBlank(userName)) {
            conditions.add(new EqualCondition("U.USERNAME", userName, Types.VARCHAR));
        }
        if (StringUtils.isNotBlank(realName)) {
            conditions.add(new LikeCondition("U.REALNAME", realName));
        }
        if (StringUtils.isNotBlank(tradeNo)) {
            conditions.add(new EqualCondition("O.TRADE_NO", tradeNo, Types.VARCHAR));
        }
        if (null != qpBeginTime) {
            conditions.add(new GreaterEqualCondition("AR.RECORD_DATE", DateUtils.getStartDate(qpBeginTime)));
        }
        if (null != qpEndTime) {
            conditions.add(new LessEqualCondition("AR.RECORD_DATE", DateUtils.getEndDate(qpEndTime)));
        }
        return conditions;
    }

    private List<QueryCondition> buildAccountConditions() {
        List<QueryCondition> conditions = new ArrayList<QueryCondition>();
        if (StringUtils.isNotBlank(userName)) {
            conditions.add(new EqualCondition("U.USERNAME", userName, Types.VARCHAR));
        }
        if (StringUtils.isNotBlank(realName)) {
            conditions.add(new LikeCondition("U.REALNAME", realName));
        }
        return conditions;
    }

    /**
     * @return Returns the qpBeginTime.
     */
    public Date getQpBeginTime() {
        return qpBeginTime;
    }

    /**
     * @param qpBeginTime
     *            The qpBeginTime to set.
     */
    public void setQpBeginTime(Date qpBeginTime) {
        this.qpBeginTime = qpBeginTime;
    }

    /**
     * @return Returns the qpEndTime.
     */
    public Date getQpEndTime() {
        return qpEndTime;
    }

    /**
     * @param qpEndTime
     *            The qpEndTime to set.
     */
    public void setQpEndTime(Date qpEndTime) {
        this.qpEndTime = qpEndTime;
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
     * @return Returns the totalAmount.
     */
    public float getTotalAmount() {
        return totalAmount;
    }

    /**
     * @return Returns the actualAmount.
     */
    public float getActualAmount() {
        return actualAmount;
    }

    /**
     * @return Returns the tradeNo.
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * @param tradeNo
     *            The tradeNo to set.
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * @return Returns the userId.
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            The userId to set.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

}
