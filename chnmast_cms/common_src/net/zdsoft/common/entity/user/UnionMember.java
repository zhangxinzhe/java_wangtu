/*
 * @(#)UnionMember.java    Created on 2016年5月16日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * ${user}$
 */
package net.zdsoft.common.entity.user;

import java.io.Serializable;
import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.entity.system.SystemRegion;
import net.zdsoft.common.enums.AuditStatusType;
import net.zdsoft.common.enums.SexType;
import net.zdsoft.common.enums.UnionMemberType;

/**
 * 联合会会员
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年5月16日 下午4:57:23 $
 */
public class UnionMember extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 姓名
     */
    private String realName;
    /**
     * 联合会员类型（1个人教育类，2个人装备类，3团体教育类，4团体教育类）
     */
    private UnionMemberType unionMemberType;
    /**
     * 会员编号
     */
    private String unionCode;

    /**
     * 性别
     */
    private SexType sex;

    /**
     * 民族
     */
    private String nation;
    /**
     * 生日
     */
    private Date birthday;

    /**
     * 学历
     */
    private String degree;
    /**
     * 毕业院校
     */
    private String graduateSchool;
    /**
     * 专业
     */
    private String major;
    /**
     * 教学年资
     */
    private Float teacherYear;
    /**
     * 政治面貌
     */
    private String political;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * 工作部门
     */
    private String workDept;
    /**
     * 工作职务
     */
    private String workDuty;
    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 行政区划
     */
    private String regionCode;

    /**
     * 单位地址
     */
    private String address;
    /**
     * 推荐人或推荐单位
     */
    private String referrer;
    /**
     * 个人介绍及工作履历/机构介绍
     */
    private String intro;

    /**
     * 单位推荐意见
     */
    private String opinion;

    /**
     * 个人/营业执照路径
     */
    private String photoFile;
    /**
     * 推荐表路径
     */
    private String paperFile;
    /**
     * 机构名称
     */
    private String agencyName;
    /**
     * 注册地址
     */
    private String agencyAddress;
    /**
     * 经营年期
     */
    private Float agencyYear;
    /**
     * 注册资金
     */
    private String agencyFund;
    /**
     * 分店数量
     */
    private Integer agencyBranchNum;
    /**
     * 品牌名称
     */
    private String agencyBrand;
    /**
     * 机构类别（1生产/2销售；多选；以','号隔开；如1,2）<br>
     * 使用枚举:UnionMemberAgencyMode
     */
    private String agencyMode;
    /**
     * 优势特色
     */
    private String agencyFeature;
    /**
     * 教师人数（员工数量）
     */
    private Integer agencyEmployeeNum;

    /**
     * 学员人数
     */
    private Integer agencyTraineeNum;
    /**
     * 负责人姓名
     */
    private String agencyPrincipal;
    /**
     * 提交申请日期
     */
    private Date applyDate;
    /**
     * 审核状态（0未提交申请，1审核中，2审核通过，3审核不通过）
     */
    private AuditStatusType unionAuditStatus;
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

    /**************************** 扩展属性 *************************/
    private SystemRegion systemRegion; // 行政区划对象
    private String paperFileName;// 推荐表文件名称
    private String photoFileName;// 营业执照文件名称

    /**************************** get set *************************/
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

    public UnionMemberType getUnionMemberType() {
        return unionMemberType;
    }

    public void setUnionMemberType(UnionMemberType unionMemberType) {
        this.unionMemberType = unionMemberType;
    }

    public String getUnionCode() {
        return unionCode;
    }

    public void setUnionCode(String unionCode) {
        this.unionCode = unionCode;
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Float getTeacherYear() {
        return teacherYear;
    }

    public void setTeacherYear(Float teacherYear) {
        this.teacherYear = teacherYear;
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

    public String getWorkDept() {
        return workDept;
    }

    public void setWorkDept(String workDept) {
        this.workDept = workDept;
    }

    public String getWorkDuty() {
        return workDuty;
    }

    public void setWorkDuty(String workDuty) {
        this.workDuty = workDuty;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(String photoFile) {
        this.photoFile = photoFile;
    }

    public String getPaperFile() {
        return paperFile;
    }

    public void setPaperFile(String paperFile) {
        this.paperFile = paperFile;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyAddress() {
        return agencyAddress;
    }

    public void setAgencyAddress(String agencyAddress) {
        this.agencyAddress = agencyAddress;
    }

    public Float getAgencyYear() {
        return agencyYear;
    }

    public void setAgencyYear(Float agencyYear) {
        this.agencyYear = agencyYear;
    }

    public String getAgencyFund() {
        return agencyFund;
    }

    public void setAgencyFund(String agencyFund) {
        this.agencyFund = agencyFund;
    }

    public Integer getAgencyBranchNum() {
        return agencyBranchNum;
    }

    public void setAgencyBranchNum(Integer agencyBranchNum) {
        this.agencyBranchNum = agencyBranchNum;
    }

    public String getAgencyBrand() {
        return agencyBrand;
    }

    public void setAgencyBrand(String agencyBrand) {
        this.agencyBrand = agencyBrand;
    }

    public String getAgencyFeature() {
        return agencyFeature;
    }

    public void setAgencyFeature(String agencyFeature) {
        this.agencyFeature = agencyFeature;
    }

    public Integer getAgencyEmployeeNum() {
        return agencyEmployeeNum;
    }

    public void setAgencyEmployeeNum(Integer agencyEmployeeNum) {
        this.agencyEmployeeNum = agencyEmployeeNum;
    }

    public Integer getAgencyTraineeNum() {
        return agencyTraineeNum;
    }

    public void setAgencyTraineeNum(Integer agencyTraineeNum) {
        this.agencyTraineeNum = agencyTraineeNum;
    }

    public String getAgencyPrincipal() {
        return agencyPrincipal;
    }

    public void setAgencyPrincipal(String agencyPrincipal) {
        this.agencyPrincipal = agencyPrincipal;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public AuditStatusType getUnionAuditStatus() {
        return unionAuditStatus;
    }

    public void setUnionAuditStatus(AuditStatusType unionAuditStatus) {
        this.unionAuditStatus = unionAuditStatus;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public SystemRegion getSystemRegion() {
        return systemRegion;
    }

    public void setSystemRegion(SystemRegion systemRegion) {
        this.systemRegion = systemRegion;
    }

    /**
     * @return Returns the paperFileName.
     */
    public String getPaperFileName() {
        return paperFileName;
    }

    /**
     * @param paperFileName
     *            The paperFileName to set.
     */
    public void setPaperFileName(String paperFileName) {
        this.paperFileName = paperFileName;
    }

    /**
     * @return Returns the photoFileName.
     */
    public String getPhotoFileName() {
        return photoFileName;
    }

    /**
     * @param photoFileName
     *            The photoFileName to set.
     */
    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    /**
     * @return Returns the referrer.
     */
    public String getReferrer() {
        return referrer;
    }

    /**
     * @param referrer
     *            The referrer to set.
     */
    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    /**
     * @return Returns the agencyMode.
     */
    public String getAgencyMode() {
        return agencyMode;
    }

    /**
     * @param agencyMode
     *            The agencyMode to set.
     */
    public void setAgencyMode(String agencyMode) {
        this.agencyMode = agencyMode;
    }

}
