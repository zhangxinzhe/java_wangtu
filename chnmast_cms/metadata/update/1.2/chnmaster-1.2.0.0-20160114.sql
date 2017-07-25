-- 1.2.0.0版 优化 语法

-- 添加字段：新闻公告说明（自动截取详细内容前150字）
alter table T_INDEX_INFO add column INFO_SHORT VARCHAR(450) NULL AFTER INFO_TYPE;