package net.zdsoft.chnmaster.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.chnmaster.config.Config;
import net.zdsoft.chnmaster.service.QQService;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.utils.HttpUtil;

@Service("qQService")
public class QQServiceImpl implements QQService {
    @Override
    public JSONObject getAccessTokenAndOpenId(String code, String redirectUrl) throws Exception {
        String appKey = Config.getParam(BaseConstants.QQ_APP_KEY);
        String appId = Config.getParam(BaseConstants.QQ_APP_ID);

        // 获取accessToken
        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=";
        url += appId + "&client_secret=" + appKey + "&code=" + code + "&redirect_uri=" + redirectUrl;
        String obj = null;
        for (int i = 0; i < 4; i++) {
            try {
                obj = HttpUtil.getString(url);
                break;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isBlank(obj)) {
            throw new RuntimeException("qq接口访问失败!");
        }
        String accessToken = null;
        String[] array = obj.split("&");
        for (String string : array) {
            if (string.indexOf("access_token=") > -1) {
                accessToken = string.replace("access_token=", "");
                break;
            }
        }
        if (StringUtils.isBlank(accessToken)) {
            throw new RuntimeException("accessToken 为空!");
        }

        // 获取openId
        url = "https://graph.qq.com/oauth2.0/me?access_token=" + accessToken;
        for (int i = 0; i < 4; i++) {
            try {
                obj = HttpUtil.getString(url);
                break;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isBlank(obj)) {
            throw new RuntimeException("qq接口访问失败!");
        }
        String clientId = obj.substring(obj.indexOf("client_id\":\"") + 12, obj.indexOf("\",\"openid"));
        if (!appId.equals(clientId)) {
            throw new RuntimeException("clientId和appId不一致!");
        }
        String openId = null;
        Matcher m = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(obj);
        if (!m.find()) {
            throw new RuntimeException("openId 为空!");
        }
        openId = m.group(1);

        // 组装返回数据
        JSONObject returnJson = new JSONObject();
        returnJson.put("openId", openId);
        returnJson.put("accessToken", accessToken);
        return returnJson;
    }

    @Override
    public JSONObject getUserInfo(String openId, String accessToken) throws Exception {
        return getUserInfo(openId, accessToken, false);
    }

    @Override
    public JSONObject getUserInfo(String openId, String accessToken, boolean isAppQQLogin) throws Exception {
        String appId = null;
        // web或wap登录的appid
        if (!isAppQQLogin) {
            appId = Config.getParam(BaseConstants.QQ_APP_ID);
        }
        // app登录的appid
        else {
            appId = Config.getParam(BaseConstants.QQ_MOBILE_APP_ID);
        }
        String url = "https://graph.qq.com/user/get_user_info?access_token=" + accessToken + "&oauth_consumer_key="
                + appId + "&openid=" + openId;

        // {"city":"杭州",
        // "figureurl":"http://qzapp.qlogo.cn/qzapp/101274950/FDB1E40FB54559DE38BE07A9F5EFD7C3/30",
        // "figureurl_1":"http://qzapp.qlogo.cn/qzapp/101274950/FDB1E40FB54559DE38BE07A9F5EFD7C3/50",
        // "figureurl_2":"http://qzapp.qlogo.cn/qzapp/101274950/FDB1E40FB54559DE38BE07A9F5EFD7C3/100",
        // "figureurl_qq_1":"http://q.qlogo.cn/qqapp/101274950/FDB1E40FB54559DE38BE07A9F5EFD7C3/40",
        // "figureurl_qq_2":"http://q.qlogo.cn/qqapp/101274950/FDB1E40FB54559DE38BE07A9F5EFD7C3/100",
        // "gender":"男","is_lost":0,"is_yellow_vip":"0","is_yellow_year_vip":"0","level":"0","msg":"","nickname":".",
        // "province":"浙江","ret":0,"vip":"0","year":"1990","yellow_vip_level":"0"}
        // 从qq获取基本信息

        JSONObject returnJson = null;
        for (int i = 0; i < 4; i++) {
            try {
                returnJson = HttpUtil.getJson(url);
                break;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (returnJson == null || returnJson.getInteger("ret") < 0) {
            throw new RuntimeException("qq接口访问失败!");
        }

        return returnJson;
    }
}
