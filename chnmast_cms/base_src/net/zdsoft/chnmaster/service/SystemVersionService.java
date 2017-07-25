/* 
 * @(#)SystemVersionService.java    Created on 2013-8-9
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service;

import java.util.Date;

import net.zdsoft.common.entity.system.SystemVersion;

/**
 * @author y
 * @version $Revision: 1.0 $, $Date: 2013-8-9 下午4:29:56 $
 */
public interface SystemVersionService {

    /**
     * 获取当前最大的版本号id，有缓存
     * 
     * @return
     */
    public long getMaxSystemVersionId();

    /**
     * 获取当前最大的版本号，返回格式：5.0.0.0 build:131001，有缓存
     * 
     * @return
     */
    public String getMaxSystemVersionNo();

    /**
     * 取得最大版本号记录
     * 
     * @return
     */
    public SystemVersion getMaxSystemVersion();

    /**
     * 添加版本号记录（demo）
     * 
     * @param version
     * @return
     */
    public int saveSystemVersion(SystemVersion version);

    /**
     * 更新版本记录（demo）
     * 
     * @param version
     * @return
     */
    public int updateSystemVersion(SystemVersion version);

    /**
     * 指定数据库进行获取时间
     * 
     * @param index
     * @return
     */
    public Date getCurDataFromIndex(int index);

    /**
     * 获取数据库中当前日期
     * 
     * @return
     */
    public Date getCurDbDate();

}
