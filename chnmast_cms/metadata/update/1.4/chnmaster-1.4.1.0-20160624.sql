-- 1.4.1.0 （支付宝配置信息---测试环境不执行，外网环境执行）

-- ------------------------------------执行1 ddl -------------------------------------

-- ------------------------------------执行2 dml -------------------------------------
-- 支付宝支付参数修改
delete from T_SYSTEM_CONFIG where code='alipay.rsa.private.key';
insert into T_SYSTEM_CONFIG(CODE, NAME, VALUE, DESCRIPTION, CAN_VIEW, can_edit, validate, order_no)
VALUES('alipay.rsa.private.key', '公司支付宝RSA私钥', 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM4JA24PKDsKU2IpTPO+fOokOF99ZldY/hjm3bZmWZFgPp0JLKWYFem64Ukq8Htan1i8ZOZKiyiN04XSO4k5YmvJ312Z5ZARfS8hKOPl2WGuuM7CFUPyPBW+sA+u17IFtKnMR1UC2jOhUA9bSvBF/qPffziDFj9c8sXLm3siPFQdAgMBAAECgYASEHQ+iBGs64DYEGrwy/jDxp0yshnw+pp8sHEr9tlPh7K1rgI+GpGBRqNN+PfQuOdTrmDiWBLPYW/0QbR6mY9pX5WcBZkCs/gtOsLCE2p5iN3cF09fx1KdupMdk91NTeNnuKOj5M/6KWFJhxHKzuXacsmco68zQT+tECWfFIAUgQJBAPSSkawsSjSJ9dT89FlklzioZXU+t48NkxcC+p3klcwE/QGEVQlI8pHbfedK/nmvZ82fwyQbGCQQ6hZayfsd1TECQQDXqXvbra0A6NWL+MB+Q4/yv+gLjC+Y+9oDrzbLJBDx7LrHlRY7J57qxLkAA8gQ5Ci0k6e/ONyIJQelGBgnPuKtAkAPuOBedhZrHDdOcthuarYB8WlcSSTZsGC1SP69abVrgSKWl2A0EZluYPLODJchUXJV2KBd0NQTdXbm6v0zBG7xAkEAy6DDyhCaoZk2yQr+9jlk2ZhJyV18M77Zg1EVM4nCVitn0Lr8Wq52ZsWIyHLJnOlRZe9lHDDcSdBs/31YkPXXoQJADQxK7uY1hh3xXzx8hoBOyjYAxiIrUJ725I0MzN5P6jNZEI21EOCih/wdD/Sk6GTKIh+954y5gHrL2THZxrcqHg==', '公司支付宝账户私钥', 0, 0, 'required', 138);
delete from T_SYSTEM_CONFIG where code='alipay.rsa.public.key';
insert into T_SYSTEM_CONFIG(CODE, NAME, VALUE, DESCRIPTION, CAN_VIEW, can_edit, validate, order_no)
values('alipay.rsa.public.key', '支付宝RSA公钥', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOCQNuDyg7ClNiKUzzvnzqJDhffWZXWP4Y5t22ZlmRYD6dCSylmBXpuuFJKvB7Wp9YvGTmSosojdOF0juJOWJryd9dmeWQEX0vISjj5dlhrrjOwhVD8jwVvrAPrteyBbSpzEdVAtozoVAPW0rwRf6j3384gxY/XPLFy5t7IjxUHQIDAQAB', '支付宝RSA公钥', 0, 0, 'required', 140);
commit;