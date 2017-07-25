package net.zdsoft.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import net.zdsoft.keel.util.SecurityUtils;

/**
 * Cookie的工具类
 * 
 * @author winupon
 * 
 */
public class CookieUtil {
    /**
     * 获取指定名字的cookie值
     * 
     * @param request
     * @param name
     * @return
     */
    public static String getCookieValueByName(HttpServletRequest request, String name) {
        Cookie[] c = request.getCookies();
        if (ArrayUtils.isEmpty(c)) {
            return "";
        }
        name = SecurityUtils.encodeByMD5(name);
        for (int i = 0; i < c.length; i++) {
            String cookname = c[i].getName();
            if (cookname.compareToIgnoreCase(name) == 0) {
                return c[i].getValue();
            }
        }
        return "";
    }

    /**
     * 修改对应名字的Cookie的值(确保name对应Cookie存在)
     * 
     * @param request
     * @param name
     * @param newValue
     */
    public static void updateCookieValueByName(HttpServletRequest request, String name, String newValue) {
        Cookie[] c = request.getCookies();
        if (ArrayUtils.isEmpty(c)) {
            return;
        }
        name = SecurityUtils.encodeByMD5(name);
        for (int i = 0; i < c.length; i++) {
            String cookname = c[i].getName();
            if (cookname.compareToIgnoreCase(name) == 0) {
                c[i].setValue(newValue);
                return;
            }
        }
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
    public static void addCookieByName(HttpServletRequest request, HttpServletResponse response, String domain,
            String name, String value, int expiry) {
        if (StringUtils.isBlank(name)) {
            return;
        }
        name = SecurityUtils.encodeByMD5(name);
        Cookie c = new Cookie(name, value);
        c.setDomain(URLUtil.cutHttp(domain));
        c.setPath("/");
        c.setMaxAge(expiry);
        response.addCookie(c);
    }

    /**
     * 删除Cookie
     * 
     * @param request
     * @param name
     */
    public static void removeCookieByName(HttpServletRequest request, HttpServletResponse response, String domain,
            String name) {
        Cookie[] c = request.getCookies();
        if (ArrayUtils.isEmpty(c)) {
            return;
        }
        String realName = SecurityUtils.encodeByMD5(name);
        for (int i = 0; i < c.length; i++) {
            String cookname = c[i].getName();
            if (cookname.compareToIgnoreCase(realName) == 0) {
                addCookieByName(request, response, domain, name, "", 0);
                break;
            }
        }
    }
}
