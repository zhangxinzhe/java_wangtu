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
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/compete/competeManage.htm">活动专题</a>&nbsp;&gt;&nbsp;<span>活动图集</span></p>
        <#include "/pages/homepage/compete/commonTab.ftl" />
        <@commonTab curLi=3 competeName='${(compete.competeName)!""}' competeId='${id!"0"}' competeType="${compete.competeType!}" />
        
        <form id="pic_form" action="" method="post">
            <input type="hidden" name="id" value="${id!''}"/>
            <input type="hidden" id="pictureId" name="picture.id" value="${picture.id!''}"/>
            <div class="userInfo-wrap">
                <table class="form-table validateForm mt-10">
                    <tr>
                       <th><span class="must">*</span>图片：</th>
                       <td>
                       <div class="userImg-wrap ml-20">
                            <@ImgStyle name='picImg' width='240' height='165'/>
                            <p class="img" style="width:240px;height:165px;margin:0 auto"><img id="picImg" src="<#if (picture.pictureFile)?? && picture.pictureFile !=''>${DOMAIN_UPLOAD_FILE}/${picture.pictureFile}<#else>/images/default_compete_playPic.jpg</#if>" alt=""></p>
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
                        <th><span class="must">*</span>名称：</th>
                        <td>
                            <input type="text" class="input-txt" id="picTitle" name="picture.pictureName" value="${(picture.pictureName)!}" notNull="true" placeholder="输入名称" maxlength="50"/>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span>是否显示：</th>
                        <td>
                            <#list yesNoTypes?if_exists as type>
                                <span <#if type_index != 0>class="ml-20"</#if>>
                                    <input type="radio" name="picture.isShow" id="isShow${type_index}" value="${type}" <#if picture.isShow?? && type==picture.isShow> checked="checked"<#elseif (!picture.isShow??) && type_index==0> checked="checked"</#if> />
                                    <label for="isShow${type_index}">${type.nameValue}</label>
                                </span>
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <th>显示序号：</th>
                        <td>
                            <input type="text" class="input-txt" id="showOrder" name="picture.showOrder" value="${(picture.showOrder)!}" placeholder="输入显示序号" dataType="integer" maxlength="4"/>
                        </td>
                    </tr>
                    <tr>
                        <th>&nbsp;</th>
                        <td>
                            <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                            <a href="${DOMAIN_CMS}/homepage/compete/tabPicture.htm?id=${id!'0'}" class="abtn abtn-green ml-10">返回</a>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/tabPictureJs.ftl" />
<script>
    $(function(){
        editPicture.init();
    });
</script>
</body>
</html>