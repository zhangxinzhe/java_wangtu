<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-分类管理</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<span>分类管理</span></p>
        <form class="studentManage" id="catalogForm" action="/basic/addCatalog.htm" method="post" enctype="multipart/form-data">
        <table class="form-table validateForm mt-10">
            <tr>
                <td width="200">
                   <input id="cname" name="cname" class="input-txt"/>
                </td>
                <td>
                    <a href="javascript:;" class="abtn abtn-green ml-10" id="newButton"><img src="../images/icon/add2.png">新增</a>
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <table class="public-table">
                <tr>
                    <th width="50" class="t-center">序号</th>
                    <th>分类名称</th>
                    <th>操作</th>
                    
                </tr>
                <#list catalogList?if_exists as catalog>
                <tr>
                    <td class="t-center">${catalog_index +1 }</td>
                    <td width="600" id="cname_${catalog.id!}" title="${catalog.cname!}" val="${catalog.cname!}">${catalog.cname!}</td>
                    
                    <td>
                        <a href="javascript:;" class="catalogEdit" title="修改" val="${catalog.id!}">修改</a>
                        <a href="javascript:;" class="saveCatalog" style="display:none" id="save_btn_${catalog.id!}" title="保存" val="${catalog.id!}">保存</a>
                        <a href="javascript:;" class="catalogDelete" title="删除" val="${catalog.id!}">删除</a>
                    </td>
                </tr>
                </#list>
                
            </table>
        </div>
    </div>
</div>
<@footer />
<script type="text/javascript" src="${DOMAIN_CMS}/js/basic/catalog/catalogManageJs.js?${appsetting.getVersionId()}"></script>
<script>
$(function(){
    CatologManage.init();
});

</script>
</body>
</html>