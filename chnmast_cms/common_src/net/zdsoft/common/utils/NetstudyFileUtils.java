/*
 * @(#)FileUtils.java    Created on 2015-1-19
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015-1-19 下午4:18:14 $
 */
public class NetstudyFileUtils {
    private static final String pos = ".";
    private static final String mark = "?";
    private static final String timeMark = "?t=";

    /**
     * 过滤 后缀后面的问号
     *
     * @param imgdest
     * @return
     * @throws Exception
     */
    public static String deleteMark(String imgdest) throws Exception {
        if (StringUtils.isBlank(imgdest)) {
            return "";
        }

        int posIndex = imgdest.lastIndexOf(pos);
        if (posIndex < 0) {
            throw new Exception("文件格式不正确");
        }

        String imgSuffix = imgdest.substring(posIndex);
        int suffixMark = imgSuffix.indexOf(mark);
        if (suffixMark > 0) {
            imgdest = imgdest.substring(0, posIndex + suffixMark);
        }
        return imgdest;
    }

    /**
     * 添加时间撮
     *
     * @param imgSrc
     * @return
     */
    public static String addCurrentTimeMark(String imgSrc) {
        try {
            imgSrc = deleteMark(imgSrc);
            imgSrc += timeMark + System.currentTimeMillis();
            return imgSrc;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取文件后缀
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String getExtension(String fileName) throws Exception {
        if (StringUtils.isBlank(fileName)) {
            return null;
        }
        int posIndex = fileName.lastIndexOf(pos) + 1;
        if (posIndex < 1) {
            throw new Exception("文件格式不正确");
        }

        int markIndex = fileName.lastIndexOf(mark);
        if (markIndex > 0) {
            return fileName.substring(posIndex, markIndex);
        }
        else {
            return fileName.substring(posIndex);
        }
    }

    /**
     * 根据源文件路径，获取压缩文件路径
     *
     * @param srcPath
     * @return
     */
    public static String builSmallFilePath(String srcPath, String tempName) {
        if (StringUtils.isEmpty(srcPath) || StringUtils.isEmpty(tempName)) {
            return null;
        }
        String[] splitStr = srcPath.split("\\.");
        String desPath = splitStr[0] + tempName + "." + splitStr[1];
        return desPath;
    }
}
