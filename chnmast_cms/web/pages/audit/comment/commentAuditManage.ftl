<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-审核管理</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=1250000 curSubModId=1250400 />
    
    <div id="content">
        <p class="crumbs">当前位置：审核管理&nbsp;&gt;&nbsp;<span>评论审核</span></p>
        <input type="hidden" class="input-txt" id="type" value="${type!}" />
        <form class="commentAuditManage" id="commentAuditForm" action="#" method="post">
        <ul class="public-tab fn-clear mt-10">
            <li id="course_tab" <#if type==1 >class="current"</#if>>课程评论</li>
            <li id="video_tab" <#if type==2 >class="current"</#if>>回顾视频评论</li>
        </ul>
        <table class="form-table validateForm mt-10">
            <tr>
                <th>课程名称：</th>
                    <td> 
                        <@courseSelect_new cId="courseId" cIdName="courseId" cIdValue="${courseId!}" cnId="courseName" cnName="courseName" cnValue="${courseName!}" fn="$('#searchTime').click()"/>
                    </td>
                </tr>
            <tr>
                <th>评论时间：</th>
                <td> 
                    <input type="text" class="input-txt fn-left t-80" notNull="true" id="beginTime" name="beginTime" value="${(beginTime?string('yyyy-MM-dd'))!}" onclick="$('#btwp').click();" readonly/>
                    <a hidefocus="true" href="javascript:;" id="btwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'beginTime',isShowClear:true,readOnly:true,isShowWeek:true,maxDate:'#F{$dp.$D(\'endTime\')}'})"></a>                            
                    <span class="fn-left mt-5 ml-10">至</span>
                    <input type="text" class="input-txt fn-left ml-10 t-80" notNull="true" id="endTime" name="endTime" value="${(endTime?string('yyyy-MM-dd'))!}" onclick="$('#etwp').click();" readonly/>
                    <a hidefocus="true" href="javascript:;" id="etwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'endTime',isShowClear:true,readOnly:true,isShowWeek:true,minDate:'#F{$dp.$D(\'beginTime\')}'})"></a>
                </td>
            </tr>
            <tr>
                <th>审核状态：</th>
                <td>
                    <select class="input-sel w-200" name="comment.auditStatus">
                        <option value="">全部</option>
                        <#list commentAuditStatus?if_exists as t>
                            <option value="${t!''}" <#if (comment.auditStatus)?? && comment.auditStatus==t>selected</#if>>${t.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="searchBtn">搜索</a>
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th class="t-center" width="50"></th>
                    <th>课程名字</th>
                    <th class="t-center">主讲人</th>
                    <th class="t-center">评论人</th>
                    <th>评论内容</th>
                    <#--
                    <th class="t-center">评论人IP</th>
                    -->
                    <th class="t-center">评分</th>
                    <th class="t-center">审核状态</th>
                    <th class="t-center">操作</th>
                </tr>
                <#if courseComments?has_content>
                <#list courseComments as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td><span title="${(item.courseName)!''}"><@cutOff cutStr="${(item.courseName)!''}" cutLen="20"/></span></td>
                    <td class="t-center">${(item.speakerName)!''}</td>
                    <td class="t-center">${(item.obsName)!''}</td>
                    <td><span title="${(item.content)!''}"><@cutOff cutStr="${(item.content)!''}" cutLen="36"/></span></td>
                    <#--<td class="t-center">${(item.ip)!''}</td>-->
                    <td class="t-center">${(item.score)!''}</td>
                    <td class="t-center">
                        <#if item.auditStatus == 'AUDITING'><span class="c-red">${(item.auditStatus.nameValue)!''}</span><#else>${(item.auditStatus.nameValue)!''}</#if>
                    </td>
                    <td class="t-center">
                        <a href="${DOMAIN_CMS}/audit/comment/auditComment.htm?id=${(item.id)!'0'}" title="查看">查看审核</a>
                    </td>
                </tr>
                </#list>
                </#if>
            </table>
            </@doublePage2>
        </div>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/audit/comment/commentAuditManageJs.ftl" />
<script>
$(function(){
    commentAuditManage.init();
});
</script>
</body>
</html>