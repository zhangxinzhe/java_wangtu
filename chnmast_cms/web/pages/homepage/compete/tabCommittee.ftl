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
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/compete/competeManage.htm">活动专题</a>&nbsp;&gt;&nbsp;<span>组委会</span></p>
        <#include "/pages/homepage/compete/commonTab.ftl" />
        <@commonTab curLi=4 competeName='${(compete.competeName)!""}' competeId='${compete.id!}' competeType="${compete.competeType!}" />
        <form id="queryForm" action="${DOMAIN_CMS}/homepage/compete/tabCommittee.htm" method="post">
        <input type="hidden" name="id" id="competeId" value="${compete.id!}"/>
        <table class="form-table validateForm mt-10">
            <tr>
                <th>姓名：</th>
                <td> 
                    <input type="text" class="input-txt" id="committeeName" name="committeeName" placeholder="输入姓名" value="${committeeName!}"/>
                </td>
            </tr>
            <tr>
                <th>头衔：</th>
                <td>
                    <#if compete.competeType==competeTypeSZCX>
                        <#assign titleTypes = titleTypesOfszcx>
                    <#elseif compete.competeType==competeTypeHLJHC || compete.competeType==competeTypeHLJYD>
                        <#assign titleTypes = titleTypesOfhlj>
                    </#if>
                    <select class="input-sel w-200" name="titleType">
                        <option value="">全部</option>
                        <#list titleTypes?if_exists as t>
                        <option value="${t}" <#if titleType?exists && titleType==t>selected</#if>>${t.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                    <a href="${DOMAIN_CMS}/homepage/compete/addOrEditCommittee.htm?competeId=${compete.id!}" class="abtn abtn-green ml-10"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <#if committeeList?exists && committeeList.size() gt 0 >
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="45" class="t-center">序号</th>
                    <th class="t-center">姓名</th>
                    <th class="t-center">性别</th>
                    <th class="t-center">组委会头衔</th>
                    <th class="t-center">个人职务</th>
                    <th class="t-center">单位</th>
                    <th class="t-center">排序号</th>
                    <th width="160" class="t-center">操作</th>
                </tr>
                <#list committeeList as com>
                <tr>
                    <td class="t-center">${com_index+1}</td>
                    <td class="t-center" id="realName${com_index+1}">${com.realName!}</td>
                    <td class="t-center">${com.sex.nameValue!}</td>
                    <td class="t-center">${com.title.nameValue!}</td>
                    <td class="t-center">${com.jobTitle!}</td>
                    <td class="t-center">${com.unitName!}</td>
                    <td class="t-center" id="orderTd${com_index+1}">${com.orderNo!}</td>
                    <td class="t-center">
                        <a href="${DOMAIN_CMS}/homepage/compete/addOrEditCommittee.htm?competeId=${compete.id!}&committeeId=${com.id!}" title="修改">修改</a>
                        <a href="javascript:committeeManage.toDelCom(${com.id!},${com_index+1});" title="删除" id="delCom${com_index+1}">删除</a>
                        <a href="javascript:committeeManage.modifyOneItemOrder(${com_index+1});" title="修改排序号" id="modifyA${com_index+1}" dataValue="${com.id!}">修改排序号</a>
                    </td>
                </tr>
                </#list>  
            </table>
            </@doublePage2>
            <#else>
                <div class="public-nodata">暂时没有数据！</div>
            </#if>
        </div>        
        
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/committeeManageJs.ftl" />
<script>
    $(function(){
        committeeManage.init();
    });
</script>
</body>
</html>