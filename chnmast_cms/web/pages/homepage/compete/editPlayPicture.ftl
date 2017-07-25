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
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/compete/competeManage.htm">活动专题</a>&nbsp;&gt;&nbsp;<span>图片轮播</span></p>
        <#include "/pages/homepage/compete/commonTab.ftl" />
        <@commonTab curLi=6 competeName='${(compete.competeName)!""}' competeId='${id!"0"}' competeType="${compete.competeType!}" />
        <form id="pic_form" action="" method="post">
            <input type="hidden" name="id" value="${id!''}"/>
            <input type="hidden" id="picId" name="indexAdvId" value="${(indexAdv.id)!'0'}"/>
            <input type="hidden" name="indexAdv.pictureFile" value="${(indexAdv.pictureFile)!}"/>
            <input type="hidden" name="indexAdv.localFile" value="${(indexAdv.localFile)!}"/>
            <div class="userInfo-wrap">
                <table class="form-table validateForm mt-10">
                    <tr>
                       <th><span class="must">*</span>图片：</th>
                       <td>
                       <div class="userImg-wrap ml-20">
                            <@ImgStyle name='picImg' width='240' height='165'/>
                            <p class="img" style="width:240px;height:165px;margin:0 auto"><img id="picImg" src="<#if (indexAdv.pictureFile)?? && indexAdv.pictureFile !=''>${DOMAIN_UPLOAD_FILE}/${indexAdv.pictureFile}<#else>/images/default_compete_playPic.jpg</#if>" alt="活动滚动图片"></p>
                            <p class="t-center">
                                <span>支持jpg、png、bmp、jpeg、gif等图片格式<br/>建议图片尺寸为290*200</span>
                            </p>
                            <p class="t-center" style="position:relative;">
                                <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                                <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" imgId="picImg" fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" filesize='3 MB' action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm'/>
                            </p>
                        </div>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span>图片标题：</th>
                        <td>
                            <input type="text" class="input-txt" id="picTitle" name="indexAdv.title" value="${(indexAdv.title)!}" notNull="true" placeholder="输入标题" maxlength="50"/>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span>是否显示：</th>
                        <td>
                            <#list yesNoTypes?if_exists as type>
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
                    <tr id="linkTr" >
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
                        <th>&nbsp;</th>
                        <td>
                            <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                            <a href="${DOMAIN_CMS}/homepage/compete/tabPlayPicture.htm?id=${id!'0'}" class="abtn abtn-green ml-10">返回</a>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/playPictureEditJs.ftl" />
<script>
    $(function(){
        playPictureEdit.init();
    });
</script>
</body>
</html>