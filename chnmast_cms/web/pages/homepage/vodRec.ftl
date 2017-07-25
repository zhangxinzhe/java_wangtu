<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-首页管理-点播视频推荐</title>

</head>
<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<span>点播视频推荐</span></p>
        <ul class="public-tab fn-clear mt-10">
            <li <#if isVodRec>class="current"</#if> operate="reced">已推荐</li>
            <li <#if !isVodRec>class="current"</#if> operate="noRec">可推荐</li>
        </ul>
        
        <#if !isVodRec>
        <form id="vodForm" method="post" action=""/>
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
            <#if isVodRec><form id="tableForm" method="post" action=""><input type="hidden" name="isVodRec" value="${isVodRec?string('false','ture')}" /></#if>
            <table class="public-table">
                <tr>
                    <th width="50" class="t-center"></th>
                    <th>课程名称</th>
                    <th>开始时间 </th>
                    <th>名师</th>
                    <th>助教</th>
                    <#if isVodRec>
                    <th>推荐时间</th>
                    <th>排序号</th>
                    </#if>
                    <th>操作</th>
                </tr>
                
                <#list list as vod>
                    <tr>
                        <td class="t-center"><#if isVodRec><input type="checkbox" name="ids" value="${vod.id!}"/><#else>&nbsp;</#if></td>
                        <td><span title="${vod.courseName!}"><@cutOff cutStr="${vod.courseName!}" cutLen="30" /></span></td>
                        <td><#if vod.beginTime??>${vod.beginTime?string('yyyy-MM-dd HH:mm:ss')!}</#if></td>
                        <td>${vod.teaRealName!}</td>
                        <td>${vod.assRealName!}</td>
                        <#if isVodRec>
                        <td><#if vod.recommendTime??>${vod.recommendTime?string('yyyy-MM-dd HH:mm:ss')!}</#if></td>
                        <td id="orderTd${vod_index}">${vod.recommendSeq!}</td>
                        </#if>
                        <td>
                            <#if !isVodRec>
                                <a href="javascript:;" class="vodRec" isReced_flag="true" dataValue="${vod.id!}" dataName="${vod.courseName!}">推荐</a>
                            <#else>
                                <a href="javascript:vodRec.modifyOneItemOrder(${vod_index});" id="modifyA${vod_index}" dataValue="${vod.id!}" dataCourseName="${vod.courseName!}">修改排序号</a>
                                <a href="javascript:;" class="vodRec" isReced_flag="false" dataValue="${vod.id!}" dataName="${vod.courseName!}">取消推荐</a>
                            </#if>
                        </td>
                    </tr>
                </#list>
                <#if isVodRec>
                <tr>
                    <td class="t-center"><input type="checkbox" id="checkAll" title="全选"></td>
                    <td colspan="6">
                        <a href="javascript:;" class="abtn abtn-blue" id="cancelRec">取消推荐</a>
                    </td>
                </tr>
                </#if>
            </table>
            <#if isVodRec></form></#if>
            </@doublePage2>
        <#else>
            <div class="record-wrap mt-10">
            <#if isVodRec>
                <div class="public-nodata">暂时没有推荐课程</div>
            <#else>
                <div class="public-nodata">暂时没有可推荐的课程</div>
            </#if>
            </div>
        </#if>
    </div>
</div>
<@footer />

<#include "/pages/jsinclude/homepage/vodRecjs.ftl"/>
<script>
    $(function(){
        vodRec.init();
    });
</script>
</body>
</html>