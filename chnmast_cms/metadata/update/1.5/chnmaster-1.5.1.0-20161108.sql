-- 1.5.1.0 视频回顾、在线评论

-- ------------------------------------执行1 ddl -------------------------------------
-- 评论表添加评论内容类型
ALTER TABLE T_COURSE_COMMENT ADD COLUMN COMMENT_TYPE TINYINT DEFAULT 1 NOT NULL AFTER ID;

-- T_INDEX_INFO表添加SHARE_PIC_FILE，分享时图片路径（upload/info/{年}/{月}/{日}/{timestamp}.{扩展名}?t=timestamp）
ALTER TABLE T_INDEX_INFO ADD COLUMN SHARE_PIC_FILE VARCHAR(200) AFTER INFO_DATE;


-- ------------------------------------执行2 dml -------------------------------------
-- 首页管理-添加视频回顾模块
delete from T_SYSTEM_MODULE where id in(1400800, 1400801, 1400802, 1400803);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400800, 1, '视频回顾', '/homepage/info/videoReManage.htm', null, 1, 1400000, 0, 0, 1400800);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400801, 1, '新增', '/homepage/info/videoReAdd.htm', NULL, 1, 1400800, 0, 1, 1400801);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400802, 1, '修改', '/homepage/info/videoReEdit.htm', NULL, 1, 1400800, 0, 1, 1400802);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400803, 1, '删除', '/homepage/info/videoReDel.htm', NULL, 1, 1400800, 0, 1, 1400803);

-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;


-- dispatch开放操作功能
update T_SYSTEM_CONFIG set can_edit = 1 where code = 'dispatch.switch';


-- 添加版本记录
delete from T_SYSTEM_VERSION where id=15;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(15, '标准版', '音乐联盟', '视频回顾、在线评论', now(), '1.5.1.0', '20161111', null);
commit;
