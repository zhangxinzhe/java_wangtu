/*
 * @(#)SystemUserAction.java    Created on 2014-6-30
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;

import net.zdsoft.chnmaster.action.common.AbstractZTreeAction;
import net.zdsoft.chnmaster.config.Config;
import net.zdsoft.chnmaster.constant.ChnmasterModuleConstants;
import net.zdsoft.chnmaster.entity.common.Node;
import net.zdsoft.chnmaster.entity.system.SystemModule;
import net.zdsoft.chnmaster.entity.system.SystemRole;
import net.zdsoft.chnmaster.entity.system.SystemUser;
import net.zdsoft.chnmaster.entity.system.SystemUserRole;
import net.zdsoft.chnmaster.enums.CmsRoleType;
import net.zdsoft.chnmaster.enums.CmsUserType;
import net.zdsoft.chnmaster.service.system.SystemLogService;
import net.zdsoft.chnmaster.service.system.SystemRoleService;
import net.zdsoft.chnmaster.service.system.SystemUserRoleService;
import net.zdsoft.chnmaster.service.system.SystemUserService;
import net.zdsoft.common.annotation.SecurityAntion;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.dao.queryCondition.LikeCondition;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.common.utils.Util;
import net.zdsoft.keel.util.Validators;

/**
 *
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-30 下午4:24:06 $
 */
@Scope("prototype")
@Controller
public class SystemUserAction extends AbstractZTreeAction<SystemModule> {

    private static final long serialVersionUID = 4297916710575571195L;
    @Resource
    private SystemUserService systemUserService;
    @Resource
    private SystemUserRoleService systemUserRoleService;
    @Resource
    private SystemRoleService systemRoleService;
    @Resource
    private SystemLogService systemLogService;

    private List<SystemRole> systemRoles;
    private List<SystemUser> systemUsers;
    private List<SystemModule> systemModules;
    private Long[] roleIds;
    private SystemUser systemUser;
    private String username;
    private String realname;
    private long userId;

    private Set<Long> moduleCheckSet = new HashSet<Long>();// 生成树字段

    /**
     * 用户管理
     *
     * @return
     * @throws Exception
     */
    public String systemUserManage() throws Exception {
        // 设置要创建的用户类型
        systemUsers = systemUserService.getSystemAdminUser(getPage(), buildCondition());
        systemRoles = systemRoleService.getSystemRoles();
        List<SystemUserRole> systemUserRoles = systemUserRoleService.getSystemUserRoles();
        StringBuilder rolename = new StringBuilder();
        for (SystemUser u : systemUsers) {
            rolename.setLength(0);
            for (SystemUserRole userRole : systemUserRoles) {
                if (u.getId() == userRole.getUserId()) {
                    for (SystemRole role : systemRoles) {
                        if (userRole.getRoleId() == role.getId()) {
                            if (rolename.length() > 0) {
                                rolename.append("<br>");
                            }
                            rolename.append(role.getName());
                        }
                    }
                }
            }
            u.setRolename(rolename.toString());
        }
        return SUCCESS;
    }

    /**
     * 添加用户信息
     *
     * @return
     * @throws Exception
     */
    @SecurityAntion(params = { "systemUser.remark" })
    public String addSystemUser() throws Exception {
        if (!getCanAddUser()) {
            return NO_PRIVACY;
        }
        if (systemUser == null) {
            return INPUT;
        }
        if (!validateSystemUser()) {
            replyDto.setIsSuccess(false);
            return wirteJsonMessage(replyDto);
        }
        // 设置要创建的用户类型
        systemUser.setUserType(CmsUserType.GENERAL_ADMIN);
        // 增加系统用户
        userId = systemUserService.addSystemUser(systemUser);
        if (userId > 0) {
            logOperateAsyn("新增系统用户(" + userId + "," + systemUser.getUserName() + ")");
            replyDto.setIsSuccess(true);
            replyDto.setPromptMsg("用户添加成功");
            return wirteJsonMessage(replyDto);
        }
        replyDto.setIsSuccess(false);
        replyDto.setErrorMsg("用户添加失败");
        return wirteJsonMessage(replyDto);
    }

    /**
     * 查看用户详细信息
     *
     * @return
     * @throws Exception
     */
    public String viewSystemUser() throws Exception {
        if (userId == 0) {
            return NOT_EXIST;
        }
        if (!getCanEditUser()) {
            return NO_PRIVACY;
        }
        systemUser = systemUserService.getSystemUserById(userId);
        if (null == systemUser) {
            return NOT_EXIST;
        }
        // 获取用户角色
        systemRoles = systemRoleService.listSystemRoleOfUser(userId);
        return SUCCESS;
    }

