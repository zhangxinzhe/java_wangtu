-- 前台用户
CREATE TABLE T_USER
(
  ID                    INTEGER NOT NULL,   -- 用户ID
  USERNAME              VARCHAR(50) BINARY, -- 用户名（区分大小写）
  PASSWORD              VARCHAR(50),        -- 密码（密文）
  QQ                    VARCHAR(11),        -- QQ
  EMAIL                 VARCHAR(50),        -- EMAIL
  PHONE                 VARCHAR(15),        -- 手机
  REALNAME              VARCHAR(75),        -- 姓名
  SEX                   TINYINT DEFAULT 0,  -- 性别（0未知，1男，2女）
  SCHOOL_ID             INTEGER,            -- 所属学校或单位id（注册用户选择，其他角色0）
  SCHOOLNAME            VARCHAR(500),       -- 所属学校或单位（暂时不用）
  CREATETIME            DATETIME,           -- 注册时间
  HOMEPAGE              VARCHAR(50),        -- 个人首页
  TELEPHONE             VARCHAR(25),        -- 固话
  USER_TYPE             TINYINT DEFAULT 1 NOT NULL,  -- 用户类型（1个人用户，2培训基地用户，3名师大家，4助教）
  REGISTER_TYPE         TINYINT DEFAULT 0 NOT NULL,  -- 账号来源（0.后台维护，1.前台自注册，2.手机端注册，3微课绑定用户）【1.3.0.0】
  IS_CANCEL             TINYINT DEFAULT 0 NOT NULL,  -- 状态（0.正常，1.注销）【原：STATUS】
  IS_MEMBER             TINYINT DEFAULT 0 NOT NULL,  -- 是否会员（0.普通用户，1.会员用户）
  IS_UNION_MEMBER       TINYINT DEFAULT 0 NOT NULL,  -- 是否联合会会员（0.否，1.是）【1.4.0.0】
  IS_TUTOR              TINYINT DEFAULT 0 NOT NULL,  -- 是否音乐导师（0.否，1.是）【1.4.0.0】
  IS_HIFI_MEMBER        TINYINT DEFAULT 0 NOT NULL,  -- 是否Hifi会员（0.否，1.是）【1.5.1.1】
  HIFI_MEMBER_DATE      DATETIME,                    -- Hifi会员到期日期（2016-12-12 23:59:59）【1.5.1.1】
  GROUP_TYPE_ID         INTEGER,            -- 用户分组类型ID（=T_USER_GROUP_TYPE.ID，默认0表示无分组）【1.4.0.0】
  AVATAR_FILE           VARCHAR(500),       -- 头像路径    
  AVATAR_ORIGINAL_FILE  VARCHAR(500),       -- 头像原图地址
  FINDPWD_DATE          CHAR(8),            -- 最后一次找回密找回日期（如20130125）
  FINDPWD_TIMES         SMALLINT DEFAULT 0, -- 找回密码次数
  SPELLNAME	            VARCHAR(150),       -- 姓名全拼
  SHORTSPELL            VARCHAR(50),        -- 姓名简拼
  AGENCY_ID	            INTEGER,            -- 培训基地ID（非培训基地用户时为0）
  REMARK                VARCHAR(4000),      -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 用户分组类型表【1.4.0.0】
CREATE TABLE T_USER_GROUP_TYPE(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  TITLE             VARCHAR(75) NOT NULL,           -- 分类名称
  IS_CAN_DEL        TINYINT DEFAULT 1,              -- 是否可以删除（0否；1是）
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 名师大家/音乐导师
CREATE TABLE T_USER_TEACHER
(
  ID                    INTEGER NOT NULL,           -- 教师ID（=T_USER.ID）
  REALNAME              VARCHAR(75),                -- 姓名
  TITLE			        VARCHAR(75),                -- 职称
  IDCODE                VARCHAR(18),                -- 身份证号
  INTRODUCTION          VARCHAR(4000),              -- 个人介绍
  PHOTO_FILE            VARCHAR(500),               -- 照片路径
  TEACH_SUBJECT         VARCHAR(500),               -- 主教专业
  TEACH_TYPE		    TINYINT DEFAULT 1 NOT NULL, -- 名师类型（1专家老师/大家，2专业老师/名师，3音乐导师）
  REGIONCODE            VARCHAR(6) NULL,            -- 所在地区（6位行政区划）
  ISRECOMMEND           TINYINT DEFAULT 0,          -- 是否推荐（首页推荐）
  RECOMMENDTIME         DATETIME,                   -- 推荐日期
  RECOMMENDSEQ          TINYINT DEFAULT 0,          -- 推荐排序号
  CREATETIME            DATETIME,                   -- 创建时间
  REMARK                VARCHAR(4000),              -- 备注
  NATION                VARCHAR(20),                -- 民族【1.4.0.0】
  BIRTHDAY              DATE,                       -- 生日【1.4.0.0】
  POLITICAL             VARCHAR(20),                -- 政治面貌【1.4.0.0】
  WORK_UNIT             VARCHAR(75),                -- 工作单位【1.4.0.0】
  ADDRESS               VARCHAR(100),               -- 联系地址【1.4.0.0】
  PHONE                 VARCHAR(15),                -- 联系方式【1.4.0.0】
  MASTER_SCHOOL         VARCHAR(75),                -- 硕士毕业院校【1.4.0.0】
  DOCTOR_SCHOOL         VARCHAR(75),                -- 博士毕业院校【1.4.0.0】
  IDCARD_FILE           VARCHAR(500),               -- 身份证照片路径【1.4.0.0】(upload/teaPhoto/{年}/{月}/{日}/{id}_idcard.{扩展名}?t=timestamp)
  HAND_IDCARD_FILE      VARCHAR(500),               -- 手持身份证照片路径【1.4.0.0】(upload/teaPhoto/{年}/{月}/{日}/{id}_hand_idcard.{扩展名}?t=timestamp)
  CARD_PHOTO_FILE       VARCHAR(500),               -- 证件一寸照，证书照片【1.4.1.2】(upload/teaPhoto/{年}/{月}/{日}/{id}_card_photo.{扩展名}?t=timestamp)
  TEA_SOURCE            TINYINT DEFAULT 0,          -- 创建来源（0后台创建，1前台申请）【1.4.0.0】
  APPLY_DATE            DATETIME,                   -- 提交申请日期【1.4.0.0】
  AUDIT_STATUS          TINYINT DEFAULT 2 NOT NULL, -- 审核状态（0未提交申请，1审核中，2审核通过，3审核不通过）【1.4.0.0】
  AUDIT_DATE            DATETIME,                   -- 审核日期【1.4.0.0】
  AUDIT_REALNAME        VARCHAR(75),                -- 审核人姓名【1.4.0.0】
  AUDIT_MSG             VARCHAR(500),               -- 审核信息【1.4.0.0】
  SHOW_GUIDE            TINYINT DEFAULT 0,          -- 个人空间是否已经显示过引导指示
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 音乐网对接平台【1.4.2.0】
CREATE TABLE T_USER_BIND(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  BIND_ID           CHAR(100) NOT NULL,                 -- 绑定id/tokenID/apikey(微课id/微信id/hifiApikey)
  NICK_NAME         VARCHAR(60),                        -- 昵称
  USER_ID           INTEGER NOT NULL,                   -- 用户id
  NEW_USERID        VARCHAR(50),                        -- 对接id（传递给第三方平台，与user_id对应，hifi对接中由域名后3位+userId，如dev51）
  PLAT_TYPE         TINYINT NOT NULL DEFAULT 0,         -- 平台类型（0微课,1微信,2QQ,3hifi音乐,9其他平台）
  BIND_TIME         DATETIME NOT NULL,                  -- 绑定时间
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 联合会会员表【1.4.0.0】
CREATE TABLE T_USER_UNION_MEMBER
(
  ID                    INTEGER NOT NULL,
  USERID                INTEGER NOT NULL,           -- 用户id        
  REALNAME              VARCHAR(75),                -- 姓名（推荐表输入的姓名）
  UNION_TYPE            TINYINT DEFAULT 0 NOT NULL, -- 联合会员类型（1个人教育类，2个人装备类，3团体教育类，4团体教育类）
  UNION_CODE            VARCHAR(20),                -- 会员编号
  SEX                   TINYINT DEFAULT 0,          -- 性别（0未知，1男，2女）
  NATION                VARCHAR(20),                -- 民族
  BIRTHDAY              DATE,                       -- 生日
  DEGREE                VARCHAR(20),                -- 学历
  GRADUATE_SCHOOL       VARCHAR(75),                -- 毕业院校
  MAJOR                 VARCHAR(75),                -- 专业
  TEACH_YEAR            FLOAT(5,1) DEFAULT 0,       -- 教学年资（如：1.5年）
  POLITICAL             VARCHAR(20),                -- 政治面貌
  EMAIL                 VARCHAR(50),                -- EMAIL
  WORK_UNIT             VARCHAR(75),                -- 工作单位
  WORK_DEPT             VARCHAR(75),                -- 工作部门
  WORK_DUTY             VARCHAR(75),                -- 工作职务
  TELEPHONE             VARCHAR(20),                -- 联系电话
  REGIONCODE            VARCHAR(6),                 -- 所在地区（6位行政区划）
  ADDRESS               VARCHAR(100),               -- 单位地址
  REFERRER              VARCHAR(75),                -- 推荐人或推荐单位
  INTRO                 VARCHAR(4000),              -- 个人/机构介绍
  OPINION               VARCHAR(500),               -- 单位推荐意见
  PHOTO_FILE            VARCHAR(500),               -- 个人照片/营业执照路径(个人照片:upload/union/{年}/{月}/{日}/{id}/{id}_photo.{扩展名}?t=timestamp;营业执照:upload/union/{年}/{月}/{日}/{id}/营业执照_{会员类型name}_{姓名/机构名}.{扩展名}?t=timestamp)
  PAPER_FILE            VARCHAR(100),               -- 推荐表扫描件路径(upload/union/{年}/{月}/{日}/{id}/推荐表_{会员类型name}_{姓名}.{扩展名}?t=timestamp)
  AGENCY_NAME           VARCHAR(100),               -- 机构名称
  AGENCY_ADDRESS        VARCHAR(100),               -- 注册地址
  AGENCY_YEAR           FLOAT(5,1) DEFAULT 0,       -- 经营年期
  AGENCY_FUND           VARCHAR(20),                -- 注册资金（如：100万）
  AGENCY_BRANCH_NUM     INTEGER,                    -- 分店数量
  AGENCY_BRAND          VARCHAR(75),                -- 品牌名称
  AGENCY_MODE           VARCHAR(20),                -- 机构类别（1生产/2销售；多选；以','号隔开；如1,2）
  AGENCY_FEATURE        VARCHAR(500),               -- 优势特色
  AGENCY_EMPLOYEE_NUM   INTEGER,                    -- 教师人数（员工数量）
  AGENCY_TRAINEE_NUM    INTEGER,                    -- 学员人数
  AGENCY_PRINCIPAL      VARCHAR(75),                -- 负责人姓名
  APPLY_DATE            DATETIME,                   -- 提交申请日期
  AUDIT_STATUS          TINYINT DEFAULT 0 NOT NULL, -- 审核状态（0未提交申请，1审核中，2审核通过，3审核不通过）
  AUDIT_DATE            DATETIME,                   -- 审核日期
  AUDIT_REALNAME        VARCHAR(75),                -- 审核人姓名
  AUDIT_MSG             VARCHAR(4000),              -- 审核信息
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 培训基地（高校、机构或琴行）
CREATE TABLE T_AGENCY
(
  ID                    INTEGER NOT NULL,
  AGENCY_NAME           VARCHAR(75),        -- 基地名称
  REGIONCODE            VARCHAR(6) NULL,    -- 所在地区（6位行政区划）
  CONTACT_MAN           VARCHAR(100),       -- 联系人
  CONTACT_PHONE         VARCHAR(100),       -- 联系电号
  EMAIL                 VARCHAR(50),        -- Email
  ADDRESS               VARCHAR(100),       -- 详细地址
  INTRODUCTION          VARCHAR(4000),      -- 介绍
  AGENCY_TYPE           TINYINT DEFAULT 1 NOT NULL,  -- 基地类型（1高校，2培训机构，3琴行）
  IS_CANCEL             TINYINT DEFAULT 0 NOT NULL,  -- 是否注销（0正常，1已注销）
  AGENCY_SOURCE         TINYINT DEFAULT 0,           -- 基地来源（0后台创建，1申请加盟）
  RANKING               TINYINT DEFAULT 0,  -- 排行（显示顺序）
  ISRECOMMEND           TINYINT DEFAULT 0,  -- 是否推荐（首页推荐）
  RECOMMENDTIME         DATETIME,
  CREATETIME            DATETIME,
  REMARK                VARCHAR(4000),      -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 申请加盟的基地（高校、机构或琴行）
CREATE TABLE T_AGENCY_JOIN
(
  ID                    INTEGER NOT NULL,   -- ID=T_AGENCY.ID（T_AGENCY序列化中取）
  AGENCY_NAME           VARCHAR(75),        -- 基地名称
  REGIONCODE            VARCHAR(6) NULL,    -- 所在地区（6位行政区划）
  CONTACT_MAN           VARCHAR(100),       -- 联系人
  CONTACT_PHONE         VARCHAR(100),       -- 联系电号
  EMAIL                 VARCHAR(50),        -- Email
  ADDRESS               VARCHAR(100),       -- 详细地址
  INTRODUCTION          VARCHAR(4000),      -- 介绍
  AGENCY_TYPE           TINYINT DEFAULT 1 NOT NULL,  -- 基地类型（1高校，2培训机构，3琴行）
  AGENCY_STATUS         TINYINT DEFAULT 0,  -- 申请加盟状态（0：申请中，1：已加盟）
  CREATETIME            DATETIME,
  REMARK                VARCHAR(4000),      -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 学校（id=1，保留id，用其他学校）
CREATE TABLE T_SCHOOL
(
  ID                    INTEGER NOT NULL,
  SCHOOL_NAME           VARCHAR(75),        -- 学校名称
  SHORTSPELL            VARCHAR(50),        -- 学校简拼
  REGIONCODE            VARCHAR(6) NULL,    -- 所在地区（6位行政区划）
  CONTACT_MAN           VARCHAR(100),       -- 联系人
  CONTACT_PHONE         VARCHAR(100),       -- 联系电话
  EMAIL                 VARCHAR(50),        -- Email
  ADDRESS               VARCHAR(100),       -- 详细地址
  INTRODUCTION          VARCHAR(4000),      -- 介绍
  IS_CANCEL             TINYINT DEFAULT 0 NOT NULL,  -- 是否注销（0正常，1已注销）
  CREATETIME            DATETIME,
  REMARK                VARCHAR(4000),      -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 高校风采展示-高校表【1.5.3.0】
CREATE TABLE t_college
(
  ID                    INTEGER NOT NULL,
  NAME                  VARCHAR(75),            -- 院校名称
  PHONE                 VARCHAR(100),           -- 联系电话
  BANNER_FILE           VARCHAR(500),           -- banner图（upload/college/{年}/{月}/{日}/{id}_banner.{扩展名}?t=timestamp）
  LOGO_FILE             VARCHAR(500),           -- logo图（upload/college/{年}/{月}/{日}/{id}_logo.{扩展名}?t=timestamp）
  INTRODUCTION          VARCHAR(4000),          -- 高校简介
  FEATURE               VARCHAR(4000),          -- 高校优势
  ISRECOMMEND           TINYINT DEFAULT 0,      -- 是否推荐
  DISPLAY_ORDER         INTEGER DEFAULT 0,      -- 显示次序
  CREATETIME            DATETIME,
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 基地课程或音乐会
CREATE TABLE T_COURSE
(
  ID                INTEGER NOT NULL,       -- 课程ID（如果dispatch开关开启，则根据dispatch生成，否则由t_seq_course生成）
  COURSE_NAME       VARCHAR(150),           -- 课程名称
  INTRODUCTION      VARCHAR(4000),          -- 课程介绍
  TEA_ID            INTEGER,                -- 主讲名师ID
  TEA_REALNAME      VARCHAR(75),            -- 主讲教师姓名
  ASS_ID            INTEGER,                -- 助教ID（0表示没有助教）
  ASS_REALNAME      VARCHAR(75),            -- 助教姓名
  STUDENT_ID        INTEGER,                -- 绑定个人用户ID【1.3.1.0】/导师发布存储导师ID【1.4.0.0】
  COLLEGE_ID        INTEGER,                -- 绑定高校机构【1.5.3.0】
  PICTURE_FILE      VARCHAR(225),           -- 课程图片路径
  BOOKINGTIME       DATETIME,               -- 预售/预定开始时间
  SALETIME          DATETIME,               -- 售票开始时间
  BEGINTIME         DATETIME,               -- 开始时间（第一个课次的开始时间，精确到分钟）
  ENDTIME           DATETIME,               -- 结束时间（最后一个课次的结束之间，精确到分钟）
  COURSE_OLDCOST       FLOAT(12,2) DEFAULT 0,  -- （原价）基地课程套价
  COURSE_NOWCOST       FLOAT(12,2) DEFAULT 0,  -- （现价）基地课程套价
  COURSE_NOWCOST_VIP   FLOAT(12,2) DEFAULT 0,  -- （现价）基地课程会员套价
  IS_USE_COUPON     TINYINT DEFAULT 1,      -- 是否允许使用优惠券购买（0否，1是）【1.5.2.0】
  IS_BUY_ALL        TINYINT DEFAULT 0,      -- 是否只允许购买整套课程（不允许课次购买）（0否，1是）【1.5.2.0】
  PLACE             VARCHAR(225),           -- 地点
  CONTENT_TYPE      TINYINT DEFAULT 1,      -- 内容分类（1基地课程，2音乐会，3活动专题，4视频点播）
  COURSE_TYPE       TINYINT DEFAULT 0,      -- 课程类型（0其他，11大师班，12普及班，21演唱会，22毕业生音乐会）
  --STOP_RETURN_DAYS  TINYINT DEFAULT 0,      -- 退课截止前天数（单位：天）（如：1，购买的课次中第一个课次时间提前一天可退课）【1.3.1.0】
  WXBID             INTEGER,                -- 无限宝服务器ID
  STATUS            TINYINT DEFAULT 0,      -- 状态（0：未发布，1已发布，2：已下架）
  PROGRESS          TINYINT DEFAULT 0,      -- 进度状态（0项目待定，1预售/预定，2售票中，3演出开始，4结束）（暂时不用，保留）
  ISRECOMMEND       TINYINT DEFAULT 0,      -- 是否推荐（0：否，1：是）
  RECOMMENDTIME     DATETIME,               -- 推荐日期
  RECOMMENDSEQ      TINYINT DEFAULT 0,      -- 推荐排序号
  CREATETIME        DATETIME,
  MODIFYTIME        DATETIME,               -- 最后修改时间
  REMARK            VARCHAR(4000),          -- 备注
  COURSE_SOURCE     TINYINT DEFAULT 0,          -- 创建来源（0后台创建，1申请发布）【1.4.0.0】
  APPLY_DATE        DATETIME,                   -- 提交申请日期【1.4.0.0】
  APPLY_INFO        VARCHAR(4000),              -- 申请描述信息【1.4.0.0】
  AUDIT_STATUS      TINYINT DEFAULT 2 NOT NULL, -- 审核状态（0未提交申请，1审核中，2审核通过，3审核不通过）【1.4.0.0】
  AUDIT_DATE        DATETIME,                   -- 审核日期【1.4.0.0】
  AUDIT_REALNAME    VARCHAR(75),                -- 审核人姓名【1.4.0.0】
  AUDIT_MSG         VARCHAR(4000),              -- 审核信息【1.4.0.0】
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 课程课次或音乐会场次
CREATE TABLE T_COURSE_TIME
(
  ID            INTEGER NOT NULL AUTO_INCREMENT,
  COURSEID      INTEGER,                -- 课程或音乐会ID
  BEGINTIME     DATETIME,               -- 开始时间
  ENDTIME       DATETIME,               -- 结束时间
  SEQ           INTEGER,                -- 课次序号
  TITLE         VARCHAR(225),           -- 课次主要内容或主题
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 课程或音乐会价格类型
CREATE TABLE T_COURSE_PRICE
(
  ID            INTEGER NOT NULL AUTO_INCREMENT,
  COURSEID      INTEGER,                    -- 课程或音乐会ID
  COURSE_TIMEID INTEGER DEFAULT 0,          -- 课次/场次ID（=T_COURSE_TIME.ID）【1.3.0.0】
  OLDPRICE      FLOAT(12,2) DEFAULT 0,      -- 原价
  NOWPRICE      FLOAT(12,2) DEFAULT 0,      -- 普通用户价格（0免费）
  NOWPRICE_VIP  FLOAT(12,2) DEFAULT 0,      -- vip用户价格（0免费）【1.3.0.0】
  SEQ           INTEGER,                    -- 显示序号
  NUM           INTEGER DEFAULT 0,          -- 总数量（0不限制数量）
  PAIN_NUM      INTEGER DEFAULT 0,          -- 已购买数量
  REMAIN_NUM    INTEGER DEFAULT 0,          -- 剩余数量（未使用）
  STUDY_TYPE    TINYINT DEFAULT 1,          -- 上课形式（0在线直播，1现场听课/现场听音乐会，2点播）【1.2.0.0】
  PRICE_TYPE    TINYINT DEFAULT 0,          -- 价格类型（0正式，1预售）【1.2.0.0】
  REMARK        VARCHAR(225),               -- 价格说明（如现场/直播）
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 点播视频的视频文件【1.4.0.0】
CREATE TABLE T_COURSE_VIDEO
(
  ID                    INTEGER NOT NULL AUTO_INCREMENT,
  COURSEID              INTEGER,                    -- 课程或音乐会ID
  COURSE_TIMEID         INTEGER DEFAULT 0,          -- 课次/场次ID（=T_COURSE_TIME.ID）
  VIDEO_DURATION        FLOAT(12,2),                -- 视频时长（分钟）
  VIDEO_FILE            VARCHAR(225),               -- 视频路径（upload/video/{年}/{月}/{日}/{courseId}/{timestamp}.扩展名）
  FILE_NAME             VARCHAR(500),               -- 文件名字
  FILE_EXT              VARCHAR(50),                -- 文件类型（扩展名）
  FILE_SIZE             INTEGER DEFAULT 0,          -- 视频文件大小（单位：字节）
  PREDICT_TIME          DATETIME,                   -- 预计更新时间
  MODIFY_TIME           DATETIME,                   -- 修改时间
  PLAY_NUM              INTEGER DEFAULT 0,          -- 点播次数
  UPLOAD_TYPE           INTEGER DEFAULT 0,          -- 上传方式（0视频上传，1ftp维护）
  CONVERT_STATUS        INTEGER DEFAULT 0,          -- 视频转换状态（0：无需转换，1：转换中，2：转换成功，3：转换失败）
  CONVERT_ID            VARCHAR(32),                -- 视频转换任务GUID
  CONVERT_RUN_TIME      DATETIME,                   -- 转换开始时间
  CONVERT_END_TIME      DATETIME,                   -- 转换结束时间
  CONVERT_MSG           VARCHAR(1000),              -- 转换结果信息
  REMARK                VARCHAR(225),               -- 说明
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 课程评论表
CREATE TABLE T_COURSE_COMMENT(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  COMMENT_TYPE      TINYINT DEFAULT 1 NOT NULL, -- 评论类型（1课程评论，2视频回顾评论）
  COURSEID          INTEGER NOT NULL,           -- 评论内容id（与评论类型对应，courseId，infoId）
  TEA_ID            INTEGER,                    -- 主讲ID（INFO_TYPE=1存储主讲，其他类型储存0）
  OBS_ID            INTEGER,                    -- 评论人ID
  OBS_NAME          VARCHAR(50),                -- 评论人姓名
  IP                VARCHAR(50),                -- 评论人IP
  SCORE             INTEGER,                    -- 评分（星级评分1-5分）
  CONTENT           VARCHAR(500),               -- 评论内容
  COMMENTTIME       DATETIME,                   -- 评论时间
  AUDIT_STATUS      TINYINT DEFAULT 2 NOT NULL, -- 审核状态（2审核通过，3审核不通过）
  AUDIT_DATE        DATETIME,                   -- 审核日期
  AUDIT_REALNAME    VARCHAR(75),                -- 审核人姓名
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- ----------------------- 用户报名 ------------------------------------
-- 用户选课订单【1.2.0.0】
CREATE TABLE T_ORDER
(
  ID                    INTEGER NOT NULL,
  TRADE_NO              VARCHAR(50) NOT NULL,   -- 交易号（32位uuid）
  USER_ID               INTEGER NOT NULL,       -- 学生用户ID
  TOTAL_AMOUNT          FLOAT(12,2) DEFAULT 0,  -- 总金额
  ANNUL_AMOUNT          FLOAT(12,2) DEFAULT 0,  -- 优惠金额
  PAY_AMOUNT            FLOAT(12,2) DEFAULT 0,  -- 实付金额
  CREATION_TIME         DATETIME,               -- 创建时间
  PAYMENT_TIME          DATETIME,               -- 付款或操作时间
  CLOSE_TIME            DATETIME,               -- 订单关闭时间       
  OPERATOR_ID           INTEGER,                -- 操作人ID
  OPERATOR_NAME         VARCHAR(75),            -- 操作人姓名
  ORDER_STATUS          TINYINT DEFAULT 0,      -- 状态（0待付款，1交易成功，2交易失败）
  STATUS_TYPE           TINYINT DEFAULT 0,      -- 状态类型（0待付款，11交易成功-用户完成，12交易成功-后台完成，13交易成功-系统完成，21交易失败-后台取消，22交易失败-用户取消，23交易失败-超时未付款）
  PAY_TYPE              TINYINT DEFAULT 0,      -- 付款方式（0未确定，1支付宝，2余额，3微信，4线下支付，5免费，6后台支付，7苹果支付，8乐币支付）
  ORDER_TYPE            TINYINT DEFAULT 0,      -- 订单来源（0前台报名，1后台报名）
  CHECK_STATUS          TINYINT DEFAULT 0,      -- 付款确认状态（ 0未查询  1查询中  2检查成功,支付宝已付款  3检查成功,支付宝未付款  4检查成功,支付宝无订单 5检查失败）
  CHECK_RESULT          VARCHAR(1000),          -- 付款查询描述（ 只有查询失败的时候才值）
  CHECK_NUM             INTEGER DEFAULT 0,      -- 付款查询次数
  CREATE_DEVICE         TINYINT DEFAULT 0,      -- 创建设备（0PC，1手机）
  MODIFY_DEVICE         TINYINT DEFAULT 0,      -- 修改设备（0PC，1手机）
  PAY_DEVICE            TINYINT DEFAULT 0,      -- 支付设备(0PC，1手机)
  BUY_TYPE              TINYINT DEFAULT 0,      -- 购买类型（0整课购买，1选课次购买）
  ORDER_KIND            TINYINT DEFAULT 0,      -- 订单类型（0课程/音乐会，3活动专题，4视频点播，5HIFI会员,6IOS乐币）（1，2保留，拆分课程/音乐会）
  HIFI_ORDER            VARCHAR(50),            -- 购买hifi会员，返回的orderId
  HIFI_CHECK_STATUS     TINYINT DEFAULT 0,      -- 购买hifi会员，对接过程状态检查（0未确定，11下单成功，12下单失败，21付款成功，22付款失败，31更新会员成功，32更新会员失败）
  REMARK                VARCHAR(4000),          -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;



-- 选课订单明细表【1.2.0.0】
CREATE TABLE T_ORDER_DETAIL
(
  ID                    INTEGER NOT NULL,
  ORDER_ID              INTEGER,                -- 订单ID=T_ORDER.ID
  WARE_ID               INTEGER NOT NULL,       -- 商品ID（courseID、competeAttendID）
  WARE_TIMEID           INTEGER DEFAULT 0,      -- 课次/场次ID（=T_COURSE_TIME.ID）【1.3.0.0】
  WARE_PRICEID          INTEGER DEFAULT 0,      -- 价格类型ID（=T_COURSE_PRICE.ID）
  WARE_TYPE             TINYINT DEFAULT 0,      -- 商品类型（1基地课程，2音乐会，3活动专题，4视频点播，5HIFI会员,6IOS乐币）
  WARE_NAME             VARCHAR(150),           -- 商品名称（=COURSE_NAME）
  WARE_NUM              INTEGER DEFAULT 1,      -- 数量【1.3.0.0】
  PAYABLE_PER           FLOAT(12,2) DEFAULT 0,  -- 应付单价【1.3.0.0】
  ACTUAL_PER            FLOAT(12,2) DEFAULT 0,  -- 实付单价【1.3.0.0】
  PAYABLE_AMOUNT        FLOAT(12,2) DEFAULT 0,  -- 应付金额（=数量*应付单价）
  ACTUAL_AMOUNT         FLOAT(12,2) DEFAULT 0,  -- 实付金额（=数量*实付单价）
  HAS_GOOD              TINYINT DEFAULT 0,      -- 是否赞过 （0未赞过， 1赞过）
  STATUS                TINYINT DEFAULT 0,      -- 课程状态（=T_ORDER_RETURN.STATUS，0正常，10退课处理中，20退课成功，30退课失败，40退费处理中，50退费成功，60退费失败）
  REMARK                VARCHAR(750),           -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 用户报名结果（支付成功的课程）【1.2.0.0】
CREATE TABLE T_ORDER_REPORT
(
  ID                    INTEGER NOT NULL AUTO_INCREMENT,
  ORDER_ID              INTEGER,                -- 订单ID=T_ORDER.ID
  DETAIL_ID             INTEGER,                -- 订单ID=T_ORDER_DETAIL.ID
  USER_ID               INTEGER,                -- 用户ID
  REALNAME              VARCHAR(75),            -- 姓名
  COURSEID              INTEGER,                -- 课程ID
  COURSE_TIMEID         INTEGER DEFAULT 0,      -- 课次/场次ID（=T_COURSE_TIME.ID）【1.3.0.0】
  COURSE_PRICEID        INTEGER DEFAULT 0,      -- 价格类型ID（=T_COURSE_PRICE.ID）
  STUDY_TYPE            TINYINT DEFAULT 1,      -- 上课形式（0在线直播，1现场听课/现场听音乐会，2点播）
  COURSE_CONTENT_TYPE   TINYINT DEFAULT 1,      -- 课程分类（1基地课程，2音乐会）
  REPORTTIME            DATETIME,               -- 报名时间
  REPORTTYPE            TINYINT DEFAULT 0,      -- 报名方式（0前台报名，1后台报名）
  PAYABLE_AMOUNT        FLOAT(12,2) DEFAULT 0,  -- 应付金额（课程价格）
  ACTUAL_AMOUNT         FLOAT(12,2) DEFAULT 0,  -- 实付金额
  IS_RETURN_COURSE      TINYINT DEFAULT 0,      -- 是否已退课（0未退课，1退课处理中，2已退课，根据T_ORDER_RETURN.STATUS字段值取值）【1.3.0.0】
  IS_RETURN_MONEY       TINYINT DEFAULT 0,      -- 是否已退费（0未退费，1退费处理中，2已退费，根据T_ORDER_RETURN.STATUS字段值取值）【1.3.0.0】
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


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
  STATUS_TYPE           TINYINT DEFAULT 0 NULL,         -- 状态类型（0退课处理中，21退课成功-无需审核，22退课成功-管理员审核，51退费成功-系统处理，52退费成功-人工打款）
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


-- 线下支付记录表、后台支付记录
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
  WARE_TYPE         TINYINT DEFAULT 0,      -- 商品类型（1基地课程，2音乐会，3活动专题，4视频点播，5HIFI会员,6IOS乐币）
  RECORD_DATE       DATETIME NOT NULL,      -- 记录产生时间
  OPE_USER_ID       INTEGER NOT NULL,       -- 操作人ID
  OPE_REAL_NAME     VARCHAR(75),            -- 操作人姓名
  CHANGE_TYPE       TINYINT NOT NULL,       -- 操作类型（参看CHARGETYPE.JAVA枚举类）
  CHANGE_FUNDS      FLOAT(12,2) NOT NULL,   -- 变动金额
  REMAIN_FUNDS      FLOAT(12,2),            -- 变动后总金额（定时器生成）
  DETAIL_TYPE       TINYINT,                -- 交易类型（0增加、入账，1减少、出账）
  REMARK            VARCHAR(1500),          -- 交易详细（{CHANGE_TYPE类型名称}，{购买/退费/报名}{WARE_TYPE类型名称}{【课程名称/活动名称】}）
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 订单优惠记录表【1.3.1.0】
CREATE TABLE T_ORDER_ANNUL
(
  ID                    INTEGER NOT NULL AUTO_INCREMENT,
  ORDER_ID              INTEGER,                -- 订单ID=T_ORDER.ID
  ANNUL_TYPE            TINYINT DEFAULT 0,      -- 优惠类型（0无，10整课购买，11后台优惠，12优惠券）
  COUPON_CODE           VARCHAR(20),            -- 优惠码（优惠类型为12优惠券，存储优惠码；其他类型为空）
  ANNUL_AMOUNT          FLOAT(12,2) DEFAULT 0,  -- 优惠金额（最多保留2位小数）
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

-- 优惠表
CREATE TABLE T_COUPON
(
  ID            INTEGER NOT NULL,
  NAME          VARCHAR(75),                -- 推广码名称
  CREATE_TYPE   INTEGER DEFAULT 0,          -- 生成类型（0单张生成；1批量生成）
  CREATE_NUM    INTEGER DEFAULT 0,          -- 批量生成数量
  BATCH_CODE    CHAR(4) NOT NULL,           -- 批次编号（4位，如1601、1602）
  COUPON_TYPE   INTEGER DEFAULT 0,          -- 优惠类型（0折扣优惠；1金额优惠）
  DISCOUNT      FLOAT(12,2) DEFAULT 1,      -- 折扣比例
  AMOUNT        FLOAT(12,2) DEFAULT 0,      -- 优惠金额
  USE_TYPE      INTEGER NOT NULL DEFAULT 0, -- 使用类型（0多次使用，1只能使用一次）
  BEGIN_TIME    DATETIME,                   -- 使用开始时间（默认00:00:00）
  END_TIME      DATETIME,                   -- 使用截至时间（默认23:59:59）
  OWNER_NAME    VARCHAR(75),                -- 推广人姓名
  CREATE_TIME   DATETIME NOT NULL,          -- 创建时间
  REMARK        VARCHAR(4000),              -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- 优惠推广码
CREATE TABLE T_COUPON_CODE(
  ID                  INTEGER NOT NULL AUTO_INCREMENT,
  COUPON_ID           INTEGER NOT NULL,             -- 优惠卷ID
  COUPON_CODE         VARCHAR(20) NOT NULL,         -- 推广码（单张生成：批次编号+8位随机数；批量生成：批次编号+12位）
  IS_CANCEL           INTEGER DEFAULT 0,            -- T_COUPON_CODE添加：状态是否注销（0否，1是）
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

-- 优惠卷课程关联表【1.4.2.0】
CREATE TABLE T_COUPON_COURSE(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  COUPON_ID         INTEGER NOT NULL,                   -- 优惠卷ID
  COURSE_ID         INTEGER NOT NULL,                   -- 课程ID
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ----------------------- 无限宝服务器 ------------------------------------------
-- 无限宝服务器
CREATE TABLE T_WXB
(
  ID                    INTEGER NOT NULL,  
  NAME                  VARCHAR(150),       -- 名称
  REGION                CHAR(6),            -- 所在地区码
  MASTER_LOGIN_URL      VARCHAR(500),       -- 主登录服务器IP/域名
  SLAVE_LOGIN_URL       VARCHAR(500),       -- 备登录服务器IP/域名
  TRANSFER_LOGIN_URL	VARCHAR(500),       -- 转接登录服务器IP/域名
  LOGINPORT             INTEGER,            -- 服务器端口号
  MONITORPORT           INTEGER,            -- 监控端口号
  COMMANDPORT           INTEGER default 8087,   -- 监听端口（截图获取用）
  MAXCAPACITY           INTEGER,            -- 最大承重量
  FTPURL                VARCHAR(100),       -- FTP地址
  FTPPORT               INTEGER,            -- FTP端口号
  FTPACCOUNT            VARCHAR(100),       -- FTP账号
  FTPPASSWORD           VARCHAR(100),       -- FTP密码
  STATUS                TINYINT,            -- 状态 （0停用，1启用）
  REMARK                VARCHAR(4000),
  CUSERID               INTEGER,            -- 添加人
  CREATETIME            DATETIME,           -- 创建时间
  DOG_NAME              VARCHAR(400),       -- 加密狗名称
  DOG_NO                VARCHAR(64),        -- 加密狗序列号
  DOG_NOEXPIRE          TINYINT DEFAULT 0,  -- 加密码是否永不过期（0否，1是）
  DOG_ENDTIME           DATETIME,           -- 加密狗到期时间
  PROXY_NUM             INTEGER DEFAULT 0,  -- 代理服务器数量
  LAST_SYN_DATE         DATETIME,           -- 同步时间
  BAND_WIDTH            INTEGER DEFAULT 0,  -- 机房带宽（单位为M）
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;



-- 无限宝登录退出日志（无限宝回传）
CREATE TABLE T_WXB_LOG
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  WXBID             INTEGER,        -- 无限宝服务器ID
  COURSEID          INTEGER,        -- 课程ID
  USERID            INTEGER,        -- 用户ID
  REALNAME          VARCHAR(75),    -- 用户姓名
  USERTYPE          TINYINT,        -- 登录无限宝身份（0：学员，1：助教，2：主讲,3.教务,4.普通特殊账户）
  LOGINTIME         DATETIME,       -- 登录时间
  LOGOUTTIME        DATETIME,       -- 退出时间
  CREATETIME        DATETIME,       -- 创建时间
  REMARK            VARCHAR(2000) DEFAULT '',
  SEQ               INTEGER,        -- 课次
  LOGINFLAG         VARCHAR(32),    -- 登录标识（无限宝服务器产生的guid值）
  NO_SERIOUS_TIME   INTEGER DEFAULT 0,  -- 不认真总时长
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;



-- -----------------------------前台栏目数据--------------------------------------

-- 首页轮播图片、精彩视频、伴奏音频
CREATE TABLE T_INDEX_ADV
(
  ID                INTEGER NOT NULL,
  TITLE             VARCHAR(450) NOT NULL,          -- 标题
  FIELD_TYPE	    TINYINT DEFAULT 0 NOT NULL,     -- 栏目类型（0轮播图片，1精彩视频，2伴奏音频，3活动专题-轮播图片）
  COMPETE_ID        INTEGER DEFAULT 0,              -- 活动ID，栏目类型为3活动专题图片存储活动id；其他类型为0 【1.3.2.0】
  ADV_SOURCE        TINYINT DEFAULT 0 NOT NULL,     -- 来源（0PC版，1手机版）
  ADV_TYPE_ID       INTEGER,                        -- 栏目类型分类（ADV_TYPE_ID=T_INDEX_ADV_TYPE.ID）
  CONTENT_TYPE      TINYINT DEFAULT 0 NOT NULL,     -- 内容类型（0无，1外部连接，2编辑内容，3视频，4音频）
  LINK_URL          VARCHAR(200),                   -- 链接地址（CONTENT_TYPE=1）
  LOCAL_FILE        VARCHAR(200),                   -- 文件路径（CONTENT_TYPE=2,3,4）
  IS_SHOW           TINYINT DEFAULT 0 NOT NULL,     -- 是否显示（0不显示，1显示）
  PICTURE_FILE      VARCHAR(200) null,              -- 图片路径
  DISPLAY_ORDER     INTEGER DEFAULT 0,              -- 显示次序/点击量
  CREATE_DATE       DATETIME,                       -- 创建时间
  REMARK            VARCHAR(100),                   -- 备注/姓名
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 首页轮播图片、精彩视频、伴奏音频（栏目类型分类）【1.2.0.0】
CREATE TABLE T_INDEX_ADV_TYPE
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  TITLE             VARCHAR(450) NOT NULL,          -- 分类名称（如伴奏音频：流行音乐、古典音乐...）
  FIELD_TYPE        TINYINT DEFAULT 0 NOT NULL,     -- 栏目类型（0轮播图片，1精彩视频，2伴奏音频）匹配t_index_adv.field_type值
  PRIMARY KEY (ID)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 首页页脚链接
CREATE TABLE T_INDEX_FOOTER
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  TITLE             VARCHAR(450) NOT NULL,          -- 标题
  CONTENT_TYPE      TINYINT NOT NULL,               -- 链接类型（1外部链接、2维护内容）
  LINK_URL          VARCHAR(200),                   -- 链接地址
  LOCAL_FILE        VARCHAR(200),                   -- 文件路径（upload/footer/{年}/{月}/{日}/{timestamp}.txt）
  IS_SHOW           TINYINT DEFAULT 0 NOT NULL,     -- 是否显示（0不显示，1显示）
  DISPLAY_ORDER     INTEGER DEFAULT 0,              -- 显示次序
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;



-- 帮助分类
CREATE TABLE T_HELP_CATALOG
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  CATALOGNAME       VARCHAR(60) NOT NULL,   -- 分类名称    
  PARENTID          INTEGER,                -- 父级主题ID（顶级时为0）
  DISPLAYORDER      INTEGER DEFAULT 0,      -- 显示顺序
  IS_SHOW           TINYINT DEFAULT 0,      -- 是否显示
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 帮助主题
CREATE TABLE T_HELP_THEME
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  CATALOG_ID	    INTEGER NOT NULL,	    -- 分类ID
  THEMENAME         VARCHAR(60) NOT NULL,   -- 主题名称    
  DISPLAYORDER      INTEGER DEFAULT 0,      -- 显示顺序
  LINKTYPE          TINYINT,                -- 内容类型（1-外部链接 2-编辑内容）
  LINKURL           VARCHAR(200),           -- 外部链接地址
  CONTENTFILE       VARCHAR(200),           -- 编辑内容文件路径
  IS_SHOW           TINYINT DEFAULT 0,      -- 是否显示
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 公告、新闻
CREATE TABLE T_INDEX_INFO
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  COMPETE_ID        INTEGER DEFAULT 0,          -- 活动ID,类型3热点追踪存储活动Id，其他类型存储0
  TITLE             VARCHAR(450) NOT NULL,      -- 标题
  INFO_TYPE         TINYINT NOT NULL,           -- 信息类型（1公告、2新闻、3热点追踪、4视频回顾）
  INFO_SHORT        VARCHAR(450) NULL,          -- 简介说明
  CONTENT_TYPE      TINYINT NOT NULL,           -- 链接类型（1外部链接、2维护内容）
  LINK_URL          VARCHAR(200),               -- 链接地址
  LOCAL_FILE        VARCHAR(200),               -- 文件路径
  IS_SHOW           TINYINT NOT NULL,           -- 是否显示
  IS_LIGHT          TINYINT,                    -- 是否高亮
  IS_URGENT         TINYINT NOT NULL DEFAULT 0, -- 是否紧急（0否，1是）【1.3.0.0】
  NEW_DAYS          INTEGER,                    -- 置新天数
  ORDER_NO          INTEGER DEFAULT 0,          -- 排序号
  EDITOR            VARCHAR(50),                -- 编辑
  COME_FROM         VARCHAR(150),               -- 来源
  COME_URL          VARCHAR(150),               -- 来源链接
  INFO_DATE         DATETIME,                   -- 日期
  SHARE_PIC_FILE    VARCHAR(200),               -- 分享时图片路径（upload/info/{年}/{月}/{日}/{timestamp}.{扩展名}?t=timestamp）
  CREATE_DATE       DATETIME NOT NULL,          -- 创建时间
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;



-- 优秀学员
CREATE TABLE T_INDEX_STUDENT
(
  ID                INTEGER NOT NULL,
  STU_NAME          VARCHAR(75),		-- 学员姓名
  PHOTO_FILE        VARCHAR(500),		-- 照片文件路径
  UNIT_NAME         VARCHAR(75),		-- 所属单位
  INTRODUCTION      VARCHAR(4000),		-- 介绍
  IS_SHOW           TINYINT NOT NULL,	-- 是否显示
  ORDER_NO          INTEGER DEFAULT 0,	-- 排序号
  CREATETIME        DATETIME,
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 照片或视频
CREATE TABLE T_PICTURE
(
  ID                    INTEGER NOT NULL,
  OBJECT_ID             INTEGER NOT NULL,   -- 来源ID
  OBJECT_TYPE           TINYINT,            -- 类型（1培训基地，2名师大家，3优秀学员，4活动图集）
  PICTURE_NAME          VARCHAR(100),       -- 名称
  PICTURE_FILE          VARCHAR(100),       -- 图片路径
  VIDEO_FILE            VARCHAR(100),       -- 视频路径
  FILE_TYPE             TINYINT DEFAULT 1,  -- 文件类型（1图片，2视频）
  FILE_SIZE             INTEGER,            -- 文件大小（字节）
  FILE_EXT              VARCHAR(50),        -- 文件类型（扩展名）
  IS_SHOW               TINYINT DEFAULT 1,  -- 是否显示（0不显示，1显示）
  SHOW_ORDER            INTEGER DEFAULT 0,  -- 显示排序
  CREATETIME            DATETIME,
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- -----------------------------活动专题表【1.3.2.0】--------------------------------------
-- 活动表
CREATE TABLE T_COMPETE
(
  ID                    INTEGER NOT NULL,
  COMPETE_NAME          VARCHAR(75),            -- 活动名称
  COMPETE_TYPE          TINYINT DEFAULT 0,      -- 活动类型（10神州唱响；20黄龙奖合唱比赛；21黄龙奖乐队比赛···）【1.3.4.0】
  COMPETE_BATCH         CHAR(6),                -- 期号（活动类型为黄龙奖，且黄龙奖同一期合唱比赛和乐队比赛，期号相同。如：201601）【1.3.4.0】
  RULE_FILE             VARCHAR(200),           -- 活动章程文件路径
  FORM_NAME             VARCHAR(75),            -- 报名表原文件名
  FORM_FILE             VARCHAR(200),           -- 报名表文件路径
  COMPETE_FEE           FLOAT(12,2) DEFAULT 0,  -- 报名费
  ATTEND_BEGINTIME      DATETIME,               -- 报名开始时间
  ATTEND_ENDTIME        DATETIME,               -- 报名结束时间
  VOTE_BEGINTIME        DATETIME,               -- 投票开始时间
  VOTE_ENDTIME          DATETIME,               -- 投票结束时间
  BEGINTIME             DATETIME,               -- 开始时间
  ENDTIME               DATETIME,               -- 结束时间
  COURSE_ID             INTEGER DEFAULT 0,      -- 关联课程
  CREATETIME            DATETIME NOT NULL,      -- 创建时间
  IS_DOING              INTEGER DEFAULT 0,      -- 是否进行中（0：否，1：是）【相同活动类型COMPETE_TYPE的活动，只允许一个正在进行中】
  REMARK                VARCHAR(4000),          -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 活动报名表
CREATE TABLE T_COMPETE_ATTEND
(
  ID                INTEGER NOT NULL,
  COMPETE_ID        INTEGER NOT NULL,           -- 活动ID
  USER_ID           INTEGER DEFAULT 0,          -- 关联用户（后台报名时，用户非必选，可存储0）
  REALNAME          VARCHAR(75),                -- 姓名（报名时输入的姓名）
  SEX               TINYINT DEFAULT 0,          -- 性别（0未知，1男，2女）
  BIRTHDAY          DATE,                       -- 生日
  PHOTO_FILE        VARCHAR(500),               -- 报名表照片路径    
  MOBILEPHONE       VARCHAR(25),                -- 联系方式
  EMAIL             VARCHAR(50),                -- Email
  SCHOOL_NAME       VARCHAR(75),                -- 学校院系
  UNIT_NAME         VARCHAR(75),                -- 选送单位
  APPLY_TYPE        TINYINT DEFAULT 0,          -- 报名形式（0前台报名，1后台报名）
  GROUP_TYPE        TINYINT DEFAULT 0,          -- 组别（1教师组民族唱法、2教师组美声唱法 、3学生组民族唱法 、4学生组美声唱法 、5学生组流行唱法）
  ATTEND_CODE       VARCHAR(11),                -- 参赛码（1001，2001，3001....）（付款成功后生成参赛码）
  CREATION_TIME     DATETIME,                   -- 创建时间
  PAY_AMOUNT        FLOAT(12,2) DEFAULT 0,      -- 实付金额
  ORDER_STATUS      TINYINT DEFAULT 0,          -- 状态（0待付款，10交易成功，20交易失败）
  VIDEO_URL         VARCHAR(200),               -- 视频地址
  AUDIT_STATUS      TINYINT DEFAULT 0,          -- （此字段暂时不用）状态（0：未审核，1审核通过，2：审核失败）
  VOTE_NUM          INTEGER DEFAULT 0,          -- 投票数（总票数：网络投票数+加票数）
  VOTE_ADD_NUM      INTEGER DEFAULT 0,          -- 加票数【1.4.2.0】
  PLAY_NUM          INTEGER DEFAULT 0,          -- 视频播放次数
  REMARK            VARCHAR(4000),              -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 黄龙奖报名表【1.3.4.0】
CREATE TABLE T_COMPETE_ATTEND_HLJ
(
  ID                INTEGER NOT NULL,
  COMPETE_ID        INTEGER NOT NULL,           -- 活动ID
  COMPETE_TYPE      TINYINT DEFAULT 20,         -- 活动类型（20黄龙奖合唱比赛；21黄龙奖乐队比赛···）==T_COMPETE.COMPETE_TYPE
  USER_ID           INTEGER DEFAULT 0,          -- 关联用户（后台报名时，用户非必选，可存储0）
  TEAMNAME          VARCHAR(75),                -- 团队名称/乐队名称（报名时输入的名称）
  TEAM_TYPE         TINYINT DEFAULT 0,          -- 参赛组别【COMPETE_TYPE==20黄龙奖合唱比赛，其他类型默认0】（1公开合唱组、2专业合唱组、3少年组，4、成人组）
  BAND_TYPE         VARCHAR(50),                -- 乐队类型【COMPETE_TYPE==21黄龙奖乐队比赛，其他类型默认空】
  UNIT_NAME         VARCHAR(75),                -- 组织单位
  TEAM_LEADER       VARCHAR(25),                -- 总负责人/领队姓名
  TEAM_NUM          INTEGER DEFAULT 0,          -- 成员数/演唱人数
  MAJOR_NAME        VARCHAR(75),                -- 指挥姓名【1.4.2.1】
  MAJOR_NUM         INTEGER DEFAULT 0,          -- 指挥人数【COMPETE_TYPE==21黄龙奖乐队比赛，其他类型默认0】
  DRUM_NAME         VARCHAR(75),                -- 钢伴姓名【1.4.2.1】
  DRUM_NUM          INTEGER DEFAULT 0,          -- 钢伴人数【COMPETE_TYPE==21黄龙奖乐队比赛，其他类型默认0】
  MANAGE_NUM        INTEGER DEFAULT 0,          -- 行政人数
  TOTAL_NUM         INTEGER DEFAULT 0,          -- 总人数【1.4.2.1】
  PHONE             VARCHAR(25),                -- 联系方式
  EMAIL             VARCHAR(50),                -- 电子邮箱
  PLACE             VARCHAR(225),               -- 联系地址
  PHOTO_FILE        VARCHAR(500),               -- 报名表照片路径 
  TEAM_INTRO        VARCHAR(4000),              -- 团队介绍
  MEMBERS_INTRO     VARCHAR(4000),              -- 成员介绍
  APPLY_TYPE        TINYINT DEFAULT 0,          -- 报名形式（0前台报名，1后台报名）
  ATTEND_CODE       VARCHAR(11),                -- 参赛码（1001，2001，3001....）
  CREATION_TIME     DATETIME,                   -- 创建时间
  VOTE_NUM          INTEGER DEFAULT 0,          -- 投票数（总票数：网络投票数+加票数）
  VOTE_ADD_NUM      INTEGER DEFAULT 0,          -- 加票数【1.4.2.0】
  VOTE_SCORE        FLOAT(12,2) DEFAULT 0,      -- 投票的分（10÷选手投票的最高票数×当前选手的票数=选手投票得分）
  SONG_INTRO        VARCHAR(4000),              -- 原创曲目介绍
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 报名曲目表
CREATE TABLE T_COMPETE_ATTEND_SONG
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  COMPETE_ID        INTEGER NOT NULL,           -- 活动ID
  ATTEND_ID         INTEGER NOT NULL,           -- 报名表ID
  STAGE             TINYINT DEFAULT 0,          -- 赛程（1初赛，2复赛，3半决赛，4决赛）
  SONG_NAME         VARCHAR(200),               -- 曲目名称
  SONG_AUTHOR       VARCHAR(75),                -- 曲目原唱
  REMARK            VARCHAR(4000),              -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 组委会表
CREATE TABLE T_COMPETE_COMMITTEE
(
  ID                INTEGER NOT NULL,
  COMPETE_ID        INTEGER NOT NULL,           -- 活动ID
  REALNAME          VARCHAR(75),                -- 姓名
  SEX               TINYINT DEFAULT 0,          -- 性别（0未知，1男，2女）
  UNIT_NAME         VARCHAR(75),                -- 单位
  TITLE             TINYINT NOT NULL,           -- 主委会头衔（9执行主席、1顾问、2主席、3副主席、4委员、5办公室主任、6办公室副主任、7办公室执行主任、8办公室执行副主任）
  JOB_TITLE         VARCHAR(200),               -- 个人职务
  INTRODUCTION      VARCHAR(4000),              -- 个人介绍
  PHOTO_FILE        VARCHAR(500),               -- 照片路径
  ORDER_NO          INTEGER NOT NULL,           -- 排序号
  REMARK            VARCHAR(4000),              -- 备注
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 活动投票表【1.3.4.0】
CREATE TABLE T_COMPETE_VOTE
(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  COMPETE_ID        INTEGER NOT NULL,           -- 活动ID
  ATTEND_ID         INTEGER NOT NULL,           -- 报名表ID
  USER_ID           INTEGER DEFAULT 0,          -- 用户id
  IP                VARCHAR(20),                -- IP
  VOTE_TIME         DATETIME,                   -- 投票时间
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 专题活动-精彩视频【1.4.2.1】
CREATE TABLE T_COMPETE_VIDEO
(
  ID                    INTEGER NOT NULL AUTO_INCREMENT,
  NAME                  VARCHAR(75),                    -- 精彩视频名称
  COMPETEID             INTEGER,                        -- 活动专题ID
  COMPETE_TYPE          TINYINT DEFAULT 10,             -- 活动类型（10神州唱响；20黄龙奖合唱比赛；21黄龙奖乐队比赛）
  PIC_FILE              VARCHAR(225),                   -- 视频预览图（upload/video/{年}/{月}/{日}/{COMPETEID}/pic_{timestamp}.扩展名）
  VIDEO_FILE            VARCHAR(225),                   -- 视频路径（upload/video/{年}/{月}/{日}/{COMPETEID}/{timestamp}.扩展名）
  FILE_NAME             VARCHAR(500),                   -- 文件名字
  FILE_EXT              VARCHAR(50),                    -- 文件类型（扩展名）
  FILE_SIZE             INTEGER DEFAULT 0,              -- 视频文件大小（单位：字节）
  VIDEO_DURATION        FLOAT(12,2),                    -- 视频时长（单位秒）
  IS_SHOW               TINYINT DEFAULT 0 NOT NULL,     -- 是否显示（0不显示，1显示）
  ORDER_NO              INTEGER DEFAULT 0,              -- 排序号  
  CREATETIME            DATETIME,                       -- 创建时间
  REMARK                VARCHAR(225),                   -- 说明
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- -----------------------------关于发短信表结构--------------------------------------

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


-- 添加短信群发信息表【1.2.0.0】
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
  BATCH_ID              VARCHAR(64),        -- 群发短信I
  gateway_type          TINYINT,            -- 短线网关类型 0表示公司网关，1表示玄武网关，2表示沃伦网关
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;



-- -----------------------------后台管理相关表--------------------------------------
-- 后台用户
CREATE TABLE T_SYSTEM_USER
(
  ID            INTEGER NOT NULL,
  USERNAME      VARCHAR(100) BINARY,    -- 用户名（区分大小写）
  REALNAME      VARCHAR(75),            -- 姓名
  REMARK        VARCHAR(2000),          -- 备注
  BINDINGEMAIL  VARCHAR(50),            -- EMAIL
  PASSWORD      VARCHAR(50),            -- 密码
  CREATETIME    DATETIME,
  USERTYPE      TINYINT,                -- 用户类型（1超级管理员，2平台普通管理员）
  ISFREEZE      TINYINT DEFAULT 0,      -- 是否冻结
  ISDELETED     TINYINT DEFAULT 0,      -- 是否删除
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 后台角色表
CREATE TABLE T_SYSTEM_ROLE
(
  ID            INTEGER NOT NULL,
  NAME          VARCHAR(150),       -- 名称
  DESCRIPTION   VARCHAR(1500),      -- 描述
  CREATETIME    DATETIME,           -- 创建日期
  ROLETYPE      INTEGER DEFAULT 1,  -- 角色类型（1平台超级管理员角色，2平台自定义角色）
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 用户角色关联表
CREATE TABLE T_SYSTEM_USER_ROLE
(
  ID            INTEGER NOT NULL AUTO_INCREMENT,
  USERID        INTEGER,
  ROLEID        INTEGER,
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 后台模块表
CREATE TABLE T_SYSTEM_MODULE
(
  ID            INTEGER NOT NULL,       -- 模块ID
  APP_ID        INTEGER NOT NULL,       -- 子系统ID
  NAME          VARCHAR(150),           -- 名称
  URL           VARCHAR(500),           -- 模块链接
  DESCRIPTION   VARCHAR(1500),          -- 描述
  ISSHOW        TINYINT DEFAULT 0,      -- 模块菜单中是否显示
  PARENTID      INTEGER DEFAULT 0,      -- 父模块ID
  ISCOMMON      TINYINT DEFAULT 0,      -- 是否公用模块（所有角色必须有的模块，不能去掉的）
  ISOPERATE     TINYINT DEFAULT 0,      -- 是否功能点（页面中的某一个功能）
  SEQ           INTEGER DEFAULT 0,      -- 排序号
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 角色模块表
CREATE TABLE T_SYSTEM_ROLE_MODULE
(
  ID            INTEGER NOT NULL AUTO_INCREMENT,
  ROLEID        INTEGER,
  MODULEID      INTEGER,
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 后台子系统表
CREATE TABLE T_SYSTEM_APP
(
  ID            INTEGER NOT NULL,       -- 子系统ID
  APP_NAME      VARCHAR(500),           -- 子系统名称
  APP_DES       VARCHAR(500),           -- 描述说明
  INDEX_URL     VARCHAR(500),           -- 后台首页地址
  IS_USING      TINYINT DEFAULT 1,      -- 是否启用（0未启用，1启用）
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 系统日志
CREATE TABLE T_SYSTEM_LOG
(
  ID            INTEGER NOT NULL AUTO_INCREMENT,
  USERID        INTEGER,            -- 操作人ID
  OPERATE       VARCHAR(1500),      -- 操作内容
  OPERATETIME   DATETIME,           -- 操作时间
  OPERATEIP     VARCHAR(50),        -- 操作IP
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 行政区划表
CREATE TABLE T_SYSTEM_REGION
(
  ID            INTEGER NOT NULL AUTO_INCREMENT,
  REGIONCODE    VARCHAR(6),         -- 行政区划码
  REGIONNAME    VARCHAR(75),        -- 区划名称
  FULLCODE      CHAR(6),            -- 行政区划全码
  FULLNAME      VARCHAR(750),       -- 区划全名称
  SHORTSPELL    VARCHAR(255),       -- 全名称简拼
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 系统参数
CREATE TABLE T_SYSTEM_CONFIG
(
  ID            INTEGER NOT NULL AUTO_INCREMENT,
  CODE          VARCHAR(50) NOT NULL,        -- 参数代码
  NAME          VARCHAR(150) NOT NULL,       -- 参数名称
  VALUE         VARCHAR(2000) NOT NULL,      -- 参数值
  DESCRIPTION   VARCHAR(450),                -- 描述
  CAN_VIEW      TINYINT DEFAULT 1 NOT NULL,  -- 是否可见
  CAN_EDIT      TINYINT DEFAULT 1 NOT NULL,  -- 是否可改
  VALIDATE      VARCHAR(50),                 -- js验证器名称
  ORDER_NO      INTEGER DEFAULT 0,           -- 排序号
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- 系统版本表
CREATE TABLE T_SYSTEM_VERSION
(
    ID              INTEGER NOT NULL,
    EDITION         VARCHAR(150),           -- 版本名称（标准版、XX定制版）
    CLIENT          VARCHAR(150),           -- 哪个用户需求修改
    DESCRIPTION     VARCHAR(1200),          -- 修改概述  
    UPGRADEDATE     DATETIME NOT NULL,      -- 更新日期
    VERSION         VARCHAR(30),            -- 版本号
    BUILD           VARCHAR(30),            -- 发布号
    DESC_FILE       VARCHAR(100),           -- 修改描述文件
    PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;





-- -----------------------------创建主键生成表--------------------------------------
-- 创建通用主键生成表 t_seq_common
CREATE TABLE t_seq_common (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name   VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;


-- 创建t_user主键生成表  t_seq_user
CREATE TABLE t_seq_user (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name   VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;


-- 创建t_user_union_member主键生成表 t_user_union_member
CREATE TABLE t_seq_user_union_member (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name   VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;


-- 创建t_agency主键生成表  t_seq_agency
CREATE TABLE t_seq_agency (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name   VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;

-- 主键序列化
CREATE TABLE t_seq_college (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name   VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;

-- 创建t_course主键生成表t_seq_course
CREATE TABLE t_seq_course (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name   VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;


-- t_coupon主键生成表 t_seq_coupon
CREATE TABLE t_seq_coupon (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name   VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;


-- 创建t_order主键生成表t_seq_order
CREATE TABLE t_seq_order (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name   VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;


-- 创建t_order_detail主键生成表t_seq_order_detail
CREATE TABLE t_seq_order_detail (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name   VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;


-- 创建t_wxb主键生成表t_seq_wxb
CREATE TABLE t_seq_wxb (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name   VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;


-- 活动专题主键生成表t_seq_compete
CREATE TABLE t_seq_compete (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name   VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;

-- 活动报名 主键生成表t_seq_compete_attend
CREATE TABLE t_seq_compete_attend (
    max_seq      bigint(20) unsigned NOT NULL auto_increment,
    table_name   VARCHAR(50) NOT NULL default '',
    PRIMARY KEY  (max_seq),
    UNIQUE KEY table_name (table_name)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;
