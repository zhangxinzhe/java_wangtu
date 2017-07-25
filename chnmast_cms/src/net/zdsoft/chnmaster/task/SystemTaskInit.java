/* 
 * @(#)ServiceInitPlugIn.java    Created on 2006-7-14
 * Copyright (c) 2007 ZDSoft Networks, Inc. All rights reserved.
 * $Header: /project/etoh2/src/net/zdsoft/etoh2/client/ServiceInitPlugIn.java,v 1.74 2008/05/26 09:34:03 yeq Exp $
 */
package net.zdsoft.chnmaster.task;

import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.keel.util.concurrent.ScheduledTaskExecutor;

/**
 * 进行服务初始化的PlugIn.
 * 
 * @author huangwj
 * @version $Revision: 39494 $, $Date: 2013-06-20 00:46:10 +0800 (Thu, 20 Jun 2013) $
 */
public class SystemTaskInit implements ServletContextListener {

    private static ApplicationContext ctx;

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // 根据jvm配置判断是否在该tomcat中启动定时任务
        if (!"true".equals(System.getProperty(BaseConstants.JVM_NETSTUDY_MONITOR))) {
            return;
        }
        ctx = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());

        // 初始化系统定时任务
        SystemTask task = new SystemTask();
        // task.setSystemControlService((SystemControlService) ctx.getBean("systemControlService"));
        getScheduledTaskExecutor().scheduleAtFixedRate(task, 1, 5, TimeUnit.MINUTES); // 5分种检查一次
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    public static void setApplicationContext(AbstractXmlApplicationContext ctx) {
        SystemTaskInit.ctx = ctx;
    }

    private ScheduledTaskExecutor getScheduledTaskExecutor() {
        return (ScheduledTaskExecutor) ctx.getBean("scheduledTaskExecutor");
    }

}
