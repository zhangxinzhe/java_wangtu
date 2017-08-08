/*
 * @(#)CompeteAttend.java    Created on 2016年4月11日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.compete;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.AuditStatus;
import net.zdsoft.common.enums.CompeteApplyType;
import net.zdsoft.common.enums.CompeteGroupType;
import net.zdsoft.common.enums.OrderStatus;
import net.zdsoft.common.enums.SexType;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.common.utils.NetstudyFileUtils;

/**
 * t_compete_attend
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年4月11日 上午11:18:07 $
 */
public class CompeteAttend extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    private long competeId;
    /**
     * 用户id
     */
    private long userId;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 性别
     */
    private SexType sex;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 报名表照片路径
     */
    private String photoFile;
    /**
     * 联系方式
     */
    private String mobilePhone;
    /**
     * email
     */
    private String email;
    /**
     * 学校院系
     */
    private String schoolName;
    /**
     * 选手单位
     */
    private String unitName;
    /**
     * 报名形式（0前台报名，1后台报名）
     */
    private CompeteApplyType applyType;
    /**
     * 组别（1教师组民族唱法、2教师组美声唱法 、3学生组民族唱法 、4学生组美声唱法 、5学生组流行唱法）
     */
    private CompeteGroupType groupType;
    /**
     * 参赛码（1001，2001，3001....）（付款成功后生成参赛码）
     */
    private String attendCode;
    /**
     * 创建时间
     */
    private Date creationTime;
    /**
     * 活动金额
     */
    private float payAmount;
    /**
     * 报名支付状态
     */
    private OrderStatus orderStatus;
    /**
     * 视频地址
     */
    private String videoUrl;
    /**
     * 状态（0：未审核，1审核通过，2：审核失败）
     */
    private AuditStatus auditStatus;
    /**
     * 投票数（总票数：网络投票数+加票数）
     */
    private int voteNum;
    /**
     * 加票数【1.4.2.0】
     */
    private int voteAddNum;
    /**
     * 视频播放次数
     */
    private int playNum;
    /**
     * 备注
     */
    private String remark;

    /*************************** 辅助字段 ******************************/
    private String competeName;// 活动名称
    private float competeFee;// 活动费用
    private Date beginTime;// 活动开始时间
    private Date endTime;// 活动结束时间
    private YesNoType isDoing;// 活动是否进行中
    private Date voteBeginTime;// 投票数开始时间
    private Date voteEndTime;// 投票数结束时间

    private String firstSong;// 初赛曲目
    private String secondSong;// 复赛曲目
    private String thirdSong;// 半决赛曲目
    private String fourthSong1;// 决赛曲目1
    private String fourthSong2;// 决赛曲目2
    private String smallPhotoFile;// 参赛图片压缩图路径

    // 导出功能，字符显示
    private String sexStr;
    private String groupTypeStr;
    private String applyTypeStr;
    private String songsStr;
    private String tradeNo;
    private String payTypeStr;

    /*************************** get set ******************************/
    public long getCompeteId() {
        return competeId;
    }

    public void setCompeteId(long competeId) {
        this.competeId = competeId;
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

    public String getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(String photoFile) {
        this.photoFile = photoFile;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public CompeteApplyType getApplyType() {
        return applyType;
    }

    public void setApplyType(CompeteApplyType applyType) {
        this.applyType = applyType;
    }

    public CompeteGroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(CompeteGroupType groupType) {
        this.groupType = groupType;
    }

    public String getAttendCode() {
        return attendCode;
    }

    public void setAttendCode(String attendCode) {
        this.attendCode = attendCode;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public float getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(float payAmount) {
        this.payAmount = payAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public int getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(int voteNum) {
        this.voteNum = voteNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFirstSong() {
        return firstSong;
    }

    public void setFirstSong(String firstSong) {
        this.firstSong = firstSong;
    }

    public String getSecondSong() {
        return secondSong;
    }

    public void setSecondSong(String secondSong) {
        this.secondSong = secondSong;
    }

    public String getThirdSong() {
        return thirdSong;
    }

    public void setThirdSong(String thirdSong) {
        this.thirdSong = thirdSong;
    }

    public String getFourthSong1() {
        return fourthSong1;
    }

    public void setFourthSong1(String fourthSong1) {
        this.fourthSong1 = fourthSong1;
    }

    public String getFourthSong2() {
        return fourthSong2;
    }

    public void setFourthSong2(String fourthSong2) {
        this.fourthSong2 = fourthSong2;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCompeteName() {
        return competeName;
    }

    public void setCompeteName(String competeName) {
        this.competeName = competeName;
    }

    public float getCompeteFee() {
        return competeFee;
    }

    public void setCompeteFee(float competeFee) {
        this.competeFee = competeFee;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public YesNoType getIsDoing() {
        return isDoing;
    }

    public void setIsDoing(YesNoType isDoing) {
        this.isDoing = isDoing;
    }

    public int getPlayNum() {
        return playNum;
    }

    public void setPlayNum(int playNum) {
        this.playNum = playNum;
    }

    public Date getVoteBeginTime() {
        return voteBeginTime;
    }

    public void setVoteBeginTime(Date voteBeginTime) {
        this.voteBeginTime = voteBeginTime;
    }

    public Date getVoteEndTime() {
        return voteEndTime;
    }

    public void setVoteEndTime(Date voteEndTime) {
        this.voteEndTime = voteEndTime;
    }

    public String getSmallPhotoFile() {
        if (StringUtils.isEmpty(photoFile)) {
            return "";
        }
        return NetstudyFileUtils.builSmallFilePath(photoFile, "_small");
    }

    public void setSmallPhotoFile(String smallPhotoFile) {
        this.smallPhotoFile = smallPhotoFile;
    }

    public int getVoteAddNum() {
        return voteAddNum;
    }

    public void setVoteAddNum(int voteAddNum) {
        this.voteAddNum = voteAddNum;
    }

    /**
     * @return Returns the songsStr.
     */
    public String getSongsStr() {
        return songsStr;
    }

    /**
     * @param songsStr
     *            The songsStr to set.
     */
    public void setSongsStr(String songsStr) {
        this.songsStr = songsStr;
    }

    /**
     * @return Returns the sexStr.
     */
    public String getSexStr() {
        return sexStr;
    }

    /**
     * @param sexStr
     *            The sexStr to set.
     */
    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }

    /**
     * @return Returns the groupTypeStr.
     */
    public String getGroupTypeStr() {
        return groupTypeStr;
    }

    /**
     * @param groupTypeStr
     *            The groupTypeStr to set.
     */
    public void setGroupTypeStr(String groupTypeStr) {
        this.groupTypeStr = groupTypeStr;
    }

    /**
     * @return Returns the applyTypeStr.
     */
    public String getApplyTypeStr() {
        return applyTypeStr;
    }

    /**
     * @param applyTypeStr
     *            The applyTypeStr to set.
     */
    public void setApplyTypeStr(String applyTypeStr) {
        this.applyTypeStr = applyTypeStr;
    }

    /**
     * @return Returns the tradeNo.
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * @param tradeNo The tradeNo to set.
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * @return Returns the payTypeStr.
     */
    public String getPayTypeStr() {
        return payTypeStr;
    }

    /**
     * @param payTypeStr The payTypeStr to set.
     */
    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }
}
