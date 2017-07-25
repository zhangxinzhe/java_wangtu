package net.zdsoft.common.asyn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 异步写出配置项
 *
 * @author wenchu.cenwc
 *
 */
public class AsynConfig {
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(AsynConfig.class);

    private static AsynConfig config;

    /**
     * 配置文件地址
     */
    public static final String ASYN_CONFIG = "asyn-config.properties";

    /**
     * flush的间隔时间，单位秒
     */
    private int flushInterval = 5;

    /**
     * 创建分页的线程数
     */
    private int createConsumerThreadCount = 3;

    /**
     * 当记录数达到多少的时候写入数据库
     */
    private int bundleMaxCount = 20;

    /**
     * 输出记录Bundle的工作线程池大小
     */
    private int writerMaxCount = 3;

    public static AsynConfig getInstance() {
        return getInstance(ASYN_CONFIG);
    }

    public static AsynConfig getInstance(String conf) {
        if (config == null) {
            config = new AsynConfig(conf);
        }

        return config;
    }

    private AsynConfig(String conf) {
        // TODO 暂时不从配置文件读参数
        // Properties prop;
        //
        // try
        // {
        // prop = new Properties();
        //
        // URL url = Thread.currentThread().getContextClassLoader()
        // .getResource(conf);
        //
        // if (url != null)
        // prop.load(url.openStream());
        //
        // if (prop.get("flushInterval") != null)
        // flushInterval = Integer.parseInt((String)prop.get("flushInterval"));
        //
        // if (prop.get("createConsumerThreadCount") != null)
        // createConsumerThreadCount =
        // Integer.parseInt((String)prop.get("createConsumerThreadCount"));
        //
        // if (prop.get("bundleMaxCount") != null)
        // bundleMaxCount =
        // Integer.parseInt((String)prop.get("bundleMaxCount"));
        //
        // if (prop.get("writerMaxCount") != null)
        // writerMaxCount =
        // Integer.parseInt((String)prop.get("writerMaxCount"));
        //
        // }
        // catch(Exception ex)
        // {
        // Logger.error("Load AsynWriter error!",ex);
        // throw new java.lang.RuntimeException(ex);
        // }
    }

    public int getFlushInterval() {
        return flushInterval;
    }

    public void setFlushInterval(int flushInterval) {
        this.flushInterval = flushInterval;
    }

    public int getCreateConsumerThreadCount() {
        return createConsumerThreadCount;
    }

    public void setCreateConsumerThreadCount(int createConsumerThreadCount) {
        this.createConsumerThreadCount = createConsumerThreadCount;
    }

    public int getBundleMaxCount() {
        return bundleMaxCount;
    }

    public void setBundleMaxCount(int bundleMaxCount) {
        this.bundleMaxCount = bundleMaxCount;
    }

    public int getWriterMaxCount() {
        return writerMaxCount;
    }

    public void setWriterMaxCount(int writerMaxCount) {
        this.writerMaxCount = writerMaxCount;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();

        result.append("flushInterval:").append(flushInterval).append(",createConsumerThreadCount:")
                .append(createConsumerThreadCount).append(",bundleMaxCount:").append(bundleMaxCount)
                .append(",writerMaxCount:").append(writerMaxCount);

        return result.toString();
    }

}
