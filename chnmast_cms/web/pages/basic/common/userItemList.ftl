<#-- 用于commonmacro-userSelectDiv -->
<#include "/pages/common/commonmacro.ftl" />
<ul class="org-search-list fn-clear" style="height:209px;">
    <#list userList?if_exists as item>
    <li><input type="radio" class="chk" name="userSelectDiv_radio" value="${item.id}" dataValue="${item.userName!}">${item.realName!}(${item.userName!})</li>
    </#list>
</ul>
<div class="public-page">
    <@commonPage1 contentDiv="userSelectDiv_userItemDiv"/>
</div>