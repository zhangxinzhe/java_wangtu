/* 
 * @(#)SystemAppServiceImpl.java    Created on 2015-4-13
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.system.SystemAppDao;
import net.zdsoft.chnmaster.entity.system.SystemApp;
import net.zdsoft.chnmaster.service.common.BaseServiceImpl;
import net.zdsoft.chnmaster.service.system.SystemAppService;
import net.zdsoft.common.cache.CacheCall.CacheObjectsParam;
import net.zdsoft.common.constant.DataTypeConstants;

/**
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2015-4-13 下午3:39:24 $
 */
@Service("systemAppService")
public class SystemAppServiceImpl extends BaseServiceImpl implements SystemAppService {
    private static final String LIST_USING_SYSTEMAPP = "listUsingSystemApp";

    @Resource
    private SystemAppDao systemAppDao;

    @Override
    public List<SystemApp> listUsingSystemApp() {
        return this.getObjectsFromCache(new CacheObjectsParam<SystemApp>() {

            @Override
            public String getDataType() {
                return DataTypeConstants.SYSTEM_APP;
            }

            @Override
            public Long getAgencyId() {
                return 0l;
            }

            @Override
            public String fetchKey() {
                return getKeyName(getDataType(), getAgencyId(), LIST_USING_SYSTEMAPP);
            }

            @Override
            public List<SystemApp> fetchObjects() {
                return systemAppDao.listUsingSystemApp();
            }
        });
    }

    @Override
    public List<SystemApp> listSystemAppOfUser(long userId) {
        // 根据用户id得到用户能访问的子系统id列表
        List<Long> appIds = systemAppDao.listSystemAppIdsOfUser(userId);
        if (CollectionUtils.isEmpty(appIds)) {
            return null;
        }

        // 从缓存中取所有在用子系统列表
        List<SystemApp> appList = this.listUsingSystemApp();

        List<SystemApp> rtnList = new ArrayList<SystemApp>();
        for (SystemApp app : appList) {
            // 是否用户有权限使用
            if (appIds.contains(Long.valueOf(app.getId()))) {
                rtnList.add(app);
            }
        }

        return rtnList;
    }

}
