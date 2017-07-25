/* 
 * @(#)SystemModuleDaoImpl.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.system.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.system.SystemModuleDao;
import net.zdsoft.chnmaster.entity.system.SystemModule;
import net.zdsoft.chnmaster.entity.system.mapper.SystemModuleRowMapper;
import net.zdsoft.common.dao.BaseDaoImpl;

@SuppressWarnings("unchecked")
@Repository("systemModuleDao")
public class SystemModuleDaoImpl extends BaseDaoImpl implements SystemModuleDao {
    private static final String list_systemmodule = "system.list_systemmodule";
    private static final String list_systemmodule_of_role = "system.list_systemmodule_of_role";
    private static final String list_systemmodule_ids_of_role = "system.list_systemmodule_ids_of_role";
    private static final String list_systemmodule_of_user = "system.list_systemmodule_of_user";
    private static final String list_systemmoduleid_of_user = "system.list_systemmoduleid_of_user";
    private static final String canvisit_systemmodule = "system.canvisit_systemmodule";
    private static final String list_systemmodule_of_user_roletype = "system.list_systemmodule_of_user_roletype";

    @Override
    // 已调用
    public List<Long> listSystemModuleIdsOfRole(long roleId) {
        return find(getSql(list_systemmodule_ids_of_role), new Object[] { roleId }, new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                return rs.getLong("MODULEID");
            }
        });
    }

    @Override
    public List<Long> listSystemModuleIdsOfUser(long userId, long appId) {
        return find(getSql(list_systemmoduleid_of_user), new Object[] { userId, appId }, new RowMapper<Long>() {

            @Override
            public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                return rs.getLong("MODULEID");
            }

        });
    }

    @Override
    public boolean canVisitSystemModule(long userId, long moduleId) {
        return findForInt(getSql(canvisit_systemmodule), new Object[] { moduleId, userId }) > 0;
    }

    @Override
    public List<SystemModule> listSystemModule() {
        return find(getSql(list_systemmodule), null, SystemModuleRowMapper.instance());
    }

    @Override
    // 已调用
    public List<SystemModule> listSystemModuleOfRole(long roleId) {
        StringBuilder sql = new StringBuilder(getSql(list_systemmodule_of_role));
        return find(sql.toString(), new Object[] { roleId }, SystemModuleRowMapper.instance());
    }

    @Override
    public List<SystemModule> listSystemModuleOfParent(long userId, long parentId) {
        StringBuffer sql = new StringBuffer(getSql(list_systemmodule_of_user));
        sql.append(" AND PARENTID=" + parentId);
        sql.append(" ORDER BY ID ASC");

        return find(sql.toString(), new Object[] { userId }, SystemModuleRowMapper.instance());
    }

    @Override
    // 已调用
    public List<SystemModule> listUserSystemModules(long userId, boolean includeHide) {
        StringBuffer sql = new StringBuffer(getSql(list_systemmodule_of_user));
        if (!includeHide) {
            sql.append(" AND ISSHOW=1 ");
        }
        return find(sql.toString(), new Object[] { userId }, SystemModuleRowMapper.instance());
    }

    @Override
    // 已调用
    public List<SystemModule> listUserSystemModules(long userId, boolean includeHide, int roleType) {
        StringBuffer sql = new StringBuffer(getSql(list_systemmodule_of_user_roletype));
        if (!includeHide) {
            sql.append(" AND M.ISSHOW=1 ");
        }

        return find(sql.toString(), new Object[] { userId, roleType }, SystemModuleRowMapper.instance());
    }
}
