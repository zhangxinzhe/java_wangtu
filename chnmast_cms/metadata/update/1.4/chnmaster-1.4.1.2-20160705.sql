-- 1.4.1.2

-- ------------------------------------执行1 ddl -------------------------------------
-- T_USER_TEACHER字段维护（证件照）
ALTER TABLE T_USER_TEACHER ADD COLUMN CARD_PHOTO_FILE VARCHAR(500) AFTER HAND_IDCARD_FILE;


-- ------------------------------------执行2 dml -------------------------------------
-- （测试环境-执行）
delete from T_SYSTEM_CONFIG where code='system_web_monitor';
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('system_web_monitor', 'web管理', 0, '后台首页是否显示web管理功能，0否，1是', 1, 1, 'config-int-range-0-1', 131);
commit;

-- （外网环境-执行）
-- delete from T_SYSTEM_CONFIG where code='system_web_monitor';
-- insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
-- values('system_web_monitor', 'web管理', 0, '后台首页是否显示web管理功能，0否，1是', 0, 0, 'config-int-range-0-1', 131);
-- commit;

-- ------------------------------------ 版本号记录 -------------------------------------
delete from T_SYSTEM_VERSION where id=12;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(12, '标准版', '音乐联盟', '音乐导师申请手机版', now(), '1.4.1.2', '20160706', null);
commit;