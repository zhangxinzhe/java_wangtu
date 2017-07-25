/*
 * @(#)XmlUtils.java    Created on 2015-5-14
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.wechatpay.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSONObject;

/**
 * XML解析工具类
 *
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015-5-14 下午4:19:19 $
 */
public class XmlUtils {

    /**
     * 解析一个XML文件
     *
     * @param file
     * @return
     * @throws DocumentException
     */
    public static Document getDocument(File file) throws DocumentException {
        SAXReader reader = new SAXReader();
        reader.setEntityResolver(new EntityResolver() {
            @SuppressWarnings("deprecation")
            @Override
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                return new InputSource(new StringBufferInputStream(" "));
            }
        });
        return reader.read(file);
    }

    /**
     * 得到一个元素的某个标签的所有子元素
     *
     * @param element
     * @param tagName
     * @return
     */
    public static List<Element> getChildElementsByTagName(Element element, String tagName) {
        List<Element> elements = new ArrayList<Element>();

        for (Object child : element.elements()) {
            Element childElement = (Element) child;
            if (childElement.getName().equals(tagName)) {
                elements.add(childElement);
            }
            else {
                elements.addAll(getChildElementsByTagName(childElement, tagName));
            }
        }
        return elements;
    }

    /**
     * 将xml转换成json
     *
     * @param element
     * @param parent
     */
    public static void parseXml2Json(Element element, JSONObject parent) {
        List<?> elements = element.elements();
        String name = element.getName();
        // 没有子元素
        if (elements.isEmpty()) {
            String value = element.getTextTrim();
            parent.put(name, value);
        }
        else {
            // 子元素
            JSONObject child = new JSONObject();

            parent.put(name, child);

            // 有子元素
            Iterator<?> it = elements.iterator();
            while (it.hasNext()) {
                Element elem = (Element) it.next();
                // 递归遍历
                parseXml2Json(elem, child);
            }
        }
    }

    /**
     * 创建xml信息
     *
     * @param obj
     * @return
     */
    public static String createXmlStr(JSONObject obj) {
        StringBuffer xmlStr = new StringBuffer();
        xmlStr.append("<xml>");
        for (String key : obj.keySet()) {
            xmlStr.append("<" + key + ">").append(obj.getString(key)).append("</" + key + ">");
        }
        xmlStr.append("</xml>");
        return xmlStr.toString();
    }

    /**
     * 获取链接中的xml信息
     *
     * @return
     * @throws Exception
     */
    public static JSONObject getRequestXml() throws Exception {
        InputStream in = ServletActionContext.getRequest().getInputStream();
        if (in != null) {
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader buf = new BufferedReader(reader);
            String str;
            StringBuffer msgXml = new StringBuffer();
            while ((str = buf.readLine()) != null) {
                msgXml.append(str);
            }
            // System.out.println("========================= msgXml:S ===================");
            // System.out.println(msgXml.toString());
            // System.out.println("========================= msgXml:E ===================");
            buf.close();
            if (StringUtils.isBlank(msgXml)) {
                return null;
            }
            JSONObject object = new JSONObject();
            Document document = DocumentHelper.parseText(msgXml.toString());
            parseXml2Json(document.getRootElement(), object);
            JSONObject reObj = object.getJSONObject("xml") == null ? new JSONObject() : object.getJSONObject("xml");
            return reObj;
        }
        return null;
    }
}
