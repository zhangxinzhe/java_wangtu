/* 
 * @(#)SystemRoleModuleServiceImpl.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.system.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.system.SystemRoleModuleDao;
import net.zdsoft.chnmaster.service.common.BaseServiceImpl;
import net.zdsoft.chnmaster.service.system.SystemRoleModuleService;

@Service("systemRoleModuleService")
public class SystemRoleModuleServiceImpl extends BaseServiceImpl implements SystemRoleModuleService {

    @Resource
    private SystemRoleModuleDao systemRoleModuleDao;

    @Override
    // 已调用
    public int deleteSystemRoleModuleByRoleId(long roleId) {
        return systemRoleModuleDao.deleteSystemRoleModuleByRoleId(roleId);
    }

    @Override
    // 已调用
    public void saveBatchSystemRoleModule(long roleId, Long[] moduleIds) {
        systemRoleModuleDao.batchAddSystemRoleModule(roleId, moduleIds);
    }
}
