<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-帮助中心管理</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <input type="hidden" id="catalogLevel" name="catalogLevel" value="${catalogLevel!}"/>
    <input type="hidden" id="operType" name="operType" value="${operType!}"/>
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;>&nbsp;<span>帮助中心管理</span></p>
        <ul class="public-tab fn-clear mt-10">
            <li id="catalog_tab" <#if operType=="catalog" >class="current"</#if>>帮助分类管理</li>
            <li id="theme_tab" <#if operType=="theme" >class="current"</#if>>帮助内容管理</li>
        </ul>
        <ul class="public-sub-tab">
            <li <#if catalogLevel=="first" >class="current"</#if> ><a href="${DOMAIN_CMS}/homepage/help/helpManage.htm?catalogLevel=first">一级分类管理</a></li>
            <li <#if catalogLevel=="seconde" >class="current"</#if> ><a href="${DOMAIN_CMS}/homepage/help/helpManage.htm?catalogLevel=seconde">二级分类管理</a></li>
        </ul>
        <p class="pt-15 pb-5 pl-10">
            <a hidefocus="true" href="javascript:;" class="abtn abtn-green catalog_add_btn"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
            <!--<a hidefocus="true" href="#" class="abtn abtn-blue"><img src="${DOMAIN_CMS}/images/icon/add2.png">复制新增</a>-->
        </p>
        
        <div class="record-wrap mt-10">
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="50"></th>
                    <th>标题</th>
                    <th width="10%">是否显示</th>
                    <th width="10%">顺序号</th>
                    <th width="10%">操作</th>
                </tr>
                <#list catalogs?if_exists as s>
                <tr>
                    <td class="t-center"><input class="check_item" name="catalogIds" type="checkbox" value="${s.id!}"/></td>
                    <td>${s.catalogName!}</td>
                    <td>${s.isShow.nameValue!}</td>
                    <td>${s.displayOrder!}</td>
                    <td>
                        <a href="javascript:;" class="catalog_toedit" val="${s.id!}" title="修改">修改</a>
                    </td>
                </tr>
                </#list>  
                <tr>
                    <td class="t-center"><input id="check_all" type="checkbox"></td>
                    <td colspan="5">
                        <a href="javascript:;" id="delete_catalog_btn" class="abtn abtn-blue">删除</a>
                    </td>
                </tr>
                
            </table>
            </@doublePage2>
        </div>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/helpManageJs.ftl" />
<script>
$(function(){
    window.HelpManage.init();
});

</script>
<form method="psot" id="delete_form">
</form>
<div class="popUp-layer" style="display:none;width:450px" id="editHelpCatalog"></div>
</body>
</html>