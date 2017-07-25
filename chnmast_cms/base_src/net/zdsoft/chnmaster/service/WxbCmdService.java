package net.zdsoft.chnmaster.service;

import java.util.Map;

/**
 * 无限宝命令服务
 *
 * @author liyixi
 *
 */
public interface WxbCmdService {

    /**
     * 请求加密狗信息
     *
     * @param netPath
     *            网络地址，格式：http://host:port
     * @return
     */
    public Map<String, String> getSoftDogInfo(String netPath);

}
