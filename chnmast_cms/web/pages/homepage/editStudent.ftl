<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-修改优秀学员</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：首页管理 > <a href="${DOMAIN_CMS}/homepage/stu/studentManage.htm">优秀学员</a> > <span>修改</span></p>
        <ul class="public-tab fn-clear mt-10">
            <span class="tt" title="${(student.stuName)!}">${(student.stuName)!}</span>
            <li class="current" onclick="javascript:window.location.href='${DOMAIN_CMS}/homepage/stu/updateStudent.htm?id=${(student.id)!}'">基本信息</li>
            <li onclick="javascript:window.location.href='${DOMAIN_CMS}/homepage/stu/studentPicManage.htm?id=${(student.id)!}'">风采展示</li>
        </ul>
        
        <form id="studentForm" method="post" action="">
         <input name="student.id" type="hidden" value="${(student.id)!}"/>
         <input name="student.photoFile" type="hidden" value="${(student.photoFile)!}"/>
         <div class="userImg-wrap fn-right mr-20">
                <@ImgStyle name='picImg' width='240' height='150'/>
                <p class="img" style="width:240px; height:150px;"><img id="picImg" src="<#if (student.photoFile)??>${DOMAIN_UPLOAD_FILE}/${student.photoFile}<#else>/images/default_student.png</#if>" alt="优秀学员图片" style="margin:0 35px;"></p>
                <p class="t-center">
                    <span>支持jpg、png、bmp、jpeg、gif等图片格式<br/>建议图片尺寸为470*290</span>
                </p>
                <p class="t-center" style="position:relative;">
                    <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                    <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" imgId="picImg" fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" filesize='3 MB' action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm' callBack="afterUpload()"/>
                    <input name="uploadTempPicFile" id="uploadTempPicFile" type="hidden"/>
                </p>
         </div>
        <div class="userInfo-wrap">
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>学员姓名：</th>
                <td>
                    <input type="text" class="input-txt" id="stuName" name="student.stuName" notNull="true" placeholder="输入学员姓名" value="${(student.stuName)!}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>所属单位：</th>
                <td>
                    <input type="text" class="input-txt" id="unitName" name="student.unitName" notNull="true" placeholder="输入单位名称" value="${(student.unitName)!}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>是否显示：</th>
                <td>
                    <#list YesNoTypes?if_exists as type>
                       <input type="radio" name="student.isShow" id="isShow${type_index}" value="${type}" 
                       <#if student?? && student.isShow?? && type==student.isShow>
                        checked="checked"
                        <#elseif !student??&&type_index==0>
                        checked="checked"
                        </#if> />
                       <label for="isShow${type_index}">${type.nameValue}</label>
                    </#list>
                </td>
            <tr>
                <th valign="top" class="pt-10">显示次序：</th>
                <td>
                    <input type="text" class="input-txt" id="orderNo" name="student.orderNo" value="${(student.orderNo)!}"  placeholder="输入显示次序" maxlength="4"/>
                </td>
            </tr>
            <tr>
                <th valign="top" class="pt-10">介绍：</th>
                <td valign="top" class="pt-10">
                    <textarea class="text-area fn-left t-500" id="introduction" name="student.introduction">${(student.introduction)!}</textarea>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td><a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a><a href="${DOMAIN_CMS}/homepage/stu/studentManage.htm" class="abtn abtn-green ml-10">返回</a></td>
            </tr>
        </table>
        </div>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/editStudentjs.ftl" />
<script>
$(function(){
    editStudent.init();
});
function afterUpload(){
   var uploadTempFile = document.getElementById("uploadTempFile").value;
   document.getElementById("uploadTempPicFile").value = uploadTempFile;
}
</script>
</body>
</html>