<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl" />
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME!}-保存出错</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div <#if topModules?exists>id="content"</#if> >
        <div class="public-404">内容包含不安全字符：<br/>
           <#if action.hasActionMessages()>
            <#list actionMessages as item>
                <#if item?exists>
                ${item?j_string?html}
                </#if>
            </#list>    
            </#if>              
        </div>
    </div>
    
</div>

<@footer />
</body>
</html>