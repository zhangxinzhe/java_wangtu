/* 
 * @(#)SystemModuleService.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.system;

import java.util.List;
import java.util.Map;

import net.zdsoft.chnmaster.entity.system.SystemModule;

/**
 * 系统模块服务接口
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-26 下午2:29:27 $
 */
public interface SystemModuleService {

    /**
     * 获取系统所有模块列表（带缓存）
     * 
     * @return
     */
    public List<SystemModule> listSystemModule();

    /**
     * 获取系统所有模块列表（带缓存）
     * 
     * @return
     */
    public Map<String, SystemModule> getSystemModuleMap();

    /**
     * 根据模块ID获取系统模块（带缓存）
     * 
     * @param parentId
     * @return
     */
    public SystemModule getSystemModule(long moduleId);

    /**
     * 根据链接获取系统模块（带缓存）
     * 
     * @param url
     * @return
     */
    public SystemModule getSystemModule(String url);

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
     * 获取用户有权使用的对应子系统模块列表，按模块parentid和seq排序（有缓存）
     * 
     * @param userId
     * @param appId
     *            子系统id
     * @param includeHide
     *            是否包含隐藏模块（true包含，false不包含）
     * @return
     */
    public List<SystemModule> listSystemModuleOfUser(long userId, long appId, boolean includeHide);

    /**
     * 获取用户对应系统模块列表，按模块parentid和id排序
     * 
     * @param userId
     * @param includeHide
     *            是否包含隐藏模块（true包含，false不包含）
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
     * 通过角色id获取该角色拥有操作权限的模块的id
     * 
     * @param id
     * @return
     */
    public List<Long> listSystemModuleIdsOfRole(long id);

    /**
     * 根据parentId获取下级模块
     * 
     * @param parentId
     *            模块的parentId
     * @return
     */
    public List<SystemModule> listSystemModuleByParentId(long parentId);

}
