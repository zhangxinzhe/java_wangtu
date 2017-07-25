<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-基地课程</title>

</head>
<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：课程管理&nbsp;&gt;&nbsp;<span>基地课程</span></p>
        
        <!--搜索显示内容start-->
        <form id="coruseSearchForm" action="" method="post" >
        <table class="form-table validateForm mt-10">
            <tr>
                <th>上课时间：</th>
                <td>
                    <input type="text" class="input-txt fn-left" style="width:80px;" readonly onkeydown="if(window.event.keyCode == 8){return false;}" onclick="$('#btwp').click();" name="course.beginTime" id="beginTime" <#if course?? && course.beginTime?exists>value="${course.beginTime?string("yyyy-MM-dd")}"</#if>>
                    <a hidefocus="true" href="javascript:;" id="btwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'beginTime',isShowClear:true,readOnly:true,isShowWeek:true,maxDate:'#F{$dp.$D(\'endTime\')}'})"></a>
                    <span class="fn-left mt-5 ml-10">至</span>
                    <input type="text" class="input-txt fn-left ml-10" style="width:80px;" readonly onkeydown="if(window.event.keyCode == 8){return false;}" onclick="$('#etwp').click();" name="course.endTime" id="endTime" <#if course?? && course.endTime?exists>value="${course.endTime?string("yyyy-MM-dd")}"</#if>>
                    <a hidefocus="true" href="javascript:;" id="etwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'endTime',isShowClear:true,readOnly:true,isShowWeek:true,minDate:'#F{$dp.$D(\'beginTime\')}'})"></a>
                </td>
            </tr>
            <tr>
                <th>课程名称：</th>
                <td>
                    <input type="text" id="course_courseName" name="course.courseName" value="${course.courseName!}" placeholder="输入课程名称" maxlength="20" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>课程类型：</th>
                <td>
                    <select class="input-sel w-200" id="course_courseType" name="course.courseType">
                        <option value="">全部</option>
                        <#list agencyCourseTypes?if_exists as type>
                            <option value="${type}" <#if course?? && course.courseType?? && course.courseType==type>selected</#if>>${type.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>来源：</th>
                <td>
                    <select class="input-sel w-200" id="course_courseSource" name="course.courseSource">
                        <option value="">全部</option>
                        <#list sourseTypes?if_exists as type>
                            <option value="${type}" <#if course?? && course.courseSource?? && course.courseSource==type>selected</#if>>${type.nameValue2!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr id="course_auditStatus_tr">
                <th>审核状态：</th>
                <td>
                    <select class="input-sel w-200" id="course_auditStatus" name="course.auditStatus">
                        <option value="">全部</option>
                        <#list courseAuditStatus?if_exists as t>
                           <option value="${t!''}" <#if (course.auditStatus)?? && course.auditStatus==t>selected</#if>>${t.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td colspan="3">
                    <a href="javascript:;" id="search" class="abtn abtn-blue">搜索</a>
                    <#if canAddCourse>
                    <a href="${DOMAIN_CMS}/course/addCourse.htm" class="abtn abtn-green ml-5"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                    </#if>
                </td>
            </tr>
        </table>
        </form>
        <!--搜索显示内容end-->
        <!--结果显示内容start-->
        <div class="record-wrap mt-10">
        <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="45" class="t-center">序号</th>
                    <th>课程名称</th>
                    <#--<th width="120">开始时间 </th>-->
                    <th>名师</th>
                    <th width="70">课程类型</th>
                    <#--<th>助教</th>-->
                    <th width="70">课程状态</th>
                    <th width="70">发布状态</th>
                    <th width="180">操作</th>
                </tr>
                <#list list?if_exists as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td><span title="${item.courseName!}"><@cutOff cutStr="${item.courseName!}" cutLen="38" /></span></td>
                    <#--<td><#if item.beginTime??>${(item.beginTime?string('yyyy-MM-dd HH:mm'))!}</#if></td>-->
                    <td><span title="${item.teaRealName!}（${item.teaName!}）"><@cutOff cutStr="${item.teaRealName!}" cutLen="10" /></span><#if item.teaName??>（${item.teaName!}）</#if></td>
                    <td>${item.courseType.nameValue}</td>
                    <#--<td><span title="${item.assRealName!}（${item.assName!}）"><@cutOff cutStr="${item.assRealName!}" cutLen="10" /></span><#if item.assName??>（${item.assName!}）</#if></td>-->
                    <td>
                        <#if item.courseSource==frontApply><span <#if item.auditStatus==auditNoPass>class="c-green"<#elseif item.auditStatus==auditing>class="c-red"</#if>>${item.auditStatus.nameValue!}</span>
                        <#else>${item.courseSource.nameValue}
                        </#if>
                    </td>
                    <td><span <#if item.status==courseCancel>class="c-red"<#elseif item.status==notPublish>class="c-orange"</#if>>${item.status.nameValue!}</span></td>
                    <td>
                        <#if item.courseSource==frontApply && item.status!=published>
                        <a href="javascript:;" class="course-audit" dataValue="${item.id!}">审核</a>
                        </#if>
                        <a href="javascript:;" class="course-edit" dataValue="${item.id!}">修改</a>
                        <#if item.status == notPublish>
                        <a href="javascript:;" class="course-invalid" dataValue="${item.id!}" flag="0">发布</a>
                        <#elseif item.status == published>
                        <a href="javascript:;" class="course-invalid" dataValue="${item.id!}" flag="1">下架</a>
                        <#elseif item.status == courseCancel>
                        <a href="javascript:;" class="course-invalid" dataValue="${item.id!}" flag="2">恢复</a>
                        </#if>
                        <a href="javascript:;" class="course-del" dataValue="${item.id!}">删除</a>
                    </td>
                </tr>
                </#list>
            </table>
        </@doublePage2>
        </div>
        <!--结果显示内容end-->
    </div>
</div>
<@footer />

<#include "/pages/jsinclude/course/courseManagejs.ftl"/>
<script>
    $(function(){
        courseManage.init();
    });
</script>
</body>
</html>