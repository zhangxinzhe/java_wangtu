<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl" />
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME!}-首页</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：<a href="/index.htm">首页</a></p>
        <#--
        <div class="record-wrap mt-20" style="height:200px;">
            <div class="public-nodata">欢迎使用&nbsp;${SITE_CMS_SITE_NAME!}(v${systemVersionMaxNo!})</div>
        </div>-->
        
        <div class="record-wrap mt-20 pl-20 pt-20 pb-20 pr-20">欢迎使用&nbsp;${SITE_CMS_SITE_NAME!}(v${systemVersionMaxNo!})</div>
        
        <div class="record-wrap mt-20 pl-20 pt-20 pb-20 pr-20">
            <span class="c-orange">待处理事项</span>
            <#if nameList?has_content>
            <div class="record-wrap mt-10">
                <table class="public-table price-table">
                <#list nameList as name>
                    <tr <#if name_index == 0>class="first"</#if> <#if name_index+1 == nameList.size()>class="last"</#if>>
                        <td width="80" class="t-center">${name_index+1}</td>
                        <td width="250">${name}</td>
                        <td width="300">共有&nbsp;<a href="${urllist.get(name_index)}" class="c-red">${numList.get(name_index)}</a>&nbsp;条数据待处理</td>
                        <td width="100"><a href="${urllist.get(name_index)}">去处理</a></td>
                        <td>&nbsp;</td>
                    </tr>
                </#list>
                </table>            
            </div>
            </#if>
        </div>
        
        <#if appsetting.getParam("system_web_monitor")?default('0')=='1'>
        <div class="record-wrap mt-20 pl-20 pt-20 pb-20 pr-20">
             <a href="monitor.htm" target="_blank">web管理</a>
        </div>
        </#if>
    </div>
</div>

<@footer />
</body>
</html>