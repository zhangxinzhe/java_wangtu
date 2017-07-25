/* 
 * @(#)BaseDao.java    Created on 2013-8-16
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.dao;

/**
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2013-8-16 上午10:41:25 $
 */
public interface BaseDao {

    /**
     * 根据表名获取主键
     * 
     * @param tableName
     *            表名，要求大写，netstudy中目前支持的表<br>
     *            t_seq_common：T_AGENCY_AUDITION、T_COURSE_VIDEO<br>
     *            t_seq_user：T_USER<br>
     *            t_seq_user_student：T_USER_STUDENT<br>
     *            t_seq_course：T_COURSE、T_COURSE_COMBO、T_COURSE_TRY<br>
     *            t_seq_student_report： T_STUDENT_REPORT <br>
     *            t_seq_student_course： T_STUDENT_COURSE <br>
     *            t_seq_ware_order：T_WARE_ORDER、T_FUNDS_ALIPAY <br>
     *            t_seq_ware_order_detail： T_WARE_ORDER_DETAIL <br>
     *            t_seq_funds： T_FUNDS<br>
     *            t_seq_funds_record： T_FUNDS_RECORD <br>
     *            t_seq_server_log： T_SERVER_LOG <br>
     *            t_seq_server_log_try： T_SERVER_LOG_TRY<br>
     *            t_seq_server_rollcall： T_SERVER_ROLLCALL <br>
     *            t_seq_system_log： T_SYSTEM_LOG <br>
     *            t_seq_stat_student_amount：T_STAT_STUDENT_AMOUNT <br>
     *            t_seq_stat_class_report： T_STAT_CLASS_REPORT<br>
     *            t_seq_user_screenshot：T_USER_SCREENSHOT<br>
     * @return
     */
    public long generatePrimaryKey(String tableName);
}
