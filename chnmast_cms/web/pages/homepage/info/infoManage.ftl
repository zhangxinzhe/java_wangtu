<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-公告、新闻</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;>&nbsp;<span>公告、新闻</span></p>
        <form class="infoManage" action="${DOMAIN_CMS}/homepage/info/infoManage.htm" method="post">
        <table class="form-table validateForm mt-10">
            <tr>
                <th>标题：</th>
                <td> 
                    
                    <input type="text" class="input-txt" id="title" name="title" placeholder="输入查询标题" value="${title!}"/>
                </td>
            </tr>
            <tr>
                <th>消息类型：</th>
                <td>
                    <select class="input-sel w-200" name="type">
                        <option value="">全部</option>
                        <#list infoTypes?if_exists as t>
                        <option value="${t}" <#if type?exists && type==t>selected</#if> >${t.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>是否紧急：</th>
                <td>
                    <select class="input-sel w-200" name="isUrgent">
                        <option value="">全部</option>
                        <#list yesNo?if_exists as u>
                        <option value="${u}" <#if isUrgent?exists && isUrgent==u>selected</#if>>${u.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>是否显示：</th>
                <td>
                    <select class="input-sel w-200" name="isShow">
                        <option value="">全部</option>
                        <#list yesNo?if_exists as t>
                        <option value="${t}" <#if isShow?exists && isShow==t>selected</#if>>${t.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                    <#if canAddInfo>
                    <a href="javascript:;" class="abtn abtn-green ml-10" id="newButton"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                    </#if>
                </td>
            </tr>
        </table>
        </form>
        <div class="record-wrap mt-10">
            <#if infoLists?exists && infoLists.size() gt 0 >
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th style="text-align:center;">序号</th>
                    <th>标题</th>
                    <th>信息类型</th>
                    <th>创建日期</th>
                    <th>是否紧急</th>
                    <th>是否显示</th>
                    <th>排序号</th>
                    <!--<th>是否高亮</th>-->
                    <!--<th>置新天数</th>-->   
                    <th>操作</th>
                </tr>
                <#list infoLists?if_exists as info>
                <tr>
                    <td style="text-align:center;">${info_index+1}</td>
                    <td title="${info.title!}"><@cutOff cutStr="${info.title!}" cutLen="50"/></td>
                    <td>${info.infoType.nameValue!}</td>
                    <td>${info.createDate?string("yyyy-MM-dd HH:mm")}</td>
                    <td><span <#if info.isUrgent==yes>class="c-red"</#if>>${info.isUrgent.nameValue!}</span></td>
                    <td>${info.isShow.nameValue!}</td>
                    <td>${info.orderNo!}</td>
                    <!--<td>${info.isLight.nameValue!}</td>-->
                    <!--<td>${info.newDays!}</td>-->
                    <td>
                        <#if canEditInfo>
                        <a href="${DOMAIN_CMS}/homepage/info/infoEdit.htm?infoId=${info.id!}" title="修改">修改</a>
                        </#if>
                        <#if canDeleteInfo>
                        <a href="javascript:;" title="删除" class="info_delete_a" val="${info.id!}">删除</a>
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
<#include "/pages/jsinclude/homepage/infoManageJs.ftl" />
<script>
$(function(){
    window.InfoManage.init();
});

</script>
</body>
</html>