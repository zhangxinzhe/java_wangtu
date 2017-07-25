/*
 * @(#)DispatcherUtils.java    Created on 2015-5-28
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.utils;

import org.apache.commons.lang3.StringUtils;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015-5-28 上午11:48:43 $
 */
public class DispatcherUtils {

    /**
     * 从调度中心取课程id<br>
     * 根据dispatch生成，如果没有dispatch则此字段值由自增序列生成
     *
     * @return -1：调度中心地址没有维护,请在系统参数里配置，...<br>
     *         -2：调度中心访问失败，请确认调度中心可以访问，...<br>
     *         -3：调度中心返回结果不是数字，请检查调度中心地址配置是否正确，...<br>
     *         -9:dispatch开关关闭<br>
     *         else：正常
     */
    public static Long generateMeetingId() {
        String courseDispatcherSwitch = NetstudyConfig.getParam(BaseConstants.DISPATCH_SWITCH);
        // 开启调动中心
        if ("1".equals(courseDispatcherSwitch)) {
            String dispatcherDomain = NetstudyConfig.getParam(BaseConstants.DISPATCHER_DOMAIN);
            // 调度中心地址没有维护
            if (StringUtils.isBlank(dispatcherDomain)) {
                return -1l;
            }
            String systemDamain = NetstudyConfig.getParam(BaseConstants.DOMAIN_HOME);
            String visitAddr = dispatcherDomain + "/getRoomId.htm?url=" + systemDamain + "/wxbCall/serverLog.htm";
            String content = HtmlUtil.getAllHtml(visitAddr, "GBK");

            // 调度中心地址访问失败
            if (StringUtils.isBlank(content)) {
                return -2l;
            }

            try {
                long meetingId = Long.parseLong(content.trim());
                return meetingId;
            }
            catch (NumberFormatException e) {
                return -3l;
            }
        }
        else {
            return -9l;
        }
    }

}
