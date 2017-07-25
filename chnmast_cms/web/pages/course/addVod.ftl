<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-点播视频</title>

</head>
<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <div id="content">
        <p class="crumbs">当前位置：课程管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/vod/vodManage.htm">点播视频</a>&nbsp;&gt;&nbsp;<span><#if !vod.id?exists || vod.id == 0>新增<#else>修改</#if></span></p>
        <ul class="public-tab fn-clear mt-10">
            <li id="courseLi" class="current">课程信息</li>
            <li id="TimeLi" dataValue="${(vod.id)!}">课次和价格</li>
        </ul>
        <form id="vod_form" action="" method="post">
            <input type="hidden" id="vodId" name="vod.id" value="${vod.id!}"/>
            <table class="form-table validateForm mt-10">
                <tr>
                    <th><span class="must">*</span>课程名称：</th>
                    <td>
                        <input type="text" class="input-txt" id="courseName" name="vod.courseName" value="${vod.courseName!}" notNull="true" placeholder="输入课程名称" maxlength="40"/>
                    </td>
                    <td rowspan="5" valign="top">
                        <div class="userImg-wrap musicImg-wrap fn-right mr-30">
                            <@ImgStyle name='courseImg' width='240' height='150'/>
                            <p class="img"><img id="courseImg" src="<#if vod.pictureFile??>${DOMAIN_UPLOAD_FILE}/${vod.pictureFile}<#else>/images/default_agencyCourse.jpg</#if>" alt="课程图片"></p>
                            <p class="t-center pb-5"><span>支持jpg、png、bmp、jpeg、gif等图片格式<br/>建议图片尺寸为470*290</span></p>
                            <p class="t-center" style="position:relative;">
                                <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                                <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" imgId="courseImg" filesize='3 MB' fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm'/>
                            </p>
                        </div>
                    </td>
                </tr>
                <#--
                <tr>
                    <th><span class="must">*</span>主讲名师：</th>
                    <td>
                        <div class="select-analog fn-left">
                            <input type="hidden" id="teaId" name="vod.teaId" value="${vod.teaId!}" />
                            <input type="text" class="input-txt" id="teaRealName" name="vod.teaRealName" value="${vod.teaRealName!}" readOnly notNull="true"/>
                            <a href="javascript:;" class="open selectTea" ></a>
                        </div>
                        <span class="onTips fn-left ml-5" style="line-height:26px">选择主讲名师</span>
                    </td>
                </tr>
                -->
                <tr>
                    <th><span class="must">*</span>主讲名师：</th>
                    <td>
                        <input type="text" class="input-txt" name="vod.teaRealName" value="${vod.teaRealName!}" notNull="true" placeholder="输入主讲名师" maxlength="75"/>
                        <span class="onTips">维护主讲或者举办方</span>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>优惠券使用：</th>
                    <td>
                        <span><input type="radio" class="chk" name="vod.isUseCoupon" value="YES" <#if !vod.isUseCoupon?? || vod.isUseCoupon=='YES'>checked</#if>/>允许</span>
                        <span class="ml-15"><input type="radio" class="chk" name="vod.isUseCoupon" value="NO" <#if vod.isUseCoupon?? && vod.isUseCoupon=='NO'>checked</#if>/>不允许</span>
                        <span class="onTips">购买此课程时，是否允许使用优惠券折扣</span>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>课程定价：</th>
                    <td>
                        <span><input type="radio" class="chk" name="vod.isBuyAll" value="NO" <#if !vod.isBuyAll?? || vod.isBuyAll=='NO'>checked</#if>/>否</span>
                        <span class="ml-15"><input type="radio" class="chk" name="vod.isBuyAll" value="YES" <#if vod.isBuyAll?? && vod.isBuyAll=='YES'>checked</#if>/>是</span>
                        <span class="onTips">此课程是否只允许整课购买（即不允许单课次购买）</span>
                    </td>
                </tr>
                <tr>
                    <th>关联高校：</th>
                    <td>
                        <#include "/pages/course/selectCollege.ftl" />
                        <span class="onTips fn-left ml-5 mt-5">高校风采展示，关联高校</span>
                    </td>
                </tr>
                <tr>
                    <th valign="top" class="pt-10">课程介绍：</th>
                    <td colspan="2" valign="top" class="pt-10">
                        <textarea class="text-area fn-left" id="introduction" name="vod.introduction" style="width:98.5%;">${vod.introduction!}</textarea>
                    </td>
                </tr>
                <tr>
                    <th valign="top" class="pt-10">备注：</th>
                    <td colspan="2" valign="top" class="pt-10">
                        <textarea class="text-area fn-left" id="remark" name="vod.remark" style="width:98.5%;">${vod.remark!}</textarea>
                    </td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td colspan="2">
                        <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                        <a href="${DOMAIN_CMS}/vod/vodManage.htm" class="abtn abtn-green ml-10" <#--onclick="javascript:window.history.back();"-->>返回</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<@footer />

<#-- 选择讲师 -->
<div class="popUp-layer" id="selectTeaDiv" style="display:none;"></div>

<#include "/pages/jsinclude/course/addVodjs.ftl"/>
<script>
    $(function(){
        addVod.init();
    });
</script>
</body>
</html>