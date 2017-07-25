package net.zdsoft.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import net.zdsoft.common.exoperate.ApplicationContextHolder;
import net.zdsoft.common.properties.PropertiesHelper;
import net.zdsoft.keel.util.Validators;

/**
 * 参数配置
 *
 * @author fangb
 *
 */
public class NetstudyConfig {
    private static final Logger log = LoggerFactory.getLogger(NetstudyConfig.class);

    /**
     * 得到系统配置参数
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
            try {
                NetstudyConfigService netstudyConfigService = aContext.getBean(NetstudyConfigService.class);
                value = netstudyConfigService.getParam(key);
            }
            catch (Exception e) {
                log.error("系统未实现【NetstudyConfigService】接口，不能获取系统配置", e);
                return null;
            }
        }
        return value;
    }

    /**
     * 获取系统最大版本号
     *
     * @return
     */
    public static long getVersionId() {
        ApplicationContext aContext = ApplicationContextHolder.getApplicationContext();
        if (aContext == null) {
            return 0;
        }
        try {
            NetstudyConfigService netstudyConfigService = aContext.getBean(NetstudyConfigService.class);
            return netstudyConfigService.getVersionId();
        }
        catch (Exception e) {
            log.error("系统未实现【NetstudyConfigService】接口，不能获取系统配置", e);
            return 0;
        }
    }
}
