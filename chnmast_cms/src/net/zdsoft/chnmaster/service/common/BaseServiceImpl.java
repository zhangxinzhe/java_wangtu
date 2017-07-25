/* 
 * @(#)BaseServiceImpl.java    Created on 2013-8-16
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.common;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import net.zdsoft.chnmaster.entity.system.SystemLog;
import net.zdsoft.chnmaster.service.system.SystemLogService;
import net.zdsoft.common.cache.BaseCacheServiceImpl;
import net.zdsoft.common.exoperate.ExoperateProcessorDispatcher;

/**
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2013-8-16 上午11:04:22 $
 */
@SuppressWarnings("rawtypes")
@Repository("baseService")
public class BaseServiceImpl extends BaseCacheServiceImpl implements BaseService {

    @Resource
    protected SystemLogService systemLogService;

    /**
     * 附加业务转发服务接口
     */
    @Resource
    protected ExoperateProcessorDispatcher exoperateProcessorDispatcher;

    /**
     * 转发附加业务操作
     * 
     * @param operateType
     * @param values
     */
    public void dispatchExoperate(String operateType, Map values) {
        exoperateProcessorDispatcher.dispatchProcessor(operateType, values);
    }

    @Override
    public void logOperate(String operate, long userId, String ip) {
        if (StringUtils.isBlank(operate)) {
            return;
        }

        // 检查操作内容长度是否超出，最大允许650个汉字
        if (operate.length() > 650) {
            operate = operate.substring(0, 650) + "...";
        }
        SystemLog systemLog = new SystemLog();
        systemLog.setOperate(operate);
        systemLog.setUserId(userId);
        systemLog.setOperateIp(ip);
        systemLogService.addSystemLog(systemLog);
    }

    @Override
    public void logOperateAsyn(String operate, long userId, String ip) {
        if (StringUtils.isBlank(operate)) {
            return;
        }

        Map<String, Object> values = new HashMap<String, Object>();
        values.put("id", userId);
        values.put("operate", operate);
        values.put("operateIp", ip);
        dispatchExoperate("S_OPERATE_LOG", values);
    }

}
