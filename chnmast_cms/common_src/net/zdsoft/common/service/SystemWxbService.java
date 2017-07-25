/* 
 * @(#)SystemWxbService.java    Created on 2015-5-22
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.service;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015-5-22 下午3:35:29 $
 */
public interface SystemWxbService {

    /**
     * 获取无限宝pc客户端路径
     * 
     * @return
     */
    public String getWxbPcClientUrl();

    /**
     * 获取无限宝Android客户端路径
     * 
     * @return
     */
    public String getWxbAndroidClientUrl();

    /**
     * 获取无限宝更新目录
     * 
     * @param agencyId
     * @return
     */
    public String getWxbClientUpdateUrl();
}
