/* 
 * @(#)SystemUserServiceImpl.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.system.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.system.SystemUserDao;
import net.zdsoft.chnmaster.entity.system.SystemUser;
import net.zdsoft.chnmaster.enums.CmsUserType;
import net.zdsoft.chnmaster.service.common.BaseServiceImpl;
import net.zdsoft.chnmaster.service.system.SystemUserService;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.common.utils.MD5;

@Service("systemUserService")
public class SystemUserServiceImpl extends BaseServiceImpl implements SystemUserService {
    @Resource
    private SystemUserDao systemUserDao;

    @Override
    public SystemUser getSystemUser(String username, String password) {
        return systemUserDao.getSystemUser(username, password);
    }

    @Override
    public List<Long> getSystemUserId(String username, String realname) {
        return systemUserDao.getSystemUserId(username, realname);
    }

    @Override
    // 已调用
    public SystemUser getSystemUserById(long userId) {
        return systemUserDao.getSystemUserById(userId);
    }

    @Override
    // 已调用
    public long addSystemUser(SystemUser user) {
        user.setPassword(new MD5().getStrToMD5(user.getPassword()));
        user.setIsFreeze(YesNoType.NO);
        user.setIsDeleted(YesNoType.NO);
        user.setCreateTime(new Date());
        return systemUserDao.addSystemUser(user);
    }

    @Override
    // 已调用
    public int updateSystemUser(SystemUser user) {
        return systemUserDao.updateSystemUser(user);
    }

    @Override
    // 已调用
    public SystemUser updateSystemUserOfPassword(long userId, String oldPassword, String newPassword) {
        if (1 == systemUserDao.updateSystemUserOfPassword(userId, oldPassword, newPassword)) {
            return systemUserDao.getSystemUserById(userId);
        }
        return null;
    }

    @Override
    // 已调用
    public boolean validateSystemUserName(long userId, String username) {
        return systemUserDao.validateSystemUserName(userId, username);
    }

    @Override
    // 已调用
    public SystemUser updateResetSystemUserOfPassword(long userId) {
        return systemUserDao.resetSystemUserOfPassword(userId);
    }

    @Override
    public SystemUser saveSystemUser(SystemUser user) throws Exception {
        int count = systemUserDao.saveUser(user);
        return count > 0 ? user : null;
    }

    @Override
    // 已调用
    public int deleteSystemUser(long id) throws Exception {
        return systemUserDao.delUser(id);
    }

    @Override
    // 已调用
    public SystemUser updateFreeseSystemUser(long userId) {
        return systemUserDao.updateFreeseSystemUser(userId);
    }

    @Override
    // 已调用
    public SystemUser updateUnfreeseSystemUser(long userId) {
        return systemUserDao.updateUnfreeseSystemUser(userId);
    }

    @Override
    // 已调用
    public List<SystemUser> getSystemUsersByUserType(CmsUserType userType) {
        return systemUserDao.getSystemUsersByUserType(userType);
    }

    @Override
    public Map<Long, String> getSystemUserMapByIds(Long[] userIds) {
        return systemUserDao.getSystemUserMapByIds(userIds);
    }

    @Override
    public SystemUser getSystemUserByUserName(String userName) {
        return systemUserDao.getSystemUserByUserName(userName);
    }

    @Override
    // 已调用
    public List<SystemUser> getSystemAdminUser(PageDto page, List<QueryCondition> conditions) {
        return systemUserDao.getSystemAdminUser(page, conditions);
    }

    @Override
    public List<SystemUser> getGenenalAdminUser(PageDto page, List<QueryCondition> conditions) {
        return systemUserDao.getGenenalAdminUser(page, conditions);
    }

}
