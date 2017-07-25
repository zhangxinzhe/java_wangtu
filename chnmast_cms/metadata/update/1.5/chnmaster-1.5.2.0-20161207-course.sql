-- 1、基地课程、点播课程设置整套购买开关；购买课程是否允许使用优惠券
-- 2、神州唱响职务可维护多职务

-- ------------------------------------执行1 ddl -------------------------------------
-- 删除字段（2016-04-08，此字段屏蔽）
alter table T_COURSE drop column IS_COST_ANNUL;

-- 添加字段，是否只允许购买整套课程（不允许课次购买）（0否，1是）
alter table T_COURSE add column IS_BUY_ALL TINYINT DEFAULT 0 after COURSE_NOWCOST_VIP;

-- 添加字段：是否允许使用优惠券购买（0否，1是）
alter table T_COURSE add column IS_USE_COUPON TINYINT DEFAULT 1 after COURSE_NOWCOST_VIP;



-- 组委会表修改字段长度
alter table T_COMPETE_COMMITTEE modify column JOB_TITLE VARCHAR(200);

-- ------------------------------------执行2 dml -------------------------------------
