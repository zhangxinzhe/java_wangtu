package net.zdsoft.common.upload;

import net.zdsoft.common.exception.NetstudyException;

@SuppressWarnings("serial")
public class FileValidationException extends NetstudyException {

    /**
     * 验证失败的文件名
     */
    private String fileRealName;

    public FileValidationException(String fileRealName, String message) {
        super(message);
        this.fileRealName = fileRealName;
    }

    public String getFileRealName() {
        return this.fileRealName;
    }

}
