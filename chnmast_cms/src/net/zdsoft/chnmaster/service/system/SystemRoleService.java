/* 
 * @(#)SystsemRoleService.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.system;

import java.util.List;

import net.zdsoft.chnmaster.entity.system.SystemRole;
import net.zdsoft.common.entity.PageDto;

/**
 * 后台角色服务接口
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-26 下午2:33:09 $
 */
public interface SystemRoleService {
    /**
     * 添加系统角色，返回id
     * 
     * @param systemRole
     */
    public long saveSystemRole(SystemRole systemRole, Long[] moduleIds);

    /**
     * 根据ID获取系统角色
     * 
     * @param id
     */
    public SystemRole getSystemRole(long id);

    /**
     * 更新系统角色
     * 
     * @param systemRole
     * @return
     */
    public int updateSystemRole(SystemRole systemRole);

    /**
     * 根据ID删除系统角色
     * 
     * @param id
     * @return
     */
    public int deleteSystemRole(long id);

    /**
     * 获取所有系统角色
     * 
     * @return
     */
    public List<SystemRole> getSystemRoles();

    /**
     * 根据角色类型分页获取系统角色
     * 
     * @param page
     * @return
     */
    public List<SystemRole> getSystemRoles(PageDto page, int roleType);

    /**
     * 根据用户id获取用户被分配的角色列表
     * 
     * @param userId
     * @return
     */
    public List<SystemRole> listSystemRoleOfUser(long userId);

    /**
     * 获取是否有角色名称等于roleName，且角色id不等于roleId，没有则返回true
     * 
     * @param roleName
     * @param roleId
     * @return
     */
    public boolean checkRoleName(String roleName, long roleId);

}
