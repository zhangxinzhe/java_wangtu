/*
 * @(#)SystemRoleAction.java    Created on 2014-6-30
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.system;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;

import net.zdsoft.chnmaster.action.common.AbstractZTreeAction;
import net.zdsoft.chnmaster.constant.ChnmasterModuleConstants;
import net.zdsoft.chnmaster.entity.common.Node;
import net.zdsoft.chnmaster.entity.system.SystemModule;
import net.zdsoft.chnmaster.entity.system.SystemRole;
import net.zdsoft.chnmaster.entity.system.SystemUser;
import net.zdsoft.chnmaster.enums.CmsRoleType;
import net.zdsoft.chnmaster.enums.CmsUserType;
import net.zdsoft.chnmaster.service.system.SystemRoleModuleService;
import net.zdsoft.chnmaster.service.system.SystemRoleService;
import net.zdsoft.chnmaster.service.system.SystemUserRoleService;
import net.zdsoft.chnmaster.service.system.SystemUserService;
import net.zdsoft.common.annotation.SecurityAntion;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.common.utils.StringUtil;
import net.zdsoft.keel.util.Validators;

/**
 *
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-30 下午3:50:13 $
 */
@Scope("prototype")
@Controller
public class SystemRoleAction extends AbstractZTreeAction<SystemModule> {
    private static final long serialVersionUID = -4908130910850940079L;

    @Resource
    private SystemRoleService systemRoleService;
    @Resource
    private SystemUserService systemUserService;
    @Resource
    private SystemRoleModuleService systemRoleModuleService;
    @Resource
    private SystemUserRoleService systemUserRoleService;

    private List<SystemRole> systemRoles;
    private List<SystemModule> systemModules;
    private List<SystemUser> systemUsers;
    private List<Long> moduleIdList;
    private List<Long> userIdsList;
    private List<SystemUser> userList;
    private SystemRole systemRole;
    private String mods;// 页面上传来的选中的权限ids
    private String roleId;
    private long roleIds;// 用于ajax方法使用
    private Long[] userIds;// 页面上传来的需要分配角色的用户id

    /**
     * 用户角色管理
     *
     * @return
     * @throws Exception
     */
    public String systemRoleManage() throws Exception {
        // 验证权限
        if (!getCanInfoRole()) {
            return NO_PRIVACY;
        }

        systemRoles = systemRoleService.getSystemRoles(getPage(), CmsRoleType.CUSTOM_ROLE.getValue());
        return SUCCESS;
    }

    /**
     * 添加系统角色
     *
     * @return
     * @throws Exception
     */
    @SecurityAntion(params = { "systemRole.description" })
    public String addSystemRole() throws Exception {
        // 验证权限
        if (!getCanAddRole()) {
            return NO_PRIVACY;
        }
        if (systemRole == null) {
            return INPUT;
        }
        if (!validateSystemRole()) {
            replyDto.setIsSuccess(false);
            return wirteJsonMessage(replyDto);
        }
        // 将前台传过来的ids转换为long数组
        Long[] moduleIds = null;
        if (!Validators.isBlank(mods)) {
            moduleIds = (Long[]) ConvertUtils.convert(mods.split(","), Long.class);
        }
        else {
            replyDto.setIsSuccess(false);
            replyDto.setErrorMsg("角色权限不能为空");
            return wirteJsonMessage(replyDto);
        }
        long roleId = systemRoleService.saveSystemRole(systemRole, moduleIds);
        systemRole = systemRoleService.getSystemRole(roleId);
        // 记录操作日志
        logOperateAsyn("新增系统角色(" + systemRole.getId() + "," + systemRole.getName() + ")");
        replyDto.setIsSuccess(true);
        replyDto.setPromptMsg("角色添加成功！");
        return wirteJsonMessage(replyDto);
    }

