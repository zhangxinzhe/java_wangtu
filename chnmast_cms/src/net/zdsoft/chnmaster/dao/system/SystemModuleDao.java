/* 
 * @(#)SystemModuleDao.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.system;

import java.util.List;

import net.zdsoft.chnmaster.entity.system.SystemModule;

/**
 * 后台模块表【t_system_module】
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-26 下午2:07:59 $
 */
public interface SystemModuleDao {
    /**
     * 获取系统模块列表
     * 
     * @return
     */
    public List<SystemModule> listSystemModule();

    /**
     * 获取角色对应系统模块列表
     * 
     * @param roleId
     * @return
     */
    public List<SystemModule> listSystemModuleOfRole(long roleId);

    /**
     * 获取用户对应父系统模块的子系统模块列表
     * 
     * @param userId
     * @param parentId
     * @return
     */
    public List<SystemModule> listSystemModuleOfParent(long userId, long parentId);

    /**
     * 获取用户对应系统模块列表
     * 
     * @param userId
     * @param includeHide
     *            是否包含隐藏模块
     * @return
     */
    public List<SystemModule> listUserSystemModules(long userId, boolean includeHide);

    /**
     * 根据用户类型获取系统模块列表
     * 
     * @param userId
     * @param includeHide
     * @param roleType
     * @return
     */
    public List<SystemModule> listUserSystemModules(long userId, boolean includeHide, int roleType);

    /**
     * 用户是否能访问指定系统模块
     * 
     * @param userId
     * @param moduleId
     * @return
     */
    public boolean canVisitSystemModule(long userId, long moduleId);

    /**
     * 根据角色id获取能访问的模块id
     * 
     * @param roleId
     * @return
     */
    public List<Long> listSystemModuleIdsOfRole(long roleId);

    /**
     * 根据用户id获取能访问的模块id
     * 
     * @param userId
     * @param appId
     * @return
     */
    public List<Long> listSystemModuleIdsOfUser(long userId, long appId);

}
