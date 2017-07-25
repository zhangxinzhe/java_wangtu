package net.zdsoft.common.alipay.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.util.GUID;

import net.zdsoft.common.alipay.entity.AlipayParam;
import net.zdsoft.common.alipay.entity.WapAlipayParam;
import net.zdsoft.common.alipay.enums.AlipayService;
import net.zdsoft.common.alipay.service.AlipayApiService;
import net.zdsoft.common.alipay.util.Payment;
import net.zdsoft.common.alipay.util.SignUtil;
import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.utils.HttpUtil;

@Service("alipayApiService")
public class AlipayApiServiceImpl implements AlipayApiService {
    private static final Logger log = LoggerFactory.getLogger(AlipayApiServiceImpl.class);
    /**
     * 交易成功，且可对该交易做操作，如：多级分润、退款等。
     */
    public static final String TRADE_SUCCESS = "TRADE_SUCCESS";
    /**
     * 交易成功且结束，即不可再做任何操作。
     */
    public static final String TRADE_FINISHED = "TRADE_FINISHED";
    /**
     * 交易创建，等待买家付款。
     */
    public static final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
    /**
     * 在指定时间段内未支付时关闭的交易； 在交易完成全额退款成功时关闭的交易。
     */
    public static final String TRADE_CLOSED = "TRADE_CLOSED";
    /**
     * 交易失败
     */
    public static final String TRADE_FAIL = "TRADE_FAIL";

    @Override
    public JSONObject singleTradeQuery(String tradeNo) {
        return dealTrade(tradeNo, AlipayService.SINGLE_TRADE_QUERY);
    }

    @Override
    public JSONObject closeTrade(String tradeNo) {
        return dealTrade(tradeNo, AlipayService.CLOSE_TRADE);
    }

    @Override
    public boolean verifySign(Object params, String sign, String signType) {
        if (StringUtils.isBlank(signType)) {
            signType = "MD5";
        }

        String inputCharset = "utf-8";
        // RSA
        if (signType.equalsIgnoreCase("RSA")) {
            String aliPublicKey = NetstudyConfig.getParam(BaseConstants.ALIPAY_RSA_PUBLIC_KEY);
            return SignUtil.verifyRSA(params, sign, aliPublicKey, inputCharset);
        }

        // MD5
        String privateKey = NetstudyConfig.getParam(BaseConstants.ALIPAY_KEY);
        if (StringUtils.isBlank(privateKey)) {
            log.error("verifySign验证失败，MD5秘钥为空");
            return false;
        }
        return SignUtil.signMd5(params, privateKey, inputCharset).equals(sign);
    }

