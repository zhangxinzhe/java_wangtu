<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-服务器维护</title>

</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：运维管理&nbsp;&gt;&nbsp;<span>服务器维护</span></p>
        <!--搜索显示内容start-->
        <form method="post" id="searchForm" action="${DOMAIN_CMS}/wxb/serverManage.htm" >
        <table class="form-table validateForm mt-10">
            <tr>
                <th>服务器名称:</th>
                <td>
                    <input class="input-txt" name="server.name" value="<#if server? exists  && server.name? exists >${server.name!}</#if>" />
                </td>
            </tr>
            <tr>
                <th>状态:</th>
                <td>
                    <select name="server.status" class="input-sel w-200">
                        <option value="">全部</option>
                        <#list serverStatusTypes as serverType>
                        <option value="${serverType!}" <#if server? exists  && server.status? exists && server.status.equals(serverType)>selected="selected"</#if>>${serverType.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td colspan="3">
                    <a href="javascript:;" id="search" class="abtn abtn-blue">搜索</a>
                    <#if addServerModule>
                    <a href="javascript:;" class="abtn abtn-green ml-5" id="addButton" ><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                    </#if>
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
                    <th class="t-center">序号</th>
                    <th>服务器名称</th>
                    <th class="t-center">服务器IP/域名</th>
                    <th class="t-center">服务器端口</th>  
                    <th class="t-center">监控端口</th>
                    <th class="t-center">命令端口</th>
                    <th class="t-center">状态</th>
                    <th class="t-center">操作</th>
                </tr>
                <#if servers? exists>
                <#list servers as server>
                <tr>
                    <td class="t-center">${server_index+1!}</td>
                    <td>${server.name!}</td>
                    <td class="t-center">${server.masterLoginUrl.replace(";", "<br>")!}</td>
                    <td class="t-center">${server.loginPort!}</td>
                    <td class="t-center">${server.monitorPort!}</td>
                    <td class="t-center">${server.commandPort!}</td>
                    <td class="t-center" <#if server.status == serverStatusTypeStop>style="color:red;"</#if>>${server.status.nameValue!}</td>
                    <td class="t-center lastTd" >
                        <#if getServerModule><a href="javascript:;" class="server_info" val="${server.id}">查看</a></#if>
                        <#if deleteServerModule><a href="javascript:;" class="del_server" val="${server.id}" >删除</a></#if>
                    </td>
                 </tr>
                 </#list>
                 </#if>
            </table>
        </@doublePage2>
        
      </div>
        <!--结果显示内容end-->
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/wxb/serverManagejs.ftl">
<script>
$(function(){
    serverManage.init();
});
</script>
</body>
</html>