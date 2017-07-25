/* 
 * @(#)SystemRole.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.system;

import java.util.Date;

import net.zdsoft.chnmaster.enums.CmsRoleType;
import net.zdsoft.common.entity.BaseEntity;

/**
 * 后台角色表【t_system_role】
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-26 上午10:58:11 $
 */
public class SystemRole extends BaseEntity {
    private static final long serialVersionUID = 6222653660393452354L;

    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 角色类型（1平台超级管理员角色，2平台自定义角色）
     */
    private CmsRoleType roleType;

    /**
     * 获取名称
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取描述
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取创建时间
     * 
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     * 
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取角色类型
     * 
     * @return
     */
    public CmsRoleType getRoleType() {
        return roleType;
    }

    /**
     * 设置角色类型
     * 
     * @param roleType
     */
    public void setRoleType(CmsRoleType roleType) {
        this.roleType = roleType;
    }

}
