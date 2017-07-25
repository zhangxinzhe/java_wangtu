<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-加入联盟</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/agencyJoinManage.htm">加入联盟</a>&nbsp;&gt;&nbsp;<span>查看</span></p>
    
        <ul class="public-tab fn-clear mt-10">
            <span class="tt">${agencyJoin.agencyName!}</span>
            <li class="current">加盟基地信息</li>
        </ul>
        
        <div class="fn-clear">
            <div class="userInfo-wrap">
                <table class="form-table validateForm mt-10">
                    <tr>
                        <th>基地名称：</th>
                        <td>${agencyJoin.agencyName!}</td>
                    </tr>
                    <tr>
                        <th>基地类型：</th>
                        <td>${agencyJoin.agencyType.nameValue}</td>
                    </tr>
                    <tr>
                        <th>申请加盟状态：</th>
                        <td>${agencyJoin.agencyStatus.nameValue}</td>
                    </tr>
                    <tr>
                        <th>联系人：</th>
                        <td>${agencyJoin.contactMan!}</td>
                    </tr>
                    <tr>
                        <th>联系电话：</th>
                        <td>${agencyJoin.contactPhone!}</td>
                    </tr>
                    <tr>
                        <th>email：</th>
                        <td>${agencyJoin.email!}</td>
                    </tr>
                    <tr>
                        <th>所在地区：</th>
                        <td>${agencyJoin.regionName!}</td>
                    </tr>
                    <tr>
                        <th>详细地址：</th>
                        <td>${agencyJoin.address!}</td>
                    </tr>
                    <tr>
                        <th>申请时间：</th>
                        <td>${agencyJoin.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                    </tr>
                    <tr>
                        <th valign="top" class="pt-10">简介：</th>
                        <td valign="top" class="pt-10">${agencyJoin.introduction!}</td>
                    </tr>
                    <tr>
                        <th valign="top" class="pt-10">备注：</th>
                        <td valign="top" class="pt-10">${agencyJoin.remark!}</td>
                    </tr>
                    <tr>
                        <th>&nbsp;</th>
                        <td>
                            <#if canEditAgencyJoin>
                            <a href="${DOMAIN_CMS}/basic/editAgencyJoin.htm?id=${agencyJoin.id!}" class="abtn abtn-blue">修改</a>
                            </#if>
                            <#if canCheckAgencyJoin && agencyJoin.agencyStatus! == agencyJoinApplying>
                            <a href="javascript:;" class="abtn abtn-blue" id="checkbtn" dataValue="${agencyJoin.id!}">同意加盟</a>
                            </#if>
                            <a href="${DOMAIN_CMS}/basic/agencyJoinManage.htm" class="abtn abtn-green ml-10">返回</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    
    </div>
</div>
<@footer />

<#--同意加盟 -->
<div class="popUp-layer" id="agenycJoinDiv" style="display:none;width:400px;"></div>
<#include "/pages/jsinclude/basic/agencyJoin/viewAgencyJoinJs.ftl" />
<script>
    $(function(){
        viewAgencyJoin.init();
    });
</script>
</body>
</html>