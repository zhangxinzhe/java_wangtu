-- 1.3.1.0版 修改语法

-- ------------------------------------执行1 ddl 添加表和索引-------------------------------------
-- 订单优惠记录表【1.3.1.0】
CREATE TABLE T_ORDER_ANNUL
(
  ID                    INTEGER NOT NULL AUTO_INCREMENT,
  ORDER_ID              INTEGER,                -- 订单ID=T_ORDER.ID
  ANNUL_TYPE            TINYINT DEFAULT 0,      -- 优惠类型（0无，10整课购买，11后台优惠，12优惠券）
  ANNUL_AMOUNT          FLOAT(12,2) DEFAULT 0,  -- 优惠金额
  OPE_USER_ID           INTEGER NOT NULL,       -- 操作人ID（系统优惠时添0）
  OPE_REAL_NAME         VARCHAR(75),            -- 操作人姓名
  OPE_TIME              DATETIME NOT NULL,      -- 记录产生时间
  REMARK                VARCHAR(1500),          -- 说明
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- 订单验证码【1.3.1.0】
CREATE TABLE T_ORDER_VERIFYCODE
(
  ID                    INTEGER NOT NULL AUTO_INCREMENT,
  ORDER_ID              INTEGER NOT NULL,       -- 订单ID=T_ORDER.ID
  ORDER_REPORT_ID       INTEGER NOT NULL,       -- 订单ID=T_ORDER_REPORT.ID
  COURSE_ID             INTEGER NOT NULL,       -- 课程ID
  COURSE_TIMEID         INTEGER NOT NULL,       -- 课次ID
  COURSE_CONTENT_TYPE   TINYINT DEFAULT 1,      -- 课程分类（1基地课程，2音乐会）
  VERIFY_CODE           INTEGER NOT NULL,       -- 验证码
  IS_USED               TINYINT DEFAULT 0,      -- 是否已使用（0未使用，1已使用）
  USED_DATE             DATETIME,               -- 验证码使用的时间
  REMARK                VARCHAR(1500),          -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- 微信在线支付
CREATE TABLE T_ORDER_WECHATPAY
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  ORDER_ID          INTEGER,                            -- 订单id=T_ORDER.ID
  TRADE_NO          VARCHAR(50) NOT NULL,               -- 交易号（32位uuid）
  TRANSACTION_ID    VARCHAR(32),                        -- 微信交易号
  PREPAY_ID         VARCHAR(64) NULL,                   -- 订单序列号ID（2小时有效）
  OPEN_ID           VARCHAR(128),                       -- 微信用户序列号
  TRADE_TYPE        VARCHAR(100) NULL,                  -- 支付方式
  CODE_URL          VARCHAR(255),                       -- 二维码支付链接
  FORM_STATE        TINYINT DEFAULT 0 NOT NULL,         -- 订单状态（0.待处理，1失败，2成功）
  FORM_AMOUNT       FLOAT(12,2) DEFAULT 0.00 NOT NULL,  -- 订单金额
  USERID            INTEGER NOT NULL,                   -- 用户ID
  CREATION_TIME     DATETIME NOT NULL,
  MODIFY_TIME       DATETIME NOT NULL,
  FORM_TYPE         TINYINT DEFAULT 0,                  -- 订单类型（0充值，1购物）
  ISDEAL            TINYINT DEFAULT 0,                  -- 微信回调状态，避免回调并发（1正在处理，0未在处理）
  OPERATOR_ID       INTEGER,                            -- 操作人id
  OPERATOR_NAME     VARCHAR(75),                        -- 操作人姓名
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ------------------新增表维护索引------------------
CREATE INDEX IDX_T_ORDER_ANNUL_ORDERID
    ON T_ORDER_ANNUL (ORDER_ID);
-- 创建订单验证码唯一索引
CREATE UNIQUE INDEX IDX_T_ORDER_VERIFYCODE_UN
    ON T_ORDER_VERIFYCODE (COURSE_ID, COURSE_TIMEID, VERIFY_CODE);
CREATE INDEX IDX_T_ORDER_WECHATPAY_TRADENO
    ON T_ORDER_WECHATPAY(TRADE_NO);




-- ------------------------------------执行2 dml 调整历史数据-------------------------------------
-- 优惠记录表中添加整套购买的优惠记录
INSERT INTO T_ORDER_ANNUL(ORDER_ID, ANNUL_TYPE, ANNUL_AMOUNT, OPE_USER_ID, OPE_TIME) 
SELECT ID, 10, ANNUL_AMOUNT, 0, CREATION_TIME 
FROM t_order o 
WHERE o.ANNUL_AMOUNT > 0 
AND o.BUY_TYPE = 0
AND NOT EXISTS ( SELECT 1 FROM T_ORDER_ANNUL WHERE o.id = order_id);





-- 后台模块调整
-- 删除未使用的模块功能
delete from T_SYSTEM_MODULE where id in (1500101, 1500102, 1500103);
-- 调整新模块
delete from T_SYSTEM_MODULE where id=1500300;
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1500300, 1, '票务验证', '/account/codeDeal.htm', null, 1, 1500000, 0, 0, 1500300);
delete from T_SYSTEM_MODULE where id=1500400;
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1500400, 1, '订单优惠', '/account/orderAnnul.htm', null, 1, 1500000, 0, 0, 1500400);
update T_SYSTEM_MODULE set name='订单查询' where id=1800300;
delete from T_SYSTEM_MODULE where id=1800400;
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800400, 1, '订单优惠查询', '/stat/orderAnnulQuery.htm', null, 1, 1800000, 0, 0, 1800400);
delete from T_SYSTEM_MODULE where id=1800700;
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800700, 1, '收支统计', '/stat/accountDetail.htm', null, 1, 1800000, 0, 0, 1800700);
update T_SYSTEM_MODULE set isshow = 0 where id=1800700;

-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;


-- 添加版本记录
delete from T_SYSTEM_VERSION where id=5;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(5, '标准版', '公司', '退课', now(), '1.3.1.0', '', null);
commit;

-- ------------------------------------执行3 ddl 修改表结构-------------------------------------
-- t_course表添加字段：退课截止前天数（单位：天）（如：1，购买的课次中第一个课次时间提前一天可退课）【1.3.1.0】
-- ALTER TABLE T_COURSE ADD COLUMN STOP_RETURN_DAYS TINYINT DEFAULT 0 AFTER COURSE_TYPE;

-- t_course表添加字段：绑定个人用户ID【1.3.1.0】
ALTER TABLE T_COURSE ADD COLUMN STUDENT_ID INTEGER DEFAULT 0 AFTER ASS_REALNAME;

-- t_order表删除优惠类型字段：ANNUL_TYPE【1.3.1.0】
ALTER TABLE T_ORDER DROP COLUMN ANNUL_TYPE;

-- t_course表添加字段：IS_COST_ANNUL，基地课程是否维护整套直播课程购买价格（0否，1是）
ALTER TABLE T_COURSE ADD COLUMN IS_COST_ANNUL TINYINT DEFAULT 0 AFTER COURSE_NOWCOST_VIP;

-- t_order修改CHECK_NUM类型
ALTER TABLE T_ORDER MODIFY CHECK_NUM INTEGER DEFAULT 0;



-- ------------------------------------执行4 dml 调整数据，维护新增字段数据-------------------------------------
-- 历史数据，基地课程默认需要维护整套直播课程购买价格
update T_COURSE set IS_COST_ANNUL=1 where CONTENT_TYPE=1






