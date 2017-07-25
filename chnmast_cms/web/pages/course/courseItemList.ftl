<#-- 用于commonmacro-courseSelectDiv -->
<#include "/pages/common/commonmacro.ftl" />
<ul class="org-search-list fn-clear" style="height:209px;">
    <#list courseList?if_exists as item>
    <li><input type="radio" class="chk" name="courseSelectDiv_radio" value="${item.id}" <#if selectCId?default(0) == item.id>checked</#if> dataValue="${item.courseName!}">${item.courseName!}</li>
    </#list>
</ul>
<div class="public-page">
    <@commonPage1 contentDiv="courseSelectDiv_courseItemDiv"/>
</div>