/* 
 * @(#)BaseService.java    Created on 2013-8-16
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.common;

/**
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2013-8-16 上午11:29:19 $
 */
public interface BaseService {
    /**
     * 同步记录操作日志<br>
     * userId是当前登录用户的Id
     * 
     * @param operate
     */
    public void logOperate(String operate, long userId, String ip);

    /**
     * 异步记录操作日志（附加业务方式实现异步记录日志）<br>
     * userId是当前登录用户的Id
     * 
     * @param operate
     */
    public void logOperateAsyn(String operate, long userId, String ip);

}
