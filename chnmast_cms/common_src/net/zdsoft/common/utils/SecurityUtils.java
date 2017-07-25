/* 
 * @(#)XssUtils.java    Created on 2014-1-14
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * 平台安全性工具类
 * 
 * @author liyixi
 * 
 */
public class SecurityUtils {

    private static final String SCRIPT_REGEX = "[\\s\\S]*javascript\\:[\\s\\S]*|[\\s\\S]*(window\\.)?document\\.[\\s\\S]*|[\\s\\S]*eval\\([\\s\\S]*\\)[\\s\\S]*";
    
    private static final String VAL_PATH = "[\\w/]+";
    
    private static Map<Character, String> symbols = new HashMap<Character, String>();
    
    private static Map<String, String> scriptTag = new HashMap<String, String>();
    
    static {
        //此处请与后台base.js中ValidatorUtil.isSecurity方法和前台G.isSecurity方法同步
        // symbols.put('|', "");
        symbols.put('&', "");
        symbols.put(';', "");
        // symbols.put('$', "");
        symbols.put('%', "");
        // symbols.put('?', "");
        // symbols.put('*', "");

        // symbols.put('@', "");
        symbols.put('\'', "");
        symbols.put('\"', "");
        symbols.put('<', "");
        symbols.put('>', "");

        // symbols.put('(', "");
        // symbols.put(')', "");
        // symbols.put('+', "");
        // symbols.put('\\', "");
        
        //替换几个高危的Javascript脚本关键字
        scriptTag.put("javascript", "javasc#ipt");
        scriptTag.put("document", "docum#nt");
        scriptTag.put("window", "win#ow");
        scriptTag.put("eval", "ev#l");
    }

    /**
     * 是否存在特殊符号，true 存在， false 不存在
     * 
     * @param val
     * @return
     */
    public static boolean isExistsSpecialSymbols(final String val) {
        if (StringUtils.isBlank(val)) {
            return false;
        }
        for (int i = 0; i < val.length(); ++i) {
            char c = val.charAt(i);
            String ver = symbols.get(c);
            if (ver != null) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 是否存在高危JAVASCRIPT脚本
     * @param val
     * @return
     */
    public static boolean isExistScriptTag(final String val){
        if(StringUtils.isBlank(val)){
            return false;
        }        
        return val.matches(SCRIPT_REGEX);
    }
    
    /**
     * 替换指定的字符集内容
     * 
     * @param val
     * @param symbols
     * @return
     */
    public static String filterSpecialSymbols(final String val) {
        if (StringUtils.isBlank(val)) {
            return val;
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < val.length(); ++i) {
            char c = val.charAt(i);
            String ver = symbols.get(c);
            if (ver != null) {
                buf.append(ver);
            }
            else {
                buf.append(c);
            }
        }
        return buf.toString();
    }
    
    
    /**
     * 替换高危的javascript关键字
     * @param val
     * @return
     */
    public static String filterSepecialScriptTag(final String val){
        if(StringUtils.isBlank(val)){
            return val;
        }
        StringBuffer buf = new StringBuffer(val);
        for(String k : scriptTag.keySet()){
            int index = buf.indexOf(k);
            if(index != -1){
                String rp = scriptTag.get(k);
                buf.replace(index, index + k.length(), rp);
            }
        }
        return buf.toString();
    }
    
    /**
     * 验证请求路径：参数格式/path1/path2/...
     * 此方式是防止通过path注入脚本，避免产生跨站脚本编制问题
     * @param path
     * @return
     */
    public static boolean ValidatorPath(final HttpServletRequest request){
        if(request == null){
            return false;
        }
        String servletPath = request.getServletPath();
        String path = servletPath.substring(0, servletPath.lastIndexOf("/") + 1);
        return path.matches(VAL_PATH);
    }
    
//    public static void main(String[] args) {
//        System.err.println(SecurityUtils.filterSepecialScriptTag("123javascript:alert(123);alert"));
//        System.err.println(SecurityUtils.filterSepecialScriptTag("document.cookies.index;"));
//        System.err.println(SecurityUtils.filterSepecialScriptTag("javascript:eval(123);"));
//        System.err.println(SecurityUtils.filterSepecialScriptTag("window.document.cookies.index;"));
//        System.out.println(SecurityUtils.filterSepecialScriptTag(">\"'><img src=\"javascript:alert(921375)\">"));
//        System.out.println(SecurityUtils.ValidatorPath("/123/alert%22123%22"));
//    }
}
