(function($, window, document){
	var pub = {};
	window.addSystemUser= pub;
    pub.init = function() {
        bindEvent();
    }

    //绑定页面事件方法
    function bindEvent() {
        $("#saveBtn").bind("click", saveData);
        $("#backBtn").bind("click", callBack);
    }
    
  //保存数据,数据校验
    function saveData(){
    	var allTheCheckIsTrue=true;
    	FieldMsg.clearFieldError("#creatForm"); //清除各字段的报错提示
    	//用于正确信息判断
    	if(Verify.checkNotEmpty("#userName", "用户名") && Verify.checkUserName("#userName", "用户名")){ 
    		FieldMsg.addFieldSuccess("#userName"); 		
    	}else{
        	allTheCheckIsTrue=false;
        }
    	if(Verify.checkNotEmpty("#realName", "姓名")){ 
    	   	FieldMsg.addFieldSuccess("#realName"); 		
    	}else{
        	allTheCheckIsTrue=false;
        }
    	if(Verify.checkOverLen3("#password","密码", 6,20)){ 
        	FieldMsg.addFieldSuccess("#password"); 		
        }else{
        	allTheCheckIsTrue=false;
        }
    	if(Verify.checkOverLen3("#repassword","确认密码", 6,20)){
        	if($("#repassword").val()==$("#password").val()){
        	FieldMsg.addFieldSuccess("#repassword"); 
        	}else{
        		FieldMsg.addFieldError("#repassword","两次输入的密码不一致");
        		return;
        	}		
        }else{
        	allTheCheckIsTrue=false;
        }
    	if(!allTheCheckIsTrue){
    		return;
    	}
    	//整个表单所有数据验证   错误信息
       	if(!Verify.checkAllVerify("#creatForm")) return; 
        var data = $('#creatForm').serialize();
        var params ={
        		"url":Domain.cms_path+"/system/addSystemUser.htm",
        		"data":data,
        		"dataType":"json",
        		"fn":function(reply) {
        			FieldMsg.drawMessages(reply, callBack, null);
		        }
        };
        Sender.ajax(params);
    }
    
    //返回
    function callBack(){
    	 window.location.href=Domain.cms_path+"/system/systemUserManage.htm";
    }
 
})(jQuery, window, document);