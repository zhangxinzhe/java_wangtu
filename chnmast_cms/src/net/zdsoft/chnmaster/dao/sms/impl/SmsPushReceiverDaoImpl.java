/*
 * @(#)PushDeviceDao.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.sms.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.PreparedStatement;

import net.zdsoft.chnmaster.dao.sms.SmsPushReceiverDao;
import net.zdsoft.chnmaster.dao.sms.rowMapper.SmsPushReceiverRowMapper;
import net.zdsoft.chnmaster.dao.sms.sql.SmsPushReceiverSql;
import net.zdsoft.chnmaster.entity.wangtu.SmsPushReceiver;
import net.zdsoft.chnmaster.enums.wangtu.SendStatusEnum;
import net.zdsoft.common.dao.BaseDaoImpl;


/**
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午3:52:42 $
 */
@Repository("smsPushUserDao")
public class SmsPushReceiverDaoImpl extends BaseDaoImpl implements SmsPushReceiverDao {

    @Override
    public int updatePushReceiverRecieved(long msgId, long userId) {
        return executeUpdate(SmsPushReceiverSql.update_push_user_recieve_status,
                new Object[] { 1, new Date(), msgId, userId, 0 });
    }
    
    @Override
    public int updatePushReceiverSendStatus(long msgId, SendStatusEnum sendStatus) {
        String sql = SmsPushReceiverSql.update_push_receiver_send_status;
        Object[] params = null;

        if (sendStatus == SendStatusEnum.FAIL) {
            sql += " and send_status = ?";
            params = new Object[] { sendStatus.getValue(), msgId, SendStatusEnum.WAIT.getValue() };
        }
        else if (sendStatus == SendStatusEnum.SUCCESS) {
            sql += " and send_status != ?";
            params = new Object[] { sendStatus.getValue(), msgId, sendStatus.getValue() };
        }
        else {
            params = new Object[] { sendStatus.getValue(), msgId };
        }

        return executeUpdate(sql, params);

    }

    @Override
    public int updatePushReceiverReaded(long msgId, long userId) {
        return executeUpdate(SmsPushReceiverSql.update_push_user_read_status,
                new Object[] { 1, new Date(), msgId, userId, 0 });
    }

    @Override
    public int updateAllPushUsersToReaded(long userId) {
        return executeUpdate(SmsPushReceiverSql.update_all_push_users_to_readed,
                new Object[] { 1, new Date(), 0, userId });
    }

    @Override
    public int updatePushUserDelete(long id) {
        return executeUpdate(SmsPushReceiverSql.update_push_user_delete, new Object[] { 1, id, 0 });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SmsPushReceiver> getUnreadPushReceiversByUserId(long userId) {
        return find(SmsPushReceiverSql.get_unread_push_receivers_by_userid, new Object[] { userId, 0 },
                SmsPushReceiverRowMapper.getInstance());
    }
    
    @Override
    public int batchSavePushReceiver(final List<SmsPushReceiver> smsPushReceivers) {
        batchUpdate(SmsPushReceiverSql.save_push_receiver, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(java.sql.PreparedStatement ps, int i) throws SQLException {
				SmsPushReceiver smsPushReceiver = smsPushReceivers.get(i);
                ps.setLong(1, smsPushReceiver.getUserId());
                ps.setLong(2, smsPushReceiver.getPushMsgId());
                ps.setLong(3, smsPushReceiver.getAgencyId());
                ps.setInt(4, smsPushReceiver.getSendStatus().getValue());
                ps.setInt(5, smsPushReceiver.getReadStatus());
                if (smsPushReceiver.getReceiveTime() != null) {
                    ps.setTimestamp(6, new Timestamp(smsPushReceiver.getReceiveTime().getTime()));
                }
                else {
                    ps.setTimestamp(6, null);
                }
                ps.setTimestamp(7, new Timestamp(smsPushReceiver.getCreateTime().getTime()));
                ps.setInt(8, smsPushReceiver.getReadStatus());
                ps.setInt(9, smsPushReceiver.getNoDevice());
                ps.setInt(10, smsPushReceiver.getIsDeleted());
				
			}
			
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return smsPushReceivers.size();
			}
		});
        return smsPushReceivers.size();
    }
}
