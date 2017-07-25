-- 1.5.0.0hifi

-- ------------------------------------执行1 ddl -------------------------------------
-- T_USER_BIND表添加对接NEW_USERID
ALTER TABLE T_USER_BIND ADD COLUMN NEW_USERID VARCHAR(50) AFTER USER_ID;


-- ------------------------------------执行2 dml -------------------------------------
-- 添加版本记录
delete from T_SYSTEM_VERSION where id=14;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(14, '标准版', '音乐联盟', 'hifi音乐', now(), '1.5.0.0', '20161024', null);
commit;
