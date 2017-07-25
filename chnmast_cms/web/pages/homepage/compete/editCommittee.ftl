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
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/compete/competeManage.htm">活动专题</a>&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/compete/tabCommittee.htm?id=${competeId}">组委会</a></p>
        <#include "/pages/homepage/compete/commonTab.ftl" />
        <@commonTab curLi=4 competeName='${(compete.competeName)!""}' competeId='${compete.id!}' competeType="${compete.competeType!}" />
        
        <form id="committeeForm" method="post" action="/homepage/compete/saveCommittee.htm">
            <input type="hidden" name="committee.id" value="${(committee.id)!}"/>
            <input type="hidden" name="committee.competeId" value="${compete.id!}" >
            <input type="hidden" name="committee.remark" value=""/> <#-- 备注字段暂不维护 -->
            <div class="userInfo-wrap">
            <table class="form-table validateForm mt-10">
                <tr>
                    <th><span class="must">*</span>姓名：</th>
                    <td>
                        <input name="committee.realName" value="${(committee.realName)!}" notNull="true" maxLength="15" type="text" class="input-txt" placeholder="请输入姓名"/>
                    </td>
                    <td rowspan="6" valign="top">
                    <#if compete.competeType==competeTypeSZCX>
                    <#else>
                        <#-- 头像上传  start-->
                        <div class="userImg-wrap fn-right">
                        <@ImgStyle name='photoImg' width='124' height='124'/>
                        <p class="img" style="width:124px;height:124px;margin:0 auto"><img id="photoImg" src="<#if committee?exists && committee.photoFile?exists>${DOMAIN_UPLOAD_FILE}/${committee.photoFile!}<#else>/images/default_compete_tee.png</#if>" alt="人员 照片"/></p>
                        <p class="t-center">
                            <span>支持jpg、png、bmp、jpeg、gif格式<br/>建议图片尺寸为124*124</span>
                        </p>
                        <p class="t-center" style="position:relative;">
                            <a href="javascript:;" class="abtn abtn-blue">选择图片</a>
                            <@uploadPic_temp_nocss position="top:0px; left:85px;" width="70" height="26" imgId="photoImg" filesize='3 MB' fileTypes="*.jpg;*.png;*.bmp;*.jpeg;*.gif" action='${DOMAIN_CMS}/common/upload/uploadPicTemp.htm' />
                        </p>
                        </div>
                        <#-- 头像上传 end -->
                    </#if>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>性别：</th>
                    <td colspan="2">
                        <input type="radio" name="committee.sex" value="MAN" <#if committeeId==0 ||(committee?exists && committee.sex=="MAN")>checked</#if>/>男
                        <input type="radio" name="committee.sex" value="WOMAN" <#if committee?exists && committee.sex=="WOMAN">checked </#if> />女
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>头衔：</th>
                    <td colspan="2">
                        <select class="input-sel w-200" name="committee.title">
                        <#if compete.competeType==competeTypeSZCX>
                            <#assign titleTypes = titleTypesOfszcx>
                        <#elseif compete.competeType==competeTypeHLJHC || compete.competeType==competeTypeHLJYD>
                            <#assign titleTypes = titleTypesOfhlj>
                        </#if>
                        <#list titleTypes?if_exists as t>
                            <option value="${t}" <#if committee?exists && committee.title==t>selected</#if>>${t.nameValue!}</option>
                        </#list>
                    </select>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>排序号：</th>
                    <td colspan="2">
                        <input name="committee.orderNo" value="${(committee.orderNo)!}" maxLength="3" dataType="integer" type="text" notNull="true" class="input-txt"  placeholder="请输入排序号"/>
                    </td>
                </tr>
                <tr>
                    <th>单位名称：</th>
                    <td colspan="2">
                        <input name="committee.unitName" value="${(committee.unitName)!}" maxLength="50" type="text" class="input-txt"  placeholder="请输入单位名称"/>
                    </td>
                </tr>
                <#if compete.competeType==competeTypeSZCX>
                <tr>
                    <th valign="top" class="pt-10">个人职务：</th>
                    <td valign="top" class="pt-10" colspan="2">
                    <#if jobTitleArray?has_content>
                        <#list jobTitleArray?if_exists as job>
                            <#if job_index==0>
                                <input name="jobTitleArray" value="${job!}" maxLength="30" type="text" class="input-txt"  placeholder="请输入个人职务"/>
                                <#if jobTitleArray?size lt 3><a href="javascript:;" id="addJobTitle">添加</a></#if>
                            <#else>
                                <br/><input name="jobTitleArray" value="${job!}" maxLength="30" type="text" class="input-txt mt-5"  placeholder="请输入个人职务"/>
                            </#if>
                        </#list>
                    <#else>
                        <input name="jobTitleArray" value="${job!}" maxLength="30" type="text" class="input-txt"  placeholder="请输入个人职务"/>
                        <a href="javascript:;" id="addJobTitle">添加</a>
                    </#if>
                    </td>
                </tr>
                <#elseif compete.competeType==competeTypeHLJHC || compete.competeType==competeTypeHLJYD>
                <tr>
                    <th>个人职务：</th>
                    <td colspan="2">
                        <input name="committee.jobTitle" value="${(committee.jobTitle)!}" maxLength="30" type="text" class="input-txt"  placeholder="请输入个人职务"/>
                    </td>
                </tr>
                </#if>
                <tr>
                    <th>个人介绍：</th>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="3">
                        <div class="ml-20">
                            <@ueditor id="editor" name="committee.introduction" content='${(committee.introduction)!}' width='1020' height='250' />
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td>
                    <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                    <a href="${DOMAIN_CMS}/homepage/compete/tabCommittee.htm?id=${competeId}" class="abtn abtn-green ml-10">返回</a>
                    </td>
                </tr>
            </table>
            </div>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/addCommitteeJs.ftl" />
<script>
    $(function(){
        var isAdd = true;
        if(${committeeId}!=0){
           isAdd = false;
        }
        addCommittee.init(isAdd,'${compete.competeType!}');
    });
</script>
</body>
</html>