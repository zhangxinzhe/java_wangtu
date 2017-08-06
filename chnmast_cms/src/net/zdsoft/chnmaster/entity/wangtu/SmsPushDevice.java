/*
 * @(#)PushDevice.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.wangtu;

import java.util.Date;

import net.zdsoft.chnmaster.enums.wangtu.DeviceType;
import net.zdsoft.common.entity.BaseEntity;


/**
 * 消息推送用户设备
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 上午11:44:55 $
 */
public class SmsPushDevice extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private long userId;
    /**
     * 机构id
     */
    private long agencyId;
    /**
     * 个推推送号（android是clientId，ios是苹果的pushToken）
     */
    private String pushToken;
    /**
     * 个推clientId
     */
    private String clientId;
    /**
     * 设备id
     */
    private String deviceId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 设备类型
     */
    private DeviceType deviceType;
    /**
     * 推送状态:(0：用户关闭推送，1：用户允许推送)
     */
    private int pushStatus;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(long agencyId) {
        this.agencyId = agencyId;
    }

    public int getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(int pushStatus) {
        this.pushStatus = pushStatus;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}
