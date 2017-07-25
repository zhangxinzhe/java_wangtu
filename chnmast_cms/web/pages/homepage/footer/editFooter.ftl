<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-新增页脚连接</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=1400000 curSubModId=1400950 />
    
    <div id="content">
        <p class="crumbs">当前位置：首页管理 > <a href="${DOMAIN_CMS}/homepage/index/footerManage.htm">页脚链接</a> > 
        <span><#if indexFooter?? && indexFooter.id??>修改<#else>新增</#if></span></p>
        <form id="footerForm" method="post" action="">
        <div class="userInfo-wrap">
        <#if indexFooter?? && indexFooter.id??>
            <input type="hidden" class="input-txt" name="indexFooter.id" value="${(indexFooter.id)?default(-1)}"/>
        </#if>
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>标题：</th>
                <td>
                    <input type="text" class="input-txt" id="footerName" name="indexFooter.title" notNull="true" placeholder="输入页脚标题" value="${(indexFooter.title)!}" maxlength="20"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>是否显示：</th>
                <td>
                    <#list YesNo?if_exists as type>
                       <input type="radio" name="indexFooter.isShow" id="isShow${type_index}" value="${type}" 
                       <#if indexFooter?? && indexFooter.isShow?? && type==indexFooter.isShow>
                        checked="checked"
                        <#elseif !indexFooter??&&type_index==0>
                        checked="checked"
                        </#if> />
                       <label for="isShow${type_index}">${type.nameValue}</label>
                    </#list>
                </td>
            <tr>
                <th valign="top" class="pt-10"><span class="must">*</span>显示次序：</th>
                <td>
                    <input type="text" class="input-txt" id="displayOrder" name="indexFooter.displayOrder" value="${(indexFooter.displayOrder)!}" notNull='true'  placeholder="输入显示次序" maxlength="4"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>链接类型：</th>
                <td>
                    <#list contentTypes?if_exists as t>
                    <#if t.value gt 0 &&t.value lt 3>
                    <label>
                    <input type="radio" <#if (indexFooter?? && indexFooter.contentType?exists && indexFooter.contentType==t)|| (!indexFooter?? && t=="LINK")>checked</#if> class="chk contentType" name="indexFooter.contentType" value="${t}">${t.nameValue!} 
                    </label>
                    </#if>
                    </#list>
                </td>
            </tr>
            
            <tr id="link_url_tr">
                <th><span class="must">*</span>链接地址：</th>
                <td>
                    <input type="text" class="input-txt t-500" id="linkUrl" name="indexFooter.linkUrl" maxLength="200" value="${(indexFooter.linkUrl)!}" />
                </td>
            </tr>
            <tr class="content_tr">
                <th><span class="must">*</span>信息内容：</th>
                <td></td>
            </tr>
            <tr class="content_tr">
                <td colspan=2>
                    <div class="ml-20">
                        <@ueditor id="editor" name="content" content='${content!}' width='1020' height='350' />
                    </div>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td><a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a><a href="${DOMAIN_CMS}/homepage/index/footerManage.htm" class="abtn abtn-green ml-10">返回</a></td>
            </tr>
        </table>
        </div>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/homepage/footerManageJs.ftl" />
<script>
$(function(){
    footer.init();
});
</script>
</body>
</html>