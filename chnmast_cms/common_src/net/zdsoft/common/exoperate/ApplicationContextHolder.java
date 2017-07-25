package net.zdsoft.common.exoperate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 用于初始化持有spring的ApplicationContext
 *
 * @author fangb
 *
 */
public class ApplicationContextHolder implements ServletContextListener {

    private static ApplicationContext ctx;

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ctx = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    public static void setApplicationContext(AbstractXmlApplicationContext ctx) {
        ApplicationContextHolder.ctx = ctx;
    }

}
