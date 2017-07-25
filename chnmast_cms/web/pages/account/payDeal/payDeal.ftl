<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-订单处理</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：业务管理&nbsp;&gt;&nbsp;<span>支付处理</span></p>
        <form class="studentManage" id="payForm" action="#" method="post">
        <table class="form-table validateForm mt-10">
            <tr>
                <th>用户名：</th>
                <td> 
                    <input type="text" class="input-txt" id="username" name="order.username" placeholder="输入用户名" value="${order.username!}"/>
                </td>
            </tr>
            <tr>
                <th>姓名：</th>
                <td> 
                    <input type="text" class="input-txt" id="realname" name="order.realname" placeholder="输入姓名" value="${order.realname!}"/>
                </td>
            </tr>
            <tr>
                <th>订单号：</th>
                <td> 
                    <input type="text" class="input-txt" id="tradeNo" name="order.tradeNo" placeholder="输入订单号" value="${order.tradeNo!}" maxLength="32"/>
                </td>
            </tr>
            <tr>
                <th>支付方式：</th>
                <td> 
                    <select class="input-sel w-200" id="payType" name="order.payType">
                        <option value="">请选择</option>
                        <#list payTypes?if_exists as item>
                        <option value="${item!}" <#if order.payType! == item!>selected</#if>>${item.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="20">&nbsp;</th>
                    <th>学员姓名</th>
                    <th>用户名</th>
                    <th>订单号</th>
                    <th>订单类型</th>
                    <th>支付方式</th>
                    <th>订单金额</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                <#list orders?if_exists as item>
                <tr>
                    <td width="20">&nbsp;</td>
                    <td>${item.realname!}</td>
                    <td>${item.username!}</td>
                    <td>${item.tradeNo!}</td>
                    <td>${(item.orderKind.nameValue)!}</td>
                    <td>${(item.payType.nameValue)!}</td>
                    <td>${item.payAmount?string("0.00")}</td>
                    <td>${(item.creationTime?string("yyyy-MM-dd HH:mm"))!}</td>
                    <td><a href="javascript:;" class="cancel" orderId="${item.id!}">退回</a>&nbsp;<a href="javascript:;" class="finish" payType=${item.payType!} orderId="${item.id!}">完成</a></td>
                </tr>
                </#list>
            </table>
            </@doublePage2>
        </div>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/account/payDeal/payDealJs.ftl" />
<script>
$(function(){
    PayDeal.init();
});

</script>
</body>
</html>
<div class="popUp-layer" style="display:none;width:450px" id="cashpayDiv"></div>