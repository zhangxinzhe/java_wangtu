/*
 * @(#)UserBindDao.java    Created on 2016年7月14日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.basic;

import java.util.List;

import net.zdsoft.common.entity.user.UserBind;
import net.zdsoft.common.enums.PlatType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年7月14日 下午2:51:57 $
 */
public interface UserBindDao {

    /**
     * 根据绑定id查询
     *
     * @param token
     * @return
     */
    public UserBind getUserBindByBindId(String token);

    /**
     * 新增绑定关系
     *
     * @param bind
     * @return
     */
    public int addUserBind(UserBind bind);

    /**
     * 根据用户和平台类型获取绑定信息
     *
     * @param userId
     * @param platType
     * @return
     */
    public List<UserBind> getUserBingByUserId(long userId, PlatType platType);
}
