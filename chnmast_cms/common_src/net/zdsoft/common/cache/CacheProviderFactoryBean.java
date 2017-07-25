/* 
 * @(#)CacheProviderFactoryBean.java    Created on 2011-8-25
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: CacheProviderFactoryBean.java 26737 2012-04-12 09:04:30Z yeq $
 */
package net.zdsoft.common.cache;

import org.springframework.beans.factory.config.AbstractFactoryBean;

import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.zdsoft.keel.cache.Cache;
import net.zdsoft.keel.cache.provider.SimpleCache;
import net.zdsoft.keel.cache.provider.XMemcachedCache;
import net.zdsoft.keel.util.Validators;

/**
 * memcache缓存bean
 * 
 * @author shenl
 * 
 */
public class CacheProviderFactoryBean extends AbstractFactoryBean<Object> {
    private String cacheServerUrl;

    @Override
    protected Object createInstance() throws Exception {
        // 如果没有 Memcached 服务器地址定义，则创建本地缓存
        if (Validators.isEmpty(cacheServerUrl)) {
            return new SimpleCache();
        }

        // 创建一个 XMemcachedCache 缓存
        XMemcachedCache cache = new XMemcachedCache();
        cache.setServers(cacheServerUrl.split(";"));
        System.out.println("====================XMemcachedCache 服务器地址：" + cacheServerUrl);
        cache.setCommandFactory(new BinaryCommandFactory()); // 设置客户端和服务端交互的协议为二进制协议
        // cache.setSessionLocator(new KetamaMemcachedSessionLocator()); // 设置缓存的分布算法为一致性哈希算法
        cache.setOperationTimeout(5000); // 设置客户端访问服务端的超时时间为1.5秒
        System.out.println("====================XMemcachedCache 处理超时时间：5000");
        cache.setConnectionPoolSize(4);// nio连接池大小，默认为1
        System.out.println("====================XMemcachedCache 线程数：4");
        cache.initialize();
        return cache;
    }

    @Override
    public Class<?> getObjectType() {
        return Cache.class;
    }

    public void setCacheServerUrl(String cacheServerUrl) {
        this.cacheServerUrl = cacheServerUrl;
    }
}
