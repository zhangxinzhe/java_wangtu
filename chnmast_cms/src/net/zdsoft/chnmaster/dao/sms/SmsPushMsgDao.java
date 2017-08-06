/*
 * @(#)PushDeviceDao.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.dao.sms;

import java.util.List;

import net.zdsoft.chnmaster.entity.wangtu.SmsPushMsg;
import net.zdsoft.chnmaster.enums.wangtu.SendStatusEnum;
import net.zdsoft.common.entity.PageDto;


/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午3:52:42 $
 */
public interface SmsPushMsgDao {

    /**
     * 获取第一条消息
     *
     * @param userId
     * @return
     */
    public SmsPushMsg getNewestPushMsg(long userId);

    /**
     * 获取消息
     *
     * @param userId
     * @param pageDto
     * @return
     */
    public List<SmsPushMsg> getPushMsgs(long userId, PageDto pageDto);

    /**
     * 消息已接收数量加一
     *
     * @param id
     * @return
     */
    public int addPushMsgReceiveNum(long id);

    /**
     * 消息已读数量加一
     *
     * @param id
     * @return
     */
    public int addPushMsgReadNum(long id);
    
    /**
     * 更新发送状态
     *
     * @param id
     * @param sendStatus
     * @return
     */
    public int updatePushMsgSendStatus(long id, SendStatusEnum sendStatus, int sendNum, String androidTaskId,
            String iosTaskId);
    
    /**
     * 保存消息
     *
     * @param smsPushMsg
     * @return
     */
    public long savePushMsg(SmsPushMsg smsPushMsg);
}
