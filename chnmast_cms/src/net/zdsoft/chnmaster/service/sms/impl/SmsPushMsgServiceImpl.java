/*
 * @(#)PushDeviceService.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.sms.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.dao.sms.SmsPushMsgDao;
import net.zdsoft.chnmaster.entity.wangtu.SmsPushDevice;
import net.zdsoft.chnmaster.entity.wangtu.SmsPushMsg;
import net.zdsoft.chnmaster.entity.wangtu.SmsPushReceiver;
import net.zdsoft.chnmaster.enums.wangtu.PushMsgTypeEnum;
import net.zdsoft.chnmaster.enums.wangtu.SendStatusEnum;
import net.zdsoft.chnmaster.service.sms.SmsPushDeviceService;
import net.zdsoft.chnmaster.service.sms.SmsPushMsgService;
import net.zdsoft.chnmaster.service.sms.SmsPushReceiverService;
import net.zdsoft.chnmaster.utils.getui.PushClientGeTuiUtil;
import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.entity.user.User;
import net.zdsoft.common.exoperate.ApplicationContextHolder;
import net.zdsoft.common.service.BeanSelfAware;

/**
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午2:46:49 $
 */
@Service("smsPushMsgService")
public class SmsPushMsgServiceImpl implements SmsPushMsgService, BeanSelfAware {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
    @Resource
    private SmsPushMsgDao smsPushMsgDao;
    @Resource
    private SmsPushReceiverService smsPushReceiverService;
    @Resource
    private SmsPushDeviceService smsPushDeviceService;
    
    protected SmsPushMsgService selfBean;

    @Override
    public SmsPushMsg getNewestPushMsg(long userId) {
        return smsPushMsgDao.getNewestPushMsg(userId);
    }

    @Override
    public List<SmsPushMsg> getPushMsgs(long userId, PageDto pageDto) {
        return smsPushMsgDao.getPushMsgs(userId, pageDto);
    }
    
    @Override
    public int updateSendStatusTo(long id, SendStatusEnum sendStatus, int sendNum, String androidTaskId,
            String iosTaskId) {
        smsPushReceiverService.updatePushReceiverSendStatus(id, sendStatus);
        return smsPushMsgDao.updatePushMsgSendStatus(id, sendStatus, sendNum, androidTaskId, iosTaskId);
    }
    
    @Override
    public long savePushMsg(SmsPushMsg smsPushMsg) {
        return smsPushMsgDao.savePushMsg(smsPushMsg);
    }
    
