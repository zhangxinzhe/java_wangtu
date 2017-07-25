-- 页脚链接

-- ------------------------------------执行1 ddl -------------------------------------
-- 首页页脚链接
CREATE TABLE T_INDEX_FOOTER
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  TITLE             VARCHAR(450) NOT NULL,          -- 标题
  CONTENT_TYPE      TINYINT NOT NULL,               -- 链接类型（1外部链接、2维护内容）
  LINK_URL          VARCHAR(200),                   -- 链接地址
  LOCAL_FILE        VARCHAR(200),                   -- 文件路径（upload/footer/{年}/{月}/{日}/{timestamp}.txt）
  IS_SHOW           TINYINT DEFAULT 0 NOT NULL,     -- 是否显示（0不显示，1显示）
  DISPLAY_ORDER     INTEGER DEFAULT 0,              -- 显示次序
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;



-- ------------------------------------执行2 dml -------------------------------------
-- 添加页脚链接模块
DELETE FROM T_SYSTEM_MODULE WHERE id = 1400950;
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400950, 1, '页脚链接', '/homepage/index/footerManage.htm', null, 1, 1400000, 0, 0, 1400950);

-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;


-- 页脚栏目初始化
insert into T_INDEX_FOOTER (title, content_type, link_url, local_file, is_show, display_order)
values ('关于我们', '1', 'http://www.chnmaster.com/aboutus.htm', '', '1', '1');
insert into T_INDEX_FOOTER (title, content_type, link_url, local_file, is_show, display_order)
values ('加入我们', '1', 'http://www.chnmaster.com/joinus.htm', '', '1', '2');
insert into T_INDEX_FOOTER (title, content_type, link_url, local_file, is_show, display_order)
values ('联系我们', '2', '', '', '1', '3');
insert into T_INDEX_FOOTER (title, content_type, link_url, local_file, is_show, display_order)
values ('帮助中心', '1', 'http://www.chnmaster.com/help.htm', '', '1', '4');
insert into T_INDEX_FOOTER (title, content_type, link_url, local_file, is_show, display_order)
values ('联盟通讯平台', '1', 'http://www.chnmaster.com/touch.htm', '', '1', '5');
commit;

