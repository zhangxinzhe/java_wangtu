-- 1.4.2.0 优化

-- ------------------------------------执行1 ddl -------------------------------------
-- 用户表用户名添加唯一索引
CREATE UNIQUE INDEX IDX_T_USER_UNIQUE_USERNAME
    ON T_USER(USERNAME);

