<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl" />
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-学员图片</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：首页管理 > <a href="${DOMAIN_CMS}/homepage/stu/studentManage.htm">优秀学员</a> > <span>风采展示</span></p>
        <ul class="public-tab fn-clear mt-10">
            <span class="tt" title="${(student.stuName)!}">${(student.stuName)!}</span>
            <li onclick="javascript:window.location.href='${DOMAIN_CMS}/homepage/stu/updateStudent.htm?id=${(student.id)!}'">基本信息</li>
            <li class="current" onclick="javascript:window.location.href='${DOMAIN_CMS}/homepage/stu/studentPicManage.htm?id=${(student.id)!}'">风采展示</li>
        </ul>
        <table class="form-table validateForm mt-10">
            <tr>
                <td>
                    <a href="${DOMAIN_CMS}/homepage/stu/addOrUpdateStuPicture.htm?id=${(student.id)!}&tabStr=pic" class="abtn abtn-green ml-10"><img src="/images/icon/add2.png">新增图片</a>
                    <a href="${DOMAIN_CMS}/homepage/stu/addOrUpdateStuPicture.htm?id=${(student.id)!}&tabStr=video" class="abtn abtn-green ml-10"><img src="/images/icon/add2.png">新增视频</a>
                </td>
            </tr>
        </table>
        <#if pictureList?exists && (pictureList.size() > 0)>
            <div class="record-wrap mt-10">
              <form id="picForm" method="post" action="">
                <input name="id" type="hidden" value="${(student.id)!}"/>
                <@doublePage2>
                <table class="public-table" id="listTable">
                    <tr>
                        <th width="7%" class="t-center"></th>
                        <th width="32%" class="t-center">名称</th>
                        <th width="10%" class="t-center">显示顺序</th>
                        <th width="10%" class="t-center">是否显示</th>
                        <th width="18%" class="t-center">类型</th>
                        <th width="23%" class="t-center">操作</th>
                    </tr>
                    <#list pictureList as picture>
                        <tr>
                            <td class="t-center"><input type="checkbox" name="ids" value="${picture.id!}"/></td>
                            <td class="t-center">${picture.pictureName!}</td>
                            <td class="t-center" id="orderTd${picture_index}">${picture.showOrder!}</td>
                            <td class="t-center"><#if picture.isShow.booleanValue!>是<#else>否</#if></td>
                            <td class="t-center">${picture.fileExt!}</td>
                            <td class="t-center">
                                <#if picture.isShow.booleanValue!>
                                <a href="javascript:studentPicManage.recOneItem(false,${picture.id!},'${picture.pictureName!}')" >不显示</a>
                                <#else>
                                <a href="javascript:studentPicManage.recOneItem(true,${picture.id!},'${picture.pictureName!}')" >显示</a></#if>&nbsp;
                                <a href="javascript:studentPicManage.modifyOneItemOrder(${picture_index})" id="modifyA${picture_index}" dataValue="${picture.id!}" dataName="${picture.pictureName!}">修改排序号</a>&nbsp;
                                <a href="${DOMAIN_CMS}/homepage/stu/addOrUpdateStuPicture.htm?id=${(student.id)!}&picId=${(picture.id)!}" class="update">修改</a>
                                <#if picture.fileType?default(1) == 2>
                                <a href="javascript:;" onclick="javascript:studentPicManage.openVideo('${picture.id}');" >预览</a>
                                </#if>
                            </td>
                        </tr>
                    </#list>
                    <tr>
                        <td width="7%" class="t-center"><input type="checkbox" id="checkAll" title="全选"></td>
                        <td colspan="5">
                            <a href="javascript:studentPicManage.deleteItem();" class="abtn abtn-blue" id="removeButton">删除</a>
                        </td>
                    </tr>
                </table>
                </@doublePage2>
                </form>
            </div>
        <#else>
            <div class="record-wrap mt-10">
                <div class="public-nodata">暂时没有数据！</div>
            </div>
        </#if>
    </div>
</div>

<@footer />

</body>
<#include "/pages/jsinclude//homepage/studentPicManagejs.ftl" />
<script>
$(function(){
    studentPicManage.init(${(student.id)!});
});
</script>
</html>