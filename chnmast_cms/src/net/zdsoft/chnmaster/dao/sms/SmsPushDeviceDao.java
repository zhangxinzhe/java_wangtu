/*
 * @(#)PushDeviceDao.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.sms;

import java.util.List;

import net.zdsoft.chnmaster.entity.wangtu.SmsPushDevice;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午3:52:42 $
 */
public interface SmsPushDeviceDao {
    /**
     * 获取设备信息
     *
     * @param pushToken
     * @param deviceId
     * @return
     */
    public SmsPushDevice getPushDeviceByPushToken(String pushToken);

    /**
     * 获取设备信息
     *
     * @param pushToken
     * @param deviceId
     * @return
     */
    public SmsPushDevice getPushDeviceByDeviceId(String deviceId);

    /**
     * 添加新设备
     *
     * @param pushDevice
     * @return
     */
    public int savePushDevice(SmsPushDevice pushDevice);

    /**
     * 删除设备
     *
     * @param id
     * @return
     */
    public int delPushDevice(long id);

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
