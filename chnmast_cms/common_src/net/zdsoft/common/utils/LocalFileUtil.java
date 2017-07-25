/*
 * @(#)FileUtil.java    Created on 2015-8-5
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2015-8-5 下午4:17:33 $
 */
public class LocalFileUtil {
    public static Boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }

    public static Boolean deleteDir(String filePath) throws Exception {
        File file = new File(filePath);
        FileUtils.deleteDirectory(file);
        return true;
    }

    public static InputStream getFileAsStream(String filePath) throws IOException {
        FileInputStream is = new FileInputStream(filePath);
        return is;
    }

    public static String getFileAsString(String filePath) throws Exception {
        if (StringUtils.isEmpty(filePath)) {
            return "";
        }
        File file = new File(filePath);
        if (!file.isFile()) {
            return "";
        }
        StringBuffer context = new StringBuffer();
        BufferedReader input = null;
        try {
            // 指定UTF-8编码读文件
            input = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file.getAbsoluteFile()), Charset.forName("UTF-8")));
            String line = null;
            while ((line = input.readLine()) != null) {
                context.append(line);
            }
        }
        finally {
            if (input != null) {
                IOUtils.closeQuietly(input);
            }
        }
        return context.toString();
    }

    public static Boolean saveFile(Object src, String filePath) throws IOException {
        File toFile = new File(filePath);
        toFile.getParentFile().mkdirs();
        if (src instanceof File) {
            // 文件
            FileUtils.copyFile((File) src, toFile);
            return true;
        }
        else if (src instanceof String) {
            // 保存文本
            // 指定UTF-8编码写文件
            FileOutputStream fos = new FileOutputStream(toFile);
            try {
                fos.write(((String) src).getBytes("UTF-8"));
            }
            finally {
                IOUtils.closeQuietly(fos);
            }
            return true;
        }
        else if (src instanceof InputStream) {
            // 保存流
            BufferedOutputStream outputStream = null;
            try {
                outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
                IOUtils.copy((InputStream) src, outputStream);
            }
            finally {
                IOUtils.closeQuietly(outputStream);
            }
            return true;
        }
        else {
            return false;
        }
    }

    public static Boolean copyFile(String srcPath, String destinationPath) throws IOException {
        File srcFile = new File(srcPath);
        File destFile = new File(destinationPath);
        FileUtils.copyFile(srcFile, destFile);
        return true;
    }

    public static Boolean copyFileToLocal(String filePath, String localFilePath) throws Exception {
        InputStream is = getFileAsStream(filePath);
        OutputStream os = new FileOutputStream(localFilePath);
        try {
            IOUtils.copy(is, os);
        }
        finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(is);
        }
        return true;
    }

    public static String[] getFiles(String dirPath) {
        File file = new File(dirPath);
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return new String[0];
        }
        String[] files = new String[listFiles.length];
        for (int i = 0; i < files.length; i++) {
            files[i] = listFiles[i].getAbsolutePath();
        }
        return files;
    }

    public static Boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists() && !file.isDirectory();
    }

    public static Boolean dirExists(String dirPath) {
        File file = new File(dirPath);
        return file.exists() && file.isDirectory();
    }

    public static Long getFileLength(String filePath) {
        File file = new File(filePath);
        if (file.isFile()) {
            return file.length();
        }
        else {
            return 0l;
        }
    }

    public static Date getLastModified(String filePath) {
        File file = new File(filePath);
        if (file.isFile()) {
            return new Date(file.lastModified());
        }
        else {
            return null;
        }
    }

    public static Boolean mkDir(String filePath) {
        File file = new File(filePath);
        return file.mkdirs();
    }

    public static Boolean cutFile(String srcPath, String destinationPath) {
        File srcFile = new File(srcPath);
        return srcFile.renameTo(new File(destinationPath));
    }

    public static String[] getFilesName(String dirPath) throws Exception {
        File file = new File(dirPath);
        File[] files = file.listFiles();
        String[] names = null;
        if (files != null) {
            names = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                File temp = files[i];
                names[i] = temp.getName();
            }
        }
        return names;
    }

    /**
     * 改文件地址(如果新地址已有文件，则删除该文件)
     *
     * @param oldPath
     * @param newPath
     * @return
     */
    public static boolean changeFilePath(String oldPath, String newPath) {
        File oldFile = new File(oldPath);
        File newFile = new File(newPath);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        if (newFile.exists()) {
            newFile.delete();
        }
        oldFile.renameTo(newFile);
        return true;
    }

    public static Boolean randomWrite(Object data, long begin, String path) throws Exception {
        RandomAccessFile fs = null;
        try {
            File file = new File(path).getParentFile();
            if (!file.exists()) {
                file.mkdirs();
            }

            fs = new RandomAccessFile(path, "rw");
            fs.seek(begin);// 指定位置开始写入
            if (begin > fs.length()) {
                throw new Exception("插入地址超过文件的长度[文件长度:" + fs.length() + ",插入地址:" + begin + "]");
            }

            // 写入
            if (data instanceof InputStream) {
                InputStream is = (InputStream) data;
                byte[] bytes = new byte[1024];
                int c = 0;
                while ((c = is.read(bytes, 0, bytes.length)) > 0) {
                    fs.write(bytes, 0, c);
                }
            }
            else {
                fs.write((byte[]) data);
            }

            return true;
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            if (fs != null) {
                try {
                    fs.close();
                }
                catch (Exception e) {

                }
            }
        }
    }
}
