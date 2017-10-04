package net.zdsoft.common.cache;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zdsoft.common.cache.CacheCall.CacheObjectMapParam;
import net.zdsoft.common.cache.CacheCall.CacheObjectParam;
import net.zdsoft.common.cache.CacheCall.CacheObjectsParam;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.keel.cache.Cache;

@SuppressWarnings("unchecked")
public class BaseCacheServiceImpl {

    protected Logger log = LoggerFactory.getLogger(getClass());
    // @Resource
    private Cache<String, Object> cacheBean;

    private final static String live_key = "chnamster.memcached_live_incr";
    // 缓存无法连接的毫秒数
    private long FAILURE_TIME = 0;
    // 延时判断缓存是否可用
    private final static int WAIT_TIME = 5 * 1000;

    /**
     * 从缓存中取值
     *
     * @param key
     * @return
     */
    protected Object getCache(String key) {
        try {
            return cacheBean.get(key);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 往缓存中放值
     *
     * @param key
     * @param value
     */
    protected void putCache(String key, Object value) {
        try {
            cacheBean.put(key, value);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 往缓存中放值，并设置默认的超时时间
     *
     * @param key
     * @param value
     */
    protected void putCacheDefExpiry(String key, Object value) {
        try {
            cacheBean.put(key, value, BaseConstants.CACHE_MAX_DAY, TimeUnit.DAYS);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 往缓存中放值，并且设置超时时间
     *
     * @param key
     * @param value
     * @param num
     *            时长
     * @param timeUnit
     *            时长单位
     */
    protected void putCache(String key, Object value, int num, TimeUnit timeUnit) {
        try {
            cacheBean.put(key, value, num, timeUnit);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 从缓存中删除
     *
     * @param key
     */
    protected void removeCache(String key) {
        try {
            cacheBean.remove(key);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 将某个计数器进行加操作
     *
     * @param key
     * @param delta
     */
    protected long incrCache(String key, long delta) {
        try {
            // 计数器从1开始，避免与异常时返回的数据冲突导致无法判断是否正常返回数据
            return cacheBean.incr(key, delta, 1);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * 通过计数器来判断缓存是否可用
     *
     * @param key
     * @param delta
     */
    protected boolean canUse() {
        long currentTime = System.currentTimeMillis();
        try {
            // 当缓存挂了之后 一分钟之内 都从数据库获取
            if (currentTime - FAILURE_TIME <= WAIT_TIME) {
                return false;
            }
            long incr = cacheBean.incr(live_key, 1, 10);
            if (incr < 10) {
                FAILURE_TIME = currentTime;
                return false;
            }
            else {
                removeCache(live_key);
                return true;
            }
        }
        catch (Exception e) {
            FAILURE_TIME = currentTime;
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 将数据新添加到缓存中，如果key在缓存中已经存在则返回false，否则返回true
     *
     * @param key
     * @param value
     */
    protected boolean addCache(String key, Object value) {
        try {
            return cacheBean.add(key, value);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 将数据新添加到缓存中，如果key在缓存中已经存在则返回false，否则返回true，并指key的有效期
     *
     * @param key
     * @param value
     * @param num
     * @param timeUnit
     */
    protected boolean addCache(String key, Object value, int num, TimeUnit timeUnit) {
        try {
            return cacheBean.add(key, value, num, timeUnit);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 清空缓存中所有数据
     */
    protected void clearCacheAll() {
        try {
            cacheBean.flushAll();
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 组装key值（仅仅组装key）
     *
     * @param params
     * @return
     */
    protected String getKeyName(String... params) {
        StringBuffer key = new StringBuffer(BaseConstants.CACHE_PREFIX);

        for (int i = 0; i < params.length; i++) {
            key.append("_");
            key.append(params[i]);
        }

        return key.toString();
    }

    /**
     * 组装key值，并将此key同样缓存起来，供其他地方使用<br>
     * 规则为：netstudy_{DataType}_{agencyId}_{methodName}<br>
     * 如：netstudy_course_1_listCourseOfRecommend<br>
     * 不属于任何机构的数据（如系统参数）参数agencyId请写0<br>
     * methodName参数通常是方法名称，也可以根据实际情况写为其他字符串，或方法名+其他字符串
     *
     * @param DataType
     * @param agencyId
     * @param methodName
     * @return
     */
    protected String getKeyName(String dataType, long agencyId, String methodName) {
        // 组装key
        StringBuffer key = new StringBuffer(BaseConstants.CACHE_PREFIX);
        key.append("_");
        key.append(dataType);

        key.append("_");
        key.append(methodName);

        key.append("_");
        key.append(Long.valueOf(agencyId));

        return key.toString();
    }

    /**
     * 将key也缓存起来
     *
     * @param indexKey
     * @param key
     */
    protected void putKeyIntoCache(final String indexKey, final String key) {
        Set<String> ids = (Set<String>) getCache(indexKey);
        if (CollectionUtils.isEmpty(ids)) {
            ids = new HashSet<String>();
        }
        ids.add(key);
        putCache(indexKey, ids);
    }

    abstract class MemcacheCacheLock {
        private Object data;

        /**
         * 具体执行代码
         */
        public abstract Object doing();

        /**
         * 加锁执行代码
         *
         * @param indexKey
         */
        public MemcacheCacheLock(String indexKey) {
            // /////////并发控制///////////
            String incrKey = indexKey + "_lock";
            long incr = incrCache(incrKey, 1);
            int count = 0;
            while (incr != 1) {
                if (incr == -1) {
                    addCache(incrKey, "0");
                }
                if (count > 50) {
                    // 等待超过50次，则不再继续等待
                    removeCache(incrKey);
                }
                else {
                    try {
                        Thread.sleep(25);
                        count++;
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                incr = incrCache(incrKey, 1);
            }
            // /////////并发控制///////////
            try {
                data = doing();
            }
            finally {
                removeCache(incrKey);
            }
        }

        public Object getData() {
            return data;
        }
    }

    /**
     * 刷新缓存，清除某机构的某类型的缓存数据
     *
     * @param DataType
     * @param agencyId
     * @return
     */
    protected void clearCache(String dataType, long agencyId) {
        // 从缓存中取出该机构该数据类型的缓存的key
        String indexKeyName = getKeyName(dataType, String.valueOf(agencyId));
        Set<String> ids = (Set<String>) getCache(indexKeyName);
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        // 根据所有key，逐个清除该类型的所有数据
        for (String id : ids) {
            removeCache(id);
        }

        // 最后再清除缓存的key
        // removeCache(indexKeyName);
    }

    /**
     * 刷新缓存，清除某一个key的缓存数据
     *
     * @param DataType
     * @param agencyId
     * @param methodName
     * @return
     */
    protected void clearCache(String dataType, long agencyId, String methodName) {
        // 从缓存中取出该机构该数据类型的缓存的key
        String indexKeyName = getKeyName(dataType, agencyId, methodName);

        removeCache(indexKeyName);
    }

    /**
     * 从缓存中取单个对对象，没有则调用对象获取方法，初始化缓存（缓存的是对象）
     *
     * @param param
     * @return
     */
    public <T> T getObjectFromCache(final CacheObjectParam<T> param) {
        // 如果缓存不可用，则通过数据库获取数据
        if (!canUse()) {
            return param.fetchObject();
        }
        final String key = param.fetchKey();
        // 查询缓存数据
        T object = (T) getCache(key);
        if (object == null) {
            final String indexKeyName = getKeyName(param.getDataType(), String.valueOf(param.getAgencyId()));
            // 加锁进行操作
            MemcacheCacheLock lock = new MemcacheCacheLock(indexKeyName) {
                @Override
                public Object doing() {
                    Object o = getCache(key);
                    if (o == null) {
                        // 查询数据，放入缓存
                        putKeyIntoCache(indexKeyName, key.toString());
                        Object object = param.fetchObject();
                        if (object == null) {
                            return null;
                        }
                        putCache(key, object);
                        return object;
                    }
                    else {
                        return o;
                    }
                }
            };
            object = (T) lock.getData();
        }
        return object;
    }

    /**
     * 从缓存中取单个对对象，缓存的是对象列表
     *
     * @param param
     * @return
     */
    public <T> List<T> getObjectsFromCache(final CacheObjectsParam<T> param) {
        // 如果缓存不可用，则通过数据库获取数据
        if (!canUse()) {
            return param.fetchObjects();
        }
        final String key = param.fetchKey();
        // 查询缓存数据
        List<T> objects = (List<T>) getCache(key);
        if (objects == null) {
            final String indexKeyName = getKeyName(param.getDataType(), String.valueOf(param.getAgencyId()));
            // 加锁进行操作
            MemcacheCacheLock lock = new MemcacheCacheLock(indexKeyName) {
                @Override
                public Object doing() {
                    Object o = getCache(key);
                    if (o == null) {
                        // 查询数据，放入缓存
                        putKeyIntoCache(indexKeyName, key.toString());
                        List<T> objects = param.fetchObjects();
                        if (objects == null) {
                            return null;
                        }
                        putCache(key, objects);
                        return objects;
                    }
                    else {
                        return o;
                    }
                }
            };
            objects = (List<T>) lock.getData();
        }
        return objects;
    }

    /**
     * 从缓存中取单个对对象，缓存的是对象列表
     *
     * @param param
     * @return
     */

    public <K, V> Map<K, V> getObjectMapFromCache(final CacheObjectMapParam<K, V> param) {
        // 如果缓存不可用，则通过数据库获取数据
        if (!canUse()) {
            return param.fetchObjects();
        }
        final String key = param.fetchKey();
        // 查询缓存数据
        Map<K, V> objects = (Map<K, V>) getCache(key);
        if (objects == null) {
            final String indexKeyName = getKeyName(param.getDataType(), String.valueOf(param.getAgencyId()));
            // 加锁进行操作
            MemcacheCacheLock lock = new MemcacheCacheLock(indexKeyName) {
                @Override
                public Object doing() {
                    Object o = getCache(key);
                    if (o == null) {
                        // 查询数据，放入缓存
                        putKeyIntoCache(indexKeyName, key.toString());
                        Map<K, V> objects = param.fetchObjects();
                        if (objects == null) {
                            return null;
                        }
                        putCache(key, objects);
                        return objects;
                    }
                    else {
                        return o;
                    }
                }
            };
            objects = (Map<K, V>) lock.getData();
        }
        return objects;
    }

}
