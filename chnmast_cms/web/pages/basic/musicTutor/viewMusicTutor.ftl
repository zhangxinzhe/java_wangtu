<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-音乐导师</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据 &nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/musicTutorManage.htm">音乐导师</a><span>查看</span></p>
        <ul class="public-tab fn-clear mt-10">
            <span class="tt">${(tutor.realName)!''}</span>
            <li class="current">基本信息</li>
        </ul>
       
            <div class="fn-clear">
               
                    <table class="form-table validateForm mt-10">
                        <tr>
                            <th style="width:140px;">姓名：</th>
                            <td colspan="1">${(tutor.realName)!''}</td>
                            
                            <td rowspan="6">
                                <div class="userImg-wrap musicImg-wrap fn-right mr-30">
                                    <@ImgStyle name='photoImg' width='130' height='180'/>
                                    <p style="width:65px;margin:0 auto">个人照片</p>
                                    <p class="img" style="width:130px;height:180px;margin:0 auto">
                                        <img id="photoImg" src="<#if tutor.photoFile?default('')!=''>${DOMAIN_UPLOAD_FILE}/${tutor.photoFile!}<#else>/images/default.jpg</#if>" alt="照片">
                                    </p>
                                </div>
                            </td>
                            <#--
                            <td colspan="1" rowspan="6">
                                    <p style="width:65px;margin:0 auto">手持身份证照片</p>
                                    <p class="img" style="width:130px;height:180px;margin:0 auto">
                                        <img style="width:130px;height:130px;" src="<#if tutor.handIdcardFile?default('')!=''>${DOMAIN_UPLOAD_FILE}/${tutor.handIdcardFile!}<#else>/images/default.jpg</#if>" alt="照片">
                                    </p>
                            </td>
                            -->
                        </tr>
                        <tr>
                            <th style="width:140px;">性别：</th>
                            <td colspan="1">${(tutor.sex.nameValue)!''}</td>
                        </tr>
                        <tr>
                            <th style="width:140px;">民族：</th>
                            <td colspan="1">${(tutor.nation)!''}</td>
                        </tr>
                        <tr>
                             <th>出生日期：</th>
                             <td colspan="1"><#if (tutor.birthday)??>${(tutor.birthday)?string('yyyy-MM-dd')!''}</#if></td>
                        </tr>
                        <tr>
                            <th style="width:120px;">工作单位：</th>
                            <td colspan="1">${(tutor.workUnit)!''}</td>
                        </tr>
                        <tr>
                            <th style="width:120px;">职务及职称：</th>
                            <td colspan="1">${(tutor.title)!''}</td>
                        </tr>
                        <tr>
                            <th style="width:120px;">联系电话：</th>
                            <td colspan="1">${(tutor.phone)!''}</td>
                            <#--
                            <td colspan="1" rowspan="6">
                                <div class="userInfo-wrap">
                                    <div class="userImg-wrap fn-right mr-20">
                                    <@ImgStyle name='photoImg2' width='130' height='180'/>
                                    <p style="width:65px;margin:0 auto">身份证照片</p>
                                    <p class="img" style="width:130px;height:180px;margin:0 auto">
                                        <img id="photoImg2" src="<#if tutor.idcardFile?default('')!=''>${DOMAIN_UPLOAD_FILE}/${tutor.idcardFile!}<#else>/images/default.jpg</#if>" alt="照片">
                                    </p>
                                </div>
                            </td>
                            <td colspan="1" rowspan="6">
                                    <p style="width:65px;margin:0 auto">身份证照片</p>
                                   <p class="img" style="width:130px;height:180px;margin:0 auto">
                                        <img id="photoImg2" src="<#if tutor.idcardFileOther?default('')!=''>${DOMAIN_UPLOAD_FILE}/${tutor.idcardFileOther!}<#else>/images/default.jpg</#if>" alt="照片">
                                   </p>
                            </td>
                            -->
                        </tr>
                        <tr>
                            <th style="width:140px;">政治面貌：</th>
                            <td colspan="1">${(tutor.political)!''}</td>
                        </tr>
                        <tr>
                            <th style="width:140px;">邮箱地址：</th>
                            <td colspan="1">${(tutor.email)!''}</td>
                        </tr>
                        <tr>
                            <th style="width:140px;">硕士毕业院校：</th>
                            <td colspan="1">${(tutor.masterSchool)!''}</td>
                        </tr>
                        <tr>
                            <th style="width:140px;">博士毕业院校：</th>
                            <td colspan="1"> ${(tutor.doctorSchool)!''}</td>
                        </tr>
                        <tr>
                            <th style="width:140px;">主要研究及教学方向：</th>
                            <td colspan="2">${(tutor.teachSubject)!''}</td>
                        </tr>
                        <tr>
                            <th style="width:140px;">研究成果及获奖情况：</th>
                            <td colspan="2">${(tutor.introduction)!''}</td>
                        </tr>
                        <#--
                        <tr>
                            <th>身份证照片：</th>
                            <td colspan="2">
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
                        <#--
                        <tr>
                            <th>个人照片：</th>
                            <td colspan="1">
                                <#if tutor.photoFile?default('')!=''>
                                    <a href="/basic/downloadFile4MusicTutor.htm?tutorId=${tutor.id!}&flag=4">${(tutor.photoFileName)!''}</a>
                                <#else>未上传或文件丢失
                                </#if>
                            </td>
                        </tr>
                        -->
                        
                        <tr>
                            <th style="width:140px;">提交申请日期：</th>
                            <td><#if (tutor.applyDate)??>${(tutor.applyDate)?string('yyyy-MM-dd HH:mm:ss')!''}</#if></td>
                        </tr>
                        <tr>
                            <th style="width:140px;">审核状态：</th>
                            <td><#if tutor.auditStatus == 'AUDITING'><span class="c-red">${(tutor.auditStatus.nameValue)!''}</span><#else>${(tutor.auditStatus.nameValue)!''}</#if></td>
                        </tr>
                    <#if tutor.auditStatus != 'AUDITING'>
                        <tr>
                            <th style="width:140px;">审核日期：</th>
                            <td><#if (tutor.auditDate)??>${(tutor.auditDate)?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                        </tr>
                        <tr>
                            <th style="width:140px;">审核人姓名：</th>
                            <td>${(tutor.auditRealName)!''}</td>
                        </tr>
                        <#if tutor.auditStatus == 'AUDITNOPASS'>
                        <tr>
                            <th style="width:140px;">审核信息：</th>
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
                        </tr>
                        <tr>
                            <th style="width:140px;">&nbsp;</th>
                            <td>
                             <#if canAuditMusicTutor>
                                 <#if tutor.auditStatus == 'AUDITING'><#-- 审核中 -->
                                     <a href="javascript:;" id="audit1" class="abtn abtn-blue" dataValue="${tutor.id!}" flag="2">审核通过</a>   
                                     <a href="javascript:;" id="audit2" class="abtn abtn-blue" dataValue="${tutor.id!}" flag="3">审核不通过</a>
                                 <#elseif tutor.auditStatus == 'AUDITPASS' || tutor.auditStatus == 'AUDITNOPASS'>
                                     <a href="javascript:;" id="audit3" class="abtn abtn-blue" dataValue="${tutor.id!}" flag="1">取消审核</a>
                                 </#if>
                             </#if>
                                 <a href="${DOMAIN_CMS}/basic/musicTutorManage.htm" id="back" class="abtn abtn-green">返回</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
    </div>
    
</div>
<@footer />

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

<#include "/pages/jsinclude/basic/musicTutor/viewMusicTutorJs.ftl" />
<script>
    $(function(){
        viewMusicTutor.init();
    });
</script>
</body>
</html>