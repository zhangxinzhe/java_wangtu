
package net.zdsoft.common.entity.user;

import java.io.Serializable;
import java.util.Date;

import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.common.enums.SexType;
import net.zdsoft.common.enums.StatusEunm;
import net.zdsoft.common.enums.UserRegTypeEnum;
import net.zdsoft.common.enums.UserType;
import net.zdsoft.common.enums.YesNoType;

/**
 * 用户
 *
 * @author
 * @version $Revision: 1.0 $, $Date: 2015年10月27日 下午3:32:19 $
 */
public class User extends BaseUser implements Serializable {

    private static final long serialVersionUID = 2219586096152384005L;
    /**
     * 绑定QQ
     */
    private String qq;
    /**
     * 绑定EMAIL
     */
    private String email;
    /**
     * 绑定手机
     */
    private String phone;
    /**
     * 性别（1男，2女）
     */
    private SexType sex;
    /**
     * 学校id
     */
    private long schoolId;
    /**
     * 所属学校或单位（暂时不用）
     */
    private String schoolName;
    /**
     * 注册时间
     */
    private Date createTime;
    /**
     * 个人首页
     */
    private String homePage;
    /**
     * 固话
     */
    private String telephone;
    /**
     * 用户类型（1个人用户，2培训基地用户，3名师大家，4助教）
     */
    private UserType userType;
    /**
     * 账号来源（0.后台维护，1.前台自注册，2.手机端注册，3微课绑定用户）
     */
    private UserRegTypeEnum registerType;
    /**
     * 状态是否注销（0.正常，1.注销）
     */
    private StatusEunm isCancel;

    /**
     * 是否联合会会员（0.否，1.是）【1.4.0.0】
     */
    private YesNoType isUnionMember;
    /**
     * 是否音乐导师（0.否，1.是）【1.4.0.0】
     */
    private YesNoType isTutor;
    /**
     * 是否hifi会员
     */
    private YesNoType isHifiMember;
    /**
     * hifi会员到期时间【1.5.0.0】
     */
    private Date hifiMemberDate;
    /**
     * 用户分组类型ID（=T_USER_GROUP_TYPE.ID，默认0表示无分组）【1.4.0.0】
     */
    private long groupTypeId;

    /**
     * 最后一次找回密找回日期（如20130125）
     */
    private String findpwdDate;
    /**
     * 找回密码次数
     */
    private int fundpwdTimes;
    /**
     * 姓名全拼【+】
     */
    private String spellName;
    /**
     * 姓名简拼【+】
     */
    private String shortSpell;
    /**
     * 培训基地ID（非培训基地用户时为0）【+】
     */
    private long agencyId;
    /**
     * 备注【+】
     */
    private String remark;

    /**************************** 扩展属性 *************************/
    private String groupTypeTitle; // 分组类型名称

    private boolean isGuest;// 对接无限宝，是否是游客（0平台用户，1游客）

    /**************************** get、set方法 *************************/
    /**
     * @return Returns the qq.
     */
    public String getQq() {
        return qq;
    }

    /**
     * @param qq
     *            The qq to set.
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Returns the phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            The phone to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return Returns the sex.
     */
    public SexType getSex() {
        return sex;
    }

    /**
     * @param sex
     *            The sex to set.
     */
    public void setSex(SexType sex) {
        this.sex = sex;
    }

    /**
     * @return Returns the schoolName.
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * @param schoolName
     *            The schoolName to set.
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * @return Returns the createTime.
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            The createTime to set.
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return Returns the homePage.
     */
    public String getHomePage() {
        return homePage;
    }

    /**
     * @param homePage
     *            The homePage to set.
     */
    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    /**
     * @return Returns the telephone.
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     *            The telephone to set.
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return Returns the userType.
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * @param userType
     *            The userType to set.
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * @return Returns the registerType.
     */
    public UserRegTypeEnum getRegisterType() {
        return registerType;
    }

