<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-基地课程</title>

</head>
<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <div id="content">
        <p class="crumbs">当前位置：课程管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/course/courseManage.htm">基地课程</a>&nbsp;&gt;&nbsp;<span><#if !course.id?exists || course.id == 0>新增<#else>修改</#if></span></p>
        <ul class="public-tab fn-clear mt-10">
            <li id="courseLi" class="current">课程信息</li>
            <li id="TimeLi" dataValue="${(course.id)!}">课次和价格</li>
        </ul>
        <form id="course_form" action="" method="post">
            <input type="hidden" id="courseId" name="course.id" value="${course.id!}"/>
            <table class="form-table validateForm mt-10">
                <tr>
                    <th><span class="must">*</span>课程名称：</th>
                    <td>
                        <input type="text" class="input-txt" id="courseName" name="course.courseName" value="${course.courseName!}" notNull="true" placeholder="输入课程名称" maxlength="40"/>
                    </td>
                    <td rowspan="7" valign="top">
                        <div class="userImg-wrap musicImg-wrap fn-right mr-30">
                            <@ImgStyle name='courseImg' width='240' height='150'/>
                            <p class="img"><img id="courseImg" src="<#if course.pictureFile??>${DOMAIN_UPLOAD_FILE}/${course.pictureFile}<#else>/images/default_agencyCourse.jpg</#if>" alt="课程图片"></p>
                            <p class="t-center pb-5"><span>支持jpg、png、bmp、jpeg、gif等图片格式<br/>建议图片尺寸为470*290</span></p>
                            <p class="t-center" style="position:relative;">
                                <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                                <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" imgId="courseImg" filesize='3 MB' fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm'/>
                            </p>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>课程类型：</th>
                    <td>
                        <#list agencyCourseTypes?if_exists as type>
                            <span <#if type_index != 0>class="ml-15"</#if>><input type="radio" class="chk" name="course.courseType" value="${type}" <#if course.courseType?? && type==course.courseType> checked="checked"</#if> />${type.nameValue}</span>
                        </#list>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>主讲名师：</th>
                    <td>
                        <div class="select-analog fn-left">
                            <input type="hidden" id="teaId" name="course.teaId" value="${course.teaId!}" />
                            <input type="text" class="input-txt" id="teaRealName" name="course.teaRealName" value="<#if course.id gt 0 && course.teaRealName?default('') == ''>-<#else>${course.teaRealName!}</#if>" readOnly notNull="true"/>
                            <a href="javascript:;" class="open selectTea" flag="1"></a>
                        </div>
                        <span class="onTips fn-left ml-5" style="line-height:26px">选择主讲名师</span>
                    </td>
                </tr>
                <tr>
                    <th>助教：</th>
                    <td>
                        <div class="select-analog fn-left w-200">
                            <input type="hidden" id="assId" name="course.assId" value="${course.assId!}" />
                            <input type="text" class="input-txt" id="assRealName" name="course.assRealName" value="${course.assRealName!}" readOnly/>
                            <a href="javascript:;" class="open selectTea" flag="2"></a>
                        </div>
                        <span class="onTips fn-left ml-5" style="line-height:26px">选择助教</span>
                    </td>
                </tr>
                <tr>
                    <th>地点：</th>
                    <td>
                        <input type="text" class="input-txt" id="place" name="course.place" value="${course.place!}" maxlength="75"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>直播服务器：</th>
                    <td>
                        <select class="input-sel w-200" id="wxbId" name="course.wxbId" notNull="true">
                            <option value="">请选择</option>
                            <#list wxbList?if_exists as wxb>
                                <option value="${wxb.id}" <#if course?? && course.wxbId?? && wxb.id == course.wxbId>selected="selected"</#if>>${wxb.name}</option>
                            </#list>
                        </select>
                        <span class="onTips">选择直播工具（无限宝）服务器</span>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>优惠券使用：</th>
                    <td>
                        <span><input type="radio" class="chk" name="course.isUseCoupon" value="YES" <#if !course.isUseCoupon?? || course.isUseCoupon=='YES'>checked</#if>/>允许</span>
                        <span class="ml-15"><input type="radio" class="chk" name="course.isUseCoupon" value="NO" <#if course.isUseCoupon?? && course.isUseCoupon=='NO'>checked</#if>/>不允许</span>
                        <span class="onTips">购买此课程时，是否允许使用优惠券折扣</span>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>课程定价：</th>
                    <td>
                        <span><input type="radio" class="chk" name="course.isBuyAll" value="NO" <#if !course.isBuyAll?? || course.isBuyAll=='NO'>checked</#if>/>否</span>
                        <span class="ml-15"><input type="radio" class="chk" name="course.isBuyAll" value="YES" <#if course.isBuyAll?? && course.isBuyAll=='YES'>checked</#if>/>是</span>
                        <span class="onTips">此课程是否只允许整课购买（即不允许单课次购买）</span>
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <th>关联高校：</th>
                    <td>
                        <#include "/pages/course/selectCollege.ftl" />
                        <span class="onTips fn-left ml-5 mt-5">高校风采展示，关联高校</span>
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <th valign="top" class="pt-10">课程介绍：</th>
                    <td colspan="2" valign="top" class="pt-10">
                        <textarea class="text-area fn-left" id="introduction" name="course.introduction" style="width:98.5%;">${course.introduction!}</textarea>
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <th valign="top" class="pt-10">备注：</th>
                    <td colspan="2" valign="top" class="pt-10">
                        <textarea class="text-area fn-left" id="remark" name="course.remark" style="width:98.5%;">${course.remark!}</textarea>
                    </td>
                </tr>
                <#if course.courseSource?exists && course.courseSource==frontApply>
                <tr>
                    <th>申请时间：</th>
                    <td colspan="2">${(course.applyDate?string('yyyy-MM-dd HH:mm'))!}</td>
                </tr>
                <tr>
                    <th>申请描述：</th>
                    <td colspan="2">${course.applyInfo!}
                    </td>
                </tr>
                <tr>
                    <th>审核状态：</th>
                    <td colspan="2"><span class="c-red">${course.auditStatus.nameValue!}</span></td>
                </tr>
                <#if course.auditStatus !=auditing >
                    <tr>
                        <th>审核日期：</th>
                        <td>
                            ${(course.auditDate?string('yyyy-MM-dd HH:mm'))!}
                        </td>
                    </tr> 
                     <tr>
                        <th>审核人姓名：</th>
                        <td>
                            ${course.auditRealName!}
                        </td>
                    </tr> 
                    <#if course.auditStatus ==auditNoPass>
                    <tr>
                        <th>审核信息：</th>
                        <td>
                            ${course.auditMsg!}
                        </td>
                    </tr> 
                    </#if>
                </#if>
                </#if>
                <tr>
                    <th>&nbsp;</th>
                    <td colspan="2">
                        <#if course.courseSource?exists && course.courseSource==frontApply && course.status==notPublish>
                            <#if course.auditStatus==auditing>
                              <a href="javascript:;" id="passBtn" class="abtn abtn-blue">审核通过</a>
                              <a href="javascript:;" id="noPassBtn" class="abtn abtn-blue">审核不通过</a>
                            <#else>
                              <a href="javascript:;" id="cancelBtn" class="abtn abtn-blue">取消审核</a>
                            </#if>
                        </#if>
                        <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                        <a href="${DOMAIN_CMS}/course/courseManage.htm" class="abtn abtn-green ml-10" <#--onclick="javascript:window.history.back();"-->>返回</a>
                        <#if course.courseSource?exists && course.courseSource==frontApply && course.auditStatus==auditing>
                        <span class="ml-30 c-red">注：必须选择直播服务器，才能审核通过！ </span>
                        </#if>
                        <span class="ml-30 c-red" id="isBuyAllMsg" style="<#if !course.isBuyAll?? || course.isBuyAll=='NO'>display:none</#if>">注：课程定价后，直播课次票价必须大于0，否则课程无法发布<span>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

<#-- 审核不通过弹窗  -->
<div class="popUp-layer layer-600" style="display:none;" id="popUp2">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>审核不通过</span></p>
    <div class="wrap" style="height:145px;">
        <div class="org-search-tt mb-5">
            <div class="org-search-td">
                <span>审核信息：</span>
            </div>
            <textarea class="text-area t-500 mt-5" id="popUp2_auditMsg" maxLength="400"></textarea>
        </div>
        <span class="c-red ml-5" id="popUp2_tips"></span>
    </div>
    <p class="dd">
        <a class="abtn abtn-blue" href="javascript:;" id="popUp2_audit"><span>确定</span></a>
        <a class="abtn abtn-blue close" href="javascript:;" ><span>关闭</span></a>
    </P>
</div>
<@footer />

<#-- 选择讲师、助教 -->
<div class="popUp-layer" id="selectTeaDiv" style="display:none;"></div>

<#include "/pages/jsinclude/course/addCoursejs.ftl"/>
<script>
    $(function(){
        addCourse.init();
    });
</script>
</body>
</html>