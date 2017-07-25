package net.zdsoft.common.entity.user;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.AuditStatusType;
import net.zdsoft.common.enums.SexType;
import net.zdsoft.common.enums.SourseType;
import net.zdsoft.common.enums.StatusEunm;
import net.zdsoft.common.enums.TeachType;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.common.utils.NetstudyFileUtils;

/**
 * 名师大家
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月27日 下午3:32:46 $
 */
public class UserTeacher extends BaseEntity {

    private static final long serialVersionUID = -2917097378182538000L;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 职称
     */
    private String title;
    /**
     * 身份证号
     */
    private String idCode;
    /**
     * 个人介绍/研究成果及获奖情况
     */
    private String introduction;
    /**
     * 照片路径
     */
    private String photoFile;
    /**
     * 所教学科/主要研究及教学方向
     */
    private String teachSubject;
    /**
     * 名师类型（1专家老师/大家，2专业老师/名师，3音乐导师）【+】
     */
    private TeachType teachType;
    /**
     * 地区号
     */
    private String regionCode;
    /**
     * 是否推荐（首页名师推荐）
     */
    private YesNoType isRecommend = YesNoType.NO;
    /**
     * 推荐日期
     */
    private Date recommendTime;
    /**
     * 排序号
     */
    private int recommendSeq;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;

    /**
     * 民族
     */
    private String nation;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 政治面貌
     */
    private String political;

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 单位地址
     */
    private String address;

    /**
     * 硕士毕业院校
     */
    private String masterSchool;

    /**
     * 博士毕业院校
     */
    private String doctorSchool;

    /**
     * 身份证照片路径
     */
    private String idcardFile;

    /**
     * 手持身份证照片路径
     */
    private String handIdcardFile;

    /**
     * 证件一寸照，证书照片
     */
    private String cardPhotoFile;

    /**
     * 创建来源（0后台创建，1前台申请）【1.4.0.0】
     */
    private SourseType teaSource;

    /**
     * 提交申请日期
     */
    private Date applyDate;
    /**
     * 审核状态（0未提交申请，1审核中，2审核通过，3审核不通过）
     */
    private AuditStatusType auditStatus = AuditStatusType.UNSUBMIT;
    /**
     * 审核日期
     */
    private Date auditDate;
    /**
     * 审核人姓名
     */
    private String auditRealName;
    /**
     * 审核信息
     */
    private String auditMsg;
    /**
     * 是否引导
     */
    private boolean showGuide;

    /**************************** 辅助字段 *************************/
    private String username; // 用户名
    private String password; // 密码
    private StatusEunm isCancel;// 注销状态

    private SexType sex;// 性别
    private String email;// 邮箱
    private String idcardFileOther;// 身份证照片另一面

    private String photoFileName;
    private String idcardFileName;
    private String idcardFileOtherName;
    private String handIdcardFileName;

    private float aveScore;// 整体评分
    private int scorePer;// 好评率:（整体评分/5）*100，求整进1

    /**************************** get、set方法 *************************/

    public String getRealName() {
        return realName;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(String photoFile) {
        this.photoFile = photoFile;
    }

    public String getTeachSubject() {
        return teachSubject;
    }

    public void setTeachSubject(String teachSubject) {
        this.teachSubject = teachSubject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public TeachType getTeachType() {
        return teachType;
    }

    public void setTeachType(TeachType teachType) {
        this.teachType = teachType;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public YesNoType getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(YesNoType isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Date getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Date recommendTime) {
        this.recommendTime = recommendTime;
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

    public String getIdcardFileOther() throws Exception {
        if (StringUtils.isEmpty(idcardFile)) {
            return "";
        }
        String otherPath = "";
        otherPath = NetstudyFileUtils.builSmallFilePath(NetstudyFileUtils.deleteMark(idcardFile), "_other");
        return NetstudyFileUtils.addCurrentTimeMark(otherPath);
    }

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return Returns the recommendSeq.
     */
    public int getRecommendSeq() {
        return recommendSeq;
    }

    /**
     * @param recommendSeq
     *            The recommendSeq to set.
     */
    public void setRecommendSeq(int recommendSeq) {
        this.recommendSeq = recommendSeq;
    }

    /**
     * @return Returns the teaSource.
     */
    public SourseType getTeaSource() {
        return teaSource;
    }

    /**
     * @param teaSource
     *            The teaSource to set.
     */
    public void setTeaSource(SourseType teaSource) {
        this.teaSource = teaSource;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMasterSchool() {
        return masterSchool;
    }

    public void setMasterSchool(String masterSchool) {
        this.masterSchool = masterSchool;
    }

    public String getDoctorSchool() {
        return doctorSchool;
    }

    public void setDoctorSchool(String doctorSchool) {
        this.doctorSchool = doctorSchool;
    }

    public String getIdcardFile() {
        return idcardFile;
    }

    public void setIdcardFile(String idcardFile) {
        this.idcardFile = idcardFile;
    }

    public String getHandIdcardFile() {
        return handIdcardFile;
    }

    public void setHandIdcardFile(String handIdcardFile) {
        this.handIdcardFile = handIdcardFile;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public AuditStatusType getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatusType auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditRealName() {
        return auditRealName;
    }

    public void setAuditRealName(String auditRealName) {
        this.auditRealName = auditRealName;
    }

    public String getAuditMsg() {
        return auditMsg;
    }

    public void setAuditMsg(String auditMsg) {
        this.auditMsg = auditMsg;
    }

    public String getPhotoFileName() throws Exception {
        if (StringUtils.isNotBlank(photoFile)) {
            return NetstudyFileUtils.deleteMark(org.springframework.util.StringUtils.getFilename(photoFile));
        }
        else {
            return "";
        }
    }

    public String getIdcardFileName() throws Exception {
        if (StringUtils.isNotBlank(idcardFile)) {
            return NetstudyFileUtils.deleteMark(org.springframework.util.StringUtils.getFilename(idcardFile));
        }
        else {
            return "";
        }
    }

    public String getIdcardFileOtherName() throws Exception {
        if (StringUtils.isNotBlank(getIdcardFileOther())) {
            return NetstudyFileUtils.deleteMark(org.springframework.util.StringUtils.getFilename(getIdcardFileOther()));
        }
        else {
            return "";
        }
    }

    public String getHandIdcardFileName() throws Exception {
        if (StringUtils.isNotBlank(handIdcardFile)) {
            return NetstudyFileUtils.deleteMark(org.springframework.util.StringUtils.getFilename(handIdcardFile));
        }
        else {
            return "";
        }
    }

    public float getAveScore() {
        return aveScore;
    }

    public void setAveScore(float aveScore) {
        this.aveScore = aveScore;
    }

    public int getScorePer() {
        return scorePer;
    }

    public void setScorePer(int scorePer) {
        this.scorePer = scorePer;
    }

    public boolean isShowGuide() {
        return showGuide;
    }

    public void setShowGuide(boolean showGuide) {
        this.showGuide = showGuide;
    }

    public String getCardPhotoFile() {
        return cardPhotoFile;
    }

    public void setCardPhotoFile(String cardPhotoFile) {
        this.cardPhotoFile = cardPhotoFile;
    }

}
