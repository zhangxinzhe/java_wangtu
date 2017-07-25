-- 1.4.0.0版 初始化语法

-- ------------------------------------执行1 ddl -------------------------------------
-- T_USER表添加是否联合会会员
ALTER TABLE T_USER ADD COLUMN IS_UNION_MEMBER TINYINT DEFAULT 0 AFTER IS_MEMBER;
-- T_USER表添加是否音乐导师
ALTER TABLE T_USER ADD COLUMN IS_TUTOR TINYINT DEFAULT 0 AFTER IS_UNION_MEMBER;


-- T_USER_TEACHER字段维护
ALTER TABLE T_USER_TEACHER ADD COLUMN NATION VARCHAR(20);
ALTER TABLE T_USER_TEACHER ADD COLUMN BIRTHDAY DATE;
ALTER TABLE T_USER_TEACHER ADD COLUMN POLITICAL VARCHAR(20);
ALTER TABLE T_USER_TEACHER ADD COLUMN WORK_UNIT VARCHAR(75);
ALTER TABLE T_USER_TEACHER ADD COLUMN ADDRESS VARCHAR(100);
ALTER TABLE T_USER_TEACHER ADD COLUMN MASTER_SCHOOL VARCHAR(75);
ALTER TABLE T_USER_TEACHER ADD COLUMN DOCTOR_SCHOOL VARCHAR(75);
ALTER TABLE T_USER_TEACHER ADD COLUMN IDCARD_FILE VARCHAR(500);
ALTER TABLE T_USER_TEACHER ADD COLUMN HAND_IDCARD_FILE VARCHAR(500);
ALTER TABLE T_USER_TEACHER ADD COLUMN TEA_SOURCE TINYINT DEFAULT 0;
ALTER TABLE T_USER_TEACHER ADD COLUMN APPLY_DATE DATE;
ALTER TABLE T_USER_TEACHER ADD COLUMN AUDIT_STATUS TINYINT DEFAULT 2 NOT NULL;
ALTER TABLE T_USER_TEACHER ADD COLUMN AUDIT_DATE DATE;
ALTER TABLE T_USER_TEACHER ADD COLUMN AUDIT_REALNAME VARCHAR(75);
ALTER TABLE T_USER_TEACHER ADD COLUMN AUDIT_MSG VARCHAR(500);

-- T_COURSE字段维护
ALTER TABLE T_COURSE modify COURSE_TYPE TINYINT DEFAULT 0;
ALTER TABLE T_COURSE ADD COLUMN COURSE_SOURCE TINYINT DEFAULT 0;
ALTER TABLE T_COURSE ADD COLUMN APPLY_DATE DATE;
ALTER TABLE T_COURSE ADD COLUMN APPLY_INFO VARCHAR(4000);
ALTER TABLE T_COURSE ADD COLUMN AUDIT_STATUS TINYINT DEFAULT 2 NOT NULL;
ALTER TABLE T_COURSE ADD COLUMN AUDIT_DATE DATE;
ALTER TABLE T_COURSE ADD COLUMN AUDIT_REALNAME VARCHAR(75);
ALTER TABLE T_COURSE ADD COLUMN AUDIT_MSG VARCHAR(500);


