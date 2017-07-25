/*
 * @(#)AddressUtilsServiceImpl.java    Created on 2015年6月10日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.zdsoft.common.service.AddressUtilsService;
import net.zdsoft.common.service.HttpClientService;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015年6月10日 下午6:24:00 $
 */
@Service("addressUtilsService")
public class AddressUtilsServiceImpl implements AddressUtilsService {
    @Resource
    private HttpClientService httpClientService;

    private static final String SERVICE_URL = "http://ip.taobao.com/service/getIpInfo.php?ip=";

    @Override
    public Map<String, String> getAddress(String ip) throws Exception {
        DefaultHttpClient client = httpClientService.getLongConnClient();
        HttpGet get = new HttpGet(SERVICE_URL + ip);
        ResponseHandler<String> response = new BasicResponseHandler();
        String returnStr = client.execute(get, response);
        Map<String, String> addressMap = new HashMap<String, String>();
        if (StringUtils.isBlank(returnStr)) {
            addressMap.put("error", "获取失败！");
            return addressMap;
        }
        JSONObject json = JSON.parseObject(returnStr);
        if (!"0".equals(json.get("code").toString())) {
            addressMap.put("error", "获取失败！");
            return addressMap;
        }
        String data = json.getString("data");
        if (StringUtils.isBlank(data)) {
            addressMap.put("error", "获取失败！");
            return addressMap;
        }
        StringBuffer buffer = new StringBuffer();
        json = JSON.parseObject(data);
        buffer.append(json.getString("region"));// 省份
        buffer.append(json.getString("city"));// 市区
        buffer.append(json.getString("county"));// 地区
        if (StringUtils.isBlank(buffer.toString())) {
            buffer.append(json.getString("country"));
        }
        else {
            addressMap.put("isp", decodeUnicode(json.getString("isp")));
            String regionCode = json.getString("county_id");
            if (StringUtils.isBlank(regionCode) || regionCode.equals("-1")) {
                regionCode = json.getString("city_id");
                if (StringUtils.isBlank(regionCode) || regionCode.equals("-1")) {
                    regionCode = json.getString("region_id");
                    if (StringUtils.isBlank(regionCode) || regionCode.equals("-1")) {
                        regionCode = json.getString("area_id");
                        if (StringUtils.isBlank(regionCode) || regionCode.equals("-1")) {
                            regionCode = json.getString("country_id");
                        }
                    }
                }
            }
            addressMap.put("regionCode", regionCode);
        }
        addressMap.put("address", buffer.toString());
        return addressMap;
    }

    public String getRs(String path, String params, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫�?
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫�?
            connection.setDoInput(true);// 是否打开输出�? true|false
            connection.setDoOutput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(params);
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect();// 关闭连接
        }
        return null;
    }

    /**
     * 字符转码
     *
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer buffer = new StringBuffer(len);
        for (int i = 0; i < len;) {
            aChar = theString.charAt(i++);
            if (aChar == '\\') {
                aChar = theString.charAt(i++);
                if (aChar == 'u') {
                    int val = 0;
                    for (int j = 0; j < 4; j++) {
                        aChar = theString.charAt(i++);
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            val = (val << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            val = (val << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            val = (val << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException("Malformed      encoding.");
                        }
                    }
                    buffer.append((char) val);
                }
                else {
                    if (aChar == 't') {
                        aChar = '\t';
                    }
                    if (aChar == 'r') {
                        aChar = '\r';
                    }
                    if (aChar == 'n') {
                        aChar = '\n';
                    }
                    if (aChar == 'f') {
                        aChar = '\f';
                    }
                    buffer.append(aChar);
                }
            }
            else {
                buffer.append(aChar);
            }
        }
        return buffer.toString();
    }
}
