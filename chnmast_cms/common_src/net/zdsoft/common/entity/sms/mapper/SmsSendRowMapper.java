/*
 * @(#)SmsSendRowMapper.java    Created on 2013-11-25
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.sms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.sms.SmsSend;
import net.zdsoft.common.enums.ReceiveStatusEnum;
import net.zdsoft.common.enums.SendStatusEnum;
import net.zdsoft.common.enums.SmsType;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2013-11-25 下午02:40:31 $
 */
@SuppressWarnings("rawtypes")
public class SmsSendRowMapper implements RowMapper {

    private static SmsSendRowMapper rowMapper = new SmsSendRowMapper();
    public static SmsSendBasicRowMapprt basicRowMapper = new SmsSendBasicRowMapprt();

    static class SmsSendBasicRowMapprt implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            SmsSend smsSend = new SmsSend();
            smsSend.setId(rs.getLong("ID"));
            smsSend.setSendRealname(rs.getString("send_realname"));
            smsSend.setSendDate(rs.getTimestamp("send_date"));
            smsSend.setContent(rs.getString("content"));
            smsSend.setReceiveUsername(rs.getString("receive_username"));
            smsSend.setReceiveRealname(rs.getString("receive_realname"));
            smsSend.setMobile(rs.getString("mobile"));
            smsSend.setReceiveStatus(ReceiveStatusEnum.get(rs.getInt("receive_status")));
            smsSend.setStatusDesc(rs.getString("status_desc"));

            return smsSend;
        }

    }

    public static SmsSendRowMapper instance() {
        return rowMapper;
    }

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        SmsSend smsSend = new SmsSend();
        smsSend.setId(rs.getLong("ID"));
        smsSend.setSendUserid(rs.getLong("send_userid"));
        smsSend.setSendRealname(rs.getString("send_realname"));
        smsSend.setSendDate(rs.getDate("send_date"));
        smsSend.setContent(rs.getString("content"));
        smsSend.setSmsType(SmsType.get(rs.getInt("sms_type")));
        smsSend.setSendStatus(SendStatusEnum.get(rs.getInt("send_status")));
        return smsSend;
    }

}
