-- 1.3.4.0版 优化语法（黄龙奖）


-- 报名表中添加字段：原创曲目介绍
ALTER TABLE T_COMPETE_ATTEND_HLJ ADD COLUMN SONG_INTRO VARCHAR(4000) AFTER VOTE_SCORE;

-- 曲目表：曲目名称字段修改
ALTER TABLE T_COMPETE_ATTEND_SONG MODIFY SONG_NAME VARCHAR(200);