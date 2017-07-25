-- 1.4.1.1 （课程评论功能）

-- ------------------------------------执行1 ddl -------------------------------------
-- 课程评论表
CREATE TABLE T_COURSE_COMMENT(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  COURSEID          INTEGER NOT NULL,           -- 课程ID
  TEA_ID            INTEGER,                    -- 主讲ID
  OBS_ID            INTEGER,                    -- 评论人ID
  OBS_NAME          VARCHAR(50),                -- 评论人姓名
  IP                VARCHAR(50),                -- 评论人IP
  SCORE             INTEGER,                    -- 评分（星级评分1-5分）
  CONTENT           VARCHAR(500),               -- 评论内容
  COMMENTTIME       DATETIME,                   -- 评论时间
  AUDIT_STATUS      TINYINT DEFAULT 2 NOT NULL, -- 审核状态（2审核通过，3审核不通过）
  AUDIT_DATE        DATETIME,                   -- 审核日期
  AUDIT_REALNAME    VARCHAR(75),                -- 审核人姓名
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- T_USER_TEACHER表添加字段：个人空间是否已经显示过引导指示
ALTER TABLE T_USER_TEACHER ADD COLUMN SHOW_GUIDE TINYINT DEFAULT 0;


-- ------------------------------------执行2 dml -------------------------------------
-- 模块新调整
delete from T_SYSTEM_MODULE where id in(1250000, 1250300, 1250400);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250000, 1, '审核管理', null, null, 1, 0, 0, 0, 1250000);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250300, 1, '导师审核', '/audit/tutorAuditManage.htm', null, 1, 1250000, 0, 0, 1250300);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250400, 1, '评论审核', '/audit/commentAuditManage.htm', null, 1, 1250000, 0, 0, 1250400);

-- 加入联盟
-- 1、删除原模块
delete from T_SYSTEM_MODULE where id in(1200500, 1200501, 1200502, 1200503, 1200504);
-- 2、添加新模块
delete from T_SYSTEM_MODULE where id in(1250100, 1250101, 1250102, 1250103, 1250104);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250100, 1, '加入联盟', '/basic/agencyJoinManage.htm', null, 1, 1250000, 0, 0, 1250100);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250101, 1, '查看', '/basic/viewAgencyJoin.htm', null, 1, 1250100, 0, 1, 1250101);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250102, 1, '修改', '/basic/editAgencyJoin.htm', null, 1, 1250100, 0, 1, 1250102);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250103, 1, '删除', '/basic/delAgencyJoin.htm', null, 1, 1250100, 0, 1, 1250103);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250104, 1, '同意加盟', '/basic/checkAgencyJoin.htm', null, 1, 1250100, 0, 1, 1250104);

-- 联合会会员
-- 1、删除原模块
delete from T_SYSTEM_MODULE where id in(1200800, 1200801, 1200802, 1200803, 1200804, 1200805);
-- 2、添加新模块
delete from T_SYSTEM_MODULE where id in(1250200, 1250201, 1250202, 1250203, 1250204, 1250205);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250200, 1, '联合会会员', '/basic/unionMemberManage.htm', null, 1, 1250000, 0, 0, 1250200);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250201, 1, '查看', '/basic/viewUnionMember.htm', null, 0, 1250200, 0, 1, 1250201);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250202, 1, '新增', '/basic/addUnionMember.htm', null, 0, 1250200, 0, 1, 1250202);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250203, 1, '修改', '/basic/editUnionMember.htm', null, 0, 1250200, 0, 1, 1250203);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250204, 1, '删除', '/basic/delUnionMember.htm', null, 0, 1250200, 0, 1, 1250204);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250205, 1, '审核', '/basic/auditUnionMember.htm', null, 0, 1250200, 0, 1, 1250205);

-- 审核管理-导师审核 屏蔽
update T_SYSTEM_MODULE set isshow = 0 where id = 1250300;

-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;


-- ------------------------------------ 版本号记录 -------------------------------------
-- 补充1.4.1.0版本号
delete from T_SYSTEM_VERSION where id=10;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(10, '标准版', '音乐联盟', '安卓APP、ftp上传方式', now(), '1.4.1.0', '20160624', null);

-- 新版本号
delete from T_SYSTEM_VERSION where id=11;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(11, '标准版', '音乐联盟', '音乐网安卓APP、断点续传、课程评论', now(), '1.4.1.1', '20160701', null);
commit;