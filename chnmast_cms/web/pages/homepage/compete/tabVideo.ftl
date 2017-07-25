<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-活动专题</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=1300000 curSubModId=1300400 />
    <div id="content">
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/compete/competeManage.htm?id=${id!'0'}">活动专题</a>&nbsp;&gt;&nbsp;<span>精彩视频</span></p>
        <#include "/pages/homepage/compete/commonTab.ftl" />
        <@commonTab curLi=7 competeName='${(compete.competeName)!""} ' competeId='${id!}' competeType="${compete.competeType!}" />
       
        <form id="videoForm" action="#" method="post">
            <input type="hidden" name="id" value="${id!}"/>
            <table class="form-table validateForm mt-10">
                <tr>
                    <th>视频名称：</th>
                    <td> 
                        <input type="text" class="input-txt" name="competeVideo.name" placeholder="输入视频名称" value="${competeVideo.name!}"/>
                    </td>
                </tr>
                <#if compete.competeType == competeTypeHLJHC || compete.competeType == competeTypeHLJYD>
                <#--黄龙奖显示-->
                <tr>
                    <th>所属类型：</th>
                    <td>
                        <select class="input-sel w-200" name="competeVideo.competeType">
                            <option value="">全部</option>
                            <#list videoUseCompeteTypes?if_exists as type>
                            <option value="${type}" <#if competeVideo?? && competeVideo.competeType?? && competeVideo.competeType==type>selected</#if>>${type.nameValue!}</option>
                            </#list>
                        </select>
                    </td>
                </tr>
                </#if>
                <tr>
                    <th>是否显示：</th>
                    <td>
                         <select class="input-sel w-200" name="competeVideo.isShow">
                            <option value="">全部</option>
                            <#list yesNoTypes?if_exists as type>
                                <option value="${type}" <#if competeVideo?? && competeVideo.isShow?? && competeVideo.isShow==type>selected</#if>>${type.nameValue!}</option>
                            </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td>
                        <a href="javascript:;" class="abtn abtn-blue" id="searchButton">搜索</a>
                        <a href="/homepage/compete/editVideo.htm?id=${id!}" class="abtn abtn-green ml-10"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                    </td>
                </tr>
            </table>
        </form>
        <div class="record-wrap mt-10">
        <#if competeVideoList?has_content>
            <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="45" class="t-center">序号</th>
                    <th>标题</th>
                    <th class="t-center">所属类型</th>
                    <th>视频名称</th>
                    <th class="t-center">视频时长 </th>
                    <th class="t-center">显示 </th>
                    <th class="t-center">排序号</th>
                    <th class="t-center">操作</th>
                </tr>
                <#list competeVideoList as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td><span title="${item.name!}"><@cutOff cutStr="${item.name!}" cutLen="30" /></span></td>
                    <td class="t-center"><span <#if item.competeType != compete.competeType> class="c-green"</#if> >${item.competeType.nameValue}</span></td>
                    <td>${item.fileName!}</td>
                    <td class="t-center">${item.videoDuration!}</td>
                    <td class="t-center">${item.isShow.nameValue!}</td>
                    <td class="t-center orderNoTd">${item.orderNo!}</td>
                    <td class="t-center optTd">
                        <#if item.isShow== 'NO'>
                        <a href="javascript:tabVideo.updateShow(true,${item.id!},'${item.name!}');">显示</a>
                        <#else>
                        <a href="javascript:tabVideo.updateShow(false,${item.id!},'${item.name!}');">不显示</a>
                        </#if>
                        <a href="javascript:;" class="updateOrderNo" dataValue="${item.id!}" dataName="${item.name!}">修改排序号</a>
                        <a href="${DOMAIN_CMS}/homepage/compete/editVideo.htm?id=${id!}&&videoId=${item.id!}">修改</a>
                        <a href="javascript:;" class="delVideo" dataValue="${item.id!}">删除</a>
                    </td>
                </tr>
                </#list>
            </table>
            </@doublePage2>
        <#else>
            <div class="public-nodata">暂时没有数据！</div>
        </#if>
        </div>        
        
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/tabVideoJs.ftl" />
<script>
    $(function(){
        tabVideo.init();
    });
</script>
</body>
</html>