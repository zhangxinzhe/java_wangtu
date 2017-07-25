--------------------------创建音乐网数据库和用户语法---------------------------
--------------------------初始部署时需要该语法，需root账户执行下面语法---------------------------
-- 删除库和用户
-- drop database chnmaster;
-- REVOKE GRANT OPTION ON *.* FROM chnmaster;
-- delete from mysql.user where user = 'chnmaster';
-- flush privileges;

use root;

-- 创建库
create database chnmaster character set utf8;

-- 创建用户
delete from mysql.user where user = 'chnmaster';
grant all privileges on chnmaster.* to chnmaster@'%' identified by 'zdsoft';
flush privileges;

-- 全局设置
set global log_bin_trust_function_creators = 1;