<#include "/pages/common/commonmacro.ftl" />
<ul class="org-search-list fn-clear" style="height:209px;">
    <#list list?if_exists as item>
    <li><input type="radio" class="chk" name="selectCollegeDiv_radio" value="${item.id}" <#if collegeId?default(0) == item.id>checked</#if> dataValue="${item.name!}">${item.name!}</li>
    </#list>
</ul>
<div class="public-page">
    <@commonPage1 contentDiv="selectCollegeDiv_content"/>
</div>