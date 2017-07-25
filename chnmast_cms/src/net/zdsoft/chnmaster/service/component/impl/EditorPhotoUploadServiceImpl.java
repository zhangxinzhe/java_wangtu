package net.zdsoft.chnmaster.service.component.impl;

import org.springframework.stereotype.Service;

import net.zdsoft.chnmaster.exception.ImageCompressException;
import net.zdsoft.chnmaster.service.common.BaseServiceImpl;
import net.zdsoft.chnmaster.service.component.EditorPhotoUploadService;
import net.zdsoft.common.enums.PathType;
import net.zdsoft.common.exception.FileUploadFailException;
import net.zdsoft.common.upload.FilePathBuilder;
import net.zdsoft.common.upload.FileUploader;
import net.zdsoft.common.upload.UploadFile;
import net.zdsoft.common.utils.URLUtil;

@Service("editorPhotoUploadService")
public class EditorPhotoUploadServiceImpl extends BaseServiceImpl implements EditorPhotoUploadService {

    @Override
    public String uploadPhoto(long agencyId, UploadFile file) throws FileUploadFailException, ImageCompressException {
        String extName = URLUtil.getExtension(file.getFileRealName());
        final FilePathBuilder pathBuilder = new FilePathBuilder(PathType.EDITOR, System.currentTimeMillis(), "",
                extName);
        // 保存图片到文件
        FileUploader.saveFile(file, pathBuilder);
        return FileUploader.getUserFileURL(pathBuilder);
    }
}
