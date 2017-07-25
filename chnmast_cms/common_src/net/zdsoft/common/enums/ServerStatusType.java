package net.zdsoft.common.enums;

/**
 * 服务器状态枚举
 * 
 * @author xiezb
 * 
 */
public enum ServerStatusType {
    /**
     * 启用
     */
    START {
        @Override
        public String getNameValue() {
            return START_NAME;
        }

        @Override
        public int getValue() {
            return 1;
        }
    },
    /**
     * 禁用
     */
    STOP {
        @Override
        public String getNameValue() {
            return STOP_NAME;
        }

        @Override
        public int getValue() {
            return 0;
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
    public static ServerStatusType get(int value) {
        for (ServerStatusType type : ServerStatusType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }

    private static final String START_NAME = "启用";
    private static final String STOP_NAME = "停用";
}
