<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-首页管理-活动专题</title>
</head>
<body>
<@header curAppId=appId/>
<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<span>活动专题</span></p>
        <!--搜索显示内容start-->
        <!--搜索显示内容start-->
        <form id="coruseSearchForm" action="/homepage/compete/competeManage.htm" method="post" >
        <table class="form-table validateForm mt-10">
            <tr>
                <th>活动名称：</th>
                <td>
                    <input type="text" id="qpCompeteName" name="qpCompeteName" value="${qpCompeteName!''}" placeholder="输入活动名称" maxlength="25" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td colspan="3">
                    <a href="javascript:;" id="search" class="abtn abtn-blue">搜索</a>
                    <a href="/homepage/compete/editCompete.htm" class="abtn abtn-green ml-5"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
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
                    <th class="t-center" width="35"></th>
                    <th>活动名称（期号）</th>
                    <th>类型</th>
                    <th>报名费</th>
                    <th class="t-center">比赛时间</th>
                    <th class="t-center">报名时间</th>
                    <th class="t-center">投票时间</th>
                    <th class="t-center">进行中</th>
                    <th class="t-center">创建时间</th>
                    <th class="t-center">操作</th>
                </tr>
                <#list competeList?if_exists as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td>${(item.competeName)!''}<#if (item.competeType==competeTypeHLJHC || item.competeType==competeTypeHLJYD) && item.competeBatch?default('')!=''>（${(item.competeBatch)!''}）</#if></td>
                    <td>${(item.competeType.nameValue)!''}</td>
                    <td>${(item.competeFee)!''}</td>
                    <td class="t-center"><#if (item.beginTime)??>${(item.beginTime)?string('yyyy-MM-dd')}至</#if><#if (item.endTime)?exists>${(item.endTime)?string('yyyy-MM-dd')}</#if></td>
                    <td class="t-center"><#if (item.attendBeginTime)??>${((item.attendBeginTime)!'')?string('yyyy-MM-dd')}至</#if><#if (item.attendEndTime)?exists>${(item.attendEndTime)?string('yyyy-MM-dd')}</#if></td>
                    <td class="t-center"><#if (item.voteBeginTime)??>${((item.voteBeginTime)!'')?string('yyyy-MM-dd')}至</#if><#if (item.voteEndTime)?exists>${(item.voteEndTime)?string('yyyy-MM-dd')}</#if></td>
                    <td class="t-center"><#if (item.isDoing).getValue()==0>否<#else>是</#if></td>
                    <td class="t-center">${(item.createTime)!''}</td>
                    <td class="t-center">
                        <a href="/homepage/compete/editCompete.htm?id=${(item.id)!'0'}">修改</a>&nbsp;
                        <a class="deleteBtn" href="javascript:;" val="${(item.id)!'0'}" >删除</a>&nbsp;
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
<#include "/pages/jsinclude/homepage/competeManageJs.ftl" />
<script>
$(function(){
    competeManage.init();
});
</script>
</body>
</html>