-- 初始化后台管理员用户
delete from T_SYSTEM_USER where id in(1);
insert into T_SYSTEM_USER (id, username, realname, remark, bindingemail, password, createtime, usertype, isfreeze, isdeleted)
values (1, 'chnmaster', '超级管理员', null, null, 'e10adc3949ba59abbe56e057f20f883e', now(), 1, 0, 0);
commit;


-- 初始化系统角色
delete from T_SYSTEM_ROLE where id in (1);
insert into T_SYSTEM_ROLE (id, name, description, createtime, roletype)
values (1, '超级管理员角色', '超级管理员角色', now(), 1);
commit;


-- 初始化系统用户角色
delete from T_SYSTEM_USER_ROLE where userid in (1);
insert into T_SYSTEM_USER_ROLE (userid, roleid)
values (1, 1);
commit;


-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;



-- 初始化系统参数
delete from T_SYSTEM_CONFIG;
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('site.home_site_name', '前台名称', '中国音乐大师网', '显示在前台页面上的标题名称', 1, 1, 'config-max-length-50', 10);
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('site.cms_site_name', '后台名称', '中国音乐大师网-后台管理', '后台管理系统名称，显示在页面上的标题名称', 1, 1, 'config-max-length-50', 20);
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('domain.home', '前台域名地址', 'http://www.chnmaster.com', '前台主应用访问地址，必须是域名，必须以http://开头', 1, 1, 'validate-url', 30);
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('domain.cms', '后台域名地址', 'http://cms.chnmaster.com', '后台管理系统访问地址，必须是域名，必须以http://开头', 1, 1, 'validate-url', 40);
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('domain.file', '系统静态文件域名地址', 'http://file.chnmaster.com', '静态文件（如js,css,image等）的访问地址，必须是域名，必须以http://开头', 1, 1, 'validate-url', 50);
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('domain.upload_file', '用户上传文件域名地址', 'http://uploadfile.chnmaster.com', '用户上传文件（如upload目录）和发布时自带的系统文件（如sysfile目录）访问地址，必须是域名，必须以http://开头', 1, 1, 'validate-url', 60);
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('dispatch.switch', '调度中心开关', '1', '课程编号是否统一由调度中心来分配，0否，1是', 1, 0, 'config-int-range-0-1', 70);
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('dispatch.domain', '调度中心地址', 'http://dispatch.kehou.com', '调度中心应用的访问地址，从调度中心获取课程编号时使用', 1, 1, 'validate-url', 80);
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('wxb.course_before_time', '课堂提前时间','15','可提前多长时间进入无限宝课堂（单位：分钟）', '1', '1', 'config-int-range-0-600', 90);
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('wxb.course_delay_time', '课堂延迟时间','15','可延迟多长时间结束无限宝课堂（单位：分钟）', '1', '1', 'config-int-range-0-600', 100);
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('wxb.update', '无限定是否自动升级', '1', '无限定客户端和辅助登录工具是否自动升级，0不升级，1升级', 0, 0, 'config-int-range-0-1', 110);
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('wxb.call_record', '是否记录无限宝回调日志', '1', '无限宝向web端发送数据时，web端本地是否记录log4j日志。0不记录，1记录', 1, 1, 'config-int-range-0-1', 120);
INSERT INTO T_SYSTEM_CONFIG(CODE, NAME, VALUE, DESCRIPTION, CAN_VIEW, can_edit, validate, order_no)
values('system.stat_script', '访问量统计脚本', '', 'cnzz或其他第三方网站访问量的统计脚本', 1, 1, '', 300);
commit;


-- 添加版本记录
delete from T_SYSTEM_VERSION where id=1;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(1, '标准版', '公司', '全新开发', now(), '1.0.0.0', '151026', null);
commit;



