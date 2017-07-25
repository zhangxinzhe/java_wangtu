<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-高校风采展示</title>
</head>

<body>
<input type="hidden" id="currentPage" value="${page.currentPage!}" />
<input type="hidden" id="rowNum" value="${page.rowNum!}" />
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：首页管理 &nbsp;&gt;&nbsp;<span>高校风采展示</span></p>
        <form class="studentManage" id="collegeForm" action="#" method="post" enctype="multipart/form-data">
        <table class="form-table validateForm mt-10">
            <tr>
                <th>名称：</th>
                <td> 
                    <input type="text" class="input-txt" id="collegeName" name="collegeName" placeholder="输入高校名称" value="${collegeName!}"/>
                </td>
            </tr>
            <tr>
                <th>是否推荐：</th>
                <td>
                    <select class="input-sel w-200" id="collegeRecommend" name="collegeRecommend">
                        <option value="">请选择</option>
                        <#list recommendList as a>
                        <option value="${a}" <#if isRecommend?exists && isRecommend==a >selected</#if> >${a.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                    <#if canAddCollege>
                    <a href="javascript:;" class="abtn abtn-green ml-10" id="newButton"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                    </#if>
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="50" class="t-center">序号</th>
                    <th width="250">院校名称</th>
                    <th width="250">联系电话</th>
                    <th width="150">是否推荐</th>
                    <th width="100">显示次序</th>
                    <th>操作</th>
                </tr>
                <#list collegeList?if_exists as college>
                <tr>
                    <td class="t-center">${college_index+1}</td>
                    <td id="collageName_${college.id!}" title="${college.name!}"><@cutOff cutStr="${college.name!}" cutLen="20"/></td>
                    <td>${college.phone!}</td>
                    <td>${college.isRecommend.nameValue!}</td>
                    <td id="order_${college.id!}">${college.displayNo!}</td>
                    <td>
                        <#if canEditCollege>
                        <a href="/homepage/college/toEditCollege.htm?id=${college.id!}" >修改</a>
                        </#if>
                        <#if canDelCollege>
                        <a href="javascript:;" class="delete_college" data-value="${college.id}" >删除</a>
                        </#if>
                        <a href="javascript:;" class="order_change" data-value="${college.id}" >修改排序号</a>
                        <#if college.isRecommend=='YES' >
                        <a href="javascript:;" class="RECOMMEND_NO" data-value="${college.id}" >取消推荐</a>
                        <#else>
                        <a href="javascript:;" class="RECOMMEND_YES" data-value="${college.id}" >推荐</a>
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
<#include "/pages/jsinclude/homepage/collegeManagejs.ftl" />
<script>
$(function(){
    window.CollegeManage.init();
});

</script>
</body>
</html>