package net.zdsoft.chnmaster.dao;

import java.util.List;
import java.util.Map;

import net.zdsoft.common.entity.system.SystemConfig;

public interface SystemConfigDao {
    /**
     * 获得所有系统参数(返回list)
     * 
     * @return
     */
    public List<SystemConfig> listConfigs(String configName);

    /**
     * 获得所有系统参数(返回map key 为 code)
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
}
