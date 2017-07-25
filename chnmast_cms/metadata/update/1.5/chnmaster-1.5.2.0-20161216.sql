-- 1.5.2.0

-- ------------------------------------执行1 ddl -------------------------------------



-- ------------------------------------执行2 dml -------------------------------------
-- web启动无限宝客户端时，添加支持网页直播功能开关
DELETE FROM T_SYSTEM_CONFIG WHERE CODE='vpweb.allowHLS';
INSERT INTO T_SYSTEM_CONFIG(CODE, NAME, VALUE, description, can_view, can_edit, validate, order_no)
VALUES('vpweb.allowHLS', '支持网页直播开关', '0', '设置是否支持网页直播功能 1表示允许，0或者不设置表示不允许', 1, 1, 'config-int-range-0-1', 220);

-- 进入无限宝网页版地址
DELETE FROM T_SYSTEM_CONFIG WHERE CODE='vpweb.invoke_url';
INSERT INTO T_SYSTEM_CONFIG(CODE, NAME, VALUE, description, can_view, can_edit, validate, order_no)
VALUES('vpweb.invoke_url', '进入无限宝网页版地址', '', '进入无限宝网页版地址（请维护完整地址，格式如：http://vpweb.netstudy5.dev/vpweb/invoke.htm）', 1, 1, 'validate-url', 221);


-- 无限宝IOS版下载链接
DELETE FROM T_SYSTEM_CONFIG WHERE CODE='wxb.ios_url';
INSERT INTO T_SYSTEM_CONFIG(CODE, NAME, VALUE, description, can_view, can_edit, validate, order_no)
VALUES('wxb.ios_url', '无限宝IOS版下载链接', 'https://itunes.apple.com/us/app/wu-xian-bao-yuan-cheng-jiao/id1126626050?l=zh&ls=1&mt=8', '无限宝IOS版下载链接地址', 1, 0, 'validate-url', 121);



-- 添加版本记录
delete from T_SYSTEM_VERSION where id=17;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(17, '标准版', '音乐联盟', '神州唱响、分享功能、无限宝网页版', now(), '1.5.2.0', '20161124', null);
commit;
