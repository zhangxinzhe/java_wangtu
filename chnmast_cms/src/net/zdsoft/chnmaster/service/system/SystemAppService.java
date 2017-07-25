/* 
 * @(#)SystemAppService.java    Created on 2015-4-13
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.system;

import java.util.List;

import net.zdsoft.chnmaster.entity.system.SystemApp;

/**
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2015-4-13 下午3:39:03 $
 */
public interface SystemAppService {

    /**
     * 获取所有在用的子系统列表（带缓存）
     * 
     * @return
     */
    public List<SystemApp> listUsingSystemApp();

    /**
     * 获取用户有权限访问的在用子系统，按子系统id排序（有缓存）
     * 
     * @param userId
     * @return
     */
    public List<SystemApp> listSystemAppOfUser(long userId);
}
