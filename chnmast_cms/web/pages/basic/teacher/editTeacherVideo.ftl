<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-维护教师风采</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <#if picture?exists>
            <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/teacherManage.htm">名师大家</a>&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/teacherStyle.htm?teacherId=${teacherId!}">风采展示</a>&nbsp;&gt;&nbsp;<span>维护风采展示</span></p>
            <ul class="public-tab fn-clear mt-10">
                <span class="tt" title="${teacher.realName!}"><@cutOff cutStr="${teacher.realName!}" cutLen="20"/></span>
                <li onclick="javascript:window.location.href='${DOMAIN_CMS}/basic/editTeacher.htm?teacherId=${teacherId!}'">基本信息</li>
                <li class="current" onclick="javascript:window.location.href='${DOMAIN_CMS}/basic/teacherStyle.htm?teacherId=${teacherId!}'">风采展示</li>
            </ul>
            <form id="addPictureForm" method="post" action="${DOMAIN_CMS}/basic/editTeacherStyle.htm">
                <input type="hidden" id="pictureIdHid" name="picture.id" value="${picture.id!}" />
                <input type="hidden" name="picture.objectId" value="${teacherId!}" />
                <input type="hidden" name="teacherId" value="${teacherId!}" />
                <input type="hidden" id="pictureFileInput" name="picture.pictureFile" value="${picture.pictureFile!}" >
                <input type="hidden" id="videoFileInput" name="picture.videoFile" value="${picture.videoFile!}" >
                <input type="hidden" name="picture.fileSize" value="${picture.fileSize!}" >
                <input type="hidden" name="picture.fileExt" value="${picture.fileExt!}" >
                <input type="hidden" name="picture.fileType" value="2" >
                <table class="form-table validateForm mt-10">
                    <tr>
                        <th><span class="must">*</span>图片文件：</th>
                        <td>
                            <div class="userImg-wrap ml-10">
                                <@ImgStyle name='picImg' width='260' height='160'/>
                                <p class="img" style="width:260px; height:160px;"><img id="picImg" src="<#if picture.pictureFile?default('') != ''>${DOMAIN_UPLOAD_FILE}/${picture.pictureFile!}<#elseif uploadTempFile?default('') != ''>${DOMAIN_UPLOAD_FILE}/${uploadTempFile!}<#else>/images/default.jpg</#if>" alt="展示图片"></p>
                                <p class="t-center">
                                    <span>建议图片尺寸为260*160</span>
                                </p>
                                <p class="t-center" style="position:relative;">
                                    <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                                    <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" imgId="picImg" index='1' total='2' filesize='3 MB' fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm' callBack="afterUpload(file,'1')" />
                                    <input name="uploadTempPicFile" type="hidden" id="uploadTempPicFile" value="${uploadTempPicFile!}" />
                                </p>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th valign="top" class="pt-10"><span class="must">*</span>视频文件：</th>
                        <td valign="top" class="pt-10">
                            <span>支持mp4等视频格式,建议视频大小为100M以下</span>
                            <div>
                                <span class="fn-left" style="position:relative;" >
                                    <a href="javascript:;" class="abtn abtn-blue">选择视频</a>
                                    <@uploadPic_temp_nocss position="top:0px; left:0px;" width="70" height="26" fileTypes="*.mp4" index='2' total='2' filesize='100 MB' action='${DOMAIN_CMS}/common/upload/uploadTemp.htm' display=true callBack="afterUpload(file,'2')" />
                                    <input name="uploadTempVideoFile" type="hidden" id="uploadTempVideoFile" value="${uploadTempVideoFile!}"/>
                                </span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span>名称：</th>
                        <td>
                            <input name="picture.pictureName" id="title" type="text" class="input-txt" notNull="true" placeholder="输入名称" value="${picture.pictureName!}">
                        </td>
                    </tr>
                    <tr id="isShowTr">
                        <th><span class="must">*</span>是否显示：</th>
                        <td notNull="true">
                            <label for="out1">
                                <input type="radio" id="out1" class="chk" <#if picture.id == 0 || picture.isShow.booleanValue>checked</#if> name="picture.isShow" value="YES">是
                            </label>
                            <label for="out2" class="ml-20">
                                <input type="radio" id="out2" class="chk" <#if picture.id != 0 && !picture.isShow.booleanValue>checked</#if> name="picture.isShow" value="NO">否
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span>显示顺序：</th>
                        <td>
                            <input name="picture.showOrder" id="showOrder" datatype="integer" integerLength="5" type="text" class="input-txt" placeholder="输入顺序" value="${picture.showOrder!}">
                        </td>
                    </tr>
                    <tr>
                        <th>&nbsp;</th>
                        <td><a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a><a href="javascript:location.href='${DOMAIN_CMS}/basic/teacherStyle.htm?teacherId=${teacherId!}'" class="abtn abtn-green ml-10">返回</a></td>
                    </tr>
                </table>
             </form> 
        <#else>
            <div class="record-wrap mt-10">
                <div class="public-nodata">此教师风采不存在</div>
            </div>
        </#if>
    </div>
</div>

<@footer />

</body>
<#if picture?exists>
    <#include "/pages/jsinclude/basic/teacher/editTeacherStyleJs.ftl"/>
</#if>
<script>
    <#if picture?exists>
        $(function(){
            EditTeacherStyle.init();
        });
        function  afterUpload(file,type){
            if(type == '1'){
                $("#uploadTempPicFile").val($("#uploadTempFile"+type).val());
            }else{
                $("#uploadTempVideoFile").val($("#uploadTempFile"+type).val());
                var fileName = file.name.split(".")[0];
                $("#title").val(fileName);
            }
        }
    </#if>
</script>
</html>