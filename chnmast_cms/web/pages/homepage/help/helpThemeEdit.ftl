<#include "/pages/common/commonmacro.ftl" />
<input type="hidden" id="catalogId" name="catalogId" value="${catalogId!}"/>
<#if theme?exists>
<form id="themeForm" method="post">
<input type="hidden" id="themeId" name="theme.id" value="${theme.id!}"/>
<input type="hidden" name="theme.cataLogId" value="${catalogId!}"/>
<input type="hidden" id="contentFile" name="theme.contentFile" value="${theme.contentFile!}"/>
<table class="form-table validateForm mt-10" >
    <tr>
        <th><span class="must">*</span>标题：</th>
        <td>
            <input type="text" class="input-txt" notNull="true" name="theme.themeName" value="${theme.themeName!}" maxLength="50" placeholder="输入标题">
        </td>
    </tr>
    <tr>
        <th><span class="must">*</span>显示顺序：</th>
        <td>
            <input type="text" class="input-txt" dataType="integer" notNull="true" name="theme.displayOrder" value="${theme.displayOrder!}" maxLength="5" placeholder="输入顺序">
        </td>
    </tr>
    <tr>
        <th><span class="must">*</span>是否显示：</th>
        <td>
            <#list yesNo?if_exists as t>
            <label>
            <input type="radio" <#if (theme.isShow?exists && theme.isShow==t) >checked</#if> class="chk" name="theme.isShow" value="${t}">${t.nameValue!} 
            </label>
            </#list>
        </td>
    </tr>
    <tr>
        <th><span class="must">*</span>链接类型：</th>
        <td>
            <#list contentTypes?if_exists as t>
            <#if t.value lt 3>
            <label>
            <input type="radio" <#if (theme.linkType?exists && theme.linkType==t)|| (!theme.linkType?exists && t=="NONE")>checked</#if> class="chk contentType" name="theme.linkType" value="${t}">${t.nameValue!} 
            </label>
            </#if>
            </#list>
        </td>
    </tr>
    <tr id="link_url_tr">
        <th><span class="must">*</span>连接地址：</th>
        <td>
            <input type="text" class="input-txt t-500" id="linkUrl" name="theme.linkUrl" maxLength="100" value="${theme.linkUrl!}" />
        </td>
    </tr>
    <tr class="content_tr">
        <th><span class="must">*</span>主题内容：</th>
        <td></td>
    </tr>
    <tr>
        <!--不知道为什么，这么放可以解决ie兼容模式下的布局问题-->
        <table class="form-table validateForm mt-10 " >
        <tr class="content_tr">
        <td colspan="2">
            <div class="ml-20">
                <@ueditor id="editor" name="content" content='${content!}' width='710' height='400' />
            </div>
        </td>
        </tr>
        </table>
    </tr>
    <tr>
        <th>&nbsp;</th>
        <td>
        <a href="javascript:;" class="mt-10 ml-10 abtn abtn-blue theme_save">提交</a>
        <!--前台帮助中心没有开放-->
        <!--<a href="javascript:;" class="mt-10 abtn abtn-green ml-10 content_tr">预览</a>-->  
        <a href="javascript:;" class="mt-10 abtn abtn-green ml-10 theme_edit_bak" >返回</a>
        </td>
    </tr>
</table>
</form>
<#else>
    <div class="public-nodata">主题内容不存在...<a href="javascript:;">返回</a></div>
</#if>
<script>
    $(function(){
        window.HelpThemeEdit.init();
    });
</script>