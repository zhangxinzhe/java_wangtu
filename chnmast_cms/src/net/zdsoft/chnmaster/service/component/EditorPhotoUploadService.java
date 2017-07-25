package net.zdsoft.chnmaster.service.component;

import net.zdsoft.chnmaster.exception.ImageCompressException;
import net.zdsoft.common.exception.FileUploadFailException;
import net.zdsoft.common.upload.UploadFile;

/**
 * 用于编辑器上传照片的服务接口
 * 
 * @author fangb
 * 
 */
public interface EditorPhotoUploadService {
    /**
     * 上传一张用于编辑器的照片
     * <p>
     * 该照片存放在userId存储目录下的editor的子目录下。
     * 
     * @param userId
     *            当前的上传用户id
     * @param file
     *            图片文件
     * @return 返回上传成功后的图片的绝对链接路径
     */
    public String uploadPhoto(long userId, UploadFile file) throws FileUploadFailException, ImageCompressException;
}
