<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-新增高校</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=1400000 curSubModId=1400560 />
    
    <div id="content">
        <p class="crumbs">当前位置：首页管理 > <a href="${DOMAIN_CMS}/homepage/college/collegeManage.htm">高校风采展示</a> > <span>修改</span></p>
        <ul class="public-tab fn-clear mt-10">
            <span class="tt" title="${(college.name)!}">${(college.name)!}</span>
            <li class="current" >基本信息</li>
        </ul>
        <form id="collegeForm" method="post" action="">
        <input name="college.id" type="hidden" value="${college.id}"/>
        <input name="college.bannerFile" type="hidden" value="${college.bannerFile!}"/>
        <input name="college.logoFile" type="hidden" value="${college.logoFile!}"/>
        <div class="userImg-wrap fn-right mr-20">
            <p align="center">logo图</p>
            <@ImgStyle name='picImg' width='154' height='154'/>
            <p class="img" style="width:154px;height:154px"><img id="picImg" src="${DOMAIN_UPLOAD_FILE}<#if (college.logoFile)?? && college.logoFile!="" >/${college.logoFile}<#else>/images/college_logo.png</#if>" alt="高校logo图片" style="margin:0 35px;"></p>
            <p class="t-center">
                <span>支持jpg、png、bmp、jpeg、gif等图片格式<br/>建议图片尺寸为154*154</span>
            </p>
            <p class="t-center" style="position:relative;">
                <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                <@uploadPic_temp_nocss position="top:0px; left:85px;" index='1' total='2' width="70" height="26" imgId="picImg" fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" filesize='3 MB' action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm' callBack="afterUpload()"/>
                <input name="uploadTempPicFile" id="uploadTempPicFile" type="hidden"/>
            </p>
         </div>
        <div class="userInfo-wrap">
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>高校姓名：</th>
                <td>
                    <input type="text" class="input-txt" id="collegeName" name="college.name" notNull="true" placeholder="输入高校名称" value="${(college.name)!}" maxlength="75"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>是否推荐：</th>
                <td>
                    <#list recommendList?if_exists as type>
                       <input type="radio" name="college.isRecommend" id="isRecommend${type_index}" value="${type}" 
                       <#if college?? && college.isRecommend?? && type==college.isRecommend>
                        checked="checked"
                        <#elseif !college??&&type_index==0>
                        checked="checked"
                        </#if> />
                       <label for="isRecommend${type_index}">${type.nameValue}</label>
                    </#list>
                </td>
            </tr>
            
            <tr>
                <th>联系电话：</th>
                <td>
                    <input type="text" class="input-txt" id="phone" name="college.phone" placeholder="输入联系电话" value="${(college.phone)!}" maxlength="15"/>
                </td>
            </tr>
            
            <tr>
                <th valign="top" class="pt-10">显示次序：</th>
                <td>
                    <input type="text" class="input-txt" id="displayNo" name="college.displayNo" value="${(college.displayNo)!}"  placeholder="输入显示次序" maxlength="4"/>
                </td>
            </tr>
            <tr>
                <th valign="top" class="pt-10">高校简介：</th>
                <td valign="top" class="pt-10">
                    <textarea class="text-area fn-left t-500" id="introduction" name="college.introduction" maxlength="4000">${(college.introduction)!}</textarea>
                </td>
            </tr>
            <tr>
                <th valign="top" class="pt-10">高校优势：</th>
                <td valign="top" class="pt-10">
                    <textarea class="text-area fn-left t-500" id="feature" name="college.feature" maxlength="4000">${(college.feature)!}</textarea>
                </td>
            </tr>
            <tr>
                <th>banner图：</th>
                <td>
                <div class="userImg-wrap fn-left" style="width:500px">
                    <@ImgStyle name='picImg2' width='500' height='60'/>
                    <p class="img" style="width:500px;height:60px"><img id="picImg2" src="${DOMAIN_UPLOAD_FILE}<#if (college.bannerFile)?? && college.bannerFile!="">/${college.bannerFile}<#else>/images/college_bg.jpg</#if>" alt="高校风采图片" ></p>
                    <p class="t-center">
                        <span>支持jpg、png、bmp、jpeg、gif等图片格式建议图片尺寸为2500*240</span>
                    </p>
                    <p class="t-center" style="position:relative;">
                        <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                        <@uploadPic_temp_nocss position="top:0px; left:215px;" index='2' total='2' width="70" height="26" imgId="picImg2" fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" filesize='3 MB' action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm' callBack="afterUpload2()"/>
                        <input name="uploadTempPicFile2" id="uploadTempPicFile2" type="hidden"/>
                    </p>
                </div>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td><a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                <a href="${DOMAIN_CMS}/homepage/college/collegeManage.htm" class="abtn abtn-green ml-10">返回</a></td>
            </tr>
        </table>
        </div>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/collegeEditjs.ftl" />

<script>
$(function(){
    window.CollegeEdit.init();
});

function afterUpload(){
   var uploadTempFile = document.getElementById("uploadTempFile1").value;
   document.getElementById("uploadTempPicFile").value = uploadTempFile;
}

function afterUpload2(){
   var uploadTempFile = document.getElementById("uploadTempFile2").value;
   document.getElementById("uploadTempPicFile2").value = uploadTempFile;
}
</script>
</body>
</html>