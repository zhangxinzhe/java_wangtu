package net.zdsoft.common.entity.wxb;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.CourseContentType;
import net.zdsoft.common.enums.WxbLogUserType;

/**
 * 无限宝登录退出日志（无限宝回传）
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月27日 下午3:31:15 $
 */
public class WxbLog extends BaseEntity {

    private static final long serialVersionUID = 5249234787830570738L;
    /**
     * 服务器id
     */
    private long wxbId;
    /**
     * 课程id
     */
    private long courseId;
    /**
     * 用户id
     */
    private long userId;
    /**
     * 用户姓名
     */
    private String realName;
    /**
     * 登录无限宝身份（1个人用户，2培训基地用户，3名师大家，4助教）
     */
    private WxbLogUserType userType;
    /**
     * 登录时间
     */
    private Date loginTime;
    /**
     * 退出时间
     */
    private Date logoutTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 课次
     */
    private int seq;
    /**
     * 登录标识（无限宝服务器产生的guid值）
     */
    private String loginflag;
    /**
     * 不认真总时长
     */
    private int noSeriousTime;

    //////////////// 非数据库字段 //////////////////

    private CourseContentType courseContentType;// 课程、音乐会
    private String courseName;
    private String userName;
    private float countHour;// 总学习时长
    private String courseTimeName; // 课次信息 （课次序号+课程开始时间~课程结束时间）
    private Date beginTime; // 课次开始时间
    private Date endTime; // 课次结束时间

    public long getWxbId() {
        return wxbId;
    }

    public void setWxbId(long wxbId) {
        this.wxbId = wxbId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getLoginflag() {
        return loginflag;
    }

    public void setLoginflag(String loginflag) {
        this.loginflag = loginflag;
    }

    public int getNoSeriousTime() {
        return noSeriousTime;
    }

    public void setNoSeriousTime(int noSeriousTime) {
        this.noSeriousTime = noSeriousTime;
    }

    public WxbLogUserType getUserType() {
        return userType;
    }

    public void setUserType(WxbLogUserType userType) {
        this.userType = userType;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getCountHour() {
        return countHour;
    }

    public void setCountHour(float countHour) {
        this.countHour = countHour;
    }

    /**
     * @return Returns the courseTimeName.
     */
    public String getCourseTimeName() {
        return courseTimeName;
    }

    /**
     * @param courseTimeName
     *            The courseTimeName to set.
     */
    public void setCourseTimeName(String courseTimeName) {
        this.courseTimeName = courseTimeName;
    }

    /**
     * @return Returns the beginTime.
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime
     *            The beginTime to set.
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return Returns the endTime.
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            The endTime to set.
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return Returns the courseContentType.
     */
    public CourseContentType getCourseContentType() {
        return courseContentType;
    }

    /**
     * @param courseContentType The courseContentType to set.
     */
    public void setCourseContentType(CourseContentType courseContentType) {
        this.courseContentType = courseContentType;
    }

}
