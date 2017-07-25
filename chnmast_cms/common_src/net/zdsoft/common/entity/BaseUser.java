package net.zdsoft.common.entity;

/**
 * 前、后台用户的通用基类
 *
 * @author xiao zihu
 * @version $Revision: 11466 $, $Date: 2010-08-05 10:43:03 +0800 (星期四, 05 八月 2010) $
 */
public class BaseUser extends BaseEntity {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7642543512951696311L;

    private String userName;// 名字
    private String realName;// 用户真实姓名
    private String password;// 密码
    // 用户类别
    // 前台用户时：1个人用户，2培训基地用户，3名师大家，4助教
    // 后台用户时：1超级管理员，2普通管理员
    private int type;
    // 验证是否本地登录 1本地，0表示对接平台登录
    private int isLocal;

    private boolean isMember;// 是否是会员（0：否，1：是）
    private String avatarFile;// 头像路径
    private String avatarOriginalFile;// 头像原图路径
    private boolean isApp;// 是否是app android用户
    private String hifiApikey;// hifi用户apikey值

    // 前台用户类别码
    public static final int USER_TYPE_PERSONAL = 1;
    public static final int USER_TYPE_AGENCYUSER = 2;
    public static final int USER_TYPE_MASTER = 3;
    public static final int USER_TYPE_ASSISTANT = 4;

    // 后台用户类别码
    public static final int USER_TYPE_SUPER_ADMIN = 1;
    public static final int USER_TYPE_GENERAL_ADMIN = 2;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(int isLocal) {
        this.isLocal = isLocal;
    }

    /**
     *
     * @return Returns the isMember.
     */
    public boolean getIsMember() {
        return isMember;
    }

    /**
     *
     * @param isMember
     *            The isMember to set.
     */
    public void setIsMember(boolean isMember) {
        this.isMember = isMember;
    }

    /**
     * @return Returns the avatarFile.
     */
    public String getAvatarFile() {
        return avatarFile;
    }

    /**
     * @param avatarFile
     *            The avatarFile to set.
     */
    public void setAvatarFile(String avatarFile) {
        this.avatarFile = avatarFile;
    }

    public String getAvatarOriginalFile() {
        return avatarOriginalFile;
    }

    public void setAvatarOriginalFile(String avatarOriginalFile) {
        this.avatarOriginalFile = avatarOriginalFile;
    }

    public boolean isApp() {
        return isApp;
    }

    public void setApp(boolean isApp) {
        this.isApp = isApp;
    }

    /**
     * @return Returns the hifiApikey.
     */
    public String getHifiApikey() {
        return hifiApikey;
    }

    /**
     * @param hifiApikey
     *            The hifiApikey to set.
     */
    public void setHifiApikey(String hifiApikey) {
        this.hifiApikey = hifiApikey;
    }
}
