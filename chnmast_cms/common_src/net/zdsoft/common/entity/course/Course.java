/*
 * @(#)Course.java    Created on 2015年10月27日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.course;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.AuditStatusType;
import net.zdsoft.common.enums.CourseContentType;
import net.zdsoft.common.enums.CourseProgressEunm;
import net.zdsoft.common.enums.CourseStatusEunm;
import net.zdsoft.common.enums.CourseType;
import net.zdsoft.common.enums.SourseType;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.common.utils.NetstudyFileUtils;

/**
 * 课程
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月27日 下午3:07:13 $
 */
public class Course extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程说明
     */
    private String introduction;
    /**
     * 主讲名师ID
     */
    private Long teaId;
    /**
     * 主讲教师姓名
     */
    private String teaRealName;
    /**
     * 助教ID
     */
    private Long assId;
    /**
     * 助教姓名
     */
    private String assRealName;
    /**
     * 绑定个人用户ID，关联发布
     */
    private Long studentId;
    /**
     * 关联的高校ID
     */
    private Long collegeId;
    /**
     * 课程图片路径
     */
    private String pictureFile;
    /**
     * 预售/预定开始时间
     */
    private Date bookingTime;
    /**
     * 售票开始时间
     */
    private Date saleTime;
    /**
     * 开始时间（第一个课次的开始时间，精确到分钟）
     */
    private Date beginTime;
    /**
     * 结束时间（最后一个课次的结束之间，精确到分钟）
     */
    private Date endTime;
    /**
     * （原价）基地课程套价
     */
    private float courseOldcost;
    /**
     * （现价）基地课程套价
     */
    private float courseNowcost;
    /**
     * （现价）基地课程会员套价
     */
    private float courseNowcostVip;
    /**
     * 是否允许使用优惠券购买（0否，1是）【1.5.2.0】
     */
    private YesNoType isUseCoupon;
    /**
     * 是否只允许购买整套课程（不允许课次购买）（0否，1是）【1.5.2.0】
     */
    private YesNoType isBuyAll;
    /**
     * 地点
     */
    private String place;
    /**
     * 内容分类（1基地课程，2音乐会，3.活动专题，4点播视频）
     */
    private CourseContentType contentType;
    /**
     * 课程类型（0其他，11大师班，12普及班，21演唱会，22毕业生音乐会）
     */
    private CourseType courseType;
    /**
     * 无限宝服务器ID
     */
    private Long wxbId;
    /**
     * 状态（0：未发布，1已发布，2：已取消）
     */
    private CourseStatusEunm status;
    /**
     * 进度状态（0项目待定，1预售/预定，2售票中，3演出开始，4结束）
     */
    private CourseProgressEunm progress;
    /**
     * 是否推荐（0：否，1：是）
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
     * 最后修改时间
     */
    private Date modifyTime;
    /**
     * 备注
     */
    private String remark;

    /**
     * 创建来源（0后台创建，1前台申请）【1.4.0.0】
     */
    private SourseType courseSource;
    /**
     * 提交申请日期【1.4.0.0】
     */
    private Date applyDate;
    /**
     * 申请描述
     */
    private String applyInfo;
    /**
     * 审核状态（0未提交申请，1审核中，2审核通过，3审核不通过）【1.4.0.0】
     */
    private AuditStatusType auditStatus;
    /**
     * 审核日期【1.4.0.0】
     */
    private Date auditDate;
    /**
     * 审核人姓名【1.4.0.0】
     */
    private String auditRealName;
    /**
     * 审核信息【1.4.0.0】
     */
    private String auditMsg;

    /**************************** 扩展属性 *************************/

    private String wxbName;// 无限宝名称
    private long priceId;
    private float oldPrice;// 原价
    private float nowPrice;// 现价

    private String teaName;
    private String assName;
    private long remainTime;// 最近课次进入课堂的剩余时间
    private Date latestBeginTime;// 最近课次的开始时间

    private int timeNum;// 课次数
    private CourseTime currentTime;// 最近的课次
    private List<CourseTime> timeList;// 课次列表
    private String singlePriceRange;// 单场次价格区间

    private int orderNum;// 下单数量

    private String tempStr;// 字符串显示前台（临时使用）

    private long verifyCode;// 验证码
    private long playNum;// 总播放数
    private int allEvalNum;// 所有评论数

    private String smallPictureFile;// 课程压缩图片

    private String courseDuration;// 课程时间段

    private boolean hasOnline;// 是否有直播课
    private boolean hasLocale;// 是否有现场课
    private String introductionStr;// 处理后的介绍字段

    /**************************** get、set方法 *************************/
    /**
     * @return Returns the courseName.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName
     *            The courseName to set.
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return Returns the introduction.
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * @param introduction
     *            The introduction to set.
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * @return Returns the pictureFile.
     */
    public String getPictureFile() {
        return pictureFile;
    }

    /**
     * @param pictureFile
     *            The pictureFile to set.
     */
    public void setPictureFile(String pictureFile) {
        this.pictureFile = pictureFile;
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
     * @return Returns the courseType.
     */
    public CourseType getCourseType() {
        return courseType;
    }

    /**
     * @param courseType
     *            The courseType to set.
     */
    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    /**
     * @return Returns the wxbId.
     */
    public Long getWxbId() {
        return wxbId;
    }

    /**
     * @param wxbId
     *            The wxbId to set.
     */
    public void setWxbId(Long wxbId) {
        this.wxbId = wxbId;
    }

    /**
     * @return Returns the status.
     */
    public CourseStatusEunm getStatus() {
        return status;
    }

    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(CourseStatusEunm status) {
        this.status = status;
    }

    /**
     * @return Returns the isRecommend.
     */
    public YesNoType getIsRecommend() {
        return isRecommend;
    }

    /**
     * @param isRecommend
     *            The isRecommend to set.
     */
    public void setIsRecommend(YesNoType isRecommend) {
        this.isRecommend = isRecommend;
    }

    /**
     * @return Returns the recommendTime.
     */
    public Date getRecommendTime() {
        return recommendTime;
    }

    /**
     * @param recommendTime
     *            The recommendTime to set.
     */
    public void setRecommendTime(Date recommendTime) {
        this.recommendTime = recommendTime;
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
     * @return Returns the modifyTime.
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     *            The modifyTime to set.
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * @return Returns the teaId.
     */
    public Long getTeaId() {
        return teaId;
    }

    /**
     * @param teaId
     *            The teaId to set.
     */
    public void setTeaId(Long teaId) {
        this.teaId = teaId;
    }

    /**
     * @return Returns the teaRealName.
     */
    public String getTeaRealName() {
        return teaRealName;
    }

    /**
     * @param teaRealName
     *            The teaRealName to set.
     */
    public void setTeaRealName(String teaRealName) {
        this.teaRealName = teaRealName;
    }

    /**
     * @return Returns the assId.
     */
    public Long getAssId() {
        return assId;
    }

    /**
     * @param assId
     *            The assId to set.
     */
    public void setAssId(Long assId) {
        this.assId = assId;
    }

    /**
     * @return Returns the assRealName.
     */
    public String getAssRealName() {
        return assRealName;
    }

    /**
     * @param assRealName
     *            The assRealName to set.
     */
    public void setAssRealName(String assRealName) {
        this.assRealName = assRealName;
    }

    /**
     * @return Returns the place.
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place
     *            The place to set.
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @return Returns the contentType.
     */
    public CourseContentType getContentType() {
        return contentType;
    }

    /**
     * @param contentType
     *            The contentType to set.
     */
    public void setContentType(CourseContentType contentType) {
        this.contentType = contentType;
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
     * @return Returns the wxbName.
     */
    public String getWxbName() {
        return wxbName;
    }

    /**
     * @param wxbName
     *            The wxbName to set.
     */
    public void setWxbName(String wxbName) {
        this.wxbName = wxbName;
    }

    /**
     * @return Returns the oldPrice.
     */
    public float getOldPrice() {
        return oldPrice;
    }

    /**
     * @param oldPrice
     *            The oldPrice to set.
     */
    public void setOldPrice(float oldPrice) {
        this.oldPrice = oldPrice;
    }

    /**
     * @return Returns the nowPrice.
     */
    public float getNowPrice() {
        return nowPrice;
    }

    /**
     * @param nowPrice
     *            The nowPrice to set.
     */
    public void setNowPrice(float nowPrice) {
        this.nowPrice = nowPrice;
    }

    /**
     * @return Returns the teaName.
     */
    public String getTeaName() {
        return teaName;
    }

    /**
     * @param teaName
     *            The teaName to set.
     */
    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    /**
     * @return Returns the assName.
     */
    public String getAssName() {
        return assName;
    }

    /**
     * @param assName
     *            The assName to set.
     */
    public void setAssName(String assName) {
        this.assName = assName;
    }

    /**
     * @return Returns the progress.
     */
    public CourseProgressEunm getProgress() {
        return progress;
    }

    /**
     * @param progress
     *            The progress to set.
     */
    public void setProgress(CourseProgressEunm progress) {
        this.progress = progress;
    }

    /**
     * @return Returns the bookingTime.
     */
    public Date getBookingTime() {
        if (bookingTime == null) {
            return saleTime;
        }
        return bookingTime;
    }

    /**
     * @param bookingTime
     *            The bookingTime to set.
     */
    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    /**
     * @return Returns the saleTime.
     */
    public Date getSaleTime() {
        return saleTime;
    }

    /**
     * @param saleTime
     *            The saleTime to set.
     */
    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public long getPriceId() {
        return priceId;
    }

    public void setPriceId(long priceId) {
        this.priceId = priceId;
    }

    /**
     * 获取（原价）基地课程套价
     *
     * @return
     */
    public float getCourseOldcost() {
        return courseOldcost;
    }

    /**
     * 设置（原价）基地课程套价
     *
     * @param courseOldcost
     */
    public void setCourseOldcost(float courseOldcost) {
        this.courseOldcost = courseOldcost;
    }

    /**
     * 获取（现价）基地课程套价
     *
     * @return
     */
    public float getCourseNowcost() {
        return courseNowcost;
    }

    /**
     * 设置（现价）基地课程套价
     *
     * @param courseNowcost
     */
    public void setCourseNowcost(float courseNowcost) {
        this.courseNowcost = courseNowcost;
    }

    /**
     * 获取（现价）基地课程会员套价
     *
     * @return
     */
    public float getCourseNowcostVip() {
        return courseNowcostVip;
    }

    /**
     * 设置（现价）基地课程会员套价
     *
     * @param courseNowcostVip
     */
    public void setCourseNowcostVip(float courseNowcostVip) {
        this.courseNowcostVip = courseNowcostVip;
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

    public long getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(long remainTime) {
        this.remainTime = remainTime;
    }

    public Date getLatestBeginTime() {
        return latestBeginTime;
    }

    public void setLatestBeginTime(Date latestBeginTime) {
        this.latestBeginTime = latestBeginTime;
    }

    /**
     * @return Returns the timeNum.
     */
    public int getTimeNum() {
        return timeNum;
    }

    /**
     * @param timeNum
     *            The timeNum to set.
     */
    public void setTimeNum(int timeNum) {
        this.timeNum = timeNum;
    }

    /**
     * @return Returns the currentTime.
     */
    public CourseTime getCurrentTime() {
        return currentTime;
    }

    /**
     * @param currentTime
     *            The currentTime to set.
     */
    public void setCurrentTime(CourseTime currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * @return Returns the timeList.
     */
    public List<CourseTime> getTimeList() {
        return timeList;
    }

    /**
     * @param timeList
     *            The timeList to set.
     */
    public void setTimeList(List<CourseTime> timeList) {
        this.timeList = timeList;
    }

    /**
     * @return Returns the studentId.
     */
    public Long getStudentId() {
        return studentId;
    }

    /**
     * @param studentId
     *            The studentId to set.
     */
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getSinglePriceRange() {
        return singlePriceRange;
    }

    public void setSinglePriceRange(String singlePriceRange) {
        this.singlePriceRange = singlePriceRange;
    }

    /**
     * @return Returns the orderNum.
     */
    public int getOrderNum() {
        return orderNum;
    }

    /**
     * @param orderNum
     *            The orderNum to set.
     */
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * @return Returns the tempStr.
     */
    public String getTempStr() {
        return tempStr;
    }

    /**
     * @param tempStr
     *            The tempStr to set.
     */
    public void setTempStr(String tempStr) {
        this.tempStr = tempStr;
    }

    /**
     * @return Returns the verifyCode.
     */
    public long getVerifyCode() {
        return verifyCode;
    }

    /**
     * @param verifyCode
     *            The verifyCode to set.
     */
    public void setVerifyCode(long verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * @return Returns the courseSource.
     */
    public SourseType getCourseSource() {
        return courseSource;
    }

    /**
     * @param courseSource
     *            The courseSource to set.
     */
    public void setCourseSource(SourseType courseSource) {
        this.courseSource = courseSource;
    }

    /**
     * @return Returns the applyDate.
     */
    public Date getApplyDate() {
        return applyDate;
    }

    /**
     * @param applyDate
     *            The applyDate to set.
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * @return Returns the auditStatus.
     */
    public AuditStatusType getAuditStatus() {
        return auditStatus;
    }

    /**
     * @param auditStatus
     *            The auditStatus to set.
     */
    public void setAuditStatus(AuditStatusType auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @return Returns the auditDate.
     */
    public Date getAuditDate() {
        return auditDate;
    }

    /**
     * @param auditDate
     *            The auditDate to set.
     */
    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    /**
     * @return Returns the auditRealName.
     */
    public String getAuditRealName() {
        return auditRealName;
    }

    /**
     * @param auditRealName
     *            The auditRealName to set.
     */
    public void setAuditRealName(String auditRealName) {
        this.auditRealName = auditRealName;
    }

    /**
     * @return Returns the auditMsg.
     */
    public String getAuditMsg() {
        return auditMsg;
    }

    /**
     * @param auditMsg
     *            The auditMsg to set.
     */
    public void setAuditMsg(String auditMsg) {
        this.auditMsg = auditMsg;
    }

    /**
     * @return Returns the applyInfo.
     */
    public String getApplyInfo() {
        return applyInfo;
    }

    /**
     * @param applyInfo
     *            The applyInfo to set.
     */
    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo;
    }

    /**
     * @return Returns the playNum.
     */
    public long getPlayNum() {
        return playNum;
    }

    /**
     * @param playNum
     *            The playNum to set.
     */
    public void setPlayNum(long playNum) {
        this.playNum = playNum;
    }

    public String getSmallPictureFile() {
        if (StringUtils.isEmpty(pictureFile)) {
            return "";
        }
        return NetstudyFileUtils.builSmallFilePath(pictureFile, "_small");
    }

    public void setSmallPictureFile(String smallPictureFile) {
        this.smallPictureFile = smallPictureFile;
    }

    /**
     * @return Returns the allEvalNum.
     */
    public int getAllEvalNum() {
        return allEvalNum;
    }

    /**
     * @param allEvalNum
     *            The allEvalNum to set.
     */
    public void setAllEvalNum(int allEvalNum) {
        this.allEvalNum = allEvalNum;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public boolean getHasOnline() {
        return hasOnline;
    }

    public void setHasOnline(boolean hasOnline) {
        this.hasOnline = hasOnline;
    }

    public boolean getHasLocale() {
        return hasLocale;
    }

    public void setHasLocale(boolean hasLocale) {
        this.hasLocale = hasLocale;
    }

    /**
     * @return Returns the collegeId.
     */
    public Long getCollegeId() {
        return collegeId;
    }

    /**
     * @param collegeId
     *            The collegeId to set.
     */
    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    /**
     * @return Returns the isBuyAll.
     */
    public YesNoType getIsBuyAll() {
        return isBuyAll;
    }

    /**
     * @param isBuyAll
     *            The isBuyAll to set.
     */
    public void setIsBuyAll(YesNoType isBuyAll) {
        this.isBuyAll = isBuyAll;
    }

    /**
     * @return Returns the isUseCoupon.
     */
    public YesNoType getIsUseCoupon() {
        return isUseCoupon;
    }

    /**
     * @param isUseCoupon
     *            The isUseCoupon to set.
     */
    public void setIsUseCoupon(YesNoType isUseCoupon) {
        this.isUseCoupon = isUseCoupon;
    }

    public String getIntroductionStr() {
        return introductionStr;
    }

    public void setIntroductionStr(String introductionStr) {
        this.introductionStr = introductionStr;
    }

}
