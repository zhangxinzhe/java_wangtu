/* 
 * @(#)CacheCall.java    Created on 2013-10-17
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.cache;

import java.util.List;
import java.util.Map;

/**
 * 缓存数据的核心回调接口
 * 
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2013-10-17 下午7:37:08 $
 */
public interface CacheCall {
    /**
     * 缓存key
     */
    public interface CacheKey {
        /**
         * 获取key
         * 
         * @return
         */
        public String fetchKey();
        /**
         * 数据类型
         * @return
         */
        public String getDataType();
        /**
         * 机构id
         * @return
         */
        public Long getAgencyId();
    }

    /**
     * 缓存对象参数
     */
    public interface CacheObjectParam<V> extends CacheKey {
        /**
         * 获取对象
         * 
         * @return
         */
        public V fetchObject();
    }

    /**
     * 缓存对象列表参数
     */
    public interface CacheObjectsParam<V> extends CacheKey {

        /**
         * 获取列表对象
         * 
         * @return
         */
        public List<V> fetchObjects();
    }

    /**
     * 缓存对象列表参数
     * 
     */
    public interface CacheObjectMapParam<K, V> extends CacheKey {

        /**
         * 获取Map对象
         * 
         * @return
         */
        public Map<K, V> fetchObjects();
    }
}
