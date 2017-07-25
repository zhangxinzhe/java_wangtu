/*
 * @(#)CoolieUtils.java    Created on 2013-11-7
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.zdsoft.chnmaster.config.Config;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.utils.CookieUtil;
import net.zdsoft.common.utils.URLUtil;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2013-11-7 上午10:21:35 $
 */
public class CookieUtils extends CookieUtil {
    /**
     * 获取当前的domain
     *
     * @return
     */
    public static String getDomain() {
        return Config.getParam(BaseConstants.DOMAIN_CMS);
    }

    public static String getCookieName(String name) {
        return URLUtil.cutHttp(getDomain()) + "_" + name;
    }

    /**
     * 增加Cookie
     *
     * @param request
     * @param response
     * @param name
     * @param value
     * @param expiry
     *            失效时间,-1表不保存(浏览器关闭时删除),0表删除Cookie.
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
            int expiry) {
        addCookieByName(request, response, getDomain(), getCookieName(name), value, expiry);
    }

    /**
     * 删除Cookie
     *
     * @param request
     * @param name
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        removeCookieByName(request, response, getDomain(), getCookieName(name));
    }

    /**
     * 获取cookie信息
     *
     * @param request
     * @param name
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        return getCookieValueByName(request, getCookieName(name));
    }
}
