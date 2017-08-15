/*
 * @(#)ServerDaoImpl.java    Created on 2013-9-5
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id: ServerDaoImpl.java 10264 2014-06-13 09:01:42Z liliang $
 */
package net.zdsoft.chnmaster.dao.sms.sql;

/**
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2016年8月10日 下午4:27:16 $
 */
public class SmsPushDeviceSql {
    /**
     * 获取设备
     */
    public static final String get_push_devies = "SELECT * FROM T_SMS_PUSH_DEVICE WHERE USER_ID IN ";
    public final static String get_push_device_by_push_token = "SELECT * FROM T_SMS_PUSH_DEVICE WHERE CLIENT_ID=?";
    public final static String get_push_device_by_device_id = "SELECT * FROM T_SMS_PUSH_DEVICE WHERE DEVICE_ID=?";
    public final static String save_push_device = "INSERT INTO T_SMS_PUSH_DEVICE(USER_ID,AGENCY_ID,PUSH_TOKEN,CLIENT_ID,DEVICE_ID,CREATE_TIME,DEVICE_TYPE) VALUES(?,?,?,?,?,?,?)";
    public final static String del_push_device = "DELETE FROM T_SMS_PUSH_DEVICE WHERE ID = ?";
    public final static String update_push_status = "UPDATE T_SMS_PUSH_DEVICE SET PUSH_STATUS = ? WHERE CLIENT_ID = ? AND PUSH_STATUS <> ?";
    public final static String delete_push_device = "DELETE FROM T_SMS_PUSH_DEVICE WHERE PUSH_TOKEN = ?";
}
