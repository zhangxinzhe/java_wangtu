<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-首页管理-音乐会推荐</title>
</head>
<body>
<@header curAppId=appId/>
<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<span>音乐会推荐</span></p>
        <ul class="public-tab fn-clear mt-10">
            <li <#if isReced>class="current"</#if> id="recedLi" >已推荐</li>
            <li <#if !isReced>class="current"</#if> id="recLi">可推荐</li>
        </ul>
        
        <#if !isReced>
        <form id="concertForm" method="post" action=""/>
            <table class="form-table validateForm mt-10">
                <tr>
                    <th width="10%">音乐会名称：</th>
                    <td width="90%">
                        <input type="text" name="concertName" class="input-txt" placeholder="输入音乐会名称" value="${concertName!}">
                        <a href="javascript:;" id="searchButton" class="abtn abtn-blue ml-5">搜索</a>
                    </td>
                </tr>
            </table>
        </form>
        </#if>
        
        <#if concertList?has_content>
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="50" class="t-center"></th>
                    <th>音乐会名称</th>
                    <th>开始时间 </th>
                    <th>名师</th>
                    <#if isReced>
                    <th>推荐时间</th>
                    <th>排序号</th>
                    </#if>
                    <th>操作</th>
                </tr>
                <#list concertList as concert>
                    <tr>
                        <td class="t-center"><#if isReced><input type="checkbox" name="ids" id="id${concert_index}" value="${concert.id!}"/><#else>&nbsp;</#if></td>
                        <td><span title="${concert.courseName!}"><@cutOff cutStr="${concert.courseName!}" cutLen="30" /></span></td>
                        <td><#if concert.beginTime??>${concert.beginTime?string('yyyy-MM-dd HH:mm:ss')!}</#if></td>
                        <td>${concert.teaRealName!}</td>
                        <#if isReced>
                        <td><#if concert.recommendTime??>${concert.recommendTime?string('yyyy-MM-dd HH:mm:ss')!}</#if></td>
                        <td id="orderTd${concert_index}">${concert.recommendSeq!}</td>
                        </#if>
                        <td>
                            <#if !isReced>
                                <a href="javascript:concertRec.recOneItem(${concert.id!},'${concert.courseName!}');">推荐</a>
                            <#else>
                                <a href="javascript:concertRec.modifyOneItemOrder(${concert_index});" id="modifyA${concert_index}" dataValue="${concert.id!}" dataCourseName="${concert.courseName!}">修改排序号</a>
                                <a href="javascript:concertRec.cancleRecOneItem(${concert.id!},'${concert.courseName!}');">取消推荐</a>
                            </#if>
                        </td>
                    </tr>
                </#list>
                <#if isReced>
                <tr>
                    <td class="t-center"><input type="checkbox" id="checkAll" title="全选"></td>
                    <td colspan="6">
                        <a href="javascript:concertRec.cancleRecBatch();" class="abtn abtn-blue" id="cancelRec">取消推荐</a>
                    </td>
                    <form id="batchForm" action="" method="post">
                       <input name="isReced" type="hidden" value="true"/>
                    </form>
                </tr>
                </#if>
            </table>
            </@doublePage2>
        <#else>
            <div class="record-wrap mt-10">
            <#if isReced>
                <div class="public-nodata">暂时没有推荐音乐会</div>
            <#else>
                <div class="public-nodata">暂时没有可推荐的音乐会</div>
            </#if>
            </div>
        </#if>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/concertRecjs.ftl"/>
<script>
$(function(){
    concertRec.init();
});
</script>
</body>
</html>