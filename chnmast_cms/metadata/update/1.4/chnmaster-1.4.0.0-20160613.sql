-- 1.4.0.0 优化语法（个人用户-分组类型）

-- ------------------------------------执行1 ddl -------------------------------------
-- 用户分组类型表【1.4.0.0】
CREATE TABLE T_USER_GROUP_TYPE(
  ID                INTEGER NOT NULL AUTO_INCREMENT,
  TITLE             VARCHAR(75) NOT NULL,           -- 分类名称
  IS_CAN_DEL        TINYINT DEFAULT 1,              -- 是否可以删除（0否；1是）
  PRIMARY KEY (ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


ALTER TABLE T_USER ADD COLUMN GROUP_TYPE_ID INTEGER AFTER IS_TUTOR;


-- ------------------------------------执行2 dml -------------------------------------
-- 默认分类
INSERT INTO T_USER_GROUP_TYPE(TITLE, IS_CAN_DEL) VALUES ('默认分类', 0);

commit;



