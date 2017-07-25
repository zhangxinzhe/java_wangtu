<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-选课报名</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=1500000 curSubModId=1500600 />
    
    <div id="content">
        <p class="crumbs">当前位置：业务管理&nbsp;&gt;&nbsp;<span>选课报名</span></p>
        <ul class="public-tab fn-clear mt-10">
            <span class="tt">选课报名</span>
            <li id="userLi">按学员报名</li>
            <li id="courseLi" class="current" href="/account/courseReport.htm">按课程报名</li>
        </ul>
        
        <form id="courseReportForm" action="#" method="post">
            <table class="form-table validateForm mt-10">
                <tr>
                    <th><span class="must">*</span>课程名称：</th>
                    <td> 
                        <@courseSelect_new action="${DOMAIN_CMS}/courseReportSelect.htm" cId="courseId" cIdName="courseId" cIdValue="${courseId!}" cnId="courseName" cnName="course.courseName" cnValue="${course.courseName!}" fn="$('#searchTime').click()"/>
                    </td>
                </tr>
                <tr>
                <th>上课时间：</th>
                <td> 
                    <input type="text" class="input-txt fn-left t-80" notNull="true" id="beginTime" name="beginTime" value="${(beginTime?string('yyyy-MM-dd'))!}" onclick="$('#btwp').click();" readonly/>
                    <a hidefocus="true" href="javascript:;" id="btwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'beginTime',isShowClear:true,readOnly:true,isShowWeek:true,maxDate:'#F{$dp.$D(\'endTime\')}'})"></a>                            
                    <span class="fn-left mt-5 ml-10">至</span>
                    <input type="text" class="input-txt fn-left ml-10 t-80" notNull="true" id="endTime" name="endTime" value="${(endTime?string('yyyy-MM-dd'))!}" onclick="$('#etwp').click();" readonly/>
                    <a hidefocus="true" href="javascript:;" id="etwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'endTime',isShowClear:true,readOnly:true,isShowWeek:true,minDate:'#F{$dp.$D(\'beginTime\')}'})"></a>
                </td>
            </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td>
                        <a href="javascript:;" class="abtn abtn-blue" id="searchTime">搜索</a>
                    </td>
                </tr>
            </table>
        </form>
        
        <div class="record-wrap mt-10">
         <form id="importForm" action="${DOMAIN_CMS}/account/importStuReport.htm" method="post" target="importStuReportFrame" enctype="multipart/form-data">
                <div class="filter">
                    <input type="text" class="input-txt fn-left ml-10" style="width:150px;" id="importName">
                    <a href="javascript:;" class="abtn abtn-blue file-analog fn-btn"><span>选择文件</span><input type="file" name="_importFile" id="importFile"></a>
                    <a href="javascript:;" id="importButton" class="abtn abtn-green fn-btn"><img src="../images/icon/import.png">导入</a>
                    <a href="javascript:;" id="viewResultButton" class="abtn abtn-blue fn-btn">查看任务</a>
                    <a href="${DOMAIN_CMS}/sysfile/template/student-templete.xls" class="fn-left fn-txt ml-10">导入模版下载</a>
                </div>
            </form> 
            <@doublePage2>
            <table class="public-table">
            <input id="contentType" name="course.contentType" value="${course.contentType!}" type="hidden">
                <tr>
                    <th width="60" class="t-center"><input id="allCheckbox1" type="checkbox"></th>
                    <th width="200">课程名称</th>
                    <th width="120">课程类型</th>
                    <th width="250">课次/场次</th>
                    <th width="150">主题</th>
                    <th width="120">名师</th>
                    <th width="120">助教</th>
                </tr>
                <#list list?if_exists as item>
                <tr>
                    <td class="t-center"><input name="courseIdsCheckbox" type="checkbox" value="${item.id!}" dataName="${item.courseName!}" /></td>
                    <td><span title="${item.courseName!}"><@cutOff cutStr="${item.courseName!}" cutLen="25" /></span></td>
                    <td>${item.contentType.nameValue!}</td>
                    <td>[${item.seq!}]  <#if item.beginTime??>${(item.beginTime?string('yyyy/MM/dd HH:mm'))!}</#if><#if item.beginTime??>-${(item.endTime?string('HH:mm'))!}</#if></td>
                    <td><span title="${item.title!}"><@cutOff cutStr="${item.title!}" cutLen="20" /></span></td>
                    <td><span title="${item.teaRealName!}"><@cutOff cutStr="${item.teaRealName!}" cutLen="15" /></span></td>
                    <td><span title="${item.assRealName!}"><@cutOff cutStr="${item.assRealName!}" cutLen="15" /></span></td>
                </tr>
                </#list>
                <#if list?has_content>
                <tr>
                    <td class="t-center"><input id="allCheckbox2" type="checkbox"></td>
                    <td colspan="6">
                        <input type="text" id="chooseUserName" class="input-txt" style="width:150px;" value="" placeholder="请选择学员" readonly disabled/>
                        <a href="javascript:;" id="chooseUser" class="abtn abtn-blue">选择学员</a>
                    </td>
                </tr>
                </#if>
            </table>
            </@doublePage2>
        </div>
          <iframe id="importStuReportFrame" name="importStuReportFrame" style="display: none;"></iframe>
    </div>
</div>
<@footer />

<#-- 课程选择弹出层 -->
<@userSelect isShowOpen=false fn="courseReport.submitReport()" />

<div class="popUp-layer layer-400" style="display:none;" id="reportInfoDiv">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>报名确认</span></p>
    <div class="wrap">
        <table class="form-table validateForm" id="reportInfoDiv_table">
        </table>
    </div>
    <p class="dd">
        <a class="abtn abtn-blue submit" href="javascript:;" id=""><span>确定</span></a>
        <a class="abtn abtn-green close ml-5" href="javascript:;"><span>取消</span></a>
    </p>
</div>

<#include "/pages/jsinclude/account/report/courseReportJs.ftl" />
<script>
    $(function(){
        courseReport.init();
    });
     function showResult(msg, isSuccess, id){
           courseReport.showResult(msg, isSuccess, id);
    }
</script>
</body>
</html>