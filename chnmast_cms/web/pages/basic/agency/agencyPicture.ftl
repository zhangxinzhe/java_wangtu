<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl" />
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-展示图片</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/agencyManage.htm">培训基地</a>&nbsp;&gt;&nbsp;<span>展示图片</span></p>
        <ul class="public-tab fn-clear mt-10">
            <span class="tt" title="${agency.agencyName!}"><@cutOff cutStr="${agency.agencyName!}" cutLen="20"/></span>
            <li onclick="javascript:window.location.href='${DOMAIN_CMS}/basic/editAgency.htm?agencyId=${agencyId!}'">基本信息</li>
            <li class="current" onclick="javascript:window.location.href='${DOMAIN_CMS}/basic/agencyPicture.htm?agencyId=${agencyId!}'">展示图片</li>
        </ul>
        <#if canEditAgencyPic>
        <table class="form-table validateForm mt-10">
            <tr>
                <td>
                    <a href="${DOMAIN_CMS}/basic/editAgencyPicture.htm?agencyId=${agencyId!}" class="abtn abtn-green ml-10"><img src="/images/icon/add2.png">新增</a>
                </td>
            </tr>
        </table>
        </#if>
        <#if pictureList?exists && pictureList.size() gt 0>
            <div class="record-wrap mt-10">
                <@doublePage2>
                <table class="public-table" id="listTable">
                    <tr>
                        <th width="7%" class="t-center"></th>
                        <th width="37%">名称</th>
                        <th width="10%">显示顺序</th>
                        <th width="10%">是否显示</th>
                        <th width="15%">类型</th>
                        <#if canEditAgencyPic>
                        <th width="18%">操作</th>
                        </#if>
                    </tr>
                    <#list pictureList as picture>
                        <tr>
                            <td class="t-center"><input type="checkbox" name="pictureIdsCheckbox" value="${picture.id!}"/></td>
                            <td>${picture.pictureName!}</td>
                            <td>${picture.showOrder!}</td>
                            <td><#if picture.isShow.booleanValue!>是<#else>否</#if></td>
                            <td>${picture.fileExt!}</td>
                            <#if canEditAgencyPic>
                            <td>
                                <a href="${DOMAIN_CMS}/basic/editAgencyPicture.htm?agencyId=${agencyId!}&picId=${picture.id!}" class="update">修改</a>
                            </td>
                            </#if>
                        </tr>
                    </#list>
                    <#if canEditAgencyPic>
                    <tr>
                        <td width="7%" class="t-center"><input type="checkbox" id="checkAll" title="全选"></td>
                        <td colspan="5">
                            <input type="hidden" id="agencyId" value="${agencyId!}"/>
                            <a href="javascript:;" class="abtn abtn-blue" id="removeButton">删除</a>
                        </td>
                    </tr>
                    </#if>
                </table>
                </@doublePage2>
            </div>
        <#else>
            <div class="record-wrap mt-10">
                <div class="public-nodata">暂时没有展示图片</div>
            </div>
        </#if>
    </div>
</div>

<@footer />

</body>
<#include "/pages/jsinclude/basic/agency/agencyPictureJs.ftl" />
<script>
$(function(){
    AgencyPicture.init();
});
</script>
</html>