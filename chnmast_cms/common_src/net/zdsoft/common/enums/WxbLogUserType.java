package net.zdsoft.common.enums;

/**
 * 登录无限宝身份（0：学员，1：助教，2：主讲,3.教务,4.普通特殊账户）
 *
 * @author hongx
 */
public enum WxbLogUserType {
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
    public static WxbLogUserType get(int value) {
        for (WxbLogUserType type : WxbLogUserType.values()) {
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
