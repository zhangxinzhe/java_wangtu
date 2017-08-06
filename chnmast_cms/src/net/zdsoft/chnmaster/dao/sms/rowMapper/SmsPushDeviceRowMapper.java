/*
 * @(#)LoginLogRowMapper.java    Created on 2016年2月2日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.sms.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.entity.wangtu.SmsPushDevice;
import net.zdsoft.chnmaster.enums.wangtu.DeviceType;
import net.zdsoft.keel.dao.MultiRowMapper;

/**
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午4:18:10 $
 */
public class SmsPushDeviceRowMapper implements RowMapper<SmsPushDevice> {
    private static SmsPushDeviceRowMapper rowMapper = new SmsPushDeviceRowMapper();
    public static PushDeviceMultiRowMapper multiRowMapper = new PushDeviceMultiRowMapper();

    static class PushDeviceMultiRowMapper implements MultiRowMapper<SmsPushDevice> {
        @Override
        public SmsPushDevice mapRow(ResultSet rs, int i) throws SQLException {
            return getInstance().mapRow(rs, i);
        }

    }

    public static SmsPushDeviceRowMapper getInstance() {
        return rowMapper;
    }

    @Override
    public SmsPushDevice mapRow(ResultSet rs, int i) throws SQLException {
        SmsPushDevice push = new SmsPushDevice();
        push = new SmsPushDevice();
        push.setId(rs.getLong("ID"));
        push.setUserId(rs.getLong("USER_ID"));
        push.setAgencyId(rs.getLong("AGENCY_ID"));
        push.setPushToken(rs.getString("PUSH_TOKEN"));
        push.setClientId(rs.getString("CLIENT_ID"));
        push.setDeviceId(rs.getString("DEVICE_ID"));
        push.setDeviceType(DeviceType.get(rs.getInt("DEVICE_TYPE")));
        push.setCreateTime(rs.getTimestamp("CREATE_TIME"));
        push.setPushStatus(rs.getInt("PUSH_STATUS"));
        return push;
    }

}
