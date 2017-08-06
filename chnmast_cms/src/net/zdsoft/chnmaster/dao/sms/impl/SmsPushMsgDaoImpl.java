/*
 * @(#)PushDeviceDao.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.sms.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.dao.sms.SmsPushMsgDao;
import net.zdsoft.chnmaster.dao.sms.rowMapper.SmsPushMsgDaoRowMapper;
import net.zdsoft.chnmaster.dao.sms.sql.SmsPushMsgSql;
import net.zdsoft.chnmaster.entity.wangtu.SmsPushMsg;
import net.zdsoft.chnmaster.enums.wangtu.SendStatusEnum;
import net.zdsoft.common.dao.BaseDaoImpl;
import net.zdsoft.common.entity.PageDto;

/**
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午3:52:42 $
 */
@Repository("smsPushMsgDao")
public class SmsPushMsgDaoImpl extends BaseDaoImpl implements SmsPushMsgDao {
    @Override
    public SmsPushMsg getNewestPushMsg(long userId) {
        return (SmsPushMsg) findForObject(SmsPushMsgSql.get_first_unread_push_msg, new Object[] { userId },
                SmsPushMsgDaoRowMapper.rowMapper);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SmsPushMsg> getPushMsgs(long userId, PageDto pageDto) {
        if (pageDto == null) {
            return find(SmsPushMsgSql.get_push_msgs, new Object[] { userId }, SmsPushMsgDaoRowMapper.rowMapper);
        }

        return findForPage(SmsPushMsgSql.get_push_msgs, new Object[] { userId }, SmsPushMsgDaoRowMapper.rowMapper,
                pageDto);
    }

    @Override
    public int addPushMsgReceiveNum(long id) {
        return executeUpdate(SmsPushMsgSql.add_push_msg_receive_num, new Object[] { id });
    }

    @Override
    public int addPushMsgReadNum(long id) {
        return executeUpdate(SmsPushMsgSql.add_push_msg_read_num, new Object[] { id });
    }
    
    @Override
    public int updatePushMsgSendStatus(long id, SendStatusEnum sendStatus, int sendNum, String androidTaskId,
            String iosTaskId) {
        String sql = SmsPushMsgSql.update_push_msg_send_status;
        Object[] params = null;

        if (sendStatus == SendStatusEnum.FAIL) {
            sql += " AND SEND_STATUS = ?";
            params = new Object[] { sendStatus.getValue(), sendNum, androidTaskId, iosTaskId, id,
                    SendStatusEnum.WAIT.getValue() };
        }
        else if (sendStatus == SendStatusEnum.SUCCESS) {
            sql += " AND SEND_STATUS != ?";
            params = new Object[] { sendStatus.getValue(), sendNum, androidTaskId, iosTaskId, id,
                    sendStatus.getValue() };
        }
        else {
            params = new Object[] { sendStatus.getValue(), sendNum, androidTaskId, iosTaskId, id };
        }

        return executeUpdate(sql, params);
    }
    
    @Override
    public long savePushMsg(SmsPushMsg smsPushMsg) {
        return saveEntityForKey(SmsPushMsgSql.save_push_msg,
                new Object[] { smsPushMsg.getTitle(), smsPushMsg.getContent(), smsPushMsg.getMsgUrl(),
                        smsPushMsg.getUserId(), smsPushMsg.getUserType(), smsPushMsg.getRealName(),
                        smsPushMsg.getRelativeId(), smsPushMsg.getMsgType().getValue(), smsPushMsg.getCreateTime(),
                        smsPushMsg.getSendStatus().getValue(), smsPushMsg.getNum(), smsPushMsg.getIsSystem(),
                        smsPushMsg.getAgencyId(), smsPushMsg.getRelativeExt() });
    }
}
