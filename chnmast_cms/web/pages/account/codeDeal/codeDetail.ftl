<#include "/pages/common/commonmacro.ftl" />
<#if verifyCodeDetail?exists>
<input id="codeId" type="hidden" value="${verifyCodeDetail.id!}"/>
<input id="detailState" type="hidden" value="${verifyCodeDetail.useState!}"/>
<table class="form-table validateForm mt-10">
    <tr>
        <th>名称:</th>
        <td>${verifyCodeDetail.courseName!}</td>
    </tr>
    <tr>
        <th>课次/场次:</th>
        <td>${(verifyCodeDetail.startTime?string("yyyy-MM-dd HH:mm"))!}-${verifyCodeDetail.endTime?string("HH:mm")}</td>
    </tr>
    <tr>   
        <th>姓名:</th>
        <td>${verifyCodeDetail.realName!}</td>
    </tr>
    <tr>
        <th>手机号:</th>
        <td>${verifyCodeDetail.phone!}</td>
    </tr>
    <tr>
        <th>状态:</th>
        <td>
        <#if verifyCodeDetail.useState == unUseState>
        <span class="c-green">
        <#else>
        <span class="c-red">
        </#if>
         ${verifyCodeDetail.useState.nameValue!}</span>
        </td>
    </tr>
</table>
<#else>
    <div class="public-nodata" style="margin:60px 0 60px">没有数据哦...</div>
</#if>
<script>
$(function(){
    //如果查询到内容焦点移动到使用按钮，如果没有查询到内容，焦点停留在输入框
    var codeId = $("#codeId").val();
    if(codeId){
        $("#quick_checke_use").focus();
    }else{
        $("#quick_verifycode").focus();
    }
});
</script>
