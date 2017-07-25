package net.zdsoft.common.upload;

import net.zdsoft.common.exception.NetstudyException;

/**
 * 文件上传失败的异常
 * 
 * @author fangb
 * 
 */
@SuppressWarnings("serial")
public class FileUploadFailException extends NetstudyException {

    /**
     * 上传失败的文件名称
     */
    private String fileName;

    public FileUploadFailException() {
        super();
    }

    /**
     * @param fileName
     *            上传失败的文件名
     */
    public FileUploadFailException(String fileName) {
        this.fileName = fileName;
    }

    public FileUploadFailException(String fileName, Throwable cause) {
        super(cause);
        this.fileName = fileName;
    }

    /**
     * 得到上传失败的文件名
     * 
     * @return
     */
    public String getFailFileName() {
        return fileName;
    }

}
