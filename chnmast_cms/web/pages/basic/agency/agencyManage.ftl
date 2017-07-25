<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-培训基地</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<span>培训基地</span></p>
        <form class="studentManage" id="agencyForm" action="#" method="post" enctype="multipart/form-data">
        <table class="form-table validateForm mt-10">
            <tr>
                <th>基地名称：</th>
                <td> 
                    <input type="text" class="input-txt" id="agencyNameInput" name="agencyName" placeholder="输入基地名称" value="${agencyName!}"/>
                </td>
            </tr>
            <tr>
                <th>基地类型：</th>
                <td>
                    <select class="input-sel w-200" name="agencyType">
                        <option value="0">请选择</option>
                        <#list agencyTypes as type>
                            <option value="${type.value}" <#if agencyType! == type.value>selected</#if>>${type.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>基地状态：</th>
                <td>
                    <select class="input-sel w-200" name="agencyStatus">
                        <option value="999">全部</option>
                        <#list statusEunms as item>
                            <option value="${item.value}" <#if agencyStatus! == item.value>selected</#if>>${item.nameValue3!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                    <#if canAddAgency>
                    <a href="javascript:;" class="abtn abtn-green ml-10" id="newButton"><img src="../images/icon/add2.png">新增</a>
                    </#if>
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="50" class="t-center"><input id="allCheckbox1" type="checkbox"></th>
                    <th>基地名称</th>
                    <th>类型</th>
                    <th>所在地区</th>
                    <th>来源</th>
                    <th>用户名</th>
                    <th>密码</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <#list agencyList?if_exists as agency>
                <tr>
                    <td class="t-center"><input name="agencyIdsCheckbox" type="checkbox" value="${agency.id!}"/></td>
                    <td id="agencyName_${agency.id!}" title="${agency.agencyName!}"><@cutOff cutStr="${agency.agencyName!}" cutLen="30"/></td>
                    <td>${agency.agencyType.nameValue!}</td>
                    <td>${agency.regionName!}</td>
                    <td><#if agency.agencySource == 0>后台创建<#else>申请加盟</#if></td>
                    <td>${agency.username!}</td>
                    <td>${agency.password!}</td>
                    <td><#if agency.isCancel.booleanValue>注销<#else>正常</#if></td>
                    <td>
                        <#if canEditAgency>
                        <a href="${DOMAIN_CMS}/basic/editAgency.htm?agencyId=${agency.id}" title="修改">修改</a>
                        </#if>
                    </td>
                </tr>
                </#list>
                <tr>
                    <td class="t-center"><input id="allCheckbox2" type="checkbox"></td>
                    <td colspan="8">
                        <#if canResetAgency>
                        <a href="javascript:;" id="resetPasswordButton" class="abtn abtn-blue">重置密码</a>
                        </#if>
                        <#if canCancelAgency>
                        <a href="javascript:;" id="cancelButton" class="abtn abtn-blue">注销</a>
                        </#if>
                        <#if canRecoverAgency>
                        <a href="javascript:;" id="recoverButton" class="abtn abtn-blue">恢复</a>
                        </#if>
                        <#if canDelAgency>
                        <a href="javascript:;" id="removeButton" class="abtn abtn-blue">删除</a>
                        </#if>
                    </td>
                </tr>
            </table>
            </@doublePage2>
        </div>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/basic/agency/agencyManageJs.ftl" />
<script>
$(function(){
    AgencyManage.init();
});

</script>
</body>
</html>