    /**
     * 分配系统角色
     *
     * @return
     */
    public String assignSystemRole() {
        if (!getCanAssignRoleUser()) {
            return NO_PRIVACY;
        }
        systemUser = systemUserService.getSystemUserById(userId);
        if (null == systemUser) {
            return NOT_EXIST;
        }
        // 批量删除用户权限
        int i = systemUserRoleService.deleteSystemRoleByUserId(userId);
        if (!ArrayUtils.isEmpty(roleIds)) {
            // 批量新增用户权限
            systemUserRoleService.saveBatchSystemUserRole(roleIds, userId);
            logOperateAsyn("分配权限给（" + systemUser.getRealName() + "）");
            addActionMessage("权限分配成功");
        }
        else {
            if (i > 0) {
                addActionMessage("已取消分配所有权限");
            }
        }
        // 获取用户角色
        systemRoles = systemRoleService.listSystemRoleOfUser(userId);
        return SUCCESS;
    }

    /**
     * 修改用户信息
     *
     * @return
     * @throws Exception
     */
    @SecurityAntion(params = { "systemUser.remark" })
    public String editSystemUser() {
        if (!getCanUpdateUser()) {
            return NO_PRIVACY;
        }
        if (!validateSystemUser()) {
            replyDto.setIsSuccess(false);
            return wirteJsonMessage(replyDto);
        }
        if (systemUserService.updateSystemUser(systemUser) > 0) {
            logOperateAsyn("修改系统用户(" + systemUser.getId() + "," + systemUser.getUserName() + ")");
            replyDto.setIsSuccess(true);
            replyDto.setPromptMsg("用户信息修改成功！");
            return wirteJsonMessage(replyDto);
        }
        replyDto.setIsSuccess(false);
        replyDto.setErrorMsg("用户信息修改失败！");
        return wirteJsonMessage(replyDto);
    }

    /**
     * 重置用户密码
     *
     * @param userId
     * @return 返回重置密码
     */
    public void resetSystemUserPassword() throws Exception {
        if (userId <= 0 || userId == getUser().getId()) {
            response.getWriter().print("数据不正确");
            return;
        }

        SystemUser systemUser = systemUserService.updateResetSystemUserOfPassword(userId);
        if (null == systemUser) {
            response.getWriter().print("用户不存在");
            return;
        }

        String password = Config.getParam(BaseConstants.SYSTEM_USER_RESET_PASSWORD);
        // 记录操作日志
        logOperateAsyn("重置用户密码(" + userId + "," + systemUser.getUserName() + ")为" + password);
        response.getWriter().print("重置密码成功。新密码为：" + password);
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     * @throws IOException
     */
    public String delSystemUser() throws IOException {
        if (!checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_DEL_USER)) {
            return NO_PRIVACY;
        }
        try {
            // 先查询一次数据库看该用户是否应经被删除了
            SystemUser user = systemUserService.getSystemUserById(userId);
            if (user == null) {
                replyDto.setIsSuccess(false);
                replyDto.setErrorMsg("用户不存在");
                return wirteJsonMessage(replyDto);
            }
            long num = systemLogService.getUserOperatorNum(userId);
            if (num <= 0L) {// 判断该用户是否存在操作记录，没有，则删除用户，否则，冻结用户
                if (systemUserService.deleteSystemUser(userId) > 0) {
                    logOperateAsyn("删除系统用户(" + user.getUserName() + "," + user.getRealName() + ")");
                    replyDto.setIsSuccess(true);
                    replyDto.setPromptMsg("用户删除成功");
                    return wirteJsonMessage(replyDto);
                }
            }
            else {
                user = systemUserService.updateFreeseSystemUser(userId);
                if (user != null) {
                    logOperateAsyn("冻结系统用户(" + user.getUserName() + "," + user.getRealName() + ")");
                    replyDto.setIsSuccess(false);
                    replyDto.setErrorMsg("用户拥有操作记录，已执行冻结操作");
                    return wirteJsonMessage(replyDto);
                }
            }
            replyDto.setIsSuccess(false);
            replyDto.setErrorMsg("操作数据有误,用户删除失败");
            return wirteJsonMessage(replyDto);
        }
        catch (Exception e) {
            replyDto.setIsSuccess(false);
            replyDto.setErrorMsg("操作数据有误,删除失败！");
            return wirteJsonMessage(replyDto);
        }
    }

