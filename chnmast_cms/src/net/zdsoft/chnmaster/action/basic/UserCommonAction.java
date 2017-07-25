/*
 * @(#)UserCommonAction.java    Created on 2016年6月1日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.basic;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsPageAction;
import net.zdsoft.chnmaster.service.basic.UserService;
import net.zdsoft.common.dao.queryCondition.EqualCondition;
import net.zdsoft.common.dao.queryCondition.LikeCondition;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.user.User;
import net.zdsoft.common.enums.StatusEunm;
import net.zdsoft.common.enums.UserType;

/**
 * @author xiongwq
 * @version $Revision: 1.0 $, $Date: 2016年6月1日 下午4:21:21 $
 */
@Scope("prototype")
@Controller
public class UserCommonAction extends CmsPageAction {

    private static final long serialVersionUID = 1L;
    @Resource
    private UserService userService;

    private User u;
    private List<User> userList;

    public String userSelect() {
        List<QueryCondition> param = new ArrayList<QueryCondition>();
        if (u != null) {
            if (StringUtils.isNotBlank(u.getUserName())) {
                param.add(new EqualCondition("U.USERNAME", u.getUserName(), Types.VARCHAR));
            }
            if (StringUtils.isNotBlank(u.getRealName())) {
                param.add(new LikeCondition("U.REALNAME", u.getRealName()));
            }
        }
        param.add(new EqualCondition("U.IS_CANCEL", StatusEunm.NORMAL.getValue(), Types.INTEGER));// 正常状态
        param.add(new EqualCondition("U.USER_TYPE", UserType.PERSONAL.getValue(), Types.INTEGER));// 个人用户
        getPage().setRowNum(24);
        userList = userService.getUserListByCondition(param, getPage());
        return SUCCESS;
    }

    /********************* get、set *********************/
    /**
     * @return Returns the u.
     */
    public User getU() {
        return u;
    }

    /**
     * @param u
     *            The u to set.
     */
    public void setU(User u) {
        this.u = u;
    }

    /**
     * @return Returns the userList.
     */
    public List<User> getUserList() {
        return userList;
    }

}
