-- 1.4.0.0 优化语法

-- ------------------------------------执行1 ddl -------------------------------------



-- ------------------------------------执行2 dml -------------------------------------
-- 开放模块（chnmaster-1.4.0.0-20160511.sql屏蔽）点播课程模块
update T_SYSTEM_MODULE set isshow = 1 where id in(1300300);

update T_SYSTEM_MODULE set name = '课程管理' where id = 1300000;
update T_SYSTEM_MODULE set name = '点播视频' where id = 1300300;

-- 首页管理-点播课程推荐
delete from T_SYSTEM_MODULE where id in(1400350);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400350, 1, '点播视频推荐', '/homepage/vod/vodRec.htm', null, 1, 1400000, 0, 0, 1400350);

-- 业务管理-选课报名
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1500600, 1, '选课报名', '/account/stuReport.htm', null, 1, 1500000, 0, 0, 1500600);


-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;

commit;


