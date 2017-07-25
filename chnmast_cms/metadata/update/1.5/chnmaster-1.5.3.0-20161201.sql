-- 高校风采展示

-- ------------------------------------执行1 ddl -------------------------------------
-- 添加字段：绑定高校机构【1.5.3.0】
alter table T_COURSE add column COLLEGE_ID INTEGER after STUDENT_ID;

-- 高校风采展示-高校表
CREATE TABLE t_college
(
  ID                    INTEGER NOT NULL,
  NAME                  VARCHAR(75),            -- 院校名称
  PHONE                 VARCHAR(100),           -- 联系电话
  BANNER_FILE           VARCHAR(500),           -- banner图（upload/college/{年}/{月}/{日}/{id}_banner.{扩展名}?t=timestamp）
  LOGO_FILE             VARCHAR(500),           -- logo图（upload/college/{年}/{月}/{日}/{id}_logo.{扩展名}?t=timestamp）
  INTRODUCTION          VARCHAR(4000),          -- 高校简介
  FEATURE               VARCHAR(4000),          -- 高校优势
  ISRECOMMEND           TINYINT DEFAULT 0,      -- 是否推荐
  DISPLAY_ORDER         INTEGER DEFAULT 0,      -- 显示次序
  CREATETIME            DATETIME,
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- 主键序列化
CREATE TABLE t_seq_college (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name   VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;


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

-- ------------------------------------执行2 dml -------------------------------------
-- 添加新模块
-- 高校风采展示
DELETE FROM T_SYSTEM_MODULE WHERE id in(1400560,1400561,1400562,1400563);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400560, 1, '高校风采展示', '/homepage/college/collegeManage.htm', null, 1, 1400000, 0, 0, 1400560);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400561, 1, '新增', '/homepage/college/collegeAdd.htm', null, 0, 1400560, 0, 1, 1400561);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400562, 1, '修改', '/homepage/college/collegeEdit.htm', null, 0, 1400560, 0, 1, 1400562);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400563, 1, '删除', '/homepage/college/collegeDel.htm', null, 0, 1400560, 0, 1, 1400563);


-- 模块调整
-- 页脚链接修改成页脚维护
update T_SYSTEM_MODULE set name='页脚维护' where id=1400950;
-- 活动专题子模块 搬移到 课程管理主模块下
DELETE FROM T_SYSTEM_MODULE WHERE id in (1401000,1300400);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300400, 1, '活动专题', '/homepage/compete/competeManage.htm', null, 1, 1300000, 0, 0, 1300400);



-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;


-- 添加版本记录
delete from T_SYSTEM_VERSION where id=18;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(18, '标准版', '音乐联盟', '高校联盟，ios支付', now(), '1.5.3.0', '20161201', null);
commit;
