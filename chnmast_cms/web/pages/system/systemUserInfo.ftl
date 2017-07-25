<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<#include "/pages/jsinclude/system/systemUserInfojs.ftl" />
<title>${SITE_CMS_SITE_NAME}-用户管理</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
	
    <div id="content">
        <!--位置start-->
    	<p class="crumbs">当前位置：系统管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/system/systemUserManage.htm">用户管理</a>&nbsp;&gt;&nbsp;<span>用户信息</span></p>
    	
    	<!--位置end-->
        <!--树start-->
        <div class="permission-tree mt-20">
        <@staticZTree action="system/systemUserModuleTree.htm?userId=${systemUser.id!}"  isShowLine=false isShowIcon=true/>
        </div>
        <!--树end-->
        <!--用户信息start-->
        <div class="permission-opt mt-15" id="showDv">
            <table class="form-table validateForm mt-10">
                    <tbody><tr>
                        <th>用户名：</th>
                        <td>${systemUser.userName!}</td>
                    </tr>
                    <tr>
                        <th>姓名：</th>
                        <td>${systemUser.realName!}</td>
                    </tr>
                    <tr>
                        <th>拥有角色：</th>
                        <td>
                        <#list systemRoles as systemRole>
                        <span class="font01 mRight15">${systemRole.name!}</span>
                        </#list>
                        </td>
                    </tr>
                   <tr>
                        <th class="pt-10" valign="top">备注：</th>
                        <td class="pt-10" valign="top">
                        <textarea  disabled="disabled" class="text-area fn-left" style="width:400px;">${(systemUser.remark?html)!}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>&nbsp;</th>
                        <td>
                        
                        <#if systemUser.userType.value!=1 && systemUser.userType.value!=3>
                            <#if canAssignRoleUser && systemUser.isFreeze.value==0>
                            <a hidefocus="true" href="javascript:;" id="assignBtn" class="abtn abtn-blue">分配权限</a>
                            </#if>
                            <#if canUpdateUser>
                            <a hidefocus="true" href="javascript:;" id="updateBtn" class="abtn abtn-blue">修改</a>
                            </#if>
                            <#if canDelUser && !systemUser.isFreeze.yes()>
                            <a hidefocus="true" href="javascript:;" id="deleteBtn" class="abtn abtn-blue">删除</a>
                            </#if>
                            <#if systemUser.isFreeze.yes() && canUnFreezeUser>
                            <a hidefocus="true" href="javascript:;" id="unfreezeBtn" class="abtn abtn-blue">解冻</a>
                            <#elseif canFreezeUser>
                            <a hidefocus="true" href="javascript:;" id="freezeBtn" class="abtn abtn-blue">冻结</a>
                            </#if>
                         </#if>
                         <a hidefocus="true" href="javascript:;" id="backBtn" class="abtn abtn-blue">返回</a>
                        </td>
                    </tr>
                </tbody></table>
        </div>
        <!--用户信息end-->
        <!--隐藏用户修改信息start-->
        <div class="permission-opt mt-15" id="updateDv" style="display:none">
        <form method="post" id="updateForm" >
            <table class="form-table validateForm mt-10">
            <input id="userid" name="systemUser.id" type="hidden" value="${systemUser.id!}" />
             <tbody>
                 <tr>
                    <th><span class="must">*</span>用户名：</th>
                    <td>
                    <input type="text" id="userName" value="${systemUser.userName!}"  name="systemUser.userName" class="input-txt" notNull="true" msgText="用户名" regex="^\w{4,25}$" regexMsg="用户名 只能数字、字母和下划线，长度为4-25位！">
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>姓名：</th>
                    <td>
                     <input type="text" value="${systemUser.realName!}" id="realName" name="systemUser.realName" class="input-txt" notNull="true" msgText="姓名">
                    </td>
                </tr>
                <tr>
                    <th valign="top" class="pt-10">备注：</th>
                    <td valign="top" class="pt-10">
                      <textarea class="text-area fn-left" id="remark" name="systemUser.remark" style="width:400px;">${(systemUser.remark?html)!}</textarea>
                    </td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td>
                        <a hidefocus="true" href="javascript:;" class="abtn abtn-blue" id="saveBtn">确认</a>
                        <#if systemUser.id!=user.id>
                        <a hidefocus="true" href="javascript:;" id="resetBtn" class="abtn abtn-blue">重置密码</a>
                        </#if>
                        <a hidefocus="true" href="javascript:;" id="cancelBtn" class="abtn abtn-blue">取消</a>
                    </td>
                </tr>
             </tbody>
            </table>
            </form>
        </div>
        <!--隐藏用户修改信息end-->
    </div>
</div>

<!--权限角色弹出层start-->
<div class="popUp-layer" id="selectLayer" style="display:none;">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>提示</span></p>
    <div class="wrap fn-clear" style="width:610px;">
        <div class="org-wrap">
            <div class="org-tt">角色：</div>
            <ul class="org-list org-list-left" id="leftUl">
            </ul>
        </div>
        <div class="org-opt">
            <a href="javascript:;" class="abtn abtn-blue" id="addAllBtn">全部添加</a>
            <a href="javascript:;" class="abtn abtn-blue" id="removeAllBtn">全部移除</a>
        </div>
        <div class="org-wrap">
            <div class="org-tt">选中的角色：</div>
            <form method="post" id="assignRoleForm" action="${DOMAIN_CMS}/system/assignSystemRole.htm" >
             <input  name="userId" type="hidden" value="${systemUser.id!}" />
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
        systemUserInfo.init();
    });
</script>
</body>
</html>