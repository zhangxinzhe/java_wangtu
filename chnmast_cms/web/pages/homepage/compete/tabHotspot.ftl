<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-活动专题</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=1300000 curSubModId=1300400 />
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/compete/competeManage.htm">活动专题</a>&nbsp;&gt;&nbsp;<span>热点追踪</span></p>
        <#include "/pages/homepage/compete/commonTab.ftl" />
        <@commonTab curLi=2 competeName='${(compete.competeName)!""}' competeId='${id}' competeType="${compete.competeType!}" />
        <form class="infoManage" action="${DOMAIN_CMS}/homepage/compete/tabHotspot.htm?id=${id!'0'}" method="post">
        <table class="form-table validateForm mt-10">
            <tr>
                <th>标题：</th>
                <td> 
                    <input type="text" class="input-txt" id="title" name="title" placeholder="输入查询标题" value="${title!}"/>
                </td>
            </tr>
            <tr>
                <th>是否显示：</th>
                <td>
                    <select class="input-sel w-200" name="isShow">
                        <option value="">全部</option>
                        <#list yesNoTypes?if_exists as t>
                        <option value="${t}" <#if isShow?exists && isShow==t>selected</#if>>${t.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                    <a href="/homepage/compete/hotSpotEdit.htm?id=${id!''}" class="abtn abtn-green ml-10" id="newButton"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <#if infoLists?exists && infoLists.size() gt 0 >
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="45" class="t-center">序号</th>
                    <th>标题</th>
                    <th class="t-center">是否显示</th>
                    <th class="t-center">排序号</th>
                    <th class="t-center">类型</th>
                    <th class="t-center">创建日期</th>
                    <th class="t-center">操作</th>
                </tr>
                <#list infoLists?if_exists as info>
                <tr>
                    <td class="t-center">${info_index+1}</td>
                    <td><span title="${info.title!}"><@cutOff cutStr="${info.title!}" cutLen="50"/></span></td>
                    <td class="t-center">${info.isShow.nameValue!}</td>
                    <td class="t-center">${info.orderNo!}</td>
                    <td class="t-center">${info.contentType.nameValue}</td>
                    <td class="t-center">${info.createDate?string("yyyy-MM-dd HH:mm")}</td>
                    <td class="t-center">
                        <a href="${DOMAIN_CMS}/homepage/compete/hotSpotEdit.htm?infoId=${info.id!}&&id=${id!'0'}" title="修改">修改</a>
                        <a href="javascript:;" title="删除" class="info_delete_a" val="${info.id!}">删除</a>
                    </td>
                </tr>
                </#list>  
            </table>
            </@doublePage2>
            <#else>
                <div class="public-nodata">暂时没有数据！</div>
            </#if>
        </div>        
        
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/tabHotSpotManageJs.ftl" />
<script>
    $(function(){
        tabHotSpotManage.init();
    });
</script>
</body>
</html>