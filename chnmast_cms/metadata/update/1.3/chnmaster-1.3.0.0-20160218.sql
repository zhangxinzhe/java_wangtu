-- 1.3.0.0版 修改语法

-- ------------------------------------添加字段-------------------------------------
-- t_user表添加字段：是否会员（0.普通用户，1.会员用户）
ALTER TABLE T_USER ADD COLUMN IS_MEMBER  tinyint NOT NULL DEFAULT 0 AFTER IS_CANCEL;
-- t_user表添加字段：头像原图地址
ALTER TABLE T_USER ADD COLUMN AVATAR_ORIGINAL_FILE VARCHAR(500) AFTER AVATAR_FILE;

-- t_index_info表添加字段：是否紧急（0.否，1.是）
ALTER TABLE T_INDEX_INFO ADD COLUMN IS_URGENT tinyint NOT NULL DEFAULT 0 AFTER IS_LIGHT;

-- t_course_price表添加字段：vip价格
ALTER TABLE T_COURSE_PRICE ADD COLUMN NOWPRICE_VIP FLOAT(12,2) DEFAULT 0 AFTER NOWPRICE;
-- t_course_price表添加字段：已购买票数
ALTER TABLE T_COURSE_PRICE ADD COLUMN PAIN_NUM INTEGER DEFAULT 0 AFTER NUM;

-- t_order 表添加字段：付款设备，购买类型
ALTER table T_ORDER ADD COLUMN PAY_DEVICE TINYINT NOT NULL DEFAULT 0 AFTER MODIFY_DEVICE;
ALTER table T_ORDER ADD COLUMN BUY_TYPE TINYINT DEFAULT 0 AFTER ANNUL_TYPE;

-- T_COURSE表添加字段：原价套价、现价套价、现价会员套价
ALTER TABLE T_COURSE ADD COLUMN COURSE_OLDCOST FLOAT(12,2) DEFAULT 0 AFTER ENDTIME;
ALTER TABLE T_COURSE ADD COLUMN COURSE_NOWCOST FLOAT(12,2) DEFAULT 0 AFTER COURSE_OLDCOST;
ALTER TABLE T_COURSE ADD COLUMN COURSE_NOWCOST_VIP FLOAT(12,2) DEFAULT 0 AFTER COURSE_NOWCOST;

-- T_ORDER_DETAIL添加字段：数量、应付单价、实付单价
ALTER TABLE T_ORDER_DETAIL ADD COLUMN WARE_NUM INTEGER DEFAULT 1 AFTER WARE_NAME;
ALTER TABLE T_ORDER_DETAIL ADD COLUMN PAYABLE_PER FLOAT(12,2) DEFAULT 0 AFTER WARE_NUM;
ALTER TABLE T_ORDER_DETAIL ADD COLUMN ACTUAL_PER FLOAT(12,2) DEFAULT 0 AFTER PAYABLE_PER;


-- t_course表添加字段：推荐排序号
ALTER TABLE T_COURSE ADD COLUMN RECOMMENDSEQ TINYINT DEFAULT 0 AFTER RECOMMENDTIME;
-- T_USER_TEACHER表添加字段：推荐排序号
ALTER TABLE T_USER_TEACHER ADD COLUMN RECOMMENDSEQ TINYINT DEFAULT 0 AFTER RECOMMENDTIME;


