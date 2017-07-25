/* 
 * @(#)SystemVersionDao.java    Created on 2013-8-9
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.zdsoft.common.dao.BaseDao;
import net.zdsoft.common.entity.system.SystemVersion;

/**
 * @author y
 * @version $Revision: 1.0 $, $Date: 2013-8-9 下午4:28:42 $
 */
public interface SystemVersionDao extends BaseDao {

    /**
     * 获取当前最大的版本号id
     * 
     * @return
     */
    public long getMaxSystemVersionId();

    /**
     * 获取当前最大的版本记录
     * 
     * @return
     */
    public SystemVersion getMaxSystemVersion();

    /**
     * 获取系统所有版本记录，并以Map<id, SystemVersion>的方式返回（demo）
     * 
     * @return
     */
    public Map<Long, SystemVersion> getSystemVersionMap();

    /**
     * 根据ids取得指定的版本记录（demo）
     * 
     * @param ids
     * @return
     */
    public List<SystemVersion> getSystemVersionByids(Long[] ids);

    /**
     * 添加版本号记录（demo）
     * 
     * @param version
     * @return
     */
    public int saveSystemVersion(SystemVersion version);

    /**
     * 更改版本记录（demo）
     * 
     * @param version
     * @return
     */
    public int updateSystemVersion(SystemVersion version);

    /**
     * 获取数据库中当前日期
     * 
     * @return
     */
    public Date getCurDbDate();

}
