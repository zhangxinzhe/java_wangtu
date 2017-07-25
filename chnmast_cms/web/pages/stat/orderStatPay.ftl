<p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span id="couDetailDiv_title">支付记录</span></p>
<div>        
    <div class="wrap">
        <div class="org-search">
            <div class="org-search-inner">
                <#if orderAlipayList?exists || orderCashpay?exists || orderWechatpayList?exists >
                    <#if payType==1> <#-- 支付宝-->
                    <table class="public-table  mt-10">
                        <tr>
                            <th class="t-center" style="width:50px;">序号</th>
                            <th>订单号</th>
                            <th>创建时间</th>
                            <th>支付状态</th>
                        </tr>
                        <#list orderAlipayList as orderAlipay>
                        <tr>
                            <td class="t-center">${orderAlipay_index+1}</td>
                            <td>${orderAlipay.tradeNo!}</td>
                            <td>${orderAlipay.creationTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                            <td>${orderAlipay.formState.nameValue!}</td>
                        </tr>
                        </#list>
                    </table>
                    <#elseif payType==3><#--微信支付 -->
                    <table class="public-table  mt-10">
                        <tr>
                            <th class="t-center" style="width:50px;">序号</th>
                            <th>订单号</th>
                            <th>创建时间</th>
                            <th>支付状态</th>
                        </tr>
                        <#list orderWechatpayList as orderWechatpay>
                        <tr>
                            <td class="t-center">${orderWechatpay_index+1}</td>
                            <td>${orderWechatpay.tradeNo!}</td>
                            <td>${orderWechatpay.creationTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                            <td>${orderWechatpay.formState.nameValue!}</td>
                        </tr>
                        </#list>
                    </table>
                    <#elseif payType==4 || payType==5>  <#--线下支付 免费-->
                    <div class="userInfo-wrap">
                    <table class="form-table mt-10">
                        <tr>
                            <th>操作人：</th>
                            <td>${orderCashpay.opeRealname!}</td>
                        </tr>
                        <tr>
                            <th>操作时间：</th>
                            <td>${orderCashpay.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                        </tr>
                        <tr>
                            <th>收款人：</th>
                            <td>${orderCashpay.cashRealname!}</td>
                        </tr>
                        <tr>
                            <th>收款时间：</th>
                            <td>${orderCashpay.cashTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                        </tr>
                        <tr>
                            <th>收款金额：</th>
                            <td>${orderCashpay.cashAmount?string("0.00")}</td>
                        </tr>
                        <tr>
                            <th>收款方式：</th>
                            <td>${orderCashpay.chargeType!}</td>
                        </tr>
                        <tr>
                            <th>备注：</th>
                            <td>${orderCashpay.remark!}</td>
                        </tr>
                    </table>
                    </div>
                    </#if>
                <#else>
                <div class="public-nodata">此订单无支付记录...</div>
                </#if>
            </div>
       </div>
    </div>
</div>
<p class="dd">
    <a class="abtn abtn-green ml-5" href="javascript:;" id="couDetailDiv_esc"><span>关闭</span></a>
</p>  
