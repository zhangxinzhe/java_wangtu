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
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/compete/competeManage.htm">活动专题</a>&nbsp;&gt;&nbsp;<span>报名参赛</span></p>
        <#include "/pages/homepage/compete/commonTab.ftl" />
        <@commonTab curLi=5 competeName='${(compete.competeName)!""}' competeId='${id}' competeType="${compete.competeType!}" />
        
        <#assign tempTeamName = ""/>
        <#assign tempLeader = ""/>
        <#if compete.competeType==competeTypeHLJHC>
            <#assign tempTeamName = "合唱团名称"/>
            <#assign tempLeader = "领队姓名" />
        <#elseif compete.competeType==competeTypeHLJYD >
            <#assign tempTeamName = "乐队名称"/>
            <#assign tempLeader = "总负责人" />
        </#if>
        <form id="attendHljSearchForm" action="#" method="post" >
            <input type="hidden" name="id" value="${id!}" />
            <table class="form-table validateForm mt-20">
                <tr>
                    <th>${tempTeamName!}：</th>
                    <td>
                        <input type="text" id="teamName" name="attendHlj.teamName" value="${(attendHlj.teamName)!''}" placeholder="${tempTeamName!}" maxlength="25" class="input-txt">
                    </td>
                </tr>
                <tr>
                    <th>组织单位：</th>
                    <td>
                        <input type="text" id="unitName" name="attendHlj.unitName" value="${(attendHlj.unitName)!''}" placeholder="组织单位" maxlength="25" class="input-txt">
                    </td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td colspan="3">
                        <a href="javascript:;" id="searchButton" class="abtn abtn-blue">搜索</a>
                        <a href="${DOMAIN_CMS}/homepage/compete/editAttendHlj.htm?id=${id}" id="newButton"  class="abtn abtn-green ml-10"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                        <#if compete.competeType=='HLJ_YD'>
                        </#if>
                        <a href="javascript:;" id="exportAttendHlj" class="abtn abtn-green ml-10"><img src="${DOMAIN_CMS}/images/icon/export.png">导出</a>
                    </td>
                </tr>
            </table>
        </form>
        <!--搜索显示内容end-->
        
        <!--结果显示内容start-->
        <div class="record-wrap mt-10">
        <#if list?? && (list.size()>0)>
        <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="45" class="t-center"></th>
                    <th>${tempTeamName!}</th>
                    <th class="t-center" width="40">参赛码</th>
                    <th>组织单位</th>
                    <th class="t-center">${tempLeader!}</th>
                    <th class="t-center">联系电话</th>
                    <th width="52" class="t-center">成员数</th>
                    <#if compete.competeType==competeTypeHLJHC>
                    <th width="52" class="t-center">总人数</th>
                    </#if>
                    <#if compete.competeType==competeTypeHLJYD>
                    <th width="52" class="t-center">行政人数</th>
                    </#if>
                    <th width="52" class="t-center">总票数</th>
                    <th width="52" class="t-center">加票数</th>
                    <th class="t-center">报名形式</th>
                    <th class="t-center">创建时间</th>
                    <th class="t-center" width="100">操作</th>
                </tr>
                
                <#list list?if_exists as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td><span title="${(item.teamName)!''}"><@cutOff cutStr="${(item.teamName)!''}" cutLen="40"/></span></td>
                    <td class="t-center">${(item.attendCode)!''}</td>
                    <td><span title="${(item.unitName)!''}"><@cutOff cutStr="${(item.unitName)!''}" cutLen="25"/></span></td>
                    <td class="t-center">${(item.teamLeader)!''}</td>
                    <td class="t-center">${(item.phone)!''}</td>
                    <td class="t-center">${(item.teamNum)!''}</td>
                    <#if compete.competeType==competeTypeHLJHC>
                    <td class="t-center">${(item.totalNum)!''}</td>
                    </#if>
                    <#if compete.competeType==competeTypeHLJYD>
                    <td class="t-center">${(item.manageNum)!''}</td>
                    </#if>
                    <td class="t-center voteNumTd">${item.voteNum!}</td>
                    <td class="t-center addVoteNumTd">${item.voteAddNum!}</td>
                    <td class="t-center">${(item.applyType.nameValue)}</td>
                    <td class="t-center">${(item.creationTime)?string("yyyy-MM-dd")!''}<br/>${(item.creationTime)?string("HH:mm:ss")!''}</td>
                    <td class="t-center optTd">
                        <a href="javaScript:;" class="addVoteOpt" dataValue="${item.id}">加票</a>
                        <a href="/homepage/compete/editAttendHlj.htm?attendId=${item.id}&id=${id}">修改</a>
                        <a href="javascript:;" class="delAttend" dataValue="${(item.id)!'0'}">删除</a>
                    </td>
                </tr>
                </#list>
                
            </table>
        </@doublePage2>
         <#else>
             <div class="public-nodata">暂时没有数据！</div>
         </#if>
        </div>
        <!--结果显示内容end-->
    </div>
</div>

<@footer />
<#include "/pages/jsinclude/homepage/tabAttend_hljJs.ftl" />
<script>
   $(function(){
        tabAttend_hlj.init();
    });
</script>
</body>
</html>