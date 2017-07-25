package net.zdsoft.common.enums;

/**
 * 用户注册类型枚举，0.后台维护，1.前台自注册，2.手机端注册，3微课绑定用户
 *
 * @author dongzk
 */
public enum UserRegTypeEnum {
    /**
     * 0.后台维护
     */
    BACK_ADD {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String getNameValue() {
            return BACK_ADD_NAME;
        }
    },

    /**
     * 1.前台自注册（web端注册）
     */
    SELF_REG {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return SELF_REG_NAME;
        }
    },

    /**
     * 2.手机端注册
     */
    APP_REG {
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return APP_REG_NAME;
        }
    },

    /**
     * 3微课绑定用户
     */
    USER_BIND {
        @Override
        public int getValue() {
            return 3;
        }

        @Override
        public String getNameValue() {
            return USER_BIND_NAME;
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
     * 根据值得到是否类型
     *
     * @param value
     * @return 如果没有得到对应的是否类型，返回null
     */
    public static UserRegTypeEnum get(int value) {
        for (UserRegTypeEnum type : UserRegTypeEnum.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return BACK_ADD;
    }

    private static final String BACK_ADD_NAME = "后台维护";
    private static final String SELF_REG_NAME = "前台自注册";
    private static final String APP_REG_NAME = "手机端注册";
    private static final String USER_BIND_NAME = "微课绑定用户";
}
