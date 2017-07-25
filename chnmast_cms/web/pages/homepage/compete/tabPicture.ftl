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
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/compete/competeManage.htm?id=${id!'0'}">活动专题</a>&nbsp;&gt;&nbsp;<span>活动图集</span></p>
        <#include "/pages/homepage/compete/commonTab.ftl" />
        <@commonTab curLi=3 competeName='${(compete.competeName)!""}' competeId='${id}' competeType="${compete.competeType!}" />
        
        <form id="pictureForm" action="#" method="post">
        <table class="form-table validateForm mt-10">
            <tr>
                <th>是否显示：</th>
                <td>
                     <select class="input-sel w-200" name="isShow">
                        <option value="">全部</option>
                        <#list yesNoTypes?if_exists as type>
                            <option value="${type}" <#if isShow?? && isShow == type>selected</#if>>${type.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                    <a href="/homepage/compete/editPicture.htm?id=${id!'0'}" class="abtn abtn-green ml-10" id="newButton"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <#if list?has_content>
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="45" class="t-center">序号</th>
                    <th>标题</th>
                    <th class="t-center">是否显示 </th>
                    <th class="t-center">显示序号</th>
                    <th class="t-center">操作</th>
                </tr>
                <#list list as item>
                <tr>
                    <td class="t-center"><input type="checkbox" name="pictureIdsCheckbox" value="${item.id!}"/></td>
                    <td><span title="${item.pictureName!}"><@cutOff cutStr="${item.pictureName!}" cutLen="80" /></span></td>
                    <td class="t-center">${item.isShow.nameValue!}</td>
                    <td class="t-center" id="showOrder${item_index}">${item.showOrder!}</td>
                    <td class="t-center">
                        <#if !item.isShow.booleanValue>
                          <a href="javascript:tabPicture.recOneItem(true,${item.id!},'${item.pictureName!}');">显示</a>&nbsp;
                        <#else>
                        <a href="javascript:tabPicture.recOneItem(false,${item.id!},'${item.pictureName!}');">不显示</a>&nbsp;
                        </#if>
                        <a href="javascript:tabPicture.modifyOneItemOrder(${item_index});" id="modifyA${item_index}" dataValue="${item.id!}" dataTitle="${item.title!}">修改排序号</a>&nbsp;
                        <a href="${DOMAIN_CMS}/homepage/compete/editPicture.htm?id=${id!'0'}&&pictureId=${item.id!}">修改</a>&nbsp;
                        <a href="javascript:;" class="delPicture" dataValue="${(item.id)!'0'}">删除</a>
                    </td>
                </tr>
                </#list>
                <tr>
                    <td width="7%" class="t-center"><input type="checkbox" id="checkAll" title="全选"></td>
                    <td colspan="4">
                        <a href="javascript:;" class="abtn abtn-blue" id="delButton">删除</a>
                    </td>
                </tr>
            </table>
            </@doublePage2>
            <#else>
             <div class="public-nodata">暂时没有数据！</div>
            </#if>
        </div>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/tabPictureJs.ftl" />
<script>
    $(function(){
        tabPicture.init();
    });
</script>
</body>
</html>