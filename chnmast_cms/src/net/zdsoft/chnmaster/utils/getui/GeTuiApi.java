/*
 * @(#)PushClientGeTuiUtil.java    Created on 2016年8月10日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.utils.getui;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;

import net.zdsoft.chnmaster.config.Config;


/**
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 上午11:41:28 $
 */
public class GeTuiApi {
    private final static Logger loger = LoggerFactory.getLogger(GeTuiApi.class);

    // 定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String android_appId = Config.getParam("push.android.appId");
    private static String android_appKey = Config.getParam("push.android.appKey");
    private static String android_masterSecret = Config.getParam("push.android.masterSecret");
    private static String android_url = Config.getParam("push.android.url");

    /**
     * 推送消息
     *
     * @param msgJson
     * @param clientIds
     */
    public static String pushMsgToAndroid(JSONObject msgJson, List<String> clientIds) {
        IGtPush push = new IGtPush(android_url, android_appKey, android_masterSecret);

        NotificationTemplate template =  getNotificationTemplate(msgJson, android_appId, android_appKey);

        return pushMsg(push, template, android_appId, clientIds);
    }

    /**
     * 发送消息（ios和android分开发送，是因为IOS在测试环境和正式环境使用不同证书的问题，而android不用）
     */
    private static String pushMsg(IGtPush push, NotificationTemplate template, String appId, List<String> clientIds) {
        ListMessage message = new ListMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(2 * 24 * 1000 * 3600);

        List<Target> targets = new ArrayList<Target>();
        for (String clientId : clientIds) {
            Target target1 = new Target();
            target1.setAppId(appId);
            target1.setClientId(clientId);
            // target1.setAlias(push.getUserId());
            targets.add(target1);
        }

        // 获取taskID
        String taskId = push.getContentId(message);
        // 使用taskID对目标进行推送
        int end = 0;
        for (int i = 0; i < targets.size(); i += 800) {
            end = i + 800;
            if (end > targets.size()) {
                end = targets.size();
            }
            try {
                IPushResult ret = push.pushMessageToList(taskId, targets.subList(i, end));
                loger.debug(ret.getResponse().toString());
            }
            catch (Exception e) {
                loger.error(e.getMessage());
                IPushResult ret = push.pushMessageToList(taskId, targets.subList(i, end));
                loger.debug(ret.getResponse().toString());
            }
        }

        return taskId;
    }

    /**
     * 提醒类消息提醒（采用静默推送，是为了IOS统一样式，同时android和ios应用在前台时的样式也可以自定义）
     *
     * @param msgJson
     * @param appId
     * @param appKey
     * @return
     */
    public static TransmissionTemplate getTemplate(JSONObject msgJson, String appId, String appKey) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTransmissionContent(msgJson.toJSONString());
        template.setTransmissionType(2);

        APNPayload payload = new APNPayload();
        payload.setBadge(1);
        payload.setContentAvailable(1);
        payload.setSound("default");
        payload.setCategory(msgJson.toJSONString());
        // 简单模式APNPayload.SimpleMsg
        payload.setAlertMsg(new APNPayload.SimpleAlertMsg(msgJson.getString("content")));
        // 字典模式使用下者
        template.setAPNInfo(payload);
        return template;
    }

    /**
     * 非提醒类消息（IOS关闭或在后台时接收不到消息）
     *
     * @param msgJson
     * @param appId
     * @param appKey
     * @return
     */
    public static TransmissionTemplate getSilenceTemplate(JSONObject msgJson, String appId, String appKey) {
        // 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent(msgJson.toJSONString());
        return template;
    }

    /**
     * 普通通知（智人博客在用）
     *
     * @param msgJson
     * @param appId
     * @param appKey
     * @return
     */
    public static NotificationTemplate getNotificationTemplate(JSONObject msgJson, String appId, String appKey) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        /** Style0系统样式 */
        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(msgJson.getString("title"));
        style.setText(msgJson.getString("content"));
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        template.setTransmissionContent(msgJson.toJSONString());
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }
}
