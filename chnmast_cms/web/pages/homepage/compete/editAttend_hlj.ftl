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
        <form id="editAttendHljForm" method="post" action="${DOMAIN_CMS}/homepage/compete/saveAttend.htm" >
            <input type="hidden" name="id" value="${id!}" />
            <input type="hidden" id="attendId" name="attend.id" value="${(attend.id)!'0'}"/>
            <input type="hidden" id="photoFile" name="attend.photoFile" value="${attend.photoFile!}"/>
            <div class="userImg-wrap fn-right mr-20">
                <p align="center"><span class="c-red">*</span>参赛照片</p>
                <@ImgStyle name='attendImg' width='160' height='200'/>
                <p class="img" style="width:160px;height:200px;margin:0 auto"><img id="attendImg" src="<#if attend.photoFile?default('') != ''>${DOMAIN_UPLOAD_FILE}/${attend.photoFile!}<#elseif uploadTempFile?default('') != ''>${DOMAIN_UPLOAD_FILE}/${uploadTempFile!}<#else>/images/default_compete_stu.jpg</#if>" alt="参赛照片"></p>
                <p class="t-center">
                    <span>支持jpg、png格式<br/>照片尺寸：3672*2755<br/>分辨率大于1000W像素</span>
                </p>
                <p class="t-center" style="position:relative;">
                    <a href="javascript:;" class="abtn abtn-blue">选择照片</a>
                    <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" imgId="attendImg" filesize='5 MB' fileTypes="*.jpg;*.png;" action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm' />
                </p>
            </div>
            <div class="userInfo-wrap">
                <table class="form-table validateForm mt-10">
                    <tr>
                        <th>
                            <#assign tempTeamName = ""/>
                            <#if compete.competeType==competeTypeHLJHC>
                                <#assign tempTeamName = "合唱团名称"/>
                            <#elseif compete.competeType==competeTypeHLJYD >
                                <#assign tempTeamName = "乐队名称"/>
                            </#if>
                            <span class="must">*</span>${tempTeamName!}：
                        </th>
                        <td>
                            <input type="text" class="input-txt t-200" notNull="true" id="teamName" name="attend.teamName"  maxlength="50" placeholder="输入${tempTeamName!}"  value="${(attend.teamName)!}" />
                        </td>
                    </tr>
                    <#if compete.competeType==competeTypeHLJHC>
                    <tr>
                        <th><span class="must">*</span>参赛组别：</th>
                        <td>
                            <select class="input-sel w-200" name="attend.teamType">
                                <option value="">--请选择--</option>
                                <#list hcTeamTypes?if_exists as type>
                                    <option value="${type}" <#if attend?? && attend.teamType?exists && attend.teamType==type>selected</#if>>${type.nameValue!}</option>
                                </#list>
                            </select>
                        </td>
                    </tr>
                    <#elseif compete.competeType==competeTypeHLJYD >
                    <tr>
                        <th><span class="must">*</span>参赛组别：</th>
                        <td>
                            <label class="gender">
                                <input type="radio" name="attend.teamType" value="${juvenileTeamType!''}" <#if attend.teamType?exists && attend.teamType==juvenileTeamType>checked</#if>>少年组
                            </label>
                            <label class="gender" id="teamType_div">
                                <input type="radio" name="attend.teamType" value="${youthTeamType!''}" <#if attend.teamType?exists && attend.teamType==youthTeamType>checked</#if>>成年组
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <th>乐队类型：</th>
                        <td>
                            <input type="text" class="input-txt t-200" placeholder="输入乐队类型" maxlength="30" id="bandType" name="attend.bandType" value="${(attend.bandType)!}" />
                        </td>
                    </tr>
                    </#if>
                    <tr>
                        <th><span class="must">*</span>组织单位：</th>
                        <td>
                            <input type="text" class="input-txt t-200" placeholder="输入组织单位" maxlength="50" notNull="true" id="unitName" name="attend.unitName" value="${(attend.unitName)!}" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <#assign tempTeamLeader = ""/>
                            <#if compete.competeType==competeTypeHLJHC>
                                <#assign tempTeamLeader = "领队姓名"/>
                            <#elseif compete.competeType==competeTypeHLJYD >
                                <#assign tempTeamLeader = "总负责人"/>
                            </#if>
                            ${tempTeamLeader!}：
                        </th>
                        <td>
                            <input type="text" class="input-txt t-200" placeholder="输入${tempTeamLeader!}" maxlength="30" id="teamLeader" name="attend.teamLeader" value="${(attend.teamLeader)!}" />
                        </td>
                    </tr>
                    <#-- 仅合唱有  -->
                    <#if compete.competeType==competeTypeHLJHC>
                    <tr>
                        <th>指挥姓名：</th>
                        <td>
                            <input type="text" class="input-txt t-200" placeholder="输入指挥姓名" maxlength="30" name="attend.majorName" value="${(attend.majorName)!}" />
                        </td>
                    </tr>
                    <tr>
                        <th>钢伴姓名：</th>
                        <td>
                            <input type="text" class="input-txt t-200" placeholder="输入钢伴姓名" maxlength="30" name="attend.drumName" value="${(attend.drumName)!}" />
                        </td>
                    </tr>
                    </#if>
                    <tr>
                        <th>
                            <#assign tempStr = ""/>
                            <#if compete.competeType==competeTypeHLJHC>
                                <#assign tempStr = "演唱人数"/>
                            <#elseif compete.competeType==competeTypeHLJYD >
                                <#assign tempStr = "成员数"/>
                            </#if>
                            <span class="must">*</span>${tempStr!}：
                        </th>
                        <td>
                            <input type="text" class="input-txt t-200" placeholder="输入${tempStr!}" notNull="true" regex="/^[0-9]{1,6}$/" regexMsg="请输入合法的数字!" name="attend.teamNum" maxlength="5" value="${(attend.teamNum)!}" />
                        </td>
                    </tr>
                    <#-- 仅合唱有  -->
                    <#if compete.competeType==competeTypeHLJHC>
                    <tr>
                        <th><span class="must">*</span>总人数：</th>
                        <td>
                            <input type="text" class="input-txt t-200" placeholder="输入总人数" notNull="true" regex="/^[0-9]{1,5}$/" regexMsg="请输入合法的数字!" name="attend.totalNum" maxlength="5" value="${(attend.totalNum)!}" />
                        </td>
                    </tr>
                    </#if>
                    <#-- 仅乐队有  -->
                    <#if compete.competeType==competeTypeHLJYD >
                    <tr>
                        <th><span class="must">*</span>行政人数：</th>
                        <td>
                            <input type="text" class="input-txt t-200" placeholder="输入行政人数" notNull="true" regex="/^[0-9]{1,5}$/" regexMsg="请输入合法的数字!" name="attend.manageNum" maxlength="5" value="${(attend.manageNum)!}" />
                        </td>
                    </tr>
                    </#if>
                    <tr>
                        <th><span class="must">*</span>联系电话：</th>
                        <td>
                            <input type="text" class="input-txt t-200" placeholder="输入联系电话" notNull="true" name="attend.phone"  maxlength="25"  value="${(attend.phone)!}" />
                        </td>
                    </tr>
                     <tr>
                        <th><span class="must">*</span>电子邮件：</th>
                        <td>
                            <input type="text" class="input-txt t-200" placeholder="输入电子邮件" notNull="true" name="attend.email"  maxlength="50"  value="${(attend.email)!}" />
                        </td>
                    </tr>
                     <tr>
                        <th><span class="must">*</span>联系地址：</th>
                        <td>
                            <input type="text" class="input-txt t-500" placeholder="输入联系地址" notNull="true" name="attend.place"  maxlength="100"  value="${(attend.place)!}" />
                        </td>
                    </tr>
                    <#-- 乐队成员  -->
                    <#if compete.competeType==competeTypeHLJYD >
                    <tr>
                        <th valign="top" class="pt-10"><span class="must">*</span>成员介绍：</th>
                        <td valign="top" class="pt-10">
                            <textarea name="attend.memberIntro" notNull="true" class="text-area fn-left t-500">${(attend.memberIntro)!''}</textarea>
                        </td>
                    </tr>
                    </#if>
                    <tr>
                        <th valign="top" class="pt-10"><span class="must">*</span><#if compete.competeType==competeTypeHLJHC>合唱团介绍：<#else>乐队介绍：</#if></th>
                        <td valign="top" class="pt-10">
                            <textarea name="attend.teamIntro" notNull="true" maxlength="500" class="text-area fn-left t-500">${(attend.teamIntro)!''}</textarea>
                            <#if compete.competeType==competeTypeHLJHC>
                                <span class="onTips fn-left mb-10">注：一篇短小的合唱团介绍（150字）</span>
                            <#else>
                                <span class="onTips fn-left mb-10">注：一篇短小的乐队介绍（150字）</span>
                            </#if>
                        </td>
                    </tr>
                    <tr>
                        <th valign="top"><span class="must">*</span><#if compete.competeType==competeTypeHLJHC>曲目一：<#else>开场曲目：</#if></th>
                        <td>
                            <input type="text" class="input-txt t-500" placeholder="" notNull="true" name="attend.firstSong"  maxlength="150"  value="${(attend.firstSong)!}" />
                            <#if compete.competeType==competeTypeHLJHC>
                                <span class="onTips mb-10">注：曲目一（请写明词、曲、编合唱作者和演唱时长）</span>
                            <#else>
                                <span class="onTips mb-10">注：每队可以准备1首开场曲（自选），时长60秒以内。乐队必须现场演奏和演唱</span>
                            </#if>
                        </td>
                    </tr>
                    <tr>
                        <th valign="top"><span class="must">*</span><#if compete.competeType==competeTypeHLJHC>曲目二：<#else>决赛曲目：</#if></th>
                        <td>
                            <input type="text" class="input-txt t-500" placeholder="" notNull="true" name="attend.secondSong"  maxlength="150"  value="${(attend.secondSong)!}" />
                            <#if compete.competeType==competeTypeHLJHC>
                                <span class="onTips mb-10">注：曲目二（请写明词、曲、编合唱作者和演唱时长）</span>
                            <#else>
                                <span class="onTips mb-10">注：乐队表演时间15分钟，自由组合曲目。<br>参赛曲目请在报名表中写明词、曲、编合唱作者和演唱时长。<br>原创歌曲或乐曲、改编歌曲或乐曲，风格不限，鼓励原创。</span>
                            </#if>
                        </td>
                    </tr>
                    <#if compete.competeType==competeTypeHLJYD>
                    <tr>
                        <th>原创曲目介绍：</th>
                        <td>
                            <input type="text" class="input-txt t-500" placeholder="" name="attend.songIntro" maxlength="500" value="${(attend.songIntro)!}" />
                        </td>
                    </tr>
                    </#if>
                    <tr>
                        <th></th>
                        <td colspan="3">
                           <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                           <a href="${DOMAIN_CMS}/homepage/compete/tabAttend.htm?id=${id}" class="abtn abtn-green ml-10">返回</a>
                        </td>
                   </tr>
                </table>
            </div>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/editAttend_hljJs.ftl" />
<script>
    $(function(){
        editAttend_hlj.init();
    });
</script>
</body>
</html>