<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-余额管理</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：业务管理&nbsp;&gt;&nbsp;<span>余额管理</span></p>
        <form id="studentForm" action="" method="post">
            <table class="form-table validateForm mt-10">
                <tr>
                    <th>用户名：</th>
                    <td> 
                        <input type="text" class="input-txt" id="userName" name="userName" placeholder="请输入用户名" value="${userName!}"/>
                    </td>
                </tr>
                <tr>
                    <th>姓名：</th>
                    <td> 
                        <input type="text" class="input-txt" id="realName" name="realName" placeholder="请输入姓名" value="${realName!}"/>
                    </td>
                </tr>
                
                <tr>
                    <th>&nbsp;</th>
                    <td>
                        <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                        <#--<a href="javascript:;" class="abtn abtn-green ml-10" id="newButton"><img src="../images/icon/add2.png">新增</a>-->
                    </td>
                </tr>
            </table>
        </form>
        
        <div class="record-wrap mt-10">
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="30" class="t-center">序号</th>
                    <th width="100">用户名</th>
                    <th width="60">姓名</th>
                    <th width="80">电话</th>
                    <th width="100">账户余额</th>
                    <th>支付宝账号</th>
                    <th>银行姓名</th>
                    <th>银行账号</th>
                    <th width="80">操作</th>
                </tr>
                <#list accountList?if_exists as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td>${item.userName!}</td>
                    <td>${item.realName!}</td>
                    <td>${item.phone!}</td>
                    <td id="funds_${item.id}" val="${item.funds?string("0.00")}">￥${item.funds?string("0.00")}</td>
                    <td>${item.alipayAccount!}</td>
                    <td>${item.bankUserName!}</td>
                    <td>${item.bankAccount!}</td>
                    <td>
                        <a href="javascript:;" class="accountUpdate" title="修改" val="${item.id}">修改</a> 
                        <a href="javascript:;" style="display:none" class="updateCancel" title="修改" val="${item.id}">取消</a>
                    </td>
                </tr>
                </#list>
            </table>
            </@doublePage2>
        </div>
    </div>
</div>
<@footer />


<script type="text/javascript" src="${DOMAIN_CMS}/js/business/accountManage.js" ></script>
<script>
    $(function(){
        window.AccountManage.init();
    });

</script>
</body>
</html>