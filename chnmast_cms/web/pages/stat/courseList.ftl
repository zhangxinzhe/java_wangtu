<#include "/pages/common/commonmacro.ftl" />
<ul class="org-search-list fn-clear" style="height:130px;">
    <#list courseList?if_exists as item>
    <li><input type="radio" class="chk" name="cou_radio" value="${item.id}" dataValue="${item.courseName!}">${item.courseName!}</li>
    </#list>
</ul>
<div class="public-page">
    <@commonPage1 contentDiv="selectCouDiv_listDiv"/>
</div>