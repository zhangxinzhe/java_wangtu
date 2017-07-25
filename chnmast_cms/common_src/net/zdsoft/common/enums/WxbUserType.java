package net.zdsoft.common.enums;

/**
 * 培训用户类型(无限宝用户类型)
 * 
 * @author hejh
 * 
 */
public enum WxbUserType {
    /**
     * 学员
     */
    STUDENT {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getNameValue() {
            return STUDENT_NAME;
        }
    },
    /**
     * 助教
     */
    ATEACHER {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return ATEACHER_NAME;
        }
    },
    /**
     * 主讲
     */
    MTEACHER {
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return MTEACHER_NAME;
        }
    },
    /**
     * 教务
     */
    VIPTEACHER {
        @Override
        public int getValue() {
            return 3;
        }

        @Override
        public String getNameValue() {
            return VIPTEACHER_NAME;
        }
    },
    /**
     * 普通特殊账户
     */
    VIPUSER {
        @Override
        public int getValue() {
            return 4;
        }

        @Override
        public String getNameValue() {
            return VIPUSER_NAME;
        }
    };

    /**
     * 得到类型的整数值
     * 
     * @return
     */
    public abstract int getValue();

    public abstract String getNameValue();

    /**
     * 根据值得到类型
     * 
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static WxbUserType get(int value) {
        for (WxbUserType type : WxbUserType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }

    private static final String STUDENT_NAME = "学员";
    private static final String ATEACHER_NAME = "助教";
    private static final String MTEACHER_NAME = "主讲";
    private static final String VIPTEACHER_NAME = "教务";
    private static final String VIPUSER_NAME = "特殊账户";
}
