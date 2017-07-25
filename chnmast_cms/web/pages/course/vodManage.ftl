<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-点播视频</title>

</head>
<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：课程管理&nbsp;&gt;&nbsp;<span>点播视频</span></p>
        
        <!--搜索显示内容start-->
        <form id="vodSearchForm" action="" method="post" >
        <table class="form-table validateForm mt-10">
            <tr>
                <th>课程名称：</th>
                <td>
                    <input type="text" id="vod_courseName" name="vod.courseName" value="${vod.courseName!}" placeholder="输入课程名称" maxlength="20" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>主讲姓名：</th>
                <td>
                    <input type="text" id="tea_realname" name="vod.teaRealName" value="${vod.teaRealName!}" placeholder="输入主讲姓名" maxlength="20" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>课程状态：</th>
                <td>
                    <select class="input-sel w-200" id="vod_vodStatus" name="vod.status">
                        <option value="">全部</option>
                        <#list vodStatus?if_exists as status>
                            <option value="${status}" <#if vod?? && vod.status?? && vod.status==status>selected</#if>>${status.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <#--<tr>
                <th>审核状态：</th>
                <td>
                    <select class="input-sel w-200" name="vod.auditStatus">
                        <option value="">全部</option>
                        <#list vodAuditStatus?if_exists as t>
                           <option value="${t!''}" <#if (vod.auditStatus)?? && vod.auditStatus==t>selected</#if>>${t.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>-->
            <tr>
                <th>&nbsp;</th>
                <td colspan="3">
                    <a href="javascript:;" id="search" class="abtn abtn-blue">搜索</a>
                    <a href="${DOMAIN_CMS}/vod/addVod.htm" class="abtn abtn-green ml-5"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                </td>
            </tr>
        </table>
        </form>
        <!--搜索显示内容end-->
        <!--结果显示内容start-->
        <div class="record-wrap mt-10">
        <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="45" class="t-center">序号</th>
                    <th>课程名称</th>
                    <th>主讲</th>
                    <#--<th width="70">审核状态</th>-->
                    <th width="80">状态</th>
                    <th width="180">操作</th>
                </tr>
                <#list list?if_exists as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td><span title="${item.courseName!}"><@cutOff cutStr="${item.courseName!}" cutLen="60" /></span></td>
                    <td>
                        <span title="${item.teaRealName!}<#if item.teaName??>（${item.teaName!}）</#if>">
                        <#if item.teaName??>
                            <@cutOff cutStr="${item.teaRealName!}" cutLen="10" />
                        <#else>
                            <@cutOff cutStr="${item.teaRealName!}" cutLen="20" />
                        </#if>
                        </span>
                        <#if item.teaName??>（${item.teaName!}）</#if>
                    </td>
                    <#--<td><span <#if item.auditStatus==auditNoPass>class="c-green"<#elseif item.auditStatus==auditing>class="c-red"</#if>>${item.auditStatus.nameValue!}</span></td>-->
                    <td><span <#if item.status==vodCancel>class="c-red"<#elseif item.status==notPublish>class="c-orange"</#if>>${item.status.nameValue!}</span></td>
                    <td>
                        <a href="javascript:;" class="course-edit" dataValue="${item.id!}">修改</a>
                        <#if item.status == notPublish>
                            <a href="javascript:;" class="course-invalid" dataValue="${item.id!}" flag="0">发布</a>
                        <#elseif item.status == published>
                            <a href="javascript:;" class="course-invalid" dataValue="${item.id!}" flag="1">下架</a>
                        <#else>
                            <a href="javascript:;" class="course-invalid" dataValue="${item.id!}" flag="2">恢复</a>
                        </#if>
                        <a href="javascript:;" class="course-del" dataValue="${item.id!}">删除</a>
                    </td>
                </tr>
                </#list>
            </table>
        </@doublePage2>
        </div>
        <!--结果显示内容end-->
    </div>
</div>
<@footer />

<#include "/pages/jsinclude/course/vodManagejs.ftl"/>
<script>
    $(function(){
        vodManage.init();
    });
</script>
</body>
</html>