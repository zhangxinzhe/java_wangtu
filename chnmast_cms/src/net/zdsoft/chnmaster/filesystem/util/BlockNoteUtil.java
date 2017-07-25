/*
 * @(#)FileUtil.java    Created on 2015-8-5
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.filesystem.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.zdsoft.common.utils.LocalFileUtil;
import net.zdsoft.keel.util.SecurityUtils;

/**
 * 文件上传日志工具类
 *
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015-8-31 下午5:11:41 $
 */
public class BlockNoteUtil {
    private final static Logger log = LoggerFactory.getLogger(BlockNoteUtil.class);
    // private final static String UPLOAD_KEY = "upload_key";
    private final static String ROOT = "/note/";

    /**
     * 通过文件地址和token和唯一性控制，产生noteKey
     *
     * @param filePath
     * @param saveToken
     * @param uniqControl
     * @return
     */
    public static String createNoteKey(String filePath, String saveToken, String uniqControl) {
        filePath = new File(filePath).getParentFile().getPath();
        return SecurityUtils.encodeByMD5(filePath + saveToken + (uniqControl == null ? "" : uniqControl));
    }

    /**
     * 获取记录
     *
     * @param saveToken
     * @return
     * @throws Exception
     */
    public static JSONObject getNote(String saveToken) {
        try {
            String t = ROOT + saveToken + ".note";
            t = UploadFileUtil.getFileAsString(t);
            return (JSONObject) JSON.parse(t);
        }
        catch (Exception e) {
            log.debug("获取记录失败！", e);
            return null;
        }
    }

    /**
     * 保存记录
     *
     * @param saveToken
     * @param json
     * @throws Exception
     */
    public static boolean putNote(String saveToken, JSONObject json) {
        String path = ROOT + saveToken + ".note";
        try {
            UploadFileUtil.saveFile(json.toJSONString(), path);
            return true;
        }
        catch (Exception e) {
            log.error("保存记录失败", e);
            return false;
        }
    }

    /**
     * 移除记录
     *
     * @param saveToken
     * @throws Exception
     */
    public static boolean removeNote(String saveToken) {
        String path = ROOT + saveToken + ".note";
        try {
            UploadFileUtil.deleteFile(path);
            return true;
        }
        catch (Exception e) {
            log.error("移出记录失败！", e);
            return false;
        }
    }

    /**
     * 获取所有token
     *
     * @return
     */
    public static String[] getAllToken() {
        String[] paths = LocalFileUtil.getFiles(ROOT);
        if (paths == null || paths.length <= 0) {
            return null;
        }
        List<String> tokens = new ArrayList<String>();
        for (String path : paths) {
            tokens.add(new File(path).getName().replace(".note", ""));
        }

        return tokens.toArray(new String[] {});
    }

    public static void main(String[] args) {
        System.out.println(BlockNoteUtil.getAllToken());
    }
}