    /**
     * 查看用户角色
     *
     * @return
     * @throws Exception
     */
    public String showSystemRole() throws Exception {
        // 验证权限
        if (!getCanShowRole()) {
            return NO_PRIVACY;
        }
        if (StringUtils.isEmpty(roleId)) {
            return NOT_EXIST;
        }
        Long id = StringUtil.toLongNumber(roleId);
        systemRole = systemRoleService.getSystemRole(id);
        if (systemRole == null) {
            return NOT_EXIST;
        }
        return SUCCESS;
    }

    /**
     * 修改系统角色
     *
     * @return
     * @throws Exception
     */
    @SecurityAntion(params = { "systemRole.description" })
    public String editSystemRole() throws Exception {
        // 【roleId != null 进入编辑页面】
        if (roleId != null) {
            // 验证权限
            if (!getCanUpdateRole()) {
                return NO_PRIVACY;
            }
            long id = Long.parseLong(roleId);
            systemRole = systemRoleService.getSystemRole(id);
            if (systemRole == null) {
                return NOT_EXIST;
            }
            return INPUT;
        }
        // 【roleId == null 对修改进行保存】
        else {
            // 验证权限
            if (!getCanUpdateRole()) {
                return NO_PRIVACY;
            }
            if (systemRole == null) {
                return NOT_EXIST;
            }
            if (!validateSystemRole()) {
                replyDto.setIsSuccess(false);
                return wirteJsonMessage(replyDto);
            }
            // 将前台传过来的ids转换为long数组
            Long[] moduleIds = null;
            if (!Validators.isBlank(mods)) {
                moduleIds = (Long[]) ConvertUtils.convert(mods.split(","), Long.class);
            }
            else {
                replyDto.setIsSuccess(false);
                replyDto.setErrorMsg("角色权限不能为空!");
                return wirteJsonMessage(replyDto);
            }
            // 删除角色原有的权限模块,插入新的权限模块
            systemRoleModuleService.deleteSystemRoleModuleByRoleId(systemRole.getId());
            long roleId = systemRoleService.saveSystemRole(systemRole, moduleIds);
            if (roleId == 0) {
                replyDto.setIsSuccess(false);
                replyDto.setErrorMsg("角色信息更新失败!");
                return wirteJsonMessage(replyDto);
            }
            // 记录操作日志
            logOperateAsyn("修改系统角色(" + systemRole.getId() + "," + systemRole.getName() + ")");
            // 成功后的查看页面
            systemRole = systemRoleService.getSystemRole(systemRole.getId());
            replyDto.setIsSuccess(true);
            replyDto.setPromptMsg("角色信息更新成功！");
            return wirteJsonMessage(replyDto);
        }
    }

    /**
     * 分配用户
     *
     * @return
     */
    public String assignSystemUser() {
        if (!getCanAssignUserRole()) {
            return NO_PRIVACY;
        }
        if (roleId == null) {
            return NOT_EXIST;
        }
        Long id = StringUtil.toLongNumber(roleId);
        SystemRole role = systemRoleService.getSystemRole(id);
        if (role == null) {
            return NOT_EXIST;
        }
        // 分配用户
        systemUserRoleService.updateSystemRoleToUsers(id, userIds, getUser().getId());
        // 记录操作日志
        logOperateAsyn("将权限(" + role.getName() + ")分配给用户");
        // 页面刷新后所需数据
        systemRole = systemRoleService.getSystemRole(id);
        addActionMessage("角色分配成功！");
        return SUCCESS;
    }

    /**
     * 验证角色数据
     *
     * @return
     */
    private boolean validateSystemRole() {
        if (StringUtils.isBlank(systemRole.getName())) {
            replyDto.setErrorMsg("角色名不能为空!");
            return false;
        }
        systemRole.setName(systemRole.getName().trim());
        if (StringUtils.isBlank(systemRole.getDescription())) {
            replyDto.setErrorMsg("描述不能为空!");
            return false;
        }
        if (!systemRoleService.checkRoleName(systemRole.getName(), systemRole.getId())) {
            replyDto.setErrorMsg("角色名已存在!");
            return false;
        }
        return true;
    }

