-- 1.2.0.0版 初始化语法

-- ----------------------- 序列及主键生成函数 ------------------------------------
-- 创建t_order主键生成表t_seq_order
CREATE TABLE t_seq_order (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name  VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;


-- 创建t_order_detail主键生成表t_seq_order_detail
CREATE TABLE t_seq_order_detail (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name  VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;


-- 创建主键生成函数
DROP FUNCTION IF EXISTS fun_next_seq; 
DELIMITER $$    
CREATE FUNCTION fun_next_seq(in_table_name varchar(50))
RETURNS int                
BEGIN
    select upper(in_table_name) into in_table_name;

    if in_table_name='T_USER' then
        REPLACE INTO t_seq_user (table_name) VALUES (in_table_name);    
    elseif in_table_name='T_AGENCY' then
        REPLACE INTO t_seq_agency (table_name) VALUES (in_table_name);  
    elseif in_table_name='T_COURSE' then
       REPLACE INTO t_seq_course (table_name) VALUES (in_table_name); 
    elseif in_table_name='T_ORDER' then
        REPLACE INTO t_seq_order (table_name) VALUES (in_table_name);
    elseif in_table_name='T_ORDER_DETAIL' then
        REPLACE INTO t_seq_order_detail (table_name) VALUES (in_table_name);
    elseif in_table_name='T_WXB' then
        REPLACE INTO t_seq_wxb (table_name) VALUES (in_table_name); 
    elseif in_table_name='T_SYSTEM_USER' or in_table_name='T_SYSTEM_ROLE' or in_table_name='T_PICTURE' or in_table_name='T_INDEX_ADV' or in_table_name='T_INDEX_STUDENT' then
        REPLACE INTO t_seq_common (table_name) VALUES (in_table_name);
    else
        RETURN 0;
    end if;
    RETURN LAST_INSERT_ID();
    
END $$                                         
DELIMITER ; 

-- ----------------------- 用户报名 ------------------------------------
-- 用户选课订单【1.2.0.0】
CREATE TABLE T_ORDER
(
  ID                    INTEGER NOT NULL,
  TRADE_NO              VARCHAR(50) NOT NULL,   -- 交易号（32位uuid）
  USER_ID               INTEGER NOT NULL,       -- 学生用户ID
  TOTAL_AMOUNT          FLOAT(12,2) DEFAULT 0,  -- 总金额
  ANNUL_AMOUNT          FLOAT(12,2) DEFAULT 0,  -- 优惠减免金额
  PAY_AMOUNT            FLOAT(12,2) DEFAULT 0,  -- 支付金额
  CREATION_TIME         DATETIME,               -- 创建时间
  PAYMENT_TIME          DATETIME,               -- 付款或操作时间
  CLOSE_TIME            DATETIME,               -- 订单关闭时间       
  OPERATOR_ID           INTEGER,                -- 操作人ID
  OPERATOR_NAME         VARCHAR(75),            -- 操作人姓名
  ORDER_STATUS          TINYINT DEFAULT 0,      -- 状态（0待付款，10交易成功，20交易失败）
  STATUS_TYPE           TINYINT DEFAULT 0,      -- 状态类型（11交易成功-系统完成，12交易成功-后台完成，21交易失败-后台取消，22交易失败-用户取消，23交易失败-超时未付款）
  PAY_TYPE              TINYINT DEFAULT 0,      -- 付款方式（0未支付，1支付宝，2余额）
  ORDER_TYPE            TINYINT DEFAULT 0,      -- 订单来源（0前台报名，1后台报名）
  CHECK_STATUS          TINYINT DEFAULT 0,      -- 付款确认状态（ 0未查询  1查询中  2检查成功,支付宝已付款  3检查成功,支付宝未付款  4检查成功,支付宝无订单 5检查失败）
  CHECK_RESULT          VARCHAR(1000),          -- 付款查询描述（ 只有查询失败的时候才值）
  CHECK_NUM             TINYINT DEFAULT 0,      -- 付款查询次数
  CREATE_DEVICE         TINYINT DEFAULT 0,      -- 创建设备（0PC，1手机）
  MODIFY_DEVICE         TINYINT DEFAULT 0,      -- 修改设备（0PC，1手机）
  ANNUL_TYPE            TINYINT DEFAULT 0,      -- 减免类型（0无，10优惠券，20内部员工，30客户试用，99其他）
  REMARK                VARCHAR(4000),          -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 选课订单明细表【1.2.0.0】
CREATE TABLE T_ORDER_DETAIL
(
  ID                    INTEGER NOT NULL,
  ORDER_ID              INTEGER,                -- 订单ID=T_ORDER.ID
  WARE_ID               INTEGER NOT NULL,       -- 课程ID（=T_COURSE.ID）
  WARE_TIMEID           INTEGER DEFAULT 0,      -- 场次ID（=T_COURSE_TIME.ID，基地课程时为0；音乐会时为场次ID）【+】
  WARE_PRICEID          INTEGER DEFAULT 0,      -- 价格类型ID（=T_COURSE_PRICE.ID）
  WARE_TYPE             TINYINT DEFAULT 0,      -- 商品类型（1基地课程，2音乐会）
  WARE_NAME             VARCHAR(150),           -- 商品名称（=COURSE_NAME）
  PAYABLE_AMOUNT        FLOAT(12,2) DEFAULT 0,  -- 应付金额（商品价格）
  ACTUAL_AMOUNT         FLOAT(12,2) DEFAULT 0,  -- 实付金额
  HAS_GOOD              TINYINT DEFAULT 0,      -- 是否赞过 （0未赞过， 1赞过）
  STATUS                TINYINT DEFAULT 0,      -- 课程状态（=T_ORDER_RETURN.STATUS，0正常，10退课处理中，20退课成功，30退课失败，40退费处理中，50退费成功，60退费失败）
  REMARK                VARCHAR(750),           -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 用户报名结果（支付成功的课程）【1.2.0.0】
CREATE TABLE T_ORDER_REPORT
(
  ID                    INTEGER NOT NULL AUTO_INCREMENT,
  ORDER_ID              INTEGER,                -- 订单ID=T_ORDER.ID（0表示没有订单）
  DETAIL_ID             INTEGER,                -- 订单ID=T_ORDER_DETAIL.ID
  USER_ID               INTEGER,                -- 用户ID
  REALNAME              VARCHAR(75),            -- 姓名
  COURSEID              INTEGER,                -- 课程ID
  COURSE_TIMEID         INTEGER DEFAULT 0,      -- 场次ID（=T_COURSE_TIME.ID，基地课程时为0；音乐会时为场次ID）
  COURSE_PRICEID        INTEGER DEFAULT 0,      -- 价格类型ID（=T_COURSE_PRICE.ID）
  STUDY_TYPE            TINYINT DEFAULT 1,      -- 上课形式（0在线直播，1现场听课/现场听音乐会，2点播）
  COURSE_CONTENT_TYPE   TINYINT DEFAULT 1,      -- 课程分类（1基地课程，2音乐会）
  REPORTTIME            DATETIME,               -- 报名时间
  REPORTTYPE            TINYINT DEFAULT 0,      -- 报名方式（0前台报名，1后台报名）
  PAYABLE_AMOUNT        FLOAT(12,2) DEFAULT 0,  -- 应付金额（课程价格）
  ACTUAL_AMOUNT         FLOAT(12,2) DEFAULT 0,  -- 实付金额
  IS_RETURN_COURSE      TINYINT DEFAULT 0,      -- 是否已退课（0未退课，1已退课，根据T_ORDER_RETURN.STATUS字段值取值）  
  IS_RETURN_MONEY       TINYINT DEFAULT 0,      -- 是否已退费（0未退费，1已退费，根据T_ORDER_RETURN.STATUS字段值取值）
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;



-- ----------------------- 其他表字段修改 ------------------------------------
-- 添加字段：场次ID（=T_COURSE_TIME.ID，基地课程时为0，表示所有课次的总价格；音乐会时为场次ID）
alter table T_COURSE_PRICE add column COURSE_TIMEID INTEGER DEFAULT 0 AFTER COURSEID;
-- 修改字段
alter table T_COURSE_PRICE modify NUM INTEGER DEFAULT 0;
-- 添加字段：剩余数量
alter table T_COURSE_PRICE add column REMAIN_NUM INTEGER DEFAULT 0 AFTER NUM;
-- 添加字段：上课形式（0在线直播，1现场听课/现场听音乐会，2点播）
alter table T_COURSE_PRICE add column STUDY_TYPE TINYINT DEFAULT 0 AFTER REMAIN_NUM;
-- 添加字段：价格类型（0正式，1预售）
alter table T_COURSE_PRICE add column PRICE_TYPE TINYINT DEFAULT 0 AFTER STUDY_TYPE;



-- 添加字段ADV_TYPE_ID：栏目类型分类
alter table T_INDEX_ADV add column ADV_TYPE_ID INTEGER AFTER FIELD_TYPE;

-- 首页轮播图片、精彩视频、伴奏音频（栏目类型分类）【1.2.0.0】
CREATE TABLE T_INDEX_ADV_TYPE
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  TITLE             VARCHAR(450) NOT NULL,          -- 分类名称（如伴奏音频：流行音乐、古典音乐...）
  FIELD_TYPE        TINYINT DEFAULT 0 NOT NULL,     -- 栏目类型（0轮播图片，1精彩视频，2伴奏音频）匹配t_index_adv.field_type值
  PRIMARY KEY (ID)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- -----------------------------调整数据--------------------------------------
update T_COURSE set STATUS=2 where STATUS=1;

update T_COURSE set STATUS=1 where STATUS=0 and CONTENT_TYPE = 1;

update T_COURSE_PRICE set REMAIN_NUM = NUM;

commit;

-- 删除音乐会价格记录中所有COURSE_TIMEID=0的记录
create table t_temp as select cp.id from T_COURSE c, T_COURSE_PRICE cp where c.id = cp.COURSEID and c.CONTENT_TYPE = 2;
delete from T_COURSE_PRICE where COURSE_TIMEID = 0 and id in(SELECT id FROM t_temp);
commit;
DROP TABLE t_temp;


-- -----------------------------关于发短信表结构与初始数据--------------------------------------
-- 短信发送表【1.2.0.0】
CREATE TABLE T_SMS_SEND
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  send_userid       INTEGER,            -- 发送者id（关联t_system_user，系统发送时为0）
  send_realname     VARCHAR(75),        -- 发送者真实姓名（系统发送时为：系统）
  send_date         DATETIME,
  content           VARCHAR(1000),
  sms_type          INTEGER DEFAULT 0,  -- 短信类型（0其他，1找回密码，2后台通知，3前台验证码，4前台注册）
  send_status       INTEGER DEFAULT 0,  -- 短信状态 （0待发送/草稿，1已发送）
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- 添加短信群发信息表 【1.2.0.0】
CREATE TABLE T_SMS_SEND_BATCH(
    ID              INTEGER         NOT NULL AUTO_INCREMENT,
    BATCH_ID        VARCHAR(64)     NOT NULL,       -- 群发短信id
    ACCOUNT_NAME    VARCHAR(64)     NOT NULL,       -- 发送账号
    CREATE_DATE     DATETIME        NOT NULL,       -- 发送时间   
    CHECK_TIMES     INTEGER DEFAULT 0,              -- 检查次数
    CHECK_DATE      DATETIME,                       -- 下次检查时间
    STATUS          TINYINT DEFAULT 0,              -- 状态0待检查，1检查完成                           
    PRIMARY KEY(ID)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- 添加验证发送日志【1.2.0.0】
CREATE TABLE T_SMS_SEND_VERIFY_CODE(
    ID              INTEGER         NOT NULL AUTO_INCREMENT,
    USER_ID         INTEGER         NOT NULL,       -- 发送者ID
    MAIN_PHONE      VARCHAR(13)     NOT NULL,       -- 发送的手机号码   
    VERIFY_CODE     VARCHAR(10)     NOT NULL,       -- 验证码
    SEND_TIME       DATETIME        NOT NULL,       -- 最后发送时间
    VERIFY_TIME     DATETIME        NOT NULL,       -- 验证码失效时间   
    PRIMARY KEY(ID)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- 短信发送明细表【1.2.0.0】
CREATE TABLE T_SMS_SEND_DETAIL
(
  ID                    INTEGER NOT NULL AUTO_INCREMENT,
  send_id               INTEGER NOT NULL,   -- 发送id
  receive_userid        INTEGER,            -- 接收者id（关联t_user表）
  receive_username      VARCHAR(50),        -- 接收者username
  receive_realname      VARCHAR(75),        -- 接收者realname
  mobile                VARCHAR(20),        -- 手机号码
  receive_status        INTEGER DEFAULT 0,  -- 接收状态（0发送到短信网关，1发送成功，2发送到网关失败，3网关发失败）
  status_desc           VARCHAR(500),       -- 接收状态描述
  sequence              VARCHAR(100),       -- 短信网关id（放32位guid，回调状态用的）
  BATCH_ID              VARCHAR(64),        -- 群发短信ID
  gateway_type          TINYINT,            -- 短线网关类型 0表示公司网关，1表示玄武网关，2表示沃伦网关
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- 增加关于短信发送的系统参数
delete from T_SYSTEM_CONFIG where code in('phone.wolun_username', 'phone.wolun_password', 'phone.public_content_gateway', 'phone.wolun_extno');

insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('phone.wolun_username', '沃伦短信账号', '242', '用于发送沃伦短信内容', 1, 0, 'config-max-length-50', 400);
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('phone.wolun_password', '沃伦短信账号密码', 'zgyydsw242', '用于发送沃伦短信内容', 0, 0, 'config-max-length-50', 401);
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('phone.public_content_gateway', '固定格式短信内容使用网关', '1', '发送找回密码，发送用户名，验证码等短信内容网关，0表示玄武网关，1表示沃伦网关', 1, 0, 'config-int-range-0-1', 402);
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('phone.wolun_extno', '沃伦短信子帐号', '01', '沃伦短信子帐号', 1, 0, 'config-max-length-50', 403);
INSERT INTO T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no) 
VALUES ('phone.defined_content_username', '玄武自定义短信账号', '', '通过玄武短信网关发送自定义短信内容', 1, 0, 'config-max-length-50', 404);
INSERT INTO T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)  
VALUES ('phone.defined_content_password', '玄武自定义短信账号密码', '', '通过玄武短信网关发送自定义短信内容', 0, 0, 'config-max-length-50', 405);
INSERT INTO T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)  
VALUES ('phone.public_content_username', '玄武固定格式短信账号', '', '通过玄武短信网关发送找回密码，发送用户名，验证码等短信内容', 1, 0, 'config-max-length-50', 406);
INSERT INTO T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)  
VALUES ('phone.public_content_password', '玄武固定格式短信账号密码', '', '通过玄武短信网关发送找回密码，发送用户名，验证码等短信内容', 0, 0, 'config-max-length-50', 407);
INSERT INTO T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no) 
VALUES ('phone.xuanwu_send_report', '是否获取玄武短信网关发送结果信息', '1', '是否获取发送结果信息：0不获取 1获取, 默认不获取', 1, 1, 'config-max-length-50', 408);
commit;


-- -----------------------------模块数据重新调整--------------------------------------
delete from T_SYSTEM_MODULE where id in(1200300, 1800000, 1800100, 1800200, 1800900);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200300, 1, '个人用户', '/basic/studentManage.htm', null, 1, 1200000, 0, 0, 1200300);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800000, 1, '统计查询', null, null, 1, 0, 0, 0, 1800000);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800100, 1, '报名统计', '/stat/reportDetail.htm', null, 1, 1800000, 0, 0, 1800100);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800200, 1, '上课统计', '/stat/inclassDetail.htm', null, 1, 1800000, 0, 0, 1800200);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800900, 1, '短信查询', '/stat/smsDetail.htm', null, 1, 1800000, 0, 0, 1800900);

commit;


-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;


-- 添加版本记录
delete from T_SYSTEM_VERSION where id=3;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(3, '标准版', '公司', '功能完善，报名、支付', now(), '1.2.0.0', '151218', null);
commit;