-- 联合会会员表
CREATE TABLE T_USER_UNION_MEMBER
(
  ID                    INTEGER NOT NULL,
  USERID                INTEGER NOT NULL,           -- 用户id        
  REALNAME              VARCHAR(75),                -- 姓名
  UNION_TYPE            TINYINT DEFAULT 0 NOT NULL, -- 联合会员类型（1个人教育类，2个人装备类，3团体教育类，4团体教育类）
  UNION_CODE            VARCHAR(20),                -- 会员编号
  SEX                   TINYINT DEFAULT 0,          -- 性别（0未知，1男，2女）
  NATION                VARCHAR(20),                -- 民族
  BIRTHDAY              DATE,                       -- 生日
  DEGREE                VARCHAR(20),                -- 学历
  GRADUATE_SCHOOL       VARCHAR(75),                -- 毕业院校
  MAJOR                 VARCHAR(75),                -- 专业
  TEACH_YEAR            FLOAT(5,1) DEFAULT 0,       -- 教学年资（如：1.5年）
  POLITICAL             VARCHAR(20),                -- 政治面貌
  EMAIL                 VARCHAR(50),                -- EMAIL
  WORK_UNIT             VARCHAR(75),                -- 工作单位
  WORK_DEPT             VARCHAR(75),                -- 工作部门
  WORK_DUTY             VARCHAR(75),                -- 工作职务
  TELEPHONE             VARCHAR(20),                -- 联系电话
  REGIONCODE            VARCHAR(6),                 -- 所在地区（6位行政区划）
  ADDRESS               VARCHAR(100),               -- 单位地址
  INTRO                 VARCHAR(4000),              -- 个人介绍及工作履历/机构介绍
  OPINION               VARCHAR(500),               -- 单位推荐意见
  PHOTO_FILE            VARCHAR(500),               -- 个人照片/营业执照路径(个人照片:upload/union/{年}/{月}/{日}/{id}/{id}_photo.{扩展名}?t=timestamp;营业执照:upload/union/{年}/{月}/{日}/{id}/营业执照_{会员类型name}_{姓名}.{扩展名}?t=timestamp)
  PAPER_FILE            VARCHAR(100),               -- 推荐表扫描件路径(upload/union/{年}/{月}/{日}/{id}/推荐表_{会员类型name}_{姓名}.{扩展名}?t=timestamp)
  AGENCY_NAME           VARCHAR(100),               -- 机构名称
  AGENCY_ADDRESS        VARCHAR(100),               -- 注册地址
  AGENCY_YEAR           FLOAT(5,1) DEFAULT 0,       -- 经营年期
  AGENCY_FUND           VARCHAR(20),                -- 注册资金（如：100万）
  AGENCY_BRANCH_NUM     INTEGER,                    -- 分店数量
  AGENCY_BRAND          VARCHAR(75),                -- 品牌名称
  AGENCY_MODE           TINYINT DEFAULT 0,          -- 机构类别（1生产/2销售）
  AGENCY_FEATURE        VARCHAR(500),               -- 优势特色
  AGENCY_EMPLOYEE_NUM   INTEGER,                    -- 教师人数（员工数量）
  AGENCY_TRAINEE_NUM    INTEGER,                    -- 学员人数
  AGENCY_PRINCIPAL      VARCHAR(75),                -- 负责人姓名
  APPLY_DATE            DATE,                       -- 提交申请日期
  AUDIT_STATUS          TINYINT DEFAULT 0 NOT NULL, -- 审核状态（0未提交申请，1审核中，2审核通过，3审核不通过）
  AUDIT_DATE            DATE,                       -- 审核日期
  AUDIT_REALNAME        VARCHAR(75),                -- 审核人姓名
  AUDIT_MSG             VARCHAR(4000),              -- 审核信息
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 点播课程的视频文件
CREATE TABLE T_COURSE_VIDEO
(
  ID                    INTEGER NOT NULL AUTO_INCREMENT,
  COURSEID              INTEGER,                    -- 课程或音乐会ID
  COURSE_TIMEID         INTEGER DEFAULT 0,          -- 课次/场次ID（=T_COURSE_TIME.ID）
  VIDEO_DURATION        INTEGER,                    -- 视频时长（分钟）
  VIDEO_FILE            VARCHAR(225),               -- 视频路径（upload/video/{年}/{月}/{日}/{id}/原文件名.扩展名）
  FILE_NAME             VARCHAR(500),               -- 文件名字
  FILE_EXT              VARCHAR(50),                -- 文件类型（扩展名）
  FILE_SIZE             INTEGER DEFAULT 0,          -- 视频文件大小（单位：字节）
  PREDICT_TIME          DATETIME,                   -- 预计更新时间
  MODIFY_TIME           DATETIME,                   -- 修改时间
  PLAY_NUM              INTEGER DEFAULT 0,          -- 点播次数
  CONVERT_STATUS        INTEGER DEFAULT 0,          -- 视频转换状态（0：无需转换，1：转换中，2：转换成功，3：转换失败）
  CONVERT_ID            VARCHAR(32),                -- 视频转换任务GUID
  CONVERT_RUN_TIME      DATETIME,                   -- 转换开始时间
  CONVERT_END_TIME      DATETIME,                   -- 转换结束时间
  CONVERT_MSG           VARCHAR(1000),              -- 转换结果信息
  REMARK                VARCHAR(225),               -- 说明
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;



-- 新建表索引
-- 用户id和会员类型唯一索引
CREATE UNIQUE INDEX IDX_T_USER_UNION_MEMBER_USERID_UNIONTYPE
    ON T_USER_UNION_MEMBER(USERID, UNION_TYPE);
CREATE INDEX IDX_T_USER_UNION_MEMBER_USERID
    ON T_USER_UNION_MEMBER(USERID);
-- 点播课程的视频文件
CREATE INDEX IDX_T_COURSE_VIDEO_COURSEID
    ON T_COURSE_VIDEO(COURSEID);    
CREATE INDEX IDX_T_COURSE_VIDEO_COURSE_TIMEID
    ON T_COURSE_VIDEO(COURSE_TIMEID);
  
    
-- 创建t_user_union_member主键生成表 t_user_union_member
CREATE TABLE t_seq_user_union_member (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name  VARCHAR(50) NOT NULL default '',
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

-- ------------------------------------执行2 dml -------------------------------------
-- 联合会会员模块
delete from T_SYSTEM_MODULE where id in(1200800, 1200801, 1200802, 1200803, 1200804, 1200805);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200800, 1, '联合会会员', '/basic/unionMemberManage.htm', null, 1, 1200000, 0, 0, 1200800);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200801, 1, '查看', '/basic/viewUnionMember.htm', null, 0, 1200800, 0, 1, 1200801);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200802, 1, '新增', '/basic/addUnionMember.htm', null, 0, 1200800, 0, 1, 1200802);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200803, 1, '修改', '/basic/editUnionMember.htm', null, 0, 1200800, 0, 1, 1200803);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200804, 1, '删除', '/basic/delUnionMember.htm', null, 0, 1200800, 0, 1, 1200804);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200805, 1, '审核', '/basic/auditUnionMember.htm', null, 0, 1200800, 0, 1, 1200805);

