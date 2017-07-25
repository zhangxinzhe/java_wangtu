<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-修改名师大家</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/teacherManage.htm">名师大家</a>&nbsp;&gt;&nbsp;<span>修改名师大家</span></p>
        <ul class="public-tab fn-clear mt-10">
            <span class="tt" title="${teacher.realName!}"><@cutOff cutStr="${teacher.realName!}" cutLen="20"/></span>
            <li class="current" onclick="javascript:window.location.href='${DOMAIN_CMS}/basic/editTeacher.htm?teacherId=${teacherId!}'">基本信息</li>
            <li onclick="javascript:window.location.href='${DOMAIN_CMS}/basic/teacherStyle.htm?teacherId=${teacherId!}'">风采展示</li>
        </ul>
        <form id="teacherForm" method="post" action="${DOMAIN_CMS}/basic/editTeacher.htm">
        <input type="hidden" name="teacher.id" value="${teacher.id}" >
        <input type="hidden" name="teacher.photoFile" value="${teacher.photoFile!}" >
        <div class="userImg-wrap fn-right mr-20">
            <@ImgStyle name='courseImg' width='189' height='158'/>
            <p class="img"><img id="courseImg" src="<#if teacher.photoFile?default('') != ''>${DOMAIN_UPLOAD_FILE}/${teacher.photoFile!}<#elseif uploadTempFile?default('') != ''>${DOMAIN_UPLOAD_FILE}/${uploadTempFile!}<#else>/images/default_teacher.png</#if>" alt="教师照片"></p>
            <p class="t-center">
                <span>支持jpg、png格式，建议图片尺寸为189*158</span>
            </p>
            <p class="t-center" style="position:relative;">
                <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" imgId="courseImg" filesize='3 MB' fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm' />
            </p>
        </div>
        <div class="userInfo-wrap">
            <table class="form-table validateForm mt-10">
                <tr>
                    <th><span class="must">*</span>姓名：</th>
                    <td>
                        <input type="text" class="input-txt" id="nameInput" name="teacher.realName" notNull="true" maxlength="25" placeholder="输入名师姓名" value="${teacher.realName!}"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>类型：</th>
                    <td>
                        <select class="input-sel w-200" name="teacherTypeValue" notNull="true">
                            <option value="">--请选择--</option>
                            <#list teachTypes as type>
                                <option value="${type.value}" <#if teacher.teachType?exists && teacher.teachType.value == type.value>selected</#if>>${type.nameValue!}</option>
                            </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>职称职衔：</th>
                    <td><input type="text" class="input-txt" id="titleInput" name="teacher.title" maxlength="50" notNull="true" value="${teacher.title!}"></td>
                </tr>
                <tr>
                    <th>主教专业：</th>
                    <td><input type="text" class="input-txt" id="teachSubjectInput" name="teacher.teachSubject" maxlength="200" value="${teacher.teachSubject!}"></td>
                </tr>
                <tr>
                    <th>所在地区：</th>
                    <td>
                        <input type="hidden" id="regionCode" name="teacher.regionCode" value="${teacher.regionCode?default("")}"/>
                        <div class="floatL">
                            <select id="provinceSel" class="input-sel">
                                <option value="" >请选择所在省</option>
                            </select>
                            <select id="citySel" class="input-sel">
                                <option value="" >请选择所在市</option>
                            </select>
                            <select id="countySel" class="input-sel">
                                <option value="" >请选择所在区/县</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td>
                        <div><span>说明：所在地区如要填写，请填写至区/县！</span></div>
                    </td>
                </tr>
                <tr>
                    <th>简介：</th>
                    <td><textarea class="text-area fn-left t-350" maxlength="300" id="introductionInput" name="teacher.introduction" >${teacher.introduction!}</textarea></td>
                </tr>
                <tr>
                    <th><span class="must">*</span>用户名：</th>
                    <td>
                        <input type="text" class="input-txt" readonly id="usernameInput" name="teacher.username" regex="/^[a-zA-Z0-9_]+$/" regexMsg="请输入英文字母或是数字,其它字符是不允许的" notNull="true" maxlength="25" placeholder="输入用户名" value="${teacher.username!}"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>密码：</th>
                    <td>
                        <input class="input-txt"  id="passwordInput" name="teacher.password" type="password" notNull="true" maxlength="20" oncopy="return false;" oncut="return false;" onpaste="return false" onkeyup="return showPwdRank(this.value);return false;" value="${teacher.password!}">
                    </td>
                </tr>
                <tr style="display:none;" id="levelTr">
                    <th>&nbsp;</th>
                    <td>
                        <div id="securityLevel" class="acc-safety acc-safety-low"><span>为保证您账号的安全性，请使用为数字+字母的组合，长度在6到20位哦！</span></div>
                    </td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td><a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a><a href="javascript:location.href='${DOMAIN_CMS}/basic/teacherManage.htm'" class="abtn abtn-green ml-10">返回</a></td>
                </tr>
            </table>
        </div>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/basic/teacher/editTeacherJs.ftl" />
<script>
$(function(){
    EditTeacher.init();
});
</script>
</body>
</html>