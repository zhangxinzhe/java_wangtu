<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<#include "/pages/jsinclude/system/addSystemRolejs.ftl" />
<title>${SITE_CMS_SITE_NAME}-用户管理</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
	
    <div id="content">
        <!--位置start-->
    	<p class="crumbs">当前位置：系统管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/system/systemRoleManage.htm">角色管理</a>&nbsp;&gt;&nbsp;<span>添加角色</span></p>
    	
    	<!--位置end-->
        <!--树start-->
        <div class="permission-tree mt-20">
        <@staticZTree action="system/systemModuleTree.htm" openSelect=true isShowLine=false isShowIcon=true/>
        </div>
        <!--树end-->
        <!--用户信息start-->
        <div class="permission-opt mt-15">
            <form method="post" id="creatForm">
                <input type="hidden" name="mods" id="mod" value="">
                <table class="form-table validateForm mt-10">
                    <tr>
                        <th><span class="must">*</span>角色名：</th>
                        <td>
                            <input type="text" name="systemRole.name" id="roleName" class="input-txt" notNull="true" />
                        </td>
                    </tr>
                    <tr>
                        <th valign="top" class="pt-10"><span class="must">*</span>备注：</th>
                        <td valign="top" class="pt-10">
                            <textarea class="text-area fn-left" id="description" name="systemRole.description" style="width:350px;" notNull="true"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>&nbsp;</th>
                        <td>
                            <a class="abtn abtn-blue submit" href="javascript:;" id="saveBtn"><span>保存</span></a>
                            <a class="abtn abtn-green reset ml-5" href="javascript:;" id="backBtn"><span>返回</span></a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <!--用户信息end-->
    </div>
</div>
<@footer />
<script type="text/javascript">
    $(function(){
        addSystemRole.init();
    });
</script>
</body>
</html>