-- 1.4.2.0

-- ------------------------------------执行1 ddl -------------------------------------
-- 音乐网对接平台【1.4.2.0】
CREATE TABLE T_USER_BIND(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  BIND_ID           CHAR(100) NOT NULL,                 -- 绑定id/tokenID(微课id/微信id)
  NICK_NAME         VARCHAR(60),                        -- 昵称
  USER_ID           INTEGER NOT NULL,                   -- 用户id
  PLAT_TYPE         TINYINT NOT NULL DEFAULT 0,         -- 平台类型（0微课,1微信,2QQ,9其他平台）
  BIND_TIME         DATETIME NOT NULL,                  -- 绑定时间
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- 优惠卷课程关联表【1.4.2.0】
CREATE TABLE T_COUPON_COURSE(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  COUPON_ID         INTEGER NOT NULL,                   -- 优惠卷ID
  COURSE_ID         INTEGER NOT NULL,                   -- 课程ID
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 新建表索引
CREATE INDEX IDX_T_USER_BIND_PB
    ON T_USER_BIND(PLAT_TYPE,BIND_ID);
CREATE UNIQUE INDEX IDX_T_USER_BIND_BINDID_USERID
    ON T_USER_BIND(BIND_ID, USER_ID);
    
CREATE INDEX IDX_T_COUPON_COURSE_COUPONID
    ON T_COUPON_COURSE(COUPON_ID);
CREATE INDEX IDX_T_COUPON_COURSE_COURSEID
    ON T_COUPON_COURSE(COURSE_ID);  

    
-- t_coupon主键生成表 t_seq_coupon
CREATE TABLE t_seq_coupon (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name   VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;
    

-- 修改表结构
-- 优惠券表ID非自增长
ALTER TABLE T_COUPON MODIFY ID INTEGER NOT NULL;

-- 活动报名表添加字段：加票数
ALTER TABLE T_COMPETE_ATTEND ADD COLUMN VOTE_ADD_NUM INTEGER DEFAULT 0 AFTER VOTE_NUM;
ALTER TABLE T_COMPETE_ATTEND_HLJ ADD COLUMN VOTE_ADD_NUM INTEGER DEFAULT 0 AFTER VOTE_NUM;


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
    
-- ------------------------------------执行2 dml -------------------------------------
-- cnzz流量监控配置参数值
update t_system_config set `VALUE` = '<script src="http://s95.cnzz.com/stat.php?id=1256819091&web_id=1256819091" language="JavaScript"></script>', CAN_EDIT = 0 
where `CODE` = 'system.stat_script';

-- 页脚参数
delete from t_system_config where code in ('footer.index_pc_value', 'footer.compete_pc_value');
insert into T_SYSTEM_CONFIG(CODE, NAME, VALUE, DESCRIPTION, CAN_VIEW, can_edit, validate, order_no)
values('footer.index_pc_value', 'PC版页脚显示', 'CopyRight 北京国臻教育科技有限公司 版权所有<span class="mx-10">|</span><a href="http://www.miitbeian.gov.cn/" target="_blank">京ICP备16013122</a>', 'PC版页脚显示内容', 1, 1, 'config-max-length-1000', 200);
insert into T_SYSTEM_CONFIG(CODE, NAME, VALUE, DESCRIPTION, CAN_VIEW, can_edit, validate, order_no)
values('footer.compete_pc_value', 'PC版活动专题页脚显示', 'CopyRight 北京国臻教育科技有限公司 版权所有 | 京ICP备16013122', 'PC版活动专题页脚显示内容', 1, 1, 'config-max-length-1000', 201);


-- 优惠券序列表初始化最大值
insert into t_seq_coupon(max_seq, table_name) values ((select max(id) from t_coupon), 'T_COUPON');

-- 优惠券管理添加导出功能
delete from T_SYSTEM_MODULE where id = 1500904;
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500904, 1, '导出', '/account/exportCoupon.htm', null, 0, 1500900, 0, 1, 1500904);


-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;


-- ------------------------------------ 版本号记录 -------------------------------------
delete from T_SYSTEM_VERSION where id=13;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(13, '标准版', '音乐联盟', '微课对接、找回密码、优惠券功能', now(), '1.4.2.0', '20160805', null);
commit;