-- ------------------------------------创建支付和退课等相关表-------------------------------------
-- 退课退费表
CREATE TABLE T_ORDER_RETURN
(
  ID                    INTEGER NOT NULL,
  ORDER_ID              INTEGER,                        -- 订单ID=T_ORDER.ID
  DETAIL_ID             INTEGER,                        -- 订单ID=T_ORDER_DETAIL.ID
  USERID                INTEGER,                        -- 退课申请人ID
  REALNAME              VARCHAR(75),                    -- 退课申请人姓名
  CREATE_TIME           DATETIME NULL,                  -- 退课发起时间  
  REASON                TINYINT DEFAULT 0,              -- 退课原因编码（0其他原因，1报错课，2电脑、网络问题上不了课，3课程不满意）
  REASON_DES            VARCHAR(385),                   -- 退课其他原因（REASON中选0时，此字段输入其他原因）
  STATUS                TINYINT DEFAULT 10 NULL,        -- 退课状态（10退课处理中，20退课成功，30退课失败，40退费处理中，50退费成功，60退费失败）
  STATUS_TYPE           TINYINT DEFAULT 0 NULL,         -- 状态类型（21退课成功-无需审核，22退课成功-管理员审核，51退费成功-系统处理，52退费成功-人工打款）
  COURSE_USERID         INTEGER,                        -- 退课处理人ID
  COURSE_REALNAME       VARCHAR(75),                    -- 退课处理人姓名
  COURSE_TIME           DATETIME,                       -- 退课处理时间
  COURSE_REMARK         VARCHAR(750),                   -- 退课处理备注
  MONEY_AMOUNT          FLOAT(12,2) DEFAULT 0 NULL,     -- 退款金额（=T_ORDER_DETAIL.ACTUAL_AMOUNT）
  MONEY_USERID          INTEGER,                        -- 退款处理人ID
  MONEY_REALNAME        VARCHAR(75),                    -- 退款处理人姓名
  MONEY_TIME            DATETIME,                       -- 退款处理时间
  MONEY_REMARK          VARCHAR(750),                   -- 退款处理备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 支付宝在线支付
CREATE TABLE T_ORDER_ALIPAY
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  ORDER_ID          INTEGER,                            -- 订单id=T_ORDER.ID
  TRADE_NO          VARCHAR(50) NOT NULL,               -- 交易号（32位uuid）
  ALIPAY_ID         VARCHAR(64) NULL,                   -- 支付宝ID（用于验证是否数据篡改）
  NOTIFY_ID         VARCHAR(100) NULL,                  -- 支付宝通知ID（用于验证是否数据篡改）
  FORM_STATE        TINYINT DEFAULT 0 NOT NULL,         -- 订单状态（0.待处理，1失败，2成功）
  FORM_AMOUNT       FLOAT(12,2) DEFAULT 0.00 NOT NULL,  -- 订单金额
  USERID            INTEGER NOT NULL,                   -- 用户ID
  CREATION_TIME     DATETIME NOT NULL,
  MODIFY_TIME       DATETIME NOT NULL,
  FORM_TYPE         TINYINT DEFAULT 0,                  -- 订单类型（0充值，1购物）
  ISDEAL            TINYINT DEFAULT 0,                  -- 支付宝回调状态，避免回调并发（1正在处理，0未在处理）
  OPERATOR_ID       INTEGER,                            -- 操作人id
  OPERATOR_NAME     VARCHAR(75),                        -- 操作人姓名
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 支付宝支付日志
CREATE TABLE T_ORDER_ALIPAY_LOG
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  TRADE_NO          VARCHAR(32) NOT NULL,   -- T_ORDER_ALIPAY.TRADE_NO
  LOG_CONTENT       VARCHAR(1500),          -- 日志内容
  CREATION_TIME     DATETIME,               -- 创建时间
  LOG_TYPE          TINYINT DEFAULT 1,      -- 日志类型（ 1表示支付，2表示查询）
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 线下支付记录表
CREATE TABLE T_ORDER_CASHPAY
(
  ID                    INTEGER NOT NULL AUTO_INCREMENT,
  ORDER_ID              INTEGER,                        -- 订单ID=T_ORDER.ID
  OPE_USERID            INTEGER,                        -- 操作人ID
  OPE_REALNAME          VARCHAR(75),                    -- 操作人姓名
  CREATE_TIME           DATETIME NULL,                  -- 操作时间 
  CASH_REALNAME         VARCHAR(75),                    -- 收款人姓名
  CASH_TIME             DATETIME,                       -- 收款时间
  CASH_AMOUNT           FLOAT(12,2) DEFAULT 0 NULL,     -- 收款金额（=T_ORDER.PAY_AMOUNT）
  CHARGE_TYPE           VARCHAR(100),                   -- 收款方式(现金/银行转账等，文字描述)
  REMARK                VARCHAR(750),                   -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 总收入金额变动流水记录【1.2.0.0】
CREATE TABLE T_ORDER_FUNDS
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  USERID            INTEGER NOT NULL,       -- 用户ID
  RELATION_ID       VARCHAR(100) NOT NULL,  -- 关联记录ID（T_ORDER.id，T_ORDER_RETURN.id）
  RECORD_DATE       DATETIME NOT NULL,      -- 记录产生时间
  OPE_USER_ID       INTEGER NOT NULL,       -- 操作人ID
  OPE_REAL_NAME     VARCHAR(75),            -- 操作人姓名
  CHANGE_TYPE       TINYINT NOT NULL,       -- 操作类型（参看CHARGETYPE.JAVA枚举类）
  CHANGE_FUNDS      FLOAT(12,2) NOT NULL,   -- 变动金额
  REMAIN_FUNDS      FLOAT(12,2),            -- 变动后总金额（定时器生成）
  DETAIL_TYPE       TINYINT,                -- 交易类型（0增加、入账，1减少、出账）
  REMARK            VARCHAR(1500),          -- 交易详细
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;



-- ------------------------------------初始化数据-------------------------------------
-- 添加支付宝账号配置
delete from T_SYSTEM_CONFIG where code in('alipay.subject', 'alipay.partner', 'alipay.key', 'alipay.seller_email', 'alipay.rsa.private.key', 'alipay.rsa.public.key');
insert into T_SYSTEM_CONFIG(CODE, NAME, VALUE, DESCRIPTION, CAN_VIEW, can_edit, validate, order_no)
values('alipay.subject', '商品名称', '支付宝—音乐大师网', '支付宝支付时显示的商品名称', 1, 1, 'config-max-length-50', 130);
insert into T_SYSTEM_CONFIG(CODE, NAME, VALUE, DESCRIPTION, CAN_VIEW, can_edit, validate, order_no)
values('alipay.partner', '支付宝合作伙伴id', '2088121921902551', '支付宝合作伙伴id (账户内提取)', 0, 0, 'required', 132);
insert into T_SYSTEM_CONFIG(CODE, NAME, VALUE, DESCRIPTION, CAN_VIEW, can_edit, validate, order_no)
values('alipay.key', '支付宝密钥', 'hx6e2tkxvtmyo5vdl8p675h9wt9m382t', '支付宝密钥', 0, 0, 'required', 134);
insert into T_SYSTEM_CONFIG(CODE, NAME, VALUE, DESCRIPTION, CAN_VIEW, can_edit, validate, order_no)
values('alipay.seller_email', '卖家支付宝帐号', 'pay@chnmaster.com', '音乐大师网卖家支付宝帐号', 0, 0, 'required', 136);
insert into T_SYSTEM_CONFIG(CODE, NAME, VALUE, DESCRIPTION, CAN_VIEW, can_edit, validate, order_no)
VALUES('alipay.rsa.private.key', '公司支付宝RSA私钥', 'MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM/uUOmwhnkX3sH5PWft6hb5UtMdPEr2WliYY9naSzwjxuRvgvCiqZrwLOvmXkNuAw/9RLaD5ZlW7bnvdlRIGrAF3pa8BT+UkWbJU7hiuYGTGVQzIDVeZibx5PjC6YRMNBWdbrpSMkneGxwn+a0QsHDbyqR3wOUIkLKnnDoibczPAgMBAAECgYATY3Z6A/LWEcq8rZ/zM/UTBVaaxuwbVVNhVXxiEP/Y8MEYBengxFoqT2Dw8j8x7G0urYAdwLjFDp0GLHUNMNgEDLWhRBcr8GBiChe6KA4RiBJndoQCx1anDpB8KWOpd+BYiSSoUJXRxFQ3w19XSKGIb2xVXPg5yaPKuLKKYFiR8QJBAPebHyr/uWEeuIpC8egKZkpYWc+Spv9+tdu0UNAOh+mz8w2AgrTebHnbgff3FIDsSHjrVBHY1mfFmVE8f8O4aOcCQQDW+t6scTn6IG4MYuoYpehhQ11MaK3bzpVbFEPVNBht/JPxR1fx2/98NgwQ9swBd8lV+rhcSeJlaJi1ncoKvfZAkEAk5h6Oc16GbSQWIqTXLhmRUbpMly/A2nqye5whbVDu0GzToceYuZ7TyBUFxXHuRogEURmNsVE+VLGk7A2kNqqXQJAJthZ2Ar1kO6/M9nDtwRzgu+rMh1QHQGtFzIIN8QcDfOvv89GYtt/xK/3ajFOF43PgYu2OGbZU72cqmTkXeu1sQJBAObSSOFxDJjlD3wuo4KyApCIntHS2PK3xyDw72ysyN7gTxmVPfsgmCgWbYMeBdLrLil5iJnGb+so7xrLEPaM/Xo=', '公司支付宝账户私钥', 0, 0, 'required', 138);
insert into T_SYSTEM_CONFIG(CODE, NAME, VALUE, DESCRIPTION, CAN_VIEW, can_edit, validate, order_no)
values('alipay.rsa.public.key', '支付宝RSA公钥', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB', '支付宝RSA公钥', 0, 0, 'required', 140);
commit;


-- 添加支付处理和查询模块
delete from T_SYSTEM_MODULE where id in (1500000, 1500100, 1500101, 1500102, 1500103, 1800300);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1500000, 1, '业务管理', null, null, 1, 0, 0, 0, 1500000);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1500100, 1, '支付处理', '/account/payDeal.htm', null, 1, 1500000, 0, 0, 1500100);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500101, 1, '取消订单', '/wxb/cancelOrder.htm', null, 0, 1500100, 0, 1, 1500101);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500102, 1, '线上支付完成订购', '/wxb/finishOnlinePay.htm', null, 0, 1500100, 0, 1, 1500102);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500103, 1, '线下支付完成订购', '/wxb/finishCashPay.htm', null, 0, 1500100, 0, 1, 1500103);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800300, 1, '支付处理查询', '/stat/payDealQuery.htm', null, 1, 1800000, 0, 0, 1800300);
commit;


