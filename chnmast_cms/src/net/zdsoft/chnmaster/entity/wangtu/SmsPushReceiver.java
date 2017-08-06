/*
 * @(#)PushDevice.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu;

import java.util.Date;

import net.zdsoft.chnmaster.enums.wangtu.SendStatusEnum;
import net.zdsoft.common.entity.BaseEntity;


/**
 * 消息推送用户
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月12日 下午3:08:14 $
 */
public class SmsPushReceiver extends BaseEntity {
    private static final long serialVersionUID = -6616681909199827519L;

    /**
     * 用户id
     */
    private long userId;
    /**
     * 消息推送id
     */
    private long pushMsgId;
    /**
     * 机构id
     */
    private long agencyId;
    /**
     * 发送状态
     */
    private SendStatusEnum sendStatus;
    /**
     * 接收状态（0:未接收,1:已接收)
     */
    private int receiveStatus;
    /**
     * 接收时间
     */
    private Date receiveTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 已读状态 (0:未读,1:已读)
     */
    private int readStatus;
    /**
     * 阅读时间
     */
    private Date readTime;
    /**
     * 没有设备 (0:有,1:无)
     */
    private int noDevice;
    /**
     * 是否删除 (0:未删除,1:已删除)
     */
    private int isDeleted;

    /* ========================附加参数======================= */
    private String msgTitle;
    private String msgContent;
    private String realName;
    private String userName;
    private String schoolName;
    private String className;
    private int enterYear;
    private String section;
    private String noDeviceStr;
    private String receiveStatusStr;
    private String readStatusStr;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPushMsgId() {
        return pushMsgId;
    }

    public void setPushMsgId(long pushMsgId) {
        this.pushMsgId = pushMsgId;
    }

    public long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(long agencyId) {
        this.agencyId = agencyId;
    }

    public SendStatusEnum getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(SendStatusEnum sendStatus) {
        this.sendStatus = sendStatus;
    }

    public int getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(int receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getNoDevice() {
        return noDevice;
    }

    public void setNoDevice(int noDevice) {
        this.noDevice = noDevice;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getEnterYear() {
        return enterYear;
    }

    public void setEnterYear(int enterYear) {
        this.enterYear = enterYear;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getNoDeviceStr() {
        return noDeviceStr;
    }

    public void setNoDeviceStr(String noDeviceStr) {
        this.noDeviceStr = noDeviceStr;
    }

    public String getReceiveStatusStr() {
        return receiveStatusStr;
    }

    public void setReceiveStatusStr(String receiveStatusStr) {
        this.receiveStatusStr = receiveStatusStr;
    }

    public String getReadStatusStr() {
        return readStatusStr;
    }

    public void setReadStatusStr(String readStatusStr) {
        this.readStatusStr = readStatusStr;
    }

}
