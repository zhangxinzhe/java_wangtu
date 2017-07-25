package net.zdsoft.common.enums;

/**
 * 是否类型<br>
 * 代表着一种boolean类型，联系业务中boolean和数据库的int类型
 * 
 * @author winupon
 * 
 */
public enum YesNoType {
    NO {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public boolean getBooleanValue() {
            return false;
        }

        @Override
        public String getNameValue() {
            return NO_NAME;
        }

        @Override
        public boolean yes() {
            return getBooleanValue();
        }
    },
    YES {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public boolean getBooleanValue() {
            return true;
        }

        @Override
        public String getNameValue() {
            return YES_NAME;
        }

        @Override
        public boolean yes() {
            return getBooleanValue();
        }
    };

    /**
     * 得到类型的整数值
     * 
     * @return
     */
    public abstract int getValue();

    public abstract boolean getBooleanValue();

    public abstract boolean yes();

    public abstract String getNameValue();

    /**
     * 根据值得到是否类型
     * 
     * @param value
     * @return 如果没有得到对应的是否类型，返回null
     */
    public static YesNoType get(int value) {
        for (YesNoType type : YesNoType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }

    private static final String NO_NAME = "否";
    private static final String YES_NAME = "是";
}
