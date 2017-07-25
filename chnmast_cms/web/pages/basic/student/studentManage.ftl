<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-个人用户</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<span>个人用户</span></p>
        <form id="studentForm" action="" method="post">
            <table class="form-table validateForm mt-10">
                <tr>
                    <th>用户名：</th>
                    <td> 
                        <input type="text" class="input-txt" id="userName" name="student.userName" placeholder="请输入用户名" value="${student.userName!}"/>
                    </td>
                </tr>
                <tr>
                    <th>姓名：</th>
                    <td> 
                        <input type="text" class="input-txt" id="realName" name="student.realName" placeholder="请输入姓名" value="${student.realName!}"/>
                    </td>
                </tr>
                <tr>
                    <th>是否会员：</th>
                    <td>
                        <select class="input-sel w-200" name="isMemberFlag">
                            <option value="0">全部</option>
                            <option value="1" <#if isMemberFlag==1>selected</#if>>是</option>
                            <option value="2" <#if isMemberFlag==2>selected</#if>>否</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>状态：</th>
                    <td>
                        <select class="input-sel w-200" name="student.isCancel">
                            <option value="">全部</option>
                            <#list statusEunms as item>
                                <option value="${item}" <#if student.isCancel?? && student.isCancel == item>selected</#if>>${item.nameValue3!}</option>
                            </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>分组类型：</th>
                    <td>
                        <div class="select-analog fn-left">
                            <input type="hidden" id="groupTypeId" name="student.groupTypeId" value="${student.groupTypeId!}" />
                            <input type="text" class="input-txt" id="groupTypeTitle" name="student.groupTypeTitle" value="${student.groupTypeTitle!}" notNull="true" readOnly/>
                            <a href="javascript:;" class="open selectType"></a>
                        </div>
                        <span class="onTips fn-left ml-5" style="line-height:26px">选择类型</span>
                    </td>
                </tr>
                <tr>
                    <th>来源类型：</th>
                    <td>
                        <select class="input-sel w-200" name="student.registerType">
                            <option value="">全部</option>
                            <#list regTypes as item>
                                <option value="${item}" <#if student.registerType?? && student.registerType == item>selected</#if>>${item.nameValue!}</option>
                            </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td>
                        <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                       <a href="javascript:;" class="abtn abtn-green ml-10" id="newButton"><img src="../images/icon/add2.png">新增</a>
                    </td>
                </tr>
            </table>
        </form>
        
        <div class="record-wrap mt-10">
            <form id="importForm" action="${DOMAIN_CMS}/basic/studentImport.htm" method="post" target="importStudentFrame" enctype="multipart/form-data">
                <div class="filter">
                    <input type="hidden" id="groupTypeIdStr" name="idStr" value=""/>
                    <input type="text" class="input-txt fn-left ml-10" style="width:150px;" id="importName">
                    <a href="javascript:;" class="abtn abtn-blue file-analog fn-btn"><span>选择文件</span><input type="file" name="_importFile" id="importFile"></a>
                    <a href="javascript:;" id="importButton" class="abtn abtn-green fn-btn"><img src="../images/icon/import.png">导入</a>
                    <a href="javascript:;" id="viewResultButton" class="abtn abtn-blue fn-btn">查看任务</a>
                    <a href="${DOMAIN_CMS}/sysfile/template/student-templete.xls" class="fn-left fn-txt ml-10">导入模版下载</a>
                </div>
            </form> 
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="50" class="t-center"><input id="allCheckbox1" type="checkbox"></th>
                    <th>用户名</th>
                    <th width="150">密码</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>是否会员</th>
                    <th>来源</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <#list list?if_exists as item>
                <tr>
                    <td class="t-center"><input name="userIdsCheckbox" type="checkbox" value="${item.id!}"/></td>
                    <td>${item.userName!}</td>
                    <td><span id="pwd_${item.id!}">******</span></td>
                    <td>${item.realName!}</td>
                    <td>${item.phone!}</td>
                    <td><#if item.isMember>是<#else>否</#if></td>
                    <td>${item.registerType.nameValue!}</td>
                    <td>${item.isCancel.nameValue!}</td>
                    <td>
                     <a href="${DOMAIN_CMS}/basic/viewStudent.htm?id=${item.id!}">查看</a>
                     <#if item.registerType=="BACK_ADD">
                         <a href="${DOMAIN_CMS}/basic/editStudent.htm?id=${item.id!}">修改</a>
                     </#if>
                     <#if item.isMember>
                         <a href="javascript:;" class="member-change" dataValue="${item.id!}" flag="0">取消会员</a>
                     <#else>
                         <a href="javascript:;" class="member-change" dataValue="${item.id!}" flag="1">升级会员</a>
                     </#if>
                    </td>
                </tr>
                </#list>
                <tr>
                    <td class="t-center"><input id="allCheckbox2" type="checkbox"></td>
                    <td colspan="7">
                        <a href="javascript:;" id="passwordBtn" dataFlag="1" class="abtn abtn-blue">显示密码</a>
                        <a href="javascript:;" id="resetPwdBtn" class="abtn abtn-blue">重置密码</a>
                    </td>
                </tr>
            </table>
            </@doublePage2>
        </div>
         <iframe id="importStudentFrame" name="importStudentFrame" style="display: none;"></iframe>
    </div>
</div>
<@footer />
<#-- 选择分类弹出层 -->
<div class="popUp-layer" id="selectTypeDiv" style="display:none;">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>选择分组类型</span></p>
    <div class="wrap" style="height:225px;">
        <div class="org-search">
            <div class="org-search-tt">
                <span>类型名称:</span>
                <input type="text" class="input-txt" name="" id="selectTypeDiv_title" maxlength="25" >
                <a href="javascript:;" class="abtn abtn-green ml-5" id="selectTypeDiv_add"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                <a href="javascript:;" class="abtn abtn-blue ml-5" id="selectTypeDiv_del">删除</a>
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

<#include "/pages/jsinclude/basic/student/studentManageJs.ftl" />
<script>
    $(function(){
        studentManage.init();
    });
    function showResult(msg, isSuccess, id){
        studentManage.showResult(msg, isSuccess, id);
    }
</script>
</body>
</html>