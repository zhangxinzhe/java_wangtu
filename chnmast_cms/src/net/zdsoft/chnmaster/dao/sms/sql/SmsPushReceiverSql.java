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
public class SmsPushReceiverSql {
    /**
     * 保存消息用户
     */
    public static final String save_push_receiver = "INSERT INTO T_SMS_PUSH_RECEIVER(USER_ID,PUSH_MSG_ID,AGENCY_ID,SEND_STATUS,RECEIVE_STATUS,RECEIVE_TIME,CREATE_TIME,READ_STATUS,NO_DEVICE,IS_DELETED) VALUES(?,?,?,?,?,?,?,?,?,?)";

    /**
     * 更新消息接收状态
     */
    public final static String update_push_user_recieve_status = "UPDATE T_SMS_PUSH_RECEIVER SET RECEIVE_STATUS = ?,RECEIVE_TIME = ? WHERE PUSH_MSG_ID = ? AND USER_ID = ? AND RECEIVE_STATUS = ?";
    public final static String update_push_user_read_status = "UPDATE T_SMS_PUSH_RECEIVER SET READ_STATUS = ?,READ_TIME = ? WHERE PUSH_MSG_ID = ? AND USER_ID = ? AND READ_STATUS = ?";
    public final static String update_all_push_users_to_readed = "UPDATE T_SMS_PUSH_RECEIVER SET READ_STATUS = ?,READ_TIME = ? WHERE READ_STATUS = ? AND USER_ID = ?";
    public final static String update_push_user_delete = "UPDATE T_SMS_PUSH_RECEIVER SET IS_DELETED = ? WHERE ID = ? AND IS_DELETED = ?";
    public final static String get_unread_push_receivers_by_userid = "SELECT * FROM T_SMS_PUSH_RECEIVER WHERE USER_ID = ? AND READ_STATUS = ?";
    /**
     * 更新状态
     */
    public static final String update_push_receiver_send_status = "UPDATE T_SMS_PUSH_RECEIVER SET SEND_STATUS = ? WHERE PUSH_MSG_ID = ?";
}
