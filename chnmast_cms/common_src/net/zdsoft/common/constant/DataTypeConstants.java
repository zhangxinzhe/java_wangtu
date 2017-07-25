package net.zdsoft.common.constant;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据类型，如：课程数据，学员数据，机构参数数据，机构栏目数据。<br>
 * 用于缓存中区别不同的数据类型
 *
 * @author dongzk
 *
 */
public class DataTypeConstants {
    /** 机构-无限宝配置 **/
    public static final String SYSTEM_WXB = "systemWxb";
    public static final String SYSTEM_WXB_NAME = "系统-无限宝配置";

    /**
     * 系统-系统参数
     */
    public static final String SYSTEM_CONFIG = "chnmaster_systemConfig";
    public static final String SYSTEM_CONFIG_NAME = "系统-系统参数";
    /**
     * 系统-系统模块
     */
    public static final String SYSTEM_MODULE = "chnmaster_systemModule";
    public static final String SYSTEM_MODULE_NAME = "系统-系统模块";
    /**
     * 系统-子系统
     */
    public static final String SYSTEM_APP = "chnmaster_systemApp";
    public static final String SYSTEM_APP_NAME = "系统-子系统";
    /**
     * 系统-系统版本号
     */
    public static final String SYSTEM_VERSION = "chnmaster_systemVersion";
    public static final String SYSTEM_VERSION_NAME = "系统-系统版本号";
    /**
     * 系统-系统行政号
     */
    public static final String SYSTEM_REGION = "chnmaster_systemRegion";
    public static final String SYSTEM_REGION_NAME = "系统-行政区划";

    /**
     * 二维码
     */
    public static final String INDEX_QUICK_RESPONSE = "index_quickResponse";
    public static final String INDEX_QUICK_RESPONSE_NAME = "二维码";

    /**
     * 页脚
     */
    public static final String INDEX_FOOTER = "index_footer";
    public static final String INDEX_FOOTER_NAME = "页脚";

    /**
     * 获取所有缓存的数据类型
     *
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    public static List<DataType> getAllDataTypeList()
            throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException {
        List<DataType> lists = new ArrayList<DataType>();
        // 得到目标类的所有的字段列表
        Class<DataTypeConstants> clazz = DataTypeConstants.class;
        Field filed[] = clazz.getDeclaredFields();
        // 循环读取所有字段
        DataType dataType = null;
        for (int i = 0; i < filed.length; i++) {
            Field f = filed[i];
            if (f.getName().lastIndexOf("_NAME") == -1) {
                dataType = new DataType();
                dataType.setValue(f.get(clazz).toString());
                dataType.setName(clazz.getField(f.getName() + "_NAME").get(clazz).toString());
                lists.add(dataType);
            }
        }
        return lists;
    }

    // 重新组装DataTypeConstants数据
    public static class DataType {
        public String value;// 参数值
        public String name;// 参数名称

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
