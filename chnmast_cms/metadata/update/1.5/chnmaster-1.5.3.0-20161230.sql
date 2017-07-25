-- ios支付

-- ------------------------------------执行1 ddl -------------------------------------
-- 个人账户余额表
CREATE TABLE T_ACCOUNT
(
  ID                INTEGER NOT NULL,                   -- 个人账户ID（=T_USER.ID）
  FUNDS             FLOAT(12,2) DEFAULT 0 NOT NULL,     -- 账户余额
  MODIFY_TIME       DATETIME NOT NULL,                  -- 修改时间
  FUNDS_LOCKED      FLOAT(12,2) DEFAULT 0,              -- 冻结金额
  STATUS            TINYINT DEFAULT 0,                  -- 申请退费状态（ 0正常用户，1正在进行退费操作中）
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 个人账户余额流水记录表
CREATE TABLE T_ACCOUNT_RECORD
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  USERID            INTEGER NOT NULL,           -- 用户ID
  RELATION_ID       VARCHAR(100) NOT NULL,      -- 关联记录ID（T_ORDER.id，T_ORDER_RETURN.id）
  RECORD_DATE       DATETIME NOT NULL,          -- 记录产生时间
  AUDIT_USER_ID     INTEGER,                    -- 审核人ID（默认0）
  AUDIT_REAL_NAME   VARCHAR(75),                -- 审核人姓名
  CHANGE_TYPE       TINYINT NOT NULL,           -- 操作类型（参看CHARGETYPE.JAVA枚举类）
  CHANGE_FUNDS      FLOAT(12,2) NOT NULL,       -- 变动金额
  REMAIN_FUNDS      FLOAT(12,2) NOT NULL,       -- 变动后账户金额
  DETAIL_TYPE       TINYINT,                    -- 交易类型（0增加、入账，1减少、出账）
  RECORD_TYPE       TINYINT NOT NULL,           -- 交易记录类型（0充值，1退费，10购买，11提现）
  REMARK            VARCHAR(1500),              -- 详细（余额{充值/购买/退费/提现}{CHANGE_FUNDS变动金额}{【课程名称/活动名称】}{成功/失败}）
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;
-- ------------------------------------执行2 dml -------------------------------------

-- 统计查询-模块调整
update T_SYSTEM_MODULE set url = '/stat/reportStat.htm' where id = 1800100;
update T_SYSTEM_MODULE set url = '/stat/inclassStat.htm' where id = 1800200;
update T_SYSTEM_MODULE set url = '/stat/orderStat.htm' where id = 1800300;
update T_SYSTEM_MODULE set url = '/stat/fundsStat.htm' where id = 1800700;
update T_SYSTEM_MODULE set url = '/stat/smsStat.htm' where id = 1800900;

-- 添加模块-苹果支付统计
DELETE FROM T_SYSTEM_MODULE WHERE id = 1800800;
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800800, 1, 'ios收支统计', '/stat/iospayStat.htm', null, 1, 1800000, 0, 0, 1800800);

-- 初始化超级管理员角色1
delete from T_SYSTEM_ROLE_MODULE where roleid=1;
insert into T_SYSTEM_ROLE_MODULE (roleid, moduleid)
select 1, id  from T_SYSTEM_MODULE where id<9999999 or id>1;
commit;


-- 添加版本记录
delete from T_SYSTEM_VERSION where id=19;
insert into T_SYSTEM_VERSION(id, edition, client, description, upgradedate, version, build, desc_file)
values(19, '标准版', '音乐联盟', 'ios支付', now(), '1.5.3.0', '20161230', null);
commit;
