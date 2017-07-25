/*
 * @(#)UserGroupTypeServiceImpl.java    Created on 2016年6月13日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.basic.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.basic.UserGroupTypeDao;
import net.zdsoft.chnmaster.service.basic.UserGroupTypeService;
import net.zdsoft.chnmaster.service.common.BaseServiceImpl;
import net.zdsoft.common.entity.user.UserGroupType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年6月13日 上午10:40:34 $
 */
@Service("userGroupTypeService")
public class UserGroupTypeServiceImpl extends BaseServiceImpl implements UserGroupTypeService {

    @Resource
    private UserGroupTypeDao userGroupTypeDao;

    @Override
    public UserGroupType getUserGroupTypeById(long id) {
        return userGroupTypeDao.getUserGroupTypeById(id);
    }

    @Override
    public int saveUserGroupType(UserGroupType entity) {
        return userGroupTypeDao.saveUserGroupType(entity);
    }

    @Override
    public int deleteUserGroupTypeById(long id) {
        return userGroupTypeDao.deleteUserGroupTypeById(id);
    }

    @Override
    public List<UserGroupType> getUserGroupTypeList() {
        return userGroupTypeDao.getUserGroupTypeList();
    }

    @Override
    public Map<Long, UserGroupType> getUserGroupTypeMap(Long[] ids) {
        return userGroupTypeDao.getUserGroupTypeMap(ids);
    }
}