    /**
     * 冻结系统用户
     *
     * @param userId
     * @return
     * @throws IOException
     */
    public void cancelSystemUser() throws IOException {
        if (!checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_CANCEL_USER)) {
            response.getWriter().print("权限不足");
            return;
        }
        SystemUser systemUser = systemUserService.updateFreeseSystemUser(userId);
        if (null != systemUser) {
            // 记录操作日志
            logOperateAsyn("冻结系统用户(" + userId + "," + systemUser.getUserName() + ")");
        }
        response.getWriter().print("用户已被冻结");
    }

    /**
     * 解冻系统用户
     *
     * @param userId
     * @return
     * @throws IOException
     */
    public void backSystemUser() throws IOException {
        if (!checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_BACK_USER)) {
            response.getWriter().print("权限不足");
            return;
        }
        SystemUser systemUser = systemUserService.updateUnfreeseSystemUser(userId);
        if (null != systemUser) {
            // 记录操作日志
            logOperateAsyn("解冻系统用户(" + userId + "," + systemUser.getUserName() + ")");
        }
        response.getWriter().print("用户已解冻");
    }

    /**
     * 获取当前登录的系统角色
     *
     * @return
     */
    public CmsUserType getCurSystemUserType() {
        return systemUserService.getSystemUserById(getUser().getId()).getUserType();
    }

    /**
     * 验证用户数据是否合法
     *
     * @return
     */
    private boolean validateSystemUser() {
        if (StringUtils.isBlank(systemUser.getUserName())) {
            replyDto.setErrorMsg("用户名不能为空！");
            return false;
        }
        int userLen = net.zdsoft.keel.util.StringUtils.getRealLength(systemUser.getUserName());
        if (userLen < 4 || userLen > 25) {
            replyDto.setErrorMsg("用户名长度不小于4并且不大于25！");
            return false;
        }
        if (!Util.checkUserName(systemUser.getUserName())) {
            replyDto.setErrorMsg("用户名必须为数字、字母或下划线！");
            return false;
        }
        systemUser.setUserName(systemUser.getUserName().trim());
        if (!systemUserService.validateSystemUserName(systemUser.getId(), systemUser.getUserName())) {
            replyDto.setErrorMsg("用户名已存在！");
            return false;
        }
        if (userId > 0L) {
            if (StringUtils.isBlank(systemUser.getPassword())) {
                replyDto.setErrorMsg("密码不能为空!");
                return false;
            }
            int len = net.zdsoft.keel.util.StringUtils.getRealLength(systemUser.getPassword());
            if (len > 20 || len < 6) {
                replyDto.setErrorMsg("密码长度不小于6并且不大于20！");
                return false;
            }
        }
        if (StringUtils.isBlank(systemUser.getRealName())) {
            replyDto.setErrorMsg("姓名不能为空!");
            return false;
        }
        int realLen = net.zdsoft.keel.util.StringUtils.getRealLength(systemUser.getRealName());
        if (realLen > 20) {
            replyDto.setErrorMsg("姓名长度不能大于20！");
            return false;
        }
        systemUser.setRealName(systemUser.getRealName().trim());
        return true;
    }

    /**
     * 判断是否是超级管理员
     *
     * @param user
     * @return
     */
    public boolean isSuperUser(CmsUserType userType) {
        if (CmsUserType.SUPER_ADMIN == userType) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是平台普通管理员
     *
     * @param user
     * @return
     */
    public boolean isGeneralUser(CmsUserType userType) {
        if (CmsUserType.GENERAL_ADMIN == userType) {
            return true;
        }
        return false;
    }

    /**
     * 构造搜索条件
     *
     * @return
     */
    private List<QueryCondition> buildCondition() {
        List<QueryCondition> conditions = new ArrayList<QueryCondition>();
        // 用户名
        if (StringUtils.isNotBlank(username)) {
            conditions.add(new LikeCondition("username", username.trim()));
        }
        // 姓名
        if (StringUtils.isNotBlank(realname)) {
            conditions.add(new LikeCondition("realname", realname.trim()));
        }
        return conditions;
    }

    /******************************* 生成树 ****************************/
    @Override
    public List<SystemModule> getData() {
        // 获取用户权限列表
        List<SystemModule> systemModules = systemModuleService.listUserSystemModules(userId, true);
        return systemModules;
    }

    @Override
    public Node toNode(SystemModule t) {

        Node node = new Node();

        node.setId(String.valueOf(t.getId()));
        node.setParentId(String.valueOf(t.getParentId()));
        node.setName(t.getName());
        node.setObject(t);
        if (YesNoType.NO.equals(t.getIsOperate())) {
            node.setIsParent(true);
            node.setOpen(true);
        }
        if (YesNoType.YES.equals(t.getIsCommon())) {
            node.setChecked(true);
            node.setChkDisabled(true);
        }
        if (moduleCheckSet.contains(node.getId())) {
            node.setChecked(true);
        }
        return node;
    }

    /******************************* ajax ****************************/

    public void listSystemRole2json() throws Exception {
        String list = JSON.toJSONString(listSystemRole(userId)).toString();
        response.getWriter().print(list);
    }

    public void listSystemUserRole2json() throws Exception {
        String list = JSON.toJSONString(listSystemUserRole(userId)).toString();
        response.getWriter().print(list);
    }

    /**
     * 根据用户id获取用户当前拥有的角色列表（right）
     *
     * @param userId
     * @return
     */
    public List<SystemUserRole> listSystemUserRole(Long userId) {
        List<SystemUserRole> list = new ArrayList<SystemUserRole>();
        if (checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_ASSIGN_ROLE)) {
            list = systemUserRoleService.getSystemUserRole(userId, CmsRoleType.CUSTOM_ROLE.getValue());
        }
        return list;
    }

    /**
     * 根据角色类型获取系统的角色列表（不包括用户已有的角色列表left）
     *
     * @param userId
     * @return
     */
    public List<SystemUserRole> listSystemRole(Long userId) {
        List<SystemRole> list = new ArrayList<SystemRole>();
        List<SystemUserRole> list1 = new ArrayList<SystemUserRole>();
        if (checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_ASSIGN_ROLE)) {
            list = systemRoleService.getSystemRoles(null, CmsRoleType.CUSTOM_ROLE.getValue());
        }
        if (list.size() < 1) {
            return null;
        }
        List<SystemUserRole> list2 = listSystemUserRole(userId);
        if (!Validators.isEmpty(list2)) {
            boolean b = false;
            for (int i = 0; i < list.size(); i++) {
                b = false;
                for (int j = 0; j < list2.size(); j++) {
                    if (list.get(i).getId() == list2.get(j).getRoleId()) {
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    SystemUserRole systemUserRole = new SystemUserRole();
                    systemUserRole.setRoleId(list.get(i).getId());
                    systemUserRole.setRoleName(list.get(i).getName());
                    systemUserRole.setUserId(userId);
                    list1.add(systemUserRole);
                }
            }
        }
        else {
            for (int i = 0; i < list.size(); i++) {
                SystemUserRole systemUserRole = new SystemUserRole();
                systemUserRole.setRoleId(list.get(i).getId());
                systemUserRole.setRoleName(list.get(i).getName());
                systemUserRole.setUserId(userId);
                list1.add(systemUserRole);
            }
        }
        return list1;
    }

    /******************************* 权限验证 ****************************/
    public boolean getCanAssignRoleUser() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_ASSIGN_ROLE);// 分配权限
    }

    public boolean getCanAddUser() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_ADD_USER);// 添加用户
    }

    public boolean getCanEditUser() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_EDIT_USER);// 查看用户
    }

    public boolean getCanUpdateUser() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_UPDATE_USER);// 修改用户
    }

    public boolean getCanInfoUser() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_USER_MANAGE);// 角色管理
    }

    public boolean getCanFreezeUser() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_CANCEL_USER);// 冻结用户
    }

    public boolean getCanUnFreezeUser() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_BACK_USER);// 解冻用户
    }

    public boolean getCanDelUser() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_DEL_USER);// 删除用户
    }

    /******************************* getset ****************************/
    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public List<SystemUser> getSystemUsers() {
        return systemUsers;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<SystemRole> getSystemRoles() {
        return systemRoles;
    }

    public void setSystemRoles(List<SystemRole> systemRoles) {
        this.systemRoles = systemRoles;
    }

    public List<SystemModule> getSystemModules() {
        return systemModules;
    }

    public void setSystemModules(List<SystemModule> systemModules) {
        this.systemModules = systemModules;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public long getUserId() {
        return userId;
    }

    public void setSystemUsers(List<SystemUser> systemUsers) {
        this.systemUsers = systemUsers;
    }

}
