/*
 * @(#)SmsSend.java    Created on 2013-11-19
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.sms;

import java.io.Serializable;
import java.util.Date;

import net.zdsoft.common.enums.ReceiveStatusEnum;
import net.zdsoft.common.enums.SendStatusEnum;
import net.zdsoft.common.enums.SmsType;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2013-11-19 下午04:07:39 $
 */
public class SmsSend implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long sendUserid;
    private String sendRealname;
    private Date sendDate;
    private String content;
    private SmsType smsType;

    private SendStatusEnum sendStatus;

    private String receiveUsername;
    private String receiveRealname;
    private String receiveAgencyName;
    private String mobile;
    private ReceiveStatusEnum receiveStatus;
    private String statusDesc;

    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Returns the sendUserid.
     */
    public Long getSendUserid() {
        return sendUserid;
    }

    /**
     * @param sendUserid
     *            The sendUserid to set.
     */
    public void setSendUserid(Long sendUserid) {
        this.sendUserid = sendUserid;
    }

    /**
     * @return Returns the sendRealname.
     */
    public String getSendRealname() {
        return sendRealname;
    }

    /**
     * @param sendRealname
     *            The sendRealname to set.
     */
    public void setSendRealname(String sendRealname) {
        this.sendRealname = sendRealname;
    }

    /**
     * @return Returns the sendDate.
     */
    public Date getSendDate() {
        return sendDate;
    }

    /**
     * @param sendDate
     *            The sendDate to set.
     */
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    /**
     * @return Returns the content.
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            The content to set.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return Returns the smsType.
     */
    public SmsType getSmsType() {
        return smsType;
    }

    /**
     * @param smsType
     *            The smsType to set.
     */
    public void setSmsType(SmsType smsType) {
        this.smsType = smsType;
    }

    /**
     * @return Returns the sendStatus.
     */
    public SendStatusEnum getSendStatus() {
        return sendStatus;
    }

    /**
     * @param sendStatus
     *            The sendStatus to set.
     */
    public void setSendStatus(SendStatusEnum sendStatus) {
        this.sendStatus = sendStatus;
    }

    /**
     * @return Returns the receiveUsername.
     */
    public String getReceiveUsername() {
        return receiveUsername;
    }

    /**
     * @param receiveUsername
     *            The receiveUsername to set.
     */
    public void setReceiveUsername(String receiveUsername) {
        this.receiveUsername = receiveUsername;
    }

    /**
     * @return Returns the receiveRealname.
     */
    public String getReceiveRealname() {
        return receiveRealname;
    }

    /**
     * @param receiveRealname
     *            The receiveRealname to set.
     */
    public void setReceiveRealname(String receiveRealname) {
        this.receiveRealname = receiveRealname;
    }

    /**
     * @return Returns the receiveAgencyName.
     */
    public String getReceiveAgencyName() {
        return receiveAgencyName;
    }

    /**
     * @param receiveAgencyName
     *            The receiveAgencyName to set.
     */
    public void setReceiveAgencyName(String receiveAgencyName) {
        this.receiveAgencyName = receiveAgencyName;
    }

    /**
     * @return Returns the mobile.
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     *            The mobile to set.
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return Returns the receiveStatus.
     */
    public ReceiveStatusEnum getReceiveStatus() {
        return receiveStatus;
    }

    /**
     * @param receiveStatus
     *            The receiveStatus to set.
     */
    public void setReceiveStatus(ReceiveStatusEnum receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    /**
     * @return Returns the statusDesc.
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * @param statusDesc
     *            The statusDesc to set.
     */
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

}
