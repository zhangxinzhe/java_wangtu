-- 1.3.4.0版 初始化语法（黄龙奖）

-- ------------------------------------执行1 ddl -------------------------------------
-- t_compete表添加活动类型
ALTER TABLE T_COMPETE ADD COLUMN COMPETE_TYPE TINYINT DEFAULT 0 AFTER COMPETE_NAME;
-- t_compete表添加批次
ALTER TABLE T_COMPETE ADD COLUMN COMPETE_BATCH CHAR(6) AFTER COMPETE_TYPE;


-- 黄龙奖报名表【1.3.4.0】
CREATE TABLE T_COMPETE_ATTEND_HLJ
(
  ID                INTEGER NOT NULL,
  COMPETE_ID        INTEGER NOT NULL,           -- 活动ID
  COMPETE_TYPE      TINYINT DEFAULT 20,         -- 活动类型（20黄龙奖合唱比赛；21黄龙奖乐队比赛···）==T_COMPETE.COMPETE_TYPE
  USER_ID           INTEGER DEFAULT 0,          -- 关联用户（后台报名时，用户非必选，可存储0）
  TEAMNAME          VARCHAR(75),                -- 团队名称/乐队名称
  TEAM_TYPE         TINYINT DEFAULT 0,          -- 参赛组别【COMPETE_TYPE==20黄龙奖合唱比赛，其他类型默认0】（1公开合唱组、2专业合唱组、3少年组，4、成人组）
  BAND_TYPE         VARCHAR(50),                -- 乐队类型【COMPETE_TYPE==21黄龙奖乐队比赛，其他类型默认空】
  UNIT_NAME         VARCHAR(75),                -- 组织单位
  TEAM_LEADER       VARCHAR(25),                -- 总负责人
  TEAM_NUM          INTEGER DEFAULT 0,          -- 成员数
  MAJOR_NUM         INTEGER DEFAULT 0,          -- 指挥人数【COMPETE_TYPE==21黄龙奖乐队比赛，其他类型默认0】
  DRUM_NUM          INTEGER DEFAULT 0,          -- 钢伴人数【COMPETE_TYPE==21黄龙奖乐队比赛，其他类型默认0】
  MANAGE_NUM        INTEGER DEFAULT 0,          -- 行政人数
  PHONE             VARCHAR(25),                -- 联系方式
  EMAIL             VARCHAR(50),                -- 电子邮箱
  PLACE             VARCHAR(225),               -- 联系地址
  PHOTO_FILE        VARCHAR(500),               -- 报名表照片路径 
  TEAM_INTRO        VARCHAR(4000),              -- 团队介绍
  MEMBERS_INTRO     VARCHAR(4000),              -- 成员介绍
  APPLY_TYPE        TINYINT DEFAULT 0,          -- 报名形式（0前台报名，1后台报名）
  ATTEND_CODE       VARCHAR(11),                -- 参赛码（1001，2001，3001....）
  CREATION_TIME     DATETIME,                   -- 创建时间
  VOTE_NUM          INTEGER DEFAULT 0,          -- 投票数
  VOTE_SCORE        FLOAT(12,2) DEFAULT 0,      -- 投票的分（10÷选手投票的最高票数×当前选手的票数=选手投票得分）
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 活动投票表【1.3.4.0】
CREATE TABLE T_COMPETE_VOTE
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  COMPETE_ID        INTEGER NOT NULL,           -- 活动ID
  ATTEND_ID         INTEGER NOT NULL,           -- 报名表ID
  IP                VARCHAR(20),                -- IP
  VOTE_TIME         DATETIME,                   -- 投票时间
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;



-- 活动报名 主键生成表t_seq_compete_attend
CREATE TABLE t_seq_compete_attend (
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


-- ------------------------------------执行2 dml 调整数据-------------------------------------
-- 添加活动类型字段，将默认类型改成神州唱响类型
update T_COMPETE set COMPETE_TYPE = 10 where COMPETE_TYPE = 0;

-- 活动报名表使用单独序列
insert into t_seq_compete_attend select max_seq, table_name from t_seq_compete where table_name='T_COMPETE_ATTEND';
delete from t_seq_compete where table_name='T_COMPETE_ATTEND';



commit;

-- ------------------------------------ 版本号记录 -------------------------------------
delete from T_SYSTEM_VERSION where id=8;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(8, '黄龙奖定制版', '公司', '黄龙奖赛事活动', now(), '1.3.4.0', '', null);
commit;

