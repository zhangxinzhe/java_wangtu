<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl" />
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-展示图片</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/teacherManage.htm">名师大家</a>&nbsp;&gt;&nbsp;<span>风采展示</span></p>
        <ul class="public-tab fn-clear mt-10">
            <span class="tt" title="${teacher.realName!}"><@cutOff cutStr="${teacher.realName!}" cutLen="20"/></span>
            <li onclick="javascript:window.location.href='${DOMAIN_CMS}/basic/editTeacher.htm?teacherId=${teacherId!}'">基本信息</li>
            <li class="current" onclick="javascript:window.location.href='${DOMAIN_CMS}/basic/teacherStyle.htm?teacherId=${teacherId!}'">风采展示</li>
        </ul>
        <table class="form-table validateForm mt-10">
            <tr>
                <td>
                    <a href="${DOMAIN_CMS}/basic/editTeacherStyle.htm?teacherId=${teacherId!}&fileType=1" class="abtn abtn-green ml-10"><img src="/images/icon/add2.png">新增照片</a>
                    <a href="${DOMAIN_CMS}/basic/editTeacherStyle.htm?teacherId=${teacherId!}&fileType=2" class="abtn abtn-green ml-10"><img src="/images/icon/add2.png">新增视频</a>
                </td>
            </tr>
        </table>
        <#if pictureList?exists && pictureList.size() gt 0>
            <div class="record-wrap mt-10">
                <@doublePage2>
                <table class="public-table" id="listTable">
                    <tr>
                        <th width="7%" class="t-center"></th>
                        <th width="37%">名称</th>
                        <th width="10%">显示顺序</th>
                        <th width="10%">是否显示</th>
                        <th width="15%">类型</th>
                        <th width="18%">操作</th>
                    </tr>
                    <#list pictureList as picture>
                        <tr>
                            <td class="t-center"><input type="checkbox" name="pictureIdsCheckbox" value="${picture.id!}"/></td>
                            <td>${picture.pictureName!}</td>
                            <td>${picture.showOrder!}</td>
                            <td><#if picture.isShow.booleanValue!>是<#else>否</#if></td>
                            <td><#if picture.fileType?default(1) == 1>照片<#else>视频</#if></td>
                            <td>
                                <a href="${DOMAIN_CMS}/basic/editTeacherStyle.htm?teacherId=${teacherId!}&picId=${picture.id!}" class="update">修改</a>
                                <#if picture.fileType?default(1) == 2>
                                <a href="javascript:;" onclick="javascript:TeacherStyle.openVideo('${picture.id}');" >预览</a>
                                </#if>
                            </td>
                        </tr>
                    </#list>
                    <tr>
                        <td width="7%" class="t-center"><input type="checkbox" id="checkAll" title="全选"></td>
                        <td colspan="5">
                            <input type="hidden" id="teacherId" value="${teacherId!}"/>
                            <a href="javascript:;" class="abtn abtn-blue" id="removeButton">删除</a>
                        </td>
                    </tr>
                </table>
                </@doublePage2>
            </div>
        <#else>
            <div class="record-wrap mt-10">
                <div class="public-nodata">教师暂时没有风采展示</div>
            </div>
        </#if>
    </div>
</div>

<@footer />

</body>
<#include "/pages/jsinclude/basic/teacher/teacherStyleJs.ftl" />
<script>
$(function(){
    TeacherStyle.init();
});
</script>
</html>