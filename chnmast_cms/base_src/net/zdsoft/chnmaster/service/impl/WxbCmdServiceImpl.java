package net.zdsoft.chnmaster.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.service.WxbCmdService;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.service.HttpClientService;
import net.zdsoft.common.utils.JsonUtil;
import net.zdsoft.keel.util.SecurityUtils;

/**
 * 无限宝命令服务
 *
 * @author liyixi
 *
 */
@Service("wxbCmdService")
public class WxbCmdServiceImpl implements WxbCmdService {
    private final static Logger loger = LoggerFactory.getLogger(WxbCmdServiceImpl.class);
    // 时间戳KEY
    private final static String TICK = "tick";
    // 校验码KEY
    private final static String KEY = "key";
    // 更新间隔时间
    public final static int INTERVAL_TIME = 10;
    // 安全校验参数
    private static String securityKey;

    @Resource
    private HttpClientService httpClientService;

    /************************************ 类接口方法 ***************************************/
    @Override
    public Map<String, String> getSoftDogInfo(String netpath) {
        if (StringUtils.isBlank(netpath)) {
            return writeError("服务器地址不能为空！");
        }
        try {
            DefaultHttpClient client = httpClientService.getLongConnClient();
            HttpGet get = new HttpGet(getShareField(netpath, "/getdoginfo"));
            loger.debug("请求地址：{}", get.toString());
            ResponseHandler<String> response = new BasicResponseHandler();
            String json = client.execute(get, response);
            loger.debug("响应内容：{}", json);
            if (StringUtils.isBlank(json)) {
                return writeError("无限宝登录服务器返回NULL的加密狗信息！");
            }
            Map<String, String> result = JsonUtil.toMap(json);
            String resultCt = result.get("result");
            if (!"1".equals(resultCt)) {
                // 请求加密狗信息失败,返回无限宝提供的错误信息
                return writeError(result.get("error"));
            }
            return result;
        }
        catch (Exception e) {
            loger.error("请求加密狗信息失败", e);
            return writeError("请求加密狗信息失败！");
        }
    }

    /************************************ 类方法 ***************************************/
    /**
     * 获取安全校验参数<br/>
     *
     * @return 注意：params[0]为ticket params[1]为校验码
     */
    private String[] getSecurityParams() {
        long seconds = 0;
        String checkCode = null;
        if (StringUtils.isBlank(securityKey)) {
            seconds = Calendar.getInstance().getTimeInMillis() / 1000;
            checkCode = SecurityUtils.encodeByMD5(seconds + BaseConstants.WINUPON_VERIFYKEY);
            securityKey = seconds + "#" + checkCode;
        }
        else {
            String keys[] = securityKey.split("#");
            seconds = Long.parseLong(keys[0]);
            checkCode = keys[1];
            long curSeconds = Calendar.getInstance().getTimeInMillis() / 1000;
            // 取间隔分钟数
            long intervalMinite = (curSeconds - seconds) / 60;
            if (intervalMinite > INTERVAL_TIME) { // 超时 更新校验码
                seconds = curSeconds;
                checkCode = SecurityUtils.encodeByMD5(curSeconds + BaseConstants.WINUPON_VERIFYKEY);
                securityKey = seconds + "#" + checkCode;
            }
        }
        return new String[] { String.valueOf(seconds), checkCode };
    }

    /**
     * 获取请求公共部分
     *
     * @param net
     * @param action
     *            格式: /test
     * @return
     */
    private String getShareField(String net, String action) {
        StringBuffer urlBuf = new StringBuffer(net);
        urlBuf.append(action);
        String[] securityParams = getSecurityParams();
        String tick = securityParams[0];
        urlBuf.append("?" + TICK + "=" + tick);
        String checkCode = securityParams[1];
        urlBuf.append("&" + KEY + "=" + checkCode);
        return urlBuf.toString();
    }

    /**
     * 返回错误信息
     *
     * @param msg
     * @return
     */
    private Map<String, String> writeError(String msg) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ERROR", msg);
        return map;
    }
}
