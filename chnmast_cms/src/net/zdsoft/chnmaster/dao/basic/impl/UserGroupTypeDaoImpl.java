/*
 * @(#)UserGroupTypeDao.java    Created on 2016年6月13日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.basic.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.basic.UserGroupTypeDao;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.entity.user.UserGroupType;
import net.zdsoft.common.entity.user.mapper.UserGroupTypeRowMapper;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年6月13日 上午10:41:28 $
 */
@SuppressWarnings("unchecked")
@Repository("userGroupTypeDao")
public class UserGroupTypeDaoImpl extends BaseDaoImpl implements UserGroupTypeDao {

    @Override
    public UserGroupType getUserGroupTypeById(long id) {
        String sql = "SELECT * FROM T_USER_GROUP_TYPE WHERE ID = ?";
        return (UserGroupType) findForObject(sql, new Object[] { id }, UserGroupTypeRowMapper.instance());
    }

    @Override
    public int saveUserGroupType(UserGroupType entity) {
        String sql = "INSERT INTO T_USER_GROUP_TYPE(TITLE, IS_CAN_DEL) VALUES (?, ?)";
        return executeUpdate(sql, new Object[] { entity.getTitle(), entity.getIsCanDel().getValue() });
    }

    @Override
    public int deleteUserGroupTypeById(long id) {
        String sql = "DELETE FROM T_USER_GROUP_TYPE WHERE IS_CAN_DEL = 1 AND ID = ?";
        return executeUpdate(sql, new Object[] { id });
    }

    @Override
    public List<UserGroupType> getUserGroupTypeList() {
        String sql = "SELECT * FROM T_USER_GROUP_TYPE";
        return find(sql, null, UserGroupTypeRowMapper.instance());
    }

    @Override
    public Map<Long, UserGroupType> getUserGroupTypeMap(Long[] ids) {
        String sql = "SELECT * FROM T_USER_GROUP_TYPE WHERE ID In";
        return findForInSQL(sql, null, ids, UserGroupTypeRowMapper.userGroupTypeMap);
    }
}
