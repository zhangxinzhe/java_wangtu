/*
 * @(#)SendSmsDetailAction.java    Created on 2015年12月21日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.stat;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsPageAction;
import net.zdsoft.chnmaster.service.SmsSendService;
import net.zdsoft.common.dao.queryCondition.EqualCondition;
import net.zdsoft.common.dao.queryCondition.GreaterCondition;
import net.zdsoft.common.dao.queryCondition.LessCondition;
import net.zdsoft.common.dao.queryCondition.LikeCondition;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.sms.SmsSend;
import net.zdsoft.common.enums.ReceiveStatusEnum;
import net.zdsoft.common.enums.SmsType;
import net.zdsoft.keel.util.DateUtils;

/**
 * 短信发送详情
 *
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2015年12月21日 下午1:56:59 $
 */
@Scope("prototype")
@Controller
public class SmsStatAction extends CmsPageAction {

    private static final long serialVersionUID = 1L;

    // 查询条件
    private Date sendDateStart;
    private Date sendDateEnd;
    private SmsType type;// 短信类型
    private ReceiveStatusEnum smsState;// 发送状态
    private String receiveName;// 发送人
    private String receivePhone;// 接收人
    private String content;// 短信内容

    private List<SmsSend> sendList;

    @Resource
    private SmsSendService smsSendService;

    /**
     * 短信查询
     *
     * @return
     */
    public String smsStat() {
        if (null == sendDateStart || null == sendDateEnd) {
            return SUCCESS;
        }
        sendDateEnd = DateUtils.addHour(sendDateEnd, 23);
        sendDateEnd = DateUtils.addMinute(sendDateEnd, 59);
        sendList = smsSendService.getSmsSends(buildCondition(), getPage());
        return SUCCESS;
    }

    /**
     * 构建查询条件
     */
    public List<QueryCondition> buildCondition() {
        List<QueryCondition> conditions = new ArrayList<QueryCondition>();
        if (null != sendDateStart) {
            conditions.add(new GreaterCondition("S.SEND_DATE", DateUtils.getStartDate(sendDateStart)));
        }
        if (null != sendDateEnd) {
            conditions.add(new LessCondition("S.SEND_DATE", DateUtils.getEndDate(sendDateEnd)));
        }
        if (null != type) {
            conditions.add(new EqualCondition("S.SMS_TYPE", type.getValue(), Types.INTEGER));
        }
        if (null != smsState) {
            conditions.add(new EqualCondition("D.RECEIVE_STATUS", smsState.getValue(), Types.INTEGER));
        }
        if (StringUtils.isNotEmpty(receiveName)) {
            conditions.add(new LikeCondition("D.RECEIVE_REALNAME", receiveName));
        }
        if (StringUtils.isNotEmpty(receivePhone)) {
            conditions.add(new LikeCondition("D.MOBILE", receivePhone));
        }
        if (StringUtils.isNotEmpty(content)) {
            conditions.add(new LikeCondition("S.CONTENT", content));
        }
        return conditions;
    }

    /**
     * 获取短信类型列表
     *
     * @return
     */
    public SmsType[] getSmsTypes() {
        return SmsType.values();
    }

    /**
     * 获取短信接收状态列表
     *
     * @return
     */
    public ReceiveStatusEnum[] getSendState() {
        return ReceiveStatusEnum.values();
    }

    public Date getSendDateStart() {
        return sendDateStart;
    }

    public void setSendDateStart(Date sendDateStart) {
        this.sendDateStart = sendDateStart;
    }

    public Date getSendDateEnd() {
        return sendDateEnd;
    }

    public void setSendDateEnd(Date sendDateEnd) {
        this.sendDateEnd = sendDateEnd;
    }

    public SmsType getType() {
        return type;
    }

    public void setType(SmsType type) {
        this.type = type;
    }

    public ReceiveStatusEnum getSmsState() {
        return smsState;
    }

    public void setSmsState(ReceiveStatusEnum smsState) {
        this.smsState = smsState;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<SmsSend> getSendList() {
        return sendList;
    }

}
