<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<script type="text/javascript" src="${DOMAIN_CMS!}/js/component/date/WdatePicker.js?${appsetting.getVersionId()!}"></script>
<title>${SITE_CMS_SITE_NAME}-短信查询</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：统计查询&nbsp;>&nbsp;<span>短信查询</span></p>
        <form class="smsDetailForm" action="${DOMAIN_CMS}/stat/smsStat.htm" method="post">
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>时间：</th>
                <td width="330"> 
                    <input type="text" style="width:60px;" class="input-txt fn-left" notNull="true" id="sendDateStart" name="sendDateStart" value="${(sendDateStart?string('yyyy-MM-dd'))!}" onclick="$('#btwp').click();" readonly/>
                    <a hidefocus="true" href="javascript:;" id="btwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'sendDateStart',isShowClear:true,readOnly:true,isShowWeek:true,maxDate:'#F{$dp.$D(\'sendDateEnd\')}'})"></a>                            
                    <span class="fn-left mt-5 ml-10">至</span>
                    <input type="text" style="width:60px;" class="input-txt fn-left ml-10" notNull="true" id="sendDateEnd" name="sendDateEnd" value="${(sendDateEnd?string('yyyy-MM-dd'))!}" onclick="$('#etwp').click();" readonly/>
                    <a hidefocus="true" href="javascript:;" id="etwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'sendDateEnd',isShowClear:true,readOnly:true,isShowWeek:true,minDate:'#F{$dp.$D(\'sendDateStart\')}'})"></a>
                </td>
                <th>接收人：</th>
                <td> 
                    <input type="text" maxLength="30" class="input-txt" id="receiveName" name="receiveName" placeholder="输入接收人姓名" value="${receiveName!}"/>
                </td>
            </tr>
            <tr>
                <th>类型：</th>
                <td>
                    <select class="input-sel w-200" name="type">
                        <option value="">全部</option>
                        <#list smsTypes?if_exists as t>
                        <option value="${t}" <#if type?exists && type==t>selected</#if> >${t.nameValue!}</option>
                        </#list>
                    </select>
                </td>
                <th>手机号：</th>
                <td> 
                    <input type="text" maxLength="15" class="input-txt" id="receivePhone" name="receivePhone" placeholder="输入接收人手机号" value="${receivePhone!}"/>
                </td>
            </tr>
            <tr>
                <th>接收状态：</th>
                <td>
                    <select class="input-sel w-200" name="smsState">
                        <option value="">全部</option>
                        <#list sendState?if_exists as t>
                        <option value="${t}" <#if smsState?exists && smsState==t>selected</#if>>${t.nameValue!}</option>
                        </#list>
                    </select>
                </td>
                <th>内容：</th>
                <td> 
                    <input type="text" maxLength="50" class="input-txt" name="content" placeholder="输入短信内容" value="${content!}"/>
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
                    <th class="t-center" style="width:50px;">序号</th>
                    <th>发送时间</th>
                    <th>发送人</th>
                    <th style="width:35%;">内容</th>
                    <th>接收人姓名</th>
                    <th>接收人用户名</th>
                    <th>接收人电话</th>
                    <th>状态</th>
                </tr>
                <#list sendList?if_exists as info>
                <tr>
                    <td class="t-center">${info_index+1}</td>
                    <td >${info.sendDate?string('yyyy-MM-dd HH:mm:ss')}</td>
                    <td>${info.sendRealname!}</td>
                    <td title="${info.content!}"><@cutOff cutStr="${info.content!}" cutLen="60"/></td>
                    <td>${info.receiveRealname!}</td>
                    <td>${info.receiveUsername!}</td>
                    <td>${info.mobile!}</td>
                    <td>${info.receiveStatus.nameValue!}</td>
                </tr>
                </#list>  
            </table>
            </@doublePage2>
        </div>
    </div>
</div>
<@footer />
</body>
<script>
    $(function(){
        $("#searchButton").click(function(){
            if(!Verify.checkAllVerify(".smsDetailForm")){
                return;
            }
            $(".smsDetailForm").submit();   
        }); 
    });
</script>
</html>