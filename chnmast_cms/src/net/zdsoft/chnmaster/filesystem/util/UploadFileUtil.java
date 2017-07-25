/*
 * @(#)upload.java    Created on 2015-3-21
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.filesystem.util;

import java.io.IOException;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.filesystem.FileSystem;
import net.zdsoft.common.filesystem.impl.LocalFileSystem;

/**
 * 文件上传服务的文件处理工具类
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2015年11月17日 下午6:10:34 $
 */
public class UploadFileUtil {
    private final static FileSystem FILE_SYSTEM;
    private static final String fileType = NetstudyConfig.getParam(BaseConstants.CHNMASTER_FILE_TYPE);

    static {
        LocalFileSystem.init(NetstudyConfig.getParam(BaseConstants.CHNMASTER_FILE_PATH));
        FILE_SYSTEM = new LocalFileSystem();
    }

    /**
     * 保存文件
     *
     * @param src
     * @param filePath
     * @return
     * @throws IOException
     */
    public static Boolean saveFile(Object src, String filePath) throws Exception {
        return FILE_SYSTEM.saveFile(src, filePath);
    }

    /**
     * 获取文件长度
     *
     * @param filePath
     * @return
     */
    public static Long getFileLength(String filePath) {
        return FILE_SYSTEM.getFileLength(filePath);
    }

    /**
     * 检查文件是否存在
     *
     * @param filePath
     * @return
     */
    public static Boolean fileExists(String filePath) {
        return FILE_SYSTEM.fileExists(filePath);
    }

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static Boolean deleteFile(String filePath) throws Exception {
        return FILE_SYSTEM.deleteFile(filePath);
    }

    /**
     * 获取文件字符串
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String getFileAsString(String filePath) throws Exception {
        return FILE_SYSTEM.getFileAsString(filePath);
    }

    /**
     * 获取文件列表
     *
     * @param dirPath
     * @return
     * @throws Exception
     */
    public static String[] getFiles(String dirPath) throws Exception {
        return FILE_SYSTEM.getFiles(dirPath);
    }

    /**
     * 写文件
     *
     * @param data
     * @param begin
     * @param path
     * @return
     * @throws Exception
     */
    public static Boolean randomWrite(Object data, long begin, String path) throws Exception {
        return FILE_SYSTEM.randomWrite(data, begin, path);
    }
}
