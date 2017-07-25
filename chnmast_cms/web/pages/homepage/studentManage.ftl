<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-首页管理-优秀学员</title>
</head>
<body>
<@header curAppId=appId/>
<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<span>优秀学员</span></p>
        
        <!--搜索显示内容start-->
        <form id="searchForm" action="${DOMAIN_CMS}/homepage/stu/studentManage.htm" method="post" >
        <table class="form-table validateForm mt-10">
            <tr>
                <th>单位名称：</th>
                <td>
                    <input type="text"  name="student.unitName" value="${(student.unitName)!}" placeholder="输入所属单位名称" maxlength="20" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>学员姓名：</th>
                <td>
                    <input type="text"  name="student.stuName" value="${(student.stuName)!}" placeholder="输入学员名称" maxlength="20" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>是否显示：</th>
                <td>
                    <select class="input-sel w-200" name="student.isShow">
                        <option value="">全部</option>
                        <#list YesNoTypes?if_exists as type>
                            <option value="${type}" <#if student?? && student.isShow?? && student.isShow==type>selected</#if>>${type.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td colspan="3">
                    <a href="javascript:;" id="search" class="abtn abtn-blue">搜索</a>
                    <a href="${DOMAIN_CMS}/homepage/stu/addStudent.htm" class="abtn abtn-green ml-5">
                    <img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                </td>
            </tr>
        </table>
        </form>
        <!--搜索显示内容end-->
        <!--结果显示内容start-->
        <div class="record-wrap mt-10">
         <#if stuList?? && (stuList.size()>0)>
           <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="5%" class="t-center">序号</th>
                    <th width="20%" class="t-center">姓名</th>
                    <th width="30%" class="t-center">所属单位</th>
                    <th width="10%" class="t-center">显示</th>
                    <th width="10%" class="t-center">排序号</th>
                    <th width="25%" class="t-center">操作</th>
                </tr>
                <#list stuList as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td class="t-center">${item.stuName!}</td>
                    <td class="t-center">${item.unitName!}</td>
                    <td class="t-center">${item.isShow.nameValue!}</td>
                    <td class="t-center" id="orderTd${item_index}">${item.orderNo!}</td>
                    <td class="t-center">
                         <#if item.isShow.nameValue=="否">
                          <a href="javascript:stuManage.recOneItem(true,${item.id!},'${item.stuName!}');">显示</a>&nbsp;
                         <#else>
                          <a href="javascript:stuManage.recOneItem(false,${item.id!},'${item.stuName!}');">不显示</a>&nbsp;
                         </#if>
                        <a href="javascript:stuManage.modifyOneItemOrder(${item_index});" id="modifyA${item_index}" dataValue="${item.id!}" dataName="${item.stuName!}">修改排序号</a>&nbsp;
                        <a href="${DOMAIN_CMS}/homepage/stu/updateStudent.htm?id=${item.id!}">修改</a>&nbsp;
                        <a href="javascript:stuManage.deleteOneItem(${item.id!});" >删除</a>
                    </td>
                </tr>
                </#list>
            </table>
         </@doublePage2>
         <#else>
             <div class="public-nodata">暂时没有数据！</div>
         </#if>
        </div>
        <!--结果显示内容end-->
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/stuManagejs.ftl"/>
<script>
$(function(){
   stuManage.init();
});
</script>
</body>
</html>