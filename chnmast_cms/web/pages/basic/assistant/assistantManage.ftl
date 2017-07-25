<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-助教管理</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据管理&nbsp;>&nbsp;<span>助教管理</span></p>
        <form class="assistantManage" action="${DOMAIN_CMS}/basic/assistantManage.htm" method="post">
        <table class="form-table validateForm mt-10">
            <tr>
                <th>姓名：</th>
                <td> 
                    <input type="text" class="input-txt" id="assistantName" name="assistantName" placeholder="输入查询姓名" value="${assistantName!}"/>
                </td>
            </tr>
            <tr>
                <th>状态：</th>
                <td>
                    <select class="input-sel w-200" name="isCancel">
                        <#list userState?if_exists as t>
                        <option value="${t}" <#if isCancel?exists && isCancel==t>selected</#if> >${t.nameValue3!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                    <#if canAddAssistant>
                    <a href="javascript:;" class="abtn abtn-green ml-10" id="newButton"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                    </#if>
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <#if assistants?exists && assistants.size() gt 0 >
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="50"></th>
                    <th width="30%">姓名</th>
                    <th width="10%">状态</th>
                    <th width="20%">用户名</th>
                    <th width="20%">密码</th> 
                    <th >操作</th>
                </tr>
                <#list assistants?if_exists as info>
                <tr>
                    <td class="t-center"><input class="check_item" name="ids" type="checkbox" value="${info.id!}"/></td>
                    <td>${info.realName!}</td>
                    <td>
                        <#if info.isCancel=='NORMAL'>
                        ${info.isCancel.nameValue!}
                        <#else>
                        <span class="c-red">${info.isCancel.nameValue3!}</span>
                        </#if>
                    </td>
                    <td>${info.userName!}</td>
                    <td>${info.password!}</td>
                    <td>
                        <#if canEditAssistant>
                        <a href="${DOMAIN_CMS}/basic/assistantEdit.htm?assistantId=${info.id!}">修改</a>
                        </#if>
                        <#if canRecoverAssistant>
                            <#if info.isCancel=='NORMAL'>
                            <a href="javascript:;" class="assistant_cancel" val="${info.id}" >注销</a>
                            </#if>
                            <#if info.isCancel=='CANCEL'>
                            <a href="javascript:;" class="assistant_recover" val="${info.id}">恢复</a>
                            </#if>
                        </#if>
                    </td>
                </tr>
                </#list>
                 <tr>
                    <td class="t-center"><input id="check_all_assist" type="checkbox"></td>
                    <td colspan="5">
                        <#if canDeleteAssistant>
                        <a href="javascript:;" id="delete_assistant_btn" class="abtn abtn-blue">删除</a>
                        </#if>
                    </td>
                </tr>
                  
            </table>
            </@doublePage2>
            <#else>
                <div class="public-nodata">没有数据哦...</div>
            </#if>
        </div>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/basic/assistant/assistantManageJs.ftl" />
<script>
$(function(){
    window.AssistantManage.init();
});

</script>
<form method="psot" id="delete_form">
</form>
</body>
</html>