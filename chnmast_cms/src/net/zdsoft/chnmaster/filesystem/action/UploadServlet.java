/*
 * @(#)upload.java    Created on 2015-3-21
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.filesystem.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.zdsoft.chnmaster.filesystem.upload.BlockUpload;
import net.zdsoft.chnmaster.filesystem.upload.TotalUpload;

import sun.misc.BASE64Decoder;

/**
 * 文件上传
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2015年11月17日 下午4:59:12 $
 */
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 132642615715031957L;
    Logger log = LoggerFactory.getLogger(UploadServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("get方式请求，属于无效访问");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /** ===================获取基本信息_S====================== **/
        InputStream is = null;// 上传文件的流
        JSONObject policy = null;// 上传文件的参数信息
        try {
            // 公司自己的文件上传
            String contentType = request.getContentType();
            String type = null;
            if (contentType.indexOf("application/x-www-form-urlencoded") > -1) {
                type = "block";
                policy = getPolicy(request, request.getInputStream());
                is = request.getInputStream();
            }

            // 获取表单信息
            if (type == null) {
                Map<String, Object> map = getFormData(request, response);
                if (map == null) {
                    return;
                }
                if (map.get("type") != null) {
                    type = (String) map.get("type");
                }
                policy = (JSONObject) map.get("policy");
                if (map.get("file") != null) {
                    is = (InputStream) map.get("file");
                }
            }
            /** ===================获取基本信息_E====================== **/
            log.debug(policy.toString());
            // System.out.println(policy.toString());

            // 分块上传
            if ("block".equals(type)) {
                print(new BlockUpload().upload(policy, is), response);
            }
            // 整块上传
            else {
                print(new TotalUpload().upload(policy, is), response);
            }
        }
        catch (Exception e) {
            log.error("文件上传失败：", e);
            e.printStackTrace();

            JSONObject result = new JSONObject();
            result.put("code", 400);
            result.put("message", "文件上传失败");
            print(result, response);
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                }
                catch (IOException e) {
                }
            }
        }
    }

    /**
     * 获取表单信息
     *
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    public Map<String, Object> getFormData(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        JSONObject result = new JSONObject();
        try {
            Map<String, List<FileItem>> params = upload.parseParameterMap(request);
            String policy = params.get("policy").get(0).getString("UTF-8");
            policy = new String(new BASE64Decoder().decodeBuffer(policy), "utf-8");
            log.debug(policy);
            Map<String, Object> map = new HashMap<String, Object>();
            JSONObject json = (JSONObject) JSON.parse(policy);
            map.put("type", json.get("type"));
            map.put("policy", json);
            if (params.get("file") != null) {
                map.put("file", params.get("file").get(0).getInputStream());
            }
            return map;
        }
        catch (Exception e) {
            log.error("文件上传失败", e);
            e.printStackTrace();

            response.setStatus(400);
            result.put("code", 400);
            result.put("message", "文件上传失败");
            print(result, response);
            return null;
        }
    }

    /**
     * 获取policy
     *
     * @param request
     * @param is
     * @return
     * @throws IOException
     */
    private JSONObject getPolicy(HttpServletRequest request, InputStream is) throws IOException {
        // policy 在url中
        String policy = request.getParameter("policy");
        if (StringUtils.isNotBlank(policy)) {
            // System.out.println("1_" + policy);
            // policy = URLDecoder.decode(policy, "utf-8");
            // System.out.println("2_" + policy);
        }
        // policy 在正文中
        if (StringUtils.isBlank(policy)) {
            char[] chars = new char[1024];
            StringBuffer temp = new StringBuffer();
            InputStreamReader ir = null;
            BufferedReader br = null;
            try {
                ir = new InputStreamReader(is, "utf-8");
                br = new BufferedReader(ir);
                int length = 0;
                while ((length = br.read(chars)) > 0) {
                    temp.append(chars, 0, length);
                }
                policy = URLDecoder.decode(temp.toString(), "utf-8");

                // 第一个参数是policy
                if (policy.indexOf("policy=") == 0) {
                    policy = policy.substring("policy=".length(), policy.lastIndexOf("&signature=") + 1);
                }
                // 第二个参数是policy
                else {
                    policy = policy.split("policy=")[1];
                }
            }
            catch (Exception e) {
                throw e;
            }
            finally {
                if (ir != null) {
                    try {
                        ir.close();
                        ir = null;// 加速回收
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (br != null) {
                    try {
                        br.close();
                        br = null;// 加速回收
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }

        }
        policy = new String(new BASE64Decoder().decodeBuffer(policy), "utf-8");
        // System.out.println("3_" + policy);
        log.debug(policy);
        return (JSONObject) JSON.parse(policy);
    }

    /**
     * 打印信息
     *
     * @param message
     * @return
     */
    protected String print(JSONObject message, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            if (message.getInteger("code") == 400) {
                response.setStatus(400);
            }
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            out = response.getWriter();
            out.print(message);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        finally {
            if (out != null) {
                out.close();
            }
        }
        return null;
    }
}
