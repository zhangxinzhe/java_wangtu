package net.zdsoft.common.filesystem.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import net.zdsoft.common.filesystem.FileSystem;
import net.zdsoft.common.utils.LocalFileUtil;

/**
 * 本地文件操作api
 *
 * @author shenl
 * @version $Revision: 1.0 $, $Date: 2013-8-19 下午2:37:25 $
 */
public class LocalFileSystem implements FileSystem {
    private static String STORE_PATH = "/opt/data/";

    /**
     * 初始化
     *
     * @param storePath
     */
    public static final void init(String storePath) {
        if (storePath.endsWith(File.separator)) {
            STORE_PATH = storePath;
        }
        else if (storePath.endsWith("/")) {
            STORE_PATH = storePath.substring(0, storePath.length() - 1) + File.separator;
        }
        else {
            STORE_PATH = storePath + File.separator;
        }
    }

    @Override
    public Boolean deleteFile(String filePath) {
        File file = new File(STORE_PATH + filePath);
        return file.delete();
    }

    @Override
    public Boolean deleteDir(String filePath) throws Exception {
        File file = new File(STORE_PATH + filePath);
        return file.delete();
    }

    @Override
    public InputStream getFileAsStream(String filePath) throws IOException {
        FileInputStream is = new FileInputStream(STORE_PATH + filePath);
        return is;
    }

    @Override
    public String getFileAsString(String filePath) throws Exception {
        if (StringUtils.isEmpty(filePath)) {
            return "";
        }
        File file = new File(STORE_PATH + filePath);
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

    @Override
    public Boolean saveFile(Object src, String filePath) throws IOException {
        File toFile = new File(STORE_PATH + filePath);
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
                outputStream = new BufferedOutputStream(new FileOutputStream(STORE_PATH + filePath));
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

    @Override
    public Boolean copyFile(String srcPath, String destinationPath) throws IOException {
        File srcFile = new File(STORE_PATH + srcPath);
        File destFile = new File(STORE_PATH + destinationPath);
        FileUtils.copyFile(srcFile, destFile);
        return true;
    }

    @Override
    public Boolean copyFileToLocal(String filePath, String localFilePath) throws Exception {
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

    @Override
    public String[] getFiles(String dirPath) {
        File file = new File(STORE_PATH + dirPath);
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return new String[0];
        }
        String[] files = new String[listFiles.length];
        for (int i = 0; i < files.length; i++) {
            String filePath = listFiles[i].getAbsolutePath();
            files[i] = filePath.substring(STORE_PATH.length());
            if (!STORE_PATH.endsWith("/")) {
                files[i] = StringUtils.replace(files[i], File.separator, "/");
            }
        }

        return files;
    }

    @Override
    public Boolean fileExists(String filePath) {
        File file = new File(STORE_PATH + filePath);
        return file.exists();
    }

    @Override
    public Boolean dirExists(String dirPath) {
        File file = new File(STORE_PATH + dirPath);
        return file.exists() && file.isDirectory();
    }

    @Override
    public Long getFileLength(String filePath) {
        File file = new File(STORE_PATH + filePath);
        if (file.isFile()) {
            return file.length();
        }
        else {
            return 0l;
        }
    }

    @Override
    public Date getLastModified(String filePath) {
        File file = new File(STORE_PATH + filePath);
        if (file.isFile()) {
            return new Date(file.lastModified());
        }
        else {
            return null;
        }
    }

    @Override
    public Boolean mkDir(String filePath) {
        File file = new File(STORE_PATH + filePath);
        return file.mkdirs();
    }

    @Override
    public Boolean cutFile(String srcPath, String destinationPath) {
        File srcFile = new File(STORE_PATH + srcPath);
        return srcFile.renameTo(new File(STORE_PATH + destinationPath));
    }

    @Override
    public String[] getFilesName(String dirPath) throws Exception {
        File file = new File(STORE_PATH + dirPath);
        File[] files = file.listFiles();
        String[] names = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            File temp = files[i];
            names[i] = temp.getName();
        }
        return names;
    }

    @Override
    public Boolean randomWrite(Object data, long begin, String path) throws Exception {
        return LocalFileUtil.randomWrite(data, begin, STORE_PATH + path);
    }
}
