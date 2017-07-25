<#if !order?exists>
<p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>没有找到订单信息</span></p>
<div class="wrap">
    <div class="record-wrap mt-10">
        <div class="public-nodata">查询不到相关的订单信息，请重试！</div>
    </div>
</div>
<#elseif order.payType! != "ALIPAY">
<p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>线下订单登记</span></p>
<div class="wrap">
    <form id = "cashpayForm" method="post">
    <input type="hidden" name="order.id" value="${order.id!}">
    <input type="hidden" name="method" value="finish">
    <table class="form-table validateForm">
        <tr>
            <th style="width:100px;"><span class="must">*</span>收款人姓名：</th>
            <td>
                <input type="text" class="input-txt" placeholder="输入收款人姓名" id="cashRealname" value="" name="cashpay.cashRealname" notNull="true" maxLength="20"/>
            </td>
        </tr>
        <tr>
            <th style="width:100px;"><span class="must">*</span>收款时间：</th>
            <td>
                <input type="text" class="input-date" style="width:200px;" id="cashTime" name="cashpay.cashTime" readonly notNull="true" 
                            onkeydown="if(window.event.keyCode == 8){return false;}"
                            onclick="WdatePicker({isShowClear:false,readOnly:true,isShowWeek:true,dateFmt:'yyyy-MM-dd HH:mm',maxDate:'${nowTime?string("yyyy-MM-dd HH:mm")}', minDate:'${order.creationTime?string("yyyy-MM-dd HH:mm")}'})" />
            </td>
        </tr>
        <tr>
            <th style="width:100px;"><span class="must">*</span>收款方式：</th>
            <td>
                <input type="text" class="input-txt" placeholder="输入收款方式" id="chargeType" value="" name="cashpay.chargeType" notNull="true" maxLength="20">
            </td>
        </tr>
        <tr>
            <th style="width:100px;">备注：</th>
            <td>
                <textarea class="text-area fn-left t-200" id="remark" name="cashpay.remark" maxLength="400"></textarea>
            </td>
        </tr>
    </table>
    </form>
</div>
<div style="background:#fff;padding-left:40px;height:30px" id="submit_tips"></div>
<p class="dd">
    <a class="abtn abtn-blue submit" href="javascript:;" id="cashpayBut"><span>确定</span></a>
    <a class="abtn abtn-green reset ml-5" href="javascript:;"><span>取消</span></a>
</p>
<script>
$(function(){
    PayDeal.cashpay();
});
</script>
<#else>
<p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>订单信息</span></p>
<div class="wrap">
    <div class="record-wrap mt-10">
        <div class="public-nodata">查询订单的支付方式是支付宝支付，无需登记线下支付信息，请重试！</div>
    </div>
</div>
</#if>