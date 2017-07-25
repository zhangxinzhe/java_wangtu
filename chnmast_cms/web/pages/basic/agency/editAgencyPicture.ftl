<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-维护展示图片</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <#if picture?exists>
            <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/agencyManage.htm">培训基地</a>&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/agencyPicture.htm?agencyId=${agencyId!}">展示图片</a> > <span>维护展示图片</span></p>
            <ul class="public-tab fn-clear mt-10">
                <span class="tt" title="${agency.agencyName!}"><@cutOff cutStr="${agency.agencyName!}" cutLen="20"/></span>
                <li onclick="javascript:window.location.href='${DOMAIN_CMS}/basic/editAgency.htm?agencyId=${agencyId!}'">基本信息</li>
                <li class="current" onclick="javascript:window.location.href='${DOMAIN_CMS}/basic/agencyPicture.htm?agencyId=${agencyId!}'">展示图片</li>
            </ul>
            <form id="addPictureForm" method="post" action="${DOMAIN_CMS}/basic/editAgencyPicture.htm">
                <input type="hidden" id="pictureIdHid" name="picture.id" value="${picture.id!}" />
                <input type="hidden" name="picture.objectId" value="${agencyId!}" />
                <input type="hidden" name="agencyId" value="${agencyId!}" />
                <input type="hidden" id="pictureFileInput" name="picture.pictureFile" value="${picture.pictureFile!}" >
                <input type="hidden" name="picture.fileSize" value="${picture.fileSize!}" >
                <input type="hidden" name="picture.fileExt" value="${picture.fileExt!}" >
                <input type="hidden" name="picture.fileType" value="1" >
                <div class="userImg-wrap ml-10">
                    <@ImgStyle name='courseImg' width='240' height='180'/>
                    <p class="img"><img id="courseImg" src="<#if picture.pictureFile?default('') != ''>${DOMAIN_UPLOAD_FILE}/${picture.pictureFile!}<#elseif uploadTempFile?default('') != ''>${DOMAIN_UPLOAD_FILE}/${uploadTempFile!}<#else>/images/user_default.jpg</#if>" alt="展示图片"></p>
                    <p class="t-center">
                        <span>建议图片尺寸为240*180</span>
                    </p>
                    <p class="t-center" style="position:relative;">
                        <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                        <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" imgId="courseImg" filesize='3 MB' fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm' callBack="afterUpload(file)" />
                    </p>
                </div>
                <table class="form-table validateForm mt-10">
                    <tr>
                        <th><span class="must">*</span>名称：</th>
                        <td>
                            <input name="picture.pictureName" id="title" type="text" class="input-txt" notNull="true" maxlength="30" placeholder="输入名称" value="${picture.pictureName!}">
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
                        <td><a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a><a href="javascript:location.href='${DOMAIN_CMS}/basic/agencyPicture.htm?agencyId=${agencyId!}'" class="abtn abtn-green ml-10">返回</a></td>
                    </tr>
                </table>
             </form> 
        <#else>
            <div class="record-wrap mt-10">
                <div class="public-nodata">此展示图片不存在</div>
            </div>
        </#if>
    </div>
</div>

<@footer />

</body>
<#if picture?exists>
    <#include "/pages/jsinclude/basic/agency/editAgencyPictureJs.ftl"/>
</#if>
<script>
    <#if picture?exists>
        $(function(){
            EditPicture.init();
        });
        function  afterUpload(file){
            var fileName = file.name.split(".")[0];
            document.getElementById("title").value = fileName;
        }
    </#if>
</script>
</html>