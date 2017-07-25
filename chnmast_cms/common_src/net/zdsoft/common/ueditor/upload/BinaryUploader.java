package net.zdsoft.common.ueditor.upload;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.ueditor.PathFormat;
import net.zdsoft.common.ueditor.define.AppInfo;
import net.zdsoft.common.ueditor.define.BaseState;
import net.zdsoft.common.ueditor.define.FileType;
import net.zdsoft.common.ueditor.define.State;
import net.zdsoft.common.upload.FileValidationException;
import net.zdsoft.common.upload.FileValidator;
import net.zdsoft.common.upload.RequestFileHandler;
import net.zdsoft.common.upload.UploadFile;

public class BinaryUploader {

    public static final State save(HttpServletRequest request, Map<String, Object> conf) {

        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
        }

        UploadFile file = null;
        try {
            file = RequestFileHandler.handleFile(request, new FileValidator() {
                @Override
                public void validate(UploadFile file) throws FileValidationException {

                }
            });

            if (file == null) {
                return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
            }

            String savePath = (String) conf.get("savePath");
            String originFileName = file.getFileRealName();
            String suffix = FileType.getSuffixByFilename(originFileName);

            originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
            savePath = savePath + suffix;

            long maxSize = ((Long) conf.get("maxSize")).longValue();

            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }

            savePath = PathFormat.parse(savePath, originFileName);

            InputStream is = new FileInputStream(file.getFile());
            State storageState = StorageManager.saveFileByInputStream(is, savePath, maxSize);
            is.close();

            if (storageState.isSuccess()) {
                storageState.putInfo("url",
                        NetstudyConfig.getParam(BaseConstants.DOMAIN_UPLOAD_FILE) + PathFormat.format(savePath));
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", originFileName + suffix);
            }

            return storageState;
        }
        catch (Exception e) {
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);

        return list.contains(type);
    }
}
