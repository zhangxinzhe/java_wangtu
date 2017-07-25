package net.zdsoft.common.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.VersionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.zdsoft.common.service.HttpClientService;

/**
 * 网络服务对象
 *
 * @author liyixi
 *
 */
@Service("HttpClientService")
public class HttpClientServiceImpl implements HttpClientService {
    private final static Logger log = LoggerFactory.getLogger(HttpClientServiceImpl.class);
    // 设置默认连接超时2秒钟(经过测试，该值在程序中表现为4秒左右)
    public final static int REQUEST_TIMEOUT = 2 * 1000;
    // 设置默认读取数据超时时间10秒钟
    public final static int SO_TIMEOUT = 10 * 1000;
    // 连接默认保持时间
    public final static int KEEP_TIME = 30 * 1000;
    // 连接池对象
    protected PoolingClientConnectionManager Pccm = null;
    // 监控线程
    protected connectionManageThread monitorThread = null;

    /************************************ 接口方法 ***************************************/
    @Override
    public DefaultHttpClient getLongConnClient() {
        // 设置组件参数, HTTP协议的版本,1.1/1.0/0.9
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
        HttpProtocolParams.setUseExpectContinue(params, true);
        // 设置连接超时时间
        params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, REQUEST_TIMEOUT);
        params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);
        DefaultHttpClient client = new DefaultHttpClient(Pccm, params);
        // 设置连接过期时间
        client.setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext arg1) {
                HeaderElementIterator it = new BasicHeaderElementIterator(
                        response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    log.debug("{}={}", param, value);
                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        try {
                            log.debug("目标服务器设置连接过期时间【{}】秒", value);
                            return Long.parseLong(value) * 1000;
                        }
                        catch (NumberFormatException ignore) {
                            ignore.printStackTrace();
                        }
                    }
                }
                return KEEP_TIME;
            }
        });
        return client;
    }

    @Override
    public DefaultHttpClient getShortConnClient(int conTimeOut, int readTimeOut) {
        // 设置组件参数, HTTP协议的版本,1.1/1.0/0.9
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
        HttpProtocolParams.setUseExpectContinue(params, true);
        // 设置连接和读取超时时间
        int cTimeOut = REQUEST_TIMEOUT;
        if (conTimeOut > 0) {
            cTimeOut = conTimeOut;
        }
        params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, cTimeOut);
        int rTimeOut = SO_TIMEOUT;
        if (readTimeOut > 0) {
            rTimeOut = readTimeOut;
        }
        params.setParameter(CoreConnectionPNames.SO_TIMEOUT, rTimeOut);
        return new DefaultHttpClient(params);
    }

    /************************************ 类方法 ***************************************/
    @PostConstruct
    public void init() {
        // 获取HttpClient版本信息
        VersionInfo coreInfo = VersionInfo.loadVersionInfo("org.apache.http", VersionInfo.class.getClassLoader());
        VersionInfo clientInfo = VersionInfo.loadVersionInfo("org.apache.http.client",
                VersionInfo.class.getClassLoader());
        System.out.println("====================HttpClientHttpClient版本信息:" + coreInfo.getModule() + "("
                + coreInfo.getRelease() + ") " + coreInfo.getModule() + "(" + clientInfo.getRelease() + ")");
        // 多连接的线程安全的管理器
        Pccm = new PoolingClientConnectionManager();
        // 设置每个路由的最大连接数
        System.out.println("====================HttpClient 客户端总连接最大数 ：200");
        Pccm.setMaxTotal(200);
        // 设置每个路由的默认连接数
        System.out.println("====================HttpClient 设置默认连接数: 20");
        Pccm.setDefaultMaxPerRoute(20);
        // 开启监控线程
        monitorThread = new connectionManageThread(Pccm);
        monitorThread.start();
    }

    @PreDestroy
    public void destory() {
        if (monitorThread != null) {
            monitorThread.shutdown();
        }
        if (Pccm != null) {
            Pccm.shutdown();
        }
        log.info("网络连接池成功关闭！");
    }

    /**
     * 维护线程
     *
     * @author liyixi
     *
     */
    protected static class connectionManageThread extends Thread {
        private final ClientConnectionManager connMgr;
        private volatile boolean shutdown;

        public connectionManageThread(ClientConnectionManager connMgr) {
            super();
            this.connMgr = connMgr;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(10000);
                        // 关闭过期的连接
                        connMgr.closeExpiredConnections();
                        // 关闭空闲时间超过30秒的连接
                        connMgr.closeIdleConnections(KEEP_TIME / 1000, TimeUnit.SECONDS);
                    }
                }
            }
            catch (InterruptedException ex) {
                // terminate
                ex.printStackTrace();
            }
        }

        /**
         * 关闭监控
         */
        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }
    }

    /************************************ 模块测试方法 ***************************************/
    // 带连接池的请求
    public void testLongConn() {
        try {
            DefaultHttpClient client = getLongConnClient();
            HttpGet get = new HttpGet("http://www.baidu.com:9000");
            ResponseHandler<String> response = new BasicResponseHandler();
            System.out.println(client.execute(get, response));
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    // 带连接池的请求
    public void testShortConn() {
        try {
            DefaultHttpClient client = getLongConnClient();
            HttpGet get = new HttpGet("http://www.baidu.com:9000");
            ResponseHandler<String> response = new BasicResponseHandler();
            System.out.println(client.execute(get, response));
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        HttpClientServiceImpl obj = new HttpClientServiceImpl();
        obj.init();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            obj.testLongConn();
            // obj.testShortConn();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));

    }

}
