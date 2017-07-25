package net.zdsoft.common.upload;

import java.util.Date;

import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.enums.FileType;
import net.zdsoft.common.enums.PathType;
import net.zdsoft.keel.util.DateUtils;
import net.zdsoft.keel.util.Validators;

/**
 * 用户上传文件路径构造类
 */
public class FilePathBuilder {
    public static final String SEPERATOR = "/";
    private long userId; // 用户ID
    protected PathType pathType;// 对象类型
    protected long id; // 文件对应记录的id
    protected String suffix; // 文件名后辍
    protected String extName; // 文件扩展名
    protected String nowDate; // 当前时间
    protected String fileName; // 文件名称（包括扩展名）

    public FilePathBuilder(long userId, PathType pathType, long id, String suffix, String extName) {
        this.userId = userId;
        this.pathType = pathType;
        this.id = id;
        this.suffix = suffix;
        this.extName = extName;
        this.nowDate = DateUtils.currentDate2String();
    }

    public FilePathBuilder(PathType pathType, long id, String suffix, String extName) {
        this.pathType = pathType;
        this.id = id;
        this.suffix = suffix;
        this.extName = extName;
        this.nowDate = DateUtils.currentDate2String();
    }

    public FilePathBuilder(PathType pathType, long id) {
        this.pathType = pathType;
        this.id = id;
    }

    public FilePathBuilder(PathType pathType, long id, String fileName) {
        this.pathType = pathType;
        this.id = id;
        this.fileName = fileName;
        this.nowDate = DateUtils.currentDate2String();
    }

    /**
     * 得到各种xls文件导入，执行结果存放文件的路径，例如：/upload/PathType.ACCOUNT_BATCH_FILE/id
     *
     * @return
     */
    public String buildImportResultFilePath() {
        StringBuffer subPath = new StringBuffer();
        subPath.append(FileType.USER_FILE.getWebContext());
        subPath.append(pathType.getValue());
        subPath.append(SEPERATOR);
        subPath.append(id);

        return subPath.toString();
    }

    /**
     * 得到用户上传文件的存放路径，如公告新闻文件的路径为：upload/bulletin/{年}/{月}/{日}/{id}.txt<br>
     * 如用户上传课程图片路径：upload/coursePic/{年}/{月}/{日}/{id}_{suffix}.{extName}<br>
     *
     * @return
     */
    public String buildUploadFilePath() {
        StringBuffer subPath = new StringBuffer();
        subPath.append(FileType.USER_FILE.getWebContext());
        subPath.append(pathType.getValue());
        subPath.append(SEPERATOR);
        subPath.append(nowDate.substring(0, 4));
        subPath.append(SEPERATOR);
        subPath.append(nowDate.substring(5, 7));
        subPath.append(SEPERATOR);
        subPath.append(nowDate.substring(8, 10));
        subPath.append(SEPERATOR);
        if (Validators.isEmpty(suffix)) {// 考虑没有后缀的情况
            subPath.append(id + "." + extName);
        }
        else {
            subPath.append(id + "_" + suffix + "." + extName);
        }

        return subPath.toString();
    }

    /**
     * 路径：upload/${pathType}/{年}/{月}/{日}/{id}/${fileName}
     */
    public String buildUploadFilePath2() {
        StringBuffer subPath = new StringBuffer();
        subPath.append(FileType.USER_FILE.getWebContext());
        subPath.append(pathType.getValue());
        subPath.append(SEPERATOR);
        subPath.append(nowDate.substring(0, 4));
        subPath.append(SEPERATOR);
        subPath.append(nowDate.substring(5, 7));
        subPath.append(SEPERATOR);
        subPath.append(nowDate.substring(8, 10));
        subPath.append(SEPERATOR);
        subPath.append(id);
        subPath.append(SEPERATOR);
        subPath.append(fileName);

        return subPath.toString();
    }

    /**
     * 得到附件上传文件的路径：upload/{agencyId}/exer/用户ID/年/月/日/{id}.pdf
     *
     * @return
     */
    public String buildUploadExerFilePath() {
        StringBuffer subPath = new StringBuffer();
        subPath.append(FileType.USER_FILE.getWebContext());
        subPath.append(pathType.getValue());
        subPath.append(SEPERATOR);
        subPath.append(userId);
        subPath.append(SEPERATOR);
        subPath.append(nowDate.substring(0, 4));
        subPath.append(SEPERATOR);
        subPath.append(nowDate.substring(5, 7));
        subPath.append(SEPERATOR);
        subPath.append(nowDate.substring(8, 10));
        subPath.append(SEPERATOR);
        if (Validators.isEmpty(suffix)) {// 考虑没有后缀的情况
            subPath.append(id + "." + extName);
        }
        else {
            subPath.append(id + "_" + suffix + "." + extName);
        }
        return subPath.toString();
    }

    /**
     * 获取store目录下的临时目录:temp/{年}/{月}/{timeStamp}/原文件名
     *
     * @return
     */
    public static String getStoreTempPath() {
        StringBuffer buffer = new StringBuffer();
        String nowDate = DateUtils.currentDate2String();
        buffer.append(FileType.USER_FILE.getWebContext());
        buffer.append(BaseConstants.TEMP);
        buffer.append(SEPERATOR);
        buffer.append(nowDate.substring(0, 4));
        buffer.append(SEPERATOR);
        buffer.append(nowDate.substring(5, 7));
        buffer.append(SEPERATOR);
        buffer.append(new Date().getTime());
        return buffer.toString();
    }

    /**
     * 获取lucene索引存储位置,如常见问题：luceneIndex/help
     *
     * @param flag
     * @return
     */
    public static String getStoreLucenePath(String flag) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("luceneIndex");
        buffer.append(SEPERATOR);
        buffer.append(flag);
        return buffer.toString();

    }

    /**
     * 得到系统文件的主路目录，如：sysfile/
     *
     * @return
     */
    public String buildSysFilePath() {

        StringBuffer subPath = new StringBuffer();
        subPath.append(FileType.SYS_FILE.getWebContext());

        return subPath.toString();
    }

    public PathType getPathType() {
        return pathType;
    }

    public void setPathType(PathType pathType) {
        this.pathType = pathType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public String getNowDate() {
        return nowDate;
    }

    public void setNowDate(String nowDate) {
        this.nowDate = nowDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
