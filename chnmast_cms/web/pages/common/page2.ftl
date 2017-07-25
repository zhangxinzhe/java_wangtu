<#list pageItems2 as pageItem>

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
function gotoPage(url,rowNum, currentPage,totalNum){
    var sizePerPage = $('#sizePerPage').val();
    if(!sizePerPage || isNaN(sizePerPage) || sizePerPage.indexOf(".") > -1 || sizePerPage < 1 || sizePerPage >100){
      alert('每页条数为正整数，最大不超过100');
      return;
    }
    if(currentPage == undefined && sizePerPage == rowNum){
      return;
    }
    //重新计算总页数
    var totalPage=Math.ceil(totalNum/sizePerPage);
    if(url.indexOf('?') != -1){
      url=url + "&page.rowNum="+sizePerPage;
    }else{
      url=url + "?page.rowNum="+sizePerPage;
    }
    //判断是否是跳转页面
    if (currentPage != undefined){
        var current = $("#currentPage").val();

        if(!current || isNaN(current) || current.indexOf(".") > -1 || current < 1 || current > totalPage){
            alert('跳转页数为正整数，最大不超过' + totalPage);
            return;
        }
        
        url=url.replace("page.currentPage="+currentPage,"page.currentPage="+current);
    }else{
        if(rowNum && sizePerPage != rowNum){
            var reg = new  RegExp("page.currentPage=(\\d+)","g");
            url=url.replace(reg,"page.currentPage=1");
        }
    }
    window.location.href=url;
}
</script>
