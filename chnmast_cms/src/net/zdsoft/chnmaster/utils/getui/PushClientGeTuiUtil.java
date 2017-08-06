/*
 * @(#)PushClientGeTuiUtil.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.utils.getui;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.chnmaster.entity.wangtu.SmsPushDevice;
import net.zdsoft.chnmaster.entity.wangtu.SmsPushMsg;
import net.zdsoft.chnmaster.enums.wangtu.DeviceType;


/**
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 上午11:41:28 $
 */
public class PushClientGeTuiUtil {
    private static final int POOL_SIZE = 20;
    private final ExecutorService sendExecutService = Executors.newFixedThreadPool(POOL_SIZE);
    private static PushClientGeTuiUtil instance = new PushClientGeTuiUtil();

    public static PushClientGeTuiUtil getInstance() {
        return instance;
    }

    public void post(Runnable handle) {
        sendExecutService.submit(handle);
    }

    /**
     * 推送消息
     *
     * @param devices
     * @param smsPushMsg
     * @return
     */
    public static boolean sendMessage(List<SmsPushDevice> devices, SmsPushMsg smsPushMsg, GeTuiCallBack callBack) {
        boolean isCmdForPush = false;

        // 消息
        JSONObject msgJson = new JSONObject();
        msgJson.put("msgId", smsPushMsg.getId());
        msgJson.put("title", smsPushMsg.getTitle());
        msgJson.put("content", smsPushMsg.getContent());
        if (StringUtils.isBlank(smsPushMsg.getOpenUrl())) {
            smsPushMsg.setOpenUrl("/appSms/pushMsgList.htm");
        }
        msgJson.put("url", smsPushMsg.getOpenUrl());

        // 推送设备分类
        List<String> androidDevices = new ArrayList<String>();
        List<String> iosDevices = new ArrayList<String>();
        List<String> iosPushByTokenDevices = new ArrayList<String>();
        for (SmsPushDevice device : devices) {
            // 该用户不允许推送
            if (device.getPushStatus() == 0) {
                continue;
            }

            if (device.getDeviceType() == DeviceType.ANDROID) {
                androidDevices.add(device.getClientId());
            }
            else {
                // 通过token发送
                if (StringUtils.isBlank(device.getClientId())) {
                    // 老的app不进行静默推送
                    if (!isCmdForPush) {
                        iosPushByTokenDevices.add(device.getPushToken());
                    }
                }
                // 通过client发送
                else {
                    iosDevices.add(device.getClientId());
                }
            }
        }

        String androidTaskId = null;
        String iosTaskId = null;

        // 进行分类
        if (CollectionUtils.isNotEmpty(androidDevices)) {
            androidTaskId = GeTuiApi.pushMsgToAndroid(msgJson, androidDevices);
        }
        if (CollectionUtils.isNotEmpty(iosDevices)) {
            //iosTaskId = GeTuiApi.pushMsgToIOS(msgJson, iosDevices);
        }

        if (callBack != null) {
            callBack.result(true, androidTaskId, iosTaskId);
        }
        return true;
    }

    /**
     * 消息推送结果
     *
     * @author Administrator
     * @version $Revision: 1.0 $, $Date: 2017年6月15日 上午11:43:45 $
     */
    public interface GeTuiCallBack {
        public void result(boolean isSuccess, String androidTaskId, String iosTaskId);
    }
}
