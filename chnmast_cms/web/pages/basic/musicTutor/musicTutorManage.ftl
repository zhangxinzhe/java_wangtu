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
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<span>音乐导师</span></p>
        <form class="musicTutorManage" id="musicTutorForm" action="#" method="post">
        <table class="form-table validateForm mt-10">
            <tr>
                <th>审核状态：</th>
                <td>
                    <select class="input-sel w-200" name="tutor.auditStatus">
                        <option value="">全部</option>
                        <#list tutorAuditStatus?if_exists as t>
                           <option value="${t!''}" <#if (tutor.auditStatus)?? && tutor.auditStatus==t>selected</#if>>${t.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>姓名：</th>
                <td> 
                    <input type="text" class="input-txt" id="realNameInput" name="tutor.realName" placeholder="输入姓名" value="${(tutor.realName)!''}"/>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th class="t-center" width="50">序号</th>
                    <th class="t-center">姓名</th>
                    <th class="t-center">申请日期</th>
                    <th class="t-center">审核状态</th>
                    <th class="t-center">审核日期</th>
                    <th class="t-center">操作</th>
                </tr>
                <#list list?if_exists as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td class="t-center">${item.realName!}</td>
                    <td class="t-center"><#if item.applyDate??>${item.applyDate?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                    <td class="t-center">
                        <#if item.auditStatus == 'AUDITING'><span class="c-red">${(item.auditStatus.nameValue)!''}</span><#else>${(item.auditStatus.nameValue)!''}</#if>
                    </td>
                    <td class="t-center"><#if item.auditDate??>${item.auditDate?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                    <td class="t-center">
                        <#if canViewMusicTutor>
                            <a href="${DOMAIN_CMS}/basic/viewMusicTutor.htm?tutorId=${(item.id)!'0'}" title="查看">查看审核</a>
                        </#if>
                        <#if canEditMusicTutor>
                            <a href="${DOMAIN_CMS}/basic/editMusicTutor.htm?tutorId=${(item.id)!'0'}" title="修改">修改</a>
                        </#if>
                    </td>
                </tr>
                </#list>
            </table>
            </@doublePage2>
        </div>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/basic/musicTutor/musicTutorManageJs.ftl" />
<script>
    $(function(){
        musicTutorManage.init();
    });
</script>
</body>
</html>