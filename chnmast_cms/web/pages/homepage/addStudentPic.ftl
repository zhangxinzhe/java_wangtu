<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-维护风采展示</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
            <p class="crumbs">当前位置：基础数据 > <a href="${DOMAIN_CMS}/basic/agencyManage.htm">培训基地</a> > <a href="${DOMAIN_CMS}/basic/agencyPicture.htm?agencyId=${agencyId!}">展示图片</a> > <span>维护展示图片</span></p>
            <ul class="public-tab fn-clear mt-10">
                <span class="tt" title="${(student.stuName)!}">${(student.stuName)!}</span>
                <li onclick="javascript:window.location.href='${DOMAIN_CMS}/homepage/stu/updateStudent.htm?id=${id!}'">基本信息</li>
                <li class="current" onclick="javascript:window.location.href='${DOMAIN_CMS}/homepage/stu/studentPicManage.htm?id=${id!}'">风采展示</li>
            </ul>
            <form id="addPictureForm" method="post" action="${DOMAIN_CMS}/basic/editAgencyPicture.htm">
                <input type="hidden" id="pictureIdHid" name="picture.id" value="${(picture.id)!}" />
                <input type="hidden" name="picture.objectId" value="${(student.id)!}" />
                <input type="hidden" id="pictureFileInput" name="picture.pictureFile" value="${(picture.pictureFile)!}" >
                <input type="hidden" id="videoFileInput" name="picture.videoFile" value="${(picture.videoFile)!}" >
                <input type="hidden" name="picture.fileSize" id="fileSize" value="${(picture.fileSize)!}" >
                <input type="hidden" name="picture.fileExt" id="fileExt" value="${(picture.fileExt)!}" >
                <table class="form-table validateForm mt-10">
                <#if tabStr=="pic">
                <div class="userImg-wrap ml-20">
                    <@ImgStyle name='stuImg' width='240' height='180'/>
                    <p class="img"><img id="stuImg" src="<#if picture?? && picture.pictureFile??>${DOMAIN_UPLOAD_FILE}/${picture.pictureFile!}<#else>/images/user_default.jpg</#if>" alt="展示图片"></p>
                    <p class="t-center">
                        <span>建议图片尺寸为240*180</span>
                    </p>
                    <p class="t-center" style="position:relative;">
                        <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                        <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" index='1' total='1'  imgId="stuImg" filesize='3 MB' fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm' callBack="afterUpload('1',file)" />
                        <input name="uploadTempPicFile" type="hidden" id="uploadTempPicFile"/>
                        <input name="picture.fileType" type="hidden" value="1"/>
                    </p>
                </div>
                <#elseif tabStr=="video">
                <div class="userImg-wrap ml-20">
                    <@ImgStyle name='videoImg' width='260' height='160'/>
                    <p class="img"><img id="videoImg" src="<#if picture?? && picture.pictureFile??>${DOMAIN_UPLOAD_FILE}/${picture.pictureFile!}<#else>/images/default.jpg</#if>" alt="视频图片"></p>
                    <p class="t-center">
                        <span>建议图片尺寸为260*160</span>
                    </p>
                    <p class="t-center" style="position:relative;">
                        <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                        <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" index='1' total='2' imgId="videoImg" filesize='3 MB' fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm' callBack="afterUpload('2',null)" />
                        <input name="uploadTempPicFile" type="hidden" id="uploadTempPicFile"/>
                    </p>
                </div>
                <tr>
                    <th><span class="must">*</span>视频文件：</th>
                    <td>
                       <div>
                        <span class="fn-left" style="position:relative;">
                            <a href="javascript:;" class="abtn abtn-blue">选择视频</a>
                            <@uploadPic_temp_nocss position="top:-6px; left:0px;" width="70" height="26" index='2' total='2'  fileTypes="*.mp4" filesize='100 MB' action='${DOMAIN_CMS}/common/upload/uploadTemp.htm' callBack="afterUpload('2',file)"/>
                            <input name="uploadTempVideoFile" type="hidden" id="uploadTempVideoFile"/>
                            <input name="picture.fileType" type="hidden" value="2"/>
                        </span>
                        <span style="line-height:26px">（支持mp4视频格式,建议视频大小为100M以下）</span>
                      </div>
                    </td>
                  </tr>
                </#if>
                    <tr>
                        <th><span class="must">*</span>名称：</th>
                        <td>
                            <input name="picture.pictureName" id="title" type="text" class="input-txt" notNull="true" placeholder="输入名称" value="${(picture.pictureName)!}" maxlength="40">
                        </td>
                    </tr>
                    <tr id="isShowTr">
                        <th><span class="must">*</span>是否显示：</th>
                        <td>
                            <#list YesNoTypes?if_exists as type>
                               <input type="radio" name="picture.isShow" id="isShow${type_index}" value="${type}" 
                               <#if picture?? && picture.isShow?? && type==picture.isShow>
                                checked="checked"
                                <#elseif !picture??&&type_index==0>
                                checked="checked"
                                </#if> />
                               <label for="isShow${type_index}">${type.nameValue}</label>
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <th>显示顺序：</th>
                        <td>
                            <input name="picture.showOrder" id="showOrder" maxlength="2" type="text" class="input-txt" value="${(picture.showOrder)!0}">
                        </td>
                    </tr>
                    <tr>
                        <th>&nbsp;</th>
                        <td><a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a><a href="javascript:window.history.back();" class="abtn abtn-green ml-10">返回</a></td>
                    </tr>
                </table>
             </form> 
    </div>
</div>

<@footer />

</body>
<#include "/pages/jsinclude/homepage/addStudentPicjs.ftl"/>
<script>
    $(function(){
        addStudentPic.init(${id!});
    });
    
function afterUpload(type,file) {
    if(type=="1"){      //新增图片
       $("#uploadTempPicFile").val($("#uploadTempFile1").val());
       var fileName = file.name.split(".")[0];
       $("#title").val(fileName);
    }else{             //新增视频  
       if(file!=null){     //上传视频
         $("#uploadTempVideoFile").val($("#uploadTempFile2").val());
         var fileName = file.name.split(".")[0];
         $("#title").val(fileName);
       }else{            //上传展示图片
         $("#uploadTempPicFile").val($("#uploadTempFile1").val());
       }
    }
}
</script>
</html>