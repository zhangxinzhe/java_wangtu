package net.zdsoft.common.upload;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.util.CollectionUtils;

/**
 * 从request中获取文件
 *
 * @author fangb
 *
 */
@SuppressWarnings("rawtypes")
public class RequestFileHandler {

    /**
     * 从HttpServletRequest中获取上传的文件
     * <p>
     * 该方法使用validator对上传的文件进行验证，一旦出现验证失败的文件，该方法立即抛出FileValidationException并退出
     * 
     * @param request
     * @param validator
     * @return 返回获取到的合法文件列表，如果request不是MultiPartRequestWrapper类型或者request中没有包含file的提交参数 ，则返回空的列表
     * @throws FileValidationException
     *             调用validator进行上传文件的验证，验证失败会抛出该异常
     */
    public static List<UploadFile> handleFiles(HttpServletRequest request, FileValidator validator)
            throws FileValidationException {
        List<UploadFile> uploadFiles = new ArrayList<UploadFile>();

        if (request instanceof MultiPartRequestWrapper) {
            MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;

            // 得到文件输入域的参数名称
            Enumeration fileFields = wrapper.getFileParameterNames();
            while (fileFields.hasMoreElements()) {
                String fieldName = (String) fileFields.nextElement();

                // 获取输入域的文件
                File[] files = wrapper.getFiles(fieldName);
                String[] fileRealNames = wrapper.getFileNames(fieldName);

                if (files == null || files.length == 0) {
                    continue;
                }

                int length = files.length;
                for (int i = 0; i < length; ++i) {
                    File file = files[i];
                    String fileRealName = fileRealNames[i];

                    UploadFile uploadFile = new UploadFile(fieldName, file, fileRealName);

                    // 对文件进行验证
                    if (null != validator) {
                        validator.validate(uploadFile);
                    }

                    // 将验证通过的文件放到文件列表中
                    uploadFiles.add(uploadFile);
                }
            }
        }

        return uploadFiles;
    }

    /**
     * 从HttpServletRequest中获取单个的参数文件实例
     * <p>
     * 如果你确定请求参数中只有一个文件参数，可以调用该方法。
     * 
     * @param request
     * @param validator
     * @return 返回UploadFile实例，如果没有文件参数，返回null
     * @throws FileValidationException
     *             FileValidationException 调用validator进行上传文件的验证，验证失败会抛出该异常
     */
    public static UploadFile handleFile(HttpServletRequest request, FileValidator validator)
            throws FileValidationException {
        List<UploadFile> files = handleFiles(request, validator);

        if (CollectionUtils.isEmpty(files)) {
            return null;
        }

        return files.get(0);
    }
}
