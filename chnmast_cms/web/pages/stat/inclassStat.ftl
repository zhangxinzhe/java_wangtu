<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<script type="text/javascript" src="${DOMAIN_CMS!}/js/component/date/WdatePicker.js?${appsetting.getVersionId()!}"></script>
<title>${SITE_CMS_SITE_NAME}-上课统计</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：统计查询&nbsp;>&nbsp;<span>上课统计</span></p>
        <form class="detailForm" action="${DOMAIN_CMS}/stat/inclassStat.htm" method="post">
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>名称：</th>
                <td> 
                     <@courseSelect_new cId="courseId" cIdName="wxbLog.courseId" cIdValue="${wxbLog.courseId!}" cnId="courseName" cnName="wxbLog.courseName" cnValue="${wxbLog.courseName!}" fn="$('#searchBut').click()"/>
                </td>
            </tr>
            <tr>
                <th>课次/场次：</th>
                <td> 
                    <@courseTimeSelect couId="courseId" couTimeSeq="courseSeq" couTimeSeqName="wxbLog.seq" couTimeSeqValue="${wxbLog.seq!}" couTimeNameId="courseTimeName" couTimeName="wxbLog.courseTimeName" couTimeNameValue="${wxbLog.courseTimeName!}" fn=""/>
                </td>
            </tr>
            <tr>
                <th>学员类型：</th>
                <td> 
                    <select class="input-sel w-200" id="studentType" name="studentType">
                        <option value="">全部</option>
                        <option value="registUser" <#if studentType?exists&&studentType=="registUser">selected</#if>>注册用户</option>
                        <option value="tourUser" <#if studentType?exists&&studentType=="tourUser">selected</#if>>游客</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th></th>
                <td> 
                    <input type="checkbox" class="chk" name="queryType" <#if queryType?exists &&queryType=='notrepeat'>checked</#if> value="notrepeat"/>
                    <span>去重复</span>
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
        <#if logList?has_content>
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th class="t-center" style="width:50px;">序号</th>
                    <th>课程名称</th>
                    <th>课次/场次</th>
                    <th>姓名</th>
                    <th>用户名</th>
                    <th>类型</th>
                    <#if queryType?exists &&queryType=='notrepeat'>
                    <th>在线总时长(小时)</th>
                    <#else>
                    <th>登录时间</th>
                    <th>登出时间</th>
                    </#if>
                </tr>
                <#list logList?if_exists as info>
                <tr>
                    <td class="t-center">${info_index+1}</td>  
                    <td title="${info.courseName!}"><@cutOff cutStr="${info.courseName!}" cutLen="40"/></td>
                    <td>[${info.seq!}]${info.beginTime?string("yyyy-MM-dd HH:mm")}-${info.endTime?string("HH:mm")}</td>
                    <td>${info.realName!}</td>
                    <td>${info.userName!}</td>
                    <td>${info.courseContentType.nameValue}</td>
                    <#if queryType?exists &&queryType=='notrepeat'>
                    <td>${info.countHour?string('0.00')}</td>
                    <#else>
                    <td>${(info.loginTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                    <td>${(info.logoutTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                    </#if>
                </tr>
                </#list>  
            </table>
            </@doublePage2>
        <#else>
            <div class="public-nodata">此课程暂时没有上课记录！</div>
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
    
    function clearTimeInfo(){
        $("#courseSeq").val("");
        $("#courseTimeName").val("");
    }
</script>
</html>