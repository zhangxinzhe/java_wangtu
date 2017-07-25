<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-收支统计</title>
<script type="text/javascript" src="${DOMAIN_CMS}/js/component/date/WdatePicker.js?${appsetting.getVersionId()}"></script>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：统计查询&nbsp;&gt;&nbsp;<span>收支统计</span></p>
        <form class="orderFundsForm" id="orderFundsForm" action="${DOMAIN_CMS}/stat/fundsStat.htm" method="post">
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>时间段：</th>
                <td> 
                    <input type="text" class="input-txt fn-left t-80" notNull="true" id="qpBeginTime" name="qpBeginTime" value="${(qpBeginTime?string('yyyy-MM-dd'))!}" onclick="$('#btwp').click();" readonly/>
                    <a hidefocus="true" href="javascript:;" id="btwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'qpBeginTime',isShowClear:true,readOnly:true,isShowWeek:true,maxDate:'#F{$dp.$D(\'qpEndTime\')}'})"></a>                            
                    <span class="fn-left mt-5 ml-10">至</span>
                    <input type="text" class="input-txt fn-left ml-10 t-80" notNull="true" id="qpEndTime" name="qpEndTime" value="${(qpEndTime?string('yyyy-MM-dd'))!}" onclick="$('#etwp').click();" readonly/>
                    <a hidefocus="true" href="javascript:;" id="etwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'qpEndTime',isShowClear:true,readOnly:true,isShowWeek:true,minDate:'#F{$dp.$D(\'qpBeginTime\')}'})"></a>
                </td>
            </tr> 
            <tr>
                <th>商品类型：</th>
                <td> 
                    <select id="wareType" class="input-sel w-200" name="orderFunds.wareType">
                        <option value="">全部</option>
                        <#list courseContentTypes?if_exists as sType>
                          <option value="${sType}" <#if orderFunds?exists && orderFunds.wareType?exists && orderFunds.wareType==sType>selected</#if> >${sType.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
             <tr>
                <th>来源：</th>
                <td> 
                    <select id="orderType" class="input-sel w-200" name="orderType">
                        <option value="">全部</option>
                        <#list orderTypes?if_exists as sType>
                          <option value="${sType}" <#if orderType?exists &&  orderType==sType>selected</#if> >${sType.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>交易类型：</th>
                <td> 
                    <select id="detailType" class="input-sel w-200" name="orderFunds.detailType">
                        <#list recordDetailTypes?if_exists as sType>
                          <option value="${sType}" <#if orderFunds?exists && orderFunds.detailType?exists && orderFunds.detailType==sType>selected</#if> >${sType.nameValue!}</option>
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
            <div class="filter">
                <span class="fn-txt pl-50 c-red f18" id="totalInfo">合计金额：${totalFunds!}元</span>
            </div>
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="40" class="t-center">序号</th>
                    <th width="200">订单编号</th>
                    <th>商品类型</th>
                    <th width="200">商品名称</th>
                    <th width="80">下单时间</th>
                    <th>用户</th>
                    <th>邮箱</th>
                    <th>金额</th>
                    <th width="50" class="t-center">类型</th>
                    <th>交易详情</th>
                </tr>
                <#list list?if_exists as fund>
                <tr>
                    <td class="t-center">${fund_index+1}</td>
                    <td>${(fund.tradeNo)!''}</td>
                    <td>${(fund.wareType.nameValue)!''}</td>
                    <td><span title="${(fund.wareName)!''}"><@cutOff cutStr="${(fund.wareName)!''}" cutLen="30" /></span></td>
                    <td>${(fund.orderCreateTime?string("yyyy-MM-dd"))!}<br/>${(fund.orderCreateTime?string("HH:mm:ss"))!}</td>
                    <td>${(fund.realName)!''}</td>
                    <td>${(fund.email)!''}</td>
                    <td>${(fund.changeFunds)!'0'}</td>
                    <td class="t-center">${fund.detailType.nameValue}</td>
                    <td><span title="${(fund.remark)!''}"><@cutOff cutStr="${(fund.remark)!''}" cutLen="20" /></span></td>
                </tr>
                </#list>
            </table>
            </@doublePage2>
        </div>
    </div>
</div>
<@footer />
<script>
    $(function(){
        $('#searchButton').click(function(){
            var beginTime = $("#qpBeginTime").val();
            var endTime = $("#qpEndTime").val();
            if(!Validator.isNotBlank(beginTime) || !Validator.isNotBlank(endTime)){
                Tips.showAlertWin({
                    info : "时间段开始和结束都不能为空！"
                });
                return;
            }
            $('#orderFundsForm').submit();
        });
    });
</script>
</body>
</html>