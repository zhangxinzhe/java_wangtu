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
        <p class="crumbs">当前位置：图片音视频&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/adv/advManage.htm?tabStr=pic">轮播图片</a>&nbsp;&gt;&nbsp;<span><#if !id?exists || id == 0>新增<#else>修改</#if></span></p>
        <form id="pic_form" action="" method="post">
            <input type="hidden" id="picId" name="indexAdv.id" value="${(indexAdv.id)!}"/>
            <input type="hidden" name="indexAdv.pictureFile" value="${(indexAdv.pictureFile)!}"/>
            <input type="hidden" name="indexAdv.localFile" value="${(indexAdv.localFile)!}"/>
            <div class="userInfo-wrap">
                <table class="form-table validateForm mt-10">
                    <#if picPlatform?exists && picPlatform=='PC'>
                    <tr>
                       <th><span class="must">*</span>PC端图片：</th>
                       <td>
                       <div class="userImg-wrap ml-20">
                            <@ImgStyle name='picImg' width='834' height='134'/>
                            <p class="img" style="width:834px; height:134px;"><img id="picImg" src="<#if (indexAdv.pictureFile)??>${DOMAIN_UPLOAD_FILE}/${indexAdv.pictureFile}<#else>/images/default.jpg</#if>" alt="首页滚动图片"></p>
                            <p class="t-center">
                                <span>支持jpg、png、bmp、jpeg、gif等图片格式<br/>建议图片尺寸为2500*400</span>
                            </p>
                            <p class="t-center" style="position:relative;">
                                <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                                <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" index='1' total='1' imgId="picImg" fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" filesize='5 MB' action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm' callBack="afterUpload('1')"/>
                                <input name="uploadTempPicFile" id="uploadTempPicFile" type="hidden"/>
                            </p>
                        </div>
                        </td>
                    </tr>
                    <#elseif picPlatform="MOBILE">
                    <tr>
                       <th><span class="must">*</span>手机端图片：</th>
                       <td>
                       <div class="userImg-wrap ml-20">
                            <@ImgStyle name='picImgForMobile' width='400' height='134'/>
                            <p class="img" style="width:400px; height:134px;"><img id="picImgForMobile" src="<#if (indexAdv.pictureFile)??>${DOMAIN_UPLOAD_FILE}/${indexAdv.pictureFile}<#else>/images/default.jpg</#if>" alt="首页滚动图片"></p>
                            <p class="t-center">
                                <span>支持jpg、png、bmp、jpeg、gif等图片格式<br/>建议图片尺寸为800*400</span>
                            </p>
                            <p class="t-center" style="position:relative;">
                                <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                                <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" index='1' total='1' imgId="picImgForMobile" fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" filesize='5 MB' action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm' callBack="afterUpload('2')"/>
                                <input name="uploadTempPicFileForMobile" id="uploadTempPicFileForMobile" type="hidden"/>
                            </p>
                        </div>
                        </td>
                    </tr>
                    </#if>
                    <tr>
                        <th><span class="must">*</span>图片标题：</th>
                        <td>
                            <input type="text" class="input-txt" id="picTitle" name="indexAdv.title" value="${(indexAdv.title)!}" notNull="true" placeholder="输入标题" maxlength="40"/>
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
                        <th><span class="must">*</span>内容类型：</th>
                        <td>
                          <#list ContentTypes?if_exists as conType>
                             <#if (conType_index>1)>
                                <#break/>
                             </#if>
                            <input type="radio" name="indexAdv.contentType" id="conType${conType_index}" value="${conType}" 
                             <#if indexAdv?? && indexAdv.contentType?? && conType==indexAdv.contentType>
                             checked="checked"
                             <#elseif !indexAdv?? && conType_index==0>
                             checked="checked"
                             </#if> />
                            <label for="conType${conType_index}">${conType.nameValue}</label>
                          </#list>
                        </td>
                    </tr>
                    <tr id="linkTr" 
                        <#if indexAdv?? && indexAdv.contentType?? && indexAdv.contentType.value!=1>
                        style="display:none"
                        <#elseif !indexAdv??>
                        style="display:none"
                        </#if>>
                        <th>外部链接：</th>
                        <td>
                           <input type="text" class="input-txt" id="picLinkUrl" name="indexAdv.linkUrl" value="${(indexAdv.linkUrl)!}" placeholder="输入链接地址（以http://开头）" maxlength="200"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <th valign="top" class="pt-10">显示次序：</th>
                        <td>
                            <input type="text" class="input-txt" id="picOrder" name="indexAdv.displayOrder" value="${(indexAdv.displayOrder)!}"  placeholder="输入显示次序" maxlength="4"/>
                        </td>
                    </tr>
                    <tr>
                        <th valign="top" class="pt-10">备注：</th>
                        <td valign="top" class="pt-10">
                            <textarea class="text-area fn-left t-500" id="remark" name="indexAdv.remark">${(indexAdv.remark)!}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>&nbsp;</th>
                        <td>
                            <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                            <a href="${DOMAIN_CMS}/homepage/adv/advManage.htm?tabStr=pic&picPlatform=${picPlatform!'PC'}" class="abtn abtn-green ml-10">返回</a>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/addPicjs.ftl"/>
<script>
    $(function(){
        addPic.init('${picPlatform!'PC'}');
    });
 function afterUpload(type) {
    switch(type){
        case "1" :
            var uploadTempFile = document.getElementById("uploadTempFile1").value;
            document.getElementById("uploadTempPicFile").value = uploadTempFile;
            break;
        case "2" :
            var uploadTempFile = document.getElementById("uploadTempFile1").value;
            document.getElementById("uploadTempPicFileForMobile").value = uploadTempFile;
            break;
    }
}
</script>
</body>
</html>