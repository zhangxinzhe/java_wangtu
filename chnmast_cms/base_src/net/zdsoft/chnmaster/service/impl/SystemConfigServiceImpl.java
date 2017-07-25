package net.zdsoft.chnmaster.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.SystemConfigDao;
import net.zdsoft.chnmaster.service.SystemConfigService;
import net.zdsoft.common.cache.BaseCacheServiceImpl;
import net.zdsoft.common.cache.CacheCall.CacheObjectMapParam;
import net.zdsoft.common.constant.DataTypeConstants;
import net.zdsoft.common.entity.system.SystemConfig;

@Service("systemConfigService")
public class SystemConfigServiceImpl extends BaseCacheServiceImpl implements SystemConfigService {
    @Resource
    private SystemConfigDao systemConfigDao;

    private static final String GET_SYSTEMCONFIG_MAP = "getSystemConfigMap";

    @Override
    public List<SystemConfig> listConfigs(String configName) {
        return systemConfigDao.listConfigs(configName);
    }

    @Override
    public Map<String, SystemConfig> getSystemConfigMap() {
        return this.getObjectMapFromCache(new CacheObjectMapParam<String, SystemConfig>() {
            @Override
            public String getDataType() {
                return DataTypeConstants.SYSTEM_CONFIG;
            }

            @Override
            public Long getAgencyId() {
                return 0l;
            }

            @Override
            public String fetchKey() {
                return getKeyName(getDataType(), getAgencyId(), GET_SYSTEMCONFIG_MAP);
            }

            @Override
            public Map<String, SystemConfig> fetchObjects() {
                return systemConfigDao.getSystemConfigMap();
            }
        });
    }

    @Override
    public int updateConfigInfo(SystemConfig config) {
        int result = systemConfigDao.updateConfigInfo(config);
        // 缓存缓存
        if (result > 0) {
            clearCache(DataTypeConstants.SYSTEM_CONFIG, 0);
        }
        return result;
    }

    @Override
    public int updateConfig(String Code, String value) {
        int result = systemConfigDao.updateConfig(Code, value);
        // 缓存缓存
        if (result > 0) {
            clearCache(DataTypeConstants.SYSTEM_CONFIG, 0);
        }
        return result;
    }

    @Override
    public SystemConfig getSystemConfig(String code) {
        return systemConfigDao.getSystemConfig(code);
    }

    @Override
    public SystemConfig getSystemConfigCache(String code) {
        // 先从缓存中取
        Map<String, SystemConfig> configMap = this.getSystemConfigMap();
        SystemConfig dto = null;
        if (configMap != null && configMap.get(code) != null) {
            dto = configMap.get(code);
        }
        // 取不到时再直接取数据库
        if (dto == null) {
            dto = this.getSystemConfig(code);
        }

        return dto;
    }
}
