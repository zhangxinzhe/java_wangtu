package net.zdsoft.common.enums;

/**
 * （登录无限宝角色，回调回馈值，说明）<br>
 * 登录无限宝角色（0：学员，1：助教，2：主讲,3.教务,4.普通特殊账户）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年11月17日 上午10:12:40 $
 */
public enum ListenType {
    /** 学员 **/
    STUDENT(0, 2, "学员"), 
    /** 主讲 **/
    MTEACHER(2, 4, "主讲"), 
    /** 助教 **/
    ATEACHER(1, 8, "助教");
    // /** 代理服务器用户 **/
    // PROXY_SERVER_USER(9, 16, "代理服务器用户"),
    // /** 旁听 **/
    // GENERAL(9, 32, "旁听"),
    // /** 教务老师 **/
    // ACADEMIC(3, 64, "教务老师"),
    // /** 答疑老师 **/
    // ANSWER(4, 128, "答疑老师");

    public static ListenType getByWxbListenType(int value) {
        for (ListenType type : ListenType.values()) {
            if (type.getWxbType() == value) {
                return type;
            }
        }
        return null;
    }

    public static ListenType getByLocalListenType(int value) {
        for (ListenType type : ListenType.values()) {
            if (type.getLocalType() == value) {
                return type;
            }
        }
        return null;
    }

    private int localType;
    private int wxbType;
    private String descript;

    private ListenType(int localType, int wxbType, String descript) {
        this.localType = localType;
        this.wxbType = wxbType;
        this.descript = descript;
    }

    /**
     * 获取本地listentype
     *
     * @return
     */
    public int getLocalType() {
        return this.localType;
    }

    /**
     * 获取无限宝对应的listentype
     *
     * @return
     */
    public int getWxbType() {
        return this.wxbType;
    }

    /**
     * 获取描述
     *
     * @return
     */
    public String getDescript() {
        return this.descript;
    }
}
