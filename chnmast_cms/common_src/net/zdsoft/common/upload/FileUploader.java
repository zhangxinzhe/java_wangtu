package net.zdsoft.common.upload;

import java.io.File;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.exception.FileUploadFailException;
import net.zdsoft.common.filesystem.util.FileSystemUtil;
import net.zdsoft.keel.util.DateUtils;
import net.zdsoft.keel.util.Validators;

/**
 * 将文件保存在服务器的类
 *
 * @author fangb
 *
 */
public class FileUploader {

    private static String SEPERATOR = "/";
    private static String DOUBLE_SEPERATOR = "//";
    private static String UNDERLINE = "_";
    private static String POINT = ".";

    /**
     * 将用户上传文件按照给定的存储路径保存在服务器上
     *
     * @param file
     * @param pathBuilder
     */
    public static void saveFile(UploadFile file, FilePathBuilder pathBuilder) throws FileUploadFailException {
        // 保存文件
        try {
            // FileUtils.copyFile(file.getFile(), new File(
            // getUserFilePath(pathBuilder)));
            FileSystemUtil.saveFile(file.getFile(), pathBuilder.buildUploadFilePath());
        }
        catch (Exception e) {
            // 如果发生IO异常，将IO异常转换为业务的FileUploadFailException，供客户端做处理
            throw new FileUploadFailException(file.getFileRealName());
        }
    }

    /**
     * 根据原始文件更改文件后辍和扩展名，得到新的文件名， 如： <br>
     * upload/{agencyid}/userAvatar/{年}/{月}/{日}/{id}_avatar.{扩展名} <br>
     * 更改为<br>
     * upload/{agencyid}/userAvatar/{年}/{月}/{日}/{id}_original.{扩展名}<br>
     * 如果toSuffix或toSuffix为空，表示不更改
     *
     * @param filePath
     * @param toSuffix
     * @param toExtName
     * @return
     */
    public static String changeFileName(String filePath, String toSuffix, String toExtName) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        // 得到文件名和文件路径，如：1234_avatar.jpg
        String fileName = null;
        String fileFolder = null;
        int startPos = filePath.lastIndexOf(SEPERATOR);
        if (startPos >= 0) {
            fileName = filePath.substring(startPos + 1);
            fileFolder = filePath.substring(0, startPos + 1);
        }
        else {
            fileName = filePath;
            fileFolder = "";
        }

        // 得到文件名字中去掉后辍和扩展名部分，即不变的部分
        String fileNameFix = null;
        String fileSuffix = "";
        String fileExtname = null;
        int underlinePos = fileName.lastIndexOf(UNDERLINE);
        int pointPos = fileName.lastIndexOf(POINT);
        if (underlinePos >= 0) {
            fileNameFix = fileName.substring(0, underlinePos + 1);
            fileSuffix = fileName.substring(underlinePos + 1, pointPos);
        }
        else {
            // 没有下划线时就取.之前的
            fileNameFix = fileName.substring(0, pointPos);
        }

        fileExtname = fileName.substring(pointPos + 1);

        // 组装新的文件名
        StringBuffer newFileName = new StringBuffer();
        newFileName.append(fileFolder);
        newFileName.append(fileNameFix);
        if (StringUtils.isNotEmpty(toSuffix)) {
            newFileName.append(toSuffix);
        }
        else {
            newFileName.append(fileSuffix);
        }
        newFileName.append(POINT);
        if (StringUtils.isNotEmpty(toExtName)) {
            newFileName.append(toExtName);
        }
        else {
            newFileName.append(fileExtname);
        }

        return newFileName.toString();
    }

    /**
     * 根据子路径的构造器得到用户上传文件的绝对url链接地址<br>
     * 如：http://file.netstudy.dev/upload/100012/video/2013/08/10/45642.wmv
     *
     * @param pathBuilder
     *            文件子路径的构造器
     * @return
     */
    public static String getUserFileURL(FilePathBuilder pathBuilder) {
        StringBuffer fileUrl = new StringBuffer();

        fileUrl.append(NetstudyConfig.getParam(BaseConstants.DOMAIN_UPLOAD_FILE));
        fileUrl.append(SEPERATOR);
        fileUrl.append(pathBuilder.buildUploadFilePath());

        return fileUrl.toString();
    }

    /**
     * 根据子路径的构造器得到系统文件的绝对url链接地址
     *
     * @param pathBuilder
     *            文件子路径的构造器
     * @return
     */
    public static String getSysFileURL(FilePathBuilder pathBuilder) {
        StringBuffer fileUrl = new StringBuffer();

        fileUrl.append(NetstudyConfig.getParam(BaseConstants.DOMAIN_FILE));
        fileUrl.append(SEPERATOR);
        fileUrl.append(pathBuilder.buildSysFilePath());

        return fileUrl.toString();
    }

    /**
     * 得到用户上传文件的完成路径，考虑到了主目录结尾和子路径开头是否含有分隔符的情况<br>
     * 如：/opt/data/netstudy/upload/100012/video/2013/08/10/45642.wmv
     *
     * @return
     */
    public static String getUserFilePath(FilePathBuilder pathBuilder) {
        String storeRoot = NetstudyConfig.getParam(BaseConstants.CHNMASTER_FILE_PATH);
        String subPath = pathBuilder.buildUploadFilePath();

        String fullPath = "";

        // 如果根路径以分隔符结束
        if (storeRoot.endsWith(SEPERATOR) || storeRoot.endsWith(DOUBLE_SEPERATOR)) {
            fullPath = storeRoot + subPath;
        }
        else {// 如果根路径未以分隔符结束
            fullPath = storeRoot + SEPERATOR + subPath;
        }

        return fullPath;
    }

    /**
     * 根据子路径的构造器得到服务器上的文件实例
     *
     * @param pathBuilder
     * @return
     */
    public static File getFileOnServer(FilePathBuilder pathBuilder) {
        return new File(getUserFilePath(pathBuilder));
    }

    public static void main(String[] args) {

    }

    public String getLatestFile(String filePath) {
        String result = null;
        String[] files = null;
        try {
            files = FileSystemUtil.getFiles(filePath);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (Validators.isEmpty(files)) {
            return null;
        }
        Date latest = DateUtils.string2Date("1900-01-01 00:00:00");
        for (String file : files) {
            int idx = file.lastIndexOf("\\");
            if (idx == -1) {
                idx = file.lastIndexOf("/");
            }
            if (idx == -1) {
                continue;
            }
            String name = file.substring(idx + 1, file.length());
            if (Validators.isEmpty(name)) {
                continue;
            }
            String dateStr = name.replace(".xls", "");
            Date olDate = DateUtils.string2Date(dateStr, "yyyy-MM-dd hh-mm-ss");
            if (null == olDate) {
                continue;
            }
            if (olDate.after(latest)) {
                latest = olDate;
                result = name;
            }
        }
        return result;
    }
}
