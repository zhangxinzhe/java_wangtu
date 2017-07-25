/*
 * @(#)UserGroupTypeDao.java    Created on 2016年6月13日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.basic;

import java.util.List;
import java.util.Map;

import net.zdsoft.common.entity.user.UserGroupType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年6月13日 上午10:41:10 $
 */
public interface UserGroupTypeDao {

    /**
     * 根据id查询分组类型
     *
     * @param id
     * @return
     */
    public UserGroupType getUserGroupTypeById(long id);

    /**
     * 新增
     *
     * @param entity
     */
    public int saveUserGroupType(UserGroupType entity);

    /**
     * 根据Id删除类型（IS_CAN_DEL=1）
     *
     * @param id
     */
    public int deleteUserGroupTypeById(long id);

    /**
     * 获取所有分组分类
     *
     * @return
     */
    public List<UserGroupType> getUserGroupTypeList();

    /**
     * 获取map
     *
     * @param ids
     * @return
     */
    public Map<Long, UserGroupType> getUserGroupTypeMap(Long[] ids);

}
