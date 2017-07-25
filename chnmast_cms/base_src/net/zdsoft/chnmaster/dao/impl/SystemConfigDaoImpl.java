package net.zdsoft.chnmaster.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.SystemConfigDao;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.entity.system.SystemConfig;
import net.zdsoft.common.entity.system.mapper.SystemConfigRowMapper;

@SuppressWarnings("unchecked")
@Repository("systemConfigDao")
public class SystemConfigDaoImpl extends BaseDaoImpl implements SystemConfigDao {
    private static final String list_all_config = "systemConfig.list_all_config";
    private static final String update_config_info = "systemConfig.update_config_info";
    private static final String get_config_bycode = "systemConfig.get_config_bycode";
    private static final String update_config_value_by_code = "systemConfig.update_config_value_by_code";

    @Override
    public List<SystemConfig> listConfigs(String configName) {
        StringBuilder sql = new StringBuilder(getSql(list_all_config));
        List<Object> args = new ArrayList<Object>();
        if (StringUtils.isNotBlank(configName)) {
            sql.append(" WHERE NAME LIKE ?");
            args.add("%" + configName + "%");
        }
        sql.append(" order by order_no");
        return find(sql.toString(), args.toArray(), SystemConfigRowMapper.instance());
    }

    @Override
    public Map<String, SystemConfig> getSystemConfigMap() {
        return findForMap(getSql(list_all_config), SystemConfigRowMapper.instSystemConfigMapRowMapper());
    }

    @Override
    public int updateConfigInfo(SystemConfig config) {
        return executeUpdate(getSql(update_config_info),
                new Object[] { config.getName(), config.getValue(), config.getDescription(), config.getCode() });
    }

    @Override
    public int updateConfig(String code, String value) {
        return executeUpdate(getSql(update_config_value_by_code), new Object[] { value, code });
    }

    @Override
    public SystemConfig getSystemConfig(String code) {
        return (SystemConfig) findForObject(getSql(get_config_bycode), new Object[] { code },
                SystemConfigRowMapper.instance());
    }

}
