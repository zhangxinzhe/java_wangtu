package net.zdsoft.chnmaster.exoperate.system;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.entity.system.SystemLog;
import net.zdsoft.chnmaster.service.system.SystemLogService;
import net.zdsoft.common.exoperate.ExoperateProcessor;

@SuppressWarnings("rawtypes")
@Controller
public class UserOperateLogProcessor implements ExoperateProcessor {
    @Resource
    private SystemLogService systemLogService;

    @Override
    public void process(String operateType, Map values) {
        SystemLog systemLog = new SystemLog();
        String operate = (String) values.get("operate");

        // 检查操作内容长度是否超出，最大允许650个汉字
        if (operate.length() > 650) {
            operate = operate.substring(0, 650) + "...";
        }
        systemLog.setUserId((Long) values.get("id"));
        systemLog.setOperate(operate);
        systemLog.setOperateIp((String) values.get("operateIp"));
        systemLogService.addSystemLog(systemLog);
    }
}
