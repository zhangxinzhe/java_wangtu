/* 
 * @(#)InjectBeanSelfProcessor.java    Created on 2008-5-4
 * Copyright (c) 2008 ZDSoft Networks, Inc. All rights reserved.
 * $Id: InjectBeanSelfProcessor.java 25833 2012-03-14 04:36:24Z yeq $
 */
package net.zdsoft.common.service;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 设置某个Bean的代理类到它自身的Processor.
 * 
 * @author huangwj
 * @version $Revision: 25833 $, $Date: 2012-03-14 12:36:24 +0800 (星期三, 14 三月 2012) $
 */
public class InjectBeanSelfProcessor implements BeanPostProcessor, ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof BeanSelfAware)) {
            return bean;
        }
        BeanSelfAware myBean = (BeanSelfAware) bean;
        // ③ 如果当前对象是AOP代理对象，直接注入
        if (AopUtils.isAopProxy(bean)) {
            myBean.setSelf(bean);
        }
        else {
            // ④ 如果当前对象不是AOP代理，则通过context.getBean(beanName)获取代理对象并注入
            // 此种方式不适合解决prototype Bean的代理对象注入
            myBean.setSelf(context.getBean(beanName));
        }
        return myBean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