    /**
     * @param registerType
     *            The registerType to set.
     */
    public void setRegisterType(UserRegTypeEnum registerType) {
        this.registerType = registerType;
    }

    /**
     * @return Returns the findpwdDate.
     */
    public String getFindpwdDate() {
        return findpwdDate;
    }

    /**
     * @param findpwdDate
     *            The findpwdDate to set.
     */
    public void setFindpwdDate(String findpwdDate) {
        this.findpwdDate = findpwdDate;
    }

    /**
     * @return Returns the fundpwdTimes.
     */
    public int getFundpwdTimes() {
        return fundpwdTimes;
    }

    /**
     * @param fundpwdTimes
     *            The fundpwdTimes to set.
     */
    public void setFundpwdTimes(int fundpwdTimes) {
        this.fundpwdTimes = fundpwdTimes;
    }

    /**
     * @return Returns the isCancel.
     */
    public StatusEunm getIsCancel() {
        return isCancel;
    }

    /**
     * @param isCancel
     *            The isCancel to set.
     */
    public void setIsCancel(StatusEunm isCancel) {
        this.isCancel = isCancel;
    }

    /**
     * @return Returns the spellName.
     */
    public String getSpellName() {
        return spellName;
    }

    /**
     * @param spellName
     *            The spellName to set.
     */
    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    /**
     * @return Returns the shortSpell.
     */
    public String getShortSpell() {
        return shortSpell;
    }

    /**
     * @param shortSpell
     *            The shortSpell to set.
     */
    public void setShortSpell(String shortSpell) {
        this.shortSpell = shortSpell;
    }

    /**
     * @return Returns the agencyId.
     */
    public long getAgencyId() {
        return agencyId;
    }

    /**
     * @param agencyId
     *            The agencyId to set.
     */
    public void setAgencyId(long agencyId) {
        this.agencyId = agencyId;
    }

    /**
     * @return Returns the remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            The remark to set.
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return Returns the schoolId.
     */
    public long getSchoolId() {
        return schoolId;
    }

    /**
     * @param schoolId
     *            The schoolId to set.
     */
    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * @return Returns the isUnionMember.
     */
    public YesNoType getIsUnionMember() {
        return isUnionMember;
    }

    /**
     * @param isUnionMember
     *            The isUnionMember to set.
     */
    public void setIsUnionMember(YesNoType isUnionMember) {
        this.isUnionMember = isUnionMember;
    }

    /**
     * @return Returns the isTutor.
     */
    public YesNoType getIsTutor() {
        return isTutor;
    }

    /**
     * @param isTutor
     *            The isTutor to set.
     */
    public void setIsTutor(YesNoType isTutor) {
        this.isTutor = isTutor;
    }

    /**
     * @return Returns the groupTypeId.
     */
    public long getGroupTypeId() {
        return groupTypeId;
    }

    /**
     * @param groupTypeId
     *            The groupTypeId to set.
     */
    public void setGroupTypeId(long groupTypeId) {
        this.groupTypeId = groupTypeId;
    }

    /**
     * @return Returns the groupTypeTitle.
     */
    public String getGroupTypeTitle() {
        return groupTypeTitle;
    }

    /**
     * @param groupTypeTitle
     *            The groupTypeTitle to set.
     */
    public void setGroupTypeTitle(String groupTypeTitle) {
        this.groupTypeTitle = groupTypeTitle;
    }

    /**
     * @return Returns the isGuest.
     */
    public boolean getIsGuest() {
        return isGuest;
    }

    /**
     * @param isGuest
     *            The isGuest to set.
     */
    public void setIsGuest(boolean isGuest) {
        this.isGuest = isGuest;
    }

    public Date getHifiMemberDate() {
        return hifiMemberDate;
    }

    public void setHifiMemberDate(Date hifiMemberDate) {
        this.hifiMemberDate = hifiMemberDate;
    }

    public YesNoType getIsHifiMember() {
        return isHifiMember;
    }

    public void setIsHifiMember(YesNoType isHifiMember) {
        this.isHifiMember = isHifiMember;
    }

}
