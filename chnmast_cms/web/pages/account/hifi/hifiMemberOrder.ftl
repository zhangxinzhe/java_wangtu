<p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span id="couDetailDiv_title">交易记录</span></p>
<div>        
    <div class="wrap">
        <div class="org-search">
            <div class="org-search-inner">
                <table class="public-table mt-10">
                    <#if list?has_content>
                        <tr>
                            <th class="t-center" style="width:50px;">序号</th>
                            <th>订单号</th>
                            <th>实付金额</th>
                            <th>下单时间</th>
                            <th>付款方式</th>
                            <th>交易状态</th>
                        </tr>
                        <#list list?if_exists as item>
                        <tr>
                            <td class="t-center">${item_index+1}</td>
                            <td>${item.tradeNo!}</td>
                            <td>${item.payAmount?string('0.00')!}</td>
                            <td>${item.creationTime?string("yyyy-MM-dd")}</td>
                            <td>${item.payType.nameValue!}</td>
                            <td>${item.statusType.nameValue!}</td>
                        </tr>
                        </#list>
                    <#else>
                    <div class="public-nodata">此用户无HIFI会员购买交易记录...</div>
                    </#if>
                </table>
            </div>
       </div>
    </div>
</div>
<p class="dd">
    <a class="abtn abtn-green ml-5 close" href="javascript:;"><span>关闭</span></a>
</p>  
