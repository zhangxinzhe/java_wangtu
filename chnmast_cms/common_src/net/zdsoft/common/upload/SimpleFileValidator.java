package net.zdsoft.common.upload;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 基本的文件验证器，该验证器验证文件的大小和文件的类型 子类必须实现<code>getMaxSize</code>和<code>getAvailableSuffixes</code> 接口，设置具体的最大大小和合法的文件类型
 * 
 * @author fangb
 * 
 */
public abstract class SimpleFileValidator implements FileValidator {

    /**
     * 图片的后缀集合
     */
    private static final Set<String> imageSuffixes = new HashSet<String>();

    static {
        imageSuffixes.add("bmp");
        imageSuffixes.add("gif");
        imageSuffixes.add("jpg");
        imageSuffixes.add("jpeg");
        imageSuffixes.add("png");
    }

    /**
     * 得到最大文件大小 该数值是文件的字节数
     * 
     * @return
     */
    public abstract long getMaxSize();

    /**
     * 得到合法的文件扩展名集合，例如：jpg,jpeg,gif
     * 
     * @return 返回以扩展名为元素的集合
     */
    public abstract Set<String> getAvailableSuffixes();

    @Override
    public void validate(UploadFile uploadFile) throws FileValidationException {
        new FileSizeValidator().validate(uploadFile);
        new FileNameValidator().validate(uploadFile);
    }

    /**
     * 文件大小的验证器
     * 
     * @author fangb
     * 
     */
    class FileSizeValidator implements FileValidator {

        @Override
        public void validate(UploadFile uploadFile) throws FileValidationException {
            File file = uploadFile.getFile();
            String fileRealName = uploadFile.getFileRealName();

            if (file == null) {
                return;
            }

            if (file.length() > getMaxSize()) {
                throw new FileValidationException(fileRealName, String.format("文件[%s]大小超过最大值%dM", fileRealName,
                        getMSize(getMaxSize())));
            }
        }

        /**
         * 得到兆大小
         * 
         * @param length
         *            字节数
         * @return
         */
        private long getMSize(long length) {
            return length / (1024 * 1024);
        }
    }

    class FileNameValidator implements FileValidator {

        private static final String BLANK_STRING = "";
        private static final String SUFFIX_SEPERATOR = ".";
        private static final String COMMA = ", ";

        @Override
        public void validate(UploadFile uploadFile) throws FileValidationException {
            String fileRealName = uploadFile.getFileRealName();

            if (getAvailableSuffixes() == null || getAvailableSuffixes().isEmpty()) {
                return;
            }

            // 如果该文件的扩展名不在合法文件的扩展名范围内，抛出验证失败的异常
            if (!getAvailableSuffixes().contains(getFileSuffix(fileRealName))) {
                throw new FileValidationException(fileRealName, String.format("文件[%s]的类型不正确，只能使用%s类型的文件", fileRealName,
                        StringUtils.join(getAvailableSuffixes(), COMMA)));
            }

            // 如果上传的是图片文件，则还需要根据图片文件的结构来对图片验证，而不是单纯地根据文件的扩展名
            if (isUploadImageFile(uploadFile)) {
                if (!validateImageFile(uploadFile)) {
                    throw new FileValidationException(fileRealName, "图片已损坏，无法上传！"

                    );
                }
            }
        }

        /**
         * 判断是否是要上传图片文件
         * 
         * @return
         */
        private boolean isUploadImageFile(UploadFile uploadFile) {
            return imageSuffixes.contains(getFileSuffix(uploadFile.getFileRealName()));
        }

        /**
         * 从文件的文件头信息来验证图片格式
         */
        private boolean validateImageFile(UploadFile uploadFile) {
            try {
                byte[] imgContent = FileUtils.readFileToByteArray(uploadFile.getFile());
                for (String suffix : getAvailableSuffixes()) {
                    suffix = suffix.toLowerCase();
                    // 得到该后缀对应的图片类型验证器
                    ImageTypeValidator validator = ImageTypeValidatorFactory.getValidator(suffix);
                    if (validator != null) {
                        if (ImageTypeValidatorFactory.getValidator(suffix).validate(imgContent)) {
                            return true;
                        }
                    }
                }
            }
            catch (IOException e) {
                return false;
            }

            return false;
        }

