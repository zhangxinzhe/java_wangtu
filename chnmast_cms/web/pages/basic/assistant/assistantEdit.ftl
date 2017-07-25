<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-助教编辑</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;>&nbsp;
        <a href="${DOMAIN_CMS}/basic/assistantManage.htm">助教管理</a>&nbsp;>&nbsp;
        <#if assistant?exists && assistant.id? exists && assistant.id!=0 >
        <span>修改</span>
        <#else>
        <span>新增</span>
        </#if>
        </p>
        <form id="assistantForm" method="post">
        
        <input type="hidden" name="assistant.id" value="${assistant.id!}"/>
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>姓名：</th>
                <td>
                    <input type="text" class="input-txt" name="assistant.realName" notNull="true" placeholder="输入姓名" maxLength="15" value="${assistant.realName!}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>用户名：</th>
                <td>
                <#if assistant?exists && assistant.id? exists && assistant.id!=0 >
                ${assistant.userName!}
                <input type="hidden" name="assistant.userName" value="${assistant.userName!}"/>
                <#else>
                <input type="text" class="input-txt" notNull="true" regex="/^[a-zA-Z]{4,20}$/" regexMsg="用户名为4-20位的英文！" maxLength="25" placeholder="输入用户名" name="assistant.userName" value="${assistant.userName!}"/>
                </#if>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>密码：</th>
                <td>
                <input type="text" class="input-txt" regex="/^[a-zA-Z0-9]{6,30}$/" regexMsg="密码为不小于6位的英文或者数字" notNull="true" oncopy="return false;" oncut="return false;" onpaste="return false" maxLength="30" name="assistant.password" value="${assistant.password!}" onkeyup="return showPwdRank(this.value);return false;"/>
                </td>
            </tr>
            <tr style="display:none;" id="levelTr">
                <th>&nbsp;</th>
                <td>
                    <div id="securityLevel" class="acc-safety acc-safety-low"><span>为保证您账号的安全性，请使用为数字+字母的组合，长度在6到20位哦！</span></div>
                </td>
            </tr>
            <tr>
                <th>备注：</th>
                <td>
                <textarea class="text-area" style="width:400px" maxLength="500" name="assistant.remark">${assistant.remark!}</textarea>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                <a href="${DOMAIN_CMS}/basic/assistantManage.htm" class="abtn abtn-green ml-10">返回</a></td>
            </tr>
        </table>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/basic/assistant/assistantEditJs.ftl" />
<script>
$(function(){
    window.AssistantEdit.init();
});

</script>
</body>
</html>