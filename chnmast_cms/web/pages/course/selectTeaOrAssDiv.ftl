<#include "/pages/common/constant.ftl" />
<#include "/pages/common/commonmacro.ftl" />
<#if !isAsyncLoadTeaList?default(false)>
<p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span><#if flag == 1>选择主讲名师<#else>选择助教</#if></span></p>
<div class="wrap" style="height:225px;">
    <div class="org-search">
        <div class="org-search-tt">
        <form id="selectTeaDiv_form" action="${DOMAIN_CMS}/course/selectTeaOrAss.htm">
            <input style="display:none" />
            <input type="hidden" name="flag" value="${flag}" />
            <input type="hidden" name="isAsyncLoadTeaList" value="true" />
            <span><#if flag == 1>名师姓名：<#else>助教姓名：</#if></span>
            <input type="text" class="input-txt" name="realname" id="selectTeaDiv_realname">
            <a href="javascript:;" class="abtn abtn-blue ml-20" id="selectTeaDiv_search">查询</a>
        </form>
        </div>
        <div class="org-search-inner" id="selectTeaDiv_listDiv">
        </div>
    </div>
</div>
<p class="dd">
    <a class="abtn abtn-blue submit" href="javascript:;" id="selectTeaDiv_select"><span>确定</span></a>
    <a class="abtn abtn-green ml-5 close" href="javascript:;" id="selectTeaDiv_esc"><span>取消</span></a>
</p>
<script>
    // 初始化列表
    Sender.submitLoad({
        "frm" : "#selectTeaDiv_form",
        "div" : "#selectTeaDiv_listDiv"
    });

    $(function(){
        $("#selectTeaDiv_search").click(function(){
            Sender.submitLoad({
                "frm" : "#selectTeaDiv_form",
                "div" : "#selectTeaDiv_listDiv"
            });
        });
        
        $("#selectTeaDiv_realname").keydown(function(e){
            if (window.event) {
                keynum = event.keyCode
            } else if (e.which) {
                keynum = e.which
            }
            if (keynum == 13) {
                $("#selectTeaDiv_search").click();
            }
        });
        
        $("#selectTeaDiv_select").click(function(){
            var obj=$("#selectTeaDiv_listDiv").find('input:radio[name="tea_radio"]:checked');
            if(obj.length == 0){
                Tips.showAlertWin({"info":"请选择一位名师或助教！", "callFn":function(){
                    Sender.openDiv({
                        "div" : "#selectTeaDiv",
                        "closeObject" : "#selectTeaDiv .close"
                    });
                }});
            }else{
                <#if flag == 1>
                    $("#teaId").val(obj.val());
                    $("#teaRealName").val(obj.attr("dataValue"));
                <#else>
                    $("#assId").val(obj.val());
                    $("#assRealName").val(obj.attr("dataValue"));
                </#if>
                $("#selectTeaDiv_esc").click();
            }
        });
    });
</script>
<#else><#-- 异步加载列表 -->
    <ul class="org-search-list fn-clear" style="height:130px;">
        <#list list?if_exists as item>
        <li><input type="radio" class="chk" name="tea_radio" value="${item.id}" dataValue="${item.realName!}">${item.realName!}</li>
        </#list>
    </ul>
    <div class="public-page">
        <@commonPage1 contentDiv="selectTeaDiv_listDiv"/>
    </div>
</#if>
