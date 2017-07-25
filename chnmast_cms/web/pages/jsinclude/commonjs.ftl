<#if devMode>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js?0"></script>
<script type="text/javascript" src="/js/jquery.json-2.4.js?0"></script>
<script type="text/javascript" src="/js/jquery.PlaceHolder.js?0"></script>
<script type="text/javascript" src="/js/jwindow.js?0"></script>
<script type="text/javascript" src="/js/md5.js?0"></script>
<script type="text/javascript" src="/js/base.js?0"></script>
<script type="text/javascript" src="/js/base_verify.js?0"></script>
<script type="text/javascript" src="/js/myscript.js?0"></script>
<#else>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js?${appsetting.getVersionId()}"></script>
<script type="text/javascript" src="/js/jquery.json-2.4.js?${appsetting.getVersionId()}"></script>
<script type="text/javascript" src="/js/jquery.PlaceHolder.js?${appsetting.getVersionId()}"></script>
<script type="text/javascript" src="/js/jwindow.js?${appsetting.getVersionId()}"></script>
<script type="text/javascript" src="/js/md5.js?${appsetting.getVersionId()}"></script>
<script type="text/javascript" src="/js/chnmaster-cms-base.min.js?${appsetting.getVersionId()}"></script>
</#if>
<script type="text/javascript">
<#--设置域名-->
Domain.home_path = '${appsetting.getParam("domain.home")?default('')}';
Domain.cms_path = '${appsetting.getParam("domain.cms")?default('')}';
Domain.file_path = '${appsetting.getParam("domain.file")?default('')}';
Domain.uploadFile_path = '${appsetting.getParam("domain.upload_file")?default('')}';

(function(){
    f = function(){
        <#if action.hasActionErrors()>
            var htmlErrContent = "";
            <#list actionErrors as item>
                <#if item?exists>
                    var itemMessage = "${item?j_string}";
                    if(itemMessage.indexOf("NO_SECURITY") == 0){
                        var temp = itemMessage.substring(11);
                        itemMessage = temp.replace(/</g, "&lt;").replace(/>/g, "&gt;");
                        itemMessage = "你提交的内容中可能包含不安全内容：【" + itemMessage + "】";
                    }
                    if (htmlErrContent != ""){
                        htmlErrContent += "<br>"; 
                    }
                    htmlErrContent += itemMessage;
                </#if>
            </#list>
            Tips.showErrorMsg({"info":htmlErrContent});
        </#if>
        <#if action.hasActionMessages()>
            var htmlContent = "";
            <#list actionMessages as item>
                <#if item?exists>
                var itemMessage = "${item?j_string}";
                if(itemMessage.indexOf("NO_SECURITY") == 0){
                    var temp = itemMessage.substring(11);
                    itemMessage = temp.replace(/</g, "&lt;").replace(/>/g, "&gt;");
                    itemMessage = "你提交的内容中可能包含不安全内容：【" + itemMessage + "】";
                }
                if (htmlContent != ""){
                    htmlContent += "<br>"; 
                }
                htmlContent+=itemMessage;
                </#if>
            </#list>
            Tips.showAlertWin({"info":htmlContent});
        </#if>
    }

    window.onload = f;
})();

//回车搜索事件
$(function(){
    if (document.getElementById("searchButton")) {
        if ($("#searchButton").parents("form").length>0) {
            var elements = $("#searchButton").parents("form")[0].elements;
            for (i = 0; i < elements.length; i++) {
                elements[i].onkeyup = function(e) {
                    if (window.event) {
                        keynum = event.keyCode
                    } else if (e.which) {
                        keynum = e.which
                    }
                    if (keynum == 13) {
                        document.getElementById("searchButton").click();
                    }
                }
            }
        }
    }
    
    if (document.getElementById("search")) {
        if ($("#search").parents("form").length>0) {
            var elements = $("#search").parents("form")[0].elements;
            for (i = 0; i < elements.length; i++) {
                elements[i].onkeyup = function(e) {
                    if (window.event) {
                        keynum = event.keyCode
                    } else if (e.which) {
                        keynum = e.which
                    }
                    if (keynum == 13) {
                        document.getElementById("search").click();
                    }
                }
            }
        }
    }
});
</script>