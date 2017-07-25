-- 1.4.2.0 优惠券批量增加

-- ------------------------------------执行1 ddl -------------------------------------
-- T_COUPON添加：生成类型（0单张生成；1批量生成）
ALTER TABLE T_COUPON ADD COLUMN CREATE_TYPE INTEGER DEFAULT 0 AFTER NAME;
-- T_COUPON添加：批量生成数量
ALTER TABLE T_COUPON ADD COLUMN CREATE_NUM INTEGER DEFAULT 0 AFTER CREATE_TYPE;

-- T_COUPON添加：优惠类型（0折扣优惠；1金额优惠）
ALTER TABLE T_COUPON ADD COLUMN COUPON_TYPE INTEGER DEFAULT 0 AFTER COUPON_CODE;
-- T_COUPON添加：优惠金额
ALTER TABLE T_COUPON ADD COLUMN AMOUNT FLOAT(12,2) DEFAULT 0 AFTER DISCOUNT;

-- 优惠推广码
CREATE TABLE T_COUPON_CODE(
  ID                  INTEGER NOT NULL AUTO_INCREMENT,
  COUPON_ID           INTEGER NOT NULL,             -- 优惠卷ID
  COUPON_CODE         VARCHAR(20) NOT NULL,         -- 推广码（单张生成：批次编号+8位随机数；批量生成：批次编号+12位）
  PRIMARY KEY(ID)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- 优惠码创建规则（13-16位随机数）【1.4.2.0】
CREATE TABLE T_COUPON_CODE_RULE(
    ID                  INTEGER NOT NULL AUTO_INCREMENT,
    BATCH_CODE          CHAR(4) NOT NULL,           -- 批次编号（1-4位）
    CODE1               INTEGER NOT NULL,           -- 优惠码5-8位编号（规则号）
    CODE2               INTEGER NOT NULL,           -- 优惠码9-12位（向下减1-10以内的随机数）
    STATUS              TINYINT DEFAULT 0,          -- 优惠码规则状态，0表示可用，1表示无效
    PRIMARY KEY(ID)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ------------------------------------执行2 dml -------------------------------------
-- 优惠推广码表数据初始化
DELETE FROM t_coupon_code;
INSERT INTO t_coupon_code(COUPON_ID, COUPON_CODE) SELECT id, COUPON_CODE FROM t_coupon;

-- ------------------------------------执行3 ddl -------------------------------------
-- 删除优惠表优惠推广码字段
ALTER TABLE T_COUPON DROP COLUMN COUPON_CODE;




