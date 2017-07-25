/*
 * @(#)SmsSendDetail.java    Created on 2013-11-19
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.sms;

import java.io.Serializable;

import net.zdsoft.common.enums.ReceiveStatusEnum;

/**
 * @author muyl
 * @version $Revision: 1.0 $, $Date: 2013-11-19 下午04:23:37 $
 */
public class SmsSendDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long sendId;
    private Long receiveUserid;
    private String receiveUsername;
    private String receiveRealname;
    private String mobile;
    private ReceiveStatusEnum receiveStatus;
    private String statusDesc;
    private String sequence;
    private String batchId;

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
     * @return Returns the sendId.
     */
    public Long getSendId() {
        return sendId;
    }

    /**
     * @param sendId
     *            The sendId to set.
     */
    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    /**
     * @return Returns the receiveUserid.
     */
    public Long getReceiveUserid() {
        return receiveUserid;
    }

    /**
     * @param receiveUserid
     *            The receiveUserid to set.
     */
    public void setReceiveUserid(Long receiveUserid) {
        this.receiveUserid = receiveUserid;
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

    /**
     * @return Returns the sequence.
     */
    public String getSequence() {
        return sequence;
    }

    /**
     * @param sequence
     *            The sequence to set.
     */
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
}
