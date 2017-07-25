<#include "/pages/common/constant.ftl" />
<input type="hidden" id="catalogId" name="catalogId" value="${catalogId!}"/>
<p class="pb-5 pl-10">
    &nbsp;
    <a hidefocus="true" href="javascript:;" class="abtn abtn-green theme_add_btn"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
</p>
<#if themes?exists && themes.size() gt 0>
<div class="record-wrap mt-10 ml-10">
    <form id="theme_list_form" method="post">
    <table class="public-table">
        <tr>
            <th width="50"></th>
            <th>标题</th>
            <th width="15%">链接类型</th>
            <th width="10%">是否显示</th>
            <th width="10%">顺序号</th>
            <th width="10%">操作</th>
        </tr>
        <#list themes?if_exists as s>
        <tr>
            <td class="t-center"><input class="check_item" name="ids" type="checkbox" value="${s.id!}"/></td>
            <td>${s.themeName!}</td>
            <td>${s.linkType.nameValue!}</td>
            <td>${s.isShow.nameValue!}</td>
            <td>${s.displayOrder!}</td>
            <td>
                <a href="javascript:;" class="theme_toedit" val="${s.id!}" title="修改">修改</a>
            </td>
        </tr>
        </#list>  
        <tr>
            <td class="t-center"><input id="check_all_theme" type="checkbox"></td>
            <td colspan="5">
                <a href="javascript:;" id="delete_theme_btn" class="abtn abtn-blue">删除</a>
            </td>
        </tr>
    </table>
    </form>
</div>
<#else>
<div class="public-nodata">暂时没有数据哦...</div>
</#if>
<script>
    $(function(){
        window.HelpThemeList.init();
    });
</script>