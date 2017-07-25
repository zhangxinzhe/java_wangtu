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
        <p class="crumbs">当前位置：运维管理&nbsp;&gt;&nbsp;<span><#if server? exists && server.id!=0>编辑<#else>新建</#if>服务器</span></p>
        
        <form action="${DOMAIN_CMS}/wxb/<#if server? exists && server.id!=0>editServer.htm<#else>addServer.htm</#if>" id="serverForm" method="post">
            <input name="server.ftpUrl" type="hidden"  value="127.0.0.1" />
            <input name="server.ftpPort" type="hidden" value="21" />
            <input name="server.ftpAccount" type="hidden" value="username"/>
            <input name="server.ftpPassword" type="hidden" value="password"/>
            <input name="server.id" id="server_id" type="hidden" value="<#if server? exists && server.id!=0>${server.id}</#if>"/>
            <input type="hidden" value="password"/>
            <table class="form-table validateForm mt-10">
                <tr>
                    <th style="width:20%"><span class="must">*</span>服务器名称：</th>
                    <td>
                        <input name="server.name" id="serverName" notNull="true" class="input-txt" type="text" maxlength="50"<#if server? exists> value="${server.name!}"</#if>/>
                        <span class="onTips">建议与加密狗的名称一样,可输入中文、数字、字母、下划线</span>
                    </td>
                </tr>
                <tr>
                    <th valign="top" class="pt-5"><span class="must">*</span>主登录服务器IP/域名：</th>
                    <td valign="top" class="pt-5">
                        <textarea class="text-area fn-left t-350" id="masterLoginUrl" notNull="true" name="server.masterLoginUrl" maxlength="150"><#if server? exists>${(server.masterLoginUrl?html)!}</#if></textarea>
                        <span class="onTips fn-left">请填写主登录服务器的IP或域名<br/>各个服务器地址使用英文字符“;”隔开</span>
                    </td>
                </tr>
                <tr>
                    <th valign="top" class="pt-5">备登录服务器IP/域名：</th>
                    <td valign="top" class="pt-5">
                        <textarea class="text-area fn-left t-350" id="slaveLoginUrl" name="server.slaveLoginUrl" maxlength="150"><#if server? exists>${(server.slaveLoginUrl?html)!}</#if></textarea>
                        <span class="onTips fn-left">请填写备登录服务器的IP或域名<br/>各个服务器地址使用英文字符“;”隔开</span>
                    </td>
                </tr>
                <tr>
                    <th valign="top" class="pt-5">登录转接服务器IP/域名：</th>
                    <td valign="top" class="pt-5">
                        <textarea class="text-area fn-left t-350" id="transferLoginUrl" name="server.transferLoginUrl" maxlength="150"><#if server? exists>${(server.transferLoginUrl?html)!}</#if></textarea>
                        <span class="onTips fn-left">请填写登录转接服务器IP/域名<br/>各个服务器地址使用英文字符“;”隔开</span>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>登录服务器端口：</th>
                    <td>
                        <input name="server.loginPort" class="input-txt" type="text" maxlength="5" notNull="true" dataType="integer" value="<#if server? exists>${server.loginPort!}<#else>${appsetting.getParam("server.default_login_port")}</#if>"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>监控端口：</th>
                    <td>
                        <input name="server.monitorPort" class="input-txt" type="text" maxlength="5" notNull="true" dataType="integer" value="<#if server? exists>${server.monitorPort!}<#else>${appsetting.getParam("server.defautl_monitor_port")}</#if>"/>
                    <td>
                </tr>
                <tr>
                    <th><span class="must">*</span>命令端口：</th>
                    <td>
                        <input name="server.commandPort" class="input-txt" type="text" maxlength="5" notNull="true" dataType="integer" value="<#if server? exists>${server.commandPort!}<#else>${appsetting.getParam("server.defautl_command_port")}</#if>"/>
                    </td>
                </tr>
                <tr>
                    <th>加密狗名称：</th>
                    <td>
                        <input id="dogName" class="input-txt input-txt-disabled" readonly class="input-txt" name="server.dogName" value="<#if server? exists>${(server.dogName?html)!}</#if>" />
                    </td>
                </tr>
                <tr>
                    <th valign="top" class="pt-5">加密狗序列号：</th>
                    <td valign="top" class="pt-5">
                        <textarea id="dogNo" class="text-area input-txt-disabled fn-left t-350" style="height:100px" readonly class="input-txt input-txt-disabled" name="server.dogNo"><#if server? exists>${(server.dogNo?html)!}</#if></textarea>
                    </td>
                </tr>
                <tr>
                    <th>加密狗人数：</th>
                    <td>
                        <input id="maxCapacity" type="text" class="input-txt input-txt-disabled" readonly name="server.maxCapacity" <#if server?exists >value="${server.maxCapacity!}"<#else>value="0"</#if> />
                    </td>
                </tr>
                <tr>
                    <th>代理服务器数量：</th>
                    <td>
                        <input id="proxyNum" type="text" class="input-txt input-txt-disabled" readonly name="server.proxyNum" <#if server? exists >value="${server.proxyNum!}"<#else>value="0"</#if> />
                    </td>
                </tr>
                <tr>
                    <th>机房带宽：</th>
                    <td>
                        <input name="server.bandWidth" class="input-txt" type="text" <#if server? exists >value="${server.bandWidth!}"<#else>value="0"</#if> />
                        <span class="onTips">单位为M，整数类型</span>
                    </td>
                </tr>
                <tr>
                    <th>加密码是否永不过期：</th>
                    <td>
                        <input name="server.dogNoExpire" id="dogNoExpire" type="hidden" value="<#if server?? && server.dogNoExpire??>${server.dogNoExpire!}</#if>"/>
                        <select id="noexpire" style="width:90px" disabled class="input-txt input-txt-disabled">
                            <#if dogNoExpireTypes? exists>
                                <#list dogNoExpireTypes as noExpire>
                                    <option value="${noExpire}" <#if dogNoExpire?exists && (dogNoExpire.value)?default(0) == noExpire.value> selected="selected"</#if> >${noExpire.nameValue}</option>
                                </#list>
                            </#if>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>加密狗到期时间：</th>
                    <td>
                        <input type="text" class="input-txt input-txt-disabled" readonly name="server.dogEndTime" id="dogEndTime" <#if server?exists && server.dogEndTime?exists>value="${server.dogEndTime?string('yyyy-MM-dd')}"</#if> />
                    </td>
                </tr>
                <tr>
                    <th>加密狗同步时间：</th>
                    <td>
                        <input type="text" id="lastSynDate" class="input-txt input-txt-disabled" readonly <#if server?exists && server.lastSynDate?exists>value="${server.lastSynDate?string('yyyy-MM-dd')}"</#if> />
                    </td>
                </tr>
                <tr>
                    <th valign="top" class="pt-5">备注：</th>
                    <td valign="top" class="pt-5">
                        <textarea name="server.remark" id="remark" class="text-area fn-left t-350"><#if server? exists>${(server.remark?html)!}</#if></textarea>
                    </td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td>
                        <a hidefocus="true" href="javascript:;" id="saveButton" class="abtn abtn-blue">保存</a>  
                        <#if server?exists && server.id != 0>
                        <a hidefocus="true" href="javascript:;" id="getDogInfo" class="abtn abtn-blue ml-5">获取信息</a>
                        </#if>    
                        <a hidefocus="true" href="javascript:;" id="backBtn" class="abtn abtn-green ml-5">返回</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<@footer />
</body>
<#include "/pages/jsinclude/wxb/addServerjs.ftl"/>
<script>
    $(function(){
        addServer.init();
    });
</script>
</html>