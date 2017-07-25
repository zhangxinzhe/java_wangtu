/*
 * @(#)SmsSendVerifyCodeRowMapper.java    Created on 2015年5月18日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.sms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.common.entity.sms.SmsSendVerifyCode;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015年5月18日 上午10:35:10 $
 */
public class SmsSendVerifyCodeRowMapper implements RowMapper<SmsSendVerifyCode> {
    public static SmsSendVerifyCodeRowMapper rowMapper = new SmsSendVerifyCodeRowMapper();

    @Override
    public SmsSendVerifyCode mapRow(ResultSet rs, int arg1) throws SQLException {
        SmsSendVerifyCode code = new SmsSendVerifyCode();
        code.setId(rs.getLong("ID"));
        code.setUserId(rs.getLong("USER_ID"));
        code.setVerifyCode(rs.getString("VERIFY_CODE"));
        code.setMainPhone(rs.getString("MAIN_PHONE"));
        code.setSendTime(rs.getTimestamp("SEND_TIME"));
        code.setVerifyTime(rs.getTimestamp("VERIFY_TIME"));
        return code;
    }

}
