<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-联合会会员</title>
</head>

<body>
<@header curAppId=appId/>
<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/musicTutorManage.htm">音乐导师</a>&nbsp;&gt;&nbsp;<span>基本信息修改</span></p>
        <ul class="public-tab fn-clear mt-10">
            <span class="tt">${tutor.realName!}</span>
            <li class="current">个人基本信息</li>
        </ul>
        <form id="musicTutorForm" method="post" action="${DOMAIN_CMS}/basic/saveMusicTutor.htm">
        <input type="hidden" id="tutorId" name="tutor.id" value="${tutor.id!}"/>
        <input type="hidden" name="tutor.idcardFile" value="${tutor.idcardFile!}"/>
        <input type="hidden" name="tutor.handIdcardFile" value="${tutor.handIdcardFile!}"/>
        <input type="hidden" id="photoFile" name="tutor.photoFile" value="${(tutor.photoFile)!''}"/>
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>姓名：</th>
                <td colspan="1">
                    <input type="text" class="input-txt"  name="tutor.realName" notNull="true" maxlength="25" placeholder="输入姓名" value="${(tutor.realName)!''}"/>
                </td>
                <td colspan="3" rowspan="7" valign="top">
                    <div class="userImg-wrap musicImg-wrap fn-right mr-30">
                        <@ImgStyle name='photoImg' width='130' height='100'/>
                        <p class="t-center img" style="height:100px;"><img id="photoImg" src="<#if tutor.photoFile?default('')!=''>${DOMAIN_UPLOAD_FILE}/${tutor.photoFile!}<#else>/images/default.jpg</#if>" alt="个人照片"></p>
                        <p class="t-center pb-5"><span>支持jpg、png、bmp、jpeg、gif等图片格式<br/>建议上传2寸电子照，大小不超过2M</span></p>
                        <p class="t-center" style="position:relative;">
                            <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                            <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" imgId="photoImg" filesize='2 MB' fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm'/>
                        </p>
                    </div>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>性别：</th>
                <td colspan="1">
                    <#list sexTypes?if_exists as type>
                        <span <#if type_index != 0>class="ml-30"</#if>><input type="radio" class="chk" name="tutor.sex" value="${type}" <#if tutor.sex?? && type==tutor.sex> checked="checked"</#if> />${type.nameValue}</span>
                    </#list>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>民族：</th>
                <td colspan="1">
                    <input type="text" class="input-txt" name="tutor.nation" notNull="true" maxlength="6" placeholder="输入民族" value="${(tutor.nation)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>出生日期：</th>
                <td colspan="1">
                    <input type="text" class="input-txt fn-left" style="width:80px;" readonly onkeydown="if(window.event.keyCode == 8){return false;}" onclick="$('#btwp').click();" name="tutor.birthday" id="birthday" <#if tutor?? && tutor.birthday?exists>value="${tutor.birthday?string("yyyy-MM-dd")}"</#if>>
                    <a hidefocus="true" href="javascript:;" id="btwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'birthday',isShowClear:true,readOnly:true,isShowWeek:true})"></a>
                </td>
            </tr>
            <#-- 身份证照片
            <tr>
                <th>身份证照片：</th>
                <td colspan="1" colspan="2">
                    <#if tutor.idcardFile?default('')!=''>
                        <a href="/basic/downloadFile4MusicTutor.htm?tutorId=${tutor.id!}&flag=1">${tutor.idcardFileName!}</a>
                    <#else>未上传或文件丢失
                    </#if>
                    &nbsp;&nbsp;
                    <#if tutor.idcardFile?default('')!=''>
                        <a href="/basic/downloadFile4MusicTutor.htm?tutorId=${tutor.id!}&flag=2">${tutor.idcardFileOtherName!}</a>
                    <#else>未上传或文件丢失
                    </#if>
                </td>
            </tr>
            <tr>
                <th>手持身份证照：</th>
                <td colspan="1">
                    <#if tutor.handIdcardFile?default('')!=''>
                        <a href="/basic/downloadFile4MusicTutor.htm?tutorId=${tutor.id!}&flag=3">${tutor.handIdcardFileName!}</a>
                    <#else>未上传或文件丢失
                    </#if>
                </td>
            </tr>
             -->

            <#-- 基本信息  -->
            <tr>
                <th><span class="must">*</span>工作单位：</th>
                <td colspan="1">
                    <input type="text" class="input-txt" name="tutor.workUnit" notNull="true" maxlength="25" placeholder="输入工作单位" value="${(tutor.workUnit)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>职务及职称：</th>
                <td colspan="2">
                    <input type="text" class="input-txt" name="tutor.title" notNull="true" maxlength="25" placeholder="输入职务及职称" value="${(tutor.title)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>联系电话：</th>
                <td colspan="1">
                    <input type="text" class="input-txt" id="phone" name="tutor.phone" notNull="true" maxlength="15" placeholder="输入联系电话" value="${(tutor.phone)!''}"/>
                </td>
            </tr>
            
            <tr>
                <th><span class="must">*</span>政治面貌：</th>
                <td colspan="1">
                    <input type="text" class="input-txt" name="tutor.political" notNull="true" maxlength="6" placeholder="输入政治面貌" value="${(tutor.political)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>邮箱地址：</th>
                <td colspan="1">
                    <input type="text" class="input-txt" id="email" name="tutor.email" notNull="true" maxlength="50" placeholder="输入邮箱地址" value="${(tutor.email)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>联系地址：</th>
                <td colspan="1">
                    <input type="text" class="input-txt" name="tutor.address" notNull="true" maxlength="33" placeholder="输入联系地址" value="${(tutor.address)!''}"/>
                </td>
            </tr>
            <tr>
                <th>硕士毕业院校：</th>
                <td colspan="1">
                    <input type="text" class="input-txt" name="tutor.masterSchool"  maxlength="25" placeholder="输入硕士毕业院校" value="${(tutor.masterSchool)!''}"/>
                </td>
                
            </tr>
            <tr>
                <th>博士毕业院校：</th>
                <td colspan="1">
                    <input type="text" class="input-txt" name="tutor.doctorSchool"  maxlength="25" placeholder="输入博士毕业院校" value="${(tutor.doctorSchool)!''}"/>
                </td>
            </tr>
            <tr>
                <th valign="top" class="pt-10"><span class="must">*</span>主要研究及教学方向：</th>
                <td colspan="2" valign="top" class="pt-10">
                    <textarea class="text-area fn-left t-500" name="tutor.teachSubject" maxLength="150">${(tutor.teachSubject)!''}</textarea>
                </td>
            </tr>
            <tr>
                <th valign="top" class="pt-10"><span class="must">*</span>研究成果及获奖情况：</th>
                <td colspan="2" valign="top" class="pt-10">
                    <textarea class="text-area fn-left t-500" name="tutor.introduction" maxLength="150">${(tutor.introduction)!''}</textarea>
                </td>
            </tr>
            
            <tr>
                <th>审核状态：</th>
                <td><#if tutor.auditStatus == 'AUDITING'><span class="c-red">${(tutor.auditStatus.nameValue)!''}</span><#else>${(tutor.auditStatus.nameValue)!''}</#if></td>
            </tr>
            <#if tutor.auditStatus != 'AUDITING'>
            <tr>
                <th>审核日期：</th>
                <td><#if (tutor.auditDate)??>${(tutor.auditDate)?string('yyyy-MM-dd')}</#if></td>
            </tr>
            <tr>
                <th>审核人姓名：</th>
                <td>${(tutor.auditRealName)!''}</td>
            </tr>
            <#if tutor.auditStatus == 'AUDITNOPASS'>
            <tr>
                <th>审核信息：</th>
                <td>${(tutor.auditMsg)!''}</td>
            </tr>
            </#if>
            </#if>
            <tr>
                <th style="width:140px;">身份证照片：</th>
                <td colspan="6">
                    <div class="userInfo-wrap">
                        <@ImgStyle name='photoImg2' width='130' height='100'/>
                        <p class="img ml-10 mt-10" style="width:500px;height:120px;">
                            <a class="ml-20" target="_blank" href="<#if tutor.idcardFile?default('')!=''>${DOMAIN_UPLOAD_FILE}/${tutor.idcardFile!}<#else>/images/default.jpg</#if>"><img id="photoImg2" src="<#if tutor.idcardFile?default('')!=''>${DOMAIN_UPLOAD_FILE}/${tutor.idcardFile!}<#else>/images/default.jpg</#if>" title="正面照" alt="照片"></a>
                            <a class="ml-20" target="_blank" href="<#if tutor.idcardFileOther?default('')!=''>${DOMAIN_UPLOAD_FILE}/${tutor.idcardFileOther!}<#else>/images/default.jpg</#if>"><img id="photoImg2" src="<#if tutor.idcardFileOther?default('')!=''>${DOMAIN_UPLOAD_FILE}/${tutor.idcardFileOther!}<#else>/images/default.jpg</#if>" title="反面照" alt="照片"></a>
                            <a class="ml-20" target="_blank" href="<#if tutor.handIdcardFile?default('')!=''>${DOMAIN_UPLOAD_FILE}/${tutor.handIdcardFile!}<#else>/images/default.jpg</#if>"><img id="photoImg2" src="<#if tutor.handIdcardFile?default('')!=''>${DOMAIN_UPLOAD_FILE}/${tutor.handIdcardFile!}<#else>/images/default.jpg</#if>" title="手持身份证照" alt="照片"></a>
                        </p>
                    </div>
                </td>
            <tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                    <a href="${DOMAIN_CMS}/basic/musicTutorManage.htm" class="abtn abtn-green">返回</a>
                </td>
            </tr>
        </table>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/basic/musicTutor/editMusicTutorJs.ftl" />
<script>
    $(function(){
        editMusicTutor.init();
    });
</script>
</body>
</html>