-- 点播课程模块
delete from T_SYSTEM_MODULE where id in(1300300, 1300301, 1300302, 1300303);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300300, 1, '点播课程', '/vod/vodManage.htm', null, 1, 1300000, 0, 0, 1300300);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300301, 1, '新增', '/vod/addVod.htm', null, 0, 1300300, 0, 1, 1300301);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300302, 1, '修改', '/vod/editVod.htm', null, 0, 1300300, 0, 1, 1300302);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300303, 1, '删除', '/vod/delVod.htm', null, 0, 1300300, 0, 1, 1300303);

-- 音乐导师审核模块
delete from T_SYSTEM_MODULE where id in(1200700, 1200701, 1200702, 1200703, 1200704);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200700, 1, '音乐导师', '/basic/musicTutorManage.htm', null, 1, 1200000, 0, 0, 1200700);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200701, 1, '查看', '/basic/viewMusicTutor.htm', null, 0, 1200700, 0, 1, 1200701);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200702, 1, '新增', '/basic/addMusicTutor.htm', null, 0, 1200700, 0, 1, 1200702);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200703, 1, '修改', '/basic/editMusicTutor.htm', null, 0, 1200700, 0, 1, 1200703);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200704, 1, '审核', '/basic/auditMusicTutor.htm', null, 0, 1200700, 0, 1, 1200704);

-- 音乐导师推荐模块
delete from T_SYSTEM_MODULE where id in(1400550);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400550, 1, '音乐导师推荐', '/homepage/tutor/tutorRec.htm', null, 1, 1400000, 0, 0, 1400550);


-- 2016-05-25打包，屏蔽模块
update T_SYSTEM_MODULE set isshow = 0 where id in(1300300, 1200700, 1400550);


-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;


-- ------------------------------------ 版本号记录 -------------------------------------
delete from T_SYSTEM_VERSION where id=9;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(9, '标准版', '音乐联盟', '添加点播、音乐导师认证和申请开课以及联合会员申请等', now(), '1.4.0.0', '', null);
commit;

