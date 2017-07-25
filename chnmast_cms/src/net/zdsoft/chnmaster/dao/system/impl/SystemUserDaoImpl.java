/* 
 * @(#)SystemUserDaoImpl.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.system.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.config.Config;
import net.zdsoft.chnmaster.dao.system.SystemUserDao;
import net.zdsoft.chnmaster.entity.system.SystemUser;
import net.zdsoft.chnmaster.entity.system.mapper.SystemUserRowMapper;
import net.zdsoft.chnmaster.enums.CmsUserType;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.dao.queryCondition.QueryConditionBuilder;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.common.utils.MD5;
import net.zdsoft.keel.jdbc.MapRowMapper;

@SuppressWarnings("unchecked")
@Repository("systemUserDao")
public class SystemUserDaoImpl extends BaseDaoImpl implements SystemUserDao {
    private final static String get_systemuser_by_name_and_pwd = "system.get_systemuser_by_name_and_pwd";
    private final static String get_system_user_id = "system.get_system_user_id";
    private final static String save_systemuser = "system.save_systemuser";
    private final static String del_systemuser = "system.del_systemuser";
    private final static String get_systemuser_by_id = "system.get_systemuser_by_id";
    private final static String add_systemuser = "system.add_systemuser";
    private final static String update_systemuser = "system.update_systemuser";
    private final static String validate_systemuser_of_name = "system.validate_systemuser_of_name";
    private final static String list_system_admin_user = "system.list_system_admin_user";
    private final static String system_user_name_map_by_ids = "system.system_user_name_map_by_ids";
    private final static String set_systemuser_password = "system.set_systemuser_of_password";
    private final static String reset_systemuser_of_password = "system.reset_systemuser_of_password";
    private final static String update_systemuser_of_isfreeze = "system.update_systemuser_of_isfreeze";
    private final static String list_systemuser_by_usertype = "system.list_systemuser_by_usertype";

    @Override
    public List<Long> getSystemUserId(String username, String realname) {
        StringBuffer sqlBuffer = new StringBuffer(getSql(get_system_user_id) + " WHERE 1 = 1");
        List<Object> argsList = new ArrayList<Object>();
        if (StringUtils.isNotBlank(username)) {
            sqlBuffer.append(" AND USERNAME LIKE ?");
            argsList.add("%" + username + "%");
        }

        if (StringUtils.isNotBlank(realname)) {
            sqlBuffer.append(" AND REALNAME LIKE ?");
            argsList.add("%" + realname + "%");
        }
        return find(sqlBuffer.toString(), argsList.toArray(), SystemUserRowMapper.systemUserIdRowMapper);
    }

    @Override
    public SystemUser getSystemUser(String username, String password) {
        return (SystemUser) findForObject(getSql(get_systemuser_by_name_and_pwd), new Object[] { username, password },
                SystemUserRowMapper.instance());
    }

    @Override
    // 已调用
    public int updateSystemUserOfPassword(long userId, String oldPassword, String newPassword) {
        return executeUpdate(getSql(set_systemuser_password), new Object[] { new MD5().getStrToMD5(newPassword),
                userId, new MD5().getStrToMD5(oldPassword) });
    }

    @Override
    public int saveUser(SystemUser user) throws Exception {
        return saveEntity(
                getSql(save_systemuser),
                new Object[] { user.getUserName(), user.getRealName(), user.getRemark(), "", user.getPassword(),
                        user.getCreateTime(), user.getUserType().getValue() }, user);
    }

    @Override
    // 已调用
    public int delUser(long id) throws Exception {
        return executeUpdate(getSql(del_systemuser), new Object[] { id });
    }

    @Override
    // 已调用
    public SystemUser getSystemUserById(long userId) {
        return (SystemUser) findForObject(getSql(get_systemuser_by_id), new Object[] { userId },
                SystemUserRowMapper.instance());
    }

    @Override
    // 已调用
    public long addSystemUser(SystemUser user) {
        long userId = generatePrimaryKey("T_SYSTEM_USER");
        executeUpdate(getSql(add_systemuser),
                new Object[] { userId, user.getUserName(), user.getRealName(), user.getPassword(), user.getRemark(),
                        user.getUserType().getValue(), user.getCreateTime(), user.getIsFreeze().getValue(),
                        user.getIsDeleted().getValue() });
        return userId;
    }

    @Override
    // 已调用
    public int updateSystemUser(SystemUser user) {
        return executeUpdate(getSql(update_systemuser),
                new Object[] { user.getUserName(), user.getRealName(), user.getRemark(), user.getId() });
    }

    @Override
    // 已调用
    public boolean validateSystemUserName(long userId, String username) {
        StringBuffer sql = new StringBuffer(getSql(validate_systemuser_of_name));
        if (userId > 0) {
            sql.append(" AND ID!=" + userId);
        }
        sql.append(" AND ISDELETED = 0");
        return findForInt(sql.toString(), new Object[] { username }) == 0;
    }

    @Override
    // 已调用
    public List<SystemUser> getSystemAdminUser(PageDto page, List<QueryCondition> conditions) {
        QueryConditionBuilder builder = new QueryConditionBuilder();
        builder.addConditions(conditions);
        StringBuilder sql = new StringBuilder(getSql(list_system_admin_user));
        sql.append(" WHERE USERTYPE = 2 ");
        if (StringUtils.isNotBlank(builder.buildCondition())) {
            sql.append(" AND ");
        }
        sql.append(builder.buildCondition());
        sql.append(" AND ISDELETED=0 ORDER BY CREATETIME DESC");
        return findForPage(sql.toString(), builder.buildParameters(), SystemUserRowMapper.instance(), page);
    }

    @Override
    public List<SystemUser> getGenenalAdminUser(PageDto page, List<QueryCondition> conditions) {
        QueryConditionBuilder builder = new QueryConditionBuilder();
        builder.addConditions(conditions);
        StringBuilder sql = new StringBuilder(getSql(list_system_admin_user));
        sql.append(" WHERE usertype>=3 AND usertype<=4 ");
        if (StringUtils.isNotBlank(builder.buildCondition())) {
            sql.append(" AND ");
        }
        sql.append(builder.buildCondition());
        sql.append(" AND ISDELETED=0 ORDER BY CREATETIME DESC");
        if (page == null) {
            return find(sql.toString(), builder.buildParameters(), SystemUserRowMapper.instance());
        }
        else {
            return findForPage(sql.toString(), builder.buildParameters(), SystemUserRowMapper.instance(), page);
        }
    }

    @Override
    // 已调用
    public SystemUser resetSystemUserOfPassword(long userId) {
        SystemUser systemUser = getSystemUserById(userId);
        if (null == systemUser) {
            return null;
        }

        if (1 == executeUpdate(getSql(reset_systemuser_of_password),
                new Object[] { new MD5().getStrToMD5(Config.getParam(BaseConstants.SYSTEM_USER_RESET_PASSWORD)), userId })) {
            return systemUser;
        }
        return null;
    }

    @Override
    // 已调用
    public SystemUser updateFreeseSystemUser(long userId) {
        SystemUser systemUser = getSystemUserById(userId);
        if (null == systemUser) {
            return null;
        }

        if (executeUpdate(getSql(update_systemuser_of_isfreeze), new Object[] { YesNoType.YES.getValue(), userId }) == 1) {
            return systemUser;
        }
        return null;
    }

    @Override
    // 已调用
    public SystemUser updateUnfreeseSystemUser(long userId) {
        SystemUser systemUser = getSystemUserById(userId);
        if (null == systemUser) {
            return null;
        }

        if (executeUpdate(getSql(update_systemuser_of_isfreeze), new Object[] { YesNoType.NO.getValue(), userId }) == 1) {
            return systemUser;
        }
        return null;
    }

    @Override
    // 已调用
    public List<SystemUser> getSystemUsersByUserType(CmsUserType userType) {
        return find(getSql(list_systemuser_by_usertype), new Object[] { userType.getValue() },
                SystemUserRowMapper.instance());
    }

    @Override
    public Map<Long, String> getSystemUserMapByIds(Long[] userIds) {
        return findForInSQL(getSql(system_user_name_map_by_ids), null, userIds, new MapRowMapper<Long, String>() {
            @Override
            public Long mapRowKey(ResultSet rs, int arg1) throws SQLException {
                return rs.getLong("ID");
            }

            @Override
            public String mapRowValue(ResultSet rs, int arg1) throws SQLException {
                return rs.getString("USERNAME");
            }
        });
    }

    @Override
    public SystemUser getSystemUserByUserName(String userName) {
        StringBuilder sql = new StringBuilder(getSql(list_system_admin_user));
        sql.append(" WHERE USERNAME = ? AND ISDELETED = 0");
        return (SystemUser) findForObject(sql.toString(), new Object[] { userName }, SystemUserRowMapper.instance());
    }

}
