/* 
 * @(#)SystemRoleModuleDaoImpl.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.system.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.system.SystemRoleModuleDao;
import net.zdsoft.common.dao.BaseDaoImpl;

@Repository("systemRoleModuleDao")
public class SystemRoleModuleDaoImpl extends BaseDaoImpl implements SystemRoleModuleDao {
    private static final String save_system_role_module = "system.save_system_role_module";
    private static final String delete_system_role_module_byroleid = "system.delete_system_role_module_byroleid";

    @Override
    // 已调用
    public int deleteSystemRoleModuleByRoleId(long roleId) {
        return executeUpdate(getSql(delete_system_role_module_byroleid), new Object[] { roleId });
    }

    @Override
    // 已调用
    public void batchAddSystemRoleModule(final long roleId, final Long[] moduleIds) {
        try {
            batchUpdate(getSql(save_system_role_module), new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int j) throws SQLException {
                    if (moduleIds[j] != null) {
                        ps.setLong(1, roleId);
                        ps.setLong(2, moduleIds[j]);
                    }
                }

                @Override
                public int getBatchSize() {
                    return moduleIds.length;
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
