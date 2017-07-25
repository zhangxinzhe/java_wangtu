<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<title>${SITE_CMS_SITE_NAME}-系统参数</title>
<#include "/pages/jsinclude/commonjs.ftl" />
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
	
    <div id="content">
    	<p class="crumbs">当前位置：系统管理&nbsp;&gt;&nbsp;<span>系统参数</span></p>
    	<!--搜索显示内容start-->
        <form method="post" id="searchForm" action="${DOMAIN_CMS}/system/systemConfigManage.htm" >
        <table class="form-table validateForm mt-10">
            <tr>
                <th>参数名称：</th>
                <td>
                    <input type="text"  name="configName" value="${configName!}" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td colspan="3">
                    <a href="javascript:;" id="search" class="abtn abtn-blue">搜索</a>
                </td>
            </tr>
        </table>
        </form>
        <!--搜索显示内容end-->
        <!--结果显示内容start-->
      <div class="record-wrap mt-10">
             
            <table class="public-table">
                <tr>
                    <th width="50" class="t-center"></th>
                    <th width="6%">序号</th>
                    <th width="25%">参数名称</th>
                    <th width="25%">参数值</th>
                    <th width="34%">描述</th>
                    <#if canEditConfig>
                    <th width="6%">操作</th>
                    </#if>           
                </tr> 
                <#assign index = 0>
                <#list systemConfigs as systemConfig>
                <#if systemConfig.canView>
                <#assign index = index + 1>
                <tr>
                    <td class="t-center"></td>
                    <td>${index}</td>
                    <td>${systemConfig.name! }</td>
                    <td style="word-break:break-all;">${systemConfig.value?html }</td>
                    <td>${systemConfig.description! }</td>
                    <td>
                    <#if canEditConfig && systemConfig.canEdit>
                    <a href="javascript:void(0);" onclick="modify('${systemConfig.code!}')">修改</a>
                    </#if>
                    </td>
                </tr>
                </#if>
            </#list>
            </table>
      </div>
        <!--结果显示内容end-->
        <!--修改页面所需数据start-->
        <form id="modifyForm"  method="post" action="${DOMAIN_CMS}/system/editSystemConfig.htm">
                <input name="configName" type="hidden" value="${configName!}">
                <input name="code" type="hidden" id="code" value="">
        </form>
        <!--修改页面所需数据end-->
    </div>
</div>
<@footer />
<script>
    $('#search').bind('click',function(){
      $('#searchForm').submit();
    });
    function modify(code){
        $('#code').val(code);
        $('#modifyForm').submit();
    }
</script>
</body>
</html>