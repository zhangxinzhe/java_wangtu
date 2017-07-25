package net.zdsoft.common.properties;

import java.util.Map;

import net.zdsoft.common.resource.ResourcesLoad;

/**
 * 配置文件帮助工具(暂时先将路径写死)
 * 
 * @author liyixi
 * 
 */
public class PropertiesHelper {

    private static final String CONFIG_DIR = "conf//business";

    private static Map<String, String> properties = null;

    static {
        properties = ResourcesLoad.load("classpath*:" + CONFIG_DIR + "/**/*.properties");
    }

    /**
     * 获取系统配置参数
     * 
     * @param key
     * @return
     */
    public static String getParam(String key) {
        return properties.get(key);
    }
}
