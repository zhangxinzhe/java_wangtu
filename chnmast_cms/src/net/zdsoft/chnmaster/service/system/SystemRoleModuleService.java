/* 
 * @(#)SystemRoleModuleService.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.system;

/**
 * 系统模块服务接口
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-26 下午4:05:22 $
 */
public interface SystemRoleModuleService {
    /**
     * 根据角色id删除角色对应的操作功能点(t_system_role_module)权限
     * 
     * @param ids
     * @return
     */
    public int deleteSystemRoleModuleByRoleId(long roleId);

    /**
     * 批量添加自定义角色模块操作功能点权限
     * 
     * @param id
     * @param moduleIds
     */
    public void saveBatchSystemRoleModule(long roleId, Long[] moduleIds);
}
