-- 1.5.1.1（Hifi会员购买）（完善1.5.0.0）

-- ------------------------------------S 开发、测试、外网 不同环境，语法分别执行-------------------------------------
-- 开发环境执行
-- DELETE FROM T_SYSTEM_CONFIG WHERE CODE='chnmaster.hifi_domain';
-- INSERT INTO T_SYSTEM_CONFIG(CODE, NAME, VALUE, description, can_view, can_edit, validate, order_no)
-- VALUES('chnmaster.hifi_domain', 'Hifi接口主域名', 'http://if2.zhenxian.fm/interfacetest2', '开发环境，hifi测试接口', 0, 0, '', 500);

-- 测试环境执行
DELETE FROM T_SYSTEM_CONFIG WHERE CODE='chnmaster.hifi_domain';
INSERT INTO T_SYSTEM_CONFIG(CODE, NAME, VALUE, description, can_view, can_edit, validate, order_no)
VALUES('chnmaster.hifi_domain', 'Hifi接口主域名', 'http://if2.zhenxian.fm/interfacetest2', '测试环境，hifi测试接口', 0, 0, '', 500);

-- 外网环境执行
-- DELETE FROM T_SYSTEM_CONFIG WHERE CODE='chnmaster.hifi_domain';
-- INSERT INTO T_SYSTEM_CONFIG(CODE, NAME, VALUE, description, can_view, can_edit, validate, order_no)
-- VALUES('chnmaster.hifi_domain', 'Hifi接口主域名', 'http://if2.zhenxian.fm/interface2', '外网环境，hifi正式接口', 0, 0, '', 500);
-- ------------------------------------E 开发、测试、外网 不同环境，语法分别执行-------------------------------------


-- ------------------------------------以下语法，开发、测试、外网 不同环境，共执行 -------------------------------------
-- ------------------------------------执行1 ddl -------------------------------------
-- 是否Hifi会员（0.否，1.是）【1.5.1.1】
ALTER TABLE T_USER ADD IS_HIFI_MEMBER TINYINT DEFAULT 0 NOT NULL after IS_TUTOR; 
-- Hifi会员到期日期（2016-12-12 23:59:59）【1.5.1.1】
ALTER TABLE T_USER ADD HIFI_MEMBER_DATE DATETIME after IS_HIFI_MEMBER; 


-- 订单表添加hifi订单id和检查hifi订单状态
ALTER TABLE T_ORDER ADD HIFI_ORDER VARCHAR(50) after ORDER_KIND;
ALTER TABLE T_ORDER ADD HIFI_CHECK_STATUS TINYINT DEFAULT 0 after HIFI_ORDER;



-- ------------------------------------执行2 dml -------------------------------------
-- Hifi会员包月价
DELETE FROM T_SYSTEM_CONFIG WHERE CODE='hifi.member_monthly_price';
INSERT INTO T_SYSTEM_CONFIG(CODE, NAME, VALUE, description, can_view, can_edit, validate, order_no)
VALUES('hifi.member_monthly_price', 'Hifi会员包月价', '5', 'Hifi会员包月价，单位元，大于0', 1, 1, '', 501);


-- Hifi管理模块
DELETE FROM T_SYSTEM_MODULE WHERE id = 1501000;
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1501000, 1, 'HIFI会员管理', '/account/hifiMemberManage.htm', null, 1, 1500000, 0, 0, 1501000);


-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;


-- 添加版本记录
delete from T_SYSTEM_VERSION where id=16;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(16, '标准版', '音乐联盟', 'Hifi会员购买过程', now(), '1.5.1.1', '20161125', null);
commit;