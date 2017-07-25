-- 1.3.2.0版 神州唱响活动

-- ------------------------------------执行1 ddl 表、索引、函数-------------------------------------

-- ------1 新增表索引------
-- 活动表
CREATE TABLE T_COMPETE
(
  ID                    INTEGER NOT NULL,
  COMPETE_NAME          VARCHAR(75),            -- 活动名称
  RULE_FILE             VARCHAR(200),           -- 活动章程文件路径
  FORM_NAME             VARCHAR(75),            -- 报名表原文件名
  FORM_FILE             VARCHAR(200),           -- 报名表文件路径
  COMPETE_FEE           FLOAT(12,2) DEFAULT 0,  -- 报名费
  ATTEND_BEGINTIME      DATETIME,               -- 报名开始时间
  ATTEND_ENDTIME        DATETIME,               -- 报名结束时间
  BEGINTIME             DATETIME,               -- 活动开始时间
  ENDTIME               DATETIME,               -- 活动结束时间
  CREATETIME            DATETIME NOT NULL,      -- 创建时间
  IS_DOING              INTEGER DEFAULT 0,      -- 是否进行中（0：否，1：是）
  REMARK                VARCHAR(4000),          -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 活动报名表
CREATE TABLE T_COMPETE_ATTEND
(
  ID                INTEGER NOT NULL,
  COMPETE_ID        INTEGER NOT NULL,           -- 活动ID
  USER_ID           INTEGER DEFAULT 0,          -- 关联用户（后台报名时，用户非必选，可存储0）
  REALNAME          VARCHAR(75),                -- 姓名
  SEX               TINYINT DEFAULT 0,          -- 性别（0未知，1男，2女）
  BIRTHDAY          DATE,                       -- 生日
  PHOTO_FILE        VARCHAR(500),               -- 报名表照片路径    
  MOBILEPHONE       VARCHAR(25),                -- 联系方式
  EMAIL             VARCHAR(50),                -- Email
  SCHOOL_NAME       VARCHAR(75),                -- 学校院系
  UNIT_NAME         VARCHAR(75),                -- 选送单位
  APPLY_TYPE        TINYINT DEFAULT 0,          -- 报名形式（0前台报名，1后台报名）
  GROUP_TYPE        TINYINT DEFAULT 0,          -- 组别（1教师组民族唱法、2教师组美声唱法 、3学生组民族唱法 、4学生组美声唱法 、5学生组流行唱法）
  ATTEND_CODE       VARCHAR(11),                -- 参赛码（1001，2001，3001....）（付款成功后生成参赛码）
  CREATION_TIME     DATETIME,                   -- 创建时间
  PAY_AMOUNT        FLOAT(12,2) DEFAULT 0,      -- 实付金额
  ORDER_STATUS      TINYINT DEFAULT 0,          -- 状态（0待付款，10交易成功，20交易失败）
  VIDEO_URL         VARCHAR(200),               -- 视频地址
  AUDIT_STATUS      TINYINT DEFAULT 0,          -- （此字段暂时不用）状态（0：未审核，1审核通过，2：审核失败）
  VOTE_NUM          INTEGER DEFAULT 0,          -- 投票数
  REMARK            VARCHAR(4000),              -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 报名曲目表
CREATE TABLE T_COMPETE_ATTEND_SONG
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  COMPETE_ID        INTEGER NOT NULL,           -- 活动ID
  ATTEND_ID         INTEGER NOT NULL,           -- 报名表ID
  STAGE             TINYINT DEFAULT 0,          -- 赛程（1初赛，2复赛，3半决赛，4决赛）
  SONG_NAME         VARCHAR(75),                -- 曲目名称
  SONG_AUTHOR       VARCHAR(75),                -- 曲目原唱
  REMARK            VARCHAR(4000),              -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 组委会表
CREATE TABLE T_COMPETE_COMMITTEE
(
  ID                INTEGER NOT NULL,
  COMPETE_ID        INTEGER NOT NULL,           -- 活动ID
  REALNAME          VARCHAR(75),                -- 姓名
  SEX               TINYINT DEFAULT 0,          -- 性别（0未知，1男，2女）
  UNIT_NAME         VARCHAR(75),                -- 单位
  TITLE             TINYINT NOT NULL,           -- 主委会头衔（1顾问、2主席、3副主席、4委员、5办公室主任、6办公室副主任）
  JOB_TITLE         VARCHAR(75),                -- 个人职务
  INTRODUCTION      VARCHAR(4000),              -- 个人介绍
  PHOTO_FILE        VARCHAR(500),               -- 照片路径
  ORDER_NO          INTEGER NOT NULL,           -- 排序号
  REMARK            VARCHAR(4000),              -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 活动专题主键生成表t_seq_compete
CREATE TABLE t_seq_compete (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name  VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;

-- ------2 新增表索引------
CREATE INDEX IDX_T_COMPETE_ATTEND_COMPETEID
    ON T_COMPETE_ATTEND(COMPETE_ID);
CREATE INDEX IDX_T_COMPETE_ATTEND_SONG_COMPETEID
    ON T_COMPETE_ATTEND_SONG(COMPETE_ID);
CREATE INDEX IDX_T_COMPETE_ATTEND_SONG_ATTENDID
    ON T_COMPETE_ATTEND_SONG(ATTEND_ID);    
CREATE INDEX IDX_T_COMPETE_COMMITTEE_COMPETEID
    ON T_COMPETE_COMMITTEE(COMPETE_ID);    


-- ------3 创建主键生成函数------
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
    elseif in_table_name='T_COURSE' then
       REPLACE INTO t_seq_course (table_name) VALUES (in_table_name); 
    elseif in_table_name='T_ORDER' then
        REPLACE INTO t_seq_order (table_name) VALUES (in_table_name);
    elseif in_table_name='T_ORDER_DETAIL' then
        REPLACE INTO t_seq_order_detail (table_name) VALUES (in_table_name);
    elseif in_table_name='T_ORDER_REPORT' then
        REPLACE INTO t_seq_order_report (table_name) VALUES (in_table_name);
    elseif in_table_name='T_WXB' then
        REPLACE INTO t_seq_wxb (table_name) VALUES (in_table_name); 
    elseif in_table_name='T_COMPETE' or in_table_name='T_COMPETE_ATTEND' or in_table_name='T_COMPETE_COMMITTEE' then
        REPLACE INTO t_seq_compete (table_name) VALUES (in_table_name); 
    elseif in_table_name='T_SYSTEM_USER' or in_table_name='T_SYSTEM_ROLE' or in_table_name='T_PICTURE' or in_table_name='T_INDEX_ADV' or in_table_name='T_INDEX_STUDENT' then
        REPLACE INTO t_seq_common (table_name) VALUES (in_table_name);
    else
        RETURN 0;
    end if;
    RETURN LAST_INSERT_ID();
    
END $$                                         
DELIMITER ; 


-- ------4 涉及表结构改造------
-- T_INDEX_INFO 增加 compete_id
ALTER TABLE T_INDEX_INFO ADD COLUMN COMPETE_ID INTEGER DEFAULT 0 AFTER ID;

-- 订单表T_ORDER增加订单类型ORDER_KIND（0课程/音乐会，3活动）
ALTER TABLE T_ORDER ADD COLUMN ORDER_KIND TINYINT DEFAULT 0 AFTER BUY_TYPE;

-- T_INDEX_ADV 增加 compete_id
ALTER TABLE T_INDEX_ADV ADD COLUMN COMPETE_ID INTEGER DEFAULT 0 AFTER FIELD_TYPE;


-- ------------------------------------执行2 dml 调整数据-------------------------------------
-- 添加模块
delete from T_SYSTEM_MODULE where id=1401000;
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1401000, 1, '活动专题', '/homepage/compete/competeManage.htm', null, 1, 1400000, 0, 0, 1401000);



-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;

-- 添加版本记录
delete from T_SYSTEM_VERSION where id=6;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(6, '神州唱响定制版', '国臻', '神州唱响', now(), '1.3.2.0', '', null);
commit;
