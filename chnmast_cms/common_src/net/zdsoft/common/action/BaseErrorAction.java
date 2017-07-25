/* 
 * @(#)BaseErrorAction.java    Created on 2013-11-7
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.action;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2013-11-7 上午11:39:10 $
 */
public interface BaseErrorAction {
    /**
     * 出现错误
     * 
     * @return
     */
    public String error();

    /**
     * 出现异常
     * 
     * @return
     */
    public String exception();

    /**
     * 没有权限
     * 
     * @return
     */
    public String noprivacy();

    /**
     * 资源不存在
     * 
     * @return
     */
    public String notexist();
}
