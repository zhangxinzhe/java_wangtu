/*
 * @(#)LoginLogRowMapper.java    Created on 2016年2月2日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.sms.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.zdsoft.chnmaster.entity.wangtu.SmsPushMsg;
import net.zdsoft.chnmaster.enums.wangtu.PushMsgTypeEnum;
import net.zdsoft.chnmaster.enums.wangtu.SendStatusEnum;


/**
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午4:18:10 $
 */
public class SmsPushMsgRowMapper implements RowMapper<SmsPushMsg> {
    private static SmsPushMsgRowMapper rowMapper = new SmsPushMsgRowMapper();

    public static SmsPushMsgRowMapper getInstance() {
        return rowMapper;
    }

    @Override
    public SmsPushMsg mapRow(ResultSet rs, int i) throws SQLException {
        SmsPushMsg smsPushMsg = new SmsPushMsg();
        smsPushMsg.setId(rs.getLong("ID"));
        smsPushMsg.setTitle(rs.getString("TITLE"));
        smsPushMsg.setContent(rs.getString("CONTENT"));
        smsPushMsg.setMsgUrl(rs.getString("MSG_URL"));
        smsPushMsg.setUserId(rs.getLong("USER_ID"));
        smsPushMsg.setUserType(rs.getInt("USER_TYPE"));
        smsPushMsg.setRealName(rs.getString("REAL_NAME"));
        smsPushMsg.setRelativeId(rs.getLong("RELATIVE_ID"));
        smsPushMsg.setRelativeExt(rs.getString("RELATIVE_EXT"));
        smsPushMsg.setMsgType(PushMsgTypeEnum.get(rs.getInt("MSG_TYPE")));
        smsPushMsg.setCreateTime(rs.getTimestamp("CREATE_TIME"));
        smsPushMsg.setSendStatus(SendStatusEnum.get(rs.getInt("SEND_STATUS")));
        smsPushMsg.setNum(rs.getInt("NUM"));
        smsPushMsg.setSendNum(rs.getInt("SEND_NUM"));
        smsPushMsg.setReceiveNum(rs.getInt("RECEIVE_NUM"));
        smsPushMsg.setReadNum(rs.getInt("READ_NUM"));
        smsPushMsg.setIsSystem(rs.getBoolean("IS_SYSTEM"));
        return smsPushMsg;
    }

}
