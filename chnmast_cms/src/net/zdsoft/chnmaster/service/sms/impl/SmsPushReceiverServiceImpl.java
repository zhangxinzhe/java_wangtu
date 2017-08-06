/*
 * @(#)PushDeviceService.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.sms.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.sms.SmsPushMsgDao;
import net.zdsoft.chnmaster.dao.sms.SmsPushReceiverDao;
import net.zdsoft.chnmaster.entity.wangtu.SmsPushReceiver;
import net.zdsoft.chnmaster.enums.wangtu.SendStatusEnum;
import net.zdsoft.chnmaster.service.sms.SmsPushReceiverService;
import net.zdsoft.common.service.BeanSelfAware;


/**
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午2:46:49 $
 */
@Service("smsPushUserService")
public class SmsPushReceiverServiceImpl implements SmsPushReceiverService, BeanSelfAware {
    private static final ExecutorService sendExecutService = Executors.newFixedThreadPool(20);

    @Resource
    private SmsPushReceiverDao smsPushReceiverDao;
    @Resource
    private SmsPushMsgDao smsPushMsgDao;

    private SmsPushReceiverService selfBean;

    @Override
    public int updatePushReceiverRecieved(long msgId, long userId) {
        int i = smsPushReceiverDao.updatePushReceiverRecieved(msgId, userId);
        if (i > 0) {
            smsPushMsgDao.addPushMsgReceiveNum(msgId);
        }
        return i;
    }

    @Override
    public int goToUpdateAllPushReceiversToReaded(final long userId) {
        sendExecutService.submit(new Runnable() {
            @Override
            public void run() {
                List<SmsPushReceiver> receivers = smsPushReceiverDao.getUnreadPushReceiversByUserId(userId);
                if (CollectionUtils.isEmpty(receivers)) {
                    return;
                }

                for (SmsPushReceiver smsPushReceiver : receivers) {
                    selfBean.updatePushReceiverReaded(smsPushReceiver.getPushMsgId(), userId);
                }
            }
        });
        return 1;
    }

    @Override
    public int updatePushUserDelete(long id) {
        return smsPushReceiverDao.updatePushUserDelete(id);
    }

    @Override
    public int updatePushReceiverReaded(long msgId, long userId) {
        int i = smsPushReceiverDao.updatePushReceiverReaded(msgId, userId);
        if (i > 0) {
            smsPushMsgDao.addPushMsgReadNum(msgId);
        }
        return i;
    }

    @Override
    public void setSelf(Object proxyBean) {
        selfBean = (SmsPushReceiverService) proxyBean;
    }
    
    /**
     * 更新用户的发送状态
     *
     * @param msgId
     *            推送消息的id
     * @param sendStatus
     * @return
     */
    @Override
    public int updatePushReceiverSendStatus(long msgId, SendStatusEnum sendStatus) {
        return smsPushReceiverDao.updatePushReceiverSendStatus(msgId, sendStatus);
    }
    
    @Override
    public int batchSavePushUser(List<SmsPushReceiver> smsPushReceivers) {
        return smsPushReceiverDao.batchSavePushReceiver(smsPushReceivers);
    }

}
