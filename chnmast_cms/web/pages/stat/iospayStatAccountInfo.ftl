<#include "/pages/common/commonmacro.ftl" />
<p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>交易详情记录</span></p>
<div class="wrap">
    <div class="record-wrap" <#if list?has_content>style="border-top:0;border-bottom:0;"</#if>>
    <#if list?has_content>
        <table class="public-table">
            <tr>
                <th width="40" class="t-center">序号</th>
                <th width="70" class="t-center">交易时间</th>
                <th width="60" class="t-center">交易类型</th>
                <th width="60" class="t-center">交易金额</th>
                <th width="70" class="t-center">账户余额</th>
                <th width="300">详情</th>
            </tr>
            <#list list?if_exists as record>
            <tr>
                <td class="t-center">${record_index+1}</td>
                <td class="t-center">${record.recordDate?string("yyyy-MM-dd")}<br/>${record.recordDate?string("HH:mm:ss")}</td>
                <td class="t-center">${record.recordType.nameValue}</td>
                <td class="t-center"><span <#if record.detailType.value==0> class="c-red"<#elseif record.detailType.value==1> class="c-green"</#if>>${record.changeFunds?string("0.00")}</span></td>
                <td class="t-center">${record.remainFunds?string("0.00")}</td>
                <td><span title="${record.remark!}"><@cutOff cutStr="${record.remark!}" cutLen="80"/></span></td>
            </tr>
            </#list>
        </table>
    <#else>
        <div class="public-nodata" style="margin:50px 0 50px;">此账户还没有任何交易记录...</div>
    </#if>
    </div>
    <div class="public-page">
        <@commonPage1 contentDiv="accountInfoDiv"/>
    </div>
</div>
<p class="dd">
    <a class="abtn abtn-green ml-5 close" href="javascript:;"><span>关闭</span></a>
</p>

<script>
     $(function(){
        Sender.openDiv({
            "div" : "#accountInfoDiv",
            "closeObject" : "#accountInfoDiv a.close"
        });
    });
</script>
