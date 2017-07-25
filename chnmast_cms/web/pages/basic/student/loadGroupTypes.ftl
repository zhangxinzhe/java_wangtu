
<ul class="org-search-list fn-clear" style="height:200px;">
    <#list list?if_exists as type>
    <li><input type="radio" class="chk w type_radio" value="${type.id!}" dataValue="${type.title!}">${type.title!}</li>
    </#list>
</ul>