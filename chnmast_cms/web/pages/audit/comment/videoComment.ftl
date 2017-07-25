<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-评论审核</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=1250000 curSubModId=1250400 />
    <div id="content">
        <p class="crumbs">当前位置：审核管理 &nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/audit/comment/commentAuditManage.htm">评论审核</a><span>查看</span></p>
        <ul class="public-tab fn-clear mt-10">
            <span class="tt">${(comment.obsName)!''}</span>
            <li class="current">基本信息</li>
        </ul>
       
            <div class="fn-clear">
               
                    <table class="form-table validateForm mt-10">
                        <tr>
                            <th style="width:140px;">视频标题：</th>
                            <td >${(comment.courseName)!''}</td>
                        </tr>
                        <tr>
                            <th style="width:140px;">评论人：</th>
                            <td >${(comment.obsName)!''}</td>
                        </tr>
                        <tr>
                            <th style="width:140px;">评论人IP：</th>
                            <td >${(comment.ip)!''}</td>
                        </tr>
                        <tr>
                            <th valign="top" class="pt-10">评论内容：</th>
                            <td colspan="2" valign="top" class="pt-10"><div style="width:650px;">
                                <textarea class="text-area fn-left" name="MSG" readonly='true' cols=120 rows=4>${(comment.content)!''}</textarea></div>
                            </td>
                        </tr>
                        
                        <tr>
                            <th style="width:140px;">审核状态：</th>
                            <td><#if comment.auditStatus == 'AUDITING'><span class="c-red">${(comment.auditStatus.nameValue)!''}</span><#else>${(comment.auditStatus.nameValue)!''}</#if></td>
                        </tr>
                    <#if comment.auditStatus != 'AUDITING'>
                        <tr>
                            <th style="width:140px;">审核日期：</th>
                            <td><#if (comment.auditDate)??>${(comment.auditDate)?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                        </tr>
                        <tr>
                            <th style="width:140px;">审核人姓名：</th>
                            <td>${(comment.auditRealName)!''}</td>
                        </tr>
                        <#if comment.auditStatus == 'AUDITNOPASS'>
                        <tr>
                            <th style="width:140px;">审核信息：</th>
                            <td>${(comment.auditMsg)!''}</td>
                        </tr>
                        </#if>
                    </#if>
                        <tr>
                            <th style="width:140px;">&nbsp;</th>
                            <td>
                                 <#if comment.auditStatus == 'AUDITPASS'><#-- 审核中 -->
                                     <a href="javascript:;" id="audit2" class="abtn abtn-blue" dataValue="${comment.id!}" flag="2">审核不通过</a>
                                 <#elseif comment.auditStatus == 'AUDITPASS' || comment.auditStatus == 'AUDITNOPASS'>
                                     <a href="javascript:;" id="audit1" class="abtn abtn-blue" dataValue="${comment.id!}" flag="2">审核通过</a>
                                     <#--   
                                     <a href="javascript:;" id="audit3" class="abtn abtn-blue" dataValue="${comment.id!}" flag="1">取消审核</a>
                                     -->
                                 </#if>
                                 <a href="${DOMAIN_CMS}/audit/comment/videoCommentAuditManage.htm?type=2" id="back" class="abtn abtn-green">返回</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
    </div>
    
</div>
<@footer />


<#include "/pages/jsinclude/audit/comment/auditCommentJs.ftl" />
<script>
    $(function(){
        auditComment.init();
    });
</script>
</body>
</html>