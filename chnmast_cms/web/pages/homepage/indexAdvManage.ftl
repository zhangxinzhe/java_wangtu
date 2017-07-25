<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-首页管理-图片音视频</title>
</head>
<body>
<@header curAppId=appId/>
<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<span>图片音视频</span></p>
        <ul class="public-tab fn-clear mt-10">
            <li <#if tabStr=="pic">class="current"</#if> id="picLi" >轮播图片</li>
            <li <#if tabStr=="audio">class="current"</#if> id="audioLi" >伴奏音频</li>
            <li <#if tabStr=="video">class="current"</#if> id="videoLi">精彩视频</li>
        </ul>
        <#if tabStr="pic">
        <ul class="public-sub-tab">
            <li   <#if  picPlatform=="PC" > class="current"</#if> ><a id="PC_A" href="${DOMAIN_CMS}/homepage/adv/advManage.htm?picPlatform=PC">PC版</a></li>
            <li   <#if  picPlatform=="MOBILE" >class="current"</#if> ><a id="MOBILE_A" href="${DOMAIN_CMS}/homepage/adv/advManage.htm?picPlatform=MOBILE">手机版</a></li>
        </ul>
        </#if>
        
        <!--搜索显示内容start-->
        <form id="searchForm" action="${DOMAIN_CMS}/homepage/adv/advManage.htm" method="post" >
        <input name="tabStr" type="hidden" value="${tabStr}"/>
        <input name="picPlatform" type="hidden" value="${picPlatform!'PC'}"/>
        <table class="form-table validateForm mt-10">
            <tr>
                <th>标题：</th>
                <td>
                    <input type="text" id="advTitle" name="indexAdv.title" value="${(indexAdv.title)!}" placeholder="输入标题" maxlength="20" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>是否显示：</th>
                <td>
                    <select class="input-sel w-200" id="advIsShow" name="indexAdv.isShow">
                        <option value="">全部</option>
                        <#list YesNoTypes?if_exists as type>
                            <option value="${type}" <#if indexAdv?? && indexAdv.isShow?? && indexAdv.isShow==type>selected</#if>>${type.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td colspan="3">
                    <a href="javascript:;" id="search" class="abtn abtn-blue">搜索</a>
                    <#if tabStr=="pic">
                      <a href="${DOMAIN_CMS}/homepage/adv/addAdv.htm?tabStr=pic&&picPlatform=${picPlatform!'PC'}" class="abtn abtn-green ml-5">
                    <#elseif tabStr=="audio">
                      <a href="${DOMAIN_CMS}/homepage/adv/addAdv.htm?tabStr=audio" class="abtn abtn-green ml-5">
                    <#else>
                      <a href="${DOMAIN_CMS}/homepage/adv/addAdv.htm?tabStr=video" class="abtn abtn-green ml-5">
                    </#if>
                    <img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                </td>
            </tr>
        </table>
        </form>
        <!--搜索显示内容end-->
        <!--结果显示内容start-->
        <div class="record-wrap mt-10">
         <#if indexAdvList?? && (indexAdvList.size()>0)>
           <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="5%" class="t-center">序号</th>
                    <#if tabStr=="pic">
                    <th width="10%" class="t-center">标题</th>
                    <th width="30%" class="t-center">内容</th>
                    <th width="16%" class="t-center">链接地址</th>
                    <th width="7%" class="t-center">显示 </th>
                    <th width="7%" class="t-center">排序号</th>
                    <#elseif tabStr="audio">
                    <th width="20%" class="t-center">标题</th>
                    <th width="20%" class="t-center">演唱者</th>
                    <th width="15%" class="t-center">显示</th>
                    <th width="15%" class="t-center">点击量</th>
                    <#else>
                    <th width="10%" class="t-center">标题</th>
                    <th width="30%" class="t-center">内容</th>
                    <th width="15%" class="t-center">显示 </th>
                    <th width="15%" class="t-center">排序号</th>
                    </#if>
                    <th width="25%" class="t-center">操作</th>
                </tr>
                <#list indexAdvList as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td class="t-center"><span title="${item.title!}"><@cutOff cutStr="${item.title!}" cutLen="25" /></span></td>
                    <#if tabStr="pic">
                    <td class="t-center"><img src="${DOMAIN_UPLOAD_FILE}/${item.pictureFile}" width="300" height="80"></td>
                    <td class="t-center"><span title="${item.linkUrl!}"><@cutOff cutStr="${item.linkUrl!}" cutLen="25" /></span></td>
                    <td class="t-center">${item.isShow.nameValue!}</td>
                    <td class="t-center" id="orderTd${item_index}">${item.displayOrder!}</td>
                    <#elseif tabStr="audio">
                    <td class="t-center" >${item.remark!}</td>
                    <td class="t-center" >${item.isShow.nameValue!}</td>
                    <td class="t-center" >${item.displayOrder!}</td>
                    <#else>
                    <td class="t-center"><img src="${DOMAIN_UPLOAD_FILE}/${item.pictureFile}" width="200" height="123"></td>
                    <td class="t-center">${item.isShow.nameValue!}</td>
                    <td class="t-center" id="orderTd${item_index}">${item.displayOrder!}</td>
                    </#if>
                    <td class="t-center">
                         <#if item.isShow.nameValue=="否">
                          <a href="javascript:indexAdv.recOneItem(true,${item.id!},'${item.title!}');">显示</a>&nbsp;
                         <#else>
                          <a href="javascript:indexAdv.recOneItem(false,${item.id!},'${item.title!}');">不显示</a>&nbsp;
                         </#if>
                        <#if tabStr!="audio">
                        <a href="javascript:indexAdv.modifyOneItemOrder(${item_index});" id="modifyA${item_index}" dataValue="${item.id!}" dataTitle="${item.title!}">修改排序号</a>&nbsp;
                        </#if>
                        <a href="${DOMAIN_CMS}/homepage/adv/updateAdv.htm?tabStr=${tabStr}&id=${item.id!}&picPlatform=${picPlatform!'PC'}">修改</a>&nbsp;
                        <a href="javascript:indexAdv.deleteOneItem(${item.id!});" >删除</a>&nbsp;
                        <#if tabStr=="video">
                        <a href="javascript:indexAdv.toOpenVideo(${item.id!});" >预览</a>
                        </#if>
                    </td>
                </tr>
                </#list>
            </table>
         </@doublePage2>
         <#else>
             <div class="public-nodata">暂时没有数据！</div>
         </#if>
        </div>
        <!--结果显示内容end-->
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/indexAdvjs.ftl"/>
<script>
$(function(){
   indexAdv.init('${tabStr}','${picPlatform!'PC'}');
});
</script>
</body>
</html>