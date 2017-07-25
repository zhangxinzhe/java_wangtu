package net.zdsoft.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.util.GUID;

import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.keel.page.TextImage;
import net.zdsoft.keel.util.RandomUtils;
import net.zdsoft.keel.util.SecurityUtils;
import net.zdsoft.keel.util.ServletUtils;

/**
 * 处理html的工具类
 *
 * @author fangb
 * @date 2010-1-25 上午02:47:56
 */
public class HtmlUtil {

    private static final String HTML_SCRIPT = "\\s*<[script|SCRIPT][^>]+\\s+\\s*>\\s+[^<]+\\s*</[script|SCRIPT]>";
    private static final String HTML_REGEX_1 = "\\&[a-zA-Z]{1,10};";
    private static final String HTML_REGEX_2 = "<[^>]*>";
    private static final String HTML_REGEX_3 = "[(/>)<]";
    private final static String HTML_REGEX_4 = "<([^>]*)>"; // 所有以<开头以>结尾的标签

    private static final String REGEX_TITLE = "\\s*<title>\\s*.*?\\s*</title>";
    private static final String REGEX_DESCRIPTION = "<\\s*meta name=\"description\"\\s+([^>]+)\\s*>";
    private static final String REGEX_DESC_CONTENT = "\\s*content\\s*=\\s*\"([^\"]+)\\s*\"";

    private static final String REGEX_IMG = "<\\s*img\\s+([^>]+)\\s*>";
    private static final String REGEX_IMG_SRC = "\\s*src\\s*=\\s*\"([^\"]+)\\s*\"";

    private static final String HTTP = "http";

    public static String removeTag(String html) {
        if (StringUtils.isBlank(html)) {
            return null;
        }

        return html.replaceAll(HTML_SCRIPT, StringUtils.EMPTY).replaceAll(HTML_REGEX_1, StringUtils.EMPTY)
                .replaceAll(HTML_REGEX_2, StringUtils.EMPTY).replaceAll(HTML_REGEX_3, StringUtils.EMPTY);
    }

    public static String removeTag(String html, String tag) {
        if (StringUtils.isBlank(html)) {
            return null;
        }
        String reg = "\\s*<[" + tag.toLowerCase() + "|" + tag.toUpperCase() + "][^>]+\\s+\\s*>\\s+[^<]+\\s*</["
                + tag.toLowerCase() + "|" + tag.toUpperCase() + "]>";
        return html.replaceAll(reg, StringUtils.EMPTY).replaceAll(HTML_REGEX_1, StringUtils.EMPTY)
                .replaceAll(HTML_REGEX_2, StringUtils.EMPTY).replaceAll(HTML_REGEX_3, StringUtils.EMPTY);
    }

    /**
     *
     * 基本功能：过滤所有以"<"开头以">"结尾的标签
     *
     * @param str
     * @return String
     */
    public static String filterHtml(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }

        Pattern pattern = Pattern.compile(HTML_REGEX_4);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 获取网页的标题，从&lt;title&gt;&lt;/title&gt;标签中获取 如果没有该标签，或是解析该标签时发生运行时异常，则返回null
     *
     * @param html
     * @return
     */
    public static String getTitle(String html) {

        Pattern pattern = Pattern.compile(REGEX_TITLE);
        Matcher matcher = pattern.matcher(html);

        String title = null;
        if (matcher.find()) {
            String titleHtml = matcher.group();
            try {
                titleHtml = titleHtml.trim();
                title = titleHtml.substring(7, titleHtml.length() - 8);
            }
            catch (Throwable e) {
                return null;
            }
        }

        return title;
    }

    /**
     * 获取网页中的描述信息，从&lt;meta name="description"/&gt;标签中得到该信息 如果没有该标签，或者在解析该标签时出现运行时异常，则返回null
     *
     * @param html
     * @return
     */
    public static String getDescription(String html) {
        Pattern pattern = Pattern.compile(REGEX_DESCRIPTION);
        Matcher matcher = pattern.matcher(html);

        String desc = null;

        Pattern descContentPattern = Pattern.compile(REGEX_DESC_CONTENT);
        if (matcher.find()) {
            String descriptionHtml = matcher.group();

            Matcher contentMatcher = descContentPattern.matcher(descriptionHtml);
            if (contentMatcher.find()) {
                desc = contentMatcher.group();
                try {
                    desc = desc.trim();
                    desc = desc.substring(9, desc.length() - 1);
                }
                catch (Throwable e) {
                    return null;
                }
            }
        }

        return desc;
    }

