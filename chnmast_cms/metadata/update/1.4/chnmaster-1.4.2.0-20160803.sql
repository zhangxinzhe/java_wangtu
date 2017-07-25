-- 1.4.2.0 专题活动投票功能（一帐号一天一票）

-- ------------------------------------执行1 ddl -------------------------------------
-- 活动报名表添加字段：加票数
ALTER TABLE T_COMPETE_VOTE ADD COLUMN USER_ID INTEGER DEFAULT 0 AFTER ATTEND_ID;

