<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-首页管理-培训基地推荐</title>

</head>
<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<span>培训基地推荐</span></p>
        <ul class="public-tab fn-clear mt-10">
            <li <#if isAgencyRec>class="current"</#if> isRec="true">已推荐</li>
            <li <#if !isAgencyRec>class="current"</#if> isRec="false">可推荐</li>
        </ul>
        
        <#if !isAgencyRec>
        <form id="agencyForm" method="post" action=""/>
            <input type="hidden" name="isAgencyRec" value="${isAgencyRec?string('true','false')}" />
            <table class="form-table validateForm mt-10">
                <tr>
                    <th width="10%">基地名称：</th>
                    <td width="20%">
                        <input type="text" name="agencyName" class="input-txt" placeholder="输入培训基地名称" value="${agencyName!}">
                    </td>
                    <th width="10%">基地类型：</th>
                    <td width="60%">
                        <select class="input-sel" name="agencyType">
                            <option value="0">--请选择--</option>
                            <#list agencyTypes as type>
                                <option value="${type.value}" <#if agencyType! == type.value>selected</#if>>${type.nameValue!}</option>
                            </#list>
                        </select>
                        <a href="javascript:;" id="searchButton" class="abtn abtn-blue ml-5">搜索</a>
                    </td>
                </tr>
            </table>
        </form>
        </#if>
        <#if agencyList?has_content>
            <@doublePage2>
            <#if isAgencyRec><form id="tableForm" method="post" action=""><input type="hidden" name="isAgencyRec" value="${isAgencyRec?string('false','true')}" /></#if>
            <table class="public-table">
                <tr>
                    <th width="50" class="t-center"></th>
                    <th>基地名称</th>
                    <th>类型</th>
                    <th>所在地区</th>
                    <#if isAgencyRec><th>推荐时间</th></#if>
                    <th width="80">操作</th>
                </tr>
                
                <#list agencyList as agency>
                    <tr>
                        <td class="t-center"><#if isAgencyRec><input type="checkbox" name="ids" value="${agency.id!}"/><#else>&nbsp;</#if></td>
                        <td><span title="${agency.agencyName!}"><@cutOff cutStr="${agency.agencyName!}" cutLen="30" /></span></td>
                        <td>${agency.agencyType.nameValue!}</td>
                        <td>${agency.regionName!}</td>
                        <#if isAgencyRec><td><#if agency.recommendTime??>${agency.recommendTime?string('yyyy-MM-dd HH:mm:ss')!}</#if></td></#if>
                        <td>
                            <#if !isAgencyRec>
                                <a href="javascript:;" class="agencyRec" isReced_flag="true" dataValue="${agency.id!}">推荐</a>
                            <#else>
                                <a href="javascript:;" class="agencyRec" isReced_flag="false" dataValue="${agency.id!}">取消推荐</a>
                            </#if>
                        </td>
                    </tr>
                </#list>
                <#if isAgencyRec>
                <tr>
                    <td class="t-center"><input type="checkbox" id="checkAll" title="全选"></td>
                    <td colspan="6">
                        <a href="javascript:;" class="abtn abtn-blue" id="cancelRec">取消推荐</a>
                    </td>
                </tr>
                </#if>
            </table>
            <#if isAgencyRec></form></#if>
            </@doublePage2>
        <#else>
            <div class="record-wrap mt-10">
            <#if isAgencyRec>
                <div class="public-nodata">暂时没有推荐培训基地</div>
            <#else>
                <div class="public-nodata">暂时没有可推荐的培训基地</div>
            </#if>
            </div>
        </#if>
    </div>
</div>
<@footer />

<#include "/pages/jsinclude/homepage/agencyRecjs.ftl"/>
<script>
    $(function(){
        agencyRec.init();
    });
</script>
</body>
</html>