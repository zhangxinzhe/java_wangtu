/* 
 * @(#)SystemUserRole.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.system;

import java.util.List;

import net.zdsoft.chnmaster.entity.system.SystemUser;
import net.zdsoft.chnmaster.entity.system.SystemUserRole;

/**
 * 用户角色表【t_system_user_role】
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-26 下午2:19:00 $
 */
public interface SystemUserRoleDao {
    /**
     * 新增，返回更新行数
     * 
     * @param systemUserRole
     */
    public int saveSystemUserRole(SystemUserRole systemUserRole);

    /**
     * 获取平台自定义角色列表
     * 
     * @return
     */
    public List<SystemUserRole> getSystemUserRoles();

    /**
     * 更新改角色下所有用户
     * 
     * @param userIds
     * @param roleid
     */
    public int[] updateSystemUsersForRole(Long[] userIds, long roleid);

    /**
     * 通过角色id获取拥有该角色的用户id数组
     * 
     * @param roleId
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
     * 根据用户id和角色类型获取用户权限
     * 
     * @param userId
     * @param roleType
     * @return
     */
    public List<SystemUserRole> getSystemUserRole(long userId, int roleType);

    /**
     * 删除该角色下面管理员以外所有的用户
     * 
     * @param roleId
     * @return
     */
    public int deleteSystemRole(long roleId);
}
