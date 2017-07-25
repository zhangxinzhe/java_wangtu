<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/jsinclude/commonjs.ftl" />
<#include "/pages/common/common.ftl">

<title>${SITE_CMS_SITE_NAME}-视频回顾编辑</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;>&nbsp;
        <a href="${DOMAIN_CMS}/homepage/info/videoReManage.htm">视频回顾</a>&nbsp;>&nbsp;
        <#if indexInfo?exists && indexInfo.id? exists && indexInfo.id!=0>
        <span>修改</span>
        <#else>
        <span>新增</span>
        </#if>
        </p>
        <form id="videoReForm" method="post" action="${DOMAIN_CMS}/homepage/info/videoReEdit.htm" style="height:900px">
        <input type="hidden" name="indexInfo.localFile" value="${indexInfo.localFile!}"/>
        <input type="hidden" name="indexInfo.id" value="${indexInfo.id!}"/>
        <input type="hidden" id="is_preview" name="preview" value="false"/>
        <input type="hidden" id="sharePicFile" name="indexInfo.sharePicFile" value="${indexInfo.sharePicFile!}"/>
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>标题：</th>
                <td>
                    <input type="text" class="input-txt" id="title" name="indexInfo.title" notNull="true" maxLength="150" value="${indexInfo.title!}"/>
                </td>
                <td rowspan="8">
                    <div class="userImg-wrap musicImg-wrap fn-right mr-30">
                        <@ImgStyle name='videoReImg' width='280' height='150'/>
                        <p class="img"><img id="videoReImg" src="<#if indexInfo.sharePicFile?? && indexInfo.sharePicFile !="" >${DOMAIN_UPLOAD_FILE}/${indexInfo.sharePicFile}<#else>/images/default_musicCourse.jpg</#if>" alt="回顾视频图片"></p>
                        <p class="t-center pb-5"><span>支持jpg、png、bmp、jpeg、gif等图片格式<br/>建议图片尺寸比例为1:1</span></p>
                        <p class="t-center" style="position:relative;">
                            <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                            <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" imgId="videoReImg" filesize='3 MB' fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm'/>
                        </p>
                    </div>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>排序号：</th>
                <td>
                <input type="text" class="input-txt" id="orderNo" dataType="integer" notNull="true"  maxLength="4" name="indexInfo.orderNo" value="${indexInfo.orderNo!}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>是否显示：</th>
                <td>
                    <#list yesNo?if_exists as t>
                    <label>
                    <input type="radio" <#if (indexInfo.isShow?exists && indexInfo.isShow==t)||!indexInfo.isShow?exists && t=="NO" >checked</#if> class="chk" name="indexInfo.isShow" value="${t}">${t.nameValue!} 
                    </label>
                    </#list>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>链接类型：</th>
                <td>
                    <#list contentTypes?if_exists as t>
                    <#if t.value gt 0 &&t.value lt 3>
                    <label>
                    <input type="radio" <#if (indexInfo.contentType?exists && indexInfo.contentType==t)|| (!indexInfo.contentType?exists && t=="CONTENT")>checked id="isType"</#if> class="chk contentType" name="indexInfo.contentType" value="${t}">${t.nameValue!} 
                    </label>
                    </#if>
                    </#list>
                </td>
            </tr>
            
            <tr id="link_url_tr">
                <th><span class="must">*</span>链接地址：</th>
                <td>
                    <input type="text" class="input-txt t-500" id="linkUrl" name="indexInfo.linkUrl" maxLength="200" value="${indexInfo.linkUrl!}" />
                </td>
            </tr>
            <tr>
                <th><span class="must mustInput">*</span>来源：</th>
                <td>
                    <input type="text" class="input-txt" id="comeFrom" name="indexInfo.comeFrom" notNull="true" maxLength="150" value="${indexInfo.comeFrom!}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must mustInput">*</span>来源链接：</th>
                <td>
                    <input type="text" class="input-txt" id="comeUrl" name="indexInfo.comeUrl" notNull="true" maxLength="200" value="${indexInfo.comeUrl!}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must mustInput">*</span>编辑：</th>
                <td>
                    <input type="text" class="input-txt" id="infoEditor" name="indexInfo.editor" notNull="true" maxLength="50" value="${indexInfo.editor!}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must mustInput">*</span>发布时间：</th>
                <td>
                    <input type="text" class="input-txt fn-left t-165" notNull="true" id="infoDate" name="indexInfo.infoDate" value="${(indexInfo.infoDate?string('yyyy-MM-dd'))!}" onclick="$('#btwp').click();" readonly/>
                    <a hidefocus="true" href="javascript:;" id="btwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'infoDate',isShowClear:true,readOnly:true,isShowWeek:true})"></a>                       
                </td>
            </tr>
            <tr class="content_tr">
                <th><span class="must">*</span>信息内容：</th>
                <td></td>
            </tr>
            <tr class="content_tr" >
                <td colspan=3>
                    <div class="ml-20">
                        <@ueditor id="editor" name="content" content='${content!}' width='1020' height='350' />
                    </div>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                <a href="javascript:;" id="preview" class="abtn abtn-blue content_tr mr-10">保存并预览</a>
                <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                <a href="${DOMAIN_CMS}/homepage/info/videoReManage.htm" class="abtn abtn-green ml-10">返回</a></td>
            </tr>
        </table>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/videoReEditJs.ftl" />
<script type="text/javascript" src="${DOMAIN_CMS!}/js/component/date/WdatePicker.js?${appsetting.getVersionId()!}"></script>

<script>
$(function(){
    window.InfoEdit.init();
    mustInput($("#isType").val());
   
    $(".contentType").change(function(){
        var type = $(this).val();
        mustInput(type);
    });
    
    function mustInput(type){
        if(type == "LINK"){
            $(".mustInput").removeClass("must").text("");
        }else{
            $(".mustInput").addClass("must").text("*");
        }
    }
});

</script>
<form id="previewForm" method="post" action="${DOMAIN_CMS}/homepage/info/videoRePreview.htm" target="_blank">
    <input type="hidden" id="preview_content" name="content" value=""/>
    <input type="hidden" id="preview_title" name="indexInfo.title" value=""/>
    <input type="hidden" id="preview_comeFrom" name="indexInfo.comeFrom" value=""/>
    <input type="hidden" id="preview_comeUrl" name="indexInfo.comeUrl" value=""/>
    <input type="hidden" id="preview_infoDate" name="indexInfo.infoDate" value=""/>
    <input type="hidden" id="preview_editor" name="indexInfo.editor" value=""/>
</form>
</body>
</html>