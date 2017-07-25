package net.zdsoft.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class URLUtil {
    private static Logger log = LoggerFactory.getLogger(URLUtil.class);

    private static String charSet = "UTF-8";
    private static String PATH_SPERATOR = "/";
    private static String HTTP = "http://";
    private static String RNMSG = "rnMsg"; // 消息字段，分页时过滤掉该字段
    private static final String parameterSplit = ",";

    public static void setCharSet(String charSet) {
        URLUtil.charSet = charSet;
    }

    /**
     * 获取地址中的参数信息
     *
     * @param request
     * @return
     */
    public static Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        // 获得POST 过来参数设置到新的PARAMS中
        Map<String, ?> requestParams = request.getParameterMap();
        for (String _key : requestParams.keySet()) {
            String[] values = (String[]) requestParams.get(_key);
            String valueStr = "";
            for (String value : values) {
                valueStr += "," + value;
            }
            params.put(_key, valueStr.substring(1));
        }
        return params;
    }

    /**
     * 获取请求信息中的参数信息
     *
     * @param request
     * @param split
     *            多个参数之前的分隔符;默认分隔符：,
     * @return
     */
    public static String getRequestParameters(HttpServletRequest request, String split) {
        if (request == null) {
            return null;
        }
        // 获取请求中的参数信息
        Map<?, ?> params = request.getParameterMap();
        if (MapUtils.isEmpty(params)) {
            return null;
        }
        // 设置分隔符
        if (StringUtils.isBlank(split)) {
            split = parameterSplit;
        }

        // 拼接参数信息
        StringBuffer context = new StringBuffer();
        for (Object _key : params.keySet()) {
            String key = String.valueOf(_key);
            context.append(split + key + "=");
            Object[] vals = (Object[]) params.get(key);
            if (ArrayUtils.isNotEmpty(vals)) {
                String strVal = Arrays.toString(vals);
                context.append(strVal.substring(1, strVal.length() - 1));
            }
        }
        return context.substring(split.length());
    }

    public static String addQueryString(String url, String queryString) {
        if (StringUtils.isBlank(queryString)) {
            return url;
        }

        if (url.indexOf(63) == -1) {
            url = url + '?' + queryString;
        }
        else {
            url = url + '&' + queryString;
        }

        return url;
    }

    public static String addQueryString(String url, String name, Object value) {
        return addQueryString(url, new String[] { name }, new Object[] { value });
    }

    public static String addQueryString(String url, Map parameters) {
        return addQueryString(url, (String[]) parameters.keySet().toArray(new String[0]),
                parameters.values().toArray(new Object[0]));
    }

    public static String addQueryString(String url, String[] names, Object[] values) {
        if (names.length != values.length) {
            throw new IllegalArgumentException("Length of array must be equal");
        }

        StringBuffer queryString = new StringBuffer();
        boolean isFirst = true;
        for (int i = 0; i < names.length; ++i) {
            if (RNMSG.equals(names[i])) {
                continue;
            }
            Object value = values[i];
            if (value != null) {
                int j;
                if (!(isFirst)) {
                    queryString.append('&');
                }
                else {
                    isFirst = false;
                }

                if (value instanceof Object[]) {
                    Object[] array = (Object[]) value;
                    for (j = 0; j < array.length; ++j) {
                        if (j > 0) {
                            queryString.append('&');
                        }

                        appendParameter(queryString, names[i], array[j]);
                    }
                }
                else if (value instanceof Collection) {
                    Iterator iterator = ((Collection) value).iterator();
                    j = 0;
                    while (iterator.hasNext()) {
                        if (j++ > 0) {
                            queryString.append('&');
                        }

                        appendParameter(queryString, names[i], iterator.next());
                    }
                }
                else {
                    appendParameter(queryString, names[i], value);
                }
            }
        }

        return addQueryString(url, queryString.toString());
    }

    public static String decode(String url, String encoding) {
        try {
            return URLDecoder.decode(url, encoding);
        }
        catch (UnsupportedEncodingException e) {
            log.error("解码失败！", e);
        }
        return null;
    }

    public static String dynamicURL(String url) {
        if (url.indexOf(63) == -1) {
            url = url + '?';
        }

        return url;
    }

    public static String encode(String url, String encoding) {
        try {
            return URLEncoder.encode(url, encoding);
        }
        catch (UnsupportedEncodingException e) {
            log.error("编码失败！", e);
        }
        return null;
    }

    /**
     * 得到请求的action名称
     *
     * @param request
     * @return 请求的action名称，例如login.action
     */
    public static String getAction(HttpServletRequest request) {
        String requestUri;

        requestUri = request.getRequestURI();

        int startIndex = requestUri.lastIndexOf(PATH_SPERATOR);

        if (startIndex < 0) {
            return requestUri;
        }
        else {
            return requestUri.substring(startIndex + 1);
        }
    }

    /**
     * 获取host
     *
     * @param request
     * @return
     */
    public static String getServerName(HttpServletRequest request) {
        return addHttp(request.getServerName());
    }

    public static String getExtension(String url) {
        int pointIndex = url.indexOf(46);

        if (pointIndex == -1) {
            return null;
        }

        int interrogationIndex = url.indexOf(63);

        return ((interrogationIndex == -1) ? url.substring(pointIndex + 1)
                : url.substring(pointIndex + 1, interrogationIndex));
    }

    public static String getNamespace(String servletPath) {
        return servletPath.substring(0, servletPath.lastIndexOf(47));
    }

    public static String ignoreLastRightSlash(String url) {
        if (StringUtils.isBlank(url)) {
            return url;
        }

        if (url.charAt(url.length() - 1) == '/') {
            return url.substring(0, url.length() - 1);
        }

        return url;
    }

    private static StringBuffer appendParameter(StringBuffer queryString, String name, Object value) {
        queryString.append(name);
        queryString.append('=');

        if (value instanceof Boolean) {
            value = (((Boolean) value).booleanValue()) ? "true" : "false";
        }

        try {
            queryString.append(URLEncoder.encode(String.valueOf(value), charSet));
        }
        catch (UnsupportedEncodingException e) {
        }

        return queryString;
    }

    /**
     * 去掉URL地址中前端的http://字符串
     *
     * @param url
     * @return
     */
    public static String cutHttp(String url) {
        if (StringUtils.isBlank(url)) {
            return url;
        }

        if (url.startsWith(HTTP)) {
            return url.substring(url.indexOf(HTTP) + 7);
        }
        else {
            return url;
        }
    }

    /**
     * 添加URL地址中前端的http://字符串
     *
     * @param url
     * @return
     */
    public static String addHttp(String url) {
        if (StringUtils.isBlank(url)) {
            return url;
        }

        if (!url.trim().startsWith(HTTP)) {
            return HTTP + url.trim();
        }
        else {
            return url;
        }
    }

    /**
     * 判断请求的URL是不是需要过滤，可带路径过滤
     *
     * @param servletPath
     *            请求地址
     * @param ignorePaths
     *            忽略的请求地址
     * @return
     */
    public static boolean isIgnorePath(String servletPath, Set<String> ignorePaths) {
        for (String ignore : ignorePaths) {
            if (ignore.endsWith(".htm")) {
                if (servletPath.endsWith(ignore)) {
                    return true;
                }
            }
            else {
                if (servletPath.contains(ignore)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 获得IP地址
     *
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;

        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                }
                catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 判断是否已http 开头
     *
     * @param url
     * @return
     */
    public static boolean isStartHttp(String url) {
        if (StringUtils.isNotBlank(url) && url.toLowerCase().startsWith(HTTP)) {
            return true;
        }
        return false;
    }
}
