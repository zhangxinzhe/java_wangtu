<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl" />
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME!}-无数据</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div <#if topModules?exists>id="content"</#if>>
        <div class="public-404">数据不存在！</div>
    </div>
</div>

<@footer />
</body>
</html>