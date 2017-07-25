
<ul class="org-search-list fn-clear" style="height:200px;">
    <#list typeList?if_exists as ty >
    <li><input type="radio" class="chk w type_radio" name="type_radio" value="${ty.id!}" dataValue="${ty.title!}">${ty.title!}</li>
    </#list>
</ul>