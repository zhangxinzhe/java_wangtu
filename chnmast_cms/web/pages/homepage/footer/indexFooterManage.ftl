<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-公告、新闻</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;>&nbsp;<span>页脚链接</span></p>
        <form id="footerManage" action="${DOMAIN_CMS}/homepage/index/footerManage.htm" method="post">
        <table class="form-table validateForm mt-10">
            <tr>
                <th>标题：</th>
                <td> 
                    <input type="text" onkeydown="if(event.keyCode == 13){search();}" class="input-txt" id="footerName" name="footerName" placeholder="输入查询标题" value="${footerName!}"/>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                    <a href="javascript:;" class="abtn abtn-green ml-10" id="newButton"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <#if indexFooters?exists && indexFooters.size() gt 0 >
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th style="text-align:center;width:5%;">序号</th>
                    <th width="20%">标题</th>
                    <th width="15%">类型</th>
                    <th width="15%">是否显示</th>
                    <th width="15%">排序号</th>
                    <th>操作</th>
                </tr>
                <#list indexFooters?if_exists as footer>
                <tr>
                    <td style="text-align:center;">${footer_index+1}</td>
                    <td title="${footer.title!}"><@cutOff cutStr="${footer.title!}" cutLen="50"/></td>
                    <td>${footer.contentType.nameValue}</td>
                    <td id="isShow${footer.id!}">${footer.isShow.nameValue!}</td>
                    <td id="displayOrder${footer.id!}">${footer.displayOrder!}</td>
                    <td>
                        <a href="javascript:;" id="modifyIsShow${footer.id!}" name="isShow" style="text-decoration:none;" data-value="${footer.isShow.value?default(0)}" dataValue='${footer.id!}'><#if footer.isShow.booleanValue == true>不显示&nbsp;<#else>&nbsp;显示&nbsp;</#if></a>
                        <a href="${DOMAIN_CMS}/homepage/index/editFooter.htm?footerId=${footer.id!}" title="修改">修改</a>
                        <a href="javascript:footer.updateSort(${footer.id!});" id="updateSort${footer.id!}" dataValue="${footer.id!}" name="updateSort" title="修改排序号">修改排序号</a>
                        <a href="javascript:;" title="删除" class="info_delete_a" dataValue="${footer.id!}">删除</a>
                    </td>
                </tr>
                </#list>  
            </table>
            </@doublePage2>
            <#else>
                <div class="public-nodata">没有数据哦...</div>
            </#if>
        </div>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/footerManageJs.ftl" />
<script>
$(function(){
    footer.init();
});

function search(){
    $("#searchButton").click();
}

</script>
</body>
</html>