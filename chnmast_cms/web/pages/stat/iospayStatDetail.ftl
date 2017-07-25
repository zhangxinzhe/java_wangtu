<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-统计查询-ios收支统计</title>
<script type="text/javascript" src="${DOMAIN_CMS}/js/component/date/WdatePicker.js?${appsetting.getVersionId()}"></script>

</head>
<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=1800000 curSubModId=1800800 />
    <div id="content">
        <p class="crumbs">当前位置：统计查询&nbsp;&gt;&nbsp;<span>ios收支统计</span></p>
        <ul class="public-tab fn-clear mt-10">
            <li flag="1">充值记录</li>
            <li flag="2" class="current">购买记录</li>
            <li flag="3">个人账户</li>
        </ul>
        <form id="iospayStatDetailForm" action="${DOMAIN_CMS}/stat/iospayStatDetail.htm" method="post">
            <table class="form-table validateForm mt-10">
                <tr>
                    <th><span class="must">*</span>时间段：</th>
                    <td> 
                        <input type="text" class="input-txt fn-left t-80" notNull="true" id="qpBeginTime" name="qpBeginTime" value="${(qpBeginTime?string('yyyy-MM-dd'))!}" onclick="$('#btwp').click();" readonly/>
                        <a hidefocus="true" href="javascript:;" id="btwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'qpBeginTime',isShowClear:true,readOnly:true,isShowWeek:true,maxDate:'#F{$dp.$D(\'qpEndTime\')}'})"></a>                            
                        <span class="fn-left mt-5 ml-10">至</span>
                        <input type="text" class="input-txt fn-left ml-10 t-80" notNull="true" id="qpEndTime" name="qpEndTime" value="${(qpEndTime?string('yyyy-MM-dd'))!}" onclick="$('#etwp').click();" readonly/>
                        <a hidefocus="true" href="javascript:;" id="etwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'qpEndTime',isShowClear:true,readOnly:true,isShowWeek:true,minDate:'#F{$dp.$D(\'qpBeginTime\')}'})"></a>
                    </td>
                </tr>
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
                    <th>订单号：</th>
                    <td> 
                        <input type="text" class="input-txt"  name="tradeNo" placeholder="输入订单号" value="${(tradeNo)!}"/>
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
                    <th>订单号</th>
                    <th>用户名</th>
                    <th>姓名</th>
                    <th>交易时间</th>
                    <th>交易类型</th>
                    <th>收入</th>
                    <th>支出</th>
                    <th>账户余额</th>
                    <th width="220">交易详情</th>
                </tr>
                <#list list?if_exists as record>
                <tr>
                    <td class="t-center">${record_index+1}</td>
                    <td>${record.tradeNo!}</td>
                    <td>${record.userName!}</td>
                    <td>${record.realName!}</td>
                    <td>${record.recordDate?string("yyyy-MM-dd HH:mm:ss")}</td>
                    <td>${record.recordType.nameValue}</td>
                    <td><#if record.detailType.value==0><span class="c-red">${record.changeFunds?string("0.00")}</span></#if></td>
                    <td><#if record.detailType.value==1><span class="c-green">${record.changeFunds?string("0.00")}</span></#if></td>
                    <td>${record.remainFunds?string("0.00")}</td>
                    <td><span title="${record.remark!}"><@cutOff cutStr="${record.remark!}" cutLen="60" /></span></td>
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
            var beginTime = $("#qpBeginTime").val();
            var endTime = $("#qpEndTime").val();
            if(!Validator.isNotBlank(beginTime) || !Validator.isNotBlank(endTime)){
                Tips.showAlertWin({
                    info : "时间段开始和结束都不能为空！"
                });
                return;
            }
            $('#iospayStatDetailForm').submit();
        });
    });
</script>
</body>
</html>