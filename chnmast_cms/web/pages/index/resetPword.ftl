<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<#include "/pages/jsinclude/index/resetPwordjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-修改密码</title>

</head>


<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">
                           当前位置：修改密码
        </p>
        <!--填写内容start-->
        <form method="post" id="passwordForm" >
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>旧密码：</th>
                <td>
                    <input type="password" id="oldPassword"  name="oldPassword" class="input-txt" notNull="true" value="" maxlength="20" msgText="旧密码">
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>新密码：</th>
                <td>
                    <input  type="password" id="newPassword" name="newPassword" class="input-txt" notNull="true" value="" maxLength="20" msgText="新密码" oncopy="return false;" oncut="return false;" onpaste="return false" onkeyup="return showPwdRank(this.value);return false;">
                </td>
            </tr>
            <tr style="display:none;" id="levelTr">
                <th>&nbsp;</th>
                <td>
                    <div id="securityLevel" class="acc-safety acc-safety-low"><span>为保证您账号的安全性，请使用为数字+字母的组合，长度在6到20位哦！</span></div>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>确认新密码：</th>
                <td><input type="password" id="reNewPassword"  class="input-txt" notNull="true" msgText="确认新密码" maxLength="20" onpaste="return false">
                </td>
            </tr>
             <tr>
                <th>&nbsp;</th>
                <td>
                <a class="abtn abtn-blue submit" href="javascript:;" id="saveBtn"><span>保存</span></a>
                </td>
            </tr>
        </table>
        </form>
        <!--填写内容end-->
     
    </div>
</div>
<@footer />
</body>
<script type="text/javascript">
$(function(){
        resetPword.init();
    });
</script>
</html>