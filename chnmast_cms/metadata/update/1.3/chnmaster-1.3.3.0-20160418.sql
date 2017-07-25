-- 1.3.3.0版 初始化语法（推广码功能）

-- ------------------------------------执行1 ddl 表、索引、函数-------------------------------------

-- ------1 新增表------
-- 优惠推广码表
CREATE TABLE T_COUPON
(
  ID            INTEGER NOT NULL AUTO_INCREMENT,
  NAME          VARCHAR(75),                -- 推广码名称
  BATCH_CODE    CHAR(4) NOT NULL,           -- 批次编号（4位，如1601、1602）
  COUPON_CODE   VARCHAR(20) NOT NULL,       -- 推广码（批次编号+8位随机数）
  DISCOUNT      FLOAT(12,2) DEFAULT 1,      -- 折扣比例
  USE_TYPE      INTEGER NOT NULL DEFAULT 0, -- 使用类型（0多次使用，1只能使用一次）
  BEGIN_TIME    DATETIME,                   -- 使用开始时间（默认00:00:00）
  END_TIME      DATETIME,                   -- 使用截至时间（默认23:59:59）
  OWNER_NAME    VARCHAR(75),                -- 推广人姓名
  CREATE_TIME   DATETIME NOT NULL,          -- 创建时间
  REMARK        VARCHAR(4000),              -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- ------2 新增表索引------
CREATE INDEX IDX_T_COUPON_COUPONCODE 
    ON T_COUPON(COUPON_CODE);


-- ------3 涉及表结构改造------
-- 订单优惠记录表T_ORDER_ANNUL增加优惠码字段：优惠码（优惠类型为12优惠券，存储优惠码；其他类型为空）
ALTER TABLE T_ORDER_ANNUL ADD COLUMN COUPON_CODE VARCHAR(20) AFTER ANNUL_TYPE;

-- 金额变动流水记录表 添加商品类型字段
ALTER TABLE T_ORDER_FUNDS ADD COLUMN WARE_TYPE TINYINT DEFAULT 0 AFTER RELATION_ID;




-- ------------------------------------执行2 dml 调整数据-------------------------------------
-- 添加模块
delete from T_SYSTEM_MODULE where id in(1500900,1500901,1500902,1500903);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1500900, 1, '优惠券管理', '/account/couponManage.htm', null, 1, 1500000, 0, 0, 1500900);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500901, 1, '（批量）新增', '/account/addCoupon.htm', null, 0, 1500900, 0, 1, 1500901);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500902, 1, '修改', '/account/editCoupon.htm', null, 0, 1500900, 0, 1, 1500902);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500903, 1, '删除', '/account/delCoupon.htm', null, 0, 1500900, 0, 1, 1500903);
-- 开放收支统计模块
update T_SYSTEM_MODULE set isshow = 1 where id=1800700;
-- 删除订单优惠查询模块
delete from T_SYSTEM_MODULE where id = 1800400;

-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;


-- 收支记录新加商品类型字段 数据调整
UPDATE T_ORDER_FUNDS a SET a.WARE_TYPE = (SELECT b.WARE_TYPE FROM t_order_detail b WHERE a.RELATION_ID = b.ORDER_ID LIMIT 1) WHERE a.DETAIL_TYPE = 0;
UPDATE t_order_funds f, t_order o, t_order_detail od SET f.REMARK = CONCAT( '网上支付，', '购买基地课程【', od.WARE_NAME, '】') WHERE f.relation_id = o.id AND o.ID = od.ORDER_ID AND f.DETAIL_TYPE = 0 AND f.WARE_TYPE = 1;
UPDATE t_order_funds f, t_order o, t_order_detail od SET f.REMARK = CONCAT( '网上支付，', '购买音乐会【', od.WARE_NAME, '】') WHERE f.relation_id = o.id AND o.ID = od.ORDER_ID AND f.DETAIL_TYPE = 0 AND f.WARE_TYPE = 2;
UPDATE t_order_funds f, t_order o, t_order_detail od SET f.REMARK = CONCAT( '网上支付，', '报名活动专题【', od.WARE_NAME, '】') WHERE f.relation_id = o.id AND o.ID = od.ORDER_ID AND f.DETAIL_TYPE = 0 AND f.WARE_TYPE = 3;
commit;

-- ------------------------------------ 版本号记录 -------------------------------------
delete from T_SYSTEM_VERSION where id=7;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(7, '标准版', '公司', '1310、1320、推广码', now(), '1.3.3.0', '', null);
commit;

