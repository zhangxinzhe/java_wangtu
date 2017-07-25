<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-订单优惠</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：业务管理&nbsp;&gt;&nbsp;<span>支付处理</span></p>
        <form id="orderSearchForm" action="#" method="post">
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
                    <th>订单金额</th>
                    <th>下单时间</th>
                    <th>状态</th>
                    <th>支付方式</th>
                    <th>操作</th>
                </tr>
                <#list list?if_exists as order>
                <tr>
                    <td>&nbsp;</td>
                    <td>${order.realname!}</td>
                    <td>${order.username!}</td>
                    <td>${order.tradeNo!}</td>
                    <td>${order.payAmount?string("0.00")}</td>
                    <td>${(order.creationTime?string("yyyy-MM-dd HH:mm"))!}</td>
                    <td>${(order.orderStatus.nameValue)!}</td>
                    <td>${(order.payType.nameValue)!}</td>
                    <td>
                        <a href="javascript:;" class="viewAnnul" orderId="${order.id!}">查看优惠</a>
                        <#if !order.isHaveAnnul>
                        <a href="javascript:;" class="addAnnul" orderId="${order.id!}">添加优惠</a>
                        <#else>
                        <a href="javascript:;" class="c-red cancelAnnul" orderId="${order.id!}" tradeNo="${order.tradeNo!}">取消优惠</a>
                        </#if>
                    </td>
                </tr>
                </#list>
            </table>
            </@doublePage2>
        </div>
    </div>
</div>
<#--查看优惠列表-->
<div class="popUp-layer layer-500" style="display:none;" id="viewAnnulDiv"></div>
<@footer />
<#include "/pages/jsinclude/account/orderAnnul/orderAnnulJs.ftl" />
<script>
    $(function(){
        orderAnnul.init();
    });
</script>
</body>
</html>