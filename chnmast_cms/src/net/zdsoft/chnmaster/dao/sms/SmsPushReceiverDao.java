/*
 * @(#)PushDeviceDao.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.sms;

import java.util.List;

import net.zdsoft.chnmaster.entity.wangtu.SmsPushReceiver;
import net.zdsoft.chnmaster.enums.wangtu.SendStatusEnum;


/**
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午3:52:42 $
 */
public interface SmsPushReceiverDao {
    /**
     * 更新用户的发送状态
     *
     * @param msgId
     *            推送消息的id
     * @param sendStatus
     * @return
     */
    public int updatePushReceiverSendStatus(long msgId, SendStatusEnum sendStatus);
	
    /**
     * 更新消息接收状态
     *
     * @param msgId
     * @param userId
     * @param status
     * @return
     */
    public int updatePushReceiverRecieved(long msgId, long userId);

    /**
     * 更新消息阅读状态
     *
     * @param msgId
     * @param userId
     * @param status
     * @return
     */
    public int updatePushReceiverReaded(long msgId, long userId);

    /**
     * 将所有未读信息更新为已读
     *
     * @param userId
     * @return
     */
    public int updateAllPushUsersToReaded(long userId);

    /**
     * 删除消息
     *
     * @param id
     * @return
     */
    public int updatePushUserDelete(long id);

    /**
     * 获取未读列表
     *
     * @param userId
     * @return
     */
    public List<SmsPushReceiver> getUnreadPushReceiversByUserId(long userId);
    
    /**
     * 批量添加推送用户
     *
     * @param smsPushReceivers
     * @return
     */
    public int batchSavePushReceiver(List<SmsPushReceiver> smsPushReceivers);
   

}
