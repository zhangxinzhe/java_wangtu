package net.zdsoft.common.upload;

import java.io.File;
import java.io.Serializable;

/**
 * 上传的文件封装类
 * 
 * @author fangb
 * 
 */
public class UploadFile implements Serializable {
    private static final long serialVersionUID = 9047661325507171778L;

    /**
     * 文件输入框名字
     */
    private String fileName;
    /**
     * 上传的文件
     */
    private File file;

    /**
     * 文件的真实文件名
     */
    private String fileRealName;

    public UploadFile(File file, String fileRealName) {
        this.fileName = "";
        this.file = file;
        this.fileRealName = fileRealName;
    }

    public UploadFile(String fileName, File file, String fileRealName) {
        this.fileName = fileName;
        this.file = file;
        this.fileRealName = fileRealName;
    }

    public String getFileName() {
        return fileName;
    }

    public File getFile() {
        return file;
    }

    public String getFileRealName() {
        return fileRealName;
    }
}
