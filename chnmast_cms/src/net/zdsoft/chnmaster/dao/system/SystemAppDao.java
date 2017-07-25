/* 
 * @(#)SystemAppDao.java    Created on 2015-4-13
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.system;

import java.util.List;

import net.zdsoft.chnmaster.entity.system.SystemApp;

/**
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2015-4-13 下午3:51:41 $
 */
public interface SystemAppDao {

    /**
     * 获取所有在用的子系统列表（带缓存）
     * 
     * @return
     */
    public List<SystemApp> listUsingSystemApp();

    /**
     * 根据用户id获取能访问的子系统id
     * 
     * @param userId
     * @return
     */
    public List<Long> listSystemAppIdsOfUser(long userId);

}
