/*
 * @(#)SystemModuleServiceImpl.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.system.SystemModuleDao;
import net.zdsoft.chnmaster.entity.system.SystemModule;
import net.zdsoft.chnmaster.service.common.BaseServiceImpl;
import net.zdsoft.chnmaster.service.system.SystemModuleService;

@Service("systemModuleService")
public class SystemModuleServiceImpl extends BaseServiceImpl implements SystemModuleService {
    private static final String LIST_SYSTEM_MODULE = "listSystemModule";

    @Resource
    private SystemModuleDao systemModuleDao;

    @Override
    public List<SystemModule> listSystemModule() {
        return systemModuleDao.listSystemModule();
        // return this.getObjectsFromCache(new CacheObjectsParam<SystemModule>() {
        //
        // @Override
        // public String getDataType() {
        // return DataTypeConstants.SYSTEM_MODULE;
        // }
        //
        // @Override
        // public Long getAgencyId() {
        // return 0l;
        // }
        //
        // @Override
        // public String fetchKey() {
        // return getKeyName(getDataType(), getAgencyId(), LIST_SYSTEM_MODULE);
        // }
        //
        // @Override
        // public List<SystemModule> fetchObjects() {
        // return systemModuleDao.listSystemModule();
        // }
        // });
    }

    @Override
    public Map<String, SystemModule> getSystemModuleMap() {
        Map<String, SystemModule> map = new HashMap<String, SystemModule>();
        // 先从缓存中取所有模块
        List<SystemModule> list = this.listSystemModule();
        for (SystemModule module : list) {
            map.put(String.valueOf(module.getId()), module);
        }

        return map;
    }

    @Override
    public SystemModule getSystemModule(long moduleId) {
        if (moduleId <= 0) {
            return null;
        }

        Map<String, SystemModule> map = this.getSystemModuleMap();
        return map.get(String.valueOf(moduleId));
    }

    @Override
    public SystemModule getSystemModule(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        SystemModule systemModule = null;
        // 先从缓存中取所有模块
        List<SystemModule> list = this.listSystemModule();
        for (SystemModule module : list) {
            if (url.equals(module.getUrl())) {
                return module;
            }
        }

        return systemModule;
    }

    @Override
    // 已调用
    public List<SystemModule> listSystemModuleOfRole(long roleId) {
        return systemModuleDao.listSystemModuleOfRole(roleId);
    }

    @Override
    public List<SystemModule> listSystemModuleOfParent(long userId, long parentId) {
        return systemModuleDao.listSystemModuleOfParent(userId, parentId);
    }

    @Override
    public List<SystemModule> listSystemModuleOfUser(long userId, long appId, boolean includeHide) {
        // 根据用户id得到用户某一子系统的所有模块id列表
        List<Long> moduleIds = systemModuleDao.listSystemModuleIdsOfUser(userId, appId);
        if (CollectionUtils.isEmpty(moduleIds)) {
            return null;
        }

        // 从缓存中取所有模块列表
        List<SystemModule> moduleList = this.listSystemModule();

        List<SystemModule> rtnList = new ArrayList<SystemModule>();
        for (SystemModule module : moduleList) {
            // 如果模块是用户有权限访问的
            if (moduleIds.contains(Long.valueOf(module.getId()))) {
                // 包含隐藏模块时
                if (includeHide) {
                    rtnList.add(module);
                }
                else {
                    // 不包含隐藏模块时
                    if (module.getIsShow().getValue() == 1) {
                        rtnList.add(module);
                    }
                }
            }
        }

        return rtnList;
    }

    @Override
    // 已调用
    public List<SystemModule> listUserSystemModules(long userId, boolean includeHide) {
        // 直接取数据库
        return systemModuleDao.listUserSystemModules(userId, includeHide);
    }

    @Override
    // 已调用
    public List<SystemModule> listUserSystemModules(long userId, boolean includeHide, int roleType) {
        return systemModuleDao.listUserSystemModules(userId, includeHide, roleType);
    }

    @Override
    public boolean canVisitSystemModule(long userId, long moduleId) {
        return systemModuleDao.canVisitSystemModule(userId, moduleId);
    }

    @Override
    // 已调用
    public List<Long> listSystemModuleIdsOfRole(long id) {
        return systemModuleDao.listSystemModuleIdsOfRole(id);
    }

    @Override
    public List<SystemModule> listSystemModuleByParentId(long parentId) {
        List<SystemModule> list = this.listSystemModule();
        List<SystemModule> results = new ArrayList<SystemModule>();
        for (SystemModule item : list) {
            if (item.getParentId() == parentId) {
                results.add(item);
            }
        }
        return results;
    }
}
