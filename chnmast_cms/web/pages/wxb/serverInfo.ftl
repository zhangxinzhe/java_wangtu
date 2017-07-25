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
			<div id="content" class="container_25">
				<p class="crumbs">当前位置：运维管理&nbsp;&gt;&nbsp;<span>查看服务器</span></p>
                <!--隐藏的id-->
                <input type="hidden" id="serverId" value="${server.id!}"/>
				<ul class="public-tab fn-clear mt-10">
				    <span class="tt">${server.name!}</span>
		        </ul>
		        <table  class="form-table mt-10">
    		        <tr>
    		            <th style="width:20%;">服务器名称：</th>
                        <td>${server.name!}</td>  
    		        </tr>
    		        <tr>
    		            <th>主登录服务器IP/域名：</th>
    		            <td>${(server.masterLoginUrl?html)!}</td>
    		        </tr>
    		        <tr>
    		            <th>备登录服务器IP/域名：</th>
    		            <td>${(server.slaveLoginUrl?html)!}</td>
    		        </tr>
    		        <tr>
                        <th>登录转接服务器IP/域名：</th>
                        <td>${(server.transferLoginUrl?html)!}</td>
                    </tr>
    		        <tr>
    		            <th>服务器端口：</th>
    		            <td>${server.loginPort!}</td>
    		        </tr>
    		        <tr>
                        <th>监控端口：</th>
                        <td>${server.monitorPort!}</td>
                    </tr>
                    <tr>
                        <th>命令端口：</th>
                        <td>${server.commandPort!}</td>
                    </tr>
                    <tr>
                        <th>加密狗人数：</th>
                        <td>${server.maxCapacity!}</td>
                    </tr>
                    <tr>
                        <th>状态：</th>
                        <td>${server.status.nameValue!}</td>
                    </tr>
                    <tr>
                        <th>加密狗名称：</th>
                        <td>${server.dogName!}</td>
                    </tr>
                    <tr>
                        <th>加密狗序列号：</th>
                        <td>${server.dogNo!}</td>
                    </tr>
                    <tr>
                        <th>代理服务器数量：</th>
                        <td>${server.proxyNum!}</td>
                    </tr>
                    <tr>
                        <th>加密是否永不过期：</th>
                        <td><#if dogNoExpire?exists>${dogNoExpire.getNameValue()!}</#if></td>
                    </tr>
                    <tr>
                        <th>加密狗到期时间：</th>
                        <td><#if server.dogEndTime?exists>${server.dogEndTime?string('yyyy-MM-dd')}</#if></td>
                    </tr>
                    <tr>
                        <th>加密狗同步时间：</th>
                        <td><#if server.lastSynDate?exists>${server.lastSynDate?string('yyyy-MM-dd')}</#if></td>
                    </tr>
                    <tr>
                        <th>机房带宽：</th>
                        <td>${server.bandWidth!} &nbsp;M</td>
                    </tr>
                    <tr>
                        <th valign="top" class="pt-10">备注：</th>
                        <td valign="top" class="pt-10">${(server.remark?html)!}
                        </td>
                    </tr>
                    <tr>
                        <th>&nbsp;</th>
                        <td>
                        <#if updateServerModule>
                        <a hidefocus="true" href="javascript:;" class="abtn abtn-blue" id="updateBtn" >修改</a>
                        </#if>   
                        <#if updateStatusModule>
                        <#if server.status==serverStartType>
                          <a id="startOrStopServer" state="stop" class="abtn abtn-blue ml-5" href="javascript:;">停用</a>
                        <#else>
                          <a id="startOrStopServer" state="start" class="abtn abtn-blue ml-5" href="javascript:;">启用</a>
                        </#if>
                    </#if>         
                        <a hidefocus="true" href="javascript:;" id="backBtn" class="abtn abtn-green ml-5">返回</a>
                        </td>
                    </td>
		        </table>
			</div>
		</div>
<@footer />
</body>
<#include "/pages/jsinclude/wxb/serverInfo.ftl">
<script>
$(function(){
    serverInfo.init();
});
</script>
</html>