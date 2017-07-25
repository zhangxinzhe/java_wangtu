<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<#include "/pages/jsinclude/system/systemRoleInfojs.ftl" />
<title>${SITE_CMS_SITE_NAME}-角色管理</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
	
    <div id="content">
        <!--位置start-->
    	<p class="crumbs">当前位置：系统管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/system/systemRoleManage.htm">角色管理</a><span>角色信息</span></p>
    	
    	<!--位置end-->
        <!--树start-->
        <div id="tree" class="permission-tree mt-20">
        <@staticZTree action="system/systemRoleModuleTree.htm?roleId=${(systemRole.id)!}" isShowLine=false isShowIcon=true/>
        </div>
        <!--树end-->
        <!--用户信息start-->
        <div class="permission-opt mt-15" id="showDv">
        <input  id="roleId" type="hidden" value="${(systemRole.id?html)!}"/>
            <table class="form-table validateForm mt-10">
                    <tbody><tr>
                        <th>角色名：</th>
                        <td>${(systemRole.name?html)!}</td>
                    </tr>
                   <tr>
                        <th class="pt-10" valign="top">描述：</th>
                        <td class="pt-10"  valign="top">
                        <textarea disabled="disabled" class="text-area fn-left" style="width:400px;">${(systemRole.description?html)!}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>&nbsp;</th>
                        <td>
                        <#if (systemRole.roleType.value) != 1 && (systemRole.roleType.value) != 3>
                        <#if canAssignUserRole>
                            <a hidefocus="true" href="javascript:;" id="assignBtn" class="abtn abtn-blue">分配用户</a>
                        </#if>
                        <#if canUpdateRole>
                            <a hidefocus="true" href="javascript:;" id="updateBtn" class="abtn abtn-blue">修改</a>
                        </#if>
                        </#if>
                        <#if canDeleteRole>
                            <a hidefocus="true" href="javascript:;" id="deleteBtn" class="abtn abtn-blue">删除</a>
                        </#if>
                            <a hidefocus="true" href="javascript:;" id="backBtn" class="abtn abtn-blue">返回</a>
                        </td>
                    </tr>
                </tbody></table>
        </div>
        <!--用户信息end-->
    </div>
</div>

<!--权限角色弹出层start-->
<div class="popUp-layer" id="selectLayer" style="display:none;">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>提示</span></p>
    <div class="wrap fn-clear" style="width:610px;">
        <div class="org-wrap">
            <div class="org-tt">用户：</div>
            <ul class="org-list org-list-left" id="leftUl">
            </ul>
        </div>
        <div class="org-opt">
            <a href="javascript:;" class="abtn abtn-blue" id="addAllBtn">全部添加</a>
            <a href="javascript:;" class="abtn abtn-blue" id="removeAllBtn">全部移除</a>
        </div>
        <div class="org-wrap">
            <div class="org-tt">已分配的用户：</div>
            <form method="post" id="assignUserForm" action="${DOMAIN_CMS}/system/assignSystemUser.htm" >
             <input  name="roleId" type="hidden" value="${(systemRole.id?html)!}" />
            <ul class="org-list org-list-right" id="rightUl">
            </ul>
            </form>
        </div>
    </div>
    <p class="dd">
        <a class="abtn abtn-blue submit" href="javascript:;"><span>确定</span></a>
        <a class="abtn abtn-green reset ml-5" href="javascript:;"><span>取消</span></a>
    </p>
</div>
<!--权限角色弹出层end-->
<@footer />
<script>
$(function(){
        systemRoleInfo.init();
    });
</script>
</body>
</html>