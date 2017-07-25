<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-加入联盟</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<span>加入联盟</span></p>
        <form id="agencyJoinForm" action="" method="post">
            <table class="form-table validateForm mt-10">
                <tr>
                    <th>基地名称：</th>
                    <td>
                        <input type="text" class="input-txt" id="agencyNameInput" name="agencyName" placeholder="输入机构名称" value="${agencyName!}"/>
                    </td>
                </tr>
                <tr>
                    <th>基地类型：</th>
                    <td>
                        <select class="input-sel w-200" name="agencyType">
                            <option value="">请选择</option>
                            <#list agencyTypes as type>
                                <option value="${type}" <#if agencyType! == type>selected</#if>>${type.nameValue!}</option>
                            </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>加盟状态：</th>
                    <td>
                        <select class="input-sel w-200" name="agencyStatus">
                            <option value="">全部</option>
                            <#list agencyJoinStatuss as item>
                                <option value="${item}" <#if agencyStatus! == item>selected</#if>>${item.nameValue!}</option>
                            </#list>
                        </select>
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
                    <th width="50" class="t-center"><input id="allCheckbox1" type="checkbox"></th>
                    <th>基地名称</th>
                    <th>类型</th>
                    <th>所在地区</th>
                    <th>联系人</th>
                    <th>联系电话</th>
                    <th>加盟状态</th>
                    <th>操作</th>
                </tr>
                <#list agencyJoinList?if_exists as agency>
                <tr>
                    <td class="t-center"><input name="agencyIdsCheckbox" type="checkbox" value="${agency.id!}"/></td>
                    <td id="agencyName_${agency.id!}" title="${agency.agencyName!}"><@cutOff cutStr="${agency.agencyName!}" cutLen="30"/></td>
                    <td>${agency.agencyType.nameValue!}</td>
                    <td>${agency.regionName!}</td>
                    <td>${agency.contactMan!}</td>
                    <td>${agency.contactPhone!}</td>
                    <td><span <#if agency.agencyStatus == agencyJoinApplying>class="c-red"</#if>>${agency.agencyStatus.nameValue!}</span></td>
                    <td>
                        <#if canViewAgencyJoin>
                            <a href="${DOMAIN_CMS}/basic/viewAgencyJoin.htm?id=${agency.id!}">查看</a>
                        </#if>
                        <#if canCheckAgencyJoin && agency.agencyStatus! == agencyJoinApplying>
                            <a href="javascript:;" class="checkAgencyJoin" dataValue="${agency.id!}">同意加盟</a>
                        </#if>
                    </td>
                </tr>
                </#list>
                <tr>
                    <td class="t-center"><input id="allCheckbox2" type="checkbox"></td>
                    <td colspan="7">
                        <#if canDelAgencyJoin>
                        <a href="javascript:;" id="removeButton" class="abtn abtn-blue">删除</a>
                        </#if>
                    </td>
                </tr>
            </table>
            </@doublePage2>
        </div>
    </div>
</div>
<@footer />

<#--同意加盟 -->
<div class="popUp-layer" id="agenycJoinDiv" style="display:none;width:400px;"></div>

<#include "/pages/jsinclude/basic/agencyJoin/agencyJoinManageJs.ftl" />
<script>
    $(function(){
        agencyJoinManage.init();
    });
</script>
</body>
</html>