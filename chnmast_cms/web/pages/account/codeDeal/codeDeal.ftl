<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-票务验证</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：业务管理&nbsp;>&nbsp;<span>票务验证</span></p>
        <form class="searchForm" action="${DOMAIN_CMS}/account/codeDeal.htm" method="post">
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>课程/音乐会：</th>
                <td> 
                    <input type="hidden" id="typeId" value=""/>
                    <@courseSelect couId="courseId" couTypeId="typeId" couIdName="courseId" couIdValue="${courseId!}" couNameId="courseName" couName="courseName" couNameValue="${courseName!}" fn="window.CodeDeal.courseChangeBack()"/>
                    <#--<@courseSelect_new cId="courseId" cIdName="courseId" cIdValue="${courseId!}" cnId="courseName" cnName="courseName" cnValue="${courseName!}" fn="window.CodeDeal.courseChangeBack()"/>-->
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>场次/课次：</th>
                <td> 
                    <input type="hidden" name="courseTimeId" id="courseTimeId" value="${courseTimeId!}"/>
                    <@courseTimeSelect couId="courseId" couTimeId="courseTimeId" couTimeSeq="courseSeq" couTimeNameId="courseTimeName" couTimeName="courseTimeValue" couTimeNameValue="${courseTimeValue!}" fn=""/>
                </td>
            </tr>
            <tr>
                <th>验票码：</th>
                <td><input type="text" class="input-txt" name="verifyCode" value="${verifyCode!}" id="verifyCode" /></td>
            </tr>
            <tr>
                <th>手机号：</th>
                <td><input type="text" class="input-txt" name="phone" id="phone" value="${phone!}" /></td>
            </tr>
            <tr>
                <th>状态：</th>
                <td> 
                    <select class="input-sel w-200" name="state">
                        <#list useStateList?if_exists as s>
                        <option value="${s}" <#if s==state >selected</#if> >${s.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="search_code_del">搜索</a> 
                    <a href="javascript:;" class="ml-10 abtn abtn-green" id="quick_check">快速验票</a> 
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th class="t-center" style="width:50px;">序号</th>
                    <th>名称</th>
                    <th>课次/场次</th>
                    <th>订单号</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>验票码</th>
                    <th>状态</th>
                    <th>验证时间</th>
                    <th>操作</th>
                </tr>
                <#list codeList?if_exists as info>
                <tr>
                    <td class="t-center">${info_index+1}</td>
                    <td>${info.courseName!}</td>
                    <td>${(info.startTime?string("yyyy-MM-dd HH:mm"))!}-${info.endTime?string("HH:mm")}</td>
                    <td>${info.orderNo!}</td>
                    <td>${info.realName!}</td>
                    <td>${info.phone!}</td>
                    <td>${info.verifyCode!}</td>
                    <td>
                    <#if info.useState == unUseState>
                    <span class="c-green">
                    <#else>
                    <span class="c-red">
                    </#if>
                     ${info.useState.nameValue!}</span>
                    </td>
                    <td>${(info.useDate?string("yyyy-MM-dd HH:mm"))!}</td>
                    <td>
                        <#if info.useState == unUseState >
                        <a href="javascript:;" class="code-use-btn" val="${info.id!}">使用</a>
                        <#else>
                        <a href="javascript:;" class="code-cancel-btn" val="${info.id!}">取消</a>
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
<#--查看弹出层-->
<div class="popUp-layer" id="quick_chick_layer" style="display:none;">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>快速验票</span></p>
    <div class="wrap" style="height:250px;">
        <div class="org-search">
            <div class="org-search-tt">
                <span>验票码：</span>
                <input type="text" class="input-txt" id="quick_verifycode">
                <a href="javascript:;" class="abtn abtn-blue ml-20" id="quick_check_search">查询</a>
            </div>
            <div class="org-search-inner" id="quick_check_container" style="height:200px;">
            </div>
       </div>
       <span class="c-red ml-5" id="quick_check_error"></span>
    </div>
    <p class="dd">
        <a class="abtn abtn-blue" href="javascript:;" id="quick_checke_use"><span>使用</span></a>
        <a class="abtn abtn-blue close" href="javascript:;" ><span>关闭</span></a>
    </P>
</div>
</body>
<#include "/pages/jsinclude/account/codeDeal/codeDealJs.ftl" />
<script>
    window.CodeDeal.init();
</script>
</html>