/* 
 * @(#)SystemLog.java    Created on 2014-6-26
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.entity.system;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;

/**
 * 系统日志【t_system_log】
 * 
 * @author oucl
 * @version $Revision: 1.0 $, $Date: 2014-6-26 下午3:46:45 $
 */
public class SystemLog extends BaseEntity {
    private static final long serialVersionUID = -2941882440029766676L;

    /**
     * 操作人id
     */
    private long userId;
    /**
     * 操作内容
     */
    private String operate;
    /**
     * 操作时间
     */
    private Date operateTime;
    /**
     * 操作ip
     */
    private String operateIp;

    // 扩展属性------------------------
    private String name; // 用户名
    private String realName; // 姓名

    /**
     * 获取操作人id
     * 
     * @return
     */
    public long getUserId() {
        return userId;
    }

    /**
     * 设置操作人id
     * 
     * @param userId
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * 获取操作内容
     * 
     * @return
     */
    public String getOperate() {
        return operate;
    }

    /**
     * 设置操作内容
     * 
     * @param operate
     */
    public void setOperate(String operate) {
        this.operate = operate;
    }

    /**
     * 获取操作时间
     * 
     * @return
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * 设置操作时间
     * 
     * @param operateTime
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取操作ip
     * 
     * @return
     */
    public String getOperateIp() {
        return operateIp;
    }

    /**
     * 设置操作ip
     * 
     * @param operateIp
     */
    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

}