    @Override
    public boolean sendMessage(final List<SmsPushDevice> devices, final SmsPushMsg smsPushMsg) {
        // 个推服务器发送消息
        PushClientGeTuiUtil.getInstance().post(new Runnable() {
            @Override
            public void run() {
                // 发送
                try {
                    PushClientGeTuiUtil.sendMessage(devices, smsPushMsg, new PushClientGeTuiUtil.GeTuiCallBack() {
                        @Override
                        public void result(boolean isSuccess, String androidTaskId, String iosTaskId) {
                            Set<Long> userIds = new HashSet<Long>();
                            for (SmsPushDevice smsPushDevice : devices) {
                                userIds.add(smsPushDevice.getUserId());
                            }

                            ApplicationContext aContext = ApplicationContextHolder.getApplicationContext();
                            if (aContext == null) {
                                log.error("不能获取ApplicationContext对象，请检查配置是否正确！");
                                return;
                            }
                            SmsPushMsgService smsPushMsgService = (SmsPushMsgService) aContext
                                    .getBean("smsPushMsgService");

                            // 更新发送状态
                            if (isSuccess) {
                                smsPushMsgService.updateSendStatusTo(smsPushMsg.getId(), SendStatusEnum.SUCCESS,userIds.size(), androidTaskId, iosTaskId);
                            }
                            else {
                                smsPushMsgService.updateSendStatusTo(smsPushMsg.getId(), SendStatusEnum.FAIL,userIds.size(), androidTaskId, iosTaskId);
                            }
                        }
                    });
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return true;
    }
    
    @Override
    public int sendPushMsg(final SmsPushMsg smsPushMsg, final List<SmsPushDevice> devices,
            final List<SmsPushReceiver> smsPushReceivers) {
        if (CollectionUtils.isEmpty(smsPushReceivers)) {
            return 1;
        }
        smsPushMsg.setNum(smsPushReceivers.size());

        // 添加消息
        final long pushMsgId = selfBean.savePushMsg(smsPushMsg);
        smsPushMsg.setId(pushMsgId);

        // 添加消息id
        for (SmsPushReceiver smsPushReceiver : smsPushReceivers) {
            smsPushReceiver.setPushMsgId(pushMsgId);
        }

        // 添加消息发送的用户
        int end = 0;
        for (int i = 0; i < smsPushReceivers.size(); i += 100) {
            end = i + 100;
            if (end > smsPushReceivers.size()) {
                end = smsPushReceivers.size();
            }
            smsPushReceiverService.batchSavePushUser(smsPushReceivers.subList(i, end));
        }

        return sendGetuiServer(smsPushMsg, devices, smsPushReceivers);
    }
    
    /**
     * 消息发送到个推服务器
     *
     * @param smsPushMsg
     * @param devices
     * @param smsPushReceivers
     * @return
     */
    private int sendGetuiServer(final SmsPushMsg smsPushMsg, final List<SmsPushDevice> devices,
            final List<SmsPushReceiver> smsPushReceivers) {

        // 没有设备直接标记成成功
        if (CollectionUtils.isEmpty(devices)) {
            selfBean.updateSendStatusTo(smsPushMsg.getId(), SendStatusEnum.SUCCESS, 0, null, null);
            return 1;
        }

        // 获取发送数量
        int sendNum = 0;
        for (SmsPushReceiver smsPushReceiver : smsPushReceivers) {
            if (smsPushReceiver.getNoDevice() == 0) {
                sendNum++;
            }
        }
        smsPushMsg.setSendNum(sendNum);

        // 个推服务器发送消息
        PushClientGeTuiUtil.getInstance().post(new Runnable() {

            @Override
            public void run() {
                // 发送
                try {
                    PushClientGeTuiUtil.sendMessage(devices, smsPushMsg, new PushClientGeTuiUtil.GeTuiCallBack() {
                        @Override
                        public void result(boolean isSuccess, String androidTaskId, String iosTaskId) {
                            ApplicationContext aContext = ApplicationContextHolder.getApplicationContext();
                            if (aContext == null) {
                                log.error("不能获取ApplicationContext对象，请检查配置是否正确！");
                                return;
                            }
                            SmsPushMsgService smsPushMsgService = (SmsPushMsgService) aContext
                                    .getBean("smsPushMsgService");

                            // 更新发送状态
                            if (isSuccess) {
                                smsPushMsgService.updateSendStatusTo(smsPushMsg.getId(), SendStatusEnum.SUCCESS,
                                        smsPushMsg.getSendNum(), androidTaskId, iosTaskId);
                            }
                            else {
                                smsPushMsgService.updateSendStatusTo(smsPushMsg.getId(), SendStatusEnum.FAIL,
                                        smsPushMsg.getSendNum(), androidTaskId, iosTaskId);
                            }
                        }
                    });
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return 1;
    }

    @Override
    public void setSelf(Object proxyBean) {
        selfBean = (SmsPushMsgService) proxyBean;
    }
    
    @Override
    public void sendMsg(String title,String msg,PushMsgTypeEnum msgType,BaseUser user,Long[] userIds){
        // 创建推送消息
        SmsPushMsg smsPushMsg = new SmsPushMsg();
        smsPushMsg.setTitle(title);
        smsPushMsg.setContent(msg);
        smsPushMsg.setUserId(user.getId());
        //smsPushMsg.setUserType(getUser().getType());
        smsPushMsg.setRealName(user.getRealName());
        smsPushMsg.setMsgType(msgType);// 目前只有这个功能，所以写死了
        smsPushMsg.setCreateTime(new Date());
        smsPushMsg.setIsSystem(false);
        smsPushMsg.setSendStatus(SendStatusEnum.WAIT);
        
        final List<SmsPushDevice> devices = smsPushDeviceService.getPushDevies(userIds);
        
        Set<Long> userSet = new HashSet<Long>();
        // 有效发送用户
        List<SmsPushReceiver> smsPushReceivers = new ArrayList<SmsPushReceiver>();
        SmsPushReceiver smsPushReceiver = null;
        Date time = new Date();
        for (SmsPushDevice device : devices) {
            // 一个用户可能有多个设备
            if (userSet.contains(device.getUserId())) {
                continue;
            }

            smsPushReceiver = new SmsPushReceiver();
            smsPushReceiver.setUserId(device.getUserId());
            smsPushReceiver.setAgencyId(device.getAgencyId());
            smsPushReceiver.setSendStatus(SendStatusEnum.WAIT);
            smsPushReceiver.setCreateTime(time);
            smsPushReceivers.add(smsPushReceiver);
            userSet.add(device.getUserId());
        }

        // 发送并保存消息
        try {
            sendPushMsg(smsPushMsg, devices, smsPushReceivers);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    
}
