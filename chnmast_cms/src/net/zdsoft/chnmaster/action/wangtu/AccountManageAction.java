/*
 * @(#)AccountManageAction.java    Created on 2017年7月31日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.wangtu;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsPageAction;
import net.zdsoft.chnmaster.service.account.AccountService;
import net.zdsoft.common.dao.queryCondition.LikeCondition;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.account.Account;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月31日 下午1:43:50 $
 */
@Scope("prototype")
@Controller
public class AccountManageAction extends CmsPageAction {

    private static final long serialVersionUID = 1L;

    private List<Account> accountList;
    private String userName;
    private String realName;

    private long accountId;
    private double founds;// 修改的余额

    @Resource
    private AccountService accountService;

    /**
     * 账户列表
     *
     * @return
     */
    public String accountManage() {
        List<QueryCondition> cons = new ArrayList<QueryCondition>();
        if (StringUtils.isNotBlank(realName)) {
            cons.add(new LikeCondition("U.REALNAME", realName));
        }
        if (StringUtils.isNotBlank(userName)) {
            cons.add(new LikeCondition("U.USERNAME", userName));
        }
        accountList = accountService.listAccount(cons, getPage());

        return SUCCESS;
    }

    /**
     * 余额修改
     */
    public void updateAccount() {
        if (null == getUser()) {
            printMsg("未登录");
            return;
        }
        if (accountId == 0) {
            printMsg("账号不存在");
            return;
        }
        if (founds < 0) {
            printMsg("余额不能小于0！");
            return;
        }
        int i = accountService.updateFundsByAccountId(accountId, founds);
        if (i > 0) {
            printMsg("success");
            logOperateAsyn("【" + accountId + "】修改余额成功：" + founds);
            return;
        }
        printMsg("修改失败，请重试！");
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getFounds() {
        return founds;
    }

    public void setFounds(double founds) {
        this.founds = founds;
    }

}
