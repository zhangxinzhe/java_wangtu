<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-活动专题</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=1300000 curSubModId=1300400 />
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/compete/competeManage.htm?id=${id!'0'}">活动专题</a>&nbsp;&gt;&nbsp;<span>精彩视频</span></p>
        <#include "/pages/homepage/compete/commonTab.ftl" />
        <@commonTab curLi=7 competeName='${(compete.competeName)!""} ' competeId='${id!}' competeType="${compete.competeType!}" />
        
        <form id="video_form" action="" method="post">
            <input type="hidden" name="id" value="${id!}"/>
            <input type="hidden" id="videoId" name="competeVideo.id" value="${competeVideo.id!}"/>
            <input type="hidden" name="competeVideo.competeId" value="${id!}"/>
            <div class="userInfo-wrap">
                <table class="form-table validateForm mt-10">
                    <tr>
                        <th><span class="must">*</span>名称：</th>
                        <td>
                            <input type="text" class="input-txt" name="competeVideo.name" value="${competeVideo.name!}" notNull="true" placeholder="输入视频名称" maxlength="30"/>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span>视频预览图：</th>
                        <td>
                            <#---- 图片上传       start----->
                            <div class="userImg-wrap">
                                <@ImgStyle name='picImg' width='240' height='150'/>
                                <p class="img" style="width:240px; height:150px;"><img id="picImg" src="<#if competeVideo.picFile??>${DOMAIN_UPLOAD_FILE}/${competeVideo.picFile}<#else>/images/default.jpg</#if>" alt="视频预览图"></p>
                                <p class="t-center">
                                    <span style="color:#999;">支持jpg、png、bmp、jpeg、gif等图片格式<br/>建议图片尺寸为240*150</span>
                                </p>
                                <p class="" style="position:relative;">
                                    <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                                    <@uploadPic_temp_nocss position="top:0px; left:0px;" width="70" height="26" index='1' total='2' imgId="picImg" fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" filesize='3 MB' action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm' callBack="editVideo.afterUpload(file, 1)"/>
                                    <input name="tempPicFile" type="hidden" id="tempPicFile" value=""/>
                                </p>
                            </div>
                            <#-----图片上传       end-------->  
                        </td>
                    </tr>
                    <tr>
                        <th valign="top" class="pt-10"><span class="must">*</span>视频文件：</th>
                        <td valign="top" class="pt-10">
                            <span id="fileNameSpan" class="c-orange">${competeVideo.fileName!}</span>
                            <#-----视频上传     start-------->
                            <div class="mb-5">
                                <span style="position:relative;">
                                    <a href="javascript:;" class="abtn abtn-blue">选择视频</a>
                                    <@uploadPic_temp_nocss position="top:-6px; left:0px;" width="70" height="26" index='2' total='2'  fileTypes="*.mp4;*.flv" filesize='1024 MB' action='${DOMAIN_CMS}/common/upload/uploadTemp.htm' display=true callBack="editVideo.afterUpload(file, 2)"/>
                                    <input name="tempVideoFile" type="hidden" id="tempVideoFile" value=""/>
                                </span>
                            </div>
                            <span style="color:#999;">支持mp4，flv视频格式,建议视频大小为1024M以下</span>
                            <#-----视频上传     end---------->
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span>是否显示：</th>
                        <td>
                            <#list yesNoTypes?if_exists as type>
                                <input type="radio" name="competeVideo.isShow" id="isShow${type_index}" value="${type}" 
                                <#if competeVideo?? && competeVideo.isShow?? && type==competeVideo.isShow>
                                checked="checked"
                                <#elseif !competeVideo.isShow?? && type_index==0>
                                checked="checked"
                                </#if> />
                                <label for="isShow${type_index}">${type.nameValue!}</label>
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <th>显示次序：</th>
                        <td>
                            <input type="text" class="input-txt" name="competeVideo.orderNo" value="${competeVideo.orderNo!}" dataType="integer" placeholder="输入显示次序" maxlength="4"/>
                        </td>
                    </tr>
                    <tr>
                        <th>&nbsp;</th>
                        <td>
                            <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                            <a href="${DOMAIN_CMS}/homepage/compete/tabVideo.htm?id=${id!}" class="abtn abtn-green ml-10">返回</a>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/tabVideoJs.ftl" />
<script>
    $(function(){
        editVideo.init();
    });
</script>
</body>
</html>