    /**
     * 从网页代码中获取所有图片的链接地址，如果没有图片，返回空的列表
     *
     * @param html
     * @return
     */
    public static List<String> getImages(String html) {
        List<String> imgSrcs = new ArrayList<String>();

        Pattern pattern1 = Pattern.compile(REGEX_IMG, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern1.matcher(html);

        // img src元素的正则表达式：匹配img标签内的src属性
        Pattern pattern2 = Pattern.compile(REGEX_IMG_SRC, Pattern.CASE_INSENSITIVE);

        while (matcher.find()) {
            // group()返回符合表达式的内容
            Matcher matcher2 = pattern2.matcher(matcher.group());
            // 一定要find(),这是实际的匹配动作
            if (matcher2.find()) {
                String src = matcher2.group();
                int i1 = src.indexOf(HTTP);
                if (i1 != -1) {
                    imgSrcs.add(src.substring(i1, src.length() - 1));
                }
            }
        }

        return imgSrcs;
    }

    /**
     * 把字符串中的特殊字符转换为HTML字符
     *
     * @param str
     * @return
     */
    public static String toHtml(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }

        String html = str;
        html = html.replace("&", "&amp;");
        html = html.replace("<", "&lt;");
        html = html.replace(">", "&gt;");
        html = html.replace("\r\n", "\n");
        html = html.replace("\n", "<br>");
        html = html.replace("\t", "    ");
        html = html.replace(" ", "&nbsp;");
        html = html.replace("\"", "&quot;");

        return html;
    }

    /**
     * 把字符串中的HTML字符换为特殊字符转
     *
     * @param html
     * @return
     */
    public static String toStr(String html) {
        if (StringUtils.isBlank(html)) {
            return "";
        }

        String str = html;
        str = str.replace("&amp;", "&");
        str = str.replace("&lt;", "<");
        str = str.replace("&gt;", ">");
        str = str.replace("\n", "\r\n");
        str = str.replace("<br>", "\n");
        str = str.replace("    ", "\t");
        str = str.replace("&nbsp;", " ");
        str = str.replace("&quot;", "\"");

        return str;
    }

    /**
     * 忽略字符串中的宽度设置样式
     *
     * @param str
     * @return
     */
    public static String ignoreWidth(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }

        str = str.replaceAll("width=\"\\d*((px)|(PX))?\"", "");
        str = str.replaceAll("", "");
        return str;

    }

    /**
     * 读取一个网页全部内容
     *
     * @param linkUrl
     * @param charSet
     * @return
     * @throws IOException
     */
    public static String getAllHtml(String linkUrl, String charset) {
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(20 * 1000);
        client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
        GetMethod method = new GetMethod(linkUrl);
        try {
            client.executeMethod(method);
            return method.getResponseBodyAsString();
        }
        catch (HttpException e1) {
        }
        catch (IOException e1) {
        }
        finally {
            method.releaseConnection();
        }
        return null;
    }

    /**
     * 产生验证码图片
     *
     * @param request
     *
     * @param httpServletResponse
     *
     * @return
     * @throws Exception
     */
    public static String generateCode(HttpServletRequest request, HttpServletResponse response, String domain,
            String cookieName) throws Exception {
        ServletUtils.clearCache(response);
        String verifyCode = RandomUtils.getRandomNum(4);
        CookieUtil.addCookieByName(request, response, domain, URLUtil.cutHttp(domain) + "_" + cookieName,
                SecurityUtils.encodeByMD5(verifyCode), -1);
        TextImage textImage = new TextImage(60, 25);
        textImage.setBgColor(0xff, 0xff, 0xff);
        textImage.setFontColor(255, 0, 0);
        textImage.setFont(20);
        textImage.setRandomPointNum(10);
        textImage.export(verifyCode, 6, 17, response.getOutputStream());
        return null;
    }

    /**
     * 获取内容
     *
     * @param url
     * @return
     * @throws IOException
     * @throws HttpException
     */
    public static String getString(String url) throws Exception {
        GetMethod getMethod = null;
        try {
            HttpClient client = new HttpClient();
            getMethod = new GetMethod(url);
            client.executeMethod(getMethod);
            return new String(getMethod.getResponseBody(), "utf-8");
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            if (getMethod != null) {
                try {
                    getMethod.releaseConnection();
                }
                catch (Exception e2) {
                }
            }
        }
    }

    /**
     * 获取json对象
     *
     * @param url
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static JSONObject getJson(String url) throws Exception {
        String cotent = getString(url);
        if (StringUtils.isBlank(cotent)) {
            return null;
        }

        return JSONObject.parseObject(cotent);
    }

    /**
     * 获取文件
     *
     * @param url
     * @return
     */
    public static File getFile(String uri) {
        HttpURLConnection conn = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            // 创建连接
            URL url = new URL(uri);
            conn = (HttpURLConnection) url.openConnection();

            // 设置必要参数
            conn.setConnectTimeout(30000);
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            // 创建链接
            conn.connect();

            // 获取文件内容
            File file = new File(OSUtil.getTempDir() + BaseConstants.SEPERATOR + "temp" + BaseConstants.SEPERATOR
                    + GUID.generateGUID() + ".temp");
            file.getParentFile().mkdir();

            os = new FileOutputStream(file);
            byte[] data = new byte[4096];
            int temp = 0;

            is = conn.getInputStream();

            while ((temp = is.read(data)) != -1) {
                os.write(data, 0, temp);
            }

            return file;
        }
        catch (IOException e) {
            return null;
        }
        finally {
            try {
                if (is != null) {
                    is.close();
                    is = null;
                }
            }
            catch (IOException e) {

            }

            try {
                if (os != null) {
                    os.close();
                    os = null;
                }
            }
            catch (IOException e) {

            }

            try {
                // 关闭连接
                if (conn != null) {
                    conn.disconnect();
                    conn = null;
                }
            }
            catch (Exception e) {

            }
        }
    }

}
