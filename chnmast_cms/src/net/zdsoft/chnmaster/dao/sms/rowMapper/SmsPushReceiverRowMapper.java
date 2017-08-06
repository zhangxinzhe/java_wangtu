/*
 * @(#)LoginLogRowMapper.java    Created on 2016年2月2日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.sms.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.entity.wangtu.SmsPushReceiver;
import net.zdsoft.chnmaster.enums.wangtu.SendStatusEnum;

/**
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午4:18:10 $
 */
public class SmsPushReceiverRowMapper implements RowMapper<SmsPushReceiver> {
    private static SmsPushReceiverRowMapper rowMapper = new SmsPushReceiverRowMapper();

    public static SmsPushReceiverRowMapper getInstance() {
        return rowMapper;
    }

    @Override
    public SmsPushReceiver mapRow(ResultSet rs, int i) throws SQLException {
        SmsPushReceiver receiver = new SmsPushReceiver();
        receiver.setId(rs.getLong("ID"));
        receiver.setUserId(rs.getLong("USER_ID"));
        receiver.setPushMsgId(rs.getLong("PUSH_MSG_ID"));
        receiver.setAgencyId(rs.getLong("AGENCY_ID"));
        receiver.setSendStatus(SendStatusEnum.get(rs.getInt("SEND_STATUS")));
        receiver.setReceiveStatus(rs.getInt("RECEIVE_STATUS"));
        receiver.setReceiveTime(rs.getTimestamp("RECEIVE_TIME"));
        receiver.setCreateTime(rs.getTimestamp("CREATE_TIME"));
        receiver.setReadStatus(rs.getInt("READ_STATUS"));
        receiver.setReadTime(rs.getTimestamp("READ_TIME"));
        receiver.setNoDevice(rs.getInt("NO_DEVICE"));
        receiver.setIsDeleted(rs.getInt("IS_DELETED"));
        return receiver;
    }

}
