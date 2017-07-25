/* 
 * @(#)SystemUserRoleDaoImpl.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.system.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.system.SystemUserRoleDao;
import net.zdsoft.chnmaster.entity.system.SystemUser;
import net.zdsoft.chnmaster.entity.system.SystemUserRole;
import net.zdsoft.chnmaster.entity.system.mapper.SystemUserRoleRowMapper;
import net.zdsoft.chnmaster.entity.system.mapper.SystemUserRowMapper;
import net.zdsoft.common.dao.BaseDaoImpl;

@SuppressWarnings("unchecked")
@Repository("systemUserRoleDao")
public class SystemUserRoleDaoImpl extends BaseDaoImpl implements SystemUserRoleDao {
    private static final String save_system_user_role = "system.save_system_user_role";
    private static final String list_system_user_role = "system.list_system_user_role";
    private static final String get_systemuser_ids_by_roleid = "system.get_systemuser_ids_by_roleid";
    private static final String get_systemuser_by_roleid = "system.get_systemuser_by_roleid";
    private static final String delete_system_user_role_by_userid = "system.delete_system_user_role_by_userid";
    private static final String get_system_user_role_by_userid_roleType = "system.get_system_user_role_by_userid_roleType";
    private static final String delete_system_role = "system.delete_system_role";

    @Override
    public int saveSystemUserRole(SystemUserRole systemUserRole) {
        return saveEntity(getSql(save_system_user_role),
                new Object[] { systemUserRole.getUserId(), systemUserRole.getRoleId() }, systemUserRole);
    }

    @Override
    // 已调用
    public List<SystemUserRole> getSystemUserRoles() {
        return this.find(getSql(list_system_user_role), new Object[] {}, SystemUserRoleRowMapper.instance());
    }

    @Override
    // 已调用
    public int[] updateSystemUsersForRole(final Long[] userIds, final long roleid) {
        BatchPreparedStatementSetter pss = new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, userIds[i]);
                ps.setLong(2, roleid);
            }

            @Override
            public int getBatchSize() {
                return userIds.length;
            }
        };

        return batchUpdate(getSql(save_system_user_role), pss);
    }

    @Override
    // 已调用
    public List<Long> getSystemUserIdsOfRoleId(long roleId) {
        return (List<Long>) find(getSql(get_systemuser_ids_by_roleid), new Object[] { roleId });
    }

    @Override
    // 已调用
    public List<SystemUser> getSystemUserOfRoleId(long roleId) {

        return find(getSql(get_systemuser_by_roleid), new Object[] { roleId }, SystemUserRowMapper.instance());
    }

    @Override
    // 已调用
    public int deleteSystemRoleByUserId(long userId) {
        return executeUpdate(getSql(delete_system_user_role_by_userid), new Object[] { userId });
    }

    @Override
    // 已调用
    public int[] saveBatchSystemUserRole(final Long[] roleIds, final long userId) {
        return batchUpdate(getSql(save_system_user_role), new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, userId);
                ps.setLong(2, roleIds[i]);
            }

            @Override
            public int getBatchSize() {
                return roleIds.length;
            }
        });
    }

    @Override
    // 已调用
    public int deleteSystemRole(long roleId) {
        return executeUpdate(getSql(delete_system_role), new Object[] { roleId });
    }

    @Override
    // 已调用
    public List<SystemUserRole> getSystemUserRole(long userId, int roleType) {
        return find(getSql(get_system_user_role_by_userid_roleType), new Object[] { userId, roleType },
                new RowMapper<SystemUserRole>() {

                    @Override
                    public SystemUserRole mapRow(ResultSet rs, int arg1) throws SQLException {
                        SystemUserRole userRole = new SystemUserRole();
                        userRole.setId(rs.getLong("ID"));
                        userRole.setRoleId(rs.getLong("ROLEID"));
                        userRole.setRoleName(rs.getString("ROLENAME"));
                        userRole.setUserId(rs.getLong("USERID"));
                        return userRole;
                    }

                });
    }

}
