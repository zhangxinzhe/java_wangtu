/*
 * @(#)systemTask.java    Created on 2013-8-24
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zdsoft.keel.util.concurrent.AbstractRunnableTask;

/**
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2013-8-24 下午3:47:39 $
 */
public class SystemTask extends AbstractRunnableTask {

    private final Logger log = LoggerFactory.getLogger(SystemTask.class);

    public SystemTask() {
        super("系统定时任务，并发控制状态检测与重置");
    }

    @Override
    public void processTask() throws Exception {
        // log.debug("=============S启动并发控制状态检测的定时任务");
        // systemControlService.updateTimeoutStatus();
        // log.debug("=============E结束并发控制状态检测的定时任务");

    }

}
