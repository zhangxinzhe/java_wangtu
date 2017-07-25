package net.zdsoft.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统并发控制常量类
 *
 * @author dongzk
 *
 */
public class ControlConstants {

    /**
     * 并发控制项的indexKey
     */
    public static final String SYSTEM_CONTROL = "chnmaster_systemControl";

    /**
     * 并发控制项
     */
    public static final String CONTROL_IMPORT_USER = "import_user"; // 用户导入（学生、教师）
    public static final String CONTROL_IMPORT_SCHOOL = "import_school"; // 学校导入
    public static final String CONTROL_IMPORT_STUREPORT = "import_stuReport"; // 学校导入
    /**
     * 并发控制项的最大处理时长，单位：分钟
     */
    public static final Map<String, Integer> CONTROL_TIME = new HashMap<String, Integer>();

    static {
        CONTROL_TIME.put(CONTROL_IMPORT_USER, Integer.valueOf(15));
        CONTROL_TIME.put(CONTROL_IMPORT_SCHOOL, Integer.valueOf(15));
        CONTROL_TIME.put(CONTROL_IMPORT_STUREPORT, Integer.valueOf(15));
    }

    /**
     * 并发控制项的名称
     */
    public static final Map<String, String> CONTROL_NAME = new HashMap<String, String>();

    static {
        CONTROL_NAME.put(CONTROL_IMPORT_USER, "学生、教师用户导入");
        CONTROL_NAME.put(CONTROL_IMPORT_SCHOOL, "学校导入");
        CONTROL_NAME.put(CONTROL_IMPORT_STUREPORT, "学员报名课程导入");
    }

}
