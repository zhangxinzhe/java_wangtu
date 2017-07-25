/*
 * @(#)SystemControlServiceImpl.java    Created on 2013-8-23
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.service.SystemControlService;
import net.zdsoft.common.cache.BaseCacheServiceImpl;
import net.zdsoft.common.constant.ControlConstants;
import net.zdsoft.keel.util.DateUtils;

/**
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2013-8-23 下午10:29:13 $
 */
@Service("systemControlService")
public class SystemControlServiceImpl extends BaseCacheServiceImpl implements SystemControlService {
    @Override
    public boolean updateStatusToWorking(long agencyId, String controlName, String userName) {
        // 获取调用的类名和方法名
        String className = "";
        String methodName = "";
        StackTraceElement[] temp = Thread.currentThread().getStackTrace();
        int len = temp.length;
        for (int i = 2; i < len; i++) {
            if (temp[i].getClassName().startsWith("net.zdsoft")) {
                className = temp[i].getClassName();
                methodName = temp[i].getMethodName();
                break;
            }
        }

        // 获取当前日期和时间
        String currentTime = DateUtils.date2StringBySecond(new Date());

        // 并发控制项内容，格式：操作时间|操作用户名|操作类名|方法名|控制项名称
        // 比如：2015-07-2014:09:32|zhangsan|net.zdsoft.chnmaster.action.user.UserStudentAction|importStudent|用户导入
        StringBuffer value = new StringBuffer();
        value.append(currentTime);
        value.append("|");
        value.append(userName);
        value.append("|");
        value.append(className);
        value.append("|");
        value.append(methodName);
        value.append("|");
        value.append(ControlConstants.CONTROL_NAME.get(controlName));
        value.append("(");
        value.append(ControlConstants.CONTROL_TIME.get(controlName));
        value.append("分钟)");

        // 并发控制项的key
        String key = getControlKeyName(agencyId, controlName);

        // 并发控制项的key的缓存key
        String indexKeyName = getKeyName(ControlConstants.SYSTEM_CONTROL);

        // 控制最大时长，如果没有则默认15分钟
        Integer controTime = ControlConstants.CONTROL_TIME.get(controlName);
        if (controTime == null) {
            controTime = Integer.valueOf(15);
        }

        // 添加并发控制项
        if (addCache(key, value.toString(), controTime.intValue(), TimeUnit.MINUTES)) {
            // 成功：表示在缓存中不存在，控制项处于空闲状态
            putKeyIntoCache(indexKeyName, key);
            return true;
        }
        else {
            // 失败：表示在缓存存在，控制项处于使用状态
            return false;
        }

    }

    @Override
    public void updateStatusToIdle(long agencyId, String controlName) {
        // 称除控制项，使之处于空闲状态
        String key = getControlKeyName(agencyId, controlName);
        this.removeCache(key);
    }

    /**
     * 根据机构ID和并发控制名称得到缓存的KEY名称
     *
     * @param agencyId
     * @param controlName
     * @return
     */
    private String getControlKeyName(long agencyId, String controlName) {
        return getKeyName(ControlConstants.SYSTEM_CONTROL, agencyId, controlName);
    }

    @Override
    public Map<String, String> getSystemControlItem() {
        Map<String, String> map = new HashMap<String, String>();

        String indexKeyName = getKeyName(ControlConstants.SYSTEM_CONTROL);

        @SuppressWarnings("unchecked")
        Set<String> ids = (Set<String>) getCache(indexKeyName);
        if (CollectionUtils.isNotEmpty(ids)) {
            // 根据所有key，逐个获取该类型的所有数据
            for (String id : ids) {
                if (getCache(id) != null) {
                    String value = (String) getCache(id);

                    // 添加历时时长
                    String time = value.substring(0, value.indexOf("|"));
                    Date startTime = DateUtils.string2DateTime(time);
                    long l = new Date().getTime() - startTime.getTime();
                    long hour = (l / (60 * 60 * 1000));
                    long min = ((l / (60 * 1000)) - hour * 60);
                    long s = (l / 1000 - hour * 60 * 60 - min * 60);
                    StringBuffer newValue = new StringBuffer(value);
                    newValue.append("|");
                    newValue.append(hour);
                    newValue.append(":");
                    newValue.append(min);
                    newValue.append(":");
                    newValue.append(s);

                    map.put(id, newValue.toString());
                }
            }
        }

        return map;
    }

    @Override
    public void removeAllSystemControl() {
        // 从缓存中取出该机构该数据类型的缓存的key
        String indexKeyName = getKeyName(ControlConstants.SYSTEM_CONTROL);

        Set<String> ids = (Set<String>) getCache(indexKeyName);
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        // 根据所有key，逐个清除该类型的所有数据
        for (String id : ids) {
            removeCache(id);
        }
    }

    @Override
    public void removeSystemControlItem(String controlKey) {
        // 称除控制项，使之处于空闲状态
        this.removeCache(controlKey);
    }

}
