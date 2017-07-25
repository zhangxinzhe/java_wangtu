/*
 * @(#)LoginUtils.java    Created on 2013-11-7
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.util.GUID;

import net.zdsoft.common.constant.BaseConstants;

/**
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2015年10月30日 下午1:31:45 $
 */
public class HttpUtil {
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
     * 获取内容
     *
     * @param url
     * @param headers
     * @return
     * @throws Exception
     */
    public static String getString(String url, Map<String, String> headers) throws Exception {
        GetMethod getMethod = null;
        try {
            HttpClient client = new HttpClient();
            getMethod = new GetMethod(url);
            if (headers != null && headers.size() > 0) {
                Set<String> keys = headers.keySet();
                for (String key : keys) {
                    getMethod.addRequestHeader(key, headers.get(key));
                }
            }
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
     * 提交表单
     *
     * @param url
     * @param params
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static String postForm(String url, Map<String, String> params) throws Exception {
        PostMethod method = null;
        try {
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(30000);// 连接超时30秒
            client.getHttpConnectionManager().getParams().setSoTimeout(30000);// 响应超时
            client.getParams().setConnectionManagerTimeout(30000);// 设置等待ConnectionManager释放connection的时间

            method = new PostMethod(url);
            method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=utf-8");

            // 提交的参数
            if (params != null) {
                Set<String> keys = params.keySet();
                NameValuePair nameValuePair = null;
                for (String key : keys) {
                    nameValuePair = new NameValuePair();
                    nameValuePair.setName(key);
                    nameValuePair.setValue(params.get(key));
                    method.addParameter(nameValuePair);
                }
            }
            method.addRequestHeader("User-Agent", "Mozilla/4.0");
            client.executeMethod(method);
            return new String(method.getResponseBody(), "utf-8");
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            if (method != null) {
                try {
                    method.releaseConnection();
                }
                catch (Exception e2) {
                }
            }
        }
    }

    /**
     * 提交表单
     *
     * @param url
     * @param params
     * @param headers
     *            头属性验证
     * @return
     * @throws Exception
     */
    public static String postForm(String url, Map<String, String> params, Map<String, String> headers)
            throws Exception {
        PostMethod method = null;
        try {
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(30000);// 连接超时30秒
            client.getHttpConnectionManager().getParams().setSoTimeout(30000);// 响应超时
            client.getParams().setConnectionManagerTimeout(30000);// 设置等待ConnectionManager释放connection的时间

            method = new PostMethod(url);
            method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=utf-8");

            // 提交的参数
            if (params != null) {
                Set<String> keys = params.keySet();
                NameValuePair nameValuePair = null;
                for (String key : keys) {
                    nameValuePair = new NameValuePair();
                    nameValuePair.setName(key);
                    nameValuePair.setValue(params.get(key));
                    method.addParameter(nameValuePair);
                }
            }
            method.addRequestHeader("User-Agent", "Mozilla/4.0");

            if (headers != null && headers.size() > 0) {
                Set<String> keys = headers.keySet();
                for (String key : keys) {
                    method.addRequestHeader(key, headers.get(key));
                }
            }

            client.executeMethod(method);
            return new String(method.getResponseBody(), "utf-8");
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            if (method != null) {
                try {
                    method.releaseConnection();
                }
                catch (Exception e2) {
                }
            }
        }
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

    /**
     * 通过form传字符串
     */
    public static String postForm(String uri, String value) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        HttpURLConnection conn = null;

        try {
            // 获取链接
            URL url = new URL(uri);
            conn = (HttpURLConnection) url.openConnection();

            // 设置必要参数
            conn.setConnectTimeout(30000);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; text/html; charset=utf-8");
            conn.setRequestProperty("Accept", "*/*");
            conn.connect();
            os = conn.getOutputStream();
            byte[] data = null;

            // 传输数据
            is = new ByteArrayInputStream(value.getBytes());
            data = new byte[4096];
            int temp = 0;
            while ((temp = is.read(data)) != -1) {
                os.write(data, 0, temp);
            }
            os.flush();

            // 返回信息的处理
            StringBuilder text = new StringBuilder();
            InputStream is1 = null;
            InputStreamReader sr = null;
            BufferedReader br = null;
            int code = conn.getResponseCode();
            try {
                is1 = code >= 400 ? conn.getErrorStream() : conn.getInputStream();

                sr = new InputStreamReader(is1);
                br = new BufferedReader(sr);

                char[] chars = new char[4096];
                int length = 0;

                while ((length = br.read(chars)) != -1) {
                    text.append(chars, 0, length);
                }
            }
            finally {
                if (br != null) {
                    br.close();
                    br = null;
                }
                if (sr != null) {
                    sr.close();
                    sr = null;
                }
                if (is1 != null) {
                    is1.close();
                    is1 = null;
                }
            }
            return text.toString();

        }
        catch (IOException e) {
            throw e;
        }
        finally {

            if (os != null) {
                os.close();
                os = null;
            }
            if (is != null) {
                is.close();
            }
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
    }
}
