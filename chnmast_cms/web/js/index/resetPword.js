(function($, window, document){
	var pub = {};
	window.resetPword= pub;
    pub.init = function() {
        bindEvent();
    }

    //绑定页面事件方法
    function bindEvent() {
        $("#saveBtn").bind("click", saveData);
    }
    
  //保存数据,数据校验
    function saveData(){
    	var allTheCheckIsTrue=true;
    	FieldMsg.clearFieldError("#passwordForm"); //清除各字段的报错提示
    	//用于正确信息判断
    	if(!Verify.checkOverLen3("#oldPassword","旧密码", 6,20)){ 
        	allTheCheckIsTrue=false;
        }
    	if(!Verify.checkOverLen3("#newPassword","新密码", 6,20)){ 
    		allTheCheckIsTrue=false;
    	}
    	if(!Verify.checkOverLen3("#reNewPassword","确认密码", 6,20)){
        	allTheCheckIsTrue=false;
        }else{
        	if($("#reNewPassword").val() != $("#newPassword").val()){
        		FieldMsg.addFieldError("#reNewPassword","两次输入的密码不一致");
        		allTheCheckIsTrue=false;
        	}		
        }
    	if($("#oldPassword").val() == $("#newPassword").val()){
    		FieldMsg.addFieldError("#newPassword","新密码不可和旧密码相同");
    		allTheCheckIsTrue=false;
    	}
    	if(!allTheCheckIsTrue){
    		return;
    	}
    	//整个表单所有数据验证   错误信息
       	if(!Verify.checkAllVerify("#passwordForm")) return; 
        var data = $('#passwordForm').serialize();
        var params ={
        		"url":Domain.cms_path+"/resetPword.htm",
        		"data":data,
        		"dataType":"json",
        		"fn":function(reply) {
        			if(reply.isSuccess){
        				//提示成功
        				var params;
        				params = {"info":reply.promptMsg, "callFn":successGo};
        				Tips.showSuccessMsg(params);
        			}else{
        				//提示出错
        				FieldMsg.addFieldError("#oldPassword", reply.errorMsg);
        				return;
        			}
		        }
        };
        Sender.ajax(params);
    }
    function successGo(){
    	location.href=Domain.cms_path+"/logout.htm";
    }
 
})(jQuery, window, document);