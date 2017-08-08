/*
 * @(#)AttendSong.java    Created on 2016年4月9日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.compete;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.CompeteStage;

/**
 * T_COMPETE_ATTEND_SONG
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年4月9日 下午1:04:04 $
 */
public class AttendSong extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    private long competeId;
    /**
     * 报名表id
     */
    private long attendId;
    /**
     * 赛程
     */
    private CompeteStage stage;
    /**
     * 歌曲名称
     */
    private String songName;
    /**
     * 歌曲作者
     */
    private String songAuthor;
    /**
     * 备注
     */
    private String remark;

    public long getCompeteId() {
        return competeId;
    }

    public void setCompeteId(long competeId) {
        this.competeId = competeId;
    }

    public long getAttendId() {
        return attendId;
    }

    public void setAttendId(long attendId) {
        this.attendId = attendId;
    }

    public CompeteStage getStage() {
        return stage;
    }

    public void setStage(CompeteStage stage) {
        this.stage = stage;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongAuthor() {
        return songAuthor;
    }

    public void setSongAuthor(String songAuthor) {
        this.songAuthor = songAuthor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
