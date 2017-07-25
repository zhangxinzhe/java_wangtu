package net.zdsoft.common.alipay.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zdsoft.common.alipay.entity.AlipayParam;

public class Md5 {
    private static final Logger log = LoggerFactory.getLogger(Md5.class);

    /**
     * md5签名
     *
     * @param content
     * @param privateKey
     * @param inputCharset
     * @return
     */
    public static String sign(String content, String privateKey, String inputCharset) {
        if (privateKey == null) {
            return null;
        }
        String signBefore = content + privateKey;
        return DigestUtils.md5Hex(getContentBytes(signBefore, inputCharset));
    }

    private static byte[] getContentBytes(String content, String charset) {
        log.debug("content={}  charset={}", content, charset);
        byte[] contentByte = null;
        try {
            if (StringUtils.isBlank(charset)) {
                contentByte = content.getBytes(new AlipayParam().getInputCharset());
            }
            else {
                contentByte = content.getBytes(charset);
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
        return contentByte;
    }
}
