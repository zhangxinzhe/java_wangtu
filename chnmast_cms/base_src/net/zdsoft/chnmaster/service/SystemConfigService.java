package net.zdsoft.chnmaster.service;

import java.util.List;
import java.util.Map;

import net.zdsoft.common.entity.system.SystemConfig;

public interface SystemConfigService {
    /**
     * 获得所有系统参数列表
     * 
     * @return
     */
    public List<SystemConfig> listConfigs(String configName);

    /**
     * 获得所有系统参数Map（有缓存）
     * 
     * @return
     */
    public Map<String, SystemConfig> getSystemConfigMap();

    /**
     * 修改参数值
     * 
     * @param config
     */
    public int updateConfigInfo(SystemConfig config);

    /**
     * 修改参数值
     * 
     * @param Code
     * @param value
     * @return
     */
    public int updateConfig(String Code, String value);

    /**
     * 获取参数信息
     * 
     * @param code
     * @return
     */
    public SystemConfig getSystemConfig(String code);

    /**
     * 获取参数信息（有缓存）
     * 
     * @param code
     * @return
     */
    public SystemConfig getSystemConfigCache(String code);
}
