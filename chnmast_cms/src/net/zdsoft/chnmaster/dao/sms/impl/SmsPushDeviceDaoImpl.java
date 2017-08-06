/*
 * @(#)PushDeviceDao.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.sms.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.sms.SmsPushDeviceDao;
import net.zdsoft.chnmaster.dao.sms.rowMapper.SmsPushDeviceRowMapper;
import net.zdsoft.chnmaster.dao.sms.sql.SmsPushDeviceSql;
import net.zdsoft.chnmaster.entity.wangtu.SmsPushDevice;
import net.zdsoft.common.dao.BaseDaoImpl;

/**
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午3:52:42 $
 */
@Repository("smsPushDeviceDao")
public class SmsPushDeviceDaoImpl extends BaseDaoImpl implements SmsPushDeviceDao {

    @Override
    public SmsPushDevice getPushDeviceByPushToken(String pushToken) {
        return (SmsPushDevice) findForObject(SmsPushDeviceSql.get_push_device_by_push_token, new Object[] { pushToken },
                SmsPushDeviceRowMapper.getInstance());
    }

    @Override
    public SmsPushDevice getPushDeviceByDeviceId(String deviceId) {
        return (SmsPushDevice) findForObject(SmsPushDeviceSql.get_push_device_by_device_id, new Object[] { deviceId },
                SmsPushDeviceRowMapper.getInstance());
    }

    @Override
    public int savePushDevice(SmsPushDevice pushDevice) {
        return executeUpdate(SmsPushDeviceSql.save_push_device,
                new Object[] { pushDevice.getUserId(), pushDevice.getAgencyId(), pushDevice.getPushToken(),
                        pushDevice.getClientId(), pushDevice.getDeviceId(), pushDevice.getCreateTime(),
                        pushDevice.getDeviceType().getValue() });
    }

    @Override
    public int delPushDevice(long id) {
        return executeUpdate(SmsPushDeviceSql.del_push_device, new Object[] { id });
    }

    @Override
    public int updatePushStatus(String pushToken, int status) {
        return executeUpdate(SmsPushDeviceSql.update_push_status, new Object[] { status, pushToken, status });
    }

    @Override
    public int deletePushDevice(String pushToken) {
        return executeUpdate(SmsPushDeviceSql.delete_push_device, new Object[] { pushToken });
    }
    
    @Override
    public List<SmsPushDevice> getPushDevies(Long[] userIds) {
        return findForInSQL(SmsPushDeviceSql.get_push_devies, null, userIds, SmsPushDeviceRowMapper.multiRowMapper);
    }

}
