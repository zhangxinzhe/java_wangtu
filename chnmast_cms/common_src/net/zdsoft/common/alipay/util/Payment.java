package net.zdsoft.common.alipay.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zdsoft.common.alipay.entity.AlipayParam;
import net.zdsoft.common.alipay.enums.AlipayService;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Payment {

    private static final Logger log = LoggerFactory.getLogger(Payment.class);

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        log.debug("content={}  charset={}", content, charset);
        byte[] contentByte = null;
        try {
            if (charset == null || "".equals(charset)) {
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

    public static String CreateUrl(AlipayParam alipayParam) throws Exception {
        Map params = getParams(alipayParam);
        String sign = getSign(alipayParam);
        log.debug("sign={}", sign);
        String parameter = alipayParam.getPayGateway() + "?";
        List keys = new ArrayList(params.keySet());
        for (int i = 0; i < keys.size(); i++) {
            try {
                Object key = keys.get(i);
                parameter += key + "=" + URLEncoder.encode((String) params.get(key), alipayParam.getInputCharset())
                        + "&";
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        parameter += "sign=" + sign + "&sign_type=" + alipayParam.getSignType();
        return parameter;
    }

    public static String getSign(AlipayParam alipayParam) throws Exception {
        Map params = getParams(alipayParam);
        log.debug("params={}", params.toString());
        return createSign(params, alipayParam);
    }

    public static String createSign(Map params, AlipayParam alipayParam) throws Exception {
        return DigestUtils
                .md5Hex(getContentBytes(getContent(params, alipayParam.getKey()), alipayParam.getInputCharset()));
    }

    private static Map getParams(AlipayParam alipayParam) {
        Map params = new HashMap();
        params.put("service", alipayParam.getService().getValue());
        // 关闭订单参数不同
        if (alipayParam.getService() == AlipayService.CLOSE_TRADE) {
            params.put("out_order_no", alipayParam.getOutTradeNo());
        }
        else {
            params.put("out_trade_no", alipayParam.getOutTradeNo());
        }
        params.put("partner", alipayParam.getPartner());
        params.put("_input_charset", alipayParam.getInputCharset());
        // 判断是否是支付订单
        if (alipayParam.getService() == AlipayService.CREATE_DIRECT_PAY_BY_USER) {
            params.put("payment_type", alipayParam.getPaymentType());
            params.put("notify_url", alipayParam.getNotifyUrl());
            params.put("return_url", alipayParam.getReturnUrl());
            params.put("seller_email", alipayParam.getSellerEmail());
            // params.put("it_b_pay", alipayParam.getItBPay());
            params.put("subject", alipayParam.getSubject());
            params.put("total_fee", alipayParam.getTotalFee());
            params.put("paymethod", alipayParam.getPayMethod());
            if ("bankPay".equalsIgnoreCase(alipayParam.getPayMethod())) {
                params.put("defaultbank", alipayParam.getDefaultBank());
            }
            else if ("creditCard".equalsIgnoreCase(alipayParam.getPayMethod())) {
                params.put("defaultbank", alipayParam.getDefaultBank());
                params.put("credit_card_pay", alipayParam.getCreditCardPay());
                params.put("credit_card_default_display", alipayParam.getCreditCardDefaultDisplay());
            }
        }
        return params;
    }

    private static String getContent(Map params, String privateKey) {
        log.debug("privateKey={}", privateKey);
        List keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            if (key == null || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            String value = (String) params.get(key);
            if (i == keys.size() - 1) {
                prestr += key + "=" + value;
            }
            else {
                prestr += key + "=" + value + "&";
            }
        }
        log.debug("content={}", prestr);
        return prestr + privateKey;
    }

    public static void main(String args[]) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("service", AlipayService.CLOSE_TRADE.getValue());
        params.put("out_trade_no", "4028BCF047E801630147E80163DD0000");
        params.put("partner", "2088001150458334");
        params.put("_input_charset", "utf-8");
        // params.put("payment_type", "1");
        // params.put("notify_url", "http://yy.kehou.com/account/alipayChargeNotify.action");
        // params.put("return_url", "http://yy.kehou.com/account/alipayChargeReturn.action");
        // params.put("seller_email", "alipay@winupon.com");
        // params.put("subject", "yuyao_netstudy_alipay");
        // params.put("total_fee", "0.01");
        // params.put("defaultbank", "");
        System.out.println(
                "_input_charset=utf-8&out_trade_no=4028BCF047E801630147E80163DD0000&partner=2088001150458334&service=close_traden35qh37ewn8ghuux0hsh6ovmjhq12oyf"
                        .equals("_input_charset=utf-8&out_order_no=4028BCF047E801630147E80163DD0000&partner=2088001150458334&service=close_traden35qh37ewn8ghuux0hsh6ovmjhq12oyf"));
        System.out.println(
                DigestUtils.md5Hex(getContentBytes(getContent(params, "n35qh37ewn8ghuux0hsh6ovmjhq12oyf"), "utf-8")));
    }
    // d2caea5b15d4d327f25d1097aed76945
}
