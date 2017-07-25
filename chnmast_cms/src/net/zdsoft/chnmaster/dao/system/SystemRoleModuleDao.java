/* 
 * @(#)SystemRoleModuleDao.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.system;

/**
 * 角色模块表【t_system_role_module】
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-26 下午3:59:32 $
 */
public interface SystemRoleModuleDao {
    /**
     * 根据角色id删除角色对应的操作功能点(t_system_role_module)权限
     * 
     * @param id
     * @return
     */
    public int deleteSystemRoleModuleByRoleId(long roleId);

    /**
     * 批量添加自定义角色模块操作功能点权限
     * 
     * @param id
     * @param moduleIds
     */
    public void batchAddSystemRoleModule(long id, Long[] moduleIds);
}