    /**
     * ajax验证角色名
     */
    public void validateRoleName() {
        if (!systemRoleService.checkRoleName(systemRole.getName(), systemRole.getId())) {
            print(1);
        }
        else {
            print(2);
        }
    }

    /**
     * 获取当前登录的系统用户
     *
     * @return
     */
    public SystemUser getCurSystemUser() {
        return systemUserService.getSystemUserById(getUser().getId());
    }

    /**************************************** ajax *********************************************/
    /**
     * 删除角色
     *
     * @param userId
     * @return
     * @throws IOException
     */
    public String delSystemRole() throws IOException {
        if (!checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_DEL_ROLE)) {
            return NO_PRIVACY;
        }
        if (systemUserRoleService.getSystemUserIdsOfRoleId(roleIds).size() > 0) {
            replyDto.setIsSuccess(false);
            replyDto.setErrorMsg("当前角色已启用，删除失败");
            return wirteJsonMessage(replyDto);
        }
        try {
            SystemRole role = systemRoleService.getSystemRole(roleIds);
            // 删除角色对应的操作功能点权限
            systemRoleModuleService.deleteSystemRoleModuleByRoleId(role.getId());
            if (systemRoleService.deleteSystemRole(roleIds) > 0) {
                logOperateAsyn("删除用户角色(" + role.getName() + ")");
                replyDto.setIsSuccess(true);
                replyDto.setPromptMsg("角色删除成功！");
                return wirteJsonMessage(replyDto);
            }
            replyDto.setIsSuccess(false);
            replyDto.setErrorMsg("角色删除失败！");
            return wirteJsonMessage(replyDto);
        }
        catch (Exception e) {
            replyDto.setIsSuccess(false);
            replyDto.setErrorMsg("操作数据有误,请刷新后重试！");
            return wirteJsonMessage(replyDto);
        }
    }

    // 获取拥有该角色的用户(right)
    public void userList() throws IOException {
        if (getCanAssignUserRole()) {
            userList = systemUserRoleService.getSystemUserOfRoleId(roleIds);
            String list = JSON.toJSONString(userList).toString();
            response.getWriter().print(list);
        }
    }

    // 获取所有可分配的用户(left,不包括右侧的数据)
    public void systemUsers() throws IOException {
        if (getCanAssignUserRole()) {
            // 所有可分配的用户
            systemUsers = systemUserService.getSystemUsersByUserType(CmsUserType.GENERAL_ADMIN);
            // 已分配的用户
            userList = systemUserRoleService.getSystemUserOfRoleId(roleIds);
            if (!Validators.isEmpty(userList)) {
                for (int i = systemUsers.size() - 1; i >= 0; i--) {
                    for (int j = 0; j < userList.size(); j++) {
                        if (userList.get(j).getId() == systemUsers.get(i).getId()) {
                            systemUsers.remove(i);
                            break;
                        }
                    }
                }
            }
            String list = JSON.toJSONString(systemUsers).toString();
            response.getWriter().print(list);
        }
    }

    /******************************* 生成业务权限模块树 (所有模块，只包含公共选中的) ****************************/

    @Override
    public List<SystemModule> getData() {
        // 获取用户权限列表
        List<SystemModule> systemModules = systemModuleService.listUserSystemModules(1, true,
                CmsUserType.SUPER_ADMIN.getValue());
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
            node.setChkDisabled(true);
            node.setChecked(true);
        }
        return node;
    }

    /******************************* 生成角色对应业务权限模块树 (只包含角色拥有的) ****************************/
    public List<SystemModule> getDataOfRole() {
        Long id = StringUtil.toLongNumber(roleId);
        // 获取角色权限列表
        List<SystemModule> systemModules = systemModuleService.listSystemModuleOfRole(id);
        return systemModules;
    }

    public List<Node> getNodesOfRole() {
        List<SystemModule> data = getDataOfRole();
        List<Node> nodes = new LinkedList<Node>();
        if (data != null) {
            for (SystemModule t : data) {
                nodes.add(toNode(t));
            }
        }
        return nodes;
    }

    public void systemRoleModuleTree() throws Exception {
        String nodes = JSON.toJSONString(getNodesOfRole()).toString();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.getWriter().print(nodes);
    }

    /******************************* 生成整树，包含的数据打钩 ****************************/
    public List<SystemModule> getDataRoleFromAll() {
        // 获取所有的权限列表
        List<SystemModule> systemModules = systemModuleService.listUserSystemModules(1, true,
                CmsUserType.SUPER_ADMIN.getValue());
        return systemModules;
    }

    public List<Node> getNodesRoleFromAll() {
        List<SystemModule> data = getDataRoleFromAll();
        Long id = StringUtil.toLongNumber(roleId);
        // 获取要打钩的id
        List<Long> moduleCheckSet = systemModuleService.listSystemModuleIdsOfRole(id);
        List<Node> nodes = new LinkedList<Node>();
        if (data != null) {
            for (SystemModule t : data) {
                nodes.add(toNodeRoleFromAll(t, moduleCheckSet));
            }
        }
        return nodes;
    }

    public Node toNodeRoleFromAll(SystemModule t, List<Long> moduleCheckSet) {

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
            node.setChkDisabled(true);
            node.setChecked(true);
        }
        if (moduleCheckSet.contains(t.getId())) {
            node.setChecked(true);
        }
        return node;
    }

    public void systemRoleModuleTreeFromAll() throws Exception {
        String nodes = JSON.toJSONString(getNodesRoleFromAll()).toString();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.getWriter().print(nodes);
    }

    /******************************* 权限相关 ****************************/

    public boolean getCanAddRole() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_ADD_ROLE);// 新增角色
    }

    public boolean getCanInfoRole() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_ROLE_MANAGE);// 角色管理
    }

    public boolean getCanShowRole() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_SHOW_ROLE);// 查看角色
    }

    public boolean getCanUpdateRole() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_UPDATE_ROLE);// 修改角色
    }

    public boolean getCanAssignUserRole() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_ASSIGN_USER);// 分配用户
    }

    public boolean getCanFreezeUser() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_CANCEL_USER);// 冻结用户
    }

    public boolean getCanUnFreezeUser() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_BACK_USER);// 解冻用户
    }

    public boolean getCanDeleteRole() {
        return checkUserMudule(ChnmasterModuleConstants.MODULE_SYSTEM_DEL_ROLE);// 删除角色
    }

    /******************************* getset ****************************/

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

    public SystemRole getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(SystemRole systemRole) {
        this.systemRole = systemRole;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<Long> getModuleIdList() {
        return moduleIdList;
    }

    public void setModuleIdList(List<Long> moduleIdList) {
        this.moduleIdList = moduleIdList;
    }

    public List<Long> getUserIdsList() {
        return userIdsList;
    }

    public void setUserIdsList(List<Long> userIdsList) {
        this.userIdsList = userIdsList;
    }

    public List<SystemUser> getSystemUsers() {
        return systemUsers;
    }

    public void setSystemUsers(List<SystemUser> systemUsers) {
        this.systemUsers = systemUsers;
    }

    public String getMods() {
        return mods;
    }

    public void setMods(String mods) {
        this.mods = mods;
    }

    public long getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(long roleIds) {
        this.roleIds = roleIds;
    }

    public List<SystemUser> getUserList() {
        return userList;
    }

    public void setUserList(List<SystemUser> userList) {
        this.userList = userList;
    }

    public Long[] getUserIds() {
        return userIds;
    }

    public void setUserIds(Long[] userIds) {
        this.userIds = userIds;
    }

}
