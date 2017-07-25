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
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/studentManage.htm">个人用户</a>&nbsp;&gt;&nbsp;<span>查看</span></p>
    
        <ul class="public-tab fn-clear mt-10">
            <span class="tt">${student.realName!}</span>
            <li class="current">个人用户信息</li>
        </ul>
        
        <div class="fn-clear">
            <div class="userInfo-wrap">
                <table class="form-table validateForm mt-10">
                    <tr>
                        <th style="width:120px;">用户名：</th>
                        <td>${student.userName!}</td>
                    </tr>
                    <tr>
                        <th>密码：</th>
                        <td>${student.password!}</td>
                    </tr>
                    <tr>
                        <th>姓名：</th>
                        <td>${student.realName!}</td>
                    </tr>
                    <tr>
                        <th>性别：</th>
                        <td>${student.sex.nameValue!}</td>
                    </tr>
                    <tr>
                        <th>手机：</th>
                        <td>${student.phone!}</td>
                    </tr>
                    <tr>
                        <th>QQ：</th>
                        <td>${student.qq!}</td>
                    </tr>
                    <tr>
                        <th>email：</th>
                        <td>${student.email!}</td>
                    </tr>
                    <tr>
                        <th>所属学校或单位：</th>
                        <td>${student.schoolName!}</td>
                    </tr>
                    <tr>
                        <th>帐号来源：</th>
                        <td>${student.registerType.nameValue}</td>
                    </tr>
                    <tr>
                        <th>用户类型：</th>
                        <td>${student.userType.nameValue}</td>
                    </tr>
                    <tr>
                        <th>是否是会员：</th>
                        <td><#if student.isMember>是<#else>否</#if></td>
                    </tr>
                    <tr>
                        <th>状态：</th>
                        <td>${student.isCancel.nameValue}</td>
                    </tr>
                    <tr>
                        <th valign="top" class="pt-10">备注：</th>
                        <td valign="top" class="pt-10">${student.remark!}</td>
                    </tr>
                    <tr>
                        <th>&nbsp;</th>
                        <td>
                         <#if student.isMember>
                             <a href="javascript:;" id="member-change" class="abtn abtn-blue " dataValue="${student.id!}" flag="0">取消会员</a>
                         <#else>
                             <a href="javascript:;" id="member-change" class="abtn abtn-blue " dataValue="${student.id!}" flag="1">升级会员</a>
                         </#if>
                            <a href="${DOMAIN_CMS}/basic/studentManage.htm" class="abtn abtn-green">返回</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/basic/student/viewStudentJs.ftl" />
<script>
    $(function(){
        studentManage.init();
    });
</script>
</body>
</html>