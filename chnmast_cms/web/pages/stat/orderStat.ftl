<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<script type="text/javascript" src="${DOMAIN_CMS!}/js/component/date/WdatePicker.js?${appsetting.getVersionId()!}"></script>
<title>${SITE_CMS_SITE_NAME}-订单查询</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：统计查询&nbsp;>&nbsp;<span>订单查询</span></p>
        <form id="searchForm" action="${DOMAIN_CMS}/stat/orderStat.htm" method="post">
        <table class="form-table validateForm mt-10">
            <tr>
                <th>交易状态：</th>
                <td width="330"> 
                    <select id="order_status" class="input-sel w-200" name="order.orderStatus">
                        <option value="">全部</option>
                        <#list orderStatus?if_exists as sType>
                        <option value="${sType}" <#if order?exists && order.orderStatus?exists && order.orderStatus==sType>selected</#if> >${sType.nameValue!}</option>
                        </#list>
                    </select>
                </td>
                <th>学员姓名：</th>
                <td> 
                    <input type="text" class="input-txt"  name="order.realname" placeholder="输入学员姓名" value="${(order.realname)!}"/>
                </td>
            </tr>
            <tr>
                <th>处理状态：</th>
                <td> 
                    <select id="status_type" class="input-sel w-200" name="order.statusType">
                        <option value="">全部</option>
                        <#--
                        <#list OrderStatusTypes?if_exists as sType>
                        <option value="${sType}" <#if order?exists && order.statusType?exists && order.statusType==sType>selected</#if> >${sType.nameValue!}</option>
                        </#list>
                        -->
                    </select>
                </td>
                <th>用户名：</th>
                <td> 
                    <input type="text" class="input-txt"  name="order.username" placeholder="输入学员用户名" value="${(order.username)!}"/>
                </td>
            </tr>
            <tr>
                <th>交易类型：</th>
                <td>
                    <select class="input-sel w-200" name="order.orderKind">
                        <option value="">全部</option>
                        <#list orderKinds?if_exists as item>
                          <option value="${item}" <#if order?exists && order.orderKind?exists && order.orderKind==item>selected</#if> >${item.nameValue!}</option>
                        </#list>
                    </select>
                </td>
                <th>处理人：</th>
                <td> 
                    <input type="text" class="input-txt"  name="order.operatorName" placeholder="输入处理人名称" value="${(order.operatorName)!}"/>
                </td>
            </tr>
            <tr>
                <th>处理时间：</th>
                <td> 
                    <input type="text" class="input-txt fn-left t-80" notNull="true" id="paymentBeginTime" name="paymentBeginTime" value="${(paymentBeginTime?string('yyyy-MM-dd'))!}" onclick="$('#btwp').click();" readonly/>
                    <a hidefocus="true" href="javascript:;" id="btwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'paymentBeginTime',isShowClear:true,readOnly:true,isShowWeek:true,maxDate:'#F{$dp.$D(\'paymentEndTime\')}'})"></a>                            
                    <span class="fn-left mt-5 ml-10">至</span>
                    <input type="text" class="input-txt fn-left ml-10 t-80" notNull="true" id="paymentEndTime" name="paymentEndTime" value="${(paymentEndTime?string('yyyy-MM-dd'))!}" onclick="$('#etwp').click();" readonly/>
                    <a hidefocus="true" href="javascript:;" id="etwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'paymentEndTime',isShowClear:true,readOnly:true,isShowWeek:true,minDate:'#F{$dp.$D(\'paymentBeginTime\')}'})"></a>
                </td>
                <th>订单号：</th>
                <td> 
                    <input type="text" class="input-txt"  name="order.tradeNo" placeholder="输入订单号" value="${(order.tradeNo)!}"/>
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
            <#if orderList?exists && orderList.size() gt 0 >
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th class="t-center" style="width:40px;">序号</th>
                    <th>姓名</th>
                    <th>用户名</th>
                    <th>订单号</th>
                    <th>应付</th>
                    <th>实付</th>
                    <th>下单时间</th>
                    <th class="t-center">付款方式</th>
                    <th class="t-center">处理状态</th>
                    <th class="t-center">处理人</th>
                    <th class="t-center">处理时间</th>
                    <th class="t-center" style="width:70px;">操作</th>
                </tr>
                <#list orderList as order>
                <tr>
                    <td class="t-center">${order_index+1}</td>
                    <td>${order.realname!}</td>
                    <td>${order.username!}</td>
                    <td>${order.tradeNo!}</td>
                    <td>${order.totalAmount?string("0.00")}</td>
                    <td>${order.payAmount?string("0.00")}</td>
                    <td>${order.creationTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                    <td class="t-center">${order.payType.nameValue!}</td>
                    <td class="t-center">${order.statusType.nameValue!}</td>
                    <td class="t-center">${order.operatorName!}</td>
                    <td class="t-center">${order.paymentTime?string("yyyy-MM-dd")}<br/>${order.paymentTime?string("HH:mm:ss")}</td>
                    <td class="t-center">
                        <#if order?exists>
                            <#if order.payAmount?? && order.payType?? && order.payAmount gt 0 && order.payType.value != 0 >
                                <a href="javascript:;" id="detail_${order_index}" data-orderId="${order.id!}" data-payType="${order.payType.value!}">支付记录</a>
                            </#if>
                            <#if order.annulAmount?? && order.annulAmount gt 0>
                                <br/><a href="javascript:;" class="viewAnnul" orderId="${order.id!}">查看优惠</a>
                            </#if>
                        </#if>
                    </td>
                </tr>
                </#list>  
            </table>
            </@doublePage2>
            <#else>
                <div class="public-nodata">没有数据哦...</div>
            </#if>
        </div>
    </div>
</div>
<@footer />
<#--查看弹出层-->
<div class="popUp-layer" id="couDetailDiv" style="display:none;"></div>
<div class="popUp-layer layer-500" style="display:none;" id="viewAnnulDiv"></div>
</body>
<script>
    $(function(){
        $("#searchButton").click(function(){
            $("#searchForm").submit();   
        }); 
        $("a[id^='detail']").click(function(){
            var _this = this;
            Sender.openDiv({
                "div" : "#couDetailDiv",
                "closeObject" : "#couDetailDiv_esc,#couDetailDiv .close",
                "url" : Domain.cms_path+"/stat/orderStatPay.htm?orderId="+$(_this).attr("data-orderId")+"&payType="+$(_this).attr("data-payType")
            });
        });
        $("a.viewAnnul").click(function(){
            $("#viewAnnulDiv").html("");
            var _this = this;
            Sender.openDiv({
                "div" : "#viewAnnulDiv",
                "closeObject" : "#viewAnnulDiv a.close",
                "url" : Domain.cms_path+"/stat/orderStatAnnul.htm?orderId="+$(_this).attr("orderId")
            });
        });
        
         function changeOrderStatus(){
            var val = $("#order_status").val();
            $("#status_type").empty();
            $("#status_type").append("<option value=''>全部</option>");
            <#-- 待付款  -->
            if(val=="UNPAY"){
                $("#status_type").append("<option value='EMPTY'>待付款</option>");
            }else if(val=="SUCCESS"){
                 $("#status_type").append("<option value='USER_SUCCESS'  >交易成功-用户完成</option>")
                 .append("<option value='CMS_SUCCESS'>交易成功-后台完成</option>")
                 .append("<option value='SYSTEM_SUCCESS'>交易成功-系统完成</option>");
            }else if(val=="FAILED"){
                $("#status_type").append("<option value='CMS_CANCEL'>交易失败-后台取消</option>")
                .append("<option value='USER_CANCEL'>交易失败-用户取消</option>")
                .append("<option value='TIMEOUT'>交易失败-超时未付款</option>");
            }else {
                $("#status_type").append("<option value='EMPTY'>待付款</option>")
                 .append("<option value='USER_SUCCESS'>交易成功-用户完成</option>")
                 .append("<option value='CMS_SUCCESS'>交易成功-后台完成</option>")
                 .append("<option value='SYSTEM_SUCCESS'>交易成功-系统完成</option>")
                 .append("<option value='CMS_CANCEL'>交易失败-后台取消</option>")
                 .append("<option value='USER_CANCEL'>交易失败-用户取消</option>")
                 .append("<option value='TIMEOUT'>交易失败-超时未付款</option>");
            }
        }
        
        $("#order_status").bind("change",changeOrderStatus);
        changeOrderStatus();
        $('#status_type').val("${(order.statusType)!''}");
    });
    
</script>
</html>