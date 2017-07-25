<#include "/pages/common/commonmacro.ftl" />
<ul class="org-search-list fn-clear" style="height:130px;width:440px">
    <#list courseTimeList?if_exists as item>
    <li>
        <input type="radio" class="chk" name="couTime_radio" value="${item.seq!}" id="${item.id!}" dataValue="${item.seq!}  ${item.beginTime?string('yyyy-MM-dd HH:mm')}--${item.endTime?string('HH:mm')}"/>
        ${item.seq!}   ${item.beginTime?string('yyyy-MM-dd HH:mm')}--${item.endTime?string('HH:mm')}
    </li>
    </#list>
</ul>
<div class="public-page">
    <@commonPage1 contentDiv="selectCouTimeDiv_listDiv"/>
</div>