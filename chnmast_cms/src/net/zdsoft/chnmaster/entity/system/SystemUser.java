/* 
 * @(#)SystemUser.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.system;

import java.io.Serializable;
import java.util.Date;

import net.zdsoft.chnmaster.enums.CmsUserType;
import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.common.enums.YesNoType;

/**
 * 后台用户表
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-26 上午11:39:55 $
 */
public class SystemUser extends BaseUser implements Serializable {

    private static final long serialVersionUID = 2896278289597617666L;
    /**
     * 备注
     */
    private String remark;
    /**
     * email
     */
    private String bindingEmail;
    /**
     * 
     */
    private Date createTime;
    /**
     * 用户类型（1超级管理员，2平台普通管理员）
     */
    private CmsUserType userType;
    /**
     * 是否冻结，默认否
     */
    private YesNoType isFreeze = YesNoType.NO;

    /**
     * 是否删除，默认否
     */
    private YesNoType isDeleted = YesNoType.NO;
    // 扩展属性-----------------------
    /**
     * 角色名称
     */
    private String rolename;

    /**
     * 获取备注
     * 
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     * 
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取email
     * 
     * @return
     */
    public String getBindingEmail() {
        return bindingEmail;
    }

    /**
     * 设置email
     * 
     * @param bindingEmail
     */
    public void setBindingEmail(String bindingEmail) {
        this.bindingEmail = bindingEmail;
    }

    /**
     * 获取createTime
     * 
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置createTime
     * 
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取用户类型
     * 
     * @return
     */
    public CmsUserType getUserType() {
        return userType;
    }

    /**
     * 设置用户类型
     * 
     * @param userType
     */
    public void setUserType(CmsUserType userType) {
        this.userType = userType;
    }

    /**
     * 获取是否冻结
     * 
     * @return
     */
    public YesNoType getIsFreeze() {
        return isFreeze;
    }

    /**
     * 设置是否冻结
     * 
     * @param isFreeze
     */
    public void setIsFreeze(YesNoType isFreeze) {
        this.isFreeze = isFreeze;
    }

    /**
     * 获取是否删除
     * 
     * @return
     */
    public YesNoType getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除
     * 
     * @param isDeleted
     */
    public void setIsDeleted(YesNoType isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取角色名称
     * 
     * @return
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * 设置角色名称
     * 
     * @param roleName
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

}
