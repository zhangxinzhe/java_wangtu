/* 
 * @(#)BeanSelfAware.java    Created on 2008-5-4
 * Copyright (c) 2008 ZDSoft Networks, Inc. All rights reserved.
 * $Id: BeanSelfAware.java 18608 2011-08-23 13:01:04Z yeq $
 */
package net.zdsoft.common.service;

/**
 * 表示某个Bean需要设置其代理类做为服务的接口.
 * 
 * @author huangwj
 * @version $Revision: 18608 $, $Date: 2011-08-23 21:01:04 +0800 (星期二, 23 八月 2011) $
 */
public interface BeanSelfAware {

    void setSelf(Object proxyBean);

}
