<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<script type="text/javascript" src="/js/component/date/WdatePicker.js?0"></script>
<title>${SITE_CMS_SITE_NAME}-系统日志</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
	
    <div id="content">
    	<p class="crumbs">当前位置：系统管理&nbsp;&gt;&nbsp;<span>系统日志</span></p>
    	
    	<!--搜索显示内容start-->
        <form method="post" id="searchForm" action="${DOMAIN_CMS}/system/systemLog.htm" >
        <table class="form-table validateForm mt-10">
            <tr>
                <th>日期：</th>
                <td>
            <input type="text" class="input-txt fn-left" readonly onkeydown="if(window.event.keyCode == 8){return false;}" onclick="$('#btwp').click();"
             style="width:80px;"  name="beginTime" id="beginTime"  <#if beginTime?exists>value="${beginTime?string("yyyy-MM-dd")}"</#if>>
             <a hidefocus="true" href="javascript:;" id="btwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'beginTime',isShowClear:true,readOnly:true,isShowWeek:true,maxDate:'#F{$dp.$D(\'endTime\')}'})"></a>
            <span class="fn-left mt-5 ml-10">至</span>
            <input type="text" class="input-txt fn-left ml-10" readonly onkeydown="if(window.event.keyCode == 8){return false;}" onclick="$('#etwp').click();"
             style="width:80px;" name="endTime" id="endTime" <#if endTime?exists>value="${endTime?string("yyyy-MM-dd")}"</#if>>
             <a hidefocus="true" href="javascript:;" id="etwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'endTime',isShowClear:true,readOnly:true,isShowWeek:true,minDate:'#F{$dp.$D(\'beginTime\')}'})"></a>
                </td>
            </tr>
            <tr>
                <th>用户名：</th>
                <td>
                    <input type="text"  name="username" <#if username?exists>value="${username!}"</#if> maxlength="20" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>处理人姓名：</th>
                <td>
                    <input type="text"  name="realName" <#if realName?exists>value="${realName!}"</#if>  maxlength="20" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>操作业务：</th>
                <td>
                    <input type="text"  name="operate" <#if operate?exists>value="${operate!}"</#if>  maxlength="20" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td colspan="3">
                <input name="f" type="hidden" value="false"/>
                    <a href="javascript:;" id="search" class="abtn abtn-blue">搜索</a>
                </td>
            </tr>
        </table>
        </form>
        <!--搜索显示内容end-->
        <!--结果显示内容start-->
      <div class="record-wrap mt-10">
        <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="50" class="t-center"></th>
                    <th width="43%">操作业务</th>
                    <th width="13%">处理人姓名</th>
                    <th width="13%">用户名</th>
                    <th width="15%">操作时间</th>
                    <th width="13%">操作人IP</th>
                    
                <#list systemLogs?if_exists as systemLog>
                <tr>
                    <td class="t-center"></td>
                    <td style="word-break:break-all;">${(systemLog.operate)!}</td>
                    <td>${(systemLog.realName)!}</td>
                    <td>${systemLog.name!}</td>
                    <td>${(systemLog.operateTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                    <td>${systemLog.operateIp!}</td>
                </tr>
                </#list>
            </table>
        </@doublePage2>
      </div>
        <!--结果显示内容end-->
    </div>
</div>
<@footer />
<script>
    $('#search').bind('click',function(){
      $('#searchForm').submit();
    });
</script>
</body>
</html>