/*
 * @(#)CompeteAttendHlj.java    Created on 2016年5月6日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.compete;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.CompeteApplyType;
import net.zdsoft.common.enums.CompeteTeamType;
import net.zdsoft.common.enums.CompeteType;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.common.utils.NetstudyFileUtils;

/**
 * t_compete_attend_hlj
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年5月6日 下午4:34:47 $
 */
public class CompeteAttendHlj extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 活动ID
     */
    private long competeId;
    /**
     * 活动类型（20黄龙奖合唱比赛；21黄龙奖乐队比赛···）
     */
    private CompeteType competeType;
    /**
     * 关联用户
     */
    private long userId;
    /**
     * 团队名称/乐队名称
     */
    private String teamName;
    /**
     * 参赛组别【COMPETE_TYPE==20黄龙奖合唱比赛，其他类型默认0】（1公开合唱组、2专业合唱组）
     */
    private CompeteTeamType teamType;
    /**
     * 乐队类型
     */
    private String bandType;
    /**
     * 组织单位
     */
    private String unitName;
    /**
     * 总负责人
     */
    private String teamLeader;
    /**
     * 成员人数
     */
    private Integer teamNum;
    /**
     * 指挥姓名
     */
    private String majorName;
    /**
     * 指挥人数
     */
    private Integer majorNum;
    /**
     * 钢伴姓名
     */
    private String drumName;
    /**
     * 钢伴人数
     */
    private Integer drunNum;
    /**
     * 行政人数
     */
    private Integer manageNum;
    /**
     * 总人数
     */
    private Integer totalNum;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 联系地址
     */
    private String place;
    /**
     * 报名表照片路径
     */
    private String photoFile;
    /**
     * 团队介绍
     */
    private String teamIntro;
    /**
     * 成员介绍
     */
    private String memberIntro;
    /**
     * 报名形式（0前台报名，1后台报名）
     */
    private CompeteApplyType applyType;
    /**
     * 参赛码（1001，2001，3001....）
     */
    private String attendCode;
    /**
     * 创建时间
     */
    private Date creationTime;
    /**
     * 投票数（总票数：网络投票数+加票数）
     */
    private int voteNum;
    /**
     * 加票数【1.4.2.0】
     */
    private int voteAddNum;
    /**
     * 投票得分（10÷选手投票的最高票数×当前选手的票数=选手投票得分）
     */
    private float voteScore;

    /**
     * 原唱曲目介绍
     */
    private String songIntro;// 原唱歌曲介绍

    /********************* 辅助字段 *************************/
    private String competeName;// 活动名称
    private String competeBatch;// 期号
    private Date beginTime;// 活动开始时间
    private Date endTime;// 活动结束时间
    private YesNoType isDoing;// 活动是否进行中
    private Date voteBeginTime;// 投票数开始时间
    private Date voteEndTime;// 投票数结束时间

    private String firstSong;// 复赛曲目
    private String secondSong;// 决赛曲目

    private String smallPhotoFile;// 参赛图片压缩图路径

    // 导出
    private String teamTypeStr;

    /********************* get、set *************************/
    public long getCompeteId() {
        return competeId;
    }

    public void setCompeteId(long competeId) {
        this.competeId = competeId;
    }

    public CompeteType getCompeteType() {
        return competeType;
    }

    public void setCompeteType(CompeteType competeType) {
        this.competeType = competeType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public CompeteTeamType getTeamType() {
        return teamType;
    }

    public void setTeamType(CompeteTeamType teamType) {
        this.teamType = teamType;
    }

    public String getBandType() {
        return bandType;
    }

    public void setBandType(String bandType) {
        this.bandType = bandType;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public Integer getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(Integer teamNum) {
        this.teamNum = teamNum;
    }

    public Integer getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(Integer majorNum) {
        this.majorNum = majorNum;
    }

    public Integer getDrunNum() {
        return drunNum;
    }

    public void setDrunNum(Integer drunNum) {
        this.drunNum = drunNum;
    }

    public Integer getManageNum() {
        return manageNum;
    }

    public void setManageNum(Integer manageNum) {
        this.manageNum = manageNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(String photoFile) {
        this.photoFile = photoFile;
    }

    public String getTeamIntro() {
        return teamIntro;
    }

    public void setTeamIntro(String teamIntro) {
        this.teamIntro = teamIntro;
    }

    public String getMemberIntro() {
        return memberIntro;
    }

    public void setMemberIntro(String memberIntro) {
        this.memberIntro = memberIntro;
    }

    public CompeteApplyType getApplyType() {
        return applyType;
    }

    public void setApplyType(CompeteApplyType applyType) {
        this.applyType = applyType;
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

    public int getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(int voteNum) {
        this.voteNum = voteNum;
    }

    public float getVoteScore() {
        return voteScore;
    }

    public void setVoteScore(float voteScore) {
        this.voteScore = voteScore;
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

    /**
     * @return Returns the competeName.
     */
    public String getCompeteName() {
        return competeName;
    }

    /**
     * @param competeName
     *            The competeName to set.
     */
    public void setCompeteName(String competeName) {
        this.competeName = competeName;
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
     * @return Returns the isDoing.
     */
    public YesNoType getIsDoing() {
        return isDoing;
    }

    /**
     * @param isDoing
     *            The isDoing to set.
     */
    public void setIsDoing(YesNoType isDoing) {
        this.isDoing = isDoing;
    }

    /**
     * @return Returns the voteBeginTime.
     */
    public Date getVoteBeginTime() {
        return voteBeginTime;
    }

    /**
     * @param voteBeginTime
     *            The voteBeginTime to set.
     */
    public void setVoteBeginTime(Date voteBeginTime) {
        this.voteBeginTime = voteBeginTime;
    }

    /**
     * @return Returns the voteEndTime.
     */
    public Date getVoteEndTime() {
        return voteEndTime;
    }

    /**
     * @param voteEndTime
     *            The voteEndTime to set.
     */
    public void setVoteEndTime(Date voteEndTime) {
        this.voteEndTime = voteEndTime;
    }

    public String getSongIntro() {
        return songIntro;
    }

    public void setSongIntro(String songIntro) {
        this.songIntro = songIntro;
    }

    /**
     * @return Returns the competeBatch.
     */
    public String getCompeteBatch() {
        return competeBatch;
    }

    /**
     * @param competeBatch
     *            The competeBatch to set.
     */
    public void setCompeteBatch(String competeBatch) {
        this.competeBatch = competeBatch;
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

    public String getTeamTypeStr() {
        return teamTypeStr;
    }

    public void setTeamTypeStr(String teamTypeStr) {
        this.teamTypeStr = teamTypeStr;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getDrumName() {
        return drumName;
    }

    public void setDrumName(String drumName) {
        this.drumName = drumName;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

}
