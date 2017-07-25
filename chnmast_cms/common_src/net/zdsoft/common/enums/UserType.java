package net.zdsoft.common.enums;

/**
 * 用户类型<br>
 * 前台用户类型：用户类型（1个人用户，2培训基地用户，3名师大家，4助教）
 *
 * @author hongx
 *
 */
public enum UserType {
    /**
     * 个人用户
     */
    PERSONAL {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getNameValue() {
            return PERSONAL_NAME;
        }
    },
    /**
     * 培训基地用户
     */
    AGENCYUSER {
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getNameValue() {
            return AGENCYUSER_NAME;
        }
    },
    /**
     * 名师大家
     */
    MASTER {
        @Override
        public int getValue() {
            return 3;
        }

        @Override
        public String getNameValue() {
            return MASTER_NAME;
        }
    },

    /**
     * 助教
     */
    ASSISTANT {
        @Override
        public int getValue() {
            return 4;
        }

        @Override
        public String getNameValue() {
            return ASSISTANT_NAME;
        }
    };

    /**
     * 得到类型的整数值
     */
    public abstract int getValue();

    /**
     * 得到类型名称
     */
    public abstract String getNameValue();

    /**
     * 根据值得到类型
     *
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static UserType get(int value) {
        for (UserType type : UserType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    private static final String PERSONAL_NAME = "个人用户";
    private static final String AGENCYUSER_NAME = "培训基地";
    private static final String MASTER_NAME = "名师大家";
    private static final String ASSISTANT_NAME = "助教";
}
