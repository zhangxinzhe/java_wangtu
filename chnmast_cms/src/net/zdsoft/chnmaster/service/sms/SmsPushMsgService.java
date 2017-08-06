/*
 * @(#)PushDeviceService.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.sms;

import java.util.List;

import net.zdsoft.chnmaster.entity.wangtu.SmsPushDevice;
import net.zdsoft.chnmaster.entity.wangtu.SmsPushMsg;
import net.zdsoft.chnmaster.entity.wangtu.SmsPushReceiver;
import net.zdsoft.chnmaster.enums.wangtu.PushMsgTypeEnum;
import net.zdsoft.chnmaster.enums.wangtu.SendStatusEnum;
import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.user.User;


/**
 * 消息推送
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午2:46:49 $
 */
public interface SmsPushMsgService {
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
    public List<net.zdsoft.chnmaster.entity.wangtu.SmsPushMsg> getPushMsgs(long userId, PageDto pageDto);
    
    /**
     * 推送消息
     *
     * @param devices
     * @param smsPushMsg
     * @return
     */
    public boolean sendMessage(List<SmsPushDevice> devices, SmsPushMsg smsPushMsg);
    
    /**
     * 发送消息
     *
     * @param smsPushMsg
     * @param devices
     * @param smsPushReceivers
     * @return
     */
    public int sendPushMsg(SmsPushMsg smsPushMsg, List<SmsPushDevice> devices, List<SmsPushReceiver> smsPushReceivers);
    
    /**
     * 更新发送状态
     *
     * @param id
     * @param sendStatus
     * @return
     */
    public int updateSendStatusTo(long id, SendStatusEnum sendStatus, int sendNum, String androidTaskId,
            String iosTaskId);
    
    /**
     * 保存消息
     *
     * @param smsPushMsg
     * @return 返回主键id
     */
    public long savePushMsg(SmsPushMsg smsPushMsg);
    
    public void sendMsg(String title,String msg,PushMsgTypeEnum msgType,BaseUser user,Long[] userIds);
}
