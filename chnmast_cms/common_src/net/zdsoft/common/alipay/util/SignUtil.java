package net.zdsoft.common.alipay.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SignUtil {

    /**
     * MD5签名
     *
     * @param content
     * @param privateKey
     * @param inputCharset
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String signMd5(Object params, String privateKey, String inputCharset) {
        if (params instanceof String) {
            return Md5.sign((String) params, privateKey, inputCharset);
        }
        return Md5.sign(getSignatureContent((Map<String, String>) params), privateKey, inputCharset);
    }

    /**
     * RSA签名
     *
     * @param params
     *            string 或 Map<String, String>
     * @param privateKey
     * @param inputCharset
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String signRSA(Object params, String privateKey, String inputCharset) {
        if (params instanceof String) {
            return RSA.sign((String) params, privateKey, inputCharset);
        }
        return RSA.sign(getSignatureContent((Map<String, String>) params), privateKey, inputCharset);
    }

    /**
     * RSA签名校验
     *
     * @param params
     *            string 或 Map<String, String>
     * @param sign
     * @param aliPublicKey
     * @param inputCharset
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean verifyRSA(Object params, String sign, String aliPublicKey, String inputCharset) {
        if (params instanceof String) {
            return RSA.verify((String) params, sign, aliPublicKey, inputCharset);
        }

        return RSA.verify(getSignatureContent((Map<String, String>) params), sign, aliPublicKey, inputCharset);
    }

    /**
     * 排序
     *
     * @param params
     * @return
     */
    public static String getSignatureContent(Map<String, String> params) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (key == null || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }
        return content.toString();
    }

    public static void main(String[] args) {
        String sign = "CxSIJDxPZrlmw0/9V5UXtjYPC2DbP9nzFgfd23Ydu1hzfc5CB2zugSKwYiZA/MImO/RS+BhyJg4ximFNb+xBwrGDuRfQAXA7Lzyz+LQ0d3TcUxnv5O3hYdJWh2vhEsHCW716KzROwTPHN5WB3imEfVZ5rxf1CuhQ7Ado0oRiyo8=";
        String aliPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
        String parmas = "partner=\"2088001150458334\"&seller_id=\"wpedu@winupon.com\"&out_trade_no=\"4028BCF051EDC1F00151EDC1F0290000\"&subject=\"[支付宝][浙大万朋][其他学校][小学][2015][1(1)班][2233][网上支付]\"&body=\"[支付宝][浙大万朋][其他学校][小学][2015][1(1)班][2233][网上支付]\"&total_fee=\"0.01\"&notify_url=\"http://www.netstudy5.dev/shoppingCar/alipayOrderNotify.htm\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"7d\"&success=\"true\"";
        System.out.println(SignUtil.verifyRSA(parmas, sign, aliPublicKey, "utf-8"));
    }
}
