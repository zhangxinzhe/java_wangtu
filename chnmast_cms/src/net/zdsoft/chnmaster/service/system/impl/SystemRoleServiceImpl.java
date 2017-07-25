/* 
 * @(#)SystemRoleServiceImpl.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.system.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.system.SystemRoleDao;
import net.zdsoft.chnmaster.entity.system.SystemRole;
import net.zdsoft.chnmaster.enums.CmsRoleType;
import net.zdsoft.chnmaster.service.common.BaseServiceImpl;
import net.zdsoft.chnmaster.service.system.SystemRoleModuleService;
import net.zdsoft.chnmaster.service.system.SystemRoleService;
import net.zdsoft.common.entity.PageDto;

@Service("systemRoleService")
public class SystemRoleServiceImpl extends BaseServiceImpl implements SystemRoleService {
    @Resource
    private SystemRoleDao systemRoleDao;
    @Resource
    private SystemRoleModuleService systemRoleModuleService;

    @Override
    // 已调用
    public long saveSystemRole(SystemRole systemRole, Long[] moduleIds) {
        long roleId = 0L;
        if (systemRole.getId() == 0L) {
            systemRole.setCreateTime(new Date());
            systemRole.setRoleType(CmsRoleType.CUSTOM_ROLE);
            roleId = systemRoleDao.saveSystemRole(systemRole);
            systemRole.setId(roleId);
        }
        else {
            updateSystemRole(systemRole);
            roleId = systemRole.getId();
        }
        if (moduleIds[0] == null) {
            moduleIds = (Long[]) ArrayUtils.subarray(moduleIds, 1, moduleIds.length);
        }
        if (!ArrayUtils.isEmpty(moduleIds)) {
            systemRoleModuleService.saveBatchSystemRoleModule(roleId, moduleIds);
        }
        return roleId;
    }

    @Override
    // 已调用
    public SystemRole getSystemRole(long id) {
        return systemRoleDao.getSystemRole(id);
    }

    @Override
    // 已调用
    public int updateSystemRole(SystemRole systemRole) {
        return systemRoleDao.updateSystemRole(systemRole);
    }

    @Override
    // 已调用
    public int deleteSystemRole(long id) {
        return systemRoleDao.deleteSystemRole(id);
    }

    @Override
    // 已调用
    public List<SystemRole> getSystemRoles() {
        return systemRoleDao.getSystemRoles();
    }

    @Override
    // 已调用
    public List<SystemRole> getSystemRoles(PageDto page, int roleType) {
        return systemRoleDao.getSystemRoles(page, roleType);
    }

    @Override
    // 已调用
    public List<SystemRole> listSystemRoleOfUser(long userId) {
        return systemRoleDao.listSystemRoleOfUser(userId);
    }

    @Override
    // 已调用
    public boolean checkRoleName(String roleName, long roleId) {
        int count = systemRoleDao.getSystemRoleNum(roleName, roleId);
        if (count == 0) {
            return true;
        }
        return false;
    }

}
