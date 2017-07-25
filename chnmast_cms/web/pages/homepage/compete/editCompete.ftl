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
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/compete/competeManage.htm">活动专题</a>&nbsp;&gt;&nbsp;<span><#if (compete.id)==0>新增<#else>修改</#if></span></p>
        <#if compete.id!=0>
        <#include "/pages/homepage/compete/commonTab.ftl" />
        <@commonTab curLi=1 competeName='${(compete.competeName)!""}' competeId='${id!}' competeType="${compete.competeType!}" />
        </#if>
        <form id="competeForm" method="post" action="/homepage/compete/saveCompete.htm">
            <input type="hidden" name="id" value="${(compete.id)!''}" >
            <input type="hidden" name="compete.id" value="${(compete.id)!''}" >
            <input type="hidden" id="compete_fomrFile" name="compete.formFile" value="${(compete.formFile)!''}" >
            <input type="hidden" id="compete_formName" name="compete.formName" value="${(compete.formName)!''}" >
            <input type="hidden" name="compete.competeType" value="${compete.competeType!}" >
            <table class="form-table validateForm mt-10">
                <tr>
                <th><span class="must">*</span>活动类型：</th>
                    <td>
                        <select id="competeType" class="input-sel w-200" <#if compete.id!=0>disabled</#if>>
                        <#list competeTypes?if_exists as type>
                            <option value="${type!''}" <#if compete.competeType?? && compete.competeType == type>selected</#if>>${type.nameValue!}</option>
                        </#list>
                        </select>
                    </td>
                </tr>
                <tr id="competeBatchTr" style="display:none;">
                    <th><span class="must">*</span>期号：</th>
                    <td>
                        <input id="competeBatch" name="compete.competeBatch" dataValue="${(compete.competeBatch)!''}" value="${(compete.competeBatch)!''}" notNull="true" dataType="integer" maxLength="6" type="text" class="input-txt" placeholder="请输入期号">
                        <span class="onTips">注：最多6位数字，如：201601。黄龙奖同一期合唱比赛和乐队比赛，期号需相同，建议自动生成</span>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>活动名称：</th>
                    <td>
                        <input name="compete.competeName" value="${(compete.competeName)!''}" notNull="true" maxLength="150" type="text" class="input-txt"  placeholder="请输入活动名称">
                    </td>
                </tr>
                <tr>
                    <th valign="top">报名表：</th>
                    <td valign="top">
                        <#-----文件上传     start-------->
                        <span id="form_file"><#if compete.formFile?? && compete.formFile!=''><a href="${DOMAIN_UPLOAD_FILE}/${compete.formFile!}">${compete.formName!}</a>&nbsp;<a href="javascript:;" id="form_file_del" dataValue="${compete.id!}">[删除]</a></#if></span>
                        <div class=" mt-5">
                            <span style="position:relative;">
                                <a href="javascript:;" class="abtn abtn-blue">选择文件</a>
                                <@uploadPic_temp_nocss position="top:0px; left:0px;" width="70" height="26" fileTypes="*.*" filesize='20 MB' action='${DOMAIN_CMS}/common/upload/uploadTemp.htm' display=true callBack="afterUpload(file)"/>
                                <input name="uploadTempFileName" type="hidden" id="uploadTempFileName" />
                            </span>
                        </div>
                        <div class="" style="line-height:26px">注：建议大小为20M以下</div>
                       <#-----文件上传     end---------->
                    </td>
                </tr>
                
                <tr id="competeFeeTr" >
                    <th>报名费：</th>
                    <td>
                        <input id="competeFee" name="compete.competeFee" value="${(compete.competeFee)!'100'}" regex="/(^[0-9]{1,5}$)|(^[0-9]{1,5}\.[0-9]{1,2}$)/" <#if compete?exists && compete.hasCompeteFee==true>readonly</#if> regexMsg="请输入合法的数值!小数点保留两位!" maxLength="8" type="text" class="input-txt t-200"  placeholder="请输入报名费">
                    </td>
                </tr>
                
                <tr>
                    <th><span class="must">*</span>活动时间：</th>
                    <td>
                        <input type="text"  class="input-txt fn-left" style="width:80px;" readonly onkeydown="if(window.event.keyCode == 8){return false;}" onclick="$('#btwp').click();" name="compete.beginTime" id="beginTime" <#if compete?? && compete.beginTime?exists>value="${compete.beginTime?string("yyyy-MM-dd")!''}"</#if>>
                        <a hidefocus="true" href="javascript:;" id="btwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'beginTime',isShowClear:true,readOnly:true,isShowWeek:true,maxDate:'#F{$dp.$D(\'endTime\')}'})"></a>
                        <span class="fn-left mt-5 ml-10">至</span>
                        <input type="text" class="input-txt fn-left ml-10" style="width:80px;" readonly onkeydown="if(window.event.keyCode == 8){return false;}" onclick="$('#etwp').click();" name="compete.endTime" id="endTime" <#if compete?? && compete.endTime?exists>value="${compete.endTime?string("yyyy-MM-dd")!''}"</#if>>
                        <a hidefocus="true" href="javascript:;" id="etwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'endTime',isShowClear:true,readOnly:true,isShowWeek:true,minDate:'#F{$dp.$D(\'beginTime\')}'})"></a>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>报名时间：</th>
                    <td>
                        <input type="text" notNull="true" class="input-txt fn-left" style="width:80px;" readonly onkeydown="if(window.event.keyCode == 8){return false;}" onclick="$('#btwp2').click();" name="compete.attendBeginTime" id="attendBeginTime" <#if compete?? && compete.attendBeginTime?exists>value="${compete.attendBeginTime?string("yyyy-MM-dd")!''}"</#if>>
                        <a hidefocus="true" href="javascript:;" id="btwp2" class="i-date fn-left mt-5 ml-10" onclick="if(!checkGameTime()){return;};WdatePicker({el:'attendBeginTime',isShowClear:true,readOnly:true,isShowWeek:true,maxDate:'#F{$dp.$D(\'attendEndTime\')||$dp.$D(\'endTime\')}',minDate:'#F{$dp.$D(\'beginTime\')}'})"></a>
                        <span class="fn-left mt-5 ml-10">至</span>
                        <input type="text" notNull="true" class="input-txt fn-left ml-10" style="width:80px;" readonly onkeydown="if(window.event.keyCode == 8){return false;}" onclick="$('#etwp2').click();" name="compete.attendEndTime" id="attendEndTime" <#if compete?? && compete.attendEndTime?exists>value="${compete.attendEndTime?string("yyyy-MM-dd")!''}"</#if>>
                        <a hidefocus="true" href="javascript:;" id="etwp2" class="i-date fn-left mt-5 ml-10" onclick="if(!checkGameTime()){return;};WdatePicker({el:'attendEndTime',isShowClear:true,readOnly:true,isShowWeek:true,minDate:'#F{$dp.$D(\'attendBeginTime\')||$dp.$D(\'beginTime\')}',maxDate:'#F{$dp.$D(\'endTime\')}'})"></a>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>投票时间：</th>
                    <td>
                        <input type="text" notNull="true" class="input-txt fn-left" style="width:80px;" readonly onkeydown="if(window.event.keyCode == 8){return false;}" onclick="$('#btwp3').click();" name="compete.voteBeginTime" id="voteBeginTime" <#if compete?? && compete.voteBeginTime?exists>value="${compete.voteBeginTime?string("yyyy-MM-dd")!''}"</#if>>
                        <a hidefocus="true" href="javascript:;" id="btwp3" class="i-date fn-left mt-5 ml-10" onclick="if(!checkGameTime()){return;};WdatePicker({el:'voteBeginTime',isShowClear:true,readOnly:true,isShowWeek:true,maxDate:'#F{$dp.$D(\'voteEndTime\')||$dp.$D(\'endTime\')}',minDate:'#F{$dp.$D(\'beginTime\')}'})"></a>
                        <span class="fn-left mt-5 ml-10">至</span>
                        <input type="text" notNull="true" class="input-txt fn-left ml-10" style="width:80px;" readonly onkeydown="if(window.event.keyCode == 8){return false;}" onclick="$('#etwp3').click();" name="compete.voteEndTime" id="voteEndTime" <#if compete?? && compete.voteEndTime?exists>value="${compete.voteEndTime?string("yyyy-MM-dd")!''}"</#if>>
                        <a hidefocus="true" href="javascript:;" id="etwp3" class="i-date fn-left mt-5 ml-10" onclick="if(!checkGameTime()){return;};WdatePicker({el:'voteEndTime',isShowClear:true,readOnly:true,isShowWeek:true,minDate:'#F{$dp.$D(\'voteBeginTime\')||$dp.$D(\'beginTime\')}',maxDate:'#F{$dp.$D(\'endTime\')}'})"></a>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>是否进行中：</th>
                    <td>
                        <select name="isDoing" class="input-sel w-50 input-txt-error">
                            <option value="0" <#if compete?? && (compete.isDoing)?exists && (compete.isDoing).getValue()==0>selected</#if> >否</option>
                            <option value="1" <#if compete?? && (compete.isDoing)?exists && (compete.isDoing).getValue()==1>selected</#if> >是</option>
                        </select>
                    </td>
                </tr>
                <#-- 屏蔽  2016年5月11日
                <tr>
                    <th>活动章程：</th>
                    <td>【手机端】(建议字体格式为：宋体，字体大小：14px)</td>
                </tr>
                <tr class="content_tr">
                    <td colspan=2>
                    <div class="ml-20">
                        <@ueditor id="editor" name="content" content='${content!}' width='1020' height='250' />
                    </div>
                    </td>
                </tr>
                -->
                <tr>
                    <th>关联课程：</th>
                    <td>
                        <@courseSelect_new cId="compete_courseId" cIdName="compete.courseId" cIdValue="${compete.courseId!}" cnId="compete_courseName" cnName="compete.courseName" cnValue="${compete.courseName!}" />
                    </td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td>
                    <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                    <a href="${DOMAIN_CMS}/homepage/compete/competeManage.htm" class="abtn abtn-green ml-10">返回</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/editCompeteJs.ftl" />
<script>
    $(function(){
        editCompete.init("${compete.id?default('')}");
    });
    
    function afterUpload(file) {
        document.getElementById("uploadTempFileName").value = file.name;
    }
</script>
</body>
</html>