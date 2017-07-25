<#include "/pages/common/constant.ftl" />
<#include "/pages/common/commonmacro.ftl" />
<#if !isAsyncLoadList?default(false)>
<p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>音乐会关联个人用户</span></p>
<div class="wrap" style="height:225px;">
    <div class="org-search">
        <div class="org-search-tt">
        <form id="relatedStuDiv_form" action="${DOMAIN_CMS}/concert/relatedStu.htm">
            <input style="display:none" />
            <input type="hidden" name="isAsyncLoadList" value="true" />
            <input type="hidden" id="courseId" name="id" value="${id!}" />
            <span>用户名：</span>
            <input type="text" class="input-txt t-200" name="userName" id="relatedStuDiv_userName">
            <a href="javascript:;" class="abtn abtn-blue" id="relatedStuDiv_search">查询</a>
            <span class="c-red ml-20" id="relate_span"><#if relateUser?exists >已关联：${relateUser.userName!}</#if></span>
            <a class="abtn abtn-blue ml-5" href="javascript:;" id="relatedStuDiv_delete" <#if !relateUser?exists >style="display:none;"</#if>>删除关联</a>
        </form>
        </div>
        <div class="org-search-inner" id="relatedStuDiv_listDiv">
        </div>
    </div>
</div>
<p class="dd">
    <a class="abtn abtn-blue" href="javascript:;" id="relatedStuDiv_submit"><span>确定关联</span></a>
    <a class="abtn abtn-green ml-5 close" href="javascript:;" id="relatedStuDiv_esc"><span>关闭</span></a>
</p>
<script>
    $(function(){
        // 初始化列表
        Sender.submitLoad({
            "frm" : "#relatedStuDiv_form",
            "div" : "#relatedStuDiv_listDiv"
        });
        $("#relatedStuDiv_search").click(function(){
            Sender.submitLoad({
                "frm" : "#relatedStuDiv_form",
                "div" : "#relatedStuDiv_listDiv"
            });
        });
        
        $("#relatedStuDiv_userName").keydown(function(e){
            if (window.event) {
                keynum = event.keyCode
            } else if (e.which) {
                keynum = e.which
            }
            if (keynum == 13) {
                $("#relatedStuDiv_search").click();
            }
        });
        $("#relatedStuDiv_delete").click(function(){
            var param = {
                info:"确定要删除关联吗？",
                "callFn":function(){
                    Sender.openDiv({
                        "div" : "#relatedStuDiv",
                        "closeObject" : "#relatedStuDiv .close"
                    });
                    relateStudent(0,"");
                }
            }
            Tips.showConfirmWin(param);
        });
        
        $("#relatedStuDiv_submit").click(function(){
            var obj=$("#relatedStuDiv_listDiv").find('input:radio[name="stu_radio"]:checked');
            if(obj.length == 0){
                Tips.showAlertWin({"info":"请选择一位学员！", "callFn":function(){
                    Sender.openDiv({
                        "div" : "#relatedStuDiv",
                        "closeObject" : "#relatedStuDiv .close"
                    });
                }});
            }else{
                relateStudent(obj.val(),obj.attr("uName"));
            }
        });

        //提交修改关联用户
        function relateStudent(stuId,relateUserName){
            var id = $("#courseId").val();
            var url = "/concert/relatedStuSubmit.htm?id="+id+"&stuId="+stuId;
            var info = "关联成功！";
            if(stuId==0){
                info = "删除成功！"
            }
            if(relateUserName!=''){
                relateUserName = "已关联："+relateUserName;
            }
            $.post(url,null,function(result){
                if(result){
                    if(result.flag){
                        Tips.showAlertWin({"info":info, "callFn":function(){
                            Sender.openDiv({
                                "div" : "#relatedStuDiv",
                                "closeObject" : "#relatedStuDiv .close"
                            });
                            //修改已关联
                            $("#relate_span").html(relateUserName);
                            if(stuId == 0){
                                $("#relatedStuDiv_delete").hide();
                            }
                            else{
                                $("#relatedStuDiv_delete").show();
                            }
                        }});
                    }else{
                        Tips.showErrorWin({"info":result.errorMsg, "callFn":function(){
                            Sender.openDiv({
                                "div" : "#relatedStuDiv",
                                "closeObject" : "#relatedStuDiv .close"
                            });
                        }});
                    }
                }
            },"json");
        }
    });
</script>
<#else><#-- 异步加载列表 -->
    <ul class="org-search-list fn-clear" style="height:130px;">
        <#list list?if_exists as item>
        <li><input type="radio" class="chk" name="stu_radio" value="${item.id}" uName="${item.userName!}">
        ${item.userName!}
        <#if item.realName?exists && item.realName != "" >
            (${item.realName!})
        </#if>
        </li>
        </#list>
    </ul>
    <div class="public-page">
        <@commonPage1 contentDiv="relatedStuDiv_listDiv"/>
    </div>
</#if>
