<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<#include "/pages/jsinclude/system/addSystemUserjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-用户管理</title>
</head>


<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
	
    <div id="content">
    	<p class="crumbs">当前位置：系统管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/system/systemUserManage.htm">用户管理</a>&nbsp;&gt;&nbsp;<span>新增用户</span></p>
    	
    	<!--填写内容start-->
        <form method="post" id="creatForm" >
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>用户名：</th>
                <td>
                    <input type="text" id="userName" name="systemUser.userName" class="input-txt" notNull="true" msgText="用户名" regex="^\w{4,25}$" regexMsg="用户名 只能数字、字母和下划线，长度为4-25位！">
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>密码：</th>
                <td>
                    <input  type="password" id="password" name="systemUser.password" class="input-txt" notNull="true" value="" maxLength="20" msgText="密码" oncopy="return false;" oncut="return false;" onpaste="return false" onkeyup="return showPwdRank(this.value);return false;">
                </td>
            </tr>
            <tr style="display:none;" id="levelTr">
                <th>&nbsp;</th>
                <td>
                    <div id="securityLevel" class="acc-safety acc-safety-low"><span>为保证您账号的安全性，请使用为数字+字母的组合，长度在6到20位哦！</span></div>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>确认密码：</th>
                <td><input type="password" id="repassword" class="input-txt" notNull="true" msgText="确认密码" onpaste="return false">
                </td>
            </tr>
             <tr>
                <th><span class="must">*</span>姓名：</th>
                <td>
                    <input type="text" id="realName"  name="systemUser.realName" class="input-txt" notNull="true" msgText="姓名">
                </td>
            </tr>
            <tr>
                <th valign="top" class="pt-10">备注：</th>
                <td valign="top" class="pt-10">
                    <textarea class="text-area fn-left" id="remark" name="systemUser.remark" style="width:500px;"></textarea>
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
        <!--填写内容end-->
     
    </div>
</div>
<@footer />
<script type="text/javascript">
    $(function(){
        addSystemUser.init();
    });
</script>
</body>
</html>