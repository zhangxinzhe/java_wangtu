/* 
 * @(#)SystemUserRole.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.system;

import net.zdsoft.common.entity.BaseEntity;

/**
 * 用户角色【t_system_user_role】
 * 
 * @author oucl
 * 
 * @version $Revision: 1.0 $, $Date: 2014-6-26 下午2:02:11 $
 */
public class SystemUserRole extends BaseEntity {
    private static final long serialVersionUID = 5368266911749856953L;
    /**
     * 后台用户id
     */
    private long userId;
    /**
     * 后台角色id
     */
    private long roleId;

    // 扩展属性-----------------------------------------------
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 获取后台用户id
     * 
     * @return
     */
    public long getUserId() {
        return userId;
    }

    /**
     * 设置后台用户id
     * 
     * @param userId
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * 获取后台角色id
     * 
     * @return
     */
    public long getRoleId() {
        return roleId;
    }

    /**
     * 设置后台角色id
     * 
     * @param roleId
     */
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     * 
     * @return
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     * 
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
