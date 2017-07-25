package net.zdsoft.common.resource;

import java.util.Map;


 
/**
 * sql资源文件加载类
 * @author shenl
 *
 */
public class SqlResources {
	/**
	 * 资源文件中对应的key,和value
	 */
	private static final Map<String, String> map = ResourcesLoad.load("classpath*:conf/sql/*.properties");
	
	/**
	 * 获取资源文件sql属性
	 * @return
	 */
	public static String getValue(String key){
		return map.get(key);
	}
}