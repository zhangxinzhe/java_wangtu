<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-个人用户</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=1200000 curSubModId=1200300 />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/studentManage.htm">个人用户</a>&nbsp;&gt;&nbsp;<span>新增用户</span></p>
        <form id="studentForm" method="post" action="${DOMAIN_CMS}/basic/studentAdd.htm">
        <input type="hidden" style="display:none"><!-- 解决火狐和谷歌浏览器自动填充用户名和密码问题 -->
        <input type="password" style="display:none"><!-- 解决火狐和谷歌浏览器自动填充用户名和密码问题 -->
        <input type="hidden" name="student.id"  value="${(student.id)!'0'}" />
            <div class="userInfo-wrap">
                <table class="form-table validateForm mt-10">
                  <tr>
                    <th><span class="must">*</span>姓名：</th>
                    <td>
                        <input type="text" class="input-txt" id="nameInput" name="student.realName" notNull="true" maxlength="25" placeholder="输入用户姓名" value="${student.realName!}"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>手机号码：</th>
                    <td>
                        <input type="text" class="input-txt" id="phoneInput" name="student.phone" notNull="true" readonly maxlength="25" placeholder="输入手机号码" value="${student.phone!}"/>
                    </td>
                </tr>
                   <tr>
                    <th>性别：</th>
                    <td>
                        <select class="input-sel w-200" id="sex" name="student.sex">
                            <#list sexTypes?if_exists as sex>
                                <option value="${sex}" <#if student?? && student.sex?? && sex == student.sex>selected="selected"</#if>>${sex.nameValue!}</option>
                            </#list>
                        </select>
                    </td>
                </tr>
                 <tr>
                    <th>绑定QQ：</th>
                    <td>
                        <input type="text" class="input-txt" id="qqInput" name="student.qq"  maxlength="25"  value="${student.qq!}"/>
                    </td>
                </tr>
                <tr>
                    <th>绑定Email：</th>
                    <td>
                        <input type="text" class="input-txt" id="emailInput" name="student.email"  maxlength="25"  value="${student.email!}"/>
                    </td>
                </tr>
                <tr>
                    <th>分组类型：</th>
                    <td>
                        <div class="select-analog fn-left">
                            <input type="hidden" id="groupTypeId" name="student.groupTypeId" value="${student.groupTypeId!}" />
                            <input type="text" class="input-txt" id="groupTypeTitle" name="student.groupTypeTitle" value="${student.groupTypeTitle!}" readOnly/>
                            <a href="javascript:;" class="open selectType"></a>
                        </div>
                        <span class="onTips fn-left ml-5" style="line-height:26px">选择类型</span>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>密码：</th>
                    <td>
                        <input class="input-txt"  id="passwordInput" name="student.password" value="${student.password!}" type="password" notNull="true" maxlength="20" oncopy="return false;" oncut="return false;" onpaste="return false" onkeyup="return showPwdRank(this.value);return false;">
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
                    <td><input class="input-txt" id="repassword" type="password" notNull="true" maxlength="20" value="${student.password!}"></td>
                </tr>
                        <th>&nbsp;</th>
                        <td>
                            <a href="javascript:;" id="saveBtn" class="abtn abtn-blue " dataValue="${student.id!}" flag="0">保存</a>
                            <a href="${DOMAIN_CMS}/basic/studentManage.htm" class="abtn abtn-green">返回</a>
                        </td>
                    </tr>
                </table>
            </div>
     </form>
    </div>
</div>
<@footer />
<#-- 选择分类弹出层 -->
<div class="popUp-layer" id="selectTypeDiv" style="display:none;">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>选择分组类型</span></p>
    <div class="wrap" style="height:225px;">
        <div class="org-search">
            <div class="org-search-tt">
                <span>类型名称:</span>
                <input type="text" class="input-txt" name="" id="selectTypeDiv_title" maxlength="25" >
                <a href="javascript:;" class="abtn abtn-green ml-5" id="selectTypeDiv_add"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                <a href="javascript:;" class="abtn abtn-blue ml-5" id="selectTypeDiv_del">删除</a>
            </div>
            <div class="org-search-inner" id="selectTypeDiv_container" style="height:200px">
            </div>
        </div>
    </div>
    
    <p class="dd">
        <span id="selectTypeDiv_errorMsg" class="c-red ml-20" style="float:left"></span>
        <a class="abtn abtn-blue submit" href="javascript:;" id="selectTypeDiv_submit"><span>确定</span></a>
        <a class="abtn abtn-green ml-5 close" href="javascript:;"><span>取消</span></a>
    </p>
</div>
<#include "/pages/jsinclude/basic/student/editStudentJs.ftl" />
<script>
    $(function(){
        editStudent.init();
    });
</script>
</body>
</html>