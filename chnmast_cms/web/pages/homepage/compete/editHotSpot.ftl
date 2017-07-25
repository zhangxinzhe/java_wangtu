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
        <p class="crumbs">当前位置：首页管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/homepage/compete/competeManage.htm">活动专题</a>&nbsp;&gt;&nbsp;<span>热点追踪</span></p>
        <#include "/pages/homepage/compete/commonTab.ftl" />
        <@commonTab curLi=2 competeName='${(compete.competeName)!""}' competeId='${id!"0"}' competeType="${compete.competeType!}" />
        <form id="infoForm" method="post" action="${DOMAIN_CMS}/homepage/compete/saveInfo.htm" style="height:900px">
        <input type="hidden" name="indexInfo.localFile" value="${(indexInfo.localFile)!''}"/>
        <input type="hidden" name="indexInfo.id" value="${(indexInfo.id)!'0'}"/>
        <input type="hidden" name="id" value="${id!''}"/>
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>标题：</th>
                <td>
                    <input type="text" class="input-txt" id="title" name="indexInfo.title" notNull="true" maxLength="50" value="${(indexInfo.title)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>排序号：</th>
                <td>
                <input type="text" class="input-txt" id="orderNo" dataType="integer" notNull="true"  maxLength="4" name="indexInfo.orderNo" value="${(indexInfo.orderNo)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>是否显示：</th>
                <td>
                    <#list yesNoTypes?if_exists as t>
                    <label>
                    <input type="radio" <#if (indexInfo.isShow?exists && indexInfo.isShow==t)||!indexInfo.isShow?exists && t=="NO" >checked</#if> class="chk" name="indexInfo.isShow" value="${t}">${(t.nameValue)!''} 
                    </label>
                    </#list>
                </td>
            </tr>
            <tr>
                <th>是否高亮：</th>
                <td>
                    <#list yesNoTypes?if_exists as t>
                    <label>
                    <input type="radio" id="isHignLight" <#if indexInfo.isLight?exists && indexInfo.isLight==t>checked</#if> class="chk chk2" name="indexInfo.isLight" value="${t}">${(t.nameValue)!''} 
                    </label>
                    </#list>
                </td>
            </tr>
            <#-- 高亮控制天数 -->
            <tr >
                <th>置新天数：</th>
                <td>
                <input type="text" class="input-txt" id="newDays" nonnegative="true" dataType="integer" maxLength="4" name="indexInfo.newDays" value="${(indexInfo.newDays)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>链接类型：</th>
                <td>
                    <#list contentTypes?if_exists as t>
                    <#if t.value gt 0 &&t.value lt 3>
                    <label>
                    <input type="radio" <#if (indexInfo.contentType?exists && indexInfo.contentType==t)|| (!indexInfo.contentType?exists && t=="CONTENT")>checked</#if> class="chk contentType" name="indexInfo.contentType" value="${t}">${(t.nameValue)!''} 
                    </label>
                    </#if>
                    </#list>
                </td>
            </tr>
            <tr id="link_url_tr">
                <th><span class="must">*</span>链接地址：</th>
                <td>
                    <input type="text" class="input-txt t-500" id="linkUrl" name="indexInfo.linkUrl" maxLength="100" value="${(indexInfo.linkUrl)!''}" />
                </td>
            </tr>
            <tr class="content_tr">
                <th><span class="must">*</span>信息内容：</th>
                <td></td>
            </tr>
            <tr class="content_tr">
                <td colspan=2>
                    <div class="ml-20">
                        <@ueditor id="editor" name="content" content='${content!""}' width='1020' height='350' />
                    </div>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td>
                <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                <a href="${DOMAIN_CMS}/homepage/compete/tabHotspot.htm?id=${id}" class="abtn abtn-green ml-10">返回</a></td>
            </tr>
        </table>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/hotSpotEditJs.ftl" />
<script>
    $(function(){
        hotSpotEdit.init();
    });
</script>
</body>
</html>