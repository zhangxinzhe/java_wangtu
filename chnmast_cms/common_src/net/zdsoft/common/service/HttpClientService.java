package net.zdsoft.common.service;

import org.apache.http.impl.client.DefaultHttpClient;


/**
 * 网络服务对象
 * 
 * @author liyixi
 * 
 */
public interface HttpClientService {
    // 默认的成功返回值
    public static final String SUCCESS = "success";
    // 默认的失败返回值
    public static final String FAILURE = "failure";
    
    /**
     * 获取一个长连接对象
     * @param isKeep
     * @return
     */
    public DefaultHttpClient getLongConnClient();
    
    /**
     * 获取一个短连接对象
     * @param conTimeOut 连接超时时间 （毫秒）
     * @param readTimeOut 读取超时时间（毫秒）
     * @return
     */
    public DefaultHttpClient getShortConnClient(int conTimeOut, int readTimeOut);
}
