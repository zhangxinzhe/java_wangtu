-- 1.4.2.0（优惠券注销功能）

-- ------------------------------------执行1 ddl -------------------------------------
-- T_COUPON_CODE添加：状态是否注销（0否，1是）
ALTER TABLE T_COUPON_CODE ADD COLUMN IS_CANCEL INTEGER DEFAULT 0 AFTER COUPON_CODE;


-- ------------------------------------执行2 dml -------------------------------------
-- 优惠券码注销功能
delete from T_SYSTEM_MODULE where id = 1500905;
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500905, 1, '注销', '/account/cancelCoupon.htm', null, 0, 1500900, 0, 1, 1500905);


-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;