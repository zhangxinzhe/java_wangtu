package net.zdsoft.common.hifi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Mark
 * @ClassName:Md5Util
 * @Description:md5加密
 * @date 2012-1-5 上午11:22:05
 */
public class Md5Util {
    public static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String process(String pSource) {
        String lDigest = "None";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(pSource.getBytes());
            byte[] byteArray = md.digest();

            StringBuffer md5StrBuff = new StringBuffer();

            for (int i = 0; i < byteArray.length; i++) {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
                }
                else {
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
                }
            }
            lDigest = md5StrBuff.toString();
        }
        catch (NoSuchAlgorithmException lEx) {
            throw new RuntimeException("Problems calculating MD5", lEx);
        }
        return lDigest;
    }

    /**
     * @param @param
     *            file
     * @param @return
     * @param @throws
     *            IOException
     * @return String
     * @throws @Title:
     *             process
     * @Description: 获得文件的md5校验码
     */
    public static String process(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        try {
            MessageDigest md5 = null;
            if (file.isFile()) {
                inputStream = new FileInputStream(file);
                byte[] buffer = new byte[1024 * 10];
                try {
                    md5 = MessageDigest.getInstance("MD5");
                    int numRead = 0;
                    while ((numRead = inputStream.read(buffer)) > 0) {
                        md5.update(buffer, 0, numRead);
                    }

                    byte[] b = md5.digest();
                    sb = new StringBuilder(b.length * 2);
                    for (byte b1 : b) {
                        sb.append(hexChar[(b1 & 0xf0) >>> 4]);
                        sb.append(hexChar[b1 & 0x0f]);
                    }
                }
                catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return sb.toString();
    }

}
