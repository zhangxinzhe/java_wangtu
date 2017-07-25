<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<#include "/pages/jsinclude/system/updateSystemRolejs.ftl" />
<title>${SITE_CMS_SITE_NAME}-角色管理</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
	
    <div id="content">
        <!--位置start-->
    	<p class="crumbs">当前位置：系统管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/system/systemRoleManage.htm">角色管理</a>&nbsp;&gt;&nbsp;<span>角色修改</span></p>
    	
    	<!--位置end-->
        <!--树start-->
        <div id="tree" class="permission-tree mt-20">
        <@staticZTree action='system/systemRoleModuleTreeFromAll.htm?roleId=${(systemRole.id)!}' openSelect=true isShowLine=false isShowIcon=true/>
        </div>
        <!--树end-->
        <!--角色修改信息start-->
    	<form method="post" id="updateForm">
        <div class="permission-opt mt-15" id="updateDv">
            <table class="form-table validateForm mt-10">
             <tbody>
                 <tr>
                    <th><span class="must">*</span>角色名：</th>
                    <td>
                    <input type="text" name="systemRole.name" id="roleName" value="${(systemRole.name?html)!}"  class="input-txt" notNull="true">
                    </td>
                </tr>
                <tr>
                    <th valign="top" class="pt-10"><span class="must">*</span>描述：</th>
                    <td valign="top" class="pt-10">
                      <textarea class="text-area fn-left"  id="description"  name="systemRole.description" style="width:400px;" notNull="true">${(systemRole.description?html)!}</textarea>
                    </td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td>
                        <a hidefocus="true" href="javascript:;" class="abtn abtn-blue" id="saveBtn">确认</a>
                        <a hidefocus="true" href="javascript:;" id="cancelBtn" class="abtn abtn-blue">取消</a>
                    </td>
                </tr>
             </tbody>
            </table>
            <input type="hidden" name="mods" id="mod"  value="">
            <input name="systemRole.id" id="roleId" type="hidden" value="${(systemRole.id?html)!}"/>
     </form>
        </div>
        <!--角色修改信息end-->
    </div>
</div>
<@footer />
<script>
    $(function(){
        updateSystemRole.init();
    });
</script>
</body>
</html>