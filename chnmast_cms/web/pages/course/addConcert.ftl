<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-音乐会</title>

</head>
<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <div id="content">
        <p class="crumbs">当前位置：课程管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/concert/concertManage.htm">音乐会</a>&nbsp;&gt;&nbsp;<span><#if !course.id?exists || course.id == 0>新增<#else>修改</#if></span></p>
        <ul class="public-tab fn-clear mt-10">
            <li id="concertLi" class="current">音乐会信息</li>
            <li id="TimeLi" dataValue="${course.id!}">场次和票价</li>
        </ul>
        <form id="course_form" action="" method="post">
            <input type="hidden" id="courseId" name="course.id" value="${course.id!}"/>
            <table class="form-table validateForm mt-10">
                <tr>
                    <th><span class="must">*</span>音乐会主题：</th>
                    <td>
                        <input type="text" class="input-txt" id="courseName" name="course.courseName" value="${course.courseName!}" notNull="true" placeholder="输入音乐会主题" maxlength="40"/>
                    </td>
                    <td rowspan="7">
                        <div class="userImg-wrap musicImg-wrap fn-right mr-30">
                            <@ImgStyle name='courseImg' width='240' height='150'/>
                            <p class="img"><img id="courseImg" src="<#if course.pictureFile??>${DOMAIN_UPLOAD_FILE}/${course.pictureFile}<#else>/images/default_musicCourse.jpg</#if>" alt="音乐会图片"></p>
                            <p class="t-center pb-5"><span>支持jpg、png、bmp、jpeg、gif等图片格式<br/>建议图片尺寸为470*290</span></p>
                            <p class="t-center" style="position:relative;">
                                <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                                <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" imgId="courseImg" filesize='3 MB' fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm'/>
                            </p>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>音乐会类型：</th>
                    <td>
                        <#list concertTypes?if_exists as type>
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
                        <select class="input-sel" id="wxbId" name="course.wxbId" style="width:212px;" notNull="true">
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
                    <th>预售时间：</th>
                    <td>
                        <input type="text" class="input-date" style="width:200px;" id="bookingTime" name="course.bookingTime" readonly 
                            value="<#if course?? && course.bookingTime??>${course.bookingTime?string('yyyy-MM-dd HH:mm')}</#if>" 
                            onkeydown="if(window.event.keyCode == 8){return false;}"
                            onclick="WdatePicker({isShowClear:false,readOnly:true,isShowWeek:true,dateFmt:'yyyy-MM-dd HH:mm', maxDate:'#F{$dp.$D(\'saleTime\')}'})" />
                        <span class="onTips">选择开始预售/预定时间</span>
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <th><span class="must">*</span>售票时间：</th>
                    <td>
                        <input type="text" class="input-date" style="width:200px;" id="saleTime" name="course.saleTime" readonly notNull="true" 
                            value="<#if course?? && course.saleTime??>${course.saleTime?string('yyyy-MM-dd HH:mm')}</#if>"  
                            onkeydown="if(window.event.keyCode == 8){return false;}"
                            onclick="WdatePicker({isShowClear:false,readOnly:true,isShowWeek:true,dateFmt:'yyyy-MM-dd HH:mm', minDate:'#F{$dp.$D(\'bookingTime\')}'})" />
                        <span class="onTips">选择开始售票时间</span>
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
                    <th valign="top" class="pt-10">音乐会介绍：</th>
                    <td colspan="2" valign="top" class="pt-10">
                        <textarea class="text-area fn-left" id="introduction" name="course.introduction" style="width:98.5%;">${course.introduction!}</textarea>
                    </td>
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
                    <td colspan="2">${course.applyInfo!}</td>
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
                        <a href="${DOMAIN_CMS}/concert/concertManage.htm" class="abtn abtn-green ml-10" <#--onclick="javascript:window.history.back();"-->>返回</a>
                        <#if course.courseSource?exists && course.courseSource==frontApply && course.auditStatus==auditing>
                        <span class="ml-30 c-red">注：必须选择直播服务器，才能审核通过！ </span>
                        </#if>
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

<#include "/pages/jsinclude/course/addConcertjs.ftl"/>
<script>
    $(function(){
        addConcert.init();
        
        <#-- 初始化场次列表
        <#if timeList?? && timeList?has_content>
        <#list timeList?if_exists as time>
            var bhHtml = addConcert.buildHourSelectHtml("beginHours", ${time.beginTime?string('HH')}, ${time.isHistory?string});
            var bmHtml = addConcert.buildMinuteSelectHtml("beginMinutes", ${time.beginTime?string('mm')}, ${time.isHistory?string});
            var ehHtml = addConcert.buildHourSelectHtml("endHours", ${time.endTime?string('HH')}, ${time.isHistory?string});
            var emHtml = addConcert.buildMinuteSelectHtml("endMinutes", ${time.endTime?string('mm')}, ${time.isHistory?string});
            var data = "${time.beginTime?string('yyyy-MM-dd')}";
            var str = "<tr>" +
                "<td class='t-center'>${time_index+1}</td>" +
                "<td><input type='text' <#if time.isHistory>class='input-txt input-txt-disabled'<#else>class='input-date' onclick='WdatePicker({isShowClear:false,readOnly:true,isShowWeek:true,minDate:\"%y-%M-%d\"})'</#if> style='width:90px;' name='days' readonly value='"+data+"' isHistoryData='${time.isHistory?string}'/></td>" + 
                "<td>" + bhHtml + bmHtml + "</td>" +
                "<td>" + ehHtml + emHtml + "</td>" +
                "<td><input type='text' <#if time.isHistory>class='input-txt input-txt-disabled' readonly<#else>class='input-txt'</#if> style='width:250px;' name='timeTitles' value='${time.title!}' maxlength='75' /></td>" +
                "<td><#if !time.isHistory><a href='javascript:;' class='i-del' title='删除' dataValue='${time.id!}'></a></#if></td>" +
                "</tr>";
            $("#time_tbody").append(str);
        </#list>
        </#if> -->
        
    });
</script>
</body>
</html>