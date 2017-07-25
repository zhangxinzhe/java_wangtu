/* 
 * @(#)SystemRoleModule.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.system;

import net.zdsoft.common.entity.BaseEntity;

/**
 * 角色模块表【t_system_role_module】
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-26 下午2:54:07 $
 */
public class SystemRoleModule extends BaseEntity {
    private static final long serialVersionUID = -1509667387178797171L;
    /**
     * 角色id
     */
    private long roleId;
    /**
     * 模块id
     */
    private long moduleId;

    /**
     * 获取角色id
     * 
     * @return
     */
    public long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     * 
     * @param roleId
     */
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取模块id
     * 
     * @return
     */
    public long getModuleId() {
        return moduleId;
    }

    /**
     * 设置角色id
     * 
     * @param moduleId
     */
    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

}
