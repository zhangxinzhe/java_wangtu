-- 1.3.0.0版  中国音乐大师网关联学校功能需求开发（tag1.3.0.0）

-- 学校（id=1，保留id，用其他学校）
CREATE TABLE T_SCHOOL
(
  ID                    INTEGER NOT NULL,
  SCHOOL_NAME           VARCHAR(75),        -- 学校名称
  SHORTSPELL            VARCHAR(50),        -- 学校简拼
  REGIONCODE            VARCHAR(6) NULL,    -- 所在地区（6位行政区划）
  CONTACT_MAN           VARCHAR(100),       -- 联系人
  CONTACT_PHONE         VARCHAR(100),       -- 联系电话
  EMAIL                 VARCHAR(50),        -- Email
  ADDRESS               VARCHAR(100),       -- 详细地址
  INTRODUCTION          VARCHAR(4000),      -- 介绍
  IS_CANCEL             TINYINT DEFAULT 0 NOT NULL,  -- 是否注销（0正常，1已注销）
  CREATETIME            DATETIME,
  REMARK                VARCHAR(4000),      -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- 初始化t_school表数据
delete from T_SCHOOL where id=1; 
insert into T_SCHOOL (id, SCHOOL_NAME, REMARK) values (1, '其他学校', '其他学校');
commit;

-- t_user表添加字段：学校id
ALTER TABLE T_USER ADD COLUMN SCHOOL_ID INTEGER AFTER SEX;


-- 调整模块
delete from T_SYSTEM_MODULE where id=1200600;
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200600, 1, '学校维护', '/basic/schoolManage.htm', null, 1, 1200000, 0, 0, 1200600);

delete from T_SYSTEM_MODULE where id=1200601;
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200601, 1, '新增', '/basic/schoolAdd.htm', null, 1, 1200600, 0, 1, 1200601);
delete from T_SYSTEM_MODULE where id=1200602;
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200602, 1, '修改', '/basic/schoolEdit.htm', null, 1, 1200600, 0, 1, 1200602);
delete from T_SYSTEM_MODULE where id=1200603;
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200603, 1, '删除', '/basic/schoolDelete.htm', null, 1, 1200600, 0, 1, 1200603);
delete from T_SYSTEM_MODULE where id=1200604;
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200604, 1, '导入', '/basic/schoolImport.htm', null, 1, 1200600, 0, 1, 1200604);

-- 学校维护模块，屏蔽
update T_SYSTEM_MODULE set isshow = 0 where id = 1200600;
commit;


-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;


-- t_index_info 表添加字段：来源：come_from，来源的链接：come_url,日期：info_date 编辑：editor
ALTER TABLE T_INDEX_INFO ADD COLUMN EDITOR VARCHAR(50) AFTER ORDER_NO;
ALTER TABLE T_INDEX_INFO ADD COLUMN COME_FROM VARCHAR(150) AFTER EDITOR;
ALTER TABLE T_INDEX_INFO ADD COLUMN COME_URL VARCHAR(150) AFTER COME_FROM;
ALTER TABLE T_INDEX_INFO ADD COLUMN INFO_DATE DATETIME AFTER COME_URL;





