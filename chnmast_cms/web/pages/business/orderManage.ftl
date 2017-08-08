<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-提现管理</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：业务管理&nbsp;&gt;&nbsp;<span>提现管理</span></p>
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
                    <th width="80">用户名</th>
                    <th width="60">姓名</th>
                    <th width="80">账户余额</th>
                    <th width="80">提现金额</th>
                    <th>支付宝账号</th>
                    <th>银行姓名</th>
                    <th>银行账号</th>
                    <th>提现方式</th>
                    <th width="80">操作</th>
                </tr>
                <#list orderList?if_exists as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td>${item.userName!}</td>
                    <td>${item.realName!}</td>
                    <td>${item.funds?string("0.00")!}</td>
                    <td>${item.payAmount?string("0.00")!}</td>
                    <td>${item.alipayAccount!}</td>
                    <td>${item.bankUserName!}</td>
                    <td>${item.bankAccount!}</td>
                    <td>${item.remark!}</td>
                    <td>
                        <a href="javascript:;" class="finishUpdate" title="修改" val="${item.id}">完成</a> 
                        
                    </td>
                </tr>
                </#list>
            </table>
            </@doublePage2>
        </div>
    </div>
</div>
<@footer />



<script>
    $(function(){
        $(".finishUpdate").on("click",function(){
            var $this = $(this);
            var id = $this.attr("val");
            var url = "/order/finishOrder.htm?orderId="+id;
            $.post(url,null,function(result){
                if(result.msg=="success"){
                    window.location.href = "/order/orderBackManage.htm";
                    return;
                }
                alert(result.msg);
            },"json");
        });
        $("#searchButton").on("click",function(){
            window.location.href = "/order/orderBackManage.htm?userName="+$("#userName").val()+"&realName="+encodeURI($("#realName").val());
        });
    });

</script>
</body>
</html>