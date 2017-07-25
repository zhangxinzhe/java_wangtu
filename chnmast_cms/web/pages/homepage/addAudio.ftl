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
        <p class="crumbs">当前位置：图片音视频&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/adv/advManage.htm?tabStr=audio">伴奏音频</a>&nbsp;&gt;&nbsp;<span><#if !id?exists || id == 0>新增<#else>修改</#if></span></p>
        <form id="audio_form" action="" method="post">
            <input type="hidden" id="audioId" name="indexAdv.id" value="${(indexAdv.id)!}"/>
            <input type="hidden" name="indexAdv.localFile" value="${(indexAdv.localFile)!}"/>
            <input type="hidden" name="indexAdv.displayOrder" value="${(indexAdv.displayOrder)!}"/>
            <div class="userInfo-wrap">
                <table class="form-table validateForm mt-10">
                    <tr>
                        <th valign="top" class="pt-10"><span class="must">*</span>音频文件：</th>
                        <td valign="top">
                            <#-----文件上传     start-------->
                            <span style="line-height:26px">支持mp3音频格式,建议音频大小为10M以下</span>
                            <div>
                                <span class="fn-left" style="position:relative;">
                                    <a href="javascript:;" class="abtn abtn-blue">选择音频</a>
                                    <@uploadPic_temp_nocss position="top:-6px; left:0px;" width="70" height="26" fileTypes="*.mp3" filesize='10 MB' action='${DOMAIN_CMS}/common/upload/uploadTemp.htm' display=true callBack="afterUpload(file)"/>
                                    <input name="uploadTempLocalFile" type="hidden" id="uploadTempLocalFile"/>
                                </span>
                            </div>
                           <#-----文件上传     end---------->
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span>音频标题：</th>
                        <td>
                            <input type="text" class="input-txt" id="audioTitle" name="indexAdv.title" value="${(indexAdv.title)!}" notNull="true" placeholder="输入音频标题" maxlength="40"/>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span>演唱者：</th>
                        <td>
                            <input type="text" class="input-txt" id="audioUser" name="indexAdv.remark" value="${(indexAdv.remark)!}" notNull="true" placeholder="输入演唱者" maxlength="100"/>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span>音频类型：</th>
                        <td>
                            <div class="select-analog fn-left w-200">
                                <input type="hidden" id="typeId" name="indexAdv.advTypeId" value="${(indexAdv.advTypeId)!}" />
                                <input type="text" class="input-txt" id="title" name="indexAdv.advTypeTitle" value="${(indexAdv.advTypeTitle)!}" notNull="true" readOnly/>
                                <a href="javascript:;" class="open selectType" flag="2"></a>
                            </div>
                            <span class="onTips fn-left ml-5" style="line-height:26px">选择类型</span>
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
                        <th>&nbsp;</th>
                        <td>
                            <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                            <a href="${DOMAIN_CMS}/homepage/adv/advManage.htm?tabStr=audio" class="abtn abtn-green ml-10" >返回</a>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
</div>
<@footer />
<div class="popUp-layer" id="selectType_div" style="display:none;">
<p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>选择音乐类型</span></p>
<div class="wrap" style="height:225px;">
    <div class="org-search">
        <div class="org-search-tt">
            <span>分类内容:</span>
            <input type="text" class="input-txt" name="select_title" id="select_title" maxlength="10" >
            <a href="javascript:;" class="abtn abtn-green ml-5" id="add_adv_type"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
            <a href="javascript:;" id="delete_adv_type" class="abtn abtn-blue">删除</a>
        </div>
        <div class="org-search-inner" id="selectType_container" style="height:200px">
        </div>
    </div>
</div>

<p class="dd">
    <span id="type_errorMsg" class="c-red ml-20" style="float:left"></span>
    <a class="abtn abtn-blue submit" href="javascript:;" id="selectType_submit"><span>确定</span></a>
    <a class="abtn abtn-green ml-5 close" href="javascript:;"><span>取消</span></a>
</p>
</div>
<#include "/pages/jsinclude/homepage/addAudiojs.ftl"/>
<script>
$(function(){
    addAudio.init();
});
function afterUpload(file) {
    var uploadTempFile = document.getElementById("uploadTempFile").value;
    document.getElementById("uploadTempLocalFile").value = uploadTempFile;
    var fileName = file.name.split(".")[0];
    document.getElementById("audioTitle").value = fileName;
}
</script>
</body>
</html>