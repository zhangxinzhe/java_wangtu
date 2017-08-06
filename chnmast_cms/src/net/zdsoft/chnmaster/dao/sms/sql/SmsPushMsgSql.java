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
public class SmsPushMsgSql {
    public final static String get_first_unread_push_msg = "SELECT M.*,U.ID PUSH_USER_ID,U.READ_STATUS,U.READ_TIME FROM T_SMS_PUSH_MSG M,T_SMS_PUSH_RECEIVER U WHERE M.ID = U.PUSH_MSG_ID AND U.IS_DELETED = 0 AND U.USER_ID = ? ORDER BY M.ID DESC LIMIT 1";
    public final static String get_push_msgs = "SELECT M.*,U.ID PUSH_USER_ID,U.READ_STATUS,U.READ_TIME FROM T_SMS_PUSH_MSG M,T_SMS_PUSH_RECEIVER U WHERE M.ID = U.PUSH_MSG_ID AND U.IS_DELETED = 0 AND U.USER_ID = ? ORDER BY M.ID DESC";
    public final static String add_push_msg_receive_num = "UPDATE T_SMS_PUSH_MSG SET RECEIVE_NUM = RECEIVE_NUM + 1 WHERE ID = ?";
    public final static String add_push_msg_read_num = "UPDATE T_SMS_PUSH_MSG SET READ_NUM = READ_NUM + 1 WHERE ID = ?";
    /**
     * 更新消息状态
     */
    public static final String update_push_msg_send_status = "UPDATE T_SMS_PUSH_MSG SET SEND_STATUS = ?,SEND_NUM = ?,ANDROID_TASK_ID = ?,IOS_TASK_ID = ? WHERE ID = ?";
    /**
     * 保存消息
     */
    public static final String save_push_msg = "INSERT INTO T_SMS_PUSH_MSG(TITLE,CONTENT,MSG_URL,USER_ID,USER_TYPE,REAL_NAME,RELATIVE_ID,MSG_TYPE,CREATE_TIME,SEND_STATUS,NUM,IS_SYSTEM,AGENCY_ID,RELATIVE_EXT) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

}
