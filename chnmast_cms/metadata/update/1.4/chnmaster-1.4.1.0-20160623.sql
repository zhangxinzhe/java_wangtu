-- 1.4.1.0 （ 微信支付参数修改）；1.4.1.1（ftp上传）

-- ------------------------------------执行1 ddl -------------------------------------
-- 视频上传方式（0视频上传，1ftp维护）
ALTER TABLE T_COURSE_VIDEO ADD COLUMN UPLOAD_TYPE INTEGER DEFAULT 0 AFTER PLAY_NUM;


-- ------------------------------------执行2 dml -------------------------------------
-- 添加微信公众账号id（app）
delete from T_SYSTEM_CONFIG where code='wechatpay.app.app_id';
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('wechatpay.app.app_id', '微信公众账号id（app）', 'wx67f3425b01d4122a', '微信公众账号id（app）', 1, 0, 'config-max-length-50', 150);
-- 添加微信公众账号应用密钥
delete from T_SYSTEM_CONFIG where code='wechatpay.app.app_secret';
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('wechatpay.app.app_secret', '微信公众账号应用密钥（app）', '7eb20d1e8f354e3aa8da7cb497e77113', '微信公众账号应用密钥（app）', 1, 0, 'config-max-length-50', 150);
-- 添加微信商户号id
delete from T_SYSTEM_CONFIG where code='wechatpay.app.mch_id';
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('wechatpay.app.mch_id', '微信商户号id（app）', '1353618502', '微信商户号id（app）', 1, 0, 'config-max-length-50', 150);
-- 添加微信安全验证key
delete from T_SYSTEM_CONFIG where code='wechatpay.app.verify_key';
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('wechatpay.app.verify_key', '微信安全验证key（app）', '75ec8c2f935742c2b7c9e871013b71fa', '微信安全验证key（app）', 1, 0, 'config-max-length-50', 150);

