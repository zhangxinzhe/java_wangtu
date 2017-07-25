/* 
 * @(#)SystemRoleDaoImpl.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.system.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.system.SystemRoleDao;
import net.zdsoft.chnmaster.entity.system.SystemRole;
import net.zdsoft.chnmaster.entity.system.mapper.SystemRoleRowMapper;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.entity.PageDto;

@SuppressWarnings("unchecked")
@Repository("systemRoleDao")
public class SystemRoleDaoImpl extends BaseDaoImpl implements SystemRoleDao {

    private static final String save_system_role = "system.save_system_role";
    private static final String update_system_role = "system.update_system_role";
    private static final String delete_system_role_byid = "system.delete_system_role_byid";
    private static final String get_system_role_byid = "system.get_system_role_byid";
    private static final String list_system_role = "system.list_system_role";
    private static final String get_system_role_by_userid = "system.get_system_role_by_userid";
    private static final String list_system_role_of_roletype = "system.list_system_role_of_roletype";
    private static final String get_system_role_num_by_rolename = "system.get_system_role_num_by_rolename";

    @Override
    // 已调用
    public long saveSystemRole(SystemRole systemRole) {
        long roleId = generatePrimaryKey("T_SYSTEM_ROLE");
        executeUpdate(getSql(save_system_role),
                new Object[] { roleId, systemRole.getName(), systemRole.getDescription(), systemRole.getCreateTime(),
                        systemRole.getRoleType().getValue() });
        return roleId;
    }

    @Override
    // 已调用
    public SystemRole getSystemRole(long id) {
        return (SystemRole) findForObject(getSql(get_system_role_byid), new Object[] { id },
                SystemRoleRowMapper.instance());
    }

    @Override
    // 已调用
    public int updateSystemRole(SystemRole systemRole) {
        return this.executeUpdate(getSql(update_system_role),
                new Object[] { systemRole.getDescription(), systemRole.getName(), systemRole.getId() });
    }

    @Override
    public int deleteSystemRole(long id) {// 已调用
        return executeUpdate(getSql(delete_system_role_byid), new Object[] { id });
    }

    @Override
    // 已调用
    public List<SystemRole> getSystemRoles() {
        return this.find(getSql(list_system_role), new Object[] {}, SystemRoleRowMapper.instance());
    }

    @Override
    // 已调用
    public List<SystemRole> getSystemRoles(PageDto page, int roleType) {
        if (page == null) {
            return this.find(getSql(list_system_role_of_roletype), new Object[] { roleType },
                    SystemRoleRowMapper.instance());
        }
        return this.findForPage(getSql(list_system_role_of_roletype), new Object[] { roleType },
                SystemRoleRowMapper.instance(), page);
    }

    @Override
    // 已调用
    public List<SystemRole> listSystemRoleOfUser(long userId) {
        return find(getSql(get_system_role_by_userid), new Object[] { userId }, SystemRoleRowMapper.instance());
    }

    @Override
    // 已调用
    public int getSystemRoleNum(String roleName, long roleId) {
        return findForInt(getSql(get_system_role_num_by_rolename), new Object[] { roleName, roleId });
    }
}
