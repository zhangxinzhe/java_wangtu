/*
 * @(#)PushDeviceService.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.sms;

import java.util.List;

import net.zdsoft.chnmaster.entity.wangtu.SmsPushDevice;

/**
 * 消息推送设备
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午2:46:49 $
 */
public interface SmsPushDeviceService {
    /**
     * 更新推送设备
     *
     * @param userId
     * @param pushToken
     * @param deviceId
     * @return
     */
    public int updatePushDevice(long userId, long agencyId, String pushToken, String clientId, String deviceId,
            net.zdsoft.chnmaster.enums.wangtu.DeviceType deviceType);

    /**
     * 更新推送状态
     *
     * @param pushToken
     * @param status
     *            0不允许，1允许
     * @return
     */
    public int updatePushStatus(String pushToken, int status);

    /**
     * 获取设备信息
     *
     * @param pushToken
     * @param deviceId
     * @return
     */
    public SmsPushDevice getPushDeviceByPushToken(String pushToken);

    /**
     * 删除推送
     *
     * @param pushToken
     * @return
     */
    public int deletePushDevice(String pushToken);
    
    /**
     * 通过用户id获取设备列表
     *
     * @param userIds
     * @return
     */
    public List<SmsPushDevice> getPushDevies(Long[] userIds);
}
