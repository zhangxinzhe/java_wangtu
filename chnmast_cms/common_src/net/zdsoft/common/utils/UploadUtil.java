package net.zdsoft.common.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.enums.FileType;

public class UploadUtil {

    private static String SEPERATOR = BaseConstants.SEPERATOR;
    private static String USERAVATAR = "userAvatar";
    private static String EDITOR = "editor";
    private static String TEACHERUPLOAD = "teacherupload";// 教务老师
    private static String GALLERY = "gallery";// 课程图库
    private static String TEMPLATE = "template"; // 模板
    private static String COURSE = "course";

    private static List<String> downloadFileExts;

    static {
        downloadFileExts = new ArrayList<String>();
        downloadFileExts.add("pdf");
    }

    /**
     * 通过tomcat下载的格式 目前包含 pdf
     *
     * @return
     */
    public static List<String> getDownloadFileExts() {
        return downloadFileExts;
    }

    /**
     * 学生教师的头像
     */
    public static String getUserFolder() {
        StringBuffer subPath = new StringBuffer();
        subPath.append(USERAVATAR);
        subPath.append(SEPERATOR);
        return subPath.toString();
    }

    /**
     * 文本编辑器上传的图片
     */
    public static String getEditorFolder() {
        StringBuffer subPath = new StringBuffer();
        subPath.append(FileType.USER_FILE.getWebContext());
        subPath.append(EDITOR);
        subPath.append(SEPERATOR);
        return subPath.toString();
    }

    /**
     * 系统课程相关文件目录
     *
     * @return
     */
    public static String getSystemCourseFolder() {
        StringBuffer subPath = new StringBuffer();
        subPath.append(FileType.SYS_FILE.getWebContext());
        subPath.append(COURSE);
        subPath.append(SEPERATOR);
        return subPath.toString();
    }

    /**
     * 课程图库的图片目录
     */
    public static String getCourseGalleryFolder() {
        StringBuffer subPath = new StringBuffer();
        subPath.append(getSystemCourseFolder());
        subPath.append(GALLERY);
        subPath.append(SEPERATOR);
        return subPath.toString();
    }

    public static String getTemplateFolder() {
        StringBuffer subPath = new StringBuffer();
        subPath.append(FileType.SYS_FILE.getWebContext());
        subPath.append(TEMPLATE);
        subPath.append(SEPERATOR);
        return subPath.toString();
    }

    /**
     * 得到某本书的存储目录结构
     *
     * @param bookId
     * @return 返回目录结构的字符串 形如 12/34/45/
     */
    public static String getBookFolder(long bookId) {
        String bookIdStr = String.valueOf(bookId);

        StringBuffer subPath = new StringBuffer();
        subPath.append(bookIdStr.substring(8, 10));
        subPath.append(SEPERATOR);
        subPath.append(bookIdStr.substring(6, 8));
        subPath.append(SEPERATOR);
        subPath.append(bookIdStr.substring(4, 6));
        subPath.append(SEPERATOR);

        return subPath.toString();
    }

    /**
     * 得到音乐的存储目录结构
     *
     * @param musicId
     * @return 返回目录结构的字符串 形如 12/34/45/
     */
    public static String getMusicFolder(long musicId) {
        String musicIdStr = String.valueOf(musicId);

        StringBuffer subPath = new StringBuffer();
        subPath.append(musicIdStr.substring(8, 10));
        subPath.append(SEPERATOR);
        subPath.append(musicIdStr.substring(6, 8));
        subPath.append(SEPERATOR);
        subPath.append(musicIdStr.substring(4, 6));
        subPath.append(SEPERATOR);

        return subPath.toString();
    }

    @SuppressWarnings("unused")
    private static String getDateString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DATE));
        return year + SEPERATOR + month + SEPERATOR + day + SEPERATOR;
    }

    public static String getTeacherUploadFolder() {
        StringBuffer subPath = new StringBuffer();
        subPath.append(TEACHERUPLOAD);
        subPath.append(SEPERATOR);
        return subPath.toString();
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
        if (StringUtils.isBlank(filePath)) {
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
        int underlinePos = fileName.lastIndexOf(BaseConstants.UNDERLINE);
        int pointPos = fileName.lastIndexOf(BaseConstants.POINT);
        if (underlinePos >= 0) {
            fileNameFix = fileName.substring(0, underlinePos + 1);
            fileSuffix = fileName.substring(underlinePos + 1, pointPos);
        }
        else {
            // 没有下划线时就取.之前的
            fileNameFix = fileName.substring(0, pointPos);
        }

        fileExtname = fileName.substring(pointPos + 1);
        // 检查文件是否带有版本后缀
        int versionIndex = fileExtname.indexOf(BaseConstants.QUESTION_MARK);
        String version = "";
        if (versionIndex > -1) {
            version = fileExtname.substring(versionIndex);
            fileExtname = fileExtname.substring(0, versionIndex);
        }

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
        newFileName.append(BaseConstants.POINT);
        if (StringUtils.isNotEmpty(toExtName)) {
            newFileName.append(toExtName);
        }
        else {
            newFileName.append(fileExtname);
        }
        // 添加版本号
        newFileName.append(version);
        return newFileName.toString();
    }
}
