
-- -----------------------------创建主键生成函数--------------------------------------
-- 创建主键生成函数
-- 创建主键生成函数
DROP FUNCTION IF EXISTS fun_next_seq; 
DELIMITER $$    
CREATE FUNCTION fun_next_seq(in_table_name varchar(50))
RETURNS int                
BEGIN
    select upper(in_table_name) into in_table_name;

    if in_table_name='T_USER' then
        REPLACE INTO t_seq_user (table_name) VALUES (in_table_name);
    elseif in_table_name='T_AGENCY' then
        REPLACE INTO t_seq_agency (table_name) VALUES (in_table_name);  
    elseif in_table_name='T_USER_UNION_MEMBER' then
        REPLACE INTO t_seq_user_union_member (table_name) VALUES (in_table_name);
    elseif in_table_name='T_COLLEGE' then
        REPLACE INTO t_seq_college (table_name) VALUES (in_table_name);
    elseif in_table_name='T_COURSE' then
        REPLACE INTO t_seq_course (table_name) VALUES (in_table_name); 
    elseif in_table_name='T_COUPON' then
        REPLACE INTO t_seq_coupon (table_name) VALUES (in_table_name); 
    elseif in_table_name='T_ORDER' then
        REPLACE INTO t_seq_order (table_name) VALUES (in_table_name);
    elseif in_table_name='T_ORDER_DETAIL' then
        REPLACE INTO t_seq_order_detail (table_name) VALUES (in_table_name);
    elseif in_table_name='T_ORDER_REPORT' then
        REPLACE INTO t_seq_order_report (table_name) VALUES (in_table_name);
    elseif in_table_name='T_WXB' then
        REPLACE INTO t_seq_wxb (table_name) VALUES (in_table_name); 
    elseif in_table_name='T_COMPETE' or in_table_name='T_COMPETE_COMMITTEE' then
        REPLACE INTO t_seq_compete (table_name) VALUES (in_table_name);
    elseif in_table_name='T_COMPETE_ATTEND' or in_table_name='T_COMPETE_ATTEND_HLJ' then
        REPLACE INTO t_seq_compete_attend (table_name) VALUES (in_table_name);
    elseif in_table_name='T_SYSTEM_USER' or in_table_name='T_SYSTEM_ROLE' or in_table_name='T_PICTURE' or in_table_name='T_INDEX_ADV' or in_table_name='T_INDEX_STUDENT' then
        REPLACE INTO t_seq_common (table_name) VALUES (in_table_name);
    else
        RETURN 0;
    end if;
    RETURN LAST_INSERT_ID();
    
END $$ 
DELIMITER ; 




