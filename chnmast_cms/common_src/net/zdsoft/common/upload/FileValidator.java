package net.zdsoft.common.upload;

/**
 * 文件的验证接口
 * 
 * @author fangb
 * 
 */
public interface FileValidator {

	void validate(UploadFile file) throws FileValidationException;
}
