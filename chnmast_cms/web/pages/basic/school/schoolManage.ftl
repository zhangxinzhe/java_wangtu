<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-学校维护</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<span>学校维护</span></p>
        <form class="studentManage" id="schoolForm" action="#" method="post" enctype="multipart/form-data">
        <table class="form-table validateForm mt-10">
            <tr>
                <th>学校名称：</th>
                <td> 
                    <input type="text" class="input-txt" id="schoolNameInput" name="schoolName" placeholder="输入学校名称或简拼" value="${schoolName!}"/>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                    <#if canAddSchool>
                    <a href="javascript:;" class="abtn abtn-green ml-10" id="newButton"><img src="../images/icon/add2.png">新增</a>
                    </#if>
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <form id="importForm" action="${DOMAIN_CMS}/basic/schoolImport.htm" method="post" target="importSchoolFrame" enctype="multipart/form-data">
                <div class="filter">
                    <input type="text" class="input-txt fn-left ml-10" style="width:150px;" id="importName">
                    <a href="javascript:;" class="abtn abtn-blue file-analog fn-btn"><span>选择文件</span><input type="file" name="_importFile" id="importFile"></a>
                    <a href="javascript:;" id="importButton" class="abtn abtn-green fn-btn"><img src="../images/icon/import.png">导入</a>
                    <a href="javascript:;" id="viewResultButton" class="abtn abtn-blue fn-btn">查看任务</a>
                    <a href="${DOMAIN_CMS}/sysfile/template/school-templete.xls" class="fn-left fn-txt ml-10">导入模版下载</a>
                </div>
            </form>
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="50" class="t-center"><input id="allCheckbox1" type="checkbox"></th>
                    <th>学校名称</th>
                    <th>所在地区</th>
                    <th>联系人</th>
                    <th>联系电话</th>
                    <th>操作</th>
                </tr>
                <#list schoolList?if_exists as school>
                <tr>
                    <td class="t-center"><input name="schoolIdsCheckbox" type="checkbox" value="${school.id!}"/></td>
                    <td id="schoolName_${school.id!}" title="${school.schoolName!}"><@cutOff cutStr="${school.schoolName!}" cutLen="30"/></td>
                    <td>${school.regionName!}</td>
                    <td>${school.contactMan!}</td>
                    <td>${school.contactPhone!}</td>
                    <td>
                        <#if canEditSchool>
                        <a href="${DOMAIN_CMS}/basic/schoolEdit.htm?schoolId=${school.id}" title="修改">修改</a>
                        </#if>
                    </td>
                </tr>
                </#list>
                <tr>
                    <td class="t-center"><input id="allCheckbox2" type="checkbox"></td>
                    <td colspan="8">
                        <#if canDelSchool>
                        <a href="javascript:;" id="removeButton" class="abtn abtn-blue">删除</a>
                        </#if>
                    </td>
                </tr>
            </table>
            </@doublePage2>
        </div>
        <iframe id="importSchoolFrame" name="importSchoolFrame" style="display: none;"></iframe>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/basic/school/schoolManageJs.ftl" />
<script>
    $(function(){
        SchoolManage.init();
    });
    function showResult(msg, isSuccess, id){
            SchoolManage.showResult(msg, isSuccess, id);
    }
</script>
</body>
</html>