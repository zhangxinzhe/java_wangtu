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
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/compete/competeManage.htm?id=${id!'0'}">活动专题</a>&nbsp;&gt;&nbsp;<span>轮播图片</span></p>
        <#include "/pages/homepage/compete/commonTab.ftl" />
        <@commonTab curLi=6 competeName='${(compete.competeName)!""} ' competeId='${id}' competeType="${compete.competeType!}" />
       
        <form class="pictureManage" action="${DOMAIN_CMS}/homepage/compete/tabPlayPicture.htm?id=${id!'0'}" method="post">
        <table class="form-table validateForm mt-10">
            <tr>
                <th>标题：</th>
                <td> 
                    <input type="text" class="input-txt" id="title" name="indexAdv.title" placeholder="输入查询标题" value="${(indexAdv.title)!}"/>
                </td>
            </tr>
            <tr>
                <th>是否显示：</th>
                <td>
                     <select class="input-sel w-200" id="advIsShow" name="indexAdv.isShow">
                        <option value="">全部</option>
                        <#list yesNoTypes?if_exists as type>
                            <option value="${type}" <#if indexAdv?? && indexAdv.isShow?? && indexAdv.isShow==type>selected</#if>>${type.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                    <a href="/homepage/compete/playPictureEdit.htm?id=${id!'0'}" class="abtn abtn-green ml-10" id="newButton"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <#if indexAdvList?? && (indexAdvList.size()>0)>
           <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="45" class="t-center">序号</th>
                    <th class="t-center">标题</th>
                    <th class="t-center">链接地址</th>
                    <th class="t-center">显示 </th>
                    <th class="t-center">排序号</th>
                    <th  width="220" class="t-center">操作</th>
                </tr>
                <#list indexAdvList as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td class="t-center"><span title="${item.title!}"><@cutOff cutStr="${item.title!}" cutLen="50" /></span></td>
                    <td class="t-center"><span title="${item.linkUrl!}"><@cutOff cutStr="${item.linkUrl!}" cutLen="25" /></span></td>
                    <td class="t-center">${item.isShow.nameValue!}</td>
                    <td class="t-center" id="orderTd${item_index}">${item.displayOrder!}</td>
                    <td class="t-center">
                         <#if item.isShow.nameValue=="否">
                          <a href="javascript:tabPlayPictureManage.recOneItem(true,${item.id!},'${item.title!}');">显示</a>&nbsp;
                         <#else>
                          <a href="javascript:tabPlayPictureManage.recOneItem(false,${item.id!},'${item.title!}');">不显示</a>&nbsp;
                         </#if>
                        <a href="javascript:tabPlayPictureManage.modifyOneItemOrder(${item_index});" id="modifyA${item_index}" dataValue="${item.id!}" dataTitle="${item.title!}">修改排序号</a>&nbsp;
                        <a href="${DOMAIN_CMS}/homepage/compete/playPictureEdit.htm?id=${id!'0'}&&indexAdvId=${item.id!}">修改</a>&nbsp;
                        <a href="javascript:;" class="indexAdv_delete_a" val="${(item.id)!'0'}">删除</a>&nbsp;
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
<#include "/pages/jsinclude/homepage/tabPlayPictureManageJs.ftl" />
<script>
    $(function(){
        tabPlayPictureManage.init();
    });
</script>
</body>
</html>