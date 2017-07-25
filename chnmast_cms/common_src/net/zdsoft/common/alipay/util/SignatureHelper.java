package net.zdsoft.common.alipay.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zdsoft.common.alipay.entity.AlipayParam;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class SignatureHelper {

    private static final Logger log = LoggerFactory.getLogger(SignatureHelper.class);

    public static String sign(Map params, String privateKey) {
        log.debug("params={}", params.toString());
        log.debug("privateKey={}", privateKey);
        Properties properties = new Properties();
        for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            Object value = params.get(name);
            if (name == null || name.equalsIgnoreCase("sign") || name.equalsIgnoreCase("sign_type")) {
                continue;
            }
            properties.setProperty(name, value.toString());
        }
        String content = getSignatureContent(properties);
        log.debug("content={}", content);
        return sign(content, privateKey, (String) params.get("_input_charset"));
    }

    private static String getSignatureContent(Properties properties) {
        StringBuffer content = new StringBuffer();
        List keys = new ArrayList(properties.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = properties.getProperty(key);
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }
        return content.toString();
    }

    private static String sign(String content, String privateKey, String inputCharset) {
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

    public static void main(String args[]) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("_input_charset", "utf-8");
        params.put("total_fee", "0.01");
        params.put("subject", "yuyao_netstudy_alipay");
        params.put("notify_url", "http://yy.kehou.com/account/alipayChargeNotify.action");
        params.put("service", "create_direct_pay_by_user");
        params.put("partner", "2088001150458334");
        params.put("seller_email", "alipay@winupon.com");
        params.put("out_trade_no", "FF80808131D68CEC0131D68CEC3A0000");
        params.put("payment_type", "1");
        params.put("return_url", "http://yy.kehou.com/account/alipayChargeReturn.action");
        params.put("sign", "5224e2830b184256ad8291b144d4a66d");
        params.put("sign_type", "MD5");
        System.out.println(sign(params, "n35qh37ewn8ghuux0hsh6ovmjhq12oyf"));
        System.out.println(sign(params, ""));

        Map<String, String> params2 = new HashMap<String, String>();
        params2.put("buyer_id", "2088002649891579");
        params2.put("exterface", "create_direct_pay_by_user");
        params2.put("trade_no", "2011080848082957");
        params2.put("notify_time", "2011-08-08 10:59:28");
        params2.put("subject", "yuyao_alipay_charge");
        params2.put("sign_type", "MD5");
        params2.put("notify_type", "trade_status_sync");
        params2.put("trade_status", "TRADE_SUCCESS");
        params2.put("out_trade_no", "5C7AC09431A74DA30131A75443670001");
        params2.put("trade_status", "TRADE_SUCCESS");
        params2.put("sign", "5ca7305f7115da89808624e35616be87");
        params2.put("is_success", "T");
        params2.put("buyer_email", "qiyesucky@163.com");
        params2.put("total_fee", "0.01");
        params2.put("seller_id", "2088001150458334");
        params2.put("notify_id", "RqPnCoPT3K9%2Fvwbh3I7w58BftjKAYVUreXgOpU6bbs9k4G2ollMyGqLsgL4EVClDzNdu");
        params2.put("seller_email", "alipay@winupon.com");
        params2.put("payment_type", "1");
        System.out.println(sign(params2, "n35qh37ewn8ghuux0hsh6ovmjhq12oyf"));
    }
}
