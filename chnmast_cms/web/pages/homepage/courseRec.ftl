<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-首页管理-基地课程推荐</title>

</head>
<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<span>基地课程推荐</span></p>
        <ul class="public-tab fn-clear mt-10">
            <li <#if isCourseRec>class="current"</#if> operate="reced">已推荐</li>
            <li <#if !isCourseRec>class="current"</#if> operate="noRec">可推荐</li>
        </ul>
        
        <#if !isCourseRec>
        <form id="courseForm" method="post" action=""/>
            <input name="operate" type="hidden" value="${operate?string}"/>
            <input style="display:none"/>
            <table class="form-table validateForm mt-10">
                <tr>
                    <th width="10%">课程名称：</th>
                    <td width="90%">
                        <input type="text" name="courseName" class="input-txt" placeholder="输入课程名称" value="${courseName!}">
                        <a href="javascript:;" id="searchButton" class="abtn abtn-blue ml-5">搜索</a>
                    </td>
                </tr>
            </table>
        </form>
        </#if>
        
        <#if list?has_content>
            <@doublePage2>
            <#if isCourseRec><form id="tableForm" method="post" action=""><input type="hidden" name="isCourseRec" value="${isCourseRec?string('false','ture')}" /></#if>
            <table class="public-table">
                <tr>
                    <th width="50" class="t-center"></th>
                    <th>课程名称</th>
                    <th>开始时间 </th>
                    <th>名师</th>
                    <th>助教</th>
                    <#if isCourseRec>
                    <th>推荐时间</th>
                    <th>排序号</th>
                    </#if>
                    <th>操作</th>
                </tr>
                
                <#list list as course>
                    <tr>
                        <td class="t-center"><#if isCourseRec><input type="checkbox" name="ids" value="${course.id!}"/><#else>&nbsp;</#if></td>
                        <td><span title="${course.courseName!}"><@cutOff cutStr="${course.courseName!}" cutLen="30" /></span></td>
                        <td><#if course.beginTime??>${course.beginTime?string('yyyy-MM-dd HH:mm:ss')!}</#if></td>
                        <td>${course.teaRealName!}</td>
                        <td>${course.assRealName!}</td>
                        <#if isCourseRec>
                        <td><#if course.recommendTime??>${course.recommendTime?string('yyyy-MM-dd HH:mm:ss')!}</#if></td>
                        <td id="orderTd${course_index}">${course.recommendSeq!}</td>
                        </#if>
                        <td>
                            <#if !isCourseRec>
                                <a href="javascript:;" class="courseRec" isReced_flag="true" dataValue="${course.id!}" dataName="${course.courseName!}">推荐</a>
                            <#else>
                                <a href="javascript:courseRec.modifyOneItemOrder(${course_index});" id="modifyA${course_index}" dataValue="${course.id!}" dataCourseName="${course.courseName!}">修改排序号</a>
                                <a href="javascript:;" class="courseRec" isReced_flag="false" dataValue="${course.id!}" dataName="${course.courseName!}">取消推荐</a>
                            </#if>
                        </td>
                    </tr>
                </#list>
                <#if isCourseRec>
                <tr>
                    <td class="t-center"><input type="checkbox" id="checkAll" title="全选"></td>
                    <td colspan="6">
                        <a href="javascript:;" class="abtn abtn-blue" id="cancelRec">取消推荐</a>
                    </td>
                </tr>
                </#if>
            </table>
            <#if isCourseRec></form></#if>
            </@doublePage2>
        <#else>
            <div class="record-wrap mt-10">
            <#if isCourseRec>
                <div class="public-nodata">暂时没有推荐课程</div>
            <#else>
                <div class="public-nodata">暂时没有可推荐的课程</div>
            </#if>
            </div>
        </#if>
    </div>
</div>
<@footer />

<#include "/pages/jsinclude/homepage/courseRecjs.ftl"/>
<script>
    $(function(){
        courseRec.init();
    });
</script>
</body>
</html>