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
        <div class="help-container fn-clear">
            <div class="help-sidebar">
                <p class="tt">帮助分类列表</p>
                <div class="help-tree">
                    <#list catalogs? if_exists as c>
                    <dl>
                        <dt class="menu_catalog" val="${c.id!}" title="${c.catalogName!}"><@cutOff cutStr="${c.catalogName!}" cutLen="35"/></dt>
                        <#list c.childCatalog?if_exists as cc>
                        <dd class="menu_catalog" val="${cc.id!}" title="${cc.catalogName!}"><@cutOff cutStr="${cc.catalogName!}" cutLen="30"/></dd>
                        </#list>
                    </dl>
                    </#list>
                </div>
            </div>
            <div class="help-content">
                
            </div>
        </div>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/helpThemeManageJs.ftl" />
<script>
$(function(){
    window.HelpThemeManage.init();
});

</script>
<form method="psot" id="delete_form">
</form>
<div class="popUp-layer" style="display:none;width:450px" id="editHelpTheme"></div>
</body>
</html>