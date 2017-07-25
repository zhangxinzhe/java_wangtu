package net.zdsoft.common.enums;

/**
 * 抓图类型
 * 
 * @author zhangxz
 * 
 */
public enum ShotType {

    /**
     * 屏幕截图
     */
    SCREEN(1, "屏幕截图"),
    /**
     * 摄像头截图
     */
    CAMERA(2, "摄像头截图");

    private int value;
    private String name;

    private ShotType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public String getNameValue() {
        return this.name;
    }

    /**
     * 根据值得到类型
     * 
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static ShotType get(int value) {
        for (ShotType type : ShotType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }
}
