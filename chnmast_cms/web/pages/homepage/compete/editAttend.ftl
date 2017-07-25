<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-报名参赛</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=1300000 curSubModId=1300400 />
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/compete/competeManage.htm">活动专题</a>&nbsp;&gt;&nbsp;<span>报名参赛</span></p>
        <#include "/pages/homepage/compete/commonTab.ftl" />
        <@commonTab curLi=5 competeName='${(compete.competeName)!""}' competeId='${id}' competeType="${compete.competeType!}" />
        <form id="editAttendForm" method="post" action="${DOMAIN_CMS}/homepage/compete/saveAttend.htm" >
            <input type="hidden" name="id" value="${id!}" />
            <input type="hidden" name="competeAttend.photoFile" value="${competeAttend.photoFile!}" >
            <div class="userImg-wrap fn-right mr-20">
                <p><span class="c-red mr-10">*</span>参赛照片</p>
                <@ImgStyle name='attendImg' width='130' height='180'/>
                <p class="img" style="width:130px;height:180px;margin:0 auto"><img id="attendImg" src="<#if competeAttend.photoFile?default('') != ''>${DOMAIN_UPLOAD_FILE}/${competeAttend.photoFile!}<#elseif uploadTempFile?default('') != ''>${DOMAIN_UPLOAD_FILE}/${uploadTempFile!}<#else>/images/default_compete_stu.jpg</#if>" alt="参赛照片"></p>
                <p class="t-center">
                    <span>支持jpg、png格式<br/>建议图片尺寸为500*700</span>
                </p>
                <p class="t-center" style="position:relative;">
                    <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                    <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" imgId="attendImg" filesize='3 MB' fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm' />
                </p>
            </div>
            <div class="userInfo-wrap">
                <table class="form-table validateForm mt-10">
                    <tr>
                        <input type="hidden" name="competeAttend.id" value="${competeAttend.id!}" />
                        <th><span class="must">*</span>姓名：</th>
                        <td>
                            <input type="text" class="input-txt" id="nameInput" name="competeAttend.realName" notNull="true" maxlength="50" placeholder="输入姓名" value="${competeAttend.realName!}"/>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span>性别：</th>
                        <td>
                            <label>
                                <#list sexTypes?if_exists as type>
                                    <span <#if type_index != 0>class="ml-30"</#if>><input type="radio" class="chk" name="competeAttend.sex" value="${type}" <#if competeAttend?? && competeAttend.sex?? && competeAttend.sex==type> checked="checked"</#if> />${type.nameValue}</span>
                                </#list>
                            </label>
                        </td>
                    </tr>
                     <tr>
                        <th><span class="must">*</span>生日：</th>
                        <td>
                            <input type="text" class="input-date t-200" notNull="true" id="birthdayInput" name="competeAttend.birthday"  maxlength="25"  value="${(competeAttend.birthday?string("yyyy-MM-dd"))!}" onclick="WdatePicker({isShowClear:false,readOnly:true,isShowWeek:true,dateFmt:'yyyy-MM-dd'})" />
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span>手机号码：</th>
                        <td>
                            <input type="text" notNull="true" class="input-txt" id="phoneInput" name="competeAttend.mobilePhone" regex="/^([0-9])\d{6,11}$/" regexMsg="请输入正确的联系电话"   value="${competeAttend.mobilePhone!}"/>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span>电子邮箱：</th>
                        <td><input type="text" notNull="true" class="input-txt" id="emailInput" name="competeAttend.email" regex="/^\w{1,}[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/" regexMsg="请输入有效的邮件地址" value="${competeAttend.email!}"></td>
                    </tr>
                   
                    <tr>
                        <th><span class="must">*</span>学校院系：</th>
                        <td><input type="text" notNull="true" class="input-txt" id="schoolNameInput" name="competeAttend.schoolName" maxlength="100" value="${competeAttend.schoolName!}"></td>
                    </tr>
                    <#-- 
                    <tr>
                        <th><span class="must">*</span>选送单位：</th>
                         <td><input type="text" notNull="true" class="input-txt" id="unitNameInput" name="competeAttend.unitName" maxlength="100" value="${competeAttend.unitName!}"></td>
                    </tr>
                    -->
                    <tr>
                        <th><span class="must">*</span>参赛组别：</th>
                        <td>
                            <select class="input-sel w-200" id="groupType" name="competeAttend.groupType">
                                <#list groupTypes?if_exists as t>
                                 <option value="${t}" <#if competeAttend.groupType?exists && competeAttend.groupType==t>selected</#if> >${t.nameValue!}</option>
                                </#list>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="must">*</span> 参赛曲目：</th>
                        <td>
                           <input type="text" class="input-txt"  name="competeAttend.firstSong" notNull="true" maxlength="150" placeholder="初赛曲目" value="${competeAttend.firstSong!}"/>
                        </td>
                    </tr>
                     <tr id="secondSong">
                        <th></th>
                        <td>
                            <input type="text" class="input-txt"  name="competeAttend.secondSong" notNull="true" maxlength="150" placeholder="复赛曲目" value="${competeAttend.secondSong!}"/>
                        </td>
                    </tr>
                     <tr>
                        <th></th>
                        <td>
                           <input type="text" class="input-txt"  name="competeAttend.thirdSong" notNull="true" maxlength="150" placeholder="半决赛曲目" value="${competeAttend.thirdSong!}"/>
                        </td>
                    </tr>
                     <tr>
                        <th></th>
                        <td>
                            <input type="text" class="input-txt"  name="competeAttend.fourthSong1" notNull="true" maxlength="150" placeholder="决赛曲目一" value="${competeAttend.fourthSong1!}"/>
                        </td>
                    </tr>
                     <tr>
                        <th></th>
                        <td>
                           <input type="text" class="input-txt"  name="competeAttend.fourthSong2" notNull="true" maxlength="150" placeholder="决赛曲目二" value="${competeAttend.fourthSong2!}"/>
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td colspan="3">
                           <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                           <a href="javascript:location.href='${DOMAIN_CMS}/homepage/compete/tabAttend.htm?id=${id}'" class="abtn abtn-green ml-10">返回</a>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/editAttendJs.ftl" />
<script>
    $(function(){
        editAttend.init();
    });
</script>
</body>
</html>