/* 
 * @(#)SystemAppDaoImpl.java    Created on 2015-4-13
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.system.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.system.SystemAppDao;
import net.zdsoft.chnmaster.entity.system.SystemApp;
import net.zdsoft.chnmaster.entity.system.mapper.SystemAppRowMapper;
import net.zdsoft.common.dao.BaseDaoImpl;

/**
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2015-4-13 下午3:52:11 $
 */
@SuppressWarnings("unchecked")
@Repository("systemAppDao")
public class SystemAppDaoImpl extends BaseDaoImpl implements SystemAppDao {
    private static final String list_systemapp_using = "system.list_systemapp_using";
    private static final String list_systemappid_of_user = "system.list_systemappid_of_user";

    @Override
    public List<SystemApp> listUsingSystemApp() {
        return find(getSql(list_systemapp_using), null, SystemAppRowMapper.instance());
    }

    @Override
    public List<Long> listSystemAppIdsOfUser(long userId) {
        return find(getSql(list_systemappid_of_user), new Object[] { userId }, new RowMapper<Long>() {

            @Override
            public Long mapRow(ResultSet rs, int arg1) throws SQLException {
                return rs.getLong("APP_ID");
            }

        });
    }

}
