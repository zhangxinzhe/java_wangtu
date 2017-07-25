package net.zdsoft.common.alipay.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.dom4j.DocumentException;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.common.alipay.entity.WapAlipayParam;

public interface AlipayApiService {
    /**
     * 单笔支付交易查询
     *
     * @param tradeNo
     * @return
     */
    public JSONObject singleTradeQuery(String tradeNo);

    /**
     * 关闭支付宝订单
     *
     * @param tradeNo
     * @return
     */
    public JSONObject closeTrade(String tradeNo);

    /**
     * 签名验证
     *
     * @param params
     *            Map<String, String>或String
     * @param sign
     * @param signType
     * @param agencyId
     * @return
     */
    public boolean verifySign(Object params, String sign, String signType);

    /**
     * 手机网页即时到账授权接口
     *
     * @param params
     * @return
     */
    public JSONObject alipayWapTradeCreateDirect(Map<String, String> params, WapAlipayParam alipayParam);

    /**
     * 手机移动端支付
     *
     * @return
     */
    public String mobileSecuritypayPay(Map<String, String> params, WapAlipayParam alipayParam)
            throws UnsupportedEncodingException;

    /**
     * 订单回调通知参数统一控制
     *
     * @param params
     * @return 成功:{
     *         "trade_status","out_trade_no","trade_no","price","notify_id","notify_time",pay_type:"支付方式:枚举OrderPayType"
     *         ,"sign","sign_type","sign_conent","raw":"原始数据"}
     */
    public JSONObject buildTradeNotifyParams(Map<String, String> params)
            throws UnsupportedEncodingException, DocumentException;
}
