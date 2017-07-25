<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-悬赏管理</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<span>悬赏管理</span></p>
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
                    <th width="50" class="t-center">序号</th>
                    <th>用户名</th>
                    <th width="150">姓名</th>
                    <th>标题</th>
                    <th>分类</th>
                    <th>价格</th>
                    <th>违约金</th>
                    <th>创建时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <#list rewardList?if_exists as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td>${item.userName!}</td>
                    <td>${item.realName!}</td>
                    <td>${item.cataName!}</td>
                    <td>${item.title!}</td>
                    <td>${item.price?string("0.00")}</td>
                    <td>${item.unfinishPrice?string("0.00")!}</td>
                    <td>${item.createTime?string("yyyy-MM-dd HH:MM")}</td>
                    <td>${item.status.nameValue}</td>
                    <td>
                        <a href="javascript:;" class="catalogDelete" title="删除" val="${item.id}">删除</a> 
                    </td>
                </tr>
                </#list>
            </table>
            </@doublePage2>
        </div>
         <iframe id="importStudentFrame" name="importStudentFrame" style="display: none;"></iframe>
    </div>
</div>
<@footer />


<#include "/pages/jsinclude/basic/student/studentManageJs.ftl" />
<script>
    $(function(){
       // studentManage.init();
    });

</script>
</body>
</html>