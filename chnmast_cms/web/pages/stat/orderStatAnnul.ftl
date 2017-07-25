<p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>订单优惠记录</span></p>
<div class="wrap">
    <div class="record-wrap" <#if list?has_content>style="border-top:0;border-bottom:0;"</#if>>
    <#if list?has_content>
        <table class="public-table">
            <tr>
                <th class="t-center" style="width:50px;">序号</th>
                <th>时间</th>
                <th>优惠类型</th>
                <th>金额</th>
                <th>操作人</th>
            </tr>
            <#list list as annul>
            <tr>
                <td class="t-center">${annul_index+1}</td>
                <td>${annul.opeTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                <td>${annul.annulType.nameValue}</td>
                <td>${annul.annulAmount?string('0.00')}</td>
                <td>${annul.opeRealName!}</td>
            </tr>
            </#list>
        </table>
    <#else>
        <div class="public-nodata" style="margin:50px 0 50px;">此订单暂无优惠记录...</div>
    </#if>
    </div>
</div>
<p class="dd">
    <a class="abtn abtn-green ml-5 close" href="javascript:;"><span>关闭</span></a>
</p>  
