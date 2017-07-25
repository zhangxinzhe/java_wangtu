<p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>用户信息</span></p>
<div class="wrap" style="">
    <form id="agenycJoinDiv_form" action="" method="post" >
        <input type="hidden" id="id" value="${id!}">
        <table class="form-table">
            <tr>
                <th style="width:100px;"><span class="must">*</span>基地帐号：</th>
                <td>
                    <input type="text" class="input-txt" id="agenycJoinDiv_username" name="user.userName" value="${user.userName!}" regex="/^[a-zA-Z0-9_]+$/" regexMsg="请输入英文字母或是数字,其它字符是不允许的" notNull="true" maxlength="25" placeholder="输入管理员用户名"/>
                </td>
            <tr>
            <th><span class="must">*</span>密码：</th>
            <td>
                <input type="password" class="input-txt" id="agenycJoinDiv_password" name="user.password" notNull="true" maxlength="20" oncopy="return false;" oncut="return false;" onpaste="return false" onkeyup="return showPwdRank(this.value);return false;">
            </td>
            </tr>
            <tr id="levelTr" style="display:none;">
                <th>&nbsp;</th>
                <td>
                    <div id="securityLevel" class="acc-safety acc-safety-low"></div>
                    <span class="c-999">为保证您账号的安全性，请使用为数字+字母的组合，长度在6到20位哦！</span>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>确认密码：</th>
                <td><input type="password" class="input-txt" id="agenycJoinDiv_repassword" notNull="true" maxlength="20"></td>
            </tr>
            </tr>
        </table>
    </form>
</div>
<p class="dd">
    <a class="abtn abtn-blue" href="javascript:;" id="agenycJoinDiv_submit"><span>确定</span></a>
    <a class="abtn abtn-green ml-5 close" href="javascript:;" id="agenycJoinDiv_cancel"><span>取消</span></a>
</p>

<script>
    $(function(){
        $("#agenycJoinDiv_submit").click(function(){
            if(!Verify.checkAllVerify("#agenycJoinDiv_form")) return;
            var userName = $("#agenycJoinDiv_username").val();
            if(Validator.isNotBlank(userName) && (StringUtil.getLength2(userName) > 20||StringUtil.getLength2(userName) < 4)){
                FieldMsg.addFieldError("#agenycJoinDiv_username", "基地帐号长度要在4~20位！", false);
                return;
            }
            var password = $("#agenycJoinDiv_password").val();
            if(Validator.test(/[&;%\'\"<>]/,password)){
                FieldMsg.addFieldError("#agenycJoinDiv_password", "密码不能包含特殊字符！", false);
                return;
            }
            if(!Verify.checkOverLen3("#agenycJoinDiv_password","密码", 6,20))return;
            if($("#agenycJoinDiv_repassword").val() != password){
                FieldMsg.addFieldError("#agenycJoinDiv_repassword", "两次输入的密码不一致！", false);
                return;
            }
            
            var id = $("#id").val();
            Sender.ajax({
                "url" : Domain.cms_path + "/basic/checkAgencyJoin.htm",
                "data" : {"id":id, "userName":userName, "password":password},
                "dataType" : "json",
                "fn" : function(reply){
                    if(reply.isSuccess){
                        Tips.showSuccessMsg({"info":reply.promptMsg, "callFn":function(){
                            document.location.href=Domain.cms_path + "/basic/agencyJoinManage.htm";
                        }});
                    }else{
                        if(Validator.isNotBlank(reply.errorMsg)){
                            Tips.showErrorMsg({
                                "info" : reply.errorMsg,
                                "callFn" : function(){
                                    Sender.openDiv({
                                        "div":"#agenycJoinDiv", 
                                        "closeObject":"#agenycJoinDiv .submit, #agenycJoinDiv .close"
                                    });
                                }
                            });
                        }
                        return;
                    }
                }
            });
        });
    });
</script>