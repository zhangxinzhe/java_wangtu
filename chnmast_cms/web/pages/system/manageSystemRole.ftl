<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<title>${SITE_CMS_SITE_NAME}-角色管理</title>
<#include "/pages/jsinclude/commonjs.ftl" />
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
	
    <div id="content">
    	<p class="crumbs">当前位置：系统管理&nbsp;&gt;&nbsp;<span>角色管理</span></p>
    	
    	<!--搜索显示内容start-->
                   <#if canAddRole>
                    <a href="${DOMAIN_CMS}/system/addSystemRole.htm" class="abtn abtn-green ml-10 mt-10"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                   </#if>
        <!--搜索显示内容end-->
        <!--结果显示内容start-->
      <div class="record-wrap mt-10">
        <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="50" class="t-center"></th>
                    <th>序号</th>
                    <th>角色名</th>
                    <th>类型</th>
                    <th>创建日期</th>
                    <th>操作</th>
                </tr>
                <#list systemRoles?if_exists  as systemRole>
                <tr>
                    <td class="t-center"></td>
                    <td>${systemRole_index+1}</td>
                    <td>${(systemRole.name?html)!}</td>
                    <td>${systemRole.roleType.nameValue!}</td>
                    <td>${systemRole.createTime?string('yyyy-MM-dd')}</td>
                    <td>
                       <#if canShowRole>
                          <a href="${DOMAIN_CMS}/system/showSystemRole.htm?roleId=${systemRole.id!}">查看</a>
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