/*
 * @(#)PushDevice.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import net.zdsoft.chnmaster.enums.wangtu.PushMsgTypeEnum;
import net.zdsoft.chnmaster.enums.wangtu.SendStatusEnum;
import net.zdsoft.common.entity.BaseEntity;

/**
 * 消息推送内容
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 上午11:44:55 $
 */
public class SmsPushMsg extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 外链
     */
    private String msgUrl;
    /**
     * 推送用户id
     */
    private long userId;
    /**
     * 用户类型
     *
     * 1超级管理员，2平台普通管理员，3机构管理员，4机构普通管理员
     *
     *
     * BaseUser.java
     *
     * public static final int USER_TYPE_SUPER_ADMIN = 1;
     *
     * public static final int USER_TYPE_GENERAL_ADMIN = 2;
     *
     * public static final int USER_TYPE_AGENCY_ADMIN = 3;
     *
     * public static final int USER_TYPE_AGENCY_GENERAL_ADMIN = 4;
     */
    private int userType;

    /**
     * 推送消息用户姓名
     */
    private String realName;

    /**
     * 消息关联表（type=0:课程id,type=10:课程id,type=21:课程id,type=22:课程id,type=30:无）
     */
    private Long relativeId;

    /**
     * 业务扩展字段
     */
    private String relativeExt;

    /**
     * 消息类型
     */
    private PushMsgTypeEnum msgType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 发送状态（0:未发送,1:已发送,2:发送失败)
     */
    private SendStatusEnum sendStatus;

    /**
     * 总数量
     */
    private int num;

    /**
     * 接收数量
     */
    private int receiveNum;

    /**
     * 已读数量
     */
    private int readNum;

    /**
     * 发送数量
     */
    private int sendNum;

    /**
     * 系统自动通知 true：系统自动发送，false：手工发送
     */
    private boolean isSystem;

    /**
     * 发送所在机构
     */
    private long agencyId;

    /**
     * 消息提醒命令
     */
    private String cmdForPush;

    /* ===============附加字段=================== */
    private long pushUserId;
    private int readStatus;
    private Date readTime;
    private String openUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(Long relativeId) {
        this.relativeId = relativeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMsgUrl() {
        return msgUrl;
    }

    public void setMsgUrl(String msgUrl) {
        this.msgUrl = msgUrl;
    }

    public PushMsgTypeEnum getMsgType() {
        return msgType;
    }

    public void setMsgType(PushMsgTypeEnum msgType) {
        this.msgType = msgType;
    }

    public boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }

    public SendStatusEnum getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(SendStatusEnum sendStatus) {
        this.sendStatus = sendStatus;
    }

    public int getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public long getPushUserId() {
        return pushUserId;
    }

    public void setPushUserId(long pushUserId) {
        this.pushUserId = pushUserId;
    }

    public String getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(int receiveNum) {
        this.receiveNum = receiveNum;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public int getSendNum() {
        return sendNum;
    }

    public void setSendNum(int sendNum) {
        this.sendNum = sendNum;
    }

    public long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(long agencyId) {
        this.agencyId = agencyId;
    }

    public String getRelativeExt() {
        return relativeExt;
    }

    public void setRelativeExt(String relativeExt) {
        this.relativeExt = relativeExt;
    }

    public String getCmdForPush() {
        return cmdForPush;
    }

    public void setCmdForPush(String cmdForPush) {
        this.cmdForPush = cmdForPush;
    }

}
