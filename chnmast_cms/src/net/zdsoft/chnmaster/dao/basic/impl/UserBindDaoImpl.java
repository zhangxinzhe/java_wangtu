/*
 * @(#)UserBindDaoImpl.java    Created on 2016年7月14日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.basic.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.basic.UserBindDao;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.entity.user.UserBind;
import net.zdsoft.common.entity.user.mapper.UserBindRowMapper;
import net.zdsoft.common.enums.PlatType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年7月14日 下午2:52:23 $
 */
@Repository("userBindDao")
public class UserBindDaoImpl extends BaseDaoImpl implements UserBindDao {

    private static String get_user_bind_by_bind_id = "user.get_user_bind_by_bind_id";
    private static String add_user_bind = "user.add_user_bind";

    @Override
    public UserBind getUserBindByBindId(String token) {
        return (UserBind) findForObject(getSql(get_user_bind_by_bind_id), new Object[] { token },
                UserBindRowMapper.instance());
    }

    @Override
    public int addUserBind(UserBind bind) {
        return this.executeUpdate(getSql(add_user_bind), new Object[] { bind.getBindId(), bind.getNickName(),
                bind.getUserId(), bind.getNewUserId(), bind.getPlatType().getValue(), bind.getBindTime() });
    }

    @Override
    public List<UserBind> getUserBingByUserId(long userId, PlatType platType) {
        String sql = "SELECT * FROM T_USER_BIND WHERE USER_ID = ? AND PLAT_TYPE = ?";
        return find(sql, new Object[] { userId, platType.getValue() }, UserBindRowMapper.instance());
    }
}
