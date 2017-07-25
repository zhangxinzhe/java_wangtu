<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<script type="text/javascript" src="${DOMAIN_CMS!}/js/component/date/WdatePicker.js?${appsetting.getVersionId()!}"></script>
<title>${SITE_CMS_SITE_NAME}-报名统计</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：统计查询&nbsp;>&nbsp;<span>报名统计</span></p>
        <form class="detailForm" action="${DOMAIN_CMS}/stat/reportStat.htm" method="post">
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>名称：</th>
                <td> 
                    <@courseSelect_new cId="courseId" cIdName="orderReport.courseId" cIdValue="${orderReport.courseId!}" cnId="courseName" cnName="orderReport.courseName" cnValue="${orderReport.courseName!}" fn="$('#searchBut').click()"/>
                </td>
            </tr>
            <tr>
                <th>报名时间：</th>
                <td> 
                    <input type="text" class="input-txt fn-left t-80" notNull="true" id="reportTimeStart" name="reportTimeStart" value="${(reportTimeStart?string('yyyy-MM-dd'))!}" onclick="$('#btwp').click();" readonly/>
                    <a hidefocus="true" href="javascript:;" id="btwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'reportTimeStart',isShowClear:true,readOnly:true,isShowWeek:true,maxDate:'#F{$dp.$D(\'reportTimeEnd\')}'})"></a>                            
                    <span class="fn-left mt-5 ml-10">至</span>
                    <input type="text" class="input-txt fn-left ml-10 t-80" notNull="true" id="reportTimeEnd" name="reportTimeEnd" value="${(reportTimeEnd?string('yyyy-MM-dd'))!}" onclick="$('#etwp').click();" readonly/>
                    <a hidefocus="true" href="javascript:;" id="etwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'reportTimeEnd',isShowClear:true,readOnly:true,isShowWeek:true,minDate:'#F{$dp.$D(\'reportTimeStart\')}'})"></a>
                </td>
            </tr>
            <tr>
                <th>上课方式：</th>
                <td> 
                    <select class="input-sel w-200" name="orderReport.studyType">
                        <option value="">全部</option>
                        <#list CourseStudyTypes?if_exists as sType>
                        <option value="${sType}" <#if orderReport.studyType?exists && orderReport.studyType==sType>selected</#if> >${sType.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="searchBut">搜索</a> 
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
        <#if reportList?has_content>
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th class="t-center" style="width:50px;">序号</th>
                    <th>课程名称</th>
                    <th style="width:200px">课次/场次</th>
                    <th style="width:150px;">报名时间</th>
                    <th>姓名</th>
                    <th>用户名</th>
                    <#--
                    <th class="t-center">应付</th>
                    <th class="t-center">实付</th>
                    -->
                    <th class="t-center">类型</th>
                    <th class="t-center">是否退课</th>
                    <th class="t-center">是否退款</th>
                </tr>
                <#list reportList?if_exists as info>
                <tr>
                    <td class="t-center">${info_index+1}</td>
                    <td title="${info.courseName!}"><@cutOff cutStr="${info.courseName!}" cutLen="40"/></td>
                    
                    <td> [${info.courseSeq!}]<#if info.courseContentType != vod>${info.courseBeginTime?string("yyyy-MM-dd HH:mm")}-${info.courseEndTime?string("HH:mm")}  </#if></td>
                  
                    <td>${info.reportTime!}</td>
                    <td>${info.realName!}</td>
                    <td>${info.userName!}</td>
                    <#-- 
                    <td class="t-center">${info.payableAmount?string("0.00")}</td>
                    <td class="t-center">${info.actualAmount?string("0.00")}</td>
                    -->
                    <td class="t-center">${info.studyType.nameValue!}</td>
                    <td class="t-center">${info.isReturnCourse.nameValue!}</td>
                    <td class="t-center">${info.isReturnMoney.nameValue!}</td>
                </tr>
                </#list>  
            </table>
            </@doublePage2>
        <#else>
            <div class="public-nodata">此课程暂时没有报名记录！</div>
        </#if>
        </div>
    </div>
</div>
<@footer />
</body>
<script>
    $(function(){
        $("#searchBut").click(function(){
        var courseId = $("#courseId").val();
        if(courseId=="0" ){
            Tips.showAlertWin({info:"请选择一门课程！"});
            return;
        }
            $(".detailForm").submit();   
        }); 
    });
</script>
</html>