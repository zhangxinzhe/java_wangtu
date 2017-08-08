/*
 * @(#)CompeteVote.java    Created on 2016年5月6日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.compete;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;

/**
 * t_compete_vote
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年5月6日 下午2:14:57 $
 */
public class CompeteVote extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 活动id
     */
    private long competeId;
    /**
     * 报名id
     */
    private long attendId;
    /**
     * 用户id
     */
    private long userId;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 投票时间
     */
    private Date voteTime;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(Date voteTime) {
        this.voteTime = voteTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
