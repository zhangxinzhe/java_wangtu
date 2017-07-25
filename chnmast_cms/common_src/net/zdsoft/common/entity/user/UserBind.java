/*
 * @(#)UserBind.java    Created on 2016年7月14日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.user;

import java.util.Date;

import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.common.enums.PlatType;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年7月14日 下午1:59:42 $
 */
public class UserBind extends BaseUser {
    private static final long serialVersionUID = 1L;

    /**
     * 绑定id/tokenID(微课id/微信id)
     */
    private String bindId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 对接id（传递给第三方平台，与user_id对应）
     */
    private String newUserId;

    /**
     * 平台类型（0微课,1微信,2QQ,3hifi音乐,9其他平台）
     */
    private PlatType platType;

    /**
     * 绑定时间
     */
    private Date bindTime;

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public PlatType getPlatType() {
        return platType;
    }

    public void setPlatType(PlatType platType) {
        this.platType = platType;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getNewUserId() {
        return newUserId;
    }

    public void setNewUserId(String newUserId) {
        this.newUserId = newUserId;
    }

}