        /**
         * 得到文件的扩展名
         * 
         * @param fileRealName
         * @return
         */
        private String getFileSuffix(String fileRealName) {
            if (fileRealName == null) {
                return BLANK_STRING;
            }

            if (fileRealName.lastIndexOf(SUFFIX_SEPERATOR) < 0) {
                return BLANK_STRING;
            }

            return fileRealName.substring(fileRealName.lastIndexOf(SUFFIX_SEPERATOR) + 1).toLowerCase();
        }
    }

    static class ImageTypeValidatorFactory {

        public static Map<String, ImageTypeValidator> validators = new HashMap<String, ImageTypeValidator>();

        static {
            validators.put("gif", new AbstractTypeValidator() {

                @Override
                public boolean validate() {
                    // GIF(G I F 8 7 a)
                    if (b0 == (byte) 'G' && b1 == (byte) 'I' && b2 == (byte) 'F' && b3 == (byte) '8'
                            && b4 == (byte) '7' && b5 == (byte) 'a') {
                        return true;
                        // GIF(G I F 8 9 a)
                    }
                    else if (b0 == (byte) 'G' && b1 == (byte) 'I' && b2 == (byte) 'F' && b3 == (byte) '8'
                            && b4 == (byte) '9' && b5 == (byte) 'a') {
                        return true;
                    }

                    return false;
                }
            });

            validators.put("jpg", new AbstractTypeValidator() {
                @Override
                public boolean validate() {
                    if (b0 == -1 && b1 == -40 && n1 == -1 && n2 == -39) {
                        return true;
                    }
                    else if (b6 == (byte) 'J' && b7 == (byte) 'F' && b8 == (byte) 'I' && b9 == (byte) 'F') {
                        return true;
                    }
                    else if (b6 == (byte) 'E' && b7 == (byte) 'x' && b8 == (byte) 'i' && b9 == (byte) 'f') {
                        return true;
                    }

                    return false;
                }
            });

            validators.put("jpeg", new AbstractTypeValidator() {
                @Override
                public boolean validate() {
                    if (b0 == -1 && b1 == -40 && n1 == -1 && n2 == -39) {
                        return true;
                    }
                    else if (b6 == (byte) 'J' && b7 == (byte) 'F' && b8 == (byte) 'I' && b9 == (byte) 'F') {
                        return true;
                    }
                    else if (b6 == (byte) 'E' && b7 == (byte) 'x' && b8 == (byte) 'i' && b9 == (byte) 'f') {
                        return true;
                    }

                    return false;
                }
            });

            validators.put("png", new AbstractTypeValidator() {
                @Override
                public boolean validate() {
                    if (b0 == -119 && b1 == (byte) 'P' && b2 == (byte) 'N' && b3 == (byte) 'G' && b4 == 13 && b5 == 10
                            && b6 == 26) {
                        return true;
                    }
                    return false;
                }
            });

            validators.put("bmp", new AbstractTypeValidator() {
                @Override
                public boolean validate() {
                    if (b0 == (byte) 'B' && b1 == (byte) 'M') {
                        return true;
                    }

                    return false;
                }
            });
        }

        /**
         * 根据后缀得到对应的验证接口
         * 
         * @param suffix
         * @return
         */
        public static ImageTypeValidator getValidator(String suffix) {
            return validators.get(suffix);
        }
    }

    /**
     * 按照图片字节信息进行验证的验证接口
     * 
     * @author Administrator
     * 
     */
    static interface ImageTypeValidator {
        boolean validate(byte[] imgContent);
    }

    static abstract class AbstractTypeValidator implements ImageTypeValidator {
        int len = 0;
        byte n1;
        byte n2;
        byte b0;
        byte b1;
        byte b2;
        byte b3;
        byte b4;
        byte b5;
        byte b6;
        byte b7;
        byte b8;
        byte b9;

        @Override
        public boolean validate(byte[] imgContent) {
            len = imgContent.length;
            n1 = imgContent[len - 2];
            n2 = imgContent[len - 1];
            b0 = imgContent[0];
            b1 = imgContent[1];
            b2 = imgContent[2];
            b3 = imgContent[3];
            b4 = imgContent[4];
            b5 = imgContent[5];
            b6 = imgContent[6];
            b7 = imgContent[7];
            b8 = imgContent[8];
            b9 = imgContent[9];

            return validate();
        }

        public abstract boolean validate();

    }

}
