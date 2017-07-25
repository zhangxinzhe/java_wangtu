<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-选课报名</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：业务管理&nbsp;&gt;&nbsp;<span>选课报名</span></p>
        <ul class="public-tab fn-clear mt-10">
            <span class="tt">选课报名</span>
            <li id="userLi" class="current">按学员报名</li>
            <li id="courseLi">按课程报名</li>
        </ul>
        
        <form id="stuReportForm" action="#" method="post">
            <table class="form-table validateForm mt-10">
                <tr>
                    <th>用户名：</th>
                    <td> 
                        <input type="text" class="input-txt" name="stuUser.userName" value="${stuUser.userName!}" placeholder="输入用户名"/>
                    </td>
                </tr>
                <tr>
                    <th>姓名：</th>
                    <td> 
                        <input type="text" class="input-txt" name="stuUser.realName" value="${stuUser.realName!}" placeholder="输入姓名"/>
                    </td>
                </tr>
                <tr>
                    <th>手机号：</th>
                    <td> 
                        <input type="text" class="input-txt" name="stuUser.phone" value="${stuUser.phone!}" placeholder="输入手机号" maxLength="15"/>
                    </td>
                </tr>
                <tr>
                    <th>学校/单位：</th>
                    <td> 
                        <input type="text" class="input-txt" name="stuUser.schoolName" value="${stuUser.schoolName!}" placeholder="输入学校/单位"/>
                    </td>
                </tr>
                <tr>
                    <th>分组类型：</th>
                    <td>
                        <div class="select-analog fn-left">
                            <input type="hidden" id="groupTypeId" name="stuUser.groupTypeId" value="${stuUser.groupTypeId!}" />
                            <input type="text" class="input-txt" id="groupTypeTitle" name="stuUser.groupTypeTitle" value="${stuUser.groupTypeTitle!}" notNull="true" readOnly/>
                            <a href="javascript:;" class="open selectType"></a>
                        </div>
                        <span class="onTips fn-left ml-5" style="line-height:26px">选择类型</span>
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
                    <th width="60" class="t-center"><input id="allCheckbox1" type="checkbox"></th>
                    <th width="150">学员姓名</th>
                    <th width="150">用户名</th>
                    <th width="150">手机号</th>
                    <th width="250">学校/单位</th>
                    <th>分组类型</th>
                    <th>是否会员</th>
                    <th>是否音乐导师</th>
                </tr>
                <#list list?if_exists as item>
                <tr>
                    <td class="t-center"><input name="userIdsCheckbox" type="checkbox" value="${item.id!}" dataName="<#if item.realName?default('')!=''>${item.realName!}<#else>${item.userName!}</#if>" /></td>
                    <td>${item.realName!}</td>
                    <td>${item.userName!}</td>
                    <td>${item.phone!}</td>
                    <td>${item.schoolName!}</td>
                    <td>${item.groupTypeTitle!}</td>
                    <td><#if item.isMember>是<#else>否</#if></td>
                    <td><#if item.isTutor.booleanValue>是<#else>否</#if></td>
                </tr>
                </#list>
                <#if list?has_content>
                <tr>
                    <td class="t-center"><input id="allCheckbox2" type="checkbox"></td>
                    <td colspan="6">
                        <input type="text" id="chooseCourseName" class="input-txt" style="width:150px;" value="" placeholder="请选择课程" readonly disabled/>
                        <a href="javascript:;" id="chooseCourse" class="abtn abtn-blue">选择课程</a>
                    </td>
                </tr>
                </#if>
            </table>
            </@doublePage2>
        </div>
    </div>
</div>
<@footer />

<#-- 课程选择弹出层 -->
<@courseSelect_new action="${DOMAIN_CMS}/courseReportSelect.htm" isShowOpen=false fn="stuReport.submitReport()" />

<div class="popUp-layer layer-400" style="display:none;" id="reportInfoDiv">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>报名确认</span></p>
    <div class="wrap">
        <table class="form-table validateForm" id="reportInfoDiv_table">
        </table>
    </div>
    <p class="dd">
        <a class="abtn abtn-blue submit" href="javascript:;"><span>确定</span></a>
        <a class="abtn abtn-green close ml-5" href="javascript:;"><span>取消</span></a>
    </p>
</div>

<#-- 选择分类弹出层 -->
<div class="popUp-layer" id="selectTypeDiv" style="display:none;">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>选择分组类型</span></p>
    <div class="wrap" style="height:225px;">
        <div class="org-search">
            <div class="org-search-tt">
                <span>类型名称:</span>
                <input type="text" class="input-txt" name="" id="selectTypeDiv_title" maxlength="25" >
                <#-- <a href="javascript:;" class="abtn abtn-green ml-5" id="selectTypeDiv_add"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                <a href="javascript:;" class="abtn abtn-blue ml-5" id="selectTypeDiv_del">删除</a>-->
            </div>
            <div class="org-search-inner" id="selectTypeDiv_container" style="height:200px">
            </div>
        </div>
    </div>
    
    <p class="dd">
        <span id="selectTypeDiv_errorMsg" class="c-red ml-20" style="float:left"></span>
        <a class="abtn abtn-blue submit" href="javascript:;" id="selectTypeDiv_submit"><span>确定</span></a>
        <a class="abtn abtn-green ml-5 close" href="javascript:;"><span>取消</span></a>
    </p>
</div>

<#include "/pages/jsinclude/account/report/stuReportJs.ftl" />
<script>
    $(function(){
        stuReport.init();
    });
</script>
</body>
</html>