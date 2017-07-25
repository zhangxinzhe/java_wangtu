package net.zdsoft.chnmaster.exoperate.system;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.entity.system.SystemLog;
import net.zdsoft.chnmaster.service.system.SystemLogService;
import net.zdsoft.common.exoperate.ExoperateProcessor;

@SuppressWarnings("rawtypes")
@Controller
public class UserLoginLogProcessor implements ExoperateProcessor {
    @Resource
    private SystemLogService systemLogService;

    @Override
    public void process(String operateType, Map values) {
        SystemLog systemLog = new SystemLog();
        systemLog.setOperate("登录系统");
        systemLog.setUserId((Long) values.get("id"));
        systemLog.setOperateIp((String) values.get("operateIp"));
        systemLogService.addSystemLog(systemLog);
    }
}
