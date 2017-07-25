<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-名师大家</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据 &nbsp;&gt;&nbsp;<span>名师大家</span></p>
        <form class="studentManage" id="teacherForm" action="#" method="post" enctype="multipart/form-data">
        <table class="form-table validateForm mt-10">
            <tr>
                <th>姓名：</th>
                <td> 
                    <input type="text" class="input-txt" id="teacherNameInput" name="teacherName" placeholder="输入名师大家姓名" value="${teacherName!}"/>
                </td>
            </tr>
            <tr>
                <th>类型：</th>
                <td>
                    <select class="input-sel w-200" name="teachType">
                        <option value="0">请选择</option>
                        <#list teachTypes as type>
                            <option value="${type.value}" <#if teachType! == type.value>selected</#if>>${type.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>状态：</th>
                <td>
                    <select class="input-sel w-200" name="teacherStatus">
                        <option value="999">全部</option>
                        <#list statusEunms as item>
                            <option value="${item.value}" <#if teacherStatus! == item.value>selected</#if>>${item.nameValue3!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                    <#if canAddTeacher>
                    <a href="javascript:;" class="abtn abtn-green ml-10" id="newButton"><img src="../images/icon/add2.png">新增</a>
                    </#if>
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="50" class="t-center"><input id="allCheckbox1" type="checkbox"></th>
                    <th>姓名</th>
                    <th>类型</th>
                    <th>职业职称</th>
                    <th>用户名</th>
                    <th>密码</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <#list teacherList?if_exists as teacher>
                <tr>
                    <td class="t-center"><input name="teacherIdsCheckbox" type="checkbox" value="${teacher.id!}"/></td>
                    <td id="teacherName_${teacher.id!}" title="${teacher.realName!}"><@cutOff cutStr="${teacher.realName!}" cutLen="20"/></td>
                    <td>${teacher.teachType.nameValue!}</td>
                    <td title="${teacher.title!}"><@cutOff cutStr="${teacher.title!}" cutLen="40"/></td>
                    <td>${teacher.username!}</td>
                    <td>${teacher.password!}</td>
                    <td><#if teacher.isCancel.booleanValue>注销<#else>正常</#if></td>
                    <td>
                        <#if canEditTeacher>
                        <a href="${DOMAIN_CMS}/basic/editTeacher.htm?teacherId=${teacher.id}" title="修改">修改</a>
                        </#if>
                    </td>
                </tr>
                </#list>
                <tr>
                    <td class="t-center"><input id="allCheckbox2" type="checkbox"></td>
                    <td colspan="7">
                        <#if canCancelTeacher>
                        <a href="javascript:;" id="cancelButton" class="abtn abtn-blue">注销</a>
                        </#if>
                        <#if canRecoverTeacher>
                        <a href="javascript:;" id="recoverButton" class="abtn abtn-blue">恢复</a>
                        </#if>
                        <#if canDelTeacher>
                        <a href="javascript:;" id="removeButton" class="abtn abtn-blue">删除</a>
                        </#if>
                    </td>
                </tr>
            </table>
            </@doublePage2>
        </div>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/basic/teacher/teacherManageJs.ftl" />
<script>
$(function(){
    TeacherManage.init();
});

</script>
</body>
</html>