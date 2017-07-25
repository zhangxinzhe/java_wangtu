-- 1.3.3.0版 优化语法（外网环境执行）

-- 添加微信公众账号id
delete from T_SYSTEM_CONFIG where code='wechatpay.app_id';
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('wechatpay.app_id', '微信公众账号id', 'wxda745a15ce29404c', '微信公众账号id', 1, 0, 'config-max-length-50', 150);
-- 添加微信公众账号应用密钥
delete from T_SYSTEM_CONFIG where code='wechatpay.app_secret';
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('wechatpay.app_secret', '微信公众账号应用密钥', '8f8d92154a16bb58e344565cc795801a', '微信公众账号应用密钥', 1, 0, 'config-max-length-50', 150);
-- 添加微信商户号id
delete from T_SYSTEM_CONFIG where code='wechatpay.mch_id';
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('wechatpay.mch_id', '微信商户号id', '1325102101', '微信商户号id', 1, 0, 'config-max-length-50', 150);
-- 添加微信安全验证key
delete from T_SYSTEM_CONFIG where code='wechatpay.verify_key';
insert into T_SYSTEM_CONFIG(code, name, value, description, can_view, can_edit, validate, order_no)
values('wechatpay.verify_key', '微信安全验证key', 'a9770e412fb309cf8de2c8b7cf6948d0', '微信安全验证key', 1, 0, 'config-max-length-50', 150);
commit;

