/* 
 * @(#)SystemUserService.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.system;

import java.util.List;
import java.util.Map;

import net.zdsoft.chnmaster.entity.system.SystemUser;
import net.zdsoft.chnmaster.enums.CmsUserType;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;

/**
 * 用户角色服务接口
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-26 下午2:36:58 $
 */
public interface SystemUserService {
    /**
     * 查询用户id 参数可为null
     * 
     * @param username
     * @param realname
     * @return
     */
    public List<Long> getSystemUserId(String username, String realname);

    /**
     * 根据用户名和密码，获取系统用户
     * 
     * @param username
     * @param password
     * @return
     */
    public SystemUser getSystemUser(String username, String password);

    /**
     * 根据用户id获取系统用户
     * 
     * @param userId
     * @return
     */
    public SystemUser getSystemUserById(long userId);

    /**
     * 根据用户ids获取系统用户
     * 
     * @param userIds
     * @return
     */
    public Map<Long, String> getSystemUserMapByIds(Long[] userIds);

    /**
     * 更新系统用户密码
     * 
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public SystemUser updateSystemUserOfPassword(long userId, String oldPassword, String newPassword);

    /**
     * 添加系统用户 返回id
     * 
     * @param user
     * @return
     */
    public long addSystemUser(SystemUser user);

    /**
     * 修改系统用户
     * 
     * @param user
     */
    public int updateSystemUser(SystemUser user);

    /**
     * 生成用户信息
     * 
     * @return
     */
    public SystemUser saveSystemUser(SystemUser user) throws Exception;

    /**
     * 删除用户信息
     * 
     * @return
     */
    public int deleteSystemUser(long id) throws Exception;

    /**
     * 验证用户名是否可用
     * 
     * @param userName
     * @return
     */
    public boolean validateSystemUserName(long userId, String username);

    /**
     * 获取平台用户
     * 
     * @param page
     * @return
     */
    public List<SystemUser> getSystemAdminUser(PageDto page, List<QueryCondition> conditions);

    /**
     * 获取机构用户
     * 
     * @param page
     * @return
     */
    public List<SystemUser> getGenenalAdminUser(PageDto page, List<QueryCondition> conditions);

    /**
     * 重置用户密码
     * 
     * @param userId
     * @return
     */
    public SystemUser updateResetSystemUserOfPassword(long userId);

    /**
     * 冻结用户
     * 
     * @param userId
     * @return
     */
    public SystemUser updateFreeseSystemUser(long userId);

    /**
     * 解冻用户
     * 
     * @param userId
     * @return
     */
    public SystemUser updateUnfreeseSystemUser(long userId);

    /**
     * 根据用户类型获取用户
     * 
     * @param userType
     * @return
     */
    public List<SystemUser> getSystemUsersByUserType(CmsUserType userType);

    /**
     * 根据用户名获取用户
     * 
     * @param userName
     * @return
     */
    public SystemUser getSystemUserByUserName(String userName);

}
