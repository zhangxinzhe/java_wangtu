<p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>添加优惠</span></p>
<div class="wrap">
    <form id="orderAnnulForm" method="post" action="">
        <input style="display:none" />
        <input type="hidden" name="orderAnnul.orderId" value="${order.id!}">
        <input type="hidden" name="order.tradeNo" value="${order.tradeNo!}">
        <table class="form-table validateForm">
            <tr>
                <th>订单号：</th>
                <td colspan="3">${order.tradeNo!}</td>
            </tr>
            <tr>
                <th style="width:100px;">下单时间：</th>
                <td>${order.creationTime?string("yyyy-MM-dd HH:mm")}</td>
                <th style="width:100px;">订单金额：</th>
                <td><span id="beforeAmount" dataValue="${order.payAmount!}">${order.payAmount?string('0.00')} 元</span></td>
            </tr>
            <tr>
                <th>姓名：</th>
                <td>${order.realname!}</td>
                <th>用户名：</th>
                <td>${order.username!}</td>
            </tr>
            <tr>
                <th><span class="must">*</span>优惠类型：</th>
                <td colspan="3">
                    <select class="input-sel w-165" id="annulType" name="orderAnnul.annulType">
                        <#list annulTypes?if_exists as annul>
                            <option value="${annul}">${annul.nameValue}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>优惠金额：</th>
                <td colspan="3">
                    <input type="text" class="input-txt t-165" placeholder="输入优惠金额" id="annulAmount" name="orderAnnul.annulAmount" maxLength="5">
                </td>
            </tr>
            <tr>
                <th>优惠后金额：</th>
                <td colspan="3"><span class="c-red" id="afterAmount"></span></td>
            </tr>
            <tr>
                <th><span class="must">*</span>优惠说明：</th>
                <td colspan="3">
                    <textarea class="text-area fn-left t-350" style="height:80px" id="remark" name="orderAnnul.remark" maxLength="400"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
<p class="dd">
    <a class="abtn abtn-blue submit" href="javascript:;" id="addAnnulSubmit"><span>确定</span></a>
    <a class="abtn abtn-green reset ml-5" href="javascript:;"><span>取消</span></a>
</p>

<script>
    $(function(){
        orderAnnul.addAnnulOperate();
    });
</script>