-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;

-- ------------------------------------调整历史数据-------------------------------------
-- 原来购买课程的记录改为购买课程的第1个课次
update T_COURSE_PRICE b set b.COURSE_TIMEID=(select id from T_COURSE_TIME a where a.seq=1 and a.COURSEID=b.COURSEID) where b.COURSE_TIMEID=0;
update T_ORDER_DETAIL b set b.ware_TIMEID=(select id from T_COURSE_TIME a where a.seq=1 and a.COURSEID=b.ware_ID) where b.ware_TIMEID=0;
update T_ORDER_REPORT b set b.COURSE_TIMEID=(select id from T_COURSE_TIME a where a.seq=1 and a.COURSEID=b.COURSEID) where b.COURSE_TIMEID=0;
commit;

-- 订单-减免类型数据调整
update T_ORDER set ANNUL_TYPE=11 where ANNUL_TYPE=10;
commit;

-- 价格表t_course_price添加已购买票数字段，调整数据（已购买成功的记录）
update T_COURSE_PRICE p,
(
SELECT detail.WARE_PRICEID AS priceId,SUM(detail.ware_num) as num FROM t_order_detail detail WHERE detail.id IN (SELECT report.detail_id from t_order_report report) GROUP BY WARE_PRICEID
) as temp 
set p.PAIN_NUM = temp.num WHERE p.ID = temp.priceId;
commit;

-- 增加手机端地址信息
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('domain.mobile', '手机端地址', 'http://m.chnmaster.com', '手机版访问地址，必须是域名，必须以http://开头', 1, 1, 'validate-url', 30);
commit;


-- 添加版本记录
delete from T_SYSTEM_VERSION where id=4;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(4, '标准版', '公司', '购买支付', now(), '1.3.0.0', '160315', null);
commit;

