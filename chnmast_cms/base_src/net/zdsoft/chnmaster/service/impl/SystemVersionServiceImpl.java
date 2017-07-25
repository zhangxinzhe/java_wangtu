/*
 * @(#)SystemVersionServiceImpl.java    Created on 2013-8-9
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.SystemVersionDao;
import net.zdsoft.chnmaster.service.SystemVersionService;
import net.zdsoft.common.cache.BaseCacheServiceImpl;
import net.zdsoft.common.cache.CacheCall.CacheObjectParam;
import net.zdsoft.common.constant.DataTypeConstants;
import net.zdsoft.common.entity.system.SystemVersion;

/**
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2013-8-9 下午5:04:35 $
 */
@Service("systemVersionService")
public class SystemVersionServiceImpl extends BaseCacheServiceImpl implements SystemVersionService {
    @Resource
    private SystemVersionDao systemVersionDao;

    private static final String GET_MAX_SYSTEMVERSION_ID = "getMaxSystemVersionId";
    private static final String GET_MAX_SYSTEMVERSION_NO = "getMaxSystemVersionNo";

    @Override
    public long getMaxSystemVersionId() {
        // 先从缓存中取
        Long versionId = getObjectFromCache(new CacheObjectParam<Long>() {
            @Override
            public String getDataType() {
                return DataTypeConstants.SYSTEM_VERSION;
            }

            @Override
            public Long getAgencyId() {
                return 0l;
            }

            @Override
            public String fetchKey() {
                return getKeyName(getDataType(), getAgencyId(), GET_MAX_SYSTEMVERSION_ID);
            }

            @Override
            public Long fetchObject() {
                return systemVersionDao.getMaxSystemVersionId();
            }
        });

        if (versionId == null) {
            return 0;
        }
        else {
            return versionId.longValue();
        }
    }

    @Override
    public SystemVersion getMaxSystemVersion() {
        return systemVersionDao.getMaxSystemVersion();
    }

    @Override
    public String getMaxSystemVersionNo() {
        String versionNo = null;

        // 先从缓存中取
        versionNo = getObjectFromCache(new CacheObjectParam<String>() {
            @Override
            public String getDataType() {
                return DataTypeConstants.SYSTEM_VERSION;
            }

            @Override
            public Long getAgencyId() {
                return 0l;
            }

            @Override
            public String fetchKey() {
                return getKeyName(getDataType(), getAgencyId(), GET_MAX_SYSTEMVERSION_NO);
            }

            @Override
            public String fetchObject() {
                SystemVersion version = systemVersionDao.getMaxSystemVersion();
                if (version == null) {
                    return "0";
                }

                // 组装版本号格式
                return (version.getVersion() + " build:" + version.getBuild());
            }
        });

        if (StringUtils.isEmpty(versionNo)) {
            return "0";
        }
        else {
            return versionNo;
        }
    }

    @Override
    public int saveSystemVersion(SystemVersion version) {
        // 获取主键
        version.setId(5000);

        int result = systemVersionDao.saveSystemVersion(version);

        // 缓存缓存
        if (result > 0) {
            clearCache(DataTypeConstants.SYSTEM_VERSION, 0);
        }
        return result;
    }

    @Override
    public int updateSystemVersion(SystemVersion version) {
        int result = systemVersionDao.updateSystemVersion(version);
        // 缓存缓存
        if (result > 0) {
            clearCache(DataTypeConstants.SYSTEM_VERSION, 0);
        }
        return result;
    }

    @Override
    public Date getCurDataFromIndex(int index) {
        try {
            return getCurDbDate();
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public Date getCurDbDate() {
        return systemVersionDao.getCurDbDate();
    }

}
