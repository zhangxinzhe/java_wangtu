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
        <p class="crumbs">当前位置：基础数据 &nbsp;&gt;&nbsp;<span>联合会会员</span></p>
        <ul class="public-tab fn-clear mt-10">
            <li isTeam="false" <#if !isTeam>class="current" </#if>>个人</li>
            <li  isTeam="true" <#if isTeam>class="current"</#if> >团队</li>
        </ul>
        <form class="unionMemberManage" id="unionMemberForm" action="${DOMAIN_CMS}/basic/unionMemberManage.htm?isTeam=${isTeam?string('true','false')}" method="post">
        <table class="form-table validateForm mt-10">
            <tr>
                <th>会员类型：</th>
                <td>
                    <select class="input-sel w-200" name="unionMember.unionMemberType">
                        <option value="">全部</option>
                        <#if !isTeam>
                            <#list personalTypes?if_exists as t>
                            <option value="${t!''}" <#if (unionMember.unionMemberType)?? && unionMember.unionMemberType==t>selected</#if>>${t.nameValue!}</option>
                            </#list>
                        <#else>
                            <#list teamTypes?if_exists as t>
                            <option value="${t!''}" <#if (unionMember.unionMemberType)?? && unionMember.unionMemberType==t>selected</#if>>${t.nameValue!}</option>
                            </#list>
                        </#if>
                    </select>
                </td>
            </tr>
            <tr>
                <th>审核状态：</th>
                <td>
                    <select class="input-sel w-200" name="unionMember.unionAuditStatus">
                        <option value="">全部</option>
                        <#list unionAuditStatus?if_exists as t>
                           <option value="${t!''}" <#if (unionMember.unionAuditStatus)?? && unionMember.unionAuditStatus==t>selected</#if>>${t.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <#if !isTeam>
            <tr>
                <th>姓名：</th>
                <td> 
                    <input type="text" class="input-txt" id="realName" name="unionMember.realName" placeholder="输入姓名" value="${(unionMember.realName)!''}"/>
                </td>
            </tr>
            <#else>
            <tr>
                <th>机构名称：</th>
                <td> 
                    <input type="text" class="input-txt" id="realName" name="unionMember.agencyName" placeholder="输入机构名称" value="${(unionMember.agencyName)!''}"/>
                </td>
            </tr>
            </#if>
            <tr>
                <th>会员编号：</th>
                <td> 
                    <input type="text" class="input-txt" id="unionCode" name="unionMember.unionCode" placeholder="输入会员编号" value="${(unionMember.unionCode)!''}"/>
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
                    <th class="t-center" width="50"></th>
                    <th><#if !isTeam>姓名<#else>机构名称</#if></th>
                    <th class="t-center">联合会类型</th>
                    <th class="t-center">会员编号</th>
                    <th class="t-center">联系电话</th>
                    <th class="t-center">申请日期</th>
                    <th class="t-center">审核状态</th>
                    <th class="t-center">审核日期</th>
                    <th class="t-center">操作</th>
                </tr>
                <#list list?if_exists as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td><#if !isTeam>${(item.realName)!''}<#else>${(item.agencyName)!''}</#if></td>
                    <td class="t-center">${(item.unionMemberType.nameValue)!''}</td>
                    <td class="t-center">${(item.unionCode)!''}</td>
                    <td class="t-center">${(item.telephone)!''}</td>
                    <td class="t-center"><#if item.applyDate??>${item.applyDate?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                    <td class="t-center">
                        <#if item.unionAuditStatus == 'AUDITING'><span class="c-red">${(item.unionAuditStatus.nameValue)!''}</span><#else>${(item.unionAuditStatus.nameValue)!''}</#if>
                    </td>
                    <td class="t-center"><#if item.auditDate??>${item.auditDate?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                    <td class="t-center">
                        <#if canViewUnionMember>
                        <a href="${DOMAIN_CMS}/basic/viewUnionMember.htm?isTeam=${isTeam?string('true','false')}&unionMemberId=${(item.id)!'0'}" title="查看">查看审核</a>
                        </#if>
                        <#if canEditUnionMember>
                        <a href="${DOMAIN_CMS}/basic/editUnionMember.htm?isTeam=${isTeam?string('true','false')}&unionMemberId=${(item.id)!'0'}" title="修改">修改</a>
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
<#include "/pages/jsinclude/basic/unionMember/unionMemberManageJs.ftl" />
<script>
$(function(){
    unionMemberManage.init();
});
</script>
</body>
</html>