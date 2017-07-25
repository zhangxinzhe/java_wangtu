-- 1.4.2.1（黄龙奖修改）

-- ------------------------------------执行1 ddl -------------------------------------
-- T_COMPETE添加：关联课程
ALTER TABLE T_COMPETE ADD COLUMN COURSE_ID INTEGER DEFAULT 0 AFTER ENDTIME;


-- 专题活动-精彩视频
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


-- 合唱比赛报名信息
-- 指挥姓名
ALTER TABLE T_COMPETE_ATTEND_HLJ ADD COLUMN MAJOR_NAME VARCHAR(75) AFTER TEAM_NUM;
-- 钢伴姓名
ALTER TABLE T_COMPETE_ATTEND_HLJ ADD COLUMN DRUM_NAME VARCHAR(75) AFTER MAJOR_NUM;
-- 总人数
ALTER TABLE T_COMPETE_ATTEND_HLJ ADD COLUMN TOTAL_NUM INTEGER DEFAULT 0 AFTER MANAGE_NUM;

-- ------------------------------------执行2 dml -------------------------------------
