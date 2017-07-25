<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-HIFI会员业务管理</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <div id="content">
        <p class="crumbs">当前位置：业务管理&nbsp;&gt;&nbsp;<span>HIFI会员</span></p>
        <form id="hifiUserForm" action="" method="post">
            <table class="form-table validateForm mt-10">
                <tr>
                    <th>用户名：</th>
                    <td> 
                        <input type="text" class="input-txt" name="hifiUser.userName" placeholder="请输入用户名" value="${hifiUser.userName!}"/>
                    </td>
                </tr>
                <tr>
                    <th>姓名：</th>
                    <td> 
                        <input type="text" class="input-txt" name="hifiUser.realName" placeholder="请输入姓名" value="${hifiUser.realName!}"/>
                    </td>
                </tr>
                <tr>
                    <th>是否有交易：</th>
                    <td>
                        <select class="input-sel w-200" name="isHaveOrder">
                            <option value="true" <#if !isHaveOrder?exists || (isHaveOrder?? && isHaveOrder)>selected</#if>>是</option>
                            <option value="false" <#if isHaveOrder?? && !isHaveOrder>selected</#if>>否</option>
                        </select>
                        <span class="onTips" style="line-height:26px">是否产生过交易记录</span>
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
                    <th width="45px" class="t-center"></th>
                    <th>用户名</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th class="t-center" width="150px">是否HIFI会员</th>
                    <th class="t-center" width="150px">到期日期</th>
                    <th>操作</th>
                </tr>
                <#list list?if_exists as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td>${item.userName!}</td>
                    <td>${item.realName!}</td>
                    <td>${item.phone!}</td>
                    <td class="t-center"><#if !item.hifiMemberDate?exists>否<#else><#if item.isHifiMember.booleanValue><span class="blue">是</span><#else><span class="grey">（已过期）</span></#if></#if></td>
                    <td class="t-center"><#if item.hifiMemberDate?exists>${item.hifiMemberDate?string('yyyy-MM-dd')}</#if></td>
                    <td>
                        <a href="javascript:;" class="tradingInfo" dataValue="${item.id!}">交易记录</a>
                    </td>
                </tr>
                </#list>
            </table>
            </@doublePage2>
        </div>
    </div>
</div>
<@footer />
<#--查看弹出层-->
<div class="popUp-layer" id="tradingInfoDiv" style="display:none;"></div>

<#include "/pages/jsinclude/account/hifi/hifiMemberManageJs.ftl"/>
<script>
    $(function(){
        hifiManage.init();
    });
</script>
</body>
</html>