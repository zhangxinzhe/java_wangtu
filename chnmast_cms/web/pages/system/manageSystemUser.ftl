<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-用户管理</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
	
    <div id="content">
    	<p class="crumbs">当前位置：系统管理&nbsp;&gt;&nbsp;<span>用户管理</span></p>
    	<!--搜索显示内容start-->
        <form method="post" id="searchForm" action="${DOMAIN_CMS}/system/systemUserManage.htm" >
        <table class="form-table validateForm mt-10">
            <tr>
                <th>姓名：</th>
                <td>
                    <input type="text" id="realname" name="realname" value="${realname!}"  maxlength="20" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>用户名：</th>
                <td>
                    <input type="text" id="username" name="username" value="${username!}" maxlength="20" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td colspan="3">
                    <a href="javascript:;" id="search" class="abtn abtn-blue">搜索</a>
                   <#if canAddUser>
                    <a href="${DOMAIN_CMS}/system/addSystemUser.htm" class="abtn abtn-green ml-5"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                   </#if>
                </td>
            </tr>
        </table>
        </form>
        <!--搜索显示内容end-->
        <!--结果显示内容start-->
      <div class="record-wrap mt-10">
        <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="50" class="t-center"></th>
                    <th>序号</th>
                    <th>用户名</th>
                    <th>姓名</th>
                    <th>角色</th>
                    <th>创建日期</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <#list systemUsers?if_exists  as systemUser>
                <tr>
                    <td class="t-center"></td>
                    <td>${systemUser_index+1}</td>
                    <td>${systemUser.userName!}</td>
                    <td>${systemUser.realName!}</td>
                    <td>${systemUser.rolename!}</td>
                    <td>${systemUser.createTime?string('yyyy-MM-dd')}</td>
                    <td><#if systemUser.isFreeze.yes()>冻结<#elseif systemUser.isDeleted.yes()>删除<#else>正常</#if></td>
                    <td>
                       <#if canEditUser>
                          <a href="${DOMAIN_CMS}/system/viewSystemUser.htm?userId=${systemUser.id!}">查看</a>
                        </#if>
                    </td>
                </tr>
                </#list>
            </table>
        </@doublePage2>
      </div>
        <!--结果显示内容end-->
    </div>
</div>
<@footer />
<script>
    $('#search').bind('click',function(){
      $('#searchForm').submit();
    });
</script>
</body>
</html>