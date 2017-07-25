/* 
 * @(#)SystemUserRole.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.system;

import java.util.List;

import net.zdsoft.chnmaster.entity.system.SystemUser;
import net.zdsoft.chnmaster.entity.system.SystemUserRole;

/**
 * 用户角色服务接口
 * 
 * @author oucl
 * 
 * @version $Revision: 1.0 $, $Date: 2014-6-26 下午2:39:48 $
 */
public interface SystemUserRoleService {
    /**
     * 新增
     * 
     * @param systemUserRole
     * @return
     */
    public SystemUserRole saveSystemUserRole(SystemUserRole systemUserRole);

    /**
     * 获取平台自定义角色列表
     * 
     * @return
     */
    public List<SystemUserRole> getSystemUserRoles();

    /**
     * 根据用户id获取用户角色
     * 
     * @param userId
     * @return
     */
    public List<SystemUserRole> getSystemUserRole(long userId, int roleType);

    /**
     * 通过角色id获取拥有该角色的用户id
     * 
     * @param userId
     * @return
     */
    public List<Long> getSystemUserIdsOfRoleId(long roleId);

    /**
     * 通过角色id获取拥有该角色的用户
     * 
     * @param userId
     * @return
     */
    public List<SystemUser> getSystemUserOfRoleId(long roleId);

    /**
     * 给角色分配用户
     * 
     * @param roleId
     * @param userIds
     * @param id
     */
    public void updateSystemRoleToUsers(long roleId, Long[] userIds, long id);

    /**
     * 通过用户id删除用户权限
     * 
     * @param userId
     */
    public int deleteSystemRoleByUserId(long userId);

    /**
     * 批量添加用户权限
     * 
     * @param roleIds
     * @param userId
     */
    public int[] saveBatchSystemUserRole(Long[] roleIds, long userId);

    /**
     * 删除某角色除管理员以外所有的用户
     * 
     * @param ids
     * @return
     */
    public int deleteSystemRole(long roleId);
}