    @Override
    public JSONObject alipayWapTradeCreateDirect(Map<String, String> params, WapAlipayParam alipayParam) {
        // 请求业务参数详细
        StringBuffer reqDataToken = new StringBuffer();
        reqDataToken.append("<direct_trade_create_req>");
        reqDataToken.append("<notify_url>" + params.get("notify_url") + "</notify_url>");
        reqDataToken.append("<call_back_url>" + params.get("call_back_url") + "</call_back_url>");
        reqDataToken.append("<seller_account_name>" + alipayParam.getSellerEmail() + "</seller_account_name>");
        reqDataToken.append("<out_trade_no>" + params.get("out_trade_no") + "</out_trade_no>");
        reqDataToken.append("<subject>" + params.get("subject") + "</subject>");
        reqDataToken.append("<total_fee>" + params.get("total_fee") + "</total_fee>");
        // reqDataToken.append("<merchant_url></merchant_url>");支付中断回调
        reqDataToken.append("</direct_trade_create_req>");

        // 把请求参数打包成数组
        Map<String, String> sParaTempToken = new HashMap<String, String>();
        sParaTempToken.put("service", AlipayService.ALIPAY_WAP_TRADE_CREATE_DIRECT.getValue());
        sParaTempToken.put("partner", alipayParam.getPartner());
        sParaTempToken.put("_input_charset", alipayParam.getInputCharset());
        sParaTempToken.put("sec_id", alipayParam.getSignType());
        sParaTempToken.put("format", alipayParam.getFormat());
        sParaTempToken.put("v", alipayParam.getV());
        sParaTempToken.put("req_id", GUID.generateGUID());// 请求号
        sParaTempToken.put("req_data", reqDataToken.toString());

        // MD5
        String sign = SignUtil.signMd5(sParaTempToken, alipayParam.getKey(), "utf-8");
        sParaTempToken.put("sign", sign);
        sParaTempToken.put("sign_type", "utf-8");
        String url = alipayParam.getPayGateway() + "?_input_charset=" + alipayParam.getInputCharset();
        String result = null;
        JSONObject jsonResult = new JSONObject();
        try {
            result = HttpUtil.postForm(url, sParaTempToken);
        }
        catch (Exception e) {
            e.printStackTrace();
            jsonResult.put("error", e.getMessage());
            return jsonResult;
        }
        // 解析文件
        try {
            jsonResult = parseAlipayWapTradeCreateDirectResult(result);
            if (jsonResult.get("err") != null) {
                jsonResult.put("error", jsonResult.getJSONObject("err").getString("msg"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            jsonResult.put("error", e.getMessage());
            return jsonResult;
        }

        return jsonResult;
    }

    @Override
    public String mobileSecuritypayPay(Map<String, String> params, WapAlipayParam alipayParam)
            throws UnsupportedEncodingException {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + alipayParam.getPartner() + "\"";
        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + alipayParam.getSellerEmail() + "\"";
        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + params.get("out_trade_no") + "\"";
        // 商品名称
        orderInfo += "&subject=" + "\"" + params.get("subject") + "\"";
        // 商品详情
        orderInfo += "&body=" + "\"" + params.get("body") + "\"";
        // 商品金额
        orderInfo += "&total_fee=" + "\"" + params.get("total_fee") + "\"";
        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + params.get("notify_url") + "\"";
        // 服务接口名称， 固定值
        orderInfo += "&service=\"" + AlipayService.MOBILE_SECURITYPAY_PAY.getValue() + "\"";
        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";
        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"" + alipayParam.getInputCharset() + "\"";
        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"7d\"";
        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        // orderInfo += "&return_url=\"\"";
        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        // RSA 签名
        String sign = SignUtil.signRSA(orderInfo, alipayParam.getRsaPrivateKey(), "utf-8");
        // 仅需对sign 做URL编码
        sign = URLEncoder.encode(sign, "utf-8");
        return orderInfo + "&sign_type=\"RSA\"&sign=\"" + sign + "\"";
    }

    @Override
    public JSONObject buildTradeNotifyParams(Map<String, String> params)
            throws UnsupportedEncodingException, DocumentException {
        JSONObject returnJson = null;
        /* =========== app同步响应的日志_S =============== */
        String sync_content = params.get("sync_content");
        if (StringUtils.isNotBlank(sync_content)) {
            // sync_content = URLDecoder.decode(sync_content, "utf-8");
            returnJson = appSyncTradeNotify(sync_content);
            returnJson.put("my_notify_type", "APP_SYNC");
            // returnJson.put("pay_type", OrderPayType.ONLINE_APP);
            return returnJson;
        }
        /* =========== app同步响应的日志_E =============== */

        /* =========== wap网页同步响应的日志_S =============== */
        // 例:out_trade_no=1320742949342&request_token=201008309e298cf01c58146274208eda1e4cdf2b&result=success&trade_no=2014040311001004370000361525&sign=49a330fee069465c64e561a25bf31c78
        String request_token = params.get("request_token");
        if (StringUtils.isNotBlank(request_token)) {
            returnJson = new JSONObject();
            returnJson.put("my_notify_type", "WAP_SYNC");
            // returnJson.put("pay_type", OrderPayType.ONLINE_WAP);
            returnJson.put("raw", params);
            returnJson.put("out_trade_no", params.get("out_trade_no"));
            returnJson.put("trade_no", params.get("trade_no"));
            String result = params.get("result");
            if (StringUtils.isNotBlank(result) && result.equalsIgnoreCase("success")) {
                returnJson.put("trade_status", TRADE_SUCCESS);
            }
            else {
                returnJson.put("trade_status", TRADE_FAIL);
            }

            returnJson.put("sign_content", params);
            returnJson.put("sign", params.get("sign"));
            returnJson.put("sign_type", params.get("sign_type"));
            return returnJson;
        }
        /* =========== wap网页同步响应的日志_E =============== */
        // sign=56b9e6f81c4bcd3a8f7871370e559caf, v=1.0, sec_id=MD5,
        // notify_data=<notify><payment_type>1</payment_type><subject>[支付宝][浙大万朋][其他学校][申屠祖斌][网上支付]</subject><trade_no>2015120700001000820063266077</trade_no><buyer_email>15067140779</buyer_email><gmt_create>2015-12-07
        // 14:43:26</gmt_create><notify_type>trade_status_sync</notify_type><quantity>1</quantity><out_trade_no>4028BCF0517B2F6501517B2F65180000</out_trade_no><notify_time>2015-12-07
        // 14:57:19</notify_time><seller_id>2088001150458334</seller_id><trade_status>TRADE_FINISHED</trade_status><is_total_fee_adjust>N</is_total_fee_adjust><total_fee>0.01</total_fee><gmt_payment>2015-12-07
        // 14:43:27</gmt_payment><seller_email>wpedu@winupon.com</seller_email><gmt_close>2015-12-07
        // 14:43:27</gmt_close><price>0.01</price><buyer_id>2088602276222823</buyer_id><notify_id>c0d3bca344572693fd25ff0ea4dea6a16k</notify_id><use_coupon>N</use_coupon></notify>,
        // service=alipay.wap.trade.create.direct
        String notify_data = params.get("notify_data");
        if (StringUtils.isNotBlank(notify_data)) {
            returnJson = new JSONObject();
            Document document = DocumentHelper.parseText(notify_data);
            parseXml2Json(document.getRootElement(), returnJson);
            returnJson = returnJson.getJSONObject("notify");
            returnJson.put("my_notify_type", "WAP_ASYN");
            // returnJson.put("pay_type", OrderPayType.ONLINE);
            returnJson.put("raw", params);
            returnJson.put("out_trade_no", returnJson.getString("out_trade_no"));
            returnJson.put("trade_no", returnJson.getString("trade_no"));
            returnJson.put("notify_id", returnJson.getString("notify_id"));
            returnJson.put("total_fee", returnJson.getString("total_fee"));
            returnJson.put("notify_time", returnJson.getString("notify_time"));
            String trade_status = returnJson.getString("trade_status");
            // WAIT_BUYER_PAY,TRADE_CLOSED,TRADE_SUCCESS,TRADE_PENDING：等待卖家收款（买家付款后，如果卖家账号被冻结）,TRADE_FINISHED
            if (trade_status.equals("TRADE_FINISHED")) {
                returnJson.put("trade_status", TRADE_FINISHED);
            }
            else if (trade_status.equals("TRADE_SUCCESS")) {
                returnJson.put("trade_status", TRADE_SUCCESS);
            }
            else {
                returnJson.put("trade_status", trade_status);
            }

            String sign_type = params.get("sec_id");
            if (sign_type.equals("0001")) {
                sign_type = "RSA";
            }
            else {
                sign_type = "MD5";
            }

            // 签名内容
            StringBuilder gotoSign_params = new StringBuilder();
            gotoSign_params.append("service=" + params.get("service"));
            gotoSign_params.append("&v=" + params.get("v"));
            gotoSign_params.append("&sec_id=" + params.get("sec_id"));
            gotoSign_params.append("&notify_data=" + params.get("notify_data"));
            returnJson.put("sign_content", gotoSign_params.toString());
            returnJson.put("sign", params.get("sign"));
            return returnJson;
        }
        /* =========== wap网页异步响应的日志_S =============== */

        /* =========== wap网页异步响应的日志_E =============== */

        /* =========== 异步或web同步响应的日志_S =============== */
        returnJson = new JSONObject();
        returnJson.put("my_notify_type", "OTHER");
        // returnJson.put("pay_type", OrderPayType.ONLINE);
        returnJson.put("raw", params);
        returnJson.put("out_trade_no", params.get("out_trade_no"));
        returnJson.put("trade_no", params.get("trade_no"));
        returnJson.put("notify_id", params.get("notify_id"));
        returnJson.put("total_fee", params.get("total_fee"));
        returnJson.put("notify_time", params.get("notify_time"));
        String trade_status = params.get("trade_status");
        if (trade_status.equals("TRADE_FINISHED")) {
            returnJson.put("trade_status", TRADE_FINISHED);
        }
        else if (trade_status.equals("TRADE_SUCCESS")) {
            returnJson.put("trade_status", TRADE_SUCCESS);
        }
        else {
            returnJson.put("trade_status", TRADE_FAIL);
        }
        returnJson.put("sign_content", params);
        returnJson.put("sign", params.get("sign"));
        returnJson.put("sign_type", params.get("sign_type"));
        return returnJson;
        /* =========== 异步或web同步响应的日志_E =============== */
    }

    // public static void main(String[] args) {
    // String con =
    // "resultStatus={9000};memo={};result={partner=\"2088101568358171\"&seller_id=\"xxx@alipay.com\"&out_trade_no=\"0819145412-6177\"&subject=\"测试\"&body=\"测试测试\"&total_fee=\"0.01\"&notify_url=\"http://notify.msp.hk/notify.htm\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"30m\"&success=\"true\"&sign_type=\"RSA\"&sign=\"hkFZr+zE9499nuqDNLZEF7W75RFFPsly876QuRSeN8WMaUgcdR00IKy5ZyBJ4eldhoJ/2zghqrD4E2G2mNjs3aE+HCLiBXrPDNdLKCZgSOIqmv46TfPTEqopYfhs+o5fZzXxt34fwdrzN4mX6S13cr3UwmEV4L3Ffir/02RBVtU=\"}";
    // new AlipayApiServiceImpl().appSyncTradeNotify(con);
    // }

    /**
     * 支付宝app同步响应订单
     *
     * @param content
     */
    public JSONObject appSyncTradeNotify(String content) {
        // 返回信息示例:
        // resultStatus={9000};
        // memo={};
        // result={partner="2088101568358171"&seller_id="xxx@alipay.com"&out_trade_no="0819145412-6177"&subject="测试"&body="测试测试"&total_fee="0.01"&notify_url="http://notify.msp.hk/notify.htm"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&success="true"&sign_type="RSA"&sign="hkFZr+zE9499nuqDNLZEF7W75RFFPsly876QuRSeN8WMaUgcdR00IKy5ZyBJ4eldhoJ/2zghqrD4E2G2mNjs3aE+HCLiBXrPDNdLKCZgSOIqmv46TfPTEqopYfhs+o5fZzXxt34fwdrzN4mX6S13cr3UwmEV4L3Ffir/02RBVtU="}

        // System.out.println("appSyncTradeNotify=================================S");
        // System.out.println(content);
        // System.out.println("appSyncTradeNotify=================================E");

        // 没有解码就进行解码
        if (content.indexOf("\\=\\{") < 0) {
            try {
                content = URLDecoder.decode(content, "utf-8");
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        JSONObject returnJson = new JSONObject();
        returnJson.put("raw", content);// 原始信息

        // 获取result和resultStatus
        String[] tempArray = content.split("\\=\\{");
        String tempValue = null;
        JSONObject params = new JSONObject();
        for (int i = 0; i < tempArray.length - 1; i++) {
            if (tempArray[i].indexOf("resultStatus") >= 0) {
                tempValue = tempArray[i + 1].substring(0, tempArray[i + 1].lastIndexOf("}"));
                params.put("resultStatus", tempValue);
                continue;
            }
            if (tempArray[i].indexOf("result") >= 0) {
                tempValue = tempArray[i + 1].substring(0, tempArray[i + 1].lastIndexOf("}"));
                params.put("result", tempValue);
            }
        }

        // 解析result，即订单详细信息
        String[] valueKeys = params.getString("result").split("&");
        if (!ArrayUtils.isEmpty(valueKeys)) {
            String[] temp = null;
            for (String valueKey : valueKeys) {
                temp = valueKey.split("=\"", 2);// 分成两节
                if (temp != null && temp.length >= 2) {
                    params.put(temp[0], temp[1].substring(0, temp[1].length() - 1));
                }
            }
        }

        // 订单id
        String tradeNo = params.getString("out_trade_no");
        returnJson.put("out_trade_no", tradeNo);// 原始信息
        returnJson.put("total_fee", params.get("total_fee"));

        // 订单状态管理
        String resultStatus = params.getString("resultStatus");
        if ("9000".equals(resultStatus)) {
            returnJson.put("trade_status", TRADE_SUCCESS);
            returnJson.put("msg", "订单支付成功！");
        }
        else if ("8000".equals(resultStatus)) {
            returnJson.put("trade_status", WAIT_BUYER_PAY);
            returnJson.put("msg", "订单等待支付中！");
        }
        else if ("4000".equals(resultStatus)) {
            returnJson.put("trade_status", TRADE_CLOSED);
            returnJson.put("msg", "订单支付失败！");
        }
        else if ("6001".equals(resultStatus)) {
            returnJson.put("trade_status", TRADE_CLOSED);
            returnJson.put("msg", "用户中途取消！");
        }
        else if ("6002".equals(resultStatus)) {
            returnJson.put("trade_status", TRADE_FAIL);
            returnJson.put("msg", "网络连接出错！");
        }

        // 签名验证
        String sign = params.getString("sign");
        String signType = params.getString("sign_type");

        // 签名字段组装
        String signContent = params.getString("result");
        if (StringUtils.isNotBlank(signContent)) {
            signContent = signContent.replaceFirst("&?sign=\".*\"&?", "&");
            signContent = signContent.replaceFirst("&?sign_type=\".*\"&?", "&");
            if (signContent.lastIndexOf("&") == signContent.length() - 1) {
                signContent = signContent.substring(0, signContent.length() - 1);
            }

            returnJson.put("sign_content", signContent);
            returnJson.put("sign", sign);
            returnJson.put("sign_type", signType);
        }
        return returnJson;
    }

    private JSONObject parseAlipayWapTradeCreateDirectResult(String result)
            throws DocumentException, UnsupportedEncodingException {
        String dataKey = "res_data";
        if (result.indexOf("res_error") >= 0) {
            dataKey = "res_error";
        }

        JSONObject resultJson = new JSONObject();
        String[] datas = result.split("&");
        for (String data : datas) {
            if (data.indexOf(dataKey) >= 0) {
                data = data.replace(dataKey + "=", "");
                data = URLDecoder.decode(data, "utf-8");
                Document document = DocumentHelper.parseText(data);
                parseXml2Json(document.getRootElement(), resultJson);
                break;
            }
        }
        resultJson.put("ret_data", result);
        return resultJson;
    }

    /**
     * 调用接口处理订单
     *
     * @param tradeNo
     * @param alipayService
     * @return
     */
    private JSONObject dealTrade(String tradeNo, AlipayService alipayService) {

        JSONObject resultJson = new JSONObject();

        // 生成sign
        AlipayParam alipayParam = new AlipayParam();
        alipayParam.setOutTradeNo(tradeNo);
        // alipayParam.setPartner(NetstudyConfig.getParam(BaseConstants.ALIPAY_PARTNER));
        // alipayParam.setSellerEmail(NetstudyConfig.getParam(BaseConstants.ALIPAY_SELLER_EMAIL));
        // alipayParam.setKey(NetstudyConfig.getParam(BaseConstants.ALIPAY_KEY));
        // alipayParam.setRsaPrivateKey(NetstudyConfig.getParam(BaseConstants.ALIPAY_RSA_PRIVATE_KEY));
        // alipayParam.setRsaPublicKey(NetstudyConfig.getParam(BaseConstants.ALIPAY_RSA_PUBLIC_KEY));
        alipayParam.setService(alipayService);
        String strUrl = null;
        try {
            // 构造访问接口的URL串
            strUrl = Payment.CreateUrl(alipayParam);
            log.debug("strUrl:" + strUrl);
        }
        catch (Exception e) {
            log.error("支付宝MD5签名错误！", e);
            e.printStackTrace();
            resultJson.put("error_code", "config_error");
            resultJson.put("error", "可能支付宝MD5签名错误！");
            return resultJson;
        }

        // 获取xml
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(new URL(strUrl).openStream());
        }
        catch (Exception e) {
            log.error("连接支付宝检查失败，请检查网络！", e);
            e.printStackTrace();
            resultJson.put("error_code", "connect_error");
            resultJson.put("error", "连接支付宝检查失败，请检查网络！");
            return resultJson;
        }

        // 解析xml
        try {
            Element root = doc.getRootElement();
            parseXml2Json(root, resultJson);
        }
        catch (Exception e) {
            log.error("支付宝信息解析失败！", e);
            e.printStackTrace();
            resultJson.put("error_code", "parse_error");
            resultJson.put("error", "支付宝信息解析失败！");
            return resultJson;
        }

        if (resultJson.getJSONObject("alipay") == null) {
            resultJson.put("error_code", "other_error");
            resultJson.put("error", "支付宝信息异常！");
            return resultJson;
        }
        resultJson = resultJson.getJSONObject("alipay");

        // 支付宝返回的错误
        if ("F".equalsIgnoreCase(resultJson.getString("is_success"))) {
            resultJson.put("error_code", "return_error");
            resultJson.put("error", resultJson.getString("error"));
            return resultJson;
        }

        // 订单查询基本信息错误
        if (alipayParam.getService() == AlipayService.SINGLE_TRADE_QUERY) {
            String errorMsg = checkInfo(resultJson, alipayParam);
            if (errorMsg != null) {
                resultJson.put("error_code", "other_error");
                resultJson.put("error", errorMsg);
                return resultJson;
            }

            // 签名验证
            try {
                // 检查签名方式
                if (!"MD5".equalsIgnoreCase(resultJson.getString("sign_type"))) {
                    resultJson.put("error_code", "other_error");
                    resultJson.put("error", "签名方式异常！");
                }
                // 签名
                String sign = resultJson.getString("sign");
                JSONObject verifyJson = resultJson.getJSONObject("response").getJSONObject("trade");
                Set<String> keySet = verifyJson.keySet();
                Map<String, String> verifyMap = new HashMap<String, String>();
                for (String key : keySet) {
                    verifyMap.put(key, verifyJson.getString(key));
                }
                // 签名验证
                if (!verifySign(verifyMap, sign, "md5")) {
                    resultJson.put("error_code", "verify_error");
                    resultJson.put("error", "支付宝返回信息签名验证失败！");
                    return resultJson;
                }
            }
            catch (Exception e) {
                log.error("支付宝返回信息签名验证失败！", e);
                e.printStackTrace();
                resultJson.put("error_code", "config_error");
                resultJson.put("error", "支付宝返回信息验证失败！");
                return resultJson;
            }
        }

        return resultJson;

    }

    /**
     * 验证基本信息是否正确
     *
     * @param resultJson
     * @param alipayParam
     * @return
     */
    private String checkInfo(JSONObject resultJson, AlipayParam alipayParam) {
        if (resultJson.getJSONObject("response") == null) {
            return "支付宝信息异常！";
        }

        if (resultJson.getJSONObject("response").getJSONObject("trade") == null) {
            return "支付宝信息异常！";
        }
        resultJson = resultJson.getJSONObject("response").getJSONObject("trade");

        // 验证支付宝id
        if (!(String.valueOf(alipayParam.getPartner()).equals(resultJson.getString("seller_id")))) {
            return "返回支付宝id错误！";
        }

        // 验证支付宝账号
        if (!alipayParam.getSellerEmail().equals(resultJson.getString("seller_email"))) {
            return "返回支付宝Eamil错误！";
        }

        // 订单不一致
        if (!alipayParam.getOutTradeNo().equals(resultJson.getString("out_trade_no"))) {
            return "返回支付宝订单号错误！";
        }
        return null;
    }

    /**
     * 将xml转换成json
     *
     * @param element
     * @param parent
     */
    private void parseXml2Json(Element element, JSONObject parent) {
        List<?> elements = element.elements();
        String name = element.getName();
        // 没有子元素
        if (elements.isEmpty()) {
            String value = element.getTextTrim();
            parent.put(name, value);
        }
        else {
            // 子元素
            JSONObject child = new JSONObject();
            parent.put(name, child);

            // 有子元素
            Iterator<?> it = elements.iterator();
            while (it.hasNext()) {
                Element elem = (Element) it.next();
                // 递归遍历
                parseXml2Json(elem, child);
            }
        }
    }
}
