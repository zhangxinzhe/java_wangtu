/* 
 * @(#)SystemUserRoleServiceImpl.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.system.SystemUserRoleDao;
import net.zdsoft.chnmaster.entity.system.SystemUser;
import net.zdsoft.chnmaster.entity.system.SystemUserRole;
import net.zdsoft.chnmaster.service.common.BaseServiceImpl;
import net.zdsoft.chnmaster.service.system.SystemUserRoleService;

@Service("systemUserRoleService")
public class SystemUserRoleServiceImpl extends BaseServiceImpl implements SystemUserRoleService {

    @Resource
    private SystemUserRoleDao systemUserRoleDao;

    @Override
    public SystemUserRole saveSystemUserRole(SystemUserRole systemUserRole) {
        int account = systemUserRoleDao.saveSystemUserRole(systemUserRole);
        return account > 0 ? systemUserRole : null;
    }

    @Override
    // 已调用
    public List<SystemUserRole> getSystemUserRoles() {
        return systemUserRoleDao.getSystemUserRoles();
    }

    @Override
    // 已调用
    public List<SystemUserRole> getSystemUserRole(long userId, int roleType) {
        return systemUserRoleDao.getSystemUserRole(userId, roleType);
    }

    @Override
    public int deleteSystemRole(long roleId) {
        return systemUserRoleDao.deleteSystemRole(roleId);
    }

    @Override
    // 已调用
    public void updateSystemRoleToUsers(long roleId, Long[] userIds, long userid) {
        // 删除除管理员以外该角色的所有用户
        deleteSystemRole(roleId);
        // 批量新增所有用户
        if (ArrayUtils.isNotEmpty(userIds)) {
            systemUserRoleDao.updateSystemUsersForRole(userIds, roleId);
        }
    }

    @Override
    // 已调用
    public List<Long> getSystemUserIdsOfRoleId(long roleId) {
        return systemUserRoleDao.getSystemUserIdsOfRoleId(roleId);
    }

    @Override
    public List<SystemUser> getSystemUserOfRoleId(long roleId) {
        return systemUserRoleDao.getSystemUserOfRoleId(roleId);
    }

    @Override
    // 已调用
    public int deleteSystemRoleByUserId(long userId) {
        return systemUserRoleDao.deleteSystemRoleByUserId(userId);
    }

    @Override
    // 已调用
    public int[] saveBatchSystemUserRole(Long[] roleIds, long userId) {
        return systemUserRoleDao.saveBatchSystemUserRole(roleIds, userId);
    }

}
