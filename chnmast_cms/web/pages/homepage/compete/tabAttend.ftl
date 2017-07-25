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
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/compete/competeManage.htm">活动专题</a>&nbsp;&gt;&nbsp;<span>报名参赛</span></p>
        <#include "/pages/homepage/compete/commonTab.ftl" />
        <@commonTab curLi=5 competeName='${(compete.competeName)!""}' competeId='${id}' competeType="${compete.competeType!}" />
        <form id="attendSearchForm" action="#" method="post" >
        <input type="hidden" name="id" value="${id!}" />
        <table class="form-table validateForm mt-10">
            <tr>
                <th>参赛码：</th>
                <td>
                    <input type="text" id="attendCode" name="attendCode" value="${attendCode!''}" placeholder="输入参赛码" maxlength="25" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>姓名：</th>
                <td>
                    <input type="text" id="realName" name="realName" value="${realName!''}" placeholder="输入姓名" maxlength="25" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>联系方式：</th>
                <td>
                    <input type="text" id="mobilePhone" name="mobilePhone" value="${mobilePhone!''}" placeholder="输入手机号码"  regex="/^([0-9])\d{6,11}$/" regexMsg="请输入正确的联系电话"   class="input-txt">
                </td>
            </tr>
            <tr>
                <th>组别：</th>
                <td>
                    <select class="input-sel w-200" name="groupType">
                        <option value="">全部</option>
                        <#list groupTypes?if_exists as t>
                         <option value="${t}" <#if groupType?exists && groupType==t>selected</#if> >${t.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>报名形式：</th>
                <td>
                    <select class="input-sel w-200" name="applyType">
                        <option value="">全部</option>
                        <#list competeApplyTypes?if_exists as c>
                            <option value="${c}" <#if applyType?exists && applyType==c>selected</#if> >${c.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>交易状态：</th>
                <td>
                    <select class="input-sel w-200" name="orderStatus">
                        <option value="">全部</option>
                        <#list payStatus?if_exists as ps>
                            <option value="${ps}" <#if orderStatus?exists && orderStatus==ps>selected</#if> >${ps.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td colspan="3">
                    <a href="javascript:;" id="searchButton" class="abtn abtn-blue">搜索</a>
                    <a href="/homepage/compete/editAttend.htm?id=${id}" id="newButton"  class="abtn abtn-green ml-10"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                    <a href="javascript:;" id="exportAttend" class="abtn abtn-green ml-10"><img src="${DOMAIN_CMS}/images/icon/export.png">导出</a>
                </td>
            </tr>
        </table>
        </form>
        <!--搜索显示内容end-->
        
        <!--结果显示内容start-->
        <div class="record-wrap mt-10">
        <#if list?? && (list.size()>0)>
        <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="40" class="t-center"></th>
                    <th>姓名</th>
                    <th class="t-center">性别</th>
                    <th class="t-center">联系方式 </th>
                    <th class="t-center">组别</th>
                    <th class="t-center">报名形式</th>
                    <th class="t-center">交易状态</th>
                    <th class="t-center">参赛码</th>
                    <th class="t-center">报名时间</th>
                    <th class="t-center">总票数</th>
                    <th class="t-center">加票数</th>
                    <th width="200">视频地址</th>
                    <th class="t-center" width="125">操作</th>
                </tr>
                
                <#list list?if_exists as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td><span title="${(item.realName)!''}"><@cutOff cutStr="${(item.realName)!''}" cutLen="10"/></span></td>
                    <td class="t-center">${(item.sex.nameValue)!''}</td>
                    <td class="t-center">${(item.mobilePhone)!''}</td>
                    <td class="t-center">${(item.groupType.nameValue)!''}</td>
                    <td class="t-center">${(item.applyType.nameValue)}</td>
                    <td class="t-center">${(item.orderStatus.nameValue)!''}</td>
                    <td class="t-center">${(item.attendCode)!''}</td>
                    <td class="t-center">${(item.creationTime?string("yyyy-MM-dd"))!''}</td>
                    <td class="t-center voteNumTd">${item.voteNum!}</td>
                    <td class="t-center addVoteNumTd">${item.voteAddNum!}</td>
                    <td><span title="${item.videoUrl!}"><@cutOff cutStr="${item.videoUrl!}" cutLen="30"/></span></td>
                    <td class="t-center optTd">
                        <a href="javaScript:;" class="addVoteOpt" dataValue="${item.id}">加票</a>
                        <a href="/homepage/compete/editAttend.htm?competeAttendId=${item.id}&id=${id}">修改</a>
                        <a href="javaScript:;" dataValue="${item.id}" videoUrl="${item.videoUrl!}" name="maintainVideoUrl">视频地址</a>
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

<#-- 维护视频地址 -->
<div class="popUp-layer layer-500" id="videoUrlDiv" style="display:none;">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>视频地址维护</span></p>
    <div class="wrap" style="height:65px;">
        <div class="org-search">
            <div class="org-search-tt">
                <table>
                    <tr>
                        <td><span>视频地址：</span></td>
                        <td>
                            <input type="hidden" id="competeAttendId" />
                            <input type="text" class="input-txt" style="width:300px;" name="videoUrl" id="videoUrl">
                            <a href="javascript:;" class="abtn abtn-blue" id="videoUrl_maintain">确定</a>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>
                            <p id="videoUrlDiv_msg" class="pt-10 c-red f12"></p>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>

<@footer />
<#include "/pages/jsinclude/homepage/tabAttendJs.ftl" />
<script>
   $(function(){
        tabAttendManage.init();
    });
</script>
</body>
</html>