<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-图片音视频</title>
</head>
<body>
<@header curAppId=appId/>
<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <div id="content">
        <p class="crumbs">当前位置：图片音视频&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/adv/advManage.htm?tabStr=video">精彩视频</a>&nbsp;&gt;&nbsp;<span><#if !id?exists || id == 0>新增<#else>修改</#if></span></p>
        <form id="video_form" action="" method="post">
            <input type="hidden" id="videoId" name="indexAdv.id" value="${(indexAdv.id)!}"/>
            <input type="hidden" name="indexAdv.pictureFile" value="${(indexAdv.pictureFile)!}"/>
            <input type="hidden" name="indexAdv.localFile" value="${(indexAdv.localFile)!}"/>
            <div class="userInfo-wrap">
             
                <table class="form-table validateForm mt-10">
                    <tr>
                        <th><span class="must">*</span>视频图片：</th>
                        <td>
                           <#---- 图片上传       start----->
                            <div class="userImg-wrap">
                                <@ImgStyle name='picImg' width='240' height='150'/>
                                <p class="img" style="width:240px; height:150px;"><img id="picImg" src="<#if (indexAdv.pictureFile)??>${DOMAIN_UPLOAD_FILE}/${indexAdv.pictureFile}<#else>/images/default.jpg</#if>" alt="视频图片"></p>
                                <p class="t-center">
                                    <span>支持jpg、png、bmp、jpeg、gif等图片格式<br/>建议图片尺寸为470*290</span>
                                </p>
                                <p class="" style="position:relative;">
                                    <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                                    <@uploadPic_temp_nocss position="top:0px; left:0px;" width="70" height="26" index='1' total='2' imgId="picImg" fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" filesize='3 MB' action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm' callBack="afterUpload(file,'1')"/>
                                    <input name="uploadTempPicFile" type="hidden" id="uploadTempPicFile" />
                                </p>
                            </div>
                            <#-----图片上传       end-------->  
                        </td>
                    </tr>
                    <tr>
                        <th valign="top" class="pt-10"><span class="must">*</span>视频文件：</th>
                        <td valign="top" class="pt-10">
                        <#-----文件上传     start-------->
                         <span>支持mp4，flv视频格式,建议视频大小为2G以下</span>
                         <div>
                            <span class="fn-left" style="position:relative;">
                                <a href="javascript:;" class="abtn abtn-blue">选择视频</a>
                                <@uploadPic_temp_nocss position="top:0px; left:0px;" width="70" height="26" index='2' total='2'  fileTypes="*.mp4;*.flv" filesize='2048 MB' action='${DOMAIN_CMS}/common/upload/uploadTemp.htm' display=true callBack="afterUpload(file,'2')"/>
                                <input name="uploadTempLocalFile" type="hidden" id="uploadTempLocalFile"/>
                            </span>
                         </div>
                       <#-----文件上传     end---------->
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span>视频标题：</th>
                        <td>
                            <input type="text" class="input-txt" id="audioTitle" name="indexAdv.title" value="${(indexAdv.title)!}" notNull="true" placeholder="输入视频标题" maxlength="40"/>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span>是否显示：</th>
                        <td>
                            <#list YesNoTypes?if_exists as type>
                               <input type="radio" name="indexAdv.isShow" id="isShow${type_index}" value="${type}" 
                               <#if indexAdv?? && indexAdv.isShow?? && type==indexAdv.isShow>
                                checked="checked"
                                <#elseif !indexAdv??&&type_index==0>
                                checked="checked"
                                </#if> />
                               <label for="isShow${type_index}">${type.nameValue}</label>
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <th valign="top" class="pt-10">显示次序：</th>
                        <td>
                            <input type="text" class="input-txt" id="audioOrder" name="indexAdv.displayOrder" value="${(indexAdv.displayOrder)!}"  placeholder="输入显示次序" maxlength="4"/>
                        </td>
                    </tr>
                    <tr>
                        <th valign="top" class="pt-10">备注：</th>
                        <td valign="top" class="pt-10">
                            <textarea class="text-area fn-left t-500" id="remark" name="indexAdv.remark" >${(indexAdv.remark)!}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>&nbsp;</th>
                        <td>
                            <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                            <a href="${DOMAIN_CMS}/homepage/adv/advManage.htm?tabStr=video" class="abtn abtn-green ml-10">返回</a>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/addVideojs.ftl"/>
<script>
$(function(){
    addVideo.init();
});
function afterUpload(file,type) {
    switch(type){
        case "1" :<#-- 图片 -->
            //上传后的图片路径
            var uploadTempFile = document.getElementById("uploadTempFile"+type).value;
            document.getElementById("uploadTempPicFile").value = uploadTempFile;
            break;
        case "2" :<#-- 文件 -->
            var uploadTempFile = document.getElementById("uploadTempFile"+type).value;
            document.getElementById("uploadTempLocalFile").value = uploadTempFile;
            var fileName = file.name.split(".")[0];
            document.getElementById("audioTitle").value = fileName;
            break;
    }
}
</script>
</body>
</html>