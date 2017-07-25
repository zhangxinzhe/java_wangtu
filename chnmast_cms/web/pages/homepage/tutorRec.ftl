<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-首页管理-音乐导师推荐</title>

</head>
<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<span>音乐导师推荐</span></p>
        <ul class="public-tab fn-clear mt-10">
            <li <#if isTutorRec>class="current"</#if> isRec="true">已推荐</li>
            <li <#if !isTutorRec>class="current"</#if> isRec="false">可推荐</li>
        </ul>
        
        <#if !isTutorRec>
        <form id="tutorForm" method="post" action=""/>
            <input type="hidden" name="isTutorRec" value="${isTutorRec?string('true','false')}" />
            <table class="form-table validateForm mt-10">
                <tr>
                    <th width="5%">姓名：</th>
                    <td width="20%">
                        <input type="text" name="tutorName" class="input-txt" placeholder="输入名师大家名称" value="${tutorName!}">
                    </td>
                    <td width="65%">
                        <a href="javascript:;" id="searchButton" class="abtn abtn-blue ml-5">搜索</a>
                    </td>
                </tr>
            </table>
        </form>
        </#if>
        <#if tutorList?has_content>
            <@doublePage2>
            <#if isTutorRec><form id="tableForm" method="post" action=""><input type="hidden" name="isTutorRec" value="${isTutorRec?string('false','true')}" /></#if>
            <table class="public-table">
                <tr>
                    <th width="5%" class="t-center"></th>
                    <th >姓名</th>
                    <th>类型</th>
                    <th>职业职称</th>
                    <#if isTutorRec>
                    <th>推荐时间</th>
                    <th>排序号</th>
                    </#if>
                    <th width="20%">操作</th>
                </tr>
                
                <#list tutorList as tutor>
                    <tr>
                        <td class="t-center"><#if isTutorRec><input type="checkbox" name="ids" value="${tutor.id!}"/><#else>&nbsp;</#if></td>
                        <td><span title="${tutor.realName!}"><@cutOff cutStr="${tutor.realName!}" cutLen="30" /></span></td>
                        <td>${tutor.teachType.nameValue!}</td>
                        <td>${tutor.title!}</td>
                        <#if isTutorRec>
                        <td><#if tutor.recommendTime??>${tutor.recommendTime?string('yyyy-MM-dd HH:mm:ss')!}</#if></td>
                        <td id="orderTd${tutor_index}">${tutor.recommendSeq!}</td>
                        </#if>
                        <td>
                            <#if !isTutorRec>
                                <a href="javascript:;" class="tutorRec" isReced_flag="true" dataValue="${tutor.id!}">推荐</a>
                            <#else>
                                <a href="javascript:tutorRec.modifyOneItemOrder(${tutor_index});" id="modifyA${tutor_index}" dataValue="${tutor.id!}" dataRealName="${tutor.realName!}">修改排序号</a>
                                <a href="javascript:;" class="tutorRec" isReced_flag="false" dataValue="${tutor.id!}">取消推荐</a>
                            </#if>
                        </td>
                    </tr>
                </#list>
                <#if isTutorRec>
                <tr>
                    <td class="t-center"><input type="checkbox" id="checkAll" title="全选"></td>
                    <td colspan="5">
                        <a href="javascript:;" class="abtn abtn-blue" id="cancelRec">取消推荐</a>
                    </td>
                </tr>
                </#if>
            </table>
            <#if isTutorRec></form></#if>
            </@doublePage2>
        <#else>
            <div class="record-wrap mt-10">
            <#if isTutorRec>
                <div class="public-nodata">暂时没有推荐音乐导师</div>
            <#else>
                <div class="public-nodata">暂时没有可推荐的音乐导师</div>
            </#if>
            </div>
        </#if>
    </div>
</div>
<@footer />

<#include "/pages/jsinclude/homepage/tutorRecjs.ftl"/>
<script>
    $(function(){
        tutorRec.init();
    });
</script>
</body>
</html>