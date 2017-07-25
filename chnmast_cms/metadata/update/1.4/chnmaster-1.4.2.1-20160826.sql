-- 1.4.2.1（无限宝参数控制）

-- ------------------------------------执行1 ddl -------------------------------------

-- ------------------------------------执行2 dml -------------------------------------
delete from T_SYSTEM_CONFIG where code in('wxb.module_params');
insert into T_SYSTEM_CONFIG(CODE, NAME, VALUE, DESCRIPTION, CAN_VIEW, can_edit, validate, order_no)
values('wxb.module_params', '无限宝参数控制', 'NoDocShare:1, NoWebShare:1, NoSysCheck:0, NoAppShare:1, NoPlayMedia:0, NoClassTest:1, NoClassMgr:0, NoNetDisk:1, NoRecord:0, NoRollCall:1, NoVote:0, NoAskQ:0, NoSEG:1', '无限宝PC客户端模块配置，隐藏/显示一些可定义的模块\r\n请用键值对写法，以英文":"隔开，参数之间以英文","隔开\r\n如（param1:value1,param2:value2,param3:value3...）', 1, 1, 'config-max-length-2000', 121);
