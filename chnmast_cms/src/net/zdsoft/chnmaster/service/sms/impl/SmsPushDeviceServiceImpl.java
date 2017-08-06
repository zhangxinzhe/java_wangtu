/*
 * @(#)PushDeviceService.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.sms.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.sms.SmsPushDeviceDao;
import net.zdsoft.chnmaster.entity.wangtu.SmsPushDevice;
import net.zdsoft.chnmaster.enums.wangtu.DeviceType;
import net.zdsoft.chnmaster.service.sms.SmsPushDeviceService;


/**
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午2:46:49 $
 */
@Service("smsPushDeviceService")
public class SmsPushDeviceServiceImpl implements SmsPushDeviceService {
    @Resource
    private SmsPushDeviceDao smsPushDeviceDao;

    @Override
    public int updatePushDevice(long userId, long agencyId, String pushToken, String clientId, String deviceId,
            DeviceType deviceType) {
        SmsPushDevice pushDevice = null;
        if (StringUtils.isNotBlank(pushToken)) {
            pushDevice = smsPushDeviceDao.getPushDeviceByPushToken(pushToken);
        }
        // pushDevice = smsPushDeviceDao.getPushDeviceByDeviceId(deviceId);

        if (pushDevice != null) {
            // 设备绑定了其他用户，执行删除重置
            if (pushDevice.getUserId() != userId) {
                smsPushDeviceDao.delPushDevice(pushDevice.getId());
                pushDevice = null;
            }
            // // 可能重装导致pushToken改变
            // else if (!pushDevice.getPushToken().equals(pushToken)) {
            // smsPushDeviceDao.delPushDevice(pushDevice.getId());
            // pushDevice = null;
            // }
            // 不需要更新
            else {
                return 1;
            }
        }

        pushDevice = new SmsPushDevice();
        pushDevice.setUserId(userId);
        pushDevice.setAgencyId(agencyId);
        pushDevice.setPushToken(pushToken);
        pushDevice.setClientId(clientId);
        pushDevice.setDeviceId(deviceId);
        pushDevice.setDeviceType(deviceType);
        pushDevice.setCreateTime(new Date());
        smsPushDeviceDao.savePushDevice(pushDevice);
        return 1;
    }

    @Override
    public int updatePushStatus(String pushToken, int status) {
        if (status != 1 && status != 0) {
            return 0;
        }
        return smsPushDeviceDao.updatePushStatus(pushToken, status);
    }

    @Override
    public SmsPushDevice getPushDeviceByPushToken(String pushToken) {
        return smsPushDeviceDao.getPushDeviceByPushToken(pushToken);
    }

    @Override
    public int deletePushDevice(String pushToken) {
        return smsPushDeviceDao.deletePushDevice(pushToken);
    }
    
    @Override
    public List<SmsPushDevice> getPushDevies(Long[] userIds) {
        return smsPushDeviceDao.getPushDevies(userIds);
    }
}
