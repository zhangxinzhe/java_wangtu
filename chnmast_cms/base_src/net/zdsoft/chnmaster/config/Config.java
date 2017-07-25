package net.zdsoft.chnmaster.config;

import java.util.Map;

import org.springframework.context.ApplicationContext;

import net.zdsoft.chnmaster.service.SystemConfigService;
import net.zdsoft.chnmaster.service.SystemVersionService;
import net.zdsoft.common.entity.system.SystemConfig;
import net.zdsoft.common.exoperate.ApplicationContextHolder;
import net.zdsoft.common.properties.PropertiesHelper;
import net.zdsoft.keel.util.Validators;

/**
 * 系统业务参数配置
 *
 * @author fangb
 *
 */
public class Config {
    /**
     * 得到系统业务配置参数
     *
     * @param key
     * @return 如果不存在，返回null
     */
    public static String getParam(String key) {
        String value = PropertiesHelper.getParam(key);
        if (Validators.isBlank(value)) {
            ApplicationContext aContext = ApplicationContextHolder.getApplicationContext();
            if (aContext == null) {
                return null;
            }
            SystemConfigService systemConfigService = (SystemConfigService) aContext.getBean("systemConfigService");
            Map<String, SystemConfig> configMap = systemConfigService.getSystemConfigMap();
            if (configMap != null && configMap.get(key) != null) {
                value = configMap.get(key).getValue();
            }
        }
        // if (Constants.DOMAIN_CMS.equals(key) || Constants.DOMAIN_TRAIN.equals(key)) {
        // value += ":8680";
        // }
        return value;
    }

    /**
     * 获取最大的版本号ID
     *
     * @return
     */
    public static long getVersionId() {
        ApplicationContext aContext = ApplicationContextHolder.getApplicationContext();
        if (aContext == null) {
            return 0;
        }
        SystemVersionService systemVersionService = (SystemVersionService) aContext.getBean("systemVersionService");
        return systemVersionService.getMaxSystemVersionId();
    }

    /**
     * 从配置文件中获取系统参数
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        return PropertiesHelper.getParam(key);
    }

}
