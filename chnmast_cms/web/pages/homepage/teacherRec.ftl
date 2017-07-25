<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-首页管理-名师大家推荐</title>

</head>
<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<span>名师大家推荐</span></p>
        <ul class="public-tab fn-clear mt-10">
            <li <#if isTeacherRec>class="current"</#if> isRec="true">已推荐</li>
            <li <#if !isTeacherRec>class="current"</#if> isRec="false">可推荐</li>
        </ul>
        
        <#if !isTeacherRec>
        <form id="teacherForm" method="post" action=""/>
            <input type="hidden" name="isTeacherRec" value="${isTeacherRec?string('true','false')}" />
            <table class="form-table validateForm mt-10">
                <tr>
                    <th width="5%">姓名：</th>
                    <td width="20%">
                        <input type="text" name="teacherName" class="input-txt" placeholder="输入名师大家名称" value="${teacherName!}">
                    </td>
                    <th width="10%">类型：</th>
                    <td width="65%">
                        <select class="input-sel" name="teachType">
                        <option value="0">--请选择--</option>
                        <#list teachTypes as type>
                            <option value="${type.value}" <#if teachType! == type.value>selected</#if>>${type.nameValue!}</option>
                        </#list>
                    </select>
                        <a href="javascript:;" id="searchButton" class="abtn abtn-blue ml-5">搜索</a>
                    </td>
                </tr>
            </table>
        </form>
        </#if>
        <#if teacherList?has_content>
            <@doublePage2>
            <#if isTeacherRec><form id="tableForm" method="post" action=""><input type="hidden" name="isTeacherRec" value="${isTeacherRec?string('false','true')}" /></#if>
            <table class="public-table">
                <tr>
                    <th width="5%" class="t-center"></th>
                    <th >姓名</th>
                    <th>类型</th>
                    <th>职业职称</th>
                    <#if isTeacherRec>
                    <th>推荐时间</th>
                    <th>排序号</th>
                    </#if>
                    <th width="20%">操作</th>
                </tr>
                
                <#list teacherList as teacher>
                    <tr>
                        <td class="t-center"><#if isTeacherRec><input type="checkbox" name="ids" value="${teacher.id!}"/><#else>&nbsp;</#if></td>
                        <td><span title="${teacher.realName!}"><@cutOff cutStr="${teacher.realName!}" cutLen="30" /></span></td>
                        <td>${teacher.teachType.nameValue!}</td>
                        <td>${teacher.title!}</td>
                        <#if isTeacherRec>
                        <td><#if teacher.recommendTime??>${teacher.recommendTime?string('yyyy-MM-dd HH:mm:ss')!}</#if></td>
                        <td id="orderTd${teacher_index}">${teacher.recommendSeq!}</td>
                        </#if>
                        <td>
                            <#if !isTeacherRec>
                                <a href="javascript:;" class="teacherRec" isReced_flag="true" dataValue="${teacher.id!}">推荐</a>
                            <#else>
                                <a href="javascript:teacherRec.modifyOneItemOrder(${teacher_index});" id="modifyA${teacher_index}" dataValue="${teacher.id!}" dataRealName="${teacher.realName!}">修改排序号</a>
                                <a href="javascript:;" class="teacherRec" isReced_flag="false" dataValue="${teacher.id!}">取消推荐</a>
                            </#if>
                        </td>
                    </tr>
                </#list>
                <#if isTeacherRec>
                <tr>
                    <td class="t-center"><input type="checkbox" id="checkAll" title="全选"></td>
                    <td colspan="5">
                        <a href="javascript:;" class="abtn abtn-blue" id="cancelRec">取消推荐</a>
                    </td>
                </tr>
                </#if>
            </table>
            <#if isTeacherRec></form></#if>
            </@doublePage2>
        <#else>
            <div class="record-wrap mt-10">
            <#if isTeacherRec>
                <div class="public-nodata">暂时没有推荐名师大家</div>
            <#else>
                <div class="public-nodata">暂时没有可推荐的名师大家</div>
            </#if>
            </div>
        </#if>
    </div>
</div>
<@footer />

<#include "/pages/jsinclude/homepage/teacherRecjs.ftl"/>
<script>
    $(function(){
        teacherRec.init();
    });
</script>
</body>
</html>