/*
 * @(#)SmsWolunCallBackAction.java    Created on 2015年11月17日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsBaseAction;
import net.zdsoft.chnmaster.service.SmsSendDetailService;
import net.zdsoft.common.enums.ReceiveStatusEnum;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class SmsWolunCallBackAction extends CmsBaseAction {
    // private final Logger log = LoggerFactory.getLogger("smsCallBackFilter");
    private String type;
    private String content;
    private static List<String> SUCCESS_STATUS = new ArrayList<String>();
    private static Map<String, String> FAILURE_STATUS = new HashMap<String, String>();

    static {
        // 成功状态初始化
        SUCCESS_STATUS.add("DELIVRD");
        SUCCESS_STATUS.add("DELIVERD");
        SUCCESS_STATUS.add("DeliveryToTerminal");
        SUCCESS_STATUS.add("Deliver");
        // 问题解析初始化
        FAILURE_STATUS.put("IA:0054", "超时未接收到响应消息");
        FAILURE_STATUS.put("DI:9403", "平台自定义错误代码：手机号码黑名单，可申请解除");
        FAILURE_STATUS.put("MK:0005", "呼叫被禁止。该用户的短信业务被禁止了。");
        FAILURE_STATUS.put("MK:0000", "一般是号码停机，空号导致的");
    }

    @Resource
    private SmsSendDetailService smsSendDetailService;

    public String callBack() {
        log.info("content:{} &type:{}", content, type);
        // 判断信息的合法性
        if (StringUtils.isBlank(content) && !content.startsWith("MT")) {
            print("FAILURE_CONTENT");
            return NONE;
        }
        // 截取真正的断线发送结果信息
        content = content.substring(2);
        String[] contents = content.split(";");
        for (String item : contents) {
            String items[] = item.split(",");
            if (items.length == 3) {
                // 判断短信id是否存在
                if (StringUtils.isBlank(items[0])) {
                    continue;
                }
                ReceiveStatusEnum status = null;
                // 发送结果
                String otherReason = items[2].trim();
                // 判断成功状态
                if (StringUtils.isBlank(items[2].trim()) || SUCCESS_STATUS.contains(items[2].trim())) {
                    status = ReceiveStatusEnum.SUCCESS;
                }
                else {
                    status = ReceiveStatusEnum.FAILURE;
                    if (StringUtils.isNotBlank(otherReason) && FAILURE_STATUS.get(otherReason) != null) {
                        otherReason += "(" + FAILURE_STATUS.get(otherReason) + ")";
                    }
                }
                smsSendDetailService.updateSmsSendDetailStatusByBatchId(items[0].trim(), 2, status, otherReason,
                        items[1].trim());
            }
        }
        print("SUCCESS");
        return NONE;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
