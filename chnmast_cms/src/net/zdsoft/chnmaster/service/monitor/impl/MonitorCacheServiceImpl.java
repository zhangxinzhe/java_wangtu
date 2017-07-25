/* 
 * @(#)MonitorCacheServiceImpl.java    Created on 2015-7-10
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.monitor.impl;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.service.common.BaseServiceImpl;
import net.zdsoft.chnmaster.service.monitor.MonitorCacheService;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015-7-10 上午11:17:06 $
 */
@Service("monitorCacheService")
public class MonitorCacheServiceImpl extends BaseServiceImpl implements MonitorCacheService {

    @Override
    public void cleanCache() {
        clearCacheAll();
    }

}
