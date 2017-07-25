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
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/unionMemberManage.htm?isTeam=false">联合会会员</a>&nbsp;&gt;&nbsp;<span>个人会员信息修改</span></p>
        <ul class="public-tab fn-clear mt-10">
            <span class="tt">${unionMember.realName!}</span>
            <li class="current">个人会员信息</li>
        </ul>
        <form id="unionMemberForm" method="post" action="${DOMAIN_CMS}/basic/saveUnionMember.htm">
        <input type="hidden" id="unionMemberId" name="unionMember.id" value="${unionMember.id!}"/>
        <input type="hidden" name="unionMember.unionMemberType" value="${unionMember.unionMemberType!}"/>
        <input type="hidden" name="unionMember.photoFile" value="${unionMember.photoFile!}"/>
        <input type="hidden" name="unionMember.paperFile" value="${unionMember.paperFile!}"/>
        <table class="form-table validateForm mt-10">
            <tr>
                <th>会员类型：</th>
                <td>${(unionMember.unionMemberType.nameValue)!''}</td>
                <td rowspan="8" valign="top">
                    <div class="userImg-wrap musicImg-wrap fn-right mr-30">
                        <@ImgStyle name='photoImg' width='130' height='180'/>
                        <p class="t-center img" style="height:180px;"><img id="photoImg" src="<#if unionMember.photoFile?default('')!=''>${DOMAIN_UPLOAD_FILE}/${unionMember.photoFile!}<#else>/images/default.jpg</#if>" alt="个人照片"></p>
                        <p class="t-center pb-5"><span>支持jpg、png、bmp、jpeg、gif等图片格式<br/>建议上传2寸电子照，大小不超过2M</span></p>
                        <p class="t-center" style="position:relative;">
                            <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                            <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" imgId="photoImg" filesize='2 MB' fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm'/>
                        </p>
                    </div>
                </td>
            </tr>
            <tr>
                <th>会员编号：</th>
                <td>${(unionMember.unionCode)!''}</td>
            </tr>
            <tr>
                <th><span class="must">*</span>姓名：</th>
                <td>
                    <input type="text" class="input-txt"  name="unionMember.realName" notNull="true" maxlength="25" placeholder="输入姓名" value="${(unionMember.realName)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>性别：</th>
                <td>
                    <#list sexTypes?if_exists as type>
                        <span <#if type_index != 0>class="ml-30"</#if>><input type="radio" class="chk" name="unionMember.sex" value="${type}" <#if unionMember.sex?? && type==unionMember.sex> checked="checked"</#if> />${type.nameValue}</span>
                    </#list>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>民族：</th>
                <td>
                    <input type="text" class="input-txt" name="unionMember.nation" notNull="true" maxlength="15" placeholder="输入民族" value="${(unionMember.nation)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>出生日期：</th>
                <td>
                    <input type="text" class="input-txt fn-left" style="width:80px;" readonly onkeydown="if(window.event.keyCode == 8){return false;}" onclick="$('#btwp').click();" name="unionMember.birthday" id="birthday" <#if unionMember?? && unionMember.birthday?exists>value="${unionMember.birthday?string("yyyy-MM-dd")}"</#if>>
                    <a hidefocus="true" href="javascript:;" id="btwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'birthday',isShowClear:true,readOnly:true,isShowWeek:true})"></a>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>学历：</th>
                <td>
                    <input type="text" class="input-txt" name="unionMember.degree" notNull="true"  maxlength="15" placeholder="输入学历" value="${(unionMember.degree)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>毕业院校：</th>
                <td>
                    <input type="text" class="input-txt" name="unionMember.graduateSchool" notNull="true" maxlength="50" placeholder="输入毕业学校" value="${(unionMember.graduateSchool)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>专业：</th>
                <td colspan="2">
                    <input type="text" class="input-txt" name="unionMember.major" notNull="true" maxlength="50" placeholder="输入专业" value="${(unionMember.major)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>教学年资：</th>
                <td colspan="2">
                    <input type="text" class="input-txt t-80" name="unionMember.teacherYear" notNull="true" maxlength="5" dataType="float" decimalLength='1' placeholder="输入教学年资" value="${(unionMember.teacherYear)!''}"/>&nbsp;年
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>政治面貌：</th>
                <td colspan="2">
                    <input type="text" class="input-txt" name="unionMember.political" notNull="true" maxlength="20" placeholder="输入政治面貌" value="${(unionMember.political)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>邮箱地址：</th>
                <td colspan="2">
                    <input type="text" class="input-txt" id="email" name="unionMember.email" notNull="true" maxlength="30" placeholder="输入邮箱地址" value="${(unionMember.email)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>工作单位：</th>
                <td colspan="2">
                    <input type="text" class="input-txt" name="unionMember.workUnit" notNull="true" maxlength="50" placeholder="输入工作单位" value="${(unionMember.workUnit)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>工作部门：</th>
                <td colspan="2">
                    <input type="text" class="input-txt" name="unionMember.workDept" notNull="true" maxlength="50" placeholder="输入工作部门" value="${(unionMember.workDept)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>工作职务：</th>
                <td colspan="2">
                    <input type="text" class="input-txt" name="unionMember.workDuty" notNull="true" maxlength="50" placeholder="输入工作职务" value="${(unionMember.workDuty)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>联系电话：</th>
                <td colspan="2">
                    <input type="text" class="input-txt" id="telephone" name="unionMember.telephone" notNull="true" maxlength="15" placeholder="输入联系电话" value="${(unionMember.telephone)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>所在地：</th>
                <td colspan="2">
                    <@regionSelect id="regionCode" name="unionMember.regionCode" value="${unionMember.regionCode!}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>单位地址：</th>
                <td colspan="2">
                    <input type="text" class="input-txt" name="unionMember.address" notNull="true" maxlength="50" placeholder="输入单位地址" value="${(unionMember.address)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>推荐人/推荐单位：</th>
                <td>
                    <input type="text" class="input-txt" name="unionMember.referrer" notNull="true" maxlength="50" placeholder="输入推荐人/推荐单位" value="${(unionMember.referrer)!''}"/>
                </td>
            </tr>
            <tr>
                <th valign="top" class="pt-10">个人介绍及工作履历：</th>
                <td colspan="2" valign="top" class="pt-10">
                    <textarea class="text-area fn-left t-500" name="unionMember.intro" maxLength="1500">${(unionMember.intro)!''}</textarea>
                </td>
            </tr>
            <tr>
                <th valign="top" class="pt-10">单位推荐意见：</th>
                <td colspan="2" valign="top" class="pt-10">
                    <textarea class="text-area fn-left t-500" name="unionMember.opinion" maxLength="150">${(unionMember.opinion)!''}</textarea>
                </td>
            </tr>
            <tr>
                <th>会员推荐表：</th>
                <td colspan="2">
                    <#if unionMember.paperFile?default('')!=''>
                        <a href="/basic/downloadFile.htm?unionMemberId=${unionMember.id!}&flag=1">${unionMember.paperFileName!}</a>
                    <#else>未上传或文件丢失
                    </#if>
                </td>
            </tr>
            
            <tr>
                <th>审核状态：</th>
                <td><#if unionMember.unionAuditStatus == 'AUDITING'><span class="c-red">${(unionMember.unionAuditStatus.nameValue)!''}</span><#else>${(unionMember.unionAuditStatus.nameValue)!''}</#if></td>
            </tr>
            <#if unionMember.unionAuditStatus != 'AUDITING'>
            <tr>
                <th>审核日期：</th>
                <td><#if (unionMember.auditDate)??>${(unionMember.auditDate)?string('yyyy-MM-dd')}</#if></td>
            </tr>
            <tr>
                <th>审核人姓名：</th>
                <td>${(unionMember.auditRealName)!''}</td>
            </tr>
            <#if unionMember.unionAuditStatus == 'AUDITNOPASS'>
            <tr>
                <th>审核信息：</th>
                <td>${(unionMember.auditMsg)!''}</td>
            </tr>
            </#if>
            </#if>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                    <a href="${DOMAIN_CMS}/basic/unionMemberManage.htm?isTeam=false" class="abtn abtn-green">返回</a>
                </td>
            </tr>
        </table>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/basic/unionMember/editUnionMemberJs.ftl" />
<script>
    $(function(){
        editUnion_pers.init();
    });
</script>
</body>
</html>