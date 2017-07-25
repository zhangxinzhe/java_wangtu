/*
 * @(#)LoginUtils.java    Created on 2013-11-7
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.zdsoft.chnmaster.config.Config;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.common.utils.CookieUtil;
import net.zdsoft.common.utils.LoginUtil;
import net.zdsoft.common.utils.URLUtil;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2013-11-7 上午9:34:57 $
 */
public class LoginUtils extends LoginUtil {
    public static final String USERINFO_COOKIE_NAME_CMS = "userinfo_cookie_name_wangtu_cms"; // properties中配置的key

    private static LoginUtils singleton = new LoginUtils();

    public static LoginUtils getInstance() {
        return singleton;
    }

    /**
     * 获取当前的domain
     *
     * @return
     */
    @Override
    public String getDomain(HttpServletRequest request) {
        return Config.getParam(BaseConstants.DOMAIN_CMS);
    }

    /**
     * 登录用户信息Cookie的名字
     */
    @Override
    public String getUserInfoCookieName() {
        return Config.getParam(USERINFO_COOKIE_NAME_CMS);
    }

    @Override
    public String getCookieName(String name, HttpServletRequest request) {
        return URLUtil.cutHttp(getDomain(request)) + "_" + name;
    }

    /**
     * 判断用户是否登录
     *
     * @param request
     * @return
     */
    public boolean isLogin(HttpServletRequest request) {
        if (request != null) {
            return (null != getUserInfo(
                    CookieUtil.getCookieValueByName(request, getCookieName(getUserInfoCookieName(), request))));
        }

        return false;
    }

    /**
     * 将登录的用户信息写入到cookie中
     *
     * @param request
     * @param systemUser
     */
    public void writeUser(HttpServletRequest request, HttpServletResponse response, BaseUser baseUser) {
        // 失效期,-1表示不保存，即浏览器关掉时删除。
        int expiry = -1;
        CookieUtil.addCookieByName(request, response, getDomain(request),
                getCookieName(getUserInfoCookieName(), request), buildUserInfo(baseUser), expiry);
    }

    /**
     * 构造用户信息Cookie
     *
     * @param systemUser
     * @return
     */
    private String buildUserInfo(BaseUser baseUser) {
        StringBuffer userinfo = new StringBuffer();

        // 用户ID[0]
        userinfo.append(baseUser.getId()).append(COOKIE_SPLIT);
        // 用户真实姓名[1]
        String realname = "";
        try {
            realname = URLEncoder.encode(baseUser.getRealName(), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        userinfo.append(realname).append(COOKIE_SPLIT);

        // 用户类型[2]
        userinfo.append(baseUser.getType()).append(COOKIE_SPLIT);

        // 用户名[3]
        String username = "";
        try {
            username = URLEncoder.encode(baseUser.getUserName(), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        userinfo.append(username).append(COOKIE_SPLIT);

        // 当前时间戳[4]
        userinfo.append(new Date().getTime());

        baseUser.setRealName(realname);

        return byte2hex(encryptMode(KEY, userinfo.toString().getBytes()));
    }

    /**
     * 解析用户Cookie
     *
     * @param request
     * @return
     */
    @Override
    public BaseUser getUserInfo(String userInfoCookie) {
        if (StringUtils.isBlank(userInfoCookie)) {
            return null;
        }

        try {
            String info = new String(decryptMode(KEY, hex2byte(userInfoCookie)));
            String[] temp = StringUtils.split(info, COOKIE_SPLIT);

            int tempLen = 5;

            // 数据为空
            if (null == temp || temp.length != tempLen) {
                return null;
            }

            BaseUser baseUser = new BaseUser();
            baseUser.setId(Long.parseLong(temp[0]));
            baseUser.setRealName(URLDecoder.decode(temp[1], "UTF-8"));
            baseUser.setType(Integer.parseInt(temp[2]));
            baseUser.setUserName(URLDecoder.decode(temp[3], "UTF-8"));
            return baseUser;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
