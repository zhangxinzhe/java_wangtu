<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-统计查询-ios收支统计</title>

</head>
<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=1800000 curSubModId=1800800 />
    <div id="content">
        <p class="crumbs">当前位置：统计查询&nbsp;&gt;&nbsp;<span>ios收支统计</span></p>
        <ul class="public-tab fn-clear mt-10">
            <li flag="1">充值记录</li>
            <li flag="2">购买记录</li>
            <li flag="3" class="current">个人账户</li>
        </ul>
        <form id="iospayStatAccountForm" action="${DOMAIN_CMS}/stat/iospayStatAccount.htm" method="post">
            <table class="form-table validateForm mt-10">
                <tr>
                    <th>姓名：</th>
                    <td> 
                        <input type="text" class="input-txt"  name="realName" placeholder="输入姓名" value="${(realName)!}"/>
                    </td>
                </tr>
                <tr>
                    <th>用户名：</th>
                    <td> 
                        <input type="text" class="input-txt"  name="userName" placeholder="输入用户名" value="${(userName)!}"/>
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
                    <th width="50" class="t-center">序号</th>
                    <th>用户名</th>
                    <th>姓名</th>
                    <th>账户余额</th>
                    <th>上次交易时间</th>
                    <th></th>
                </tr>
                <#list list?if_exists as account>
                <tr>
                    <td class="t-center">${account_index+1}</td>
                    <td>${account.userName!}</td>
                    <td>${account.realName!}</td>
                    <td>${account.funds?string("0.00")}</td>
                    <td>${account.modifyTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                    <td><a href="javascript:;" class="accountInfo" value="${account.id!}">交易详情</a></td>
                </tr>
                </#list>
            </table>
            </@doublePage2>
        </div>
    </div>
</div>
<@footer />
<div class="popUp-layer" style="display:none; width:650px;" id="accountInfoDiv"></div>
<script>
    $(function(){
        $('.public-tab li').click(function(){
            var flag = $(this).attr("flag");
            var url = "";
            if(flag==1){
                url = Domain.cms_path+"/stat/iospayStat.htm";
            }else if(flag==2){
                url = Domain.cms_path+"/stat/iospayStatDetail.htm";
            }else if(flag==3){
                url = Domain.cms_path+"/stat/iospayStatAccount.htm";
            }
            window.location.href = url;
        });
        
        $('#searchButton').click(function(){
            $('#iospayStatAccountForm').submit();
        });
        
        $('.accountInfo').click(function(){
            var userId = $(this).attr("value");
            Sender.openDiv({
                "div" : "#accountInfoDiv",
                "closeObject" : "#accountInfoDiv a.close",
                "url" : Domain.cms_path + "/stat/iospayStatAccountInfo.htm?userId="+userId
            });
        });
    });
</script>
</body>
</html>