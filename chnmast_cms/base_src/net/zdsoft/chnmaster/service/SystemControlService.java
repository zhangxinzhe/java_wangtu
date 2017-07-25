/*
 * @(#)SystemControlService.java    Created on 2013-8-23
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.service;

import java.util.Map;

/**
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2013-8-23 下午10:28:56 $
 */
public interface SystemControlService {
    /**
     * 根据任务名称和机构ID判断当前并发控制是否空闲，如果空闲就可以执行操作了，并把状态改为工作<br>
     * 返回true表示为空闲，可以开始导入等任务，返回false表示正忙，不能开始新的导入任务
     *
     * @param agencyId
     *            如果没有机构或全平台控制，请写0
     * @param controlName
     *            并发控制项，参看常量类Constants.java
     * @param userName
     *            操作用户名
     * @return
     */
    public boolean updateStatusToWorking(long agencyId, String controlName, String userName);

    /**
     * 根据机构ID和任务名称更改状态为空闲
     *
     * @param agencyId
     *            如果没有机构或全平台控制，请写0
     * @param controlName
     *            并发控制项，参看常量类Constants.java
     * @return
     */
    public void updateStatusToIdle(long agencyId, String controlName);

    /**
     * 获取所有正在处于使用状态的并发控制项
     *
     * @return
     */
    public Map<String, String> getSystemControlItem();

    /**
     * 清除正在处于使用状态的某个并发控制项，使之处于空闲状
     *
     * @param key
     * @return
     */
    public void removeSystemControlItem(String controlKey);

    /**
     * 清除所有并发控制项
     */
    public void removeAllSystemControl();

}
