<#list pageItems?if_exists as pageItem>
 
    <#if pageItem.pages>
    <#if pageItem.hasHref>
    <a href="javascript:void(0)" class='ml-10' onclick="${pageItem.href!};return false;">${pageItem.text!}</a>
    <#else>
    <span class="no">${pageItem.text!}</span>
    </#if>
 <#else>
    ${pageItem.text!}
    </#if>

</#list>
<div class="clear"></div>
<script type="text/javascript">
 function gotoPage(url){
    window.location.href=url;
    
